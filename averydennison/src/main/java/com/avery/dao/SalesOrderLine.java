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
String LineIndicator;
String Division;
String OrderSource;
String SystemUniqueID;
String SystemUniqueIDLineNo;
String SOLDTORBONumber;
String BillToNumber;
String ShipToNumber;
String ShippingMethod;
String CustomerPONumber;
String CustomerJob;
String InternalItemNumber;
String CustomerItemNumber;
String ItemDescription;
String OrderdedQty;
Date DateOrdered;
Date CustomerRequestDate;
Date PromiseDate;
String FreightTerms;
String CSR;
String PackingInstruction;
String ShippingInstructions;
String InvoicelineInstruction;
String DivisionforInterfaceERPORG;
String BillToContact;
String BillToTEL;
String BillToFAX;
String BillToEMAIL;
String ShipToContact;
String ShipToTEL;
String ShipToFAX;
String ShipToEMAIL;
String Artworkhold;
String Artworkworkattachment;
String VariableDataBreakdown;
String ManufacturingNotes;
String Ordertype;
String OrderBy;
String EndCustomer;
String ShippingOnlyNotes;
String BankCharge;
String FreightCharge;
String ShippingHold;
String ProductionHold;
String SplitShipSet;
String Agreement;
String ModelSerialNumber;
String WaiveMOQ;
String APOType;
String Price;


