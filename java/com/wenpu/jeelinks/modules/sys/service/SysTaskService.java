/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.sys.dao.SysTaskDao;
import com.wenpu.jeelinks.modules.sys.entity.SysTask;

/**
 * 系统任务调度Service
 * @author webcat
 * @version 2017-09-08
 */
@Service
@Transactional(readOnly = true)
public class SysTaskService extends CrudService<SysTaskDao, SysTask> {

	public SysTask get(String id) {
		return super.get(id);
	}
	
	public List<SysTask> findList(SysTask sysTask) {
		return super.findList(sysTask);
	}
	
	public Page<SysTask> findPage(Page<SysTask> page, SysTask sysTask) {
		return super.findPage(page, sysTask);
	}
	
	@Transactional(readOnly = false)
	public void save(SysTask sysTask) {
		super.save(sysTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysTask sysTask) {
		super.delete(sysTask);
	}
	
}