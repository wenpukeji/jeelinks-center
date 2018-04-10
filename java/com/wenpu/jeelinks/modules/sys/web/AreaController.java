/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wenpu.jeelinks.common.config.Global;
import com.wenpu.jeelinks.common.mapper.JsonMapper;
import com.wenpu.jeelinks.common.utils.StringUtils;
import com.wenpu.jeelinks.common.web.BaseController;
import com.wenpu.jeelinks.modules.sys.entity.Area;
import com.wenpu.jeelinks.modules.sys.service.AreaService;
import com.wenpu.jeelinks.modules.sys.service.OfficeService;
import com.wenpu.jeelinks.modules.sys.utils.UserUtils;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute("area")
	public Area get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return areaService.get(id);
		}else{
			return new Area();
		}
	}
/*
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list", ""})
	public String list(Area area, Model model) {
		model.addAttribute("list", areaService.findAll());
		return "modules/sys/areaList";
	}
	*/
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list", ""})
	public String list(Area area, Model model) {
		model.addAttribute("list", areaService.findAll());
		return "modules/sys/areaList";
	}
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"findByParent"})
	@ResponseBody
	public String findByParent(String parentId, Model model) {
		if(parentId==null||StringUtils.isBlank(parentId)){
			parentId="0";
		}
		List<Area> areaList=areaService.findByParentId(parentId);
		return JsonMapper.toJsonString(areaList);
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "form")
	public String form(Area area, Model model) {
		if (area.getParent()==null||area.getParent().getId()==null){
			area.setParent(UserUtils.getUser().getOffice().getArea());
		}
		area.setParent(areaService.get(area.getParent().getId()));
//		// 自动获取排序号
//		if (StringUtils.isBlank(area.getId())){
//			int size = 0;
//			List<Area> list = areaService.findAll();
//			for (int i=0; i<list.size(); i++){
//				Area e = list.get(i);
//				if (e.getParent()!=null && e.getParent().getId()!=null
//						&& e.getParent().getId().equals(area.getParent().getId())){
//					size++;
//				}
//			}
//			area.setCode(area.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size : 1), 4, "0"));
//		}
		model.addAttribute("area", area);
		return "modules/sys/areaForm";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "save")
	public String save(Area area, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/area";
		}
		if (!beanValidator(model, area)){
			return form(area, model);
		}
		areaService.save(area);
		addMessage(redirectAttributes, "保存区域'" + area.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/area/";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(Area area, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/area";
		}
/*//		if (Area.isRoot(id)){
//			addMessage(redirectAttributes, "删除区域失败, 不允许删除顶级区域或编号为空");
//		}else{
			areaService.delete(area);
			addMessage(redirectAttributes, "删除区域成功");
//		}
*/		
		
		int result=areaService.checkAndDelete(area);
		if(result>0){
			addMessage(redirectAttributes, "删除区域成功");
		}else{
			addMessage(redirectAttributes, "删除区域失败,请先删除【"+area.getName()+"】及所有子区域下的机构后重试");
		}
		
		return "redirect:" + adminPath + "/sys/area/";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Area> list = areaService.findAll();
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	@RequestMapping(value = "handle")
	public void handle(Area area, RedirectAttributes redirectAttributes) {
		List<Area> list = areaService.findList(new Area());
		for (Area a : list) {
			if (a.getId().contains("0000")) {
				a.setParentIds("0,1,");
				Area aa = new Area();
				aa.setId("1");
				a.setParent(aa);
			} else if(a.getId().endsWith("00")){
				a.setParentIds("0," + a.getId().substring(0,2) + "0000,");
				Area aa = new Area();
				aa.setId(a.getId().substring(0,2) + "0000");
				a.setParent(aa);
			} else {
				a.setParentIds("0," + a.getId().substring(0,2) + "0000," + a.getId().substring(0,4)+"00,");
				Area bb = new Area();
				bb.setId(a.getId().substring(0,4)+"00");
				a.setParent(bb);
			}
			areaService.save(a);
		}
	}
}
