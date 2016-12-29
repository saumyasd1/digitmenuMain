package com.avery.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(name="system")
public class SystemInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	int id;
	@Column(name = "name",length=50,unique=true)
	String name;
	@Column(name = "comment",length=250)
	String comment;
	@Column(name = "createdBy",length=50)
	String createdBy;
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "lastModifiedBy",length=50)
	String lastModifiedBy;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@OneToOne(mappedBy="varSystemInfo",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	OrderSystemInfo varOrderSystemInfo;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "varSystem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<AOC_OrderConfiguration> listAOC_OrderConfiguration = new ArrayList<AOC_OrderConfiguration>();
	
	


	public SystemInfo() {}
	
	


	public SystemInfo(String name) {
		super();
		this.name = name;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public OrderSystemInfo getVarOrderSystemInfo() {
		return varOrderSystemInfo;
	}


	public void setVarOrderSystemInfo(OrderSystemInfo varOrderSystemInfo) {
		this.varOrderSystemInfo = varOrderSystemInfo;
	}


	public List<AOC_OrderConfiguration> getListAOC_OrderConfiguration() {
		return listAOC_OrderConfiguration;
	}

	public void setListAOC_OrderConfiguration(
			List<AOC_OrderConfiguration> listAOC_OrderConfiguration) {
		this.listAOC_OrderConfiguration = listAOC_OrderConfiguration;
	}

	
	
	
}