public SalesOrderLine(String division, String orderSource) {
	Division = division;
	OrderSource = orderSource;
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
public String getLineIndicator() {
	return LineIndicator;
}
public void setLineIndicator(String lineIndicator) {
	LineIndicator = lineIndicator;
}
public String getDivision() {
	return Division;
}
public void setDivision(String division) {
	Division = division;
}
public String getOrderSource() {
	return OrderSource;
}
public void setOrderSource(String orderSource) {
	OrderSource = orderSource;
}
public String getSystemUniqueID() {
	return SystemUniqueID;
}
public void setSystemUniqueID(String systemUniqueID) {
	SystemUniqueID = systemUniqueID;
}
public String getSystemUniqueIDLineNo() {
	return SystemUniqueIDLineNo;
}
public void setSystemUniqueIDLineNo(String systemUniqueIDLineNo) {
	SystemUniqueIDLineNo = systemUniqueIDLineNo;
}
public String getSOLDTORBONumber() {
	return SOLDTORBONumber;
}
public void setSOLDTORBONumber(String sOLDTORBONumber) {
	SOLDTORBONumber = sOLDTORBONumber;
}
public String getBillToNumber() {
	return BillToNumber;
}
public void setBillToNumber(String billToNumber) {
	BillToNumber = billToNumber;
}
public String getShipToNumber() {
	return ShipToNumber;
}
public void setShipToNumber(String shipToNumber) {
	ShipToNumber = shipToNumber;
}
public String getShippingMethod() {
	return ShippingMethod;
}
public void setShippingMethod(String shippingMethod) {
	ShippingMethod = shippingMethod;
}
public String getCustomerPONumber() {
	return CustomerPONumber;
}
public void setCustomerPONumber(String customerPONumber) {
	CustomerPONumber = customerPONumber;
}
public String getCustomerJob() {
	return CustomerJob;
}
public void setCustomerJob(String customerJob) {
	CustomerJob = customerJob;
}
public String getInternalItemNumber() {
	return InternalItemNumber;
}
public void setInternalItemNumber(String internalItemNumber) {
	InternalItemNumber = internalItemNumber;
}
public String getCustomerItemNumber() {
	return CustomerItemNumber;
}
public void setCustomerItemNumber(String customerItemNumber) {
	CustomerItemNumber = customerItemNumber;
}
public String getItemDescription() {
	return ItemDescription;
}
public void setItemDescription(String itemDescription) {
	ItemDescription = itemDescription;
}
public String getOrderdedQty() {
	return OrderdedQty;
}
public void setOrderdedQty(String orderdedQty) {
	OrderdedQty = orderdedQty;
}
public Date getDateOrdered() {
	return DateOrdered;
}
public void setDateOrdered(Date dateOrdered) {
	DateOrdered = dateOrdered;
}
public Date getCustomerRequestDate() {
	return CustomerRequestDate;
}
public void setCustomerRequestDate(Date customerRequestDate) {
	CustomerRequestDate = customerRequestDate;
}
public Date getPromiseDate() {
	return PromiseDate;
}
public void setPromiseDate(Date promiseDate) {
	PromiseDate = promiseDate;
}
public String getFreightTerms() {
	return FreightTerms;
}
public void setFreightTerms(String freightTerms) {
	FreightTerms = freightTerms;
}
public String getCSR() {
	return CSR;
}
public void setCSR(String cSR) {
	CSR = cSR;
}
public String getPackingInstruction() {
	return PackingInstruction;
}
public void setPackingInstruction(String packingInstruction) {
	PackingInstruction = packingInstruction;
}
public String getShippingInstructions() {
	return ShippingInstructions;
}
public void setShippingInstructions(String shippingInstructions) {
	ShippingInstructions = shippingInstructions;
}
public String getInvoicelineInstruction() {
	return InvoicelineInstruction;
}
public void setInvoicelineInstruction(String invoicelineInstruction) {
	InvoicelineInstruction = invoicelineInstruction;
}
public String getDivisionforInterfaceERPORG() {
	return DivisionforInterfaceERPORG;
}
public void setDivisionforInterfaceERPORG(String divisionforInterfaceERPORG) {
	DivisionforInterfaceERPORG = divisionforInterfaceERPORG;
}
public String getBillToContact() {
	return BillToContact;
}
public void setBillToContact(String billToContact) {
	BillToContact = billToContact;
}
public String getBillToTEL() {
	return BillToTEL;
}
public void setBillToTEL(String billToTEL) {
	BillToTEL = billToTEL;
}
public String getBillToFAX() {
	return BillToFAX;
}
public void setBillToFAX(String billToFAX) {
	BillToFAX = billToFAX;
}
public String getBillToEMAIL() {
	return BillToEMAIL;
}
public void setBillToEMAIL(String billToEMAIL) {
	BillToEMAIL = billToEMAIL;
}
public String getShipToContact() {
	return ShipToContact;
}
public void setShipToContact(String shipToContact) {
	ShipToContact = shipToContact;
}
public String getShipToTEL() {
	return ShipToTEL;
}
public void setShipToTEL(String shipToTEL) {
	ShipToTEL = shipToTEL;
}
public String getShipToFAX() {
	return ShipToFAX;
}
public void setShipToFAX(String shipToFAX) {
	ShipToFAX = shipToFAX;
}
public String getShipToEMAIL() {
	return ShipToEMAIL;
}
public void setShipToEMAIL(String shipToEMAIL) {
	ShipToEMAIL = shipToEMAIL;
}
public String getArtworkhold() {
	return Artworkhold;
}
public void setArtworkhold(String artworkhold) {
	Artworkhold = artworkhold;
}
public String getArtworkworkattachment() {
	return Artworkworkattachment;
}
public void setArtworkworkattachment(String artworkworkattachment) {
	Artworkworkattachment = artworkworkattachment;
}
public String getVariableDataBreakdown() {
	return VariableDataBreakdown;
}
public void setVariableDataBreakdown(String variableDataBreakdown) {
	VariableDataBreakdown = variableDataBreakdown;
}
public String getManufacturingNotes() {
	return ManufacturingNotes;
}
public void setManufacturingNotes(String manufacturingNotes) {
	ManufacturingNotes = manufacturingNotes;
}
public String getOrdertype() {
	return Ordertype;
}
public void setOrdertype(String ordertype) {
	Ordertype = ordertype;
}
public String getOrderBy() {
	return OrderBy;
}
public void setOrderBy(String orderBy) {
	OrderBy = orderBy;
}
public String getEndCustomer() {
	return EndCustomer;
}
public void setEndCustomer(String endCustomer) {
	EndCustomer = endCustomer;
}
public String getShippingOnlyNotes() {
	return ShippingOnlyNotes;
}
public void setShippingOnlyNotes(String shippingOnlyNotes) {
	ShippingOnlyNotes = shippingOnlyNotes;
}
public String getBankCharge() {
	return BankCharge;
}
public void setBankCharge(String bankCharge) {
	BankCharge = bankCharge;
}
public String getFreightCharge() {
	return FreightCharge;
}
public void setFreightCharge(String freightCharge) {
	FreightCharge = freightCharge;
}
public String getShippingHold() {
	return ShippingHold;
}
public void setShippingHold(String shippingHold) {
	ShippingHold = shippingHold;
}
public String getProductionHold() {
	return ProductionHold;
}
public void setProductionHold(String productionHold) {
	ProductionHold = productionHold;
}
public String getSplitShipSet() {
	return SplitShipSet;
}
public void setSplitShipSet(String splitShipSet) {
	SplitShipSet = splitShipSet;
}
public String getAgreement() {
	return Agreement;
}
public void setAgreement(String agreement) {
	Agreement = agreement;
}
public String getModelSerialNumber() {
	return ModelSerialNumber;
}
public void setModelSerialNumber(String modelSerialNumber) {
	ModelSerialNumber = modelSerialNumber;
}
public String getWaiveMOQ() {
	return WaiveMOQ;
}
public void setWaiveMOQ(String waiveMOQ) {
	WaiveMOQ = waiveMOQ;
}
public String getAPOType() {
	return APOType;
}
public void setAPOType(String aPOType) {
	APOType = aPOType;
}
public String getPrice() {
	return Price;
}
public void setPrice(String price) {
	Price = price;
}


}
