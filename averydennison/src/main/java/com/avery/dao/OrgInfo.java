package com.avery.dao;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="orginfo")
public class OrgInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	int id;
	@Column(name="orgCodeId")
    int orgCodeId;
	@Column(name="isDefault")
	boolean isDefault;
	@Column(name="billToCode",length=250)
	String billToCode;
	@Column(name="shipToCode",length=250)
	String shipToCode;
	@Column(name="freightTerm",length=250)
	String freightTerm;
	@Column(name="shippingMethod",length=255)
	String shippingMethod;
	@Column(name="shippingInstruction",length=255)
	String shippingInstruction;
	@Column(name="shippingOnlyNotes",length=500)
	String shippingOnlyNotes;
	@Column(name="createdBy",length=50)
	String createdBy;
	@Column(name="createdDate")
	Date createdDate;
	@Column(name="lastModifiedBy",length=50)
	String lastModifiedBy;
	@Column(name="lastModifiedDate")
	Date lastModifiedDate;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="orderSystemInfoId")
	OrderSystemInfo varOrderSystemInfo;
	
	
	
	public OrgInfo() {}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getOrgCodeId() {
		return orgCodeId;
	}



	public void setOrgCodeId(int orgCodeId) {
		this.orgCodeId = orgCodeId;
	}



	public boolean isDefault() {
		return isDefault;
	}



	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}



	public String getBillToCode() {
		return billToCode;
	}



	public void setBillToCode(String billToCode) {
		this.billToCode = billToCode;
	}



	public String getShipToCode() {
		return shipToCode;
	}



	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}



	public String getFreightTerm() {
		return freightTerm;
	}



	public void setFreightTerm(String freightTerm) {
		this.freightTerm = freightTerm;
	}



	public String getShippingMethod() {
		return shippingMethod;
	}



	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}



	public String getShippingInstruction() {
		return shippingInstruction;
	}



	public void setShippingInstruction(String shippingInstruction) {
		this.shippingInstruction = shippingInstruction;
	}



	public String getShippingOnlyNotes() {
		return shippingOnlyNotes;
	}



	public void setShippingOnlyNotes(String shippingOnlyNotes) {
		this.shippingOnlyNotes = shippingOnlyNotes;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public String getLastModifiedBy() {
		return lastModifiedBy;
	}



	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}



	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}



	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}



	public OrderSystemInfo getVarOrderSystemInfo() {
		return varOrderSystemInfo;
	}



	public void setVarOrderSystemInfo(OrderSystemInfo varOrderSystemInfo) {
		this.varOrderSystemInfo = varOrderSystemInfo;
	}
	
	
	
}
