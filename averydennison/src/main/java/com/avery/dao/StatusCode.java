package com.avery.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statusCode")
public class StatusCode {
	 int id;
	 String type;
	 String code;
	 String value;
	 String description;
	 Date createdDate;
	 String createdBy;
	 Date lastModifiedDate;
	 String lastModifiedBy;
	 
	 
	 
	 
	public StatusCode(String type, String code) {
		
		this.type = type;
		this.code = code;
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
	@Column(name="type" , length=100)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="code" , length=100)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="value" , length=100)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="createdBy" , length=100)
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
	 
	 
}
