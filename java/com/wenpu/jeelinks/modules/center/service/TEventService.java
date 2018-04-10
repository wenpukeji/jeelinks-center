/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.center.dao.TEventDao;
import com.wenpu.jeelinks.modules.center.entity.TEvent;

/**
 * 积分事件Service
 * @author webcat
 * @version 2017-08-13
 */
@Service
@Transactional(readOnly = true)
public class TEventService extends CrudService<TEventDao, TEvent> {

	public TEvent get(String id) {
		return super.get(id);
	}
	
	public List<TEvent> findList(TEvent tEvent) {
		return super.findList(tEvent);
	}
	
	public Page<TEvent> findPage(Page<TEvent> page, TEvent tEvent) {
		return super.findPage(page, tEvent);
	}
	
	@Transactional(readOnly = false)
	public void save(TEvent tEvent) {
		super.save(tEvent);
	}
	
	@Transactional(readOnly = false)
	public void delete(TEvent tEvent) {
		super.delete(tEvent);
	}
	/**
	 * 根据事件编码获取积分
	 */
	public TEvent getByEventCode(String eventCode) {
		TEvent tEvent = new TEvent();
		tEvent.setEventCode(eventCode);
		return this.get(tEvent);
	}
	
}