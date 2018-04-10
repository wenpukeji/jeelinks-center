/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.dao;

import com.wenpu.jeelinks.common.persistence.CrudDao;
import com.wenpu.jeelinks.common.persistence.annotation.MyBatisDao;
import com.wenpu.jeelinks.modules.center.entity.TLevel;

/**
 * 用户等级DAO接口
 * @author webcat
 * @version 2017-08-20
 */
@MyBatisDao
public interface TLevelDao extends CrudDao<TLevel> {

	TLevel findByScopeAndPonit(TLevel tlevelParam);
	
}