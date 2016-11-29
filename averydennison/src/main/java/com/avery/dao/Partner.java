package com.avery.dao;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "partner")
public class Partner {
	@Id
	@GeneratedValue
	@Column(name = "id")
	int ID;
	@Column(name = "name", length = 250)
	String name;
	@Column(name = "emailDomain", length = 100)
	String emailDomain;
	@Column(name = "emailId", length = 100)
	String emailId;
	@Column(name = "address1", length = 250)
	String address1;
	@Column(name = "address2", length = 250)
	String address2;
	@Column(name = "address3", length = 250)
	String address3;
	@Column(name = "city", length = 250)
	String city;
	@Column(name = "state", length = 250)
	String state;
	@Column(name = "country", length = 250)
	String country;
	@Column(name = "phone", length = 250)
	String phone;
	@Column(name = "alt_Phone", length = 250)
	String alt_Phone;
	@Column(name = "fax", length = 250)
	String fax;
	@Column(name = "contactPerson", length = 100)
	String contactPerson;
	@Column(name = "active")
	boolean active;
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "createdByName", length = 50)
	String createdByName;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@Column(name = "lastModifiedbyName", length = 50)
	String lastModifiedbyName;
	@Column(name = "comment", length = 250)
	String comment;


	public Partner() {

	}

	public Partner(int iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAlt_Phone() {
		return alt_Phone;
	}

	public void setAlt_Phone(String alt_Phone) {
		this.alt_Phone = alt_Phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedby() {
		return lastModifiedby;
	}

	public void setLastModifiedby(String lastModifiedby) {
		this.lastModifiedby = lastModifiedby;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
