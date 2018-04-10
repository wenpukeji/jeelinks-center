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
import com.wenpu.jeelinks.modules.center.entity.TEvent;
import com.wenpu.jeelinks.modules.center.service.TEventService;

/**
 * 积分事件Controller
 * @author webcat
 * @version 2017-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/center/tEvent")
public class TEventController extends BaseController {

	@Autowired
	private TEventService tEventService;
	
	@ModelAttribute
	public TEvent get(@RequestParam(required=false) String id) {
		TEvent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = new TEvent();
			entity.setId(id);
			entity = tEventService.get(entity);
		}
		if (entity == null){
			entity = new TEvent();
		}
		return entity;
	}
	
	@RequiresPermissions("center:tEvent:view")
	@RequestMapping(value = {"list", ""})
	public String list(TEvent tEvent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TEvent> page = tEventService.findPage(new Page<TEvent>(request, response), tEvent); 
		model.addAttribute("page", page);
		model.addAttribute("tEvent", tEvent);
		return "modules/center/tEventList";
	}

	@RequiresPermissions("center:tEvent:view")
	@RequestMapping(value = "form")
	public String form(TEvent tEvent, Model model) {
		model.addAttribute("tEvent", tEvent);
		return "modules/center/tEventForm";
	}

	@RequiresPermissions("center:tEvent:edit")
	@RequestMapping(value = "save")
	public String save(TEvent tEvent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tEvent)){
			return form(tEvent, model);
		}
		tEventService.save(tEvent);
		addMessage(redirectAttributes, "保存积分事件成功");
		return "redirect:"+Global.getAdminPath()+"/center/tEvent/?repage";
	}
	
	@RequiresPermissions("center:tEvent:edit")
	@RequestMapping(value = "delete")
	public String delete(TEvent tEvent, RedirectAttributes redirectAttributes) {
		tEventService.delete(tEvent);
		addMessage(redirectAttributes, "删除积分事件成功");
		return "redirect:"+Global.getAdminPath()+"/center/tEvent/?repage";
	}

}