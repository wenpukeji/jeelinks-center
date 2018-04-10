/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenpu.jeelinks.common.config.Global;
import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.utils.StringUtils;
import com.wenpu.jeelinks.common.web.BaseController;
import com.wenpu.jeelinks.modules.sys.entity.OfficeLicense;
import com.wenpu.jeelinks.modules.sys.service.OfficeLicenseService;

/**
 * 授权Controller
 * @author webcat
 * @version 2017-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/officeLicense")
public class OfficeLicenseController extends BaseController {

	@Autowired
	private OfficeLicenseService officeLicenseService;
	
	@ModelAttribute
	public OfficeLicense get(@RequestParam(required=false) String id) {
		OfficeLicense entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = officeLicenseService.get(id);
		}
		if (entity == null){
			entity = new OfficeLicense();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:officeLicense:view")
	@RequestMapping(value = {"list", ""})
	public String list(OfficeLicense officeLicense, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OfficeLicense> page = officeLicenseService.findPage(new Page<OfficeLicense>(request, response), officeLicense); 
		model.addAttribute("page", page);
		return "modules/sys/officeLicenseList";
	}

	@RequiresPermissions("sys:officeLicense:view")
	@RequestMapping(value = "form")
	public String form(OfficeLicense officeLicense, Model model) {
		model.addAttribute("officeLicense", officeLicense);
		return "modules/sys/officeLicenseForm";
	}

	@RequiresPermissions("sys:officeLicense:edit")
	@RequestMapping(value = "save")
	public String save(OfficeLicense officeLicense, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, officeLicense)){
			return form(officeLicense, model);
		}
		officeLicenseService.save(officeLicense);
		addMessage(redirectAttributes, "保存授权成功");
		return "redirect:"+Global.getAdminPath()+"/sys/officeLicense/?repage";
	}
	
	@RequiresPermissions("sys:officeLicense:edit")
	@RequestMapping(value = "delete")
	public String delete(OfficeLicense officeLicense, RedirectAttributes redirectAttributes) {
		officeLicenseService.delete(officeLicense);
		addMessage(redirectAttributes, "删除授权成功");
		return "redirect:"+Global.getAdminPath()+"/sys/officeLicense/?repage";
	}

}