/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenpu.jeelinks.common.config.Global;
import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.utils.StringUtils;
import com.wenpu.jeelinks.common.web.BaseController;
import com.wenpu.jeelinks.modules.center.entity.EUser;
import com.wenpu.jeelinks.modules.center.service.EUserService;

/**
 * 用户中心Controller
 * @author webcat
 * @version 2017-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/center/eUser")
public class EUserController extends BaseController {

	@Autowired
	private EUserService eUserService;
	
	@ModelAttribute
	public EUser get(@RequestParam(required=false) String id) {
		EUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = eUserService.get(id);
		}
		if (entity == null){
			entity = new EUser();
		}
		return entity;
	}
	/**
	 * 不同学校下的相同的学号不算做重复
	 * @param oldStdCode
	 * @param stdCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkEuserName")
	public String checkEuserName(String oldUserName,String userName) {
		EUser euser=new EUser();
		euser.setUserName(userName);
		if (userName !=null && userName.equals(oldUserName)) {
			return "true";
		} else if (userName !=null && eUserService.getByUserName(userName) == null) {
			return "true";
		}
		return "false";
	}
	
	@RequiresPermissions("center:eUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(EUser eUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EUser> page = eUserService.findPage(new Page<EUser>(request, response), eUser); 
		model.addAttribute("page", page);
		return "modules/center/eUserList";
	}

	@RequiresPermissions("center:eUser:view")
	@RequestMapping(value = "form")
	public String form(EUser eUser, Model model) {
		model.addAttribute("eUser", eUser);
		return "modules/center/eUserForm";
	}

	@RequiresPermissions("center:eUser:edit")
	@RequestMapping(value = "save")
	public String save(EUser eUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, eUser)){
			return form(eUser, model);
		}
		eUserService.save(eUser);
		addMessage(redirectAttributes, "保存用户中心成功");
		return "redirect:"+Global.getAdminPath()+"/center/eUser/?repage";
	}
	
	@RequiresPermissions("center:eUser:edit")
	@RequestMapping(value = "delete")
	public String delete(EUser eUser, RedirectAttributes redirectAttributes) {
		eUserService.delete(eUser);
		addMessage(redirectAttributes, "删除用户中心成功");
		return "redirect:"+Global.getAdminPath()+"/center/eUser/?repage";
	}

}