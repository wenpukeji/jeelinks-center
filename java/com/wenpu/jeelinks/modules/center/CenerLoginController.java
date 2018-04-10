/**
 * Copyright (c) 2011-2014, hubin (243194995@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wenpu.jeelinks.modules.center;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.wenpu.jeelinks.common.MyCaptcha;
import com.wenpu.jeelinks.common.web.BaseController;
import com.wenpu.jeelinks.modules.center.entity.EUser;
import com.wenpu.jeelinks.modules.center.service.EUserService;
import com.wenpu.jeelinks.modules.sys.service.SystemService;
import com.wenpu.jeelinks.sso.SSOConfig;
import com.wenpu.jeelinks.sso.SSOHelper;
import com.wenpu.jeelinks.sso.SSOToken;
import com.wenpu.jeelinks.sso.annotation.Action;
import com.wenpu.jeelinks.sso.annotation.Login;
import com.wenpu.jeelinks.sso.common.util.HttpUtil;
import com.wenpu.jeelinks.sso.common.util.RandomUtil;
import com.wenpu.jeelinks.sso.web.waf.request.WafRequestWrapper;

/**
 * 登录
 */
@Controller
public class CenerLoginController extends BaseController {
	@Autowired
	EUserService eUserService;
	@Autowired
	SystemService systemService;

	/**
	 * 登录 （注解跳过权限验证）
	 */
	@Login(action = Action.Skip)
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		if (SSOHelper.getToken(request) != null) {
			return "redirect:" + HttpUtil.decodeURL(request.getParameter("returnUrl"));
		}
		model.addAttribute("returnUrl", HttpUtil.decodeURL(request.getParameter("returnUrl")));
		model.addAttribute(MyCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		return "/login";
	}

	/**
	 * 登录 （注解跳过权限验证）
	 */
	@Login(action = Action.Skip)
	@RequestMapping("loginpost")
	public String loginpost(HttpServletRequest request, HttpServletResponse response, Model model) {
		String errorMsg = "用户名或密码错误";
		/**
		 * 过滤 XSS SQL 注入
		 */
		WafRequestWrapper wr = new WafRequestWrapper(request);
		String ctoken = wr.getParameter("ctoken");
		String captcha = wr.getParameter("captcha");
		if (StringUtils.isNotBlank(ctoken) && StringUtils.isNotBlank(captcha)
				&& MyCaptcha.getInstance().verification(wr, ctoken, captcha)) {
			String loginName = wr.getParameter("loginName");
			String password = wr.getParameter("password");
			/**
			 * <p>
			 * 用户存在，签名合法，登录成功 <br>
			 * 进入后台
			 * </p>
			 */
			EUser user = eUserService.findLoginName(loginName);
			if (user != null && systemService.validatePassword(password, user.getUserPassword())) {
				SSOToken st = new SSOToken(request);
				st.setId(user.getId());
				
				HashMap<String, String> hm = new HashMap();
				hm.put("loginName", loginName);
				hm.put("role", "1");
				hm.put("roleName", "学习管理员");
				hm.put("schoolId", "10001");
				hm.put("schoolName", "测试学校");
				
				st.setData(JSON.toJSONString(hm));

				
				st.setUid(user.getId());
				// 记住密码，设置 cookie 时长 1 周 = 604800 秒
				String rememberMe = wr.getParameter("rememberMe");
				if ("on".equals(rememberMe)) {
					request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
				}

				SSOHelper.setSSOCookie(request, response, st, true);
				String url = request.getParameter("returnUrl");
				if (StringUtils.isBlank(url)) {
					return "redirect:" + url; 
				} else {
					return "redirect:index"; 
				}
				
			}
		} else {
			errorMsg = "验证码错误";
		}
		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("returnUrl", request.getParameter("returnUrl"));
		model.addAttribute(MyCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		return "/login";
	}
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		SSOToken token = (SSOToken)SSOHelper.attrToken(request);
		EUser user = eUserService.get(token.getIp());
		model.addAttribute("user", user);
		return "index";
	}
}
