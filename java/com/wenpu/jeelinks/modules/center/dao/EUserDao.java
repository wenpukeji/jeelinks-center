/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.dao;

import com.wenpu.jeelinks.common.persistence.CrudDao;
import com.wenpu.jeelinks.common.persistence.annotation.MyBatisDao;
import com.wenpu.jeelinks.modules.center.entity.EUser;

/**
 * 用户中心DAO接口
 * 
 * @author webcat
 * @version 2017-07-08
 */
@MyBatisDao
public interface EUserDao extends CrudDao<EUser> {
	public EUser findByLoginName(String loginName);

	public EUser getByStudentId(String stuId);

	public EUser getByTeacherId(String teacherId);

	public void syncPointFromStudent();

	public EUser getByUserName(String userName);
}