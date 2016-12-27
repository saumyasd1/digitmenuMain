package com.avery.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesOrderDetail")
public class SalesOrderDetail {

	
	int ID;
	int salesOrderID;
	int processQueueID;
	String division;
	String soNumber;
	String soDetails;
	String oracleItemNumber;
	String level;
	String skUno;
	String typeSetter;
	String variableFieldName;
	String variableDataValue;
	int fiberPercent;
	Date sentToOracleDate;
	Date createdDate;
	String createdBy;
	Date lastModifiedDate;
	String lastModifiedBy;
	String divisionforInterfaceERPORG;

	public SalesOrderDetail(String division, String sOnumber) {
		division = division;
		soNumber = sOnumber;
	}
	
	@Id 
    @GeneratedValue 
    @Column(name = "id")
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	@Column(name="salesOrderID")
	public int getSalesOrderID() {
		return salesOrderID;
	}

	public void setSalesOrderID(int salesOrderID) {
		this.salesOrderID = salesOrderID;
	}
	@Column(name="processQueueID" , length=100)
	public int getProcessQueueID() {
		return processQueueID;
	}

	public void setProcessQueueID(int processQueueID) {
		this.processQueueID = processQueueID;
	}
	@Column(name="division" , length=100)
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}
	@Column(name="soNumber" , length=100)
	public String getSoNumber() {
		return soNumber;
	}

	public void setSoNumber(String soNumber) {
		this.soNumber = soNumber;
	}
	@Column(name="soDetails" , length=100)
	public String getSoDetails() {
		return soDetails;
	}

	public void setSoDetails(String soDetails) {
		this.soDetails = soDetails;
	}
	@Column(name="oracleItemNumber" , length=100)
	public String getOracleItemNumber() {
		return oracleItemNumber;
	}

	public void setOracleItemNumber(String oracleItemNumber) {
		this.oracleItemNumber = oracleItemNumber;
	}
	@Column(name="level" , length=100)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	@Column(name="skUno" , length=100)
	public String getSkUno() {
		return skUno;
	}

	public void setSkUno(String skUno) {
		this.skUno = skUno;
	}
	@Column(name="typeSetter" , length=100)
	public String getTypeSetter() {
		return typeSetter;
	}

	public void setTypeSetter(String typeSetter) {
		this.typeSetter = typeSetter;
	}
	@Column(name="variableFieldName" , length=100)
	public String getVariableFieldName() {
		return variableFieldName;
	}

	public void setVariableFieldName(String variableFieldName) {
		this.variableFieldName = variableFieldName;
	}
	@Column(name="variableDataValue" , length=100)
	public String getVariableDataValue() {
		return variableDataValue;
	}

	public void setVariableDataValue(String variableDataValue) {
		this.variableDataValue = variableDataValue;
	}
	@Column(name="fiberPercent" )
	public int getFiberPercent() {
		return fiberPercent;
	}

	public void setFiberPercent(int fiberPercent) {
		this.fiberPercent = fiberPercent;
	}
	@Column(name="sentToOracleDate" )
	public Date getSentToOracleDate() {
		return sentToOracleDate;
	}

	public void setSentToOracleDate(Date sentToOracleDate) {
		this.sentToOracleDate = sentToOracleDate;
	}
	@Column(name="createdDate" )
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="createdBy")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="lastModifiedDate" )
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@Column(name="lastModifiedBy" , length=100)
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	@Column(name="divisionforInterfaceERPORG" , length=100)
	public String getDivisionforInterfaceERPORG() {
		return divisionforInterfaceERPORG;
	}

	public void setDivisionforInterfaceERPORG(String divisionforInterfaceERPORG) {
		this.divisionforInterfaceERPORG = divisionforInterfaceERPORG;
	}
	
	
	
	
}
