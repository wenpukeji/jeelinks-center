/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.wenpu.jeelinks.common.persistence.TreeDao;
import com.wenpu.jeelinks.common.persistence.annotation.MyBatisDao;
import com.wenpu.jeelinks.modules.sys.entity.Office;

import java.util.List;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {

	/**
	 * 根据机构类型查找(用于查询学校)
	 * @param office
	 * @return
	 */
	List<Office> findByType(Office office);

	List<Office> findIdInAreaIds(Office office);

	List<Office> findSchool(Office office);

	List<Office> findByParentId(@Param("parentId") String parentId , @Param("delFlag") String delFlag);
	List<Office> findByParent(@Param("parentId") String parentId);

	List<Office> findSchoolList(Office office);

	List<Office> findSchoolListByQy(Office office);

	List<Office> findSchoolByAreaId(String areaId);
	List<Office> findByIds(@Param("ids")List<String> ids);
}
