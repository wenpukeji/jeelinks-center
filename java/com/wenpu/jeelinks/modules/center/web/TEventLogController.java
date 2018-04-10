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
import com.wenpu.jeelinks.modules.center.entity.TEventLog;
import com.wenpu.jeelinks.modules.center.service.TEventLogService;

/**
 * 积分日志Controller
 * @author webcat
 * @version 2017-08-12
 */
@Controller
@RequestMapping(value = "${adminPath}/center/tEventLog")
public class TEventLogController extends BaseController {

	@Autowired
	private TEventLogService tEventLogService;
	
	@ModelAttribute
	public TEventLog get(@RequestParam(required=false) String id) {
		TEventLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tEventLogService.get(id);
		}
		if (entity == null){
			entity = new TEventLog();
		}
		return entity;
	}
	
	@RequiresPermissions("center:tEventLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(TEventLog tEventLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TEventLog> page = tEventLogService.findPage(new Page<TEventLog>(request, response), tEventLog); 
		model.addAttribute("page", page);
		return "modules/center/tEventLogList";
	}

	@RequiresPermissions("center:tEventLog:view")
	@RequestMapping(value = "form")
	public String form(TEventLog tEventLog, Model model) {
		model.addAttribute("tEventLog", tEventLog);
		return "modules/center/tEventLogForm";
	}

	@RequiresPermissions("center:tEventLog:edit")
	@RequestMapping(value = "save")
	public String save(TEventLog tEventLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tEventLog)){
			return form(tEventLog, model);
		}
		tEventLogService.save(tEventLog);
		addMessage(redirectAttributes, "保存webcat成功");
		return "redirect:"+Global.getAdminPath()+"/center/tEventLog/?repage";
	}
	
	@RequiresPermissions("center:tEventLog:edit")
	@RequestMapping(value = "delete")
	public String delete(TEventLog tEventLog, RedirectAttributes redirectAttributes) {
		tEventLogService.delete(tEventLog);
		addMessage(redirectAttributes, "删除webcat成功");
		return "redirect:"+Global.getAdminPath()+"/center/tEventLog/?repage";
	}

}