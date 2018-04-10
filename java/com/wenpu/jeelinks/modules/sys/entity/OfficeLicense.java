/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenpu.jeelinks.common.persistence.DataEntity;
import com.wenpu.jeelinks.modules.sys.entity.Office;

/**
 * 授权Entity
 * @author webcat
 * @version 2017-09-08
 */
public class OfficeLicense extends DataEntity<OfficeLicense> {
	
	private static final long serialVersionUID = 1L;
	private String auName;		// 名称
	private Office office;		// 机构
	private String auType;		// 类型
	private String auCode;		// 授权码
	private Date validStartDate;		// 开始日期
	private Date validEndDate;		// 结束日期
	private String auNum;		// 数量
	private String auMoney;		// 金额
	private String auScope;		// 使用范围
	private String auState;		// 状态
	
	public OfficeLicense() {
		super();
	}

	public OfficeLicense(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getAuName() {
		return auName;
	}

	public void setAuName(String auName) {
		this.auName = auName;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getAuType() {
		return auType;
	}

	public void setAuType(String auType) {
		this.auType = auType;
	}
	
	@Length(min=0, max=100, message="授权码长度必须介于 0 和 100 之间")
	public String getAuCode() {
		return auCode;
	}

	public void setAuCode(String auCode) {
		this.auCode = auCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidStartDate() {
		return validStartDate;
	}

	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidEndDate() {
		return validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}
	
	@Length(min=0, max=8, message="数量长度必须介于 0 和 8 之间")
	public String getAuNum() {
		return auNum;
	}

	public void setAuNum(String auNum) {
		this.auNum = auNum;
	}
	
	public String getAuMoney() {
		return auMoney;
	}

	public void setAuMoney(String auMoney) {
		this.auMoney = auMoney;
	}
	
	@Length(min=0, max=2, message="使用范围长度必须介于 0 和 2 之间")
	public String getAuScope() {
		return auScope;
	}

	public void setAuScope(String auScope) {
		this.auScope = auScope;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getAuState() {
		return auState;
	}

	public void setAuState(String auState) {
		this.auState = auState;
	}
	
}