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
@Table(name="aoc_orderconfiguration")
public class AOC_OrderConfiguration {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	int id;	
	@Column(name="createdBy",length=255)
	String createdBy;	
	@Column(name="createdDate")
	Date createdDate;	
	@Column(name="lastModifiedBy",length=255)
	String lastModifiedBy;	
	@Column(name="lastModifiedDate")
	Date lastModifiedDate;
	@Column(name="propertyName",length=50)
	String propertyName;
	@Column(name="propertyValue",length=500)
	String propertyValue;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="systemId")
	SystemInfo varSystem;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="orgId")
	Org varOrg;
	
	
	public AOC_OrderConfiguration() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getPropertyName() {
		return propertyName;
	}


	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}


	public String getPropertyValue() {
		return propertyValue;
	}


	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}


	public SystemInfo getVarSystem() {
		return varSystem;
	}


	public void setVarSystem(SystemInfo varSystem) {
		this.varSystem = varSystem;
	}


	public Org getVarOrg() {
		return varOrg;
	}


	public void setVarOrg(Org varOrg) {
		this.varOrg = varOrg;
	}
	
	
	

}
