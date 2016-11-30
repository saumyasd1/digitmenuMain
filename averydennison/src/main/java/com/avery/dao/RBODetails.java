package com.avery.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rbodetails")
public class RBODetails {

	@Id
	@GeneratedValue
	@Column(name = "id",nullable=false)
	int ID;
	@Column(name = "RBOName", length = 250)
	String RBOName;
	public RBODetails(int iD, String rBOName) {
		super();
		ID = iD;
		RBOName = rBOName;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getRBOName() {
		return RBOName;
	}
	public void setRBOName(String rBOName) {
		RBOName = rBOName;
	}
	
	

}
