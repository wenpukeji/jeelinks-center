/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.BaseEntity;
import com.wenpu.jeelinks.common.service.TreeService;
import com.wenpu.jeelinks.common.utils.SpringContextHolder;
import com.wenpu.jeelinks.common.utils.StringUtils;
import com.wenpu.jeelinks.modules.sys.dao.AreaDao;
import com.wenpu.jeelinks.modules.sys.dao.OfficeDao;
import com.wenpu.jeelinks.modules.sys.dao.UserDao;
import com.wenpu.jeelinks.modules.sys.entity.Area;
import com.wenpu.jeelinks.modules.sys.entity.Office;
import com.wenpu.jeelinks.modules.sys.entity.User;
import com.wenpu.jeelinks.modules.sys.utils.UserUtils;

import java.util.*;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	@Autowired
	private AreaDao areaDao;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	UserDao userDao;

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}

	public List<Office> findList(Boolean isAll,String extId){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList(extId);
		}else{
			return UserUtils.getOfficeList(extId);
		}
	}
	
	public List<Office> findAllCompanyList(Boolean isAll,String extId){
		if (isAll != null && isAll){
			return UserUtils.getCompanyAllList(extId);
		}else{
			return UserUtils.getCompanyList(extId);
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			office.setParentIds("%"+office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}
	//获取所有的学校类型的office
	public List<Office> findSchoolList(Office office){
		return dao.findSchoolList(office);
	}

	public List<String> getAllSubOffices(Office office){
		List<String> childrenIds = new ArrayList<>();
		setChildrenIds(office,childrenIds);
		return childrenIds;
	}

	public void setChildrenIds(Office office, List<String> childrenIds){
		List<Office> children = officeDao.findByParent(office.getId());
		for (Office child : children) {
			childrenIds.add(child.getId());
			setChildrenIds(child,childrenIds);
		}
	}
	public List<Office> findSchoolByAreaId(String areaId){
		Office office = new Office();
		Area area = new Area();
		area.setId(areaId);
		office.setArea(area);
		return findSchoolList(office);
	}

	//根据区域id获取所有学校类型的office
	public List<Office> findSchoolListByQy(String quyuId,Office office){
		office.setQuyuId(quyuId);
		return dao.findSchoolListByQy(office);
	}

	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	@Transactional(readOnly = false)
	public int checkAndDelete(Office office) {
		int result=0;

		if(office!=null && StringUtils.isNotBlank(office.getType())){
			User user=new User();
			if("1".equals(office.getType()))
				user.setCompany(office);
			if("2".equals(office.getType()))
				user.setOffice(office);

			List<User> list=userDao.findUserByOfficeIdAndLikeChlidId(user);

			if(list.isEmpty()&&list.size()<=0){
				delete(office);
				result=1;
			}
		}
		return result;
	}
	/**
	 * 判读是否有子机构
	 * @param office
	 * @return
	 */
	public boolean hasSubCompany(Office office) {
		List list = officeDao.findByType(office);
		if (list == null || list.size()<=0) {
			return false;
		} else {
			return true;
		}
	}

	public List<Office> findByType(Office office) {
		return officeDao.findByType(office);
	}
	public List<Office> findSchool(Office office) {
		return officeDao.findSchool(office);
	}
	public List<Office> findByParentId(String parentId) {
		return officeDao.findByParentId(parentId, BaseEntity.DEL_FLAG_NORMAL);
	}

	//根据officeid获取学校，如老师的officeid可能指向学校下的某个部门
	public Office getSchoolOfficeByOfficeId(String id) {
		Office office=officeDao.get(id);
		if(office==null){
			return null;
		}else if("1".equals(office.getOfficeType())){
			return office;
		}else{
			return getSchoolOfficeByOfficeId(office.getParentId());
		}
	}

	public List<Office> notLinkedToAreaList(Office office) {
		Office platform = new Office();
		platform.setId("1");
		office.setParent(platform);
		return findSchoolList(office);
	}
	@Transactional(readOnly = false)
	public void link(List<String> officeIds, Office parent) {
		List<Office> offices = officeDao.findByIds(officeIds);
		if (officeIds.size() == 0) return;
		if (parent == null) return;
		Date now = new Date();
		for (Office office : offices) {
			office.setParent(parent);
			office.setLinkTime(now);
			this.save(office);
		}
	}

	@Transactional(readOnly = false)
    public void removeLink(String officeId) {
		Office office = officeDao.get(officeId);
		Office platform = officeDao.get("1");
		office.setParent(platform);
		office.setLinkTime(null);
		this.save(office);
    }
	
	@Transactional(readOnly = false)
    public Office getCompany(String officeId) {
		
		while (true) {
			Office office = officeDao.get(officeId);
			if (office.getType().equals("1")) {
				return office;
			} else {
				officeId = office.getParent().getId();
			}
		}
    }
	

	private List<Office>  getOfficeChildListDiGui(List<Office> offices,String id){
		List<Office> officesTmp=findByParentId(id);
		offices.addAll(officesTmp);
		for (Office office : officesTmp) {
			offices=getOfficeChildListDiGui(offices,office.getId());
		}
		return offices;
	} 
	public List<Office>  getOfficeChildList(String id){
		List<Office> officesTmp=new ArrayList<Office>();
		officesTmp.add(new Office(id));
		List<Office> offices= getOfficeChildListDiGui(officesTmp,id);
		return offices;
	}
}
