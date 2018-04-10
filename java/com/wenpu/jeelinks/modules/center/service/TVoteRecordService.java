/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.center.dao.TVoteRecordDao;
import com.wenpu.jeelinks.modules.center.entity.TVoteRecord;

/**
 * 投票Service
 * @author webcat
 * @version 2017-06-27
 */
@Service
@Transactional(readOnly = true)
public class TVoteRecordService extends CrudService<TVoteRecordDao, TVoteRecord> {

	public TVoteRecord get(String id) {
		return super.get(id);
	}
	
	public List<TVoteRecord> findList(TVoteRecord tVoteRecord) {
		return super.findList(tVoteRecord);
	}
	
	public Page<TVoteRecord> findPage(Page<TVoteRecord> page, TVoteRecord tVoteRecord) {
		return super.findPage(page, tVoteRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(TVoteRecord tVoteRecord) {
		super.save(tVoteRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(TVoteRecord tVoteRecord) {
		super.delete(tVoteRecord);
	}
	
}