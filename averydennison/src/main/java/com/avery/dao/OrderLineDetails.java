package com.avery.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderLineDetails")
public class OrderLineDetails {
	 int id;
	 String orderLineID;
	 String orderQueueID;
	 String oracleItemNumber;
	 String level;
	 String SKUno;
	 String typeSetter;
	 String fiberPercent;
	 String sentToOracleDate;
	 Date createdDate;
	 String createdBy;
	 Date lastModifiedDate;
	 String lastModifiedBy;
	 String divisionforInterfaceERPORG;
	 String mandatory;
	 String createdByName;
	 String comment;
	 
	 
	 
	public OrderLineDetails(String orderLineID, String orderQueueID) {
		
		this.orderLineID = orderLineID;
		this.orderQueueID = orderQueueID;
	}
	
	@Id 
    @GeneratedValue 
    @Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderLineID() {
		return orderLineID;
	}
	public void setOrderLineID(String orderLineID) {
		this.orderLineID = orderLineID;
	}
	public String getOrderQueueID() {
		return orderQueueID;
	}
	public void setOrderQueueID(String orderQueueID) {
		this.orderQueueID = orderQueueID;
	}
	public String getOracleItemNumber() {
		return oracleItemNumber;
	}
	public void setOracleItemNumber(String oracleItemNumber) {
		this.oracleItemNumber = oracleItemNumber;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSKUno() {
		return SKUno;
	}
	public void setSKUno(String sKUno) {
		SKUno = sKUno;
	}
	public String getTypeSetter() {
		return typeSetter;
	}
	public void setTypeSetter(String typeSetter) {
		this.typeSetter = typeSetter;
	}
	public String getFiberPercent() {
		return fiberPercent;
	}
	public void setFiberPercent(String fiberPercent) {
		this.fiberPercent = fiberPercent;
	}
	public String getSentToOracleDate() {
		return sentToOracleDate;
	}
	public void setSentToOracleDate(String sentToOracleDate) {
		this.sentToOracleDate = sentToOracleDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public String getDivisionforInterfaceERPORG() {
		return divisionforInterfaceERPORG;
	}
	public void setDivisionforInterfaceERPORG(String divisionforInterfaceERPORG) {
		this.divisionforInterfaceERPORG = divisionforInterfaceERPORG;
	}
	public String getMandatory() {
		return mandatory;
	}
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	 
	 
}
