/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.center.dao.TFeedbackDao;
import com.wenpu.jeelinks.modules.center.entity.TFeedback;

/**
 * 用户反馈Service
 * @author webcat
 * @version 2017-08-25
 */
@Service
@Transactional(readOnly = true)
public class TFeedbackService extends CrudService<TFeedbackDao, TFeedback> {

	public TFeedback get(String id) {
		return super.get(id);
	}
	
	public List<TFeedback> findList(TFeedback tFeedback) {
		return super.findList(tFeedback);
	}
	
	public Page<TFeedback> findPage(Page<TFeedback> page, TFeedback tFeedback) {
		return super.findPage(page, tFeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(TFeedback tFeedback) {
		super.save(tFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(TFeedback tFeedback) {
		super.delete(tFeedback);
	}
	
}