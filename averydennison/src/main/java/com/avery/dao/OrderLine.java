package com.avery.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderLine")
public class OrderLine {
	 
	 int id;
	 String orderFileAttchmentID;
	 String orderQueueID;
	 String partnerID;
	 String rboID;
	 String productLineType;
	 String customerPONumber;
	 Date orderedDate;
	 String partnerCustomerName;
	 String bulk;
	 String partnerVendorName;
	 String shipToCustomer;
	 String shipToContact;
	 String shipToAddress1;
	 String shipToAddress2;
	 String shipToAddress3;
	 String shipToCity;
	 String shipToState;
	 String shipToZip;
	 String shipToCountry;
	 String shipToTelephone;
	 String shipToFax;
	 String shipToEmail;
	 String billToCustomer;
	 String billToContact;
	 String billToAddress1;
	 String billToAddress2;
	 String billToAddress3;
	 String billToCity;
	 String billToState;
	 String billToZip;
	 String billToCountry;
	 String billToTelephone;
	 String billToFax;
	 String billToEmail;
	 Date requestedDevliveryDate;
	 String shippingMethod;
	 String specialInstruction;
	 Date orderReceivedDate;
	 String soldToRBONumber;
	 String oracleBilltoSiteNumber;
	 String oracleShiptoSiteNumber;
	 String retailerPOCustomerJob;
	 String averyItemNumber;
	 String oracleItemNumber;
	 String customerItemNumber;
	 String itemDescription;
	 String customerColorCode;
	 String customerColorDescription;
	 String customerSize;
	 String customerUnitPrice;
	 String customerCost;
	 String contractNumber;
	 String styleNo;
	 String customerItemNumber1;
	 String customerItemNumber2;
	 String customerSeason;
	 String customerUOM;
	 String customerOrderedQty;
	 String calculatedOrderdedQty;
	 String orderDate;
	 String customerRequestDate;
	 String promiseDate;
	 String freightTerms;
	 String csr;
	 String packingInstruction;
	 String shippingInstructions;
	 String invoicelineInstruction;
	 String divisionForInterfaceERPORG;
	 String artWorkhold;
	 String artworkAttachment;
	/* String variableDataBreakdown;
	 String manufacturingNotes;
	 String orderType;
	 String orderBy;
	 String endCustomer;
	 String shippingOnlyNotes;
	 String bankCharge;
	 String freightCharge;
	 String shippingHold;
	 String productionHold;
	 String splitShipset;
	 String agreement;
	 String modelSerialNumber;
	 String waiveMOQ;
	 String APOType;
	 Date sentToOracleDate;
	 String status;
	 String duplicatePOFlag;
	 String customerPOFlag;
	 String bulkSampleValidationFlag;
	 String MOQValidationFlag;
	 String ATOValidationFlag;
	 String mandatoryVariableDataFieldFlag;
	 String HTLSizePageValidationFlag;
	 Date createdDate;
	 String createdBy;
	 Date lastModifiedDate;
	 String lastModifiedBy;
	 String region;
	 String PONumber;
	 String comment;
	 String roundQty;
	 String MOQDiffQty;
	 String updateMOQ;
	 String customerNumber;
	 String rushOrderCheck;
	 String FOB;
	 String sample;
	// Division For Interface ERPORG
	 String qtyUnit;
	 String remark;
	 String pageSize;
	 String fabricCode;
	 String carrier;
	 String account;
	 String shipVia;
	 String createdByName;
	 String lastModifiedByName;*/
	 
	 
	 
	 
	public OrderLine(String partnerID, String rboID) {
		
		this.partnerID = partnerID;
		this.rboID = rboID;
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
	public String getOrderFileAttchmentID() {
		return orderFileAttchmentID;
	}
	public void setOrderFileAttchmentID(String orderFileAttchmentID) {
		this.orderFileAttchmentID = orderFileAttchmentID;
	}
	public String getOrderQueueID() {
		return orderQueueID;
	}
	public void setOrderQueueID(String orderQueueID) {
		this.orderQueueID = orderQueueID;
	}
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	public String getRboID() {
		return rboID;
	}
	public void setRboID(String rboID) {
		this.rboID = rboID;
	}
	public String getProductLineType() {
		return productLineType;
	}
	public void setProductLineType(String productLineType) {
		this.productLineType = productLineType;
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
	public String getBulk() {
		return bulk;
	}
	public void setBulk(String bulk) {
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
	public String getOracleBilltoSiteNumber() {
		return oracleBilltoSiteNumber;
	}
	public void setOracleBilltoSiteNumber(String oracleBilltoSiteNumber) {
		this.oracleBilltoSiteNumber = oracleBilltoSiteNumber;
	}
	public String getOracleShiptoSiteNumber() {
		return oracleShiptoSiteNumber;
	}
	public void setOracleShiptoSiteNumber(String oracleShiptoSiteNumber) {
		this.oracleShiptoSiteNumber = oracleShiptoSiteNumber;
	}
	public String getRetailerPOCustomerJob() {
		return retailerPOCustomerJob;
	}
	public void setRetailerPOCustomerJob(String retailerPOCustomerJob) {
		this.retailerPOCustomerJob = retailerPOCustomerJob;
	}
	public String getAveryItemNumber() {
		return averyItemNumber;
	}
	public void setAveryItemNumber(String averyItemNumber) {
		this.averyItemNumber = averyItemNumber;
	}
	public String getOracleItemNumber() {
		return oracleItemNumber;
	}
	public void setOracleItemNumber(String oracleItemNumber) {
		this.oracleItemNumber = oracleItemNumber;
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
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustomerRequestDate() {
		return customerRequestDate;
	}
	public void setCustomerRequestDate(String customerRequestDate) {
		this.customerRequestDate = customerRequestDate;
	}
	public String getPromiseDate() {
		return promiseDate;
	}
	public void setPromiseDate(String promiseDate) {
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
	/*public String getVariableDataBreakdown() {
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
	public String getFOB() {
		return FOB;
	}
	public void setFOB(String fOB) {
		FOB = fOB;
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
	public String getCreatedByName() {
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
	 
	 
	 
}
