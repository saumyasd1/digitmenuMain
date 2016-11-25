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
	int SalesOrderID;
	int ProcessQueueID;
	String Division;
	String SOnumber;
	String SODetails;
	String Oracleitemnumber;
	String Level;
	String SKUno;
	String typesetter;
	String Variablefieldname;
	String variabledatavalue;
	int FiberPercent;
	Date SentToOracleDate;
	Date CreatedDate;
	String CreatedBy;
	Date LastModifiedDate;
	String LastModifiedBy;
	String DivisionforInterfaceERPORG;

	public SalesOrderDetail(String division, String sOnumber) {
		Division = division;
		SOnumber = sOnumber;
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
	public int getSalesOrderID() {
		return SalesOrderID;
	}
	public void setSalesOrderID(int salesOrderID) {
		SalesOrderID = salesOrderID;
	}
	public int getProcessQueueID() {
		return ProcessQueueID;
	}
	public void setProcessQueueID(int processQueueID) {
		ProcessQueueID = processQueueID;
	}
	public String getDivision() {
		return Division;
	}
	public void setDivision(String division) {
		Division = division;
	}
	public String getSOnumber() {
		return SOnumber;
	}
	public void setSOnumber(String sOnumber) {
		SOnumber = sOnumber;
	}
	public String getSODetails() {
		return SODetails;
	}
	public void setSODetails(String sODetails) {
		SODetails = sODetails;
	}
	public String getOracleitemnumber() {
		return Oracleitemnumber;
	}
	public void setOracleitemnumber(String oracleitemnumber) {
		Oracleitemnumber = oracleitemnumber;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getSKUno() {
		return SKUno;
	}
	public void setSKUno(String sKUno) {
		SKUno = sKUno;
	}
	public String getTypesetter() {
		return typesetter;
	}
	public void setTypesetter(String typesetter) {
		this.typesetter = typesetter;
	}
	public String getVariablefieldname() {
		return Variablefieldname;
	}
	public void setVariablefieldname(String variablefieldname) {
		Variablefieldname = variablefieldname;
	}
	public String getVariabledatavalue() {
		return variabledatavalue;
	}
	public void setVariabledatavalue(String variabledatavalue) {
		this.variabledatavalue = variabledatavalue;
	}
	public Date getSentToOracleDate() {
		return SentToOracleDate;
	}
	public void setSentToOracleDate(Date sentToOracleDate) {
		SentToOracleDate = sentToOracleDate;
	}
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public int getFiberPercent() {
		return FiberPercent;
	}
	public void setFiberPercent(int fiberPercent) {
		FiberPercent = fiberPercent;
	}
	public Date getLastModifiedDate() {
		return LastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}
	public String getDivisionforInterfaceERPORG() {
		return DivisionforInterfaceERPORG;
	}
	public void setDivisionforInterfaceERPORG(String divisionforInterfaceERPORG) {
		DivisionforInterfaceERPORG = divisionforInterfaceERPORG;
	}
	
	
	
}
