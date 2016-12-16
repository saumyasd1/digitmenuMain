package com.avery.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	int id;
	@Column(name = "description", length = 500)
	String description;
	@Column(name = "address1", length = 500)
	String address1;
	@Column(name = "address2", length = 500)
	String address2;
	@Column(name = "address3", length = 500)
	String address3;
	@Column(name = "address4", length = 500)
	String address4;
	@Column(name = "city", length = 250)
	String city;
	@Column(name = "state", length = 250)
	String state;
	@Column(name = "country", length = 250)
	String country;
	@Column(name = "contact", length = 255)
	String contact;
	@Column(name = "email", length = 255)
	String email;
	@Column(name = "fax", length = 255)
	String fax;
	@Column(name = "phone1", length = 255)
	String phone1;
	@Column(name = "phone2", length = 255)
	String phone2;
	@Column(name = "siteNumber",length=255)
	String siteNumber;
	@Column(name = "siteType", length = 255)
	String siteType;
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
	@Column(name = "siteId")
	int siteId;
	@Column(name="zip",length=250)
	String zip;
	@Column(name = "orgCodeId")
	int orgCodeId;
	@Column(name = "system")
	int system;
	@Column(name = "freightTerm", length = 250)
	String freightTerm;
	@Column(name = "shippingMethod", length = 255)
	String shippingMethod;
	@Column(name = "shippingInstruction", length = 255)
	String shippingInstruction;
	
	
	public Address() {}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
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

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}



	int getOrgCodeId() {
		return orgCodeId;
	}



	void setOrgCodeId(int orgCodeId) {
		this.orgCodeId = orgCodeId;
	}



	int getSystem() {
		return system;
	}



	void setSystem(int system) {
		this.system = system;
	}



	String getFreightTerm() {
		return freightTerm;
	}



	void setFreightTerm(String freightTerm) {
		this.freightTerm = freightTerm;
	}



	String getShippingMethod() {
		return shippingMethod;
	}



	void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}



	String getShippingInstruction() {
		return shippingInstruction;
	}



	void setShippingInstruction(String shippingInstruction) {
		this.shippingInstruction = shippingInstruction;
	}

	
	

}
