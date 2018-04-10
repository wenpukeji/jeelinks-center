/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenpu.jeelinks.common.persistence.Page;
import com.wenpu.jeelinks.common.service.CrudService;
import com.wenpu.jeelinks.modules.sys.dao.OfficeLicenseDao;
import com.wenpu.jeelinks.modules.sys.entity.OfficeLicense;

/**
 * 授权Service
 * @author webcat
 * @version 2017-09-08
 */
@Service
@Transactional(readOnly = true)
public class OfficeLicenseService extends CrudService<OfficeLicenseDao, OfficeLicense> {

	public OfficeLicense get(String id) {
		return super.get(id);
	}
	
	public List<OfficeLicense> findList(OfficeLicense officeLicense) {
		return super.findList(officeLicense);
	}
	
	public Page<OfficeLicense> findPage(Page<OfficeLicense> page, OfficeLicense officeLicense) {
		return super.findPage(page, officeLicense);
	}
	
	@Transactional(readOnly = false)
	public void save(OfficeLicense officeLicense) {
		super.save(officeLicense);
	}
	
	@Transactional(readOnly = false)
	public void delete(OfficeLicense officeLicense) {
		super.delete(officeLicense);
	}
	
}