package com.avery.dao;

import java.sql.Date;
import java.util.ArrayList;
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
@Table(name = "salesorderline")
public class SalesOrderLine {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	int id;
	@Column(name = "division", length = 100)
	String division;
	@Column(name = "orderSource", length = 100)
	String orderSource;
	@Column(name = "systemUniqueId", length = 100)
	String systemUniqueId;
	@Column(name = "systemUniqueIdLineNo", length = 100)
	String systemUniqueIdLineNo;
	@Column(name = "soldToRboNumber", length = 100)
	String soldToRboNumber;
	@Column(name = "shippingMethod", length = 100)
	String shippingMethod;
	@Column(name = "customerPoNumber", length = 100)
	String customerPoNumber;
	@Column(name = "retailerPo_CustomerJob", length = 100)
	String retailerPo_CustomerJob;
	@Column(name = "itemDescription", length = 100)
	String itemDescription;
	@Column(name = "orderdedQty", length = 100)
	String orderdedQty;
	@Column(name = "dateOrdered")
	Date dateOrdered;
	@Column(name = "customerRequestDate")
	Date customerRequestDate;
	@Column(name = "promiseDate")
	Date promiseDate;
	@Column(name = "freightTerms", length = 100)
	String freightTerms;
	@Column(name = "csr", length = 100)
	String csr;
	@Column(name = "packingInstruction", length = 100)
	String packingInstruction;
	@Column(name = "shippingInstructions", length = 100)
	String shippingInstructions;
	@Column(name = "invoiceLineInstruction", length = 100)
	String invoiceLineInstruction;
	@Column(name = "divisionForInterfaceErporg", length = 10)
	String divisionForInterfaceErporg;
	@Column(name = "billToContact", length = 100)
	String billToContact;
	@Column(name = "billToTel", length = 50)
	String billToTel;
	@Column(name = "billToFax", length = 50)
	String billToFax;
	@Column(name = "billToEmail", length = 50)
	String billToEmail;
	@Column(name = "shipToContact", length = 50)
	String shipToContact;
	@Column(name = "shipToTel", length = 50)
	String shipToTel;
	@Column(name = "shipToFax", length = 50)
	String shipToFax;
	@Column(name = "shipToEmail", length = 50)
	String shipToEmail;
	@Column(name = "artworkHold", length = 100)
	String artworkHold;
	@Column(name = "artworkAttachment", length = 5)
	String artworkAttachment;
	@Column(name = "variableDataBreakdown", length = 100)
	String variableDataBreakdown;
	@Column(name = "manufacturingNotes", length = 100)
	String manufacturingNotes;
	@Column(name = "orderType", length = 100)
	String orderType;
	@Column(name = "orderBy", length = 100)
	String orderBy;
	@Column(name = "endCustomer", length = 100)
	String endCustomer;
	@Column(name = "shippingOnlyNotes", length = 100)
	String shippingOnlyNotes;
	@Column(name = "bankCharge", length = 100)
	String bankCharge;
	@Column(name = "freightCharge", length = 100)
	String freightCharge;
	@Column(name = "shippingHold", length = 100)
	String shippingHold;
	@Column(name = "productionHold", length = 100)
	String productionHold;
	@Column(name = "splitShipSet", length = 100)
	String splitShipSet;
	@Column(name = "agreement", length = 100)
	String agreement;
	@Column(name = "modelSerialNumber", length = 100)
	String modelSerialNumber;
	@Column(name = "waiveMOQ", length = 100)
	String waiveMOQ;
	@Column(name = "apoType", length = 100)
	String apoType;
	@Column(name = "atoValidationFlag",length=50)
	String atoValidationFlag;
	@Column(name = "bulkSampleValidationFlag",length=50)
	String bulkSampleValidationFlag;// 250
	@Column(name = "comment", length = 250)
	String comment;// 250
	@Column(name = "contractNumber", length = 50)
	String contractNumber;// 50
	@Column(name = "createdBy", length = 50)
	String createdBy;// 50
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "customerColorCode", length = 200)
	String customerColorCode;// 200
	@Column(name = "customerColorDescription", length = 50)
	String customerColorDescription;
	@Column(name = "customerCost", length = 50)
	String customerCost;
	@Column(name = "customerItemNumber", length = 50)
	String customerItemNumber;
	@Column(name = "customerItemNumber1", length = 50)
	String customerItemNumber1;
	@Column(name = "customerItemNumber2", length = 50)
	String customerItemNumber2;
	@Column(name = "customerOrderedQty", length = 50)
	String customerOrderedQty;
	@Column(name = "customerPoFlag", length = 50)
	String customerPoFlag;
	@Column(name = "customerSeason", length = 50)
	String customerSeason;
	@Column(name = "customerSize", length = 50)
	String customerSize;
	@Column(name = "customerUnitPrice", length = 50)
	String customerUnitPrice;
	@Column(name = "customerUom", length = 50)
	String customerUom;
	@Column(name = "duplicatePoFlag",length=50)
	String duplicatePOFlag;// 250
	@Column(name = "grpedOlid", length = 1000)
	String grpedOLID;// 1000
	@Column(name = "htlSizePageValidationFlag",length=50)
	String htlSizePageValidationFlag;// 250
	@Column(name = "lastModifiedBy", length = 50)
	String lastModifiedBy;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@Column(name = "mandatoryVariableDataFieldFlag", length = 50)
	String mandatoryVariableDataFieldFlag;
	@Column(name = "moqValidationFlag", length = 50)
	String moqValidationFlag;
	@Column(name = "oracleBillToSiteNumber", length = 100)
	String oracleBillToSiteNumber;// 100
	@Column(name = "oracleExportId")
	int oracleExportId;
	@Column(name = "oracleItemNumber", length = 100)
	String oracleItemNumber;// 100
	@Column(name = "oracleShipToSiteNumber", length = 100)
	String oracleShipToSiteNumber;// 100
	@Column(name = "sentToOracleDate")
	Date sentToOracleDate;
	@Column(name = "system_Status",length=100)
	String system_Status;
	@Column(name = "status",length=100)
	String status;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "orderLineId")
	OrderLine varOrderLine;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "orderQueueId")
	OrderFileQueue varOrderFileQueue;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "partnerId")
	Partner varPartner;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "rboId")
	RBO varRbo;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "varSalesOrderLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<SalesOrderDetails> varSalesOrderDetails=new ArrayList<SalesOrderDetails>();

	
	public SalesOrderLine() {}


	public SalesOrderLine(String division, String orderSource) {
		division = division;
		orderSource = orderSource;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
	}


	public String getOrderSource() {
		return orderSource;
	}


	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}


	public String getSystemUniqueId() {
		return systemUniqueId;
	}


	public void setSystemUniqueId(String systemUniqueId) {
		this.systemUniqueId = systemUniqueId;
	}


	public String getSystemUniqueIdLineNo() {
		return systemUniqueIdLineNo;
	}


	public void setSystemUniqueIdLineNo(String systemUniqueIdLineNo) {
		this.systemUniqueIdLineNo = systemUniqueIdLineNo;
	}


	public String getSoldToRboNumber() {
		return soldToRboNumber;
	}


	public void setSoldToRboNumber(String soldToRboNumber) {
		this.soldToRboNumber = soldToRboNumber;
	}


	public String getShippingMethod() {
		return shippingMethod;
	}


	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}


	public String getCustomerPoNumber() {
		return customerPoNumber;
	}


	public void setCustomerPoNumber(String customerPoNumber) {
		this.customerPoNumber = customerPoNumber;
	}


	public String getRetailerPo_CustomerJob() {
		return retailerPo_CustomerJob;
	}


	public void setRetailerPo_CustomerJob(String retailerPo_CustomerJob) {
		this.retailerPo_CustomerJob = retailerPo_CustomerJob;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public String getOrderdedQty() {
		return orderdedQty;
	}


	public void setOrderdedQty(String orderdedQty) {
		this.orderdedQty = orderdedQty;
	}


	public Date getDateOrdered() {
		return dateOrdered;
	}


	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
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


	public String getInvoiceLineInstruction() {
		return invoiceLineInstruction;
	}


	public void setInvoiceLineInstruction(String invoiceLineInstruction) {
		this.invoiceLineInstruction = invoiceLineInstruction;
	}


	public String getDivisionForInterfaceErporg() {
		return divisionForInterfaceErporg;
	}


	public void setDivisionForInterfaceErporg(String divisionForInterfaceErporg) {
		this.divisionForInterfaceErporg = divisionForInterfaceErporg;
	}


	public String getBillToContact() {
		return billToContact;
	}


	public void setBillToContact(String billToContact) {
		this.billToContact = billToContact;
	}


	public String getBillToTel() {
		return billToTel;
	}


	public void setBillToTel(String billToTel) {
		this.billToTel = billToTel;
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


	public String getShipToContact() {
		return shipToContact;
	}


	public void setShipToContact(String shipToContact) {
		this.shipToContact = shipToContact;
	}


	public String getShipToTel() {
		return shipToTel;
	}


	public void setShipToTel(String shipToTel) {
		this.shipToTel = shipToTel;
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


	public String getArtworkHold() {
		return artworkHold;
	}


	public void setArtworkHold(String artworkHold) {
		this.artworkHold = artworkHold;
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


	public String getSplitShipSet() {
		return splitShipSet;
	}


	public void setSplitShipSet(String splitShipSet) {
		this.splitShipSet = splitShipSet;
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


	public String getApoType() {
		return apoType;
	}


	public void setApoType(String apoType) {
		this.apoType = apoType;
	}


	public String getAtoValidationFlag() {
		return atoValidationFlag;
	}


	public void setAtoValidationFlag(String atoValidationFlag) {
		this.atoValidationFlag = atoValidationFlag;
	}


	public String getBulkSampleValidationFlag() {
		return bulkSampleValidationFlag;
	}


	public void setBulkSampleValidationFlag(String bulkSampleValidationFlag) {
		this.bulkSampleValidationFlag = bulkSampleValidationFlag;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getContractNumber() {
		return contractNumber;
	}


	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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


	public String getCustomerCost() {
		return customerCost;
	}


	public void setCustomerCost(String customerCost) {
		this.customerCost = customerCost;
	}


	public String getCustomerItemNumber() {
		return customerItemNumber;
	}


	public void setCustomerItemNumber(String customerItemNumber) {
		this.customerItemNumber = customerItemNumber;
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


	public String getCustomerOrderedQty() {
		return customerOrderedQty;
	}


	public void setCustomerOrderedQty(String customerOrderedQty) {
		this.customerOrderedQty = customerOrderedQty;
	}


	public String getCustomerPoFlag() {
		return customerPoFlag;
	}


	public void setCustomerPoFlag(String customerPoFlag) {
		this.customerPoFlag = customerPoFlag;
	}


	public String getCustomerSeason() {
		return customerSeason;
	}


	public void setCustomerSeason(String customerSeason) {
		this.customerSeason = customerSeason;
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


	public String getCustomerUom() {
		return customerUom;
	}


	public void setCustomerUom(String customerUom) {
		this.customerUom = customerUom;
	}


	public String getDuplicatePOFlag() {
		return duplicatePOFlag;
	}


	public void setDuplicatePOFlag(String duplicatePOFlag) {
		this.duplicatePOFlag = duplicatePOFlag;
	}


	public String getGrpedOLID() {
		return grpedOLID;
	}


	public void setGrpedOLID(String grpedOLID) {
		this.grpedOLID = grpedOLID;
	}

	public String getHtlSizePageValidationFlag() {
		return htlSizePageValidationFlag;
	}


	public void setHtlSizePageValidationFlag(String htlSizePageValidationFlag) {
		this.htlSizePageValidationFlag = htlSizePageValidationFlag;
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


	public  void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getMandatoryVariableDataFieldFlag() {
		return mandatoryVariableDataFieldFlag;
	}


	public void setMandatoryVariableDataFieldFlag(
			String mandatoryVariableDataFieldFlag) {
		this.mandatoryVariableDataFieldFlag = mandatoryVariableDataFieldFlag;
	}


	public String getMoqValidationFlag() {
		return moqValidationFlag;
	}


	public void setMoqValidationFlag(String moqValidationFlag) {
		this.moqValidationFlag = moqValidationFlag;
	}


	public String getOracleBillToSiteNumber() {
		return oracleBillToSiteNumber;
	}


	public void setOracleBillToSiteNumber(String oracleBillToSiteNumber) {
		this.oracleBillToSiteNumber = oracleBillToSiteNumber;
	}


	public int getOracleExportId() {
		return oracleExportId;
	}


	public void setOracleExportId(int oracleExportId) {
		this.oracleExportId = oracleExportId;
	}


	public String getOracleItemNumber() {
		return oracleItemNumber;
	}


	public void setOracleItemNumber(String oracleItemNumber) {
		this.oracleItemNumber = oracleItemNumber;
	}


	public String getOracleShipToSiteNumber() {
		return oracleShipToSiteNumber;
	}


	public void setOracleShipToSiteNumber(String oracleShipToSiteNumber) {
		this.oracleShipToSiteNumber = oracleShipToSiteNumber;
	}


	public Date getSentToOracleDate() {
		return sentToOracleDate;
	}


	public void setSentToOracleDate(Date sentToOracleDate) {
		this.sentToOracleDate = sentToOracleDate;
	}

	public String getSystem_Status() {
		return system_Status;
	}


	public void setSystem_Status(String system_Status) {
		this.system_Status = system_Status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public OrderLine getVarOrderLine() {
		return varOrderLine;
	}


	public void setVarOrderLine(OrderLine varOrderLine) {
		this.varOrderLine = varOrderLine;
	}


	public OrderFileQueue getVarOrderFileQueue() {
		return varOrderFileQueue;
	}


	public void setVarOrderFileQueue(OrderFileQueue varOrderFileQueue) {
		this.varOrderFileQueue = varOrderFileQueue;
	}


	public Partner getVarPartner() {
		return varPartner;
	}


	public void setVarPartner(Partner varPartner) {
		this.varPartner = varPartner;
	}


	public RBO getVarRbo() {
		return varRbo;
	}


	public void setVarRbo(RBO varRbo) {
		this.varRbo = varRbo;
	}


	public List<SalesOrderDetails> getVarSalesOrderDetails() {
		return varSalesOrderDetails;
	}


	public void setVarSalesOrderDetails(List<SalesOrderDetails> varSalesOrderDetails) {
		this.varSalesOrderDetails = varSalesOrderDetails;
	}



}
