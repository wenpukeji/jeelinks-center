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
import com.wenpu.jeelinks.modules.center.entity.TFeedback;
import com.wenpu.jeelinks.modules.center.service.TFeedbackService;

/**
 * 用户反馈Controller
 * @author webcat
 * @version 2017-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/center/tFeedback")
public class TFeedbackController extends BaseController {

	@Autowired
	private TFeedbackService tFeedbackService;
	
	@ModelAttribute
	public TFeedback get(@RequestParam(required=false) String id) {
		TFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tFeedbackService.get(id);
		}
		if (entity == null){
			entity = new TFeedback();
		}
		return entity;
	}
	
	@RequiresPermissions("center:tFeedback:view")
	@RequestMapping(value = {"list", ""})
	public String list(TFeedback tFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TFeedback> page = tFeedbackService.findPage(new Page<TFeedback>(request, response), tFeedback); 
		model.addAttribute("page", page);
		model.addAttribute("tFeedback", tFeedback);
		return "modules/center/tFeedbackList";
	}

	@RequiresPermissions("center:tFeedback:view")
	@RequestMapping(value = "form")
	public String form(TFeedback tFeedback, Model model) {
		model.addAttribute("tFeedback", tFeedback);
		return "modules/center/tFeedbackForm";
	}

	@RequiresPermissions("center:tFeedback:edit")
	@RequestMapping(value = "save")
	public String save(TFeedback tFeedback, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tFeedback)){
			return form(tFeedback, model);
		}
		tFeedbackService.save(tFeedback);
		addMessage(redirectAttributes, "保存用户反馈成功");
		return "redirect:"+Global.getAdminPath()+"/center/tFeedback/?repage";
	}
	
	@RequiresPermissions("center:tFeedback:edit")
	@RequestMapping(value = "delete")
	public String delete(TFeedback tFeedback, RedirectAttributes redirectAttributes) {
		tFeedbackService.delete(tFeedback);
		addMessage(redirectAttributes, "删除用户反馈成功");
		return "redirect:"+Global.getAdminPath()+"/center/tFeedback/?repage";
	}

}