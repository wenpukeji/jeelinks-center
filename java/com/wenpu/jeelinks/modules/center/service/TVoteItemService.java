/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.center.dao.TVoteItemDao;
import com.wenpu.jeelinks.modules.center.entity.TVoteItem;

/**
 * 投票Service
 * @author webcat
 * @version 2017-06-27
 */
@Service
@Transactional(readOnly = true)
public class TVoteItemService extends CrudService<TVoteItemDao, TVoteItem> {

	public TVoteItem get(String id) {
		return super.get(id);
	}
	
	public List<TVoteItem> findList(TVoteItem tVoteItem) {
		return super.findList(tVoteItem);
	}
	
	public Page<TVoteItem> findPage(Page<TVoteItem> page, TVoteItem tVoteItem) {
		return super.findPage(page, tVoteItem);
	}
	
	@Transactional(readOnly = false)
	public void save(TVoteItem tVoteItem) {
		super.save(tVoteItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(TVoteItem tVoteItem) {
		super.delete(tVoteItem);
	}
	
}