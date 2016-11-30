package com.avery.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "salesOrderLine")
public class SalesOrderLine {
int id;
String lineIndicator;
String division;
String orderSource;
String systemUniqueID;
String systemUniqueIDLineNo;
String soldToRBONumber;
String billToNumber;
String shipToNumber;
String shippingMethod;
String customerPoNumber;
String customerJob;
String internalItemNumber;
String customerItemNumber;
String itemDescription;
String orderdedQty;
Date dateOrdered;
Date customerRequestDate;
Date promiseDate;
String freightTerms;
String csr;
String packingInstruction;
String shippingInstructions;
String invoicelineInstruction;
String divisionforInterfaceERPORG;
String billToContact;
String billToTel;
String billToFax;
String billToEmail;
String shipToContact;
String shipToTel;
String shipToFAX;
String shipToEmail;
String artworkhold;
String artworkAttachment;
String variableDataBreakdown;
String manufacturingNotes;
String orderType;
String orderBy;
String endCustomer;
String shippingOnlyNotes;
String bankCharge;
String freightCharge;
String shippingHold;
String productionHold;
String splitShipSet;
String agreement;
String modelSerialNumber;
String waiveMOQ;
String APOType;
String price;


public SalesOrderLine(String division, String orderSource) {
	division = division;
	orderSource = orderSource;
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
@Column(name="lineIndicator" , length=100)
public String getLineIndicator() {
	return lineIndicator;
}
public void setLineIndicator(String lineIndicator) {
	this.lineIndicator = lineIndicator;
}
@Column(name="division" , length=100)
public String getDivision() {
	return division;
}
public void setDivision(String division) {
	this.division = division;
}
@Column(name="orderSource" , length=100)
public String getOrderSource() {
	return orderSource;
}
public void setOrderSource(String orderSource) {
	this.orderSource = orderSource;
}
@Column(name="systemUniqueID" , length=100)
public String getSystemUniqueID() {
	return systemUniqueID;
}
public void setSystemUniqueID(String systemUniqueID) {
	this.systemUniqueID = systemUniqueID;
}
@Column(name="systemUniqueIDLineNo" , length=100)
public String getSystemUniqueIDLineNo() {
	return systemUniqueIDLineNo;
}
public void setSystemUniqueIDLineNo(String systemUniqueIDLineNo) {
	this.systemUniqueIDLineNo = systemUniqueIDLineNo;
}
@Column(name="soldToRBONumber" , length=100)
public String getSoldToRBONumber() {
	return soldToRBONumber;
}
public void setSoldToRBONumber(String soldToRBONumber) {
	this.soldToRBONumber = soldToRBONumber;
}
@Column(name="billToNumber" , length=100)
public String getBillToNumber() {
	return billToNumber;
}
public void setBillToNumber(String billToNumber) {
	this.billToNumber = billToNumber;
}
@Column(name="shipToNumber" , length=100)
public String getShipToNumber() {
	return shipToNumber;
}
public void setShipToNumber(String shipToNumber) {
	this.shipToNumber = shipToNumber;
}
@Column(name="shippingMethod" , length=100)
public String getShippingMethod() {
	return shippingMethod;
}
public void setShippingMethod(String shippingMethod) {
	this.shippingMethod = shippingMethod;
}
@Column(name="customerPoNumber" , length=100)
public String getCustomerPoNumber() {
	return customerPoNumber;
}
public void setCustomerPoNumber(String customerPoNumber) {
	this.customerPoNumber = customerPoNumber;
}
@Column(name="customerJob" , length=100)
public String getCustomerJob() {
	return customerJob;
}
public void setCustomerJob(String customerJob) {
	this.customerJob = customerJob;
}
@Column(name="internalItemNumber" , length=100)
public String getInternalItemNumber() {
	return internalItemNumber;
}
public void setInternalItemNumber(String internalItemNumber) {
	this.internalItemNumber = internalItemNumber;
}
@Column(name="customerItemNumber" , length=100)
public String getCustomerItemNumber() {
	return customerItemNumber;
}
public void setCustomerItemNumber(String customerItemNumber) {
	this.customerItemNumber = customerItemNumber;
}
@Column(name="itemDescription" , length=100)
public String getItemDescription() {
	return itemDescription;
}
public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
}
@Column(name="orderdedQty" , length=100)
public String getOrderdedQty() {
	return orderdedQty;
}
public void setOrderdedQty(String orderdedQty) {
	this.orderdedQty = orderdedQty;
}
@Column(name="dateOrdered" )
public Date getDateOrdered() {
	return dateOrdered;
}
public void setDateOrdered(Date dateOrdered) {
	this.dateOrdered = dateOrdered;
}
@Column(name="customerRequestDate")
public Date getCustomerRequestDate() {
	return customerRequestDate;
}
public void setCustomerRequestDate(Date customerRequestDate) {
	this.customerRequestDate = customerRequestDate;
}
@Column(name="promiseDate" )
public Date getPromiseDate() {
	return promiseDate;
}
public void setPromiseDate(Date promiseDate) {
	this.promiseDate = promiseDate;
}
@Column(name="freightTerms" , length=100)
public String getFreightTerms() {
	return freightTerms;
}
public void setFreightTerms(String freightTerms) {
	this.freightTerms = freightTerms;
}
@Column(name="csr" , length=100)
public String getCsr() {
	return csr;
}
public void setCsr(String csr) {
	this.csr = csr;
}
@Column(name="packingInstruction" , length=100)
public String getPackingInstruction() {
	return packingInstruction;
}
public void setPackingInstruction(String packingInstruction) {
	this.packingInstruction = packingInstruction;
}
@Column(name="shippingInstructions" , length=100)
public String getShippingInstructions() {
	return shippingInstructions;
}
public void setShippingInstructions(String shippingInstructions) {
	this.shippingInstructions = shippingInstructions;
}
@Column(name="invoicelineInstruction" , length=100)
public String getInvoicelineInstruction() {
	return invoicelineInstruction;
}
public void setInvoicelineInstruction(String invoicelineInstruction) {
	this.invoicelineInstruction = invoicelineInstruction;
}
@Column(name="divisionforInterfaceERPORG" , length=100)
public String getDivisionforInterfaceERPORG() {
	return divisionforInterfaceERPORG;
}
public void setDivisionforInterfaceERPORG(String divisionforInterfaceERPORG) {
	this.divisionforInterfaceERPORG = divisionforInterfaceERPORG;
}
@Column(name="billToContact" , length=100)
public String getBillToContact() {
	return billToContact;
}
public void setBillToContact(String billToContact) {
	this.billToContact = billToContact;
}
@Column(name="billToTel" , length=100)
public String getBillToTel() {
	return billToTel;
}
public void setBillToTel(String billToTel) {
	this.billToTel = billToTel;
}
@Column(name="billToFax" , length=100)
public String getBillToFax() {
	return billToFax;
}
public void setBillToFax(String billToFax) {
	this.billToFax = billToFax;
}
@Column(name="billToEmail" , length=100)
public String getBillToEmail() {
	return billToEmail;
}
public void setBillToEmail(String billToEmail) {
	this.billToEmail = billToEmail;
}
@Column(name="shipToContact" , length=100)
public String getShipToContact() {
	return shipToContact;
}
public void setShipToContact(String shipToContact) {
	this.shipToContact = shipToContact;
}
@Column(name="shipToTel" , length=100)
public String getShipToTel() {
	return shipToTel;
}
public void setShipToTel(String shipToTel) {
	this.shipToTel = shipToTel;
}
@Column(name="shipToFAX" , length=100)
public String getShipToFAX() {
	return shipToFAX;
}
public void setShipToFAX(String shipToFAX) {
	this.shipToFAX = shipToFAX;
}
@Column(name="shipToEmail" , length=100)
public String getShipToEmail() {
	return shipToEmail;
}
public void setShipToEmail(String shipToEmail) {
	this.shipToEmail = shipToEmail;
}
@Column(name="artworkhold" , length=100)
public String getArtworkhold() {
	return artworkhold;
}
public void setArtworkhold(String artworkhold) {
	this.artworkhold = artworkhold;
}
@Column(name="artworkAttachment" , length=100)
public String getArtworkAttachment() {
	return artworkAttachment;
}
public void setArtworkAttachment(String artworkAttachment) {
	this.artworkAttachment = artworkAttachment;
}
@Column(name="variableDataBreakdown" , length=100)
public String getVariableDataBreakdown() {
	return variableDataBreakdown;
}
public void setVariableDataBreakdown(String variableDataBreakdown) {
	this.variableDataBreakdown = variableDataBreakdown;
}
@Column(name="manufacturingNotes" , length=100)
public String getManufacturingNotes() {
	return manufacturingNotes;
}
public void setManufacturingNotes(String manufacturingNotes) {
	this.manufacturingNotes = manufacturingNotes;
}
@Column(name="orderType" , length=100)
public String getOrderType() {
	return orderType;
}
public void setOrderType(String orderType) {
	this.orderType = orderType;
}
@Column(name="orderBy" , length=100)
public String getOrderBy() {
	return orderBy;
}
public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
}
@Column(name="endCustomer" , length=100)
public String getEndCustomer() {
	return endCustomer;
}
public void setEndCustomer(String endCustomer) {
	this.endCustomer = endCustomer;
}

