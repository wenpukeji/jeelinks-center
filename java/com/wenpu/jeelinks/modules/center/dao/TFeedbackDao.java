/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.dao;

import com.wenpu.jeelinks.common.persistence.CrudDao;
import com.wenpu.jeelinks.common.persistence.annotation.MyBatisDao;
import com.wenpu.jeelinks.modules.center.entity.TFeedback;

/**
 * 用户反馈DAO接口
 * @author webcat
 * @version 2017-08-25
 */
@MyBatisDao
public interface TFeedbackDao extends CrudDao<TFeedback> {
	
}