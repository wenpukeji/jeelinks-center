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
import com.wenpu.jeelinks.modules.center.entity.TVoteRecord;
import com.wenpu.jeelinks.modules.center.service.TVoteRecordService;

/**
 * 投票Controller
 * @author webcat
 * @version 2017-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/tVoteRecord")
public class TVoteRecordController extends BaseController {

	@Autowired
	private TVoteRecordService tVoteRecordService;
	
	@ModelAttribute
	public TVoteRecord get(@RequestParam(required=false) String id) {
		TVoteRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tVoteRecordService.get(id);
		}
		if (entity == null){
			entity = new TVoteRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:tVoteRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(TVoteRecord tVoteRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TVoteRecord> page = tVoteRecordService.findPage(new Page<TVoteRecord>(request, response), tVoteRecord); 
		model.addAttribute("page", page);
		return "modules/cms/tVoteRecordList";
	}

	@RequiresPermissions("cms:tVoteRecord:view")
	@RequestMapping(value = "form")
	public String form(TVoteRecord tVoteRecord, Model model) {
		model.addAttribute("tVoteRecord", tVoteRecord);
		return "modules/cms/tVoteRecordForm";
	}

	@RequiresPermissions("cms:tVoteRecord:edit")
	@RequestMapping(value = "save")
	public String save(TVoteRecord tVoteRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tVoteRecord)){
			return form(tVoteRecord, model);
		}
		tVoteRecordService.save(tVoteRecord);
		addMessage(redirectAttributes, "保存投票成功");
		return "redirect:"+Global.getAdminPath()+"/cms/tVoteRecord/?repage";
	}
	
	@RequiresPermissions("cms:tVoteRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(TVoteRecord tVoteRecord, RedirectAttributes redirectAttributes) {
		tVoteRecordService.delete(tVoteRecord);
		addMessage(redirectAttributes, "删除投票成功");
		return "redirect:"+Global.getAdminPath()+"/cms/tVoteRecord/?repage";
	}

}