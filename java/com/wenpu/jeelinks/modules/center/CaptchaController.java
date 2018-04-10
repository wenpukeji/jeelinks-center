package com.wenpu.jeelinks.modules.center;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenpu.jeelinks.common.MyCaptcha;
import com.wenpu.jeelinks.common.web.BaseController;
import com.wenpu.jeelinks.sso.annotation.Action;
import com.wenpu.jeelinks.sso.annotation.Login;
import com.wenpu.jeelinks.sso.annotation.Permission;

/**
 * <p>
 * 验证码服务
 * </p>
 * 
 * @author hubin
 * @Date 2016-01-06
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {

	/**
	 * 生成图片
	 */
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/image")
	public void image(HttpServletRequest request, HttpServletResponse response, Model model,String ctoken) {
		try {
			MyCaptcha.getInstance().generate(request, response.getOutputStream(), ctoken);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
