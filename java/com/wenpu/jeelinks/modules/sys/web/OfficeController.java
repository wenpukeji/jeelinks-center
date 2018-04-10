/*
 * Copyright (c) 2017.
 * Date:17-8-22 上午10:48
 * Author:liuhui
 */
package com.wenpu.jeelinks.modules.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wenpu.jeelinks.common.config.Global;
import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.utils.StringUtils;
import com.wenpu.jeelinks.common.web.BaseController;
import com.wenpu.jeelinks.modules.sys.entity.Area;
import com.wenpu.jeelinks.modules.sys.entity.Office;
import com.wenpu.jeelinks.modules.sys.service.AreaService;
import com.wenpu.jeelinks.modules.sys.service.OfficeService;
import com.wenpu.jeelinks.modules.sys.utils.DictUtils;
import com.wenpu.jeelinks.modules.sys.utils.UserUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 机构Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;
	@Autowired
	private AreaService areaService;
	
	@ModelAttribute("office")
	public Office get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return officeService.get(id);
		}else{
			return new Office();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {""})
	public String index(Office office, Model model) {
//        model.addAttribute("list", officeService.findAll());
		return "modules/sys/officeIndex";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"linkedList"})
	public String linkedToAreaList(Office office, Model model,HttpServletRequest request,HttpServletResponse response) {
		Page<Office> page = new Page<>(request,response,10);
//		Office parent = UserUtils.getUser().getCompany();
//		List<String> officeIds = officeService.getAllSubOffices(parent);
//		office.setIds(officeIds);
		page.setOrderBy("a.link_time desc");
		office.setPage(page);
		office.setLinkStatus(2);
		List<Office> schools = officeService.findSchoolList(office);
		page.setList(schools);
		page.setCount(schools.size());
		model.addAttribute("page", page);
		return "modules/sys/school/linkedList";
	}
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"notLinkedList"})
	public String notLinkedToAreaList(Office office, Model model,HttpServletRequest request,HttpServletResponse response) {
		Page<Office> page = new Page<>(request,response,10);
		page.setOrderBy("a.create_date desc");
		office.setPage(page);
		office.setLinkStatus(1);
		List<Office> offices = officeService.notLinkedToAreaList(office);
		page.setList(offices);
		model.addAttribute("page",page);
		return "modules/sys/school/notLinkedList";
	}
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"link"})
	public String link(String linkedId,String officeIds,Model model){
		String[] ids = officeIds.split(",");
		officeService.link(Arrays.asList(ids), officeService.getCompany(linkedId));
		return "redirect:"+Global.getAdminPath()+"/sys/office/linkedList";
	}
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = {"removeLink"})
	public String removeLink(String officeId,Model model) {
		officeService.removeLink(officeId);
		return "redirect:"+Global.getAdminPath()+"/sys/office/linkedList";
	}


	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"schoolList"})
	public String schoolList(Office office,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<Office> page = new Page<>(request,response,10);
		page.setOrderBy("a.create_date desc");
		office.setPage(page);
		if (office.getArea() != null && StringUtils.isBlank(office.getArea().getId())){
			office.setArea(null);
		}
		if (office.getArea() != null && StringUtils.isNotBlank(office.getArea().getId())){
			List<Area> areas = areaService.findByParentId(office.getArea().getId());
			List<String> areaIds = new ArrayList<>();
			for (Area area : areas) {
				areaIds.add(area.getId());
			}
			areaIds.add(office.getArea().getId());
			office.setAreaIds(areaIds);
		}
		List<Office> schools = officeService.findSchoolList(office);
		page.setList(schools);
		model.addAttribute("page", page);
		return "modules/sys/school/schoolList";
	}
	
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "editSchool")
	public String editSchool(Office office,@RequestParam(required=false) String extId, Model model) {
		try{
		    if(office.getParent()!=null && StringUtils.isNotBlank(office.getParent().getId())&&!"0".equals(office.getParent().getId())){//根节点没有父id
				Office parentOffice=officeService.get(office.getParent().getId());
				if(parentOffice!=null){
					office.setParent(parentOffice);
					if (!"1".equals(office.getOfficeType())){
						office.setArea(parentOffice.getArea());
					}
				}
			}
		}catch(Exception e){
			
		}
		model.addAttribute("extId", extId);
		model.addAttribute("office", office);
		return "modules/sys/school/schoolForm";
		
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"list"})
	public String list(Office office, @RequestParam(required=false) String extId, Model model) {
		List<Office> list = null;

		if (office == null) {
			office = new Office();
		}

		if(StringUtils.isBlank(office.getParentIds())){
			if(StringUtils.isBlank(extId)){
				if(!UserUtils.getUser().getId().equals("1")) //非超级管理员
				{
					extId = UserUtils.getUser().getOffice().getParentIds();
				}else
				{
					extId = "0,";
				}
			}
			office.setParentIds(extId);
			list = officeService.findList(office);
		}else{
			System.out.println(extId);
			if(StringUtils.isBlank(extId) && !"0".equals(extId)){
				extId = office.getParentIds()+office.getId();
			}
			System.out.println(extId);
			Office offices = new Office();
			offices.setParentIds(extId);
			list = officeService.findList(offices);
		}
		if(list.size()<1){
			if(office.getName()!=null)
			{
				model.addAttribute("message", office.getName()+"下无分支机构！");
			}

		}else{
			for (Office office2 : list) {
				String type=office2.getType();
				if(("1").equals(type)){
					office2.setType("机构");
				}
				if(("2").equals(type)){
					office2.setType("部门");
				}

				office2.setUseable(DictUtils.getDictLabel(office2.getUseable(), "yes_no", ""));
				office2.setEnable(DictUtils.getDictLabel(office2.getEnable(), "yes_no", ""));


			}
		}
		model.addAttribute("office", office);
		model.addAttribute("extId", extId);
		model.addAttribute("list", list);
		return "modules/sys/officeList";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "form")
	public String form(Office office,@RequestParam(required=false) String extId, Model model) {
		/*User user = UserUtils.getUser();
		if (office.getParent()==null || office.getParent().getId()==null){
			//office.setParent(user.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		if (office.getArea()==null){
		//	office.setArea(user.getOffice().getArea());
		}*/
		// 自动获取排序号
	/*	if (StringUtils.isBlank(office.getId())&&office.getParent()!=null){
			int size = 0;
			List<Office> list = officeService.findAll();
			for (int i=0; i<list.size(); i++){
				Office e = list.get(i);
				if (e.getParent()!=null && e.getParent().getId()!=null
						&& e.getParent().getId().equals(office.getParent().getId())){
					size++;
				}
			}
			office.setCode(office.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size+1 : 1), 3, "0"));
		}*/
		
		try{
		    if(office.getParent()!=null && StringUtils.isNotBlank(office.getParent().getId())&&!"0".equals(office.getParent().getId())){//根节点没有父id
				Office parentOffice=officeService.get(office.getParent().getId());
				if(parentOffice!=null){
					office.setParent(parentOffice);
				}
			}
		    if (StringUtils.isNotBlank(office.getAreaId())) {
		    	  office.setArea(areaService.get(office.getAreaId()));
		    }
		  
		}catch(Exception e){
			
		}
		model.addAttribute("extId", extId);
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "saveSchool")
	public String saveSchool(Office office, @RequestParam(required=false) String extId, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/";
		}
		office.setGrade("1");//可能是默认值
		office.setParent(UserUtils.getUser().getCompany());
		if(office.getId()!=null){
			Office officeOld=officeService.get(new Office(office.getId()));
			if(officeOld!=null){
				office.setParent(officeOld.getParent());
				if(officeOld.getGrade()!=null){
					office.setGrade(officeOld.getGrade());
				}
			}
		}
		if (!beanValidator(model, office)){
			return form(office,extId, model);
		}
		officeService.save(office);
		return "redirect:" + adminPath + "/sys/office/schoolList?repage&extId="+extId;
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	public String save(Office office, @RequestParam(required=false) String extId, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/";
		}
		if (!beanValidator(model, office)){
			return form(office,extId, model);
		}
		officeService.save(office);
		System.out.println(extId);
		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?repage&extId="+extId;
		/*if(office.getChildDeptList()!=null){
			Office childOffice = null;
			int sort =1;
			try {
				sort =office.getSort();
			} catch (Exception e) {
				// TODO: handle exception
			}

			for(String id : office.getChildDeptList()){
				childOffice = new Office();
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				childOffice.setParent(office);
				childOffice.setArea(office.getArea());
				childOffice.setType("2");
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade())+1));
				childOffice.setUseable(Global.YES);
				childOffice.setSort(sort);
				officeService.save(childOffice);
			}
		}else
		{
			officeService.save(office);

		}*/
	}
	
	@RequiresPermissions("sys:office:delete")
	@RequestMapping(value = "delete")
	public String delete(Office office, @RequestParam(required=false) String extId, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/list";
		}
		String officeType = "1".equals(office.getOfficeType()) ? "学校" : "机构";
		int result=officeService.checkAndDelete(office);
		if(result>0){
			addMessage(redirectAttributes, "删除"+officeType+"成功");
		}else{
			addMessage(redirectAttributes,"删除"+officeType+"失败,请先删除【"+office.getName()+"】下及所有子"+officeType+"下的用户后重试");
		}
		if ("1".equals(office.getOfficeType())){
			return "redirect:" + adminPath + "/sys/office/schoolList?repage&extId="+extId;
		}else{
			return "redirect:" + adminPath + "/sys/office/list?repage&extId="+extId;
		}
	}

	/**
	 * 获取全部机构JSON数据。
	 * @param extId 排除的ID
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,  @RequestParam(required=false) String pId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response, HttpServletRequest request) {
		List<Office> list = null;
	  
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			 pId = UserUtils.getUser().getOffice().getParentId();
			 list = officeService.findList(isAll,pId);
			 list.add(0,officeService.get(pId));
		}else {
			 list = officeService.findList(isAll);
		}
		 
	 
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
//			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
//					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
//					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
//					){//&& Global.YES.equals(e.getUseable())
				if ((StringUtils.isNotBlank(extId) && (extId.equals(e.getId()) || e.getParentIds().indexOf(extId)!=-1))){
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && ("3".equals(e.getType()) || "1".equals(e.getType()))){
					map.put("isParent", true);
				}
				mapList.add(map);
//			}
		}
		return mapList;
	}
	
	
	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData3")
	public List<Map<String, Object>> treeCompanyData(@RequestParam(required=false) String extId,  @RequestParam(required=false) String pId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response, HttpServletRequest request) {
		List<Office> list = null;
	  
		List<Map<String, Object>> mapList = Lists.newArrayList();
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			 pId = UserUtils.getUser().getOffice().getParentId();
			 list = officeService.findList(isAll,pId);
			 list.add(0,officeService.get(pId));
		}else {
			 list = officeService.findList(isAll);
		}
		 
	 
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					){//&& Global.YES.equals(e.getUseable())
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && ("3".equals(e.getType()) || "1".equals(e.getType()))){
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	
	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData2")
	public List<Map<String, Object>> treeData2(@RequestParam(required=false) String extId,  @RequestParam(required=false) String pId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response, HttpServletRequest request) {
		List<Office> list = null;
	  
		List<Map<String, Object>> mapList = Lists.newArrayList();
		pId = UserUtils.getUser().getOffice().getParentId();
		list = officeService.findList(isAll,pId);
		 
	 
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
		
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && "3".equals(type)){
					map.put("isParent", true);
				}
				mapList.add(map);
			
		}
		return mapList;
	}
	

	/**
	 * 获取机构JSON数据,只有公司。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData4")
	public List<Map<String, Object>> treeData4(@RequestParam(required=false) String extId,  @RequestParam(required=false) String pId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response, HttpServletRequest request) {
		List<Office> list = null;
	  
		List<Map<String, Object>> mapList = Lists.newArrayList();
		pId = UserUtils.getUser().getCompany().getId();
		list = officeService.findAllCompanyList(isAll,pId);
		 
	 
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
		
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				
				mapList.add(map);
			
		}
		return mapList;
	}
	
	public static void sortList(List<Office> list, List<Office> sourcelist,
			String string) {
		for (int i = 0; i < sourcelist.size(); i++) {
			Office e = sourcelist.get(i);
			if (e.getParent() != null && e.getParent().getId() != null
					&& e.getParent().getId().equals(string)) {
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j = 0; j < sourcelist.size(); j++) {
					Office child = sourcelist.get(j);
					if (child.getParent() != null
							&& child.getParent().getId() != null
							&& child.getParent().getId().equals(e.getId())) {
						sortList(list, sourcelist, e.getId());
						break;
					}
				}
			}
		}
	}
}
