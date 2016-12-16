package com.avery.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "orderline")
public class OrderLine {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id",nullable=false)
	int id;
	@Column(name = "customerPONumber", length = 50)
	String customerPONumber;
	@Column(name = "orderedDate")
	Date orderedDate;
	@Column(name = "partnerCustomerName", length = 250)
	String partnerCustomerName;
	@Column(name = "bulk")
	boolean bulk;
	@Column(name = "partnerVendorName", length = 250)
	String partnerVendorName;
	@Column(name = "shipToCustomer", length = 250)
	String shipToCustomer;
	@Column(name = "shipToContact", length = 250)
	String shipToContact;
	@Column(name = "shipToAddress1", length = 250)
	String shipToAddress1;
	@Column(name = "shipToAddress2", length = 250)
	String shipToAddress2;
	@Column(name = "shipToAddress3", length = 250)
	String shipToAddress3;
	@Column(name = "shipToCity", length = 100)
	String shipToCity;
	@Column(name = "shipToState", length = 100)
	String shipToState;
	@Column(name = "shipToZip", length = 50)
	String shipToZip;
	@Column(name = "shipToCountry", length = 100)
	String shipToCountry;
	@Column(name = "shipToTelephone", length = 50)
	String shipToTelephone;
	@Column(name = "shipToFax", length = 100)
	String shipToFax;
	@Column(name = "shipToEmail", length = 100)
	String shipToEmail;
	@Column(name = "billToCustomer", length = 250)
	String billToCustomer;
	@Column(name = "billToContact", length = 250)
	String billToContact;
	@Column(name = "billToAddress1", length = 250)
	String billToAddress1;
	@Column(name = "billToAddress2", length = 250)
	String billToAddress2;
	@Column(name = "billToAddress3", length = 250)
	String billToAddress3;
	@Column(name = "billToCity", length = 100)
	String billToCity;
	@Column(name = "billToState", length = 100)
	String billToState;
	@Column(name = "billToZip", length = 50)
	String billToZip;
	@Column(name = "billToCountry", length = 100)
	String billToCountry;
	@Column(name = "billToTelephone", length = 50)
	String billToTelephone;
	@Column(name = "billToFax", length = 100)
	String billToFax;
	@Column(name = "billToEmail", length = 100)
	String billToEmail;
	@Column(name = "requestedDevliveryDate")
	Date requestedDevliveryDate;
	@Column(name = "shippingMethod", length = 50)
	String shippingMethod;
	@Column(name = "specialInstruction", length = 500)
	String specialInstruction;
	@Column(name = "orderReceivedDate")
	Date orderReceivedDate;
	@Column(name = "soldToRBONumber", length = 50)
	String soldToRBONumber;
	@Column(name = "oracleBillToSiteNumber", length = 50)
	String oracleBillToSiteNumber;
	@Column(name = "oracleShipToSiteNumber", length = 50)
	String oracleShipToSiteNumber;
	@Column(name = "retailerPO_CustomerJob", length = 100)
	String retailerPO_CustomerJob;
	@Column(name = "averyItemNumber", length = 50)
	String averyItemNumber;
	@Column(name = "customerItemNumber", length = 50)
	String customerItemNumber;
	@Column(name = "itemDescription", length = 50)
	String itemDescription;
	@Column(name = "customerColorCode", length = 50)
	String customerColorCode;
	@Column(name = "customerColorDescription", length = 50)
	String customerColorDescription;
	@Column(name = "customerSize", length = 50)
	String customerSize;
	@Column(name = "customerUnitPrice", length = 50)
	String customerUnitPrice;
	@Column(name = "customerCost", length = 50)
	String customerCost;
	@Column(name = "contractNumber", length = 50)
	String contractNumber;
	@Column(name = "styleNo", length = 50)
	String styleNo;
	@Column(name = "customerItemNumber1", length = 50)
	String customerItemNumber1;
	@Column(name = "customerItemNumber2", length = 50)
	String customerItemNumber2;
	@Column(name = "customerSeason", length = 50)
	String customerSeason;
	@Column(name = "customerUOM", length = 50)
	String customerUOM;
	@Column(name = "customerOrderedQty", length = 50)
	String customerOrderedQty;
	@Column(name = "calculatedOrderdedQty", length = 10)
	String calculatedOrderdedQty;
	@Column(name = "orderDate")
	Date orderDate;
	@Column(name = " customerRequestDate")
	Date customerRequestDate;
	@Column(name = "promiseDate")
	Date promiseDate;
	@Column(name = "freightTerms", length = 50)
	String freightTerms;
	@Column(name = "csr", length = 50)
	String csr;
	@Column(name = "packingInstruction", length = 500)
	String packingInstruction;
	@Column(name = "shippingInstructions", length = 500)
	String shippingInstructions;
	@Column(name = "invoicelineInstruction", length = 500)
	String invoicelineInstruction;
	@Column(name = "divisionForInterfaceERPORG", length = 100)
	String divisionForInterfaceERPORG;
	@Column(name = "artWorkhold", length = 5)
	String artWorkhold;
	@Column(name = "artworkAttachment", length = 5)
	String artworkAttachment;
	@Column(name = "variableDataBreakdown", length = 500)
	String variableDataBreakdown;
	@Column(name = "manufacturingNotes", length = 500)
	String manufacturingNotes;
	@Column(name = "orderType", length = 50)
	String orderType;
	@Column(name = "orderBy", length = 50)
	String orderBy;
	@Column(name = "endCustomer", length = 50)
	String endCustomer;
	@Column(name = "shippingOnlyNotes", length = 500)
	String shippingOnlyNotes;
	@Column(name = "bankCharge", length = 10)
	String bankCharge;
	@Column(name = "freightCharge", length = 10)
	String freightCharge;
	@Column(name = "shippingHold", length = 50)
	String shippingHold;
	@Column(name = "productionHold", length = 5)
	String productionHold;
	@Column(name = "splitShipset", length = 5)
	String splitShipset;
	@Column(name = "agreement", length = 50)
	String agreement;
	@Column(name = "modelSerialNumber", length = 50)
	String modelSerialNumber;
	@Column(name = "waiveMOQ", length = 5)
	String waiveMOQ;
	@Column(name = "targetSystem", length = 50)
	String targetSystem;
	@Column(name = "APOType", length = 5)
	String APOType;
	@Column(name = "sentToOracleDate")
	Date sentToOracleDate;
	@Column(name = "status", length = 100,nullable=false)
	String status;
	@Column(name="reviseOrderFlag",length=50)
	String reviseOrderFlag;
	@Column(name="cooTranslationFlag",length=50)
	String cooTranslationFlag;
	@Column(name="febricPercentageFlag",length=50)
	String febricPercentageFlag;
	@Column(name = "duplicatePOFlag",length=50)
	String duplicatePOFlag;
	@Column(name = "customerPOFlag",length=50)
	String customerPOFlag;
	@Column(name = "bulkSampleValidationFlag",length=50)
	String bulkSampleValidationFlag;
	@Column(name = "MOQValidationFlag",length=50)
	String MOQValidationFlag;
	@Column(name = "ATOValidationFlag",length=50)
	String ATOValidationFlag;
	@Column(name = "mandatoryVariableDataFieldFlag",length=50)
	String mandatoryVariableDataFieldFlag;
	@Column(name = "HTLSizePageValidationFlag",length=50)
	String HTLSizePageValidationFlag;
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "createdBy", length = 50)
	String createdBy;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@Column(name = "lastModifiedBy", length = 50)
	String lastModifiedBy;
	@Column(name = "region", length = 100)
	String region;
	@Column(name = "PONumber", length = 100)
	String PONumber;
	@Column(name = "comment", length = 250)
	String comment;
	@Column(name = "roundQty", length = 100)
	String roundQty;
	@Column(name = "MOQDiffQty", length = 100)
	String MOQDiffQty;
	@Column(name = "updateMOQ", length = 100)
	String updateMOQ;
	@Column(name = "customerNumber", length = 100)
	String customerNumber;
	@Column(name = "rushOrderCheck", length = 100)
	String rushOrderCheck;
	@Column(name = "FOO", length = 100)
	String FOO;
	@Column(name = "sample", length = 100)
	String sample;
	@Column(name = "qtyUnit", length = 100)
	String qtyUnit;
	@Column(name = "remark", length = 100)
	String remark;
	@Column(name = "pageSize", length = 100)
	String pageSize;
	@Column(name = "fabricCode", length = 100)
	String fabricCode;
	@Column(name = "carrier", length = 100)
	String carrier;
	@Column(name = "account", length = 100)
	String account;
	@Column(name = "shipVia", length = 100)
	String shipVia;
	@Column(name = "createdByName", length = 50)
	String createdByName;
	@Column(name = "lastModifiedByName", length = 50)
	String lastModifiedByName;
	@Column(name = "productLineType", length = 50)
	String productLineType;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="orderQueueId",nullable=false)
	OrderFileQueue varOrderFileQueue;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="varOrderLine",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<OrderLineDetails> listOrderlineDetails=new ArrayList<OrderLineDetails>();
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="varOrderLine",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<SalesOrderLine> listSalesOrderLine=new ArrayList<SalesOrderLine>();
	
	
	public OrderLine() {
	}


	int getId() {
		return id;
	}


	void setId(int id) {
		this.id = id;
	}


	String getCustomerPONumber() {
		return customerPONumber;
	}


	void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}


	Date getOrderedDate() {
		return orderedDate;
	}


	void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}


	String getPartnerCustomerName() {
		return partnerCustomerName;
	}


	void setPartnerCustomerName(String partnerCustomerName) {
		this.partnerCustomerName = partnerCustomerName;
	}


	boolean isBulk() {
		return bulk;
	}


	void setBulk(boolean bulk) {
		this.bulk = bulk;
	}


	String getPartnerVendorName() {
		return partnerVendorName;
	}


	void setPartnerVendorName(String partnerVendorName) {
		this.partnerVendorName = partnerVendorName;
	}


	String getShipToCustomer() {
		return shipToCustomer;
	}


	void setShipToCustomer(String shipToCustomer) {
		this.shipToCustomer = shipToCustomer;
	}


	String getShipToContact() {
		return shipToContact;
	}


	void setShipToContact(String shipToContact) {
		this.shipToContact = shipToContact;
	}


	String getShipToAddress1() {
		return shipToAddress1;
	}


	void setShipToAddress1(String shipToAddress1) {
		this.shipToAddress1 = shipToAddress1;
	}


	String getShipToAddress2() {
		return shipToAddress2;
	}


	void setShipToAddress2(String shipToAddress2) {
		this.shipToAddress2 = shipToAddress2;
	}


	String getShipToAddress3() {
		return shipToAddress3;
	}


	void setShipToAddress3(String shipToAddress3) {
		this.shipToAddress3 = shipToAddress3;
	}


	String getShipToCity() {
		return shipToCity;
	}


	void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}


	String getShipToState() {
		return shipToState;
	}


	void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}


	String getShipToZip() {
		return shipToZip;
	}


	void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}


	String getShipToCountry() {
		return shipToCountry;
	}


	void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}


	String getShipToTelephone() {
		return shipToTelephone;
	}


	void setShipToTelephone(String shipToTelephone) {
		this.shipToTelephone = shipToTelephone;
	}


	String getShipToFax() {
		return shipToFax;
	}


	void setShipToFax(String shipToFax) {
		this.shipToFax = shipToFax;
	}


	String getShipToEmail() {
		return shipToEmail;
	}


	void setShipToEmail(String shipToEmail) {
		this.shipToEmail = shipToEmail;
	}


	String getBillToCustomer() {
		return billToCustomer;
	}


	void setBillToCustomer(String billToCustomer) {
		this.billToCustomer = billToCustomer;
	}


	String getBillToContact() {
		return billToContact;
	}


	void setBillToContact(String billToContact) {
		this.billToContact = billToContact;
	}


	String getBillToAddress1() {
		return billToAddress1;
	}


	void setBillToAddress1(String billToAddress1) {
		this.billToAddress1 = billToAddress1;
	}


	String getBillToAddress2() {
		return billToAddress2;
	}


	void setBillToAddress2(String billToAddress2) {
		this.billToAddress2 = billToAddress2;
	}


	String getBillToAddress3() {
		return billToAddress3;
	}


	void setBillToAddress3(String billToAddress3) {
		this.billToAddress3 = billToAddress3;
	}


	String getBillToCity() {
		return billToCity;
	}


	void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}


	String getBillToState() {
		return billToState;
	}


	void setBillToState(String billToState) {
		this.billToState = billToState;
	}


	String getBillToZip() {
		return billToZip;
	}


	void setBillToZip(String billToZip) {
		this.billToZip = billToZip;
	}


	String getBillToCountry() {
		return billToCountry;
	}


	void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
	}


	String getBillToTelephone() {
		return billToTelephone;
	}


	void setBillToTelephone(String billToTelephone) {
		this.billToTelephone = billToTelephone;
	}


	String getBillToFax() {
		return billToFax;
	}


	void setBillToFax(String billToFax) {
		this.billToFax = billToFax;
	}


	String getBillToEmail() {
		return billToEmail;
	}


	void setBillToEmail(String billToEmail) {
		this.billToEmail = billToEmail;
	}


	Date getRequestedDevliveryDate() {
		return requestedDevliveryDate;
	}


	void setRequestedDevliveryDate(Date requestedDevliveryDate) {
		this.requestedDevliveryDate = requestedDevliveryDate;
	}


	String getShippingMethod() {
		return shippingMethod;
	}


	void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}


	String getSpecialInstruction() {
		return specialInstruction;
	}


	void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}


	Date getOrderReceivedDate() {
		return orderReceivedDate;
	}


	void setOrderReceivedDate(Date orderReceivedDate) {
		this.orderReceivedDate = orderReceivedDate;
	}


	String getSoldToRBONumber() {
		return soldToRBONumber;
	}


	void setSoldToRBONumber(String soldToRBONumber) {
		this.soldToRBONumber = soldToRBONumber;
	}


	String getOracleBillToSiteNumber() {
		return oracleBillToSiteNumber;
	}


	void setOracleBillToSiteNumber(String oracleBillToSiteNumber) {
		this.oracleBillToSiteNumber = oracleBillToSiteNumber;
	}


	String getOracleShipToSiteNumber() {
		return oracleShipToSiteNumber;
	}


	void setOracleShipToSiteNumber(String oracleShipToSiteNumber) {
		this.oracleShipToSiteNumber = oracleShipToSiteNumber;
	}


	String getRetailerPO_CustomerJob() {
		return retailerPO_CustomerJob;
	}


	void setRetailerPO_CustomerJob(String retailerPO_CustomerJob) {
		this.retailerPO_CustomerJob = retailerPO_CustomerJob;
	}


	String getAveryItemNumber() {
		return averyItemNumber;
	}


	void setAveryItemNumber(String averyItemNumber) {
		this.averyItemNumber = averyItemNumber;
	}


	String getCustomerItemNumber() {
		return customerItemNumber;
	}


	void setCustomerItemNumber(String customerItemNumber) {
		this.customerItemNumber = customerItemNumber;
	}


	String getItemDescription() {
		return itemDescription;
	}


	void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	String getCustomerColorCode() {
		return customerColorCode;
	}


	void setCustomerColorCode(String customerColorCode) {
		this.customerColorCode = customerColorCode;
	}


	String getCustomerColorDescription() {
		return customerColorDescription;
	}


	void setCustomerColorDescription(String customerColorDescription) {
		this.customerColorDescription = customerColorDescription;
	}


	String getCustomerSize() {
		return customerSize;
	}


	void setCustomerSize(String customerSize) {
		this.customerSize = customerSize;
	}


	String getCustomerUnitPrice() {
		return customerUnitPrice;
	}


	void setCustomerUnitPrice(String customerUnitPrice) {
		this.customerUnitPrice = customerUnitPrice;
	}


	String getCustomerCost() {
		return customerCost;
	}


	void setCustomerCost(String customerCost) {
		this.customerCost = customerCost;
	}


	String getContractNumber() {
		return contractNumber;
	}


	void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}


	String getStyleNo() {
		return styleNo;
	}


	void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}


	String getCustomerItemNumber1() {
		return customerItemNumber1;
	}


	void setCustomerItemNumber1(String customerItemNumber1) {
		this.customerItemNumber1 = customerItemNumber1;
	}


	String getCustomerItemNumber2() {
		return customerItemNumber2;
	}


	void setCustomerItemNumber2(String customerItemNumber2) {
		this.customerItemNumber2 = customerItemNumber2;
	}


	String getCustomerSeason() {
		return customerSeason;
	}


	void setCustomerSeason(String customerSeason) {
		this.customerSeason = customerSeason;
	}


	String getCustomerUOM() {
		return customerUOM;
	}


	void setCustomerUOM(String customerUOM) {
		this.customerUOM = customerUOM;
	}


	String getCustomerOrderedQty() {
		return customerOrderedQty;
	}


	void setCustomerOrderedQty(String customerOrderedQty) {
		this.customerOrderedQty = customerOrderedQty;
	}


	String getCalculatedOrderdedQty() {
		return calculatedOrderdedQty;
	}


	void setCalculatedOrderdedQty(String calculatedOrderdedQty) {
		this.calculatedOrderdedQty = calculatedOrderdedQty;
	}


	Date getOrderDate() {
		return orderDate;
	}


	void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	Date getCustomerRequestDate() {
		return customerRequestDate;
	}


	void setCustomerRequestDate(Date customerRequestDate) {
		this.customerRequestDate = customerRequestDate;
	}


	Date getPromiseDate() {
		return promiseDate;
	}


	void setPromiseDate(Date promiseDate) {
		this.promiseDate = promiseDate;
	}


	String getFreightTerms() {
		return freightTerms;
	}


	void setFreightTerms(String freightTerms) {
		this.freightTerms = freightTerms;
	}


	String getCsr() {
		return csr;
	}


	void setCsr(String csr) {
		this.csr = csr;
	}


	String getPackingInstruction() {
		return packingInstruction;
	}


	void setPackingInstruction(String packingInstruction) {
		this.packingInstruction = packingInstruction;
	}


	String getShippingInstructions() {
		return shippingInstructions;
	}


	void setShippingInstructions(String shippingInstructions) {
		this.shippingInstructions = shippingInstructions;
	}


	String getInvoicelineInstruction() {
		return invoicelineInstruction;
	}


	void setInvoicelineInstruction(String invoicelineInstruction) {
		this.invoicelineInstruction = invoicelineInstruction;
	}


	String getDivisionForInterfaceERPORG() {
		return divisionForInterfaceERPORG;
	}


	void setDivisionForInterfaceERPORG(String divisionForInterfaceERPORG) {
		this.divisionForInterfaceERPORG = divisionForInterfaceERPORG;
	}


	String getArtWorkhold() {
		return artWorkhold;
	}


	void setArtWorkhold(String artWorkhold) {
		this.artWorkhold = artWorkhold;
	}


	String getArtworkAttachment() {
		return artworkAttachment;
	}


	void setArtworkAttachment(String artworkAttachment) {
		this.artworkAttachment = artworkAttachment;
	}


	String getVariableDataBreakdown() {
		return variableDataBreakdown;
	}


	void setVariableDataBreakdown(String variableDataBreakdown) {
		this.variableDataBreakdown = variableDataBreakdown;
	}


	String getManufacturingNotes() {
		return manufacturingNotes;
	}


	void setManufacturingNotes(String manufacturingNotes) {
		this.manufacturingNotes = manufacturingNotes;
	}


	String getOrderType() {
		return orderType;
	}


	void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	String getOrderBy() {
		return orderBy;
	}


	void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	String getEndCustomer() {
		return endCustomer;
	}


	void setEndCustomer(String endCustomer) {
		this.endCustomer = endCustomer;
	}


	String getShippingOnlyNotes() {
		return shippingOnlyNotes;
	}


	void setShippingOnlyNotes(String shippingOnlyNotes) {
		this.shippingOnlyNotes = shippingOnlyNotes;
	}


	String getBankCharge() {
		return bankCharge;
	}


	void setBankCharge(String bankCharge) {
		this.bankCharge = bankCharge;
	}


	String getFreightCharge() {
		return freightCharge;
	}


	void setFreightCharge(String freightCharge) {
		this.freightCharge = freightCharge;
	}


	String getShippingHold() {
		return shippingHold;
	}


	void setShippingHold(String shippingHold) {
		this.shippingHold = shippingHold;
	}


	String getProductionHold() {
		return productionHold;
	}


	void setProductionHold(String productionHold) {
		this.productionHold = productionHold;
	}


	String getSplitShipset() {
		return splitShipset;
	}


	void setSplitShipset(String splitShipset) {
		this.splitShipset = splitShipset;
	}


	String getAgreement() {
		return agreement;
	}


	void setAgreement(String agreement) {
		this.agreement = agreement;
	}


	String getModelSerialNumber() {
		return modelSerialNumber;
	}


	void setModelSerialNumber(String modelSerialNumber) {
		this.modelSerialNumber = modelSerialNumber;
	}


	String getWaiveMOQ() {
		return waiveMOQ;
	}


	void setWaiveMOQ(String waiveMOQ) {
		this.waiveMOQ = waiveMOQ;
	}


	String getTargetSystem() {
		return targetSystem;
	}


	void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}


	String getAPOType() {
		return APOType;
	}


	void setAPOType(String aPOType) {
		APOType = aPOType;
	}


	Date getSentToOracleDate() {
		return sentToOracleDate;
	}


	void setSentToOracleDate(Date sentToOracleDate) {
		this.sentToOracleDate = sentToOracleDate;
	}


	String getStatus() {
		return status;
	}


	void setStatus(String status) {
		this.status = status;
	}


	String getReviseOrderFlag() {
		return reviseOrderFlag;
	}


	void setReviseOrderFlag(String reviseOrderFlag) {
		this.reviseOrderFlag = reviseOrderFlag;
	}


	String getCooTranslationFlag() {
		return cooTranslationFlag;
	}


	void setCooTranslationFlag(String cooTranslationFlag) {
		this.cooTranslationFlag = cooTranslationFlag;
	}


	String getFebricPercentageFlag() {
		return febricPercentageFlag;
	}


	void setFebricPercentageFlag(String febricPercentageFlag) {
		this.febricPercentageFlag = febricPercentageFlag;
	}


	String getDuplicatePOFlag() {
		return duplicatePOFlag;
	}


	void setDuplicatePOFlag(String duplicatePOFlag) {
		this.duplicatePOFlag = duplicatePOFlag;
	}


	String getCustomerPOFlag() {
		return customerPOFlag;
	}


	void setCustomerPOFlag(String customerPOFlag) {
		this.customerPOFlag = customerPOFlag;
	}


	String getBulkSampleValidationFlag() {
		return bulkSampleValidationFlag;
	}


	void setBulkSampleValidationFlag(String bulkSampleValidationFlag) {
		this.bulkSampleValidationFlag = bulkSampleValidationFlag;
	}


	String getMOQValidationFlag() {
		return MOQValidationFlag;
	}


	void setMOQValidationFlag(String mOQValidationFlag) {
		MOQValidationFlag = mOQValidationFlag;
	}


	String getATOValidationFlag() {
		return ATOValidationFlag;
	}


	void setATOValidationFlag(String aTOValidationFlag) {
		ATOValidationFlag = aTOValidationFlag;
	}


	String getMandatoryVariableDataFieldFlag() {
		return mandatoryVariableDataFieldFlag;
	}


	void setMandatoryVariableDataFieldFlag(String mandatoryVariableDataFieldFlag) {
		this.mandatoryVariableDataFieldFlag = mandatoryVariableDataFieldFlag;
	}


	String getHTLSizePageValidationFlag() {
		return HTLSizePageValidationFlag;
	}


	void setHTLSizePageValidationFlag(String hTLSizePageValidationFlag) {
		HTLSizePageValidationFlag = hTLSizePageValidationFlag;
	}


	Date getCreatedDate() {
		return createdDate;
	}


	void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	String getCreatedBy() {
		return createdBy;
	}


	void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	String getLastModifiedBy() {
		return lastModifiedBy;
	}


	void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	String getRegion() {
		return region;
	}


	void setRegion(String region) {
		this.region = region;
	}


	String getPONumber() {
		return PONumber;
	}


	void setPONumber(String pONumber) {
		PONumber = pONumber;
	}


	String getComment() {
		return comment;
	}


	void setComment(String comment) {
		this.comment = comment;
	}


	String getRoundQty() {
		return roundQty;
	}


	void setRoundQty(String roundQty) {
		this.roundQty = roundQty;
	}


	String getMOQDiffQty() {
		return MOQDiffQty;
	}


	void setMOQDiffQty(String mOQDiffQty) {
		MOQDiffQty = mOQDiffQty;
	}


	String getUpdateMOQ() {
		return updateMOQ;
	}


	void setUpdateMOQ(String updateMOQ) {
		this.updateMOQ = updateMOQ;
	}


	String getCustomerNumber() {
		return customerNumber;
	}


	void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	String getRushOrderCheck() {
		return rushOrderCheck;
	}


	void setRushOrderCheck(String rushOrderCheck) {
		this.rushOrderCheck = rushOrderCheck;
	}


	String getFOO() {
		return FOO;
	}


	void setFOO(String fOO) {
		FOO = fOO;
	}


	String getSample() {
		return sample;
	}


	void setSample(String sample) {
		this.sample = sample;
	}


	String getQtyUnit() {
		return qtyUnit;
	}


	void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}


	String getRemark() {
		return remark;
	}


	void setRemark(String remark) {
		this.remark = remark;
	}


	String getPageSize() {
		return pageSize;
	}


	void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	String getFabricCode() {
		return fabricCode;
	}


	void setFabricCode(String fabricCode) {
		this.fabricCode = fabricCode;
	}


	String getCarrier() {
		return carrier;
	}


	void setCarrier(String carrier) {
		this.carrier = carrier;
	}


	String getAccount() {
		return account;
	}


	void setAccount(String account) {
		this.account = account;
	}


	String getShipVia() {
		return shipVia;
	}


	void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}


	String getCreatedByName() {
		return createdByName;
	}


	void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}


	String getLastModifiedByName() {
		return lastModifiedByName;
	}


	void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}


	String getProductLineType() {
		return productLineType;
	}


	void setProductLineType(String productLineType) {
		this.productLineType = productLineType;
	}


	OrderFileQueue getVarOrderFileQueue() {
		return varOrderFileQueue;
	}


	void setVarOrderFileQueue(OrderFileQueue varOrderFileQueue) {
		this.varOrderFileQueue = varOrderFileQueue;
	}


	List<OrderLineDetails> getListOrderlineDetails() {
		return listOrderlineDetails;
	}


	void setListOrderlineDetails(List<OrderLineDetails> listOrderlineDetails) {
		this.listOrderlineDetails = listOrderlineDetails;
	}


	List<SalesOrderLine> getListSalesOrderLine() {
		return listSalesOrderLine;
	}


	void setListSalesOrderLine(List<SalesOrderLine> listSalesOrderLine) {
		this.listSalesOrderLine = listSalesOrderLine;
	}


	
	
}
