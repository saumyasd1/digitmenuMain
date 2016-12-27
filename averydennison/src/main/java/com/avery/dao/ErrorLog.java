package com.avery.dao;

import java.sql.Clob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysql.jdbc.Blob;

@Entity
@Table(name = "errorlog")
public class ErrorLog {
	
@Id
@GeneratedValue
@Column(name = "id",nullable=false)	
int ID;
@Column(name = "partnerID", length = 250)
String PartnerID;
@Column(name = "rboid", length = 250)
String RBOID;
@Column(name = "productLineID", length = 250)
String ProductLineID;
@Column(name = "pfid", length = 250)
long PFID;
@Column(name = "pid", length = 250)
long PID;
@Column(name = "activityID", length = 100)
long ActivityID;
@Column(name = "errorCode", length = 100)
int ErrorCode;
@Column(name = "severityCode", length = 100)
String SeverityCode;
@Column(name = "errorCategory", length = 250)
String ErrorCategory;
@Column(name = "errorContent", length = 1000)
String ErrorContent;
@Column(name = "user", length = 100)
String User;
@Column(name = "status", length = 100)
String Status;
@Column(name = "createdDate")
Date CreatedDate;
@Column(name = "createdBy", length = 100)
String CreatedBy;
@Column(name = "modifiedDate")
Date ModifiedDate;
@Column(name = "modifiedBy", length = 250)
String ModifiedBy;
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public String getPartnerID() {
	return PartnerID;
}
public void setPartnerID(String partnerID) {
	PartnerID = partnerID;
}
public String getRBOID() {
	return RBOID;
}
public void setRBOID(String rBOID) {
	RBOID = rBOID;
}
public String getProductLineID() {
	return ProductLineID;
}
public void setProductLineID(String productLineID) {
	ProductLineID = productLineID;
}
public long getPFID() {
	return PFID;
}
public void setPFID(long pFID) {
	PFID = pFID;
}
public long getPID() {
	return PID;
}
public void setPID(long pID) {
	PID = pID;
}
public long getActivityID() {
	return ActivityID;
}
public void setActivityID(long activityID) {
	ActivityID = activityID;
}
public int getErrorCode() {
	return ErrorCode;
}
public void setErrorCode(int errorCode) {
	ErrorCode = errorCode;
}
public String getSeverityCode() {
	return SeverityCode;
}
public void setSeverityCode(String severityCode) {
	SeverityCode = severityCode;
}
public String getErrorCategory() {
	return ErrorCategory;
}
public void setErrorCategory(String errorCategory) {
	ErrorCategory = errorCategory;
}


public String getErrorContent() {
	return ErrorContent;
}
public void setErrorContent(String errorContent) {
	ErrorContent = errorContent;
}
public String getUser() {
	return User;
}
public void setUser(String user) {
	User = user;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
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
public Date getModifiedDate() {
	return ModifiedDate;
}
public void setModifiedDate(Date modifiedDate) {
	ModifiedDate = modifiedDate;
}
public String getModifiedBy() {
	return ModifiedBy;
}
public void setModifiedBy(String modifiedBy) {
	ModifiedBy = modifiedBy;
}


}
