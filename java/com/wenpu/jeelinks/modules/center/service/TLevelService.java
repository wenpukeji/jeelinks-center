/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.center.dao.TLevelDao;
import com.wenpu.jeelinks.modules.center.entity.TLevel;

/**
 * 用户等级Service
 * @author webcat
 * @version 2017-08-20
 */
@Service
@Transactional(readOnly = true)
public class TLevelService extends CrudService<TLevelDao, TLevel> {

	public TLevel get(String id) {
		return super.get(id);
	}
	
	public List<TLevel> findList(TLevel tLevel) {
		return super.findList(tLevel);
	}
	
	public Page<TLevel> findPage(Page<TLevel> page, TLevel tLevel) {
		return super.findPage(page, tLevel);
	}
	
	@Transactional(readOnly = false)
	public void save(TLevel tLevel) {
		super.save(tLevel);
	}
	
	@Transactional(readOnly = false)
	public void delete(TLevel tLevel) {
		super.delete(tLevel);
	}

	public TLevel findByScopeAndPonit(TLevel tlevelParam) {
		return dao.findByScopeAndPonit(tlevelParam);
	}
	
}