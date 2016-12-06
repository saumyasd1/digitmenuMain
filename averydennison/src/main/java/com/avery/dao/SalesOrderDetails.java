package com.avery.dao;

import java.sql.Date;
import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "salesorderdetails")
public class SalesOrderDetails {
	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name = "id",nullable=false)
	int ID;
	@Column(name="division" , length=100)
	String division;
	@Column(name="soNumber" , length=100)
	String soNumber;
	@Column(name="soDetails" , length=100)
	String soDetails;
	@Column(name="oracleItemNumber" , length=50)
	String oracleItemNumber;
	@Column(name="level" , length=100)
	String level;
	@Column(name="SKUno" , length=100)
	String SKUno;
	@Column(name="typeSetter" , length=100)
	String typeSetter;
	@Column(name="variableFieldName" , length=100)
	String variableFieldName;
	@Column(name="variableDataValue" , length=100)
	String variableDataValue;
	@Column(name="fiberPercent" )
	int fiberPercent;
	@Column(name="sumOfFiberPercentage" )
	int sumOfFiberPercentage ;
	@Column(name="createdDate" )
	Date createdDate;
	@Column(name="createdBy",length=50)
	String createdBy;
	@Column(name="lastModifiedDate" )
	Date lastModifiedDate;
	@Column(name="lastModifiedBy" , length=50)
	String lastModifiedBy;
	@Column(name = "comment",length=250)
	String comment;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="salesOrderId")
	SalesOrderLine varSalesOrderLine;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="orderQueueId")
	OrderFileQueue varOrderFileQueue;
	
	
	

	public SalesOrderDetails() {}


	public SalesOrderDetails(String division, String sOnumber) {
		division = division;
		soNumber = sOnumber;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public OrderFileQueue getVarOrderFileQueue() {
		return varOrderFileQueue;
	}


	public void setVarOrderFileQueue(OrderFileQueue varOrderFileQueue) {
		this.varOrderFileQueue = varOrderFileQueue;
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
	}


	public String getSoNumber() {
		return soNumber;
	}


	public void setSoNumber(String soNumber) {
		this.soNumber = soNumber;
	}


	public String getSoDetails() {
		return soDetails;
	}


	public void setSoDetails(String soDetails) {
		this.soDetails = soDetails;
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


	public String getVariableFieldName() {
		return variableFieldName;
	}


	public void setVariableFieldName(String variableFieldName) {
		this.variableFieldName = variableFieldName;
	}


	public String getVariableDataValue() {
		return variableDataValue;
	}


	public void setVariableDataValue(String variableDataValue) {
		this.variableDataValue = variableDataValue;
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


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public SalesOrderLine getVarSalesOrderLine() {
		return varSalesOrderLine;
	}


	public void setVarSalesOrderLine(SalesOrderLine varSalesOrderLine) {
		this.varSalesOrderLine = varSalesOrderLine;
	}
	
	
	
	
	
}

