package com.avery.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sitedetails")
public class SiteDetails {
	@Id
	@GeneratedValue
	@Column(name = "id")
	int ID;
	@Column(name = "siteName",length=100)
	String siteName;
	@Column(name = "mailId",length=100)
	String mailId;
	public SiteDetails(int iD, String siteName) {
		super();
		ID = iD;
		this.siteName = siteName;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	
}
