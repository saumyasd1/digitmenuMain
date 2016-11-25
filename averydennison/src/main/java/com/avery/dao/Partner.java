package com.avery.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partner")
public class Partner {

	int id;
	String partnerID;
    String PartnerName;
    String Address;
    String ContactPerson;
    int phone;
    int isActive;
    Date createdDate;
    String createdby;
    Date modifiedDate;
    String modifiedby;
    
    public Partner() {
   	 
    }
    public Partner(String partnerID, String PartnerName) {
        this.partnerID = partnerID;
        this.PartnerName = PartnerName;
        
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
	 @Column
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	 @Column
	public String getPartnerName() {
		return PartnerName;
	}
	public void setPartnerName(String partnerName) {
		PartnerName = partnerName;
	}
	 @Column
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	 @Column
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	 @Column
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	 @Column
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	 @Column
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	 @Column
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	 @Column
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	 @Column
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
   
    
    
}
