package com.avery.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class Address {
int id;
String orgCode;
String description;
String address1;
String address2;
String address3;
String city;
String state;
String country;
String contact;
String email;
String fax;
String phone1;
String phone2;
int siteNumber;
String siteType;



public Address(int id, String orgCode) {
	super();
	this.id = id;
	this.orgCode = orgCode;
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

@Column(name="orgCode" , length=25)
public String getOrgCode() {
	return orgCode;
}
public void setOrgCode(String orgCode) {
	this.orgCode = orgCode;
}

@Column(name="description" , length=250)
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

@Column(name="address1" , length=250)
public String getAddress1() {
	return address1;
}
public void setAddress1(String address1) {
	this.address1 = address1;
}

@Column(name="address2" , length=250)
public String getAddress2() {
	return address2;
}
public void setAddress2(String address2) {
	this.address2 = address2;
}

@Column(name="address3" , length=250)
public String getAddress3() {
	return address3;
}
public void setAddress3(String address3) {
	this.address3 = address3;
}

@Column(name="city" , length=250)
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}

@Column(name="state" , length=250)
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}

@Column(name="country" , length=250)
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}

@Column(name="contact" , length=250)
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}

@Column(name="email" , length=250)
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@Column(name="fax" , length=250)
public String getFax() {
	return fax;
}
public void setFax(String fax) {
	this.fax = fax;
}

@Column(name="phone1" , length=250)
public String getPhone1() {
	return phone1;
}
public void setPhone1(String phone1) {
	this.phone1 = phone1;
}

@Column(name="phone2" , length=250)
public String getPhone2() {
	return phone2;
}
public void setPhone2(String phone2) {
	this.phone2 = phone2;
}

@Column(name="siteNumber" )
public int getSiteNumber() {
	return siteNumber;
}
public void setSiteNumber(int siteNumber) {
	this.siteNumber = siteNumber;
}

@Column(name="siteType" , length=100)
public String getSiteType() {
	return siteType;
}
public void setSiteType(String siteType) {
	this.siteType = siteType;
}



}
