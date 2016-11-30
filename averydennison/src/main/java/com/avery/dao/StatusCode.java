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
	  
	public StatusCode(String type, String code) {
		
		this.type = type;
		this.code = code;
	}
	
	@Id 
    @GeneratedValue 
    @Column(name = "id",nullable=false)
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
	 
}
