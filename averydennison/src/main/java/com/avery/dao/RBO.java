package com.avery.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "rbo")
public class RBO {

	@Id
	@GeneratedValue
	@Column(name = "id",nullable=false)
	int id;
	@Column(name = "rboName", length = 250)
	String rboName;
	@Column(name = "comment", length = 250)
	String comment;
	@Column(name = "createdBy", length = 50)
	String createdBy;
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "lastModifiedBy", length = 50)
	String lastModifiedBy;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "varRbo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Partner_RBOProductLine> listProductLine = new ArrayList<Partner_RBOProductLine>();
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "varRbo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<SalesOrderLine> listSalesOrderLine = new ArrayList<SalesOrderLine>();
	
	
	public RBO() {}
	
	public RBO(int id, String rboName) {
		super();
		id = id;
		rboName = rboName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRboName() {
		return rboName;
	}

	public void setRboName(String rboName) {
		this.rboName = rboName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public List<Partner_RBOProductLine> getListProductLine() {
		return listProductLine;
	}

	public void setListProductLine(List<Partner_RBOProductLine> listProductLine) {
		this.listProductLine = listProductLine;
	}

	public List<SalesOrderLine> getListSalesOrderLine() {
		return listSalesOrderLine;
	}

	public void setListSalesOrderLine(List<SalesOrderLine> listSalesOrderLine) {
		this.listSalesOrderLine = listSalesOrderLine;
	}
	
	


}
