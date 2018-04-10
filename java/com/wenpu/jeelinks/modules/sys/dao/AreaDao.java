/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.dao;

import java.util.List;

import com.wenpu.jeelinks.common.persistence.TreeDao;
import com.wenpu.jeelinks.common.persistence.annotation.MyBatisDao;
import com.wenpu.jeelinks.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

	List<Area> findByParentId(String pId);

	List<Area> findByIds(String ids);
	
}
