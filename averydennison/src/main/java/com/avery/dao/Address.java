package com.avery.dao;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	int id;
	int PartnerID;
    String BillToSiteNumber;
    String ShipToSiteNumber;
    String Description;
    String Address1;
    String Address2;
    String Address3;
    String Address4;
    String City;
    String State;
    String Zip;
    String BillToContact;
    String BillToPhone1;
    String BillToPhone2;
    String BillToFax;
    String BillToEmail;
    String ShipToContact;
    String ShipToPhone1;
    String ShipToPhone2;
    String FreightTerms;
    String ShippingInstructions;
    String ShippingMethod;
    Date CreatedDate;
    String CreatedBy;
    Date LastModifiedDate;
    String LastModifiedBy;
    
    public Address() {
    	 
    }
 
    public Address(String BillToSiteNumber, String ShipToSiteNumber) {
        this.BillToSiteNumber = BillToSiteNumber;
        this.ShipToSiteNumber = ShipToSiteNumber;
       
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
	public int getPartnerID() {
		return PartnerID;
	}
	public void setPartnerID(int partnerID) {
		PartnerID = partnerID;
	}
	@Column
	public String getBillToSiteNumber() {
		return BillToSiteNumber;
	}
	public void setBillToSiteNumber(String billToSiteNumber) {
		BillToSiteNumber = billToSiteNumber;
	}
	@Column
	public String getShipToSiteNumber() {
		return ShipToSiteNumber;
	}
	public void setShipToSiteNumber(String shipToSiteNumber) {
		ShipToSiteNumber = shipToSiteNumber;
	}
	@Column
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Column
	public String getAddress1() {
		return Address1;
	}
	public void setAddress1(String address1) {
		Address1 = address1;
	}
	@Column
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
	}
	@Column
	public String getAddress3() {
		return Address3;
	}
	public void setAddress3(String address3) {
		Address3 = address3;
	}
	@Column
	public String getAddress4() {
		return Address4;
	}
	public void setAddress4(String address4) {
		Address4 = address4;
	}
	@Column
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	@Column
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@Column
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	@Column
	public String getBillToContact() {
		return BillToContact;
	}
	public void setBillToContact(String billToContact) {
		BillToContact = billToContact;
	}
	@Column
	public String getBillToPhone1() {
		return BillToPhone1;
	}
	public void setBillToPhone1(String billToPhone1) {
		BillToPhone1 = billToPhone1;
	}
	@Column
	public String getBillToPhone2() {
		return BillToPhone2;
	}
	public void setBillToPhone2(String billToPhone2) {
		BillToPhone2 = billToPhone2;
	}
	@Column
	public String getBillToFax() {
		return BillToFax;
	}
	public void setBillToFax(String billToFax) {
		BillToFax = billToFax;
	}
	@Column
	public String getBillToEmail() {
		return BillToEmail;
	}
	public void setBillToEmail(String billToEmail) {
		BillToEmail = billToEmail;
	}
	@Column
	public String getShipToContact() {
		return ShipToContact;
	}
	public void setShipToContact(String shipToContact) {
		ShipToContact = shipToContact;
	}
	@Column
	public String getShipToPhone1() {
		return ShipToPhone1;
	}
	public void setShipToPhone1(String shipToPhone1) {
		ShipToPhone1 = shipToPhone1;
	}
	@Column
	public String getShipToPhone2() {
		return ShipToPhone2;
	}
	public void setShipToPhone2(String shipToPhone2) {
		ShipToPhone2 = shipToPhone2;
	}
	@Column
	public String getFreightTerms() {
		return FreightTerms;
	}
	public void setFreightTerms(String freightTerms) {
		FreightTerms = freightTerms;
	}
	@Column
	public String getShippingInstructions() {
		return ShippingInstructions;
	}
	public void setShippingInstructions(String shippingInstructions) {
		ShippingInstructions = shippingInstructions;
	}
	@Column
	public String getShippingMethod() {
		return ShippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		ShippingMethod = shippingMethod;
	}
	@Column
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
	@Column
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	@Column
	public Date getLastModifiedDate() {
		return LastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	@Column
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}
    
    
    
}
