package com.avery.dao;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="localitem")
public class LocalItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	int id;
	@Column(name="system",length=500)
	String system;
	@Column(name="site",length=50)
	String site;
	@Column(name="partnerName",length=500)
	String partnerName;
	@Column(name="rboName",length=500)
	String rboName;
	@Column(name="customerItemNO",length=250)
	String customerItemNO;
	@Column(name="identifier",length=255)
	String identifier;
	@Column(name="identifierVariable",length=250)
	String identifierVariable;
	@Column(name="internalItemNo",length=250)
	String internalItemNo;
	@Column(name="cancelItem")
	boolean cancelItem;
	@Column(name="glid")
	long glid;
	@Column(name="createdBy",length=50)
	String createdBy;
	@Column(name="createdDate")
	Date createdDate;
	@Column(name="lastModifiedBy",length=50)
	String lastModifiedBy;
	@Column(name="lastModifiedDate")
	Date lastModifiedDate;
	
	
	
	public LocalItem() {}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getSystem() {
		return system;
	}



	public void setSystem(String system) {
		this.system = system;
	}



	public String getSite() {
		return site;
	}



	public void setSite(String site) {
		this.site = site;
	}



	public String getPartnerName() {
		return partnerName;
	}



	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}



	public String getRboName() {
		return rboName;
	}



	public void setRboName(String rboName) {
		this.rboName = rboName;
	}



	public String getCustomerItemNO() {
		return customerItemNO;
	}



	public void setCustomerItemNO(String customerItemNO) {
		this.customerItemNO = customerItemNO;
	}



	public String getIdentifier() {
		return identifier;
	}



	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}



	public String getIdentifierVariable() {
		return identifierVariable;
	}



	public void setIdentifierVariable(String identifierVariable) {
		this.identifierVariable = identifierVariable;
	}



	public String getInternalItemNo() {
		return internalItemNo;
	}



	public void setInternalItemNo(String internalItemNo) {
		this.internalItemNo = internalItemNo;
	}



	public boolean isCancelItem() {
		return cancelItem;
	}



	public void setCancelItem(boolean cancelItem) {
		this.cancelItem = cancelItem;
	}



	public long getGlid() {
		return glid;
	}



	public void setGlid(long glid) {
		this.glid = glid;
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
	
	

	
	
}
