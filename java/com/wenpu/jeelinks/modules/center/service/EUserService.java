/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.center.dao.EUserDao;
import com.wenpu.jeelinks.modules.center.entity.EUser;

/**
 * 用户中心Service
 * @author webcat
 * @version 2017-07-08
 */
@Service
@Transactional(readOnly = true)
public class EUserService extends CrudService<EUserDao, EUser> {

	public EUser get(String id) {
		return super.get(id);
	}
	public EUser getByStudentId(String stuId){
		return dao.getByStudentId(stuId);
	}
	public EUser getByTeacherId(String teacherId){
		return dao.getByTeacherId(teacherId);
	}
	
		
	public EUser findLoginName(String loginName) {
		return this.dao.findByLoginName(loginName);
	}
	
	public List<EUser> findList(EUser eUser) {
		return super.findList(eUser);
	}
	
	public Page<EUser> findPage(Page<EUser> page, EUser eUser) {
		return super.findPage(page, eUser);
	}
	
	@Transactional(readOnly = false)
	public void save(EUser eUser) {
		super.save(eUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(EUser eUser) {
		super.delete(eUser);
	}
	@Transactional(readOnly = false)
	public void syncPointFromStudent() {
		dao.syncPointFromStudent();
	}
	public EUser getByUserName(String userName) {
		return dao.getByUserName(userName);
	}
	public String checkEuserName(String oldUserName,String userName) {
		EUser euser=new EUser();
		euser.setUserName(userName);
		if (userName !=null && userName.equals(oldUserName)) {
			return "true";
		} else if (userName !=null && getByUserName(userName) == null) {
			return "true";
		}
		return "false";
	}
	
}