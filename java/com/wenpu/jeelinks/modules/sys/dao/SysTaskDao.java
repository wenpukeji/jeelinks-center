/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.dao;

import com.wenpu.jeelinks.common.persistence.CrudDao;
import com.wenpu.jeelinks.common.persistence.annotation.MyBatisDao;
import com.wenpu.jeelinks.modules.sys.entity.SysTask;

/**
 * 系统任务调度DAO接口
 * @author webcat
 * @version 2017-09-08
 */
@MyBatisDao
public interface SysTaskDao extends CrudDao<SysTask> {
	
}