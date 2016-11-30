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
	@Id
	@GeneratedValue
	@Column(name = "id",nullable=false)
	int id;
	@Column(name = "orderLineId",nullable=false)
	int orderLineId;
	@Column(name = "orderQueueId",nullable=false)
	int orderQueueId;
	@Column(name = "oracleItemNumber", length = 50)
	String oracleItemNumber;
	@Column(name = "level", length = 50)
	String level;
	@Column(name = "SKUno", length = 50)
	String SKUno;
	@Column(name = "typeSetter", length = 50)
	String typeSetter;
	@Column(name = "fiberPercent")
	int fiberPercent;
	@Column(name = "sumOfFiberPercentage")
	int sumOfFiberPercentage;
	@Column(name = "sentToOracleDate")
	Date sentToOracleDate;
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "createdBy", length = 50)
	String createdBy;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@Column(name = "lastModifiedBy", length = 50)
	String lastModifiedBy;
	@Column(name = "divisionforInterfaceERPORG", length = 10)
	String divisionforInterfaceERPORG;
	@Column(name = "mandatory", length = 100)
	String mandatory;
	@Column(name = "createdByName", length = 50)
	String createdByName;
	@Column(name = "comment", length = 250)
	String comment;

	public OrderLineDetails(int orderLineID, int orderQueueID) {

		this.orderLineId = orderLineID;
		this.orderQueueId = orderQueueID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(int orderLineId) {
		this.orderLineId = orderLineId;
	}

	public int getOrderQueueId() {
		return orderQueueId;
	}

	public void setOrderQueueId(int orderQueueId) {
		this.orderQueueId = orderQueueId;
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

	public int getFiberPercent() {
		return fiberPercent;
	}

	public void setFiberPercent(int fiberPercent) {
		this.fiberPercent = fiberPercent;
	}

	public int getSumOfFiberPercentage() {
		return sumOfFiberPercentage;
	}

	public void setSumOfFiberPercentage(int sumOfFiberPercentage) {
		this.sumOfFiberPercentage = sumOfFiberPercentage;
	}

	public Date getSentToOracleDate() {
		return sentToOracleDate;
	}

	public void setSentToOracleDate(Date sentToOracleDate) {
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