@Column(name="shippingOnlyNotes" , length=100)
public String getShippingOnlyNotes() {
	return shippingOnlyNotes;
}
public void setShippingOnlyNotes(String shippingOnlyNotes) {
	this.shippingOnlyNotes = shippingOnlyNotes;
}
@Column(name="bankCharge" , length=100)
public String getBankCharge() {
	return bankCharge;
}
public void setBankCharge(String bankCharge) {
	this.bankCharge = bankCharge;
}
@Column(name="freightCharge" , length=100)
public String getFreightCharge() {
	return freightCharge;
}
public void setFreightCharge(String freightCharge) {
	this.freightCharge = freightCharge;
}
@Column(name="shippingHold" , length=100)
public String getShippingHold() {
	return shippingHold;
}
public void setShippingHold(String shippingHold) {
	this.shippingHold = shippingHold;
}
@Column(name="productionHold" , length=100)
public String getProductionHold() {
	return productionHold;
}
public void setProductionHold(String productionHold) {
	this.productionHold = productionHold;
}
@Column(name="splitShipSet" , length=100)
public String getSplitShipSet() {
	return splitShipSet;
}
public void setSplitShipSet(String splitShipSet) {
	this.splitShipSet = splitShipSet;
}
@Column(name="agreement" , length=100)
public String getAgreement() {
	return agreement;
}
public void setAgreement(String agreement) {
	this.agreement = agreement;
}
@Column(name="modelSerialNumber" , length=100)
public String getModelSerialNumber() {
	return modelSerialNumber;
}
public void setModelSerialNumber(String modelSerialNumber) {
	this.modelSerialNumber = modelSerialNumber;
}
@Column(name="waiveMOQ" , length=100)
public String getWaiveMOQ() {
	return waiveMOQ;
}
public void setWaiveMOQ(String waiveMOQ) {
	this.waiveMOQ = waiveMOQ;
}
@Column(name="APOType" , length=100)
public String getAPOType() {
	return APOType;
}
public void setAPOType(String aPOType) {
	APOType = aPOType;
}
@Column(name="price" , length=100)
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}

}
