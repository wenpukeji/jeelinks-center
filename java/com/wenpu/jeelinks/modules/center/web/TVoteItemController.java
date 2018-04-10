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
import com.wenpu.jeelinks.modules.center.entity.TVoteItem;
import com.wenpu.jeelinks.modules.center.service.TVoteItemService;

/**
 * 投票Controller
 * @author webcat
 * @version 2017-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/tVoteItem")
public class TVoteItemController extends BaseController {

	@Autowired
	private TVoteItemService tVoteItemService;
	
	@ModelAttribute
	public TVoteItem get(@RequestParam(required=false) String id) {
		TVoteItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tVoteItemService.get(id);
		}
		if (entity == null){
			entity = new TVoteItem();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:tVoteItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(TVoteItem tVoteItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TVoteItem> page = tVoteItemService.findPage(new Page<TVoteItem>(request, response), tVoteItem); 
		model.addAttribute("page", page);
		return "modules/cms/tVoteItemList";
	}

	@RequiresPermissions("cms:tVoteItem:view")
	@RequestMapping(value = "form")
	public String form(TVoteItem tVoteItem, Model model) {
		model.addAttribute("tVoteItem", tVoteItem);
		return "modules/cms/tVoteItemForm";
	}

	@RequiresPermissions("cms:tVoteItem:edit")
	@RequestMapping(value = "save")
	public String save(TVoteItem tVoteItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tVoteItem)){
			return form(tVoteItem, model);
		}
		tVoteItemService.save(tVoteItem);
		addMessage(redirectAttributes, "保存投票成功");
		return "redirect:"+Global.getAdminPath()+"/cms/tVoteItem/?repage";
	}
	
	@RequiresPermissions("cms:tVoteItem:edit")
	@RequestMapping(value = "delete")
	public String delete(TVoteItem tVoteItem, RedirectAttributes redirectAttributes) {
		tVoteItemService.delete(tVoteItem);
		addMessage(redirectAttributes, "删除投票成功");
		return "redirect:"+Global.getAdminPath()+"/cms/tVoteItem/?repage";
	}

}