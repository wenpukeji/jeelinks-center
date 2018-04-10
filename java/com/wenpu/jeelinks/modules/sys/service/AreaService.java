/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.service.TreeService;
import com.wenpu.jeelinks.modules.sys.dao.AreaDao;
import com.wenpu.jeelinks.modules.sys.dao.OfficeDao;
import com.wenpu.jeelinks.modules.sys.entity.Area;
import com.wenpu.jeelinks.modules.sys.entity.Office;
import com.wenpu.jeelinks.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	@Autowired
	OfficeDao officeDao;
	
	
	
	//取不到则取全国，考试和测验要用必传字段
	@Override
	public Area get(Area entity) {
		Area a=super.get(entity);
		if(a==null){
			a=super.get(new Area("1"));
		}
		return a;
	}
	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}
	public List<Area> findByParentId(String pId){
		return dao.findByParentId(pId);
	}
	public List<Area> findByIds(String ids){
		return dao.findByIds(ids);
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	//检查并删除
		@Transactional(readOnly = false)
		public int checkAndDelete(Area area) {
			int result=0;
			Office office=new Office();
			office.setArea(area);
			List<Office> list=officeDao.findIdInAreaIds(office);
			if(list.isEmpty()&&list.size()<=0){
				result=1;
				delete(area);
			}
			return result;
		}
	
}
