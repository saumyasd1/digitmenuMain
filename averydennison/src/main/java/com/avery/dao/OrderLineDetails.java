package com.avery.dao;

import java.util.Calendar;
import java.util.Date;

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
@Table(name = "orderlinedetails")
public class OrderLineDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id",nullable=false)
	int id;
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
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "createdBy", length = 50)
	String createdBy;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@Column(name = "lastModifiedBy", length = 50)
	String lastModifiedBy;
	@Column(name = "mandatory", length = 100)
	String mandatory;
	@Column(name = "comment", length = 250)
	String comment;
	@Column(name = "variableDataValue", length = 250)
	String variableDataValue;
	@Column(name = "variableFieldName", length = 100)
	String variableFieldName;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="orderLineId")
	OrderLine varOrderLine;
	
	
	public OrderLineDetails() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getMandatory() {
		return mandatory;
	}


	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getVariableDataValue() {
		return variableDataValue;
	}


	public void setVariableDataValue(String variableDataValue) {
		this.variableDataValue = variableDataValue;
	}


	public String getVariableFieldName() {
		return variableFieldName;
	}


	public void setVariableFieldName(String variableFieldName) {
		this.variableFieldName = variableFieldName;
	}


	public OrderLine getVarOrderLine() {
		return varOrderLine;
	}


	public void setVarOrderLine(OrderLine varOrderLine) {
		this.varOrderLine = varOrderLine;
	}

	
	

}
