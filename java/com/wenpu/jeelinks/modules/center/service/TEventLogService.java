/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.center.dao.TEventLogDao;
import com.wenpu.jeelinks.modules.center.entity.TEventLog;

/**
 * 积分日志Service
 * @author webcat
 * @version 2017-08-12
 */
@Service
@Transactional(readOnly = true)
public class TEventLogService extends CrudService<TEventLogDao, TEventLog> {

	public TEventLog get(String id) {
		return super.get(id);
	}
	
	public List<TEventLog> findList(TEventLog tEventLog) {
		return super.findList(tEventLog);
	}
	
	public Page<TEventLog> findPage(Page<TEventLog> page, TEventLog tEventLog) {
		return super.findPage(page, tEventLog);
	}
	
	@Transactional(readOnly = false)
	public void save(TEventLog tEventLog) {
		super.save(tEventLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(TEventLog tEventLog) {
		super.delete(tEventLog);
	}
	
}