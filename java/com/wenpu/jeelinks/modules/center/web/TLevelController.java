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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenpu.jeelinks.common.config.Global;
import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.utils.StringUtils;
import com.wenpu.jeelinks.common.web.BaseController;
import com.wenpu.jeelinks.modules.center.entity.TLevel;
import com.wenpu.jeelinks.modules.center.service.TLevelService;
import com.wenpu.jeelinks.modules.sys.service.SystemService;

/**
 * 用户等级Controller
 * @author webcat
 * @version 2017-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/center/tLevel")
public class TLevelController extends BaseController {

	@Autowired
	private TLevelService tLevelService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public TLevel get(@RequestParam(required=false) String id) {
		TLevel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tLevelService.get(id);
		}
		if (entity == null){
			entity = new TLevel();
		}
		return entity;
	}
	
	@RequiresPermissions("center:tLevel:view")
	@RequestMapping(value = {"list", ""})
	public String list(TLevel tLevel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TLevel> page = tLevelService.findPage(new Page<TLevel>(request, response), tLevel); 
		for (TLevel l : page.getList()) {
			l.setUpdateBy(systemService.getUser(l.getUpdateBy().getId()));
		}
		model.addAttribute("tLevel", tLevel);
		model.addAttribute("page", page);
		return "modules/center/tLevelList";
	}

	@RequiresPermissions("center:tLevel:view")
	@RequestMapping(value = "form")
	public String form(TLevel tLevel, Model model) {
		model.addAttribute("tLevel", tLevel);
		return "modules/center/tLevelForm";
	}

	@RequiresPermissions("center:tLevel:edit")
	@RequestMapping(value = "save")
	public String save(TLevel tLevel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tLevel)){
			return form(tLevel, model);
		}
		tLevelService.save(tLevel);
		addMessage(redirectAttributes, "保存用户等级成功");
		return "redirect:"+Global.getAdminPath()+"/center/tLevel/?repage";
	}
	
	@RequiresPermissions("center:tLevel:edit")
	@RequestMapping(value = "delete")
	public String delete(TLevel tLevel, RedirectAttributes redirectAttributes) {
		tLevelService.delete(tLevel);
		addMessage(redirectAttributes, "删除用户等级成功");
		return "redirect:"+Global.getAdminPath()+"/center/tLevel/?repage";
	}

}