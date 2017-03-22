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
	@Column(name = "productionLine", length = 1000)
	String productionLine;
	@Column(name = "shipMark")
	String shipMark;
	@Column(name = "additionalLabelInternalItem")
	String additionalLabelInternalItem; 
	/*@Column(name = "createdByName", length = 50)
	String createdByName;
	@Column(name = "lastModifiedByName", length = 50)
	String lastModifiedByName;*/
	@Column(name = "productLineType", length = 50)
	String productLineType;
	@Column(name = "skuQtyDiffrence", length = 100)
	String skuQtyDiffrence; 
	@Column(name="addditionalFileId",length=100)
	String additionalFileId;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="orderQueueId",nullable=false)
	OrderFileQueue varOrderFileQueue;
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy="varOrderLine",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	List<OrderLineDetails> listOrderlineDetails=new ArrayList<OrderLineDetails>();
/*	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="varOrderLine",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<SalesOrderLine> listSalesOrderLine=new ArrayList<SalesOrderLine>();*/
	
	@Column(name = "averyMOQ", length = 50)
	String averyMOQ;
	@Column(name = "averyRoundupQty", length = 50)
	String averyRoundupQty;
	@Column(name = "averyATO", length = 50)
	String averyATO;
	@Column(name = "averyRegion", length = 50)
	String averyRegion;
	@Column(name = "averyProductLineType", length = 50)
	String averyProductLineType;
	
	@Column(name = "averyBulk", length = 50)
	String averyBulk;
	
	@Column(name = "orderFileOrderType", length = 50)
	String orderFileOrderType;
	
	public OrderLine() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCustomerPONumber() {
		return customerPONumber;
	}


	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}


	public Date getOrderedDate() {
		return orderedDate;
	}


	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}


	public String getPartnerCustomerName() {
		return partnerCustomerName;
	}


	public void setPartnerCustomerName(String partnerCustomerName) {
		this.partnerCustomerName = partnerCustomerName;
	}


	public boolean isBulk() {
		return bulk;
	}


	public void setBulk(boolean bulk) {
		this.bulk = bulk;
	}


	public String getPartnerVendorName() {
		return partnerVendorName;
	}


	public void setPartnerVendorName(String partnerVendorName) {
		this.partnerVendorName = partnerVendorName;
	}


	public String getShipToCustomer() {
		return shipToCustomer;
	}


	public void setShipToCustomer(String shipToCustomer) {
		this.shipToCustomer = shipToCustomer;
	}


	public String getShipToContact() {
		return shipToContact;
	}


	public void setShipToContact(String shipToContact) {
		this.shipToContact = shipToContact;
	}


	public String getShipToAddress1() {
		return shipToAddress1;
	}


	public void setShipToAddress1(String shipToAddress1) {
		this.shipToAddress1 = shipToAddress1;
	}


	public String getShipToAddress2() {
		return shipToAddress2;
	}


	public void setShipToAddress2(String shipToAddress2) {
		this.shipToAddress2 = shipToAddress2;
	}


	public String getShipToAddress3() {
		return shipToAddress3;
	}


	public void setShipToAddress3(String shipToAddress3) {
		this.shipToAddress3 = shipToAddress3;
	}


	public String getShipToCity() {
		return shipToCity;
	}


	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}


	public String getShipToState() {
		return shipToState;
	}


	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}


	public String getShipToZip() {
		return shipToZip;
	}


	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}


	public String getShipToCountry() {
		return shipToCountry;
	}


	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}


	public String getShipToTelephone() {
		return shipToTelephone;
	}


	public void setShipToTelephone(String shipToTelephone) {
		this.shipToTelephone = shipToTelephone;
	}


	public String getShipToFax() {
		return shipToFax;
	}


	public void setShipToFax(String shipToFax) {
		this.shipToFax = shipToFax;
	}


	public String getShipToEmail() {
		return shipToEmail;
	}


	public void setShipToEmail(String shipToEmail) {
		this.shipToEmail = shipToEmail;
	}


	public String getBillToCustomer() {
		return billToCustomer;
	}


	public void setBillToCustomer(String billToCustomer) {
		this.billToCustomer = billToCustomer;
	}


	public String getBillToContact() {
		return billToContact;
	}


	public void setBillToContact(String billToContact) {
		this.billToContact = billToContact;
	}


	public String getBillToAddress1() {
		return billToAddress1;
	}


	public void setBillToAddress1(String billToAddress1) {
		this.billToAddress1 = billToAddress1;
	}


	public String getBillToAddress2() {
		return billToAddress2;
	}


	public void setBillToAddress2(String billToAddress2) {
		this.billToAddress2 = billToAddress2;
	}


	public String getBillToAddress3() {
		return billToAddress3;
	}


	public void setBillToAddress3(String billToAddress3) {
		this.billToAddress3 = billToAddress3;
	}


	public String getBillToCity() {
		return billToCity;
	}


	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}


	public String getBillToState() {
		return billToState;
	}


	public void setBillToState(String billToState) {
		this.billToState = billToState;
	}


	public String getBillToZip() {
		return billToZip;
	}


	public void setBillToZip(String billToZip) {
		this.billToZip = billToZip;
	}


	public String getBillToCountry() {
		return billToCountry;
	}


	public void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
	}


	public String getBillToTelephone() {
		return billToTelephone;
	}


	public void setBillToTelephone(String billToTelephone) {
		this.billToTelephone = billToTelephone;
	}


	public String getBillToFax() {
		return billToFax;
	}


	public void setBillToFax(String billToFax) {
		this.billToFax = billToFax;
	}


	public String getBillToEmail() {
		return billToEmail;
	}


	public void setBillToEmail(String billToEmail) {
		this.billToEmail = billToEmail;
	}


	public Date getRequestedDevliveryDate() {
		return requestedDevliveryDate;
	}


	public void setRequestedDevliveryDate(Date requestedDevliveryDate) {
		this.requestedDevliveryDate = requestedDevliveryDate;
	}


	public String getShippingMethod() {
		return shippingMethod;
	}


	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}


	public String getSpecialInstruction() {
		return specialInstruction;
	}


	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}


	public Date getOrderReceivedDate() {
		return orderReceivedDate;
	}


	public void setOrderReceivedDate(Date orderReceivedDate) {
		this.orderReceivedDate = orderReceivedDate;
	}


	public String getSoldToRBONumber() {
		return soldToRBONumber;
	}


	public void setSoldToRBONumber(String soldToRBONumber) {
		this.soldToRBONumber = soldToRBONumber;
	}


	public String getOracleBillToSiteNumber() {
		return oracleBillToSiteNumber;
	}


	public void setOracleBillToSiteNumber(String oracleBillToSiteNumber) {
		this.oracleBillToSiteNumber = oracleBillToSiteNumber;
	}


	public String getOracleShipToSiteNumber() {
		return oracleShipToSiteNumber;
	}


	public void setOracleShipToSiteNumber(String oracleShipToSiteNumber) {
		this.oracleShipToSiteNumber = oracleShipToSiteNumber;
	}


	public String getRetailerPO_CustomerJob() {
		return retailerPO_CustomerJob;
	}


	public void setRetailerPO_CustomerJob(String retailerPO_CustomerJob) {
		this.retailerPO_CustomerJob = retailerPO_CustomerJob;
	}


	public String getAveryItemNumber() {
		return averyItemNumber;
	}


	public void setAveryItemNumber(String averyItemNumber) {
		this.averyItemNumber = averyItemNumber;
	}


	public String getCustomerItemNumber() {
		return customerItemNumber;
	}


	public void setCustomerItemNumber(String customerItemNumber) {
		this.customerItemNumber = customerItemNumber;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public String getCustomerColorCode() {
		return customerColorCode;
	}


	public void setCustomerColorCode(String customerColorCode) {
		this.customerColorCode = customerColorCode;
	}


	public String getCustomerColorDescription() {
		return customerColorDescription;
	}


	public void setCustomerColorDescription(String customerColorDescription) {
		this.customerColorDescription = customerColorDescription;
	}


	public String getCustomerSize() {
		return customerSize;
	}


	public void setCustomerSize(String customerSize) {
		this.customerSize = customerSize;
	}


	public String getCustomerUnitPrice() {
		return customerUnitPrice;
	}


	public void setCustomerUnitPrice(String customerUnitPrice) {
		this.customerUnitPrice = customerUnitPrice;
	}


	public String getCustomerCost() {
		return customerCost;
	}


	public void setCustomerCost(String customerCost) {
		this.customerCost = customerCost;
	}


	public String getContractNumber() {
		return contractNumber;
	}


	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}


	public String getStyleNo() {
		return styleNo;
	}


	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}


	public String getCustomerItemNumber1() {
		return customerItemNumber1;
	}


	public void setCustomerItemNumber1(String customerItemNumber1) {
		this.customerItemNumber1 = customerItemNumber1;
	}


	public String getCustomerItemNumber2() {
		return customerItemNumber2;
	}


	public void setCustomerItemNumber2(String customerItemNumber2) {
		this.customerItemNumber2 = customerItemNumber2;
	}


	public String getCustomerSeason() {
		return customerSeason;
	}


	public void setCustomerSeason(String customerSeason) {
		this.customerSeason = customerSeason;
	}


	public String getCustomerUOM() {
		return customerUOM;
	}


	public void setCustomerUOM(String customerUOM) {
		this.customerUOM = customerUOM;
	}


	public String getCustomerOrderedQty() {
		return customerOrderedQty;
	}


	public void setCustomerOrderedQty(String customerOrderedQty) {
		this.customerOrderedQty = customerOrderedQty;
	}


	public String getCalculatedOrderdedQty() {
		return calculatedOrderdedQty;
	}


	public void setCalculatedOrderdedQty(String calculatedOrderdedQty) {
		this.calculatedOrderdedQty = calculatedOrderdedQty;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public Date getCustomerRequestDate() {
		return customerRequestDate;
	}


	public void setCustomerRequestDate(Date customerRequestDate) {
		this.customerRequestDate = customerRequestDate;
	}


	public Date getPromiseDate() {
		return promiseDate;
	}


	public void setPromiseDate(Date promiseDate) {
		this.promiseDate = promiseDate;
	}


	public String getFreightTerms() {
		return freightTerms;
	}


	public void setFreightTerms(String freightTerms) {
		this.freightTerms = freightTerms;
	}


	public String getCsr() {
		return csr;
	}


	public void setCsr(String csr) {
		this.csr = csr;
	}


	public String getPackingInstruction() {
		return packingInstruction;
	}


	public void setPackingInstruction(String packingInstruction) {
		this.packingInstruction = packingInstruction;
	}


	public String getShippingInstructions() {
		return shippingInstructions;
	}


	public void setShippingInstructions(String shippingInstructions) {
		this.shippingInstructions = shippingInstructions;
	}


	public String getInvoicelineInstruction() {
		return invoicelineInstruction;
	}


	public void setInvoicelineInstruction(String invoicelineInstruction) {
		this.invoicelineInstruction = invoicelineInstruction;
	}


	public String getDivisionForInterfaceERPORG() {
		return divisionForInterfaceERPORG;
	}


	public void setDivisionForInterfaceERPORG(String divisionForInterfaceERPORG) {
		this.divisionForInterfaceERPORG = divisionForInterfaceERPORG;
	}


	public String getArtWorkhold() {
		return artWorkhold;
	}


	public void setArtWorkhold(String artWorkhold) {
		this.artWorkhold = artWorkhold;
	}


	public String getArtworkAttachment() {
		return artworkAttachment;
	}


	public void setArtworkAttachment(String artworkAttachment) {
		this.artworkAttachment = artworkAttachment;
	}


	public String getVariableDataBreakdown() {
		return variableDataBreakdown;
	}


	public void setVariableDataBreakdown(String variableDataBreakdown) {
		this.variableDataBreakdown = variableDataBreakdown;
	}


	public String getManufacturingNotes() {
		return manufacturingNotes;
	}


	public void setManufacturingNotes(String manufacturingNotes) {
		this.manufacturingNotes = manufacturingNotes;
	}


	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public String getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	public String getEndCustomer() {
		return endCustomer;
	}


	public void setEndCustomer(String endCustomer) {
		this.endCustomer = endCustomer;
	}


	public String getShippingOnlyNotes() {
		return shippingOnlyNotes;
	}


	public void setShippingOnlyNotes(String shippingOnlyNotes) {
		this.shippingOnlyNotes = shippingOnlyNotes;
	}


	public String getBankCharge() {
		return bankCharge;
	}


	public void setBankCharge(String bankCharge) {
		this.bankCharge = bankCharge;
	}


	public String getFreightCharge() {
		return freightCharge;
	}


	public void setFreightCharge(String freightCharge) {
		this.freightCharge = freightCharge;
	}


	public String getShippingHold() {
		return shippingHold;
	}


	public void setShippingHold(String shippingHold) {
		this.shippingHold = shippingHold;
	}


	public String getProductionHold() {
		return productionHold;
	}


	public void setProductionHold(String productionHold) {
		this.productionHold = productionHold;
	}


	public String getSplitShipset() {
		return splitShipset;
	}


	public void setSplitShipset(String splitShipset) {
		this.splitShipset = splitShipset;
	}


	public String getAgreement() {
		return agreement;
	}


	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}


	public String getModelSerialNumber() {
		return modelSerialNumber;
	}


	public void setModelSerialNumber(String modelSerialNumber) {
		this.modelSerialNumber = modelSerialNumber;
	}


	public String getWaiveMOQ() {
		return waiveMOQ;
	}


	public void setWaiveMOQ(String waiveMOQ) {
		this.waiveMOQ = waiveMOQ;
	}


	public String getTargetSystem() {
		return targetSystem;
	}


	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}


	public String getAPOType() {
		return APOType;
	}


	public void setAPOType(String aPOType) {
		APOType = aPOType;
	}


	public Date getSentToOracleDate() {
		return sentToOracleDate;
	}


	public void setSentToOracleDate(Date sentToOracleDate) {
		this.sentToOracleDate = sentToOracleDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getReviseOrderFlag() {
		return reviseOrderFlag;
	}


	public void setReviseOrderFlag(String reviseOrderFlag) {
		this.reviseOrderFlag = reviseOrderFlag;
	}


	public String getCooTranslationFlag() {
		return cooTranslationFlag;
	}


	public void setCooTranslationFlag(String cooTranslationFlag) {
		this.cooTranslationFlag = cooTranslationFlag;
	}


	public String getFebricPercentageFlag() {
		return febricPercentageFlag;
	}


	public void setFebricPercentageFlag(String febricPercentageFlag) {
		this.febricPercentageFlag = febricPercentageFlag;
	}


	public String getDuplicatePOFlag() {
		return duplicatePOFlag;
	}


	public void setDuplicatePOFlag(String duplicatePOFlag) {
		this.duplicatePOFlag = duplicatePOFlag;
	}


	public String getCustomerPOFlag() {
		return customerPOFlag;
	}


	public void setCustomerPOFlag(String customerPOFlag) {
		this.customerPOFlag = customerPOFlag;
	}


	public String getBulkSampleValidationFlag() {
		return bulkSampleValidationFlag;
	}


	public void setBulkSampleValidationFlag(String bulkSampleValidationFlag) {
		this.bulkSampleValidationFlag = bulkSampleValidationFlag;
	}


	public String getMOQValidationFlag() {
		return MOQValidationFlag;
	}


	public void setMOQValidationFlag(String mOQValidationFlag) {
		MOQValidationFlag = mOQValidationFlag;
	}


	public String getATOValidationFlag() {
		return ATOValidationFlag;
	}


	public void setATOValidationFlag(String aTOValidationFlag) {
		ATOValidationFlag = aTOValidationFlag;
	}


	public String getMandatoryVariableDataFieldFlag() {
		return mandatoryVariableDataFieldFlag;
	}


	public void setMandatoryVariableDataFieldFlag(
			String mandatoryVariableDataFieldFlag) {
		this.mandatoryVariableDataFieldFlag = mandatoryVariableDataFieldFlag;
	}


	public String getHTLSizePageValidationFlag() {
		return HTLSizePageValidationFlag;
	}


	public void setHTLSizePageValidationFlag(String hTLSizePageValidationFlag) {
		HTLSizePageValidationFlag = hTLSizePageValidationFlag;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public String getLastModifiedBy() {
		return lastModifiedBy;
	}


	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getPONumber() {
		return PONumber;
	}


	public void setPONumber(String pONumber) {
		PONumber = pONumber;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getRoundQty() {
		return roundQty;
	}


	public void setRoundQty(String roundQty) {
		this.roundQty = roundQty;
	}


	public String getMOQDiffQty() {
		return MOQDiffQty;
	}


	public void setMOQDiffQty(String mOQDiffQty) {
		MOQDiffQty = mOQDiffQty;
	}


	public String getUpdateMOQ() {
		return updateMOQ;
	}


	public void setUpdateMOQ(String updateMOQ) {
		this.updateMOQ = updateMOQ;
	}


	public String getCustomerNumber() {
		return customerNumber;
	}


	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	public String getRushOrderCheck() {
		return rushOrderCheck;
	}


	public void setRushOrderCheck(String rushOrderCheck) {
		this.rushOrderCheck = rushOrderCheck;
	}


	public String getFOO() {
		return FOO;
	}


	public void setFOO(String fOO) {
		FOO = fOO;
	}


	public String getSample() {
		return sample;
	}


	public void setSample(String sample) {
		this.sample = sample;
	}


	public String getQtyUnit() {
		return qtyUnit;
	}


	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getPageSize() {
		return pageSize;
	}


	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	public String getFabricCode() {
		return fabricCode;
	}


	public void setFabricCode(String fabricCode) {
		this.fabricCode = fabricCode;
	}


	public String getCarrier() {
		return carrier;
	}


	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getShipVia() {
		return shipVia;
	}


	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}

	
	/*public String getCreatedByName() {
		return createdByName;
	}


	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}


	public String getLastModifiedByName() {
		return lastModifiedByName;
	}


	public void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}*/


	public String getProductionLine() {
		return productionLine;
	}


	public void setProductionLine(String productionLine) {
		this.productionLine = productionLine;
	}


	public String getProductLineType() {
		return productLineType;
	}


	public void setProductLineType(String productLineType) {
		this.productLineType = productLineType;
	}

	public String getAdditionalFileId() {
		return additionalFileId;
	}


	public void setAdditionalFileId(String additionalFileId) {
		this.additionalFileId = additionalFileId;
	}


	public OrderFileQueue getVarOrderFileQueue() {
		return varOrderFileQueue;
	}


	public void setVarOrderFileQueue(OrderFileQueue varOrderFileQueue) {
		this.varOrderFileQueue = varOrderFileQueue;
	}


	/*public List<OrderLineDetails> getListOrderlineDetails() {
		return listOrderlineDetails;
	}


	public void setListOrderlineDetails(List<OrderLineDetails> listOrderlineDetails) {
		this.listOrderlineDetails = listOrderlineDetails;
	}*/


	public String getAveryMOQ() {
		return averyMOQ;
	}


	public void setAveryMOQ(String averyMOQ) {
		this.averyMOQ = averyMOQ;
	}


	public String getAveryRoundupQty() {
		return averyRoundupQty;
	}


	public void setAveryRoundupQty(String averyRoundupQty) {
		this.averyRoundupQty = averyRoundupQty;
	}


	public String getAveryATO() {
		return averyATO;
	}


	public void setAveryATO(String averyATO) {
		this.averyATO = averyATO;
	}


	public String getAveryRegion() {
		return averyRegion;
	}


	public void setAveryRegion(String averyRegion) {
		this.averyRegion = averyRegion;
	}


	public String getAveryProductLineType() {
		return averyProductLineType;
	}


	public void setAveryProductLineType(String averyProductLineType) {
		this.averyProductLineType = averyProductLineType;
	}


	public String getAveryBulk() {
		return averyBulk;
	}


	public void setAveryBulk(String averyBulk) {
		this.averyBulk = averyBulk;
	}


	public String getShipMark() {
		return shipMark;
	}


	public void setShipMark(String shipMark) {
		this.shipMark = shipMark;
	}


	public String getAdditionalLabelInternalItem() {
		return additionalLabelInternalItem;
	}


	public void setAdditionalLabelInternalItem(String additionalLabelInternalItem) {
		this.additionalLabelInternalItem = additionalLabelInternalItem;
	}


	public String getOrderFileOrderType() {
		return orderFileOrderType;
	}


	public void setOrderFileOrderType(String orderFileOrderType) {
		this.orderFileOrderType = orderFileOrderType;
	}




	/*public List<SalesOrderLine> getListSalesOrderLine() {
		return listSalesOrderLine;
	}


	public void setListSalesOrderLine(List<SalesOrderLine> listSalesOrderLine) {
		this.listSalesOrderLine = listSalesOrderLine;
	}*/


	
	
	
}
