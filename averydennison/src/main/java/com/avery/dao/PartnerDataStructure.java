package com.avery.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partnerDataStructure")
public class PartnerDataStructure {

	@Id
	@GeneratedValue
	@Column(name = "id",nullable=false)
	int id;
	@Column(name = "partnerId",nullable=false)
	int partnerId;
	@Column(name = "rboId",nullable=false)
	int rboId;
	@Column(name = "rborName", length = 250)
	String rborName;
	@Column(name = "site", length = 25)
	String site;
	@Column(name = "orgCode", length = 25)
	String orgCode;
	@Column(name = "csrPrimaryEmail", length = 250)
	String csrPrimaryEmail;
	@Column(name = "csrSecondryGroupEmail", length = 250)
	String csrSecondryGroupEmail;
	@Column(name = "productLineType", length = 25)
	String productLineType;
	@Column(name = "defaultBillToCode")
	int defaultBillToCode;
	@Column(name = "defaultShipToCode")
	int defaultShipToCode;
	@Column(name = "invoiceLineInstruction", length = 500)
	String invoiceLineInstruction;
	@Column(name = "packingInstruction", length = 500)
	String packingInstruction;
	@Column(name = "variableDataBreakdown", length = 500)
	String variableDataBreakdown;
	@Column(name = "manufacturingNotes", length = 500)
	String manufacturingNotes;
	@Column(name = "shippingOnlyNotes", length = 500)
	String shippingOnlyNotes;
	@Column(name = "splitShipsetBy", length = 5)
	String splitShipsetBy;
	@Column(name = "artworkHold", length = 5)
	String artworkHold;
	@Column(name = "miscCsrInstruction", length=500)
	String miscCsrInstruction;
	@Column(name = "waiveMoa")
	boolean waiveMoa;
	@Column(name = "waiveMoq")
	boolean waiveMoq;
	@Column(name = "sizeCheck")
	boolean sizeCheck;
	@Column(name = "discountPriceCheck")
	boolean discountPriceCheck;
	@Column(name = "fabricCheck")
	boolean fabricCheck;
	@Column(name = "shipMark")
	boolean shipMark;
	@Column(name = "llkkCheck")
	boolean llkkCheck;
	@Column(name = "localBillingCheck")
	boolean localBillingCheck;
	@Column(name = "factoryTransferCheck")
	boolean factoryTransferCheck;
	@Column(name = "shipmentSample")
	boolean shipmentSample;
	@Column(name = "orderFilenamePattern", length = 25)
	String orderFilenamePattern;
	@Column(name = "orderFilenameExtension", length = 100)
	String orderFilenameExtension;
	@Column(name = "orderFileSchemaId", length = 50)
	String orderFileSchemaId;
	@Column(name = "orderFileMappingId", length = 50)
	String orderFileMappingId;
	@Column(name = "orderHasInternalItems")
	boolean orderHasInternalItems;
	@Column(name = "orderHasLocalItems")
	boolean orderHasLocalItems;
	@Column(name = "oracleItem")
	boolean oracleItem;
	@Column(name = "trimItem")
	boolean trimItem;
	@Column(name = "sparrowItem")
	boolean sparrowItem;
	@Column(name = "adRequired")
	boolean adRequired;
	@Column(name = "adFileNamePattern_1", length = 50)
	String adFileNamePattern_1;
	@Column(name = "adFileNameExtension_1", length = 50)
	String adFileNameExtension_1;
	@Column(name = "adFileSchemaID_1", length = 50)
	String adFileSchemaID_1;
	@Column(name = "adMappingID_1", length = 50)
	String adMappingID_1;
	@Column(name = "adFileNamePattern_2", length = 50)
	String adFileNamePattern_2;
	@Column(name = "adFileNameExtension_2", length = 50)
	String adFileNameExtension_2;
	@Column(name = "adFileSchemaID_2", length = 50)
	String adFileSchemaID_2;
	@Column(name = "adMappingID_2", length = 50)
	String adMappingID_2;
	@Column(name = "adFileNamePattern_3", length = 50)
	String adFileNamePattern_3;
	@Column(name = "adFileNameExtension_3", length = 50)
	String adFileNameExtension_3;
	@Column(name = "adFileSchemaID_3", length = 50)
	String adFileSchemaID_3;
	@Column(name = "adMappingID_3", length = 50)
	String adMappingID_3;
	@Column(name = "adFileNamePattern_4", length = 50)
	String adFileNamePattern_4;
	@Column(name = "adFileNameExtension_4", length = 50)
	String adFileNameExtension_4;
	@Column(name = "adFileSchemaID_4", length = 50)
	String adFileSchemaID_4;
	@Column(name = "adMappingID_4", length = 50)
	String adMappingID_4;
	@Column(name = "emailSubjectRBOMatch", length = 100)
	String emailSubjectRBOMatch;
	@Column(name = "emailSubjectRBOMatchLocation", length = 100)
	String emailSubjectRBOMatchLocation;
	@Column(name = "emailSubjectRBOMatchRequired")
	boolean emailSubjectRBOMatchRequired;
	@Column(name = "emailSubjectProductLineMatch", length = 100)
	String emailSubjectProductLineMatch;
	@Column(name = "emailSubjectProductLineMatchLocation", length = 100)
	String emailSubjectProductLineMatchLocation;
	@Column(name = "emailSubjectProductLineMatchRequired")
	boolean emailSubjectProductLineMatchRequired;
	@Column(name = "fileRBOMatch", length = 100)
	String fileRBOMatch;
	@Column(name = "fileMatchLocation", length = 100)
	String fileMatchLocation;
	@Column(name = "fileMatchRequired")
	boolean fileMatchRequired;
	@Column(name = "fileProductLineMatch", length = 100)
	String fileProductLineMatch;
	@Column(name = "fileProductLineMatchLocation", length = 100)
	String fileProductLineMatchLocation;
	@Column(name = "fileProductLineMatchRequired")
	boolean fileProductLineMatchRequired;
	@Column(name = "fileOrderMatch", length = 100)
	String fileOrderMatch;
	@Column(name = "fileOrderMatchLocation", length = 100)
	String fileOrderMatchLocation;
	@Column(name = "fileOrderMatchRequired")
	boolean fileOrderMatchRequired;
	@Column(name = "adFileRBOMatch", length = 100)
	String adFileRBOMatch;
	@Column(name = "fileAdMatchLocation", length = 100)
	String fileAdMatchLocation;
	@Column(name = "adFileMatchRequired")
	boolean adFileMatchRequired;
	@Column(name = "fileADProductLineMatch", length = 100)
	String fileADProductLineMatch;
	@Column(name = "adFileProductLineMatchLocation", length = 100)
	String adFileProductLineMatchLocation;
	@Column(name = "adFileProductLineMatchRequired")
	boolean adFileProductLineMatchRequired;
	@Column(name = "adFileOrderMatch", length = 100)
	String adFileOrderMatch;
	@Column(name = "adFileOrderMatchLocation", length = 100)
	String adFileOrderMatchLocation;
	@Column(name = "adFileOrderMatchRequired")
	boolean adFileOrderMatchRequired;
	@Column(name = "active")
	boolean active;
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "createdByName", length = 50)
	String createdByName;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@Column(name = "lastModifiedByName", length = 250)
	String lastModifiedByName;
	@Column(name = "comment", length = 500)
	String comment;
	@Column(name="controlData")
	boolean controlData;
	

	public PartnerDataStructure() {

	}

	public PartnerDataStructure(String rborName, String site) {
		this.rborName = rborName;
		this.site = site;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public int getRboId() {
		return rboId;
	}

	public void setRboId(int rboId) {
		this.rboId = rboId;
	}

	public String getRborName() {
		return rborName;
	}

	public void setRborName(String rborName) {
		this.rborName = rborName;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCsrPrimaryEmail() {
		return csrPrimaryEmail;
	}

	public void setCsrPrimaryEmail(String csrPrimaryEmail) {
		this.csrPrimaryEmail = csrPrimaryEmail;
	}

	public String getCsrSecondryGroupEmail() {
		return csrSecondryGroupEmail;
	}

	public void setCsrSecondryGroupEmail(String csrSecondryGroupEmail) {
		this.csrSecondryGroupEmail = csrSecondryGroupEmail;
	}

	public String getProductLineType() {
		return productLineType;
	}

	public void setProductLineType(String productLineType) {
		this.productLineType = productLineType;
	}

	public int getDefaultBillToCode() {
		return defaultBillToCode;
	}

	public void setDefaultBillToCode(int defaultBillToCode) {
		this.defaultBillToCode = defaultBillToCode;
	}

	public int getDefaultShipToCode() {
		return defaultShipToCode;
	}

	public void setDefaultShipToCode(int defaultShipToCode) {
		this.defaultShipToCode = defaultShipToCode;
	}

	public String getInvoiceLineInstruction() {
		return invoiceLineInstruction;
	}

	public void setInvoiceLineInstruction(String invoiceLineInstruction) {
		this.invoiceLineInstruction = invoiceLineInstruction;
	}

	public String getPackingInstruction() {
		return packingInstruction;
	}

	public void setPackingInstruction(String packingInstruction) {
		this.packingInstruction = packingInstruction;
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

	public String getShippingOnlyNotes() {
		return shippingOnlyNotes;
	}

	public void setShippingOnlyNotes(String shippingOnlyNotes) {
		this.shippingOnlyNotes = shippingOnlyNotes;
	}

	public String getSplitShipsetBy() {
		return splitShipsetBy;
	}

	public void setSplitShipsetBy(String splitShipsetBy) {
		this.splitShipsetBy = splitShipsetBy;
	}

	public String getArtworkHold() {
		return artworkHold;
	}

	public void setArtworkHold(String artworkHold) {
		this.artworkHold = artworkHold;
	}

	public String getMiscCsrInstruction() {
		return miscCsrInstruction;
	}

	public void setMiscCsrInstruction(String miscCsrInstruction) {
		this.miscCsrInstruction = miscCsrInstruction;
	}

	public boolean isWaiveMoa() {
		return waiveMoa;
	}

	public void setWaiveMoa(boolean waiveMoa) {
		this.waiveMoa = waiveMoa;
	}

	public boolean isWaiveMoq() {
		return waiveMoq;
	}

	public void setWaiveMoq(boolean waiveMoq) {
		this.waiveMoq = waiveMoq;
	}

	public boolean isSizeCheck() {
		return sizeCheck;
	}

	public void setSizeCheck(boolean sizeCheck) {
		this.sizeCheck = sizeCheck;
	}

	public boolean isDiscountPriceCheck() {
		return discountPriceCheck;
	}

	public void setDiscountPriceCheck(boolean discountPriceCheck) {
		this.discountPriceCheck = discountPriceCheck;
	}

	public boolean isFabricCheck() {
		return fabricCheck;
	}

	public void setFabricCheck(boolean fabricCheck) {
		this.fabricCheck = fabricCheck;
	}

	public boolean isShipMark() {
		return shipMark;
	}

	public void setShipMark(boolean shipMark) {
		this.shipMark = shipMark;
	}

	public boolean isLlkkCheck() {
		return llkkCheck;
	}

	public void setLlkkCheck(boolean llkkCheck) {
		this.llkkCheck = llkkCheck;
	}

	public boolean isLocalBillingCheck() {
		return localBillingCheck;
	}

	public void setLocalBillingCheck(boolean localBillingCheck) {
		this.localBillingCheck = localBillingCheck;
	}

	public boolean isFactoryTransferCheck() {
		return factoryTransferCheck;
	}

	public void setFactoryTransferCheck(boolean factoryTransferCheck) {
		this.factoryTransferCheck = factoryTransferCheck;
	}

	public boolean isShipmentSample() {
		return shipmentSample;
	}

	public void setShipmentSample(boolean shipmentSample) {
		this.shipmentSample = shipmentSample;
	}

	public String getOrderFilenamePattern() {
		return orderFilenamePattern;
	}

	public void setOrderFilenamePattern(String orderFilenamePattern) {
		this.orderFilenamePattern = orderFilenamePattern;
	}

	public String getOrderFilenameExtension() {
		return orderFilenameExtension;
	}

	public void setOrderFilenameExtension(String orderFilenameExtension) {
		this.orderFilenameExtension = orderFilenameExtension;
	}

	public String getOrderFileSchemaId() {
		return orderFileSchemaId;
	}

	public void setOrderFileSchemaId(String orderFileSchemaId) {
		this.orderFileSchemaId = orderFileSchemaId;
	}

	public String getOrderFileMappingId() {
		return orderFileMappingId;
	}

	public void setOrderFileMappingId(String orderFileMappingId) {
		this.orderFileMappingId = orderFileMappingId;
	}

	public boolean isOrderHasInternalItems() {
		return orderHasInternalItems;
	}

	public void setOrderHasInternalItems(boolean orderHasInternalItems) {
		this.orderHasInternalItems = orderHasInternalItems;
	}

	public boolean isOrderHasLocalItems() {
		return orderHasLocalItems;
	}

	public void setOrderHasLocalItems(boolean orderHasLocalItems) {
		this.orderHasLocalItems = orderHasLocalItems;
	}

	public boolean isOracleItem() {
		return oracleItem;
	}

	public void setOracleItem(boolean oracleItem) {
		this.oracleItem = oracleItem;
	}

	public boolean isTrimItem() {
		return trimItem;
	}

	public void setTrimItem(boolean trimItem) {
		this.trimItem = trimItem;
	}

	public boolean isSparrowItem() {
		return sparrowItem;
	}

	public void setSparrowItem(boolean sparrowItem) {
		this.sparrowItem = sparrowItem;
	}

	public boolean isAdRequired() {
		return adRequired;
	}

	public void setAdRequired(boolean adRequired) {
		this.adRequired = adRequired;
	}

	public String getAdFileNamePattern_1() {
		return adFileNamePattern_1;
	}

	public void setAdFileNamePattern_1(String adFileNamePattern_1) {
		this.adFileNamePattern_1 = adFileNamePattern_1;
	}

	public String getAdFileNameExtension_1() {
		return adFileNameExtension_1;
	}

	public void setAdFileNameExtension_1(String adFileNameExtension_1) {
		this.adFileNameExtension_1 = adFileNameExtension_1;
	}

	public String getAdFileSchemaID_1() {
		return adFileSchemaID_1;
	}

	public void setAdFileSchemaID_1(String adFileSchemaID_1) {
		this.adFileSchemaID_1 = adFileSchemaID_1;
	}

	public String getAdMappingID_1() {
		return adMappingID_1;
	}

	public void setAdMappingID_1(String adMappingID_1) {
		this.adMappingID_1 = adMappingID_1;
	}

	public String getAdFileNamePattern_2() {
		return adFileNamePattern_2;
	}

	public void setAdFileNamePattern_2(String adFileNamePattern_2) {
		this.adFileNamePattern_2 = adFileNamePattern_2;
	}

	public String getAdFileNameExtension_2() {
		return adFileNameExtension_2;
	}

	public void setAdFileNameExtension_2(String adFileNameExtension_2) {
		this.adFileNameExtension_2 = adFileNameExtension_2;
	}

	public String getAdFileSchemaID_2() {
		return adFileSchemaID_2;
	}

	public void setAdFileSchemaID_2(String adFileSchemaID_2) {
		this.adFileSchemaID_2 = adFileSchemaID_2;
	}

	public String getAdMappingID_2() {
		return adMappingID_2;
	}

	public void setAdMappingID_2(String adMappingID_2) {
		this.adMappingID_2 = adMappingID_2;
	}

	public String getAdFileNamePattern_3() {
		return adFileNamePattern_3;
	}

	public void setAdFileNamePattern_3(String adFileNamePattern_3) {
		this.adFileNamePattern_3 = adFileNamePattern_3;
	}

	public String getAdFileNameExtension_3() {
		return adFileNameExtension_3;
	}

	public void setAdFileNameExtension_3(String adFileNameExtension_3) {
		this.adFileNameExtension_3 = adFileNameExtension_3;
	}

	public String getAdFileSchemaID_3() {
		return adFileSchemaID_3;
	}

	public void setAdFileSchemaID_3(String adFileSchemaID_3) {
		this.adFileSchemaID_3 = adFileSchemaID_3;
	}

	public String getAdMappingID_3() {
		return adMappingID_3;
	}

	public void setAdMappingID_3(String adMappingID_3) {
		this.adMappingID_3 = adMappingID_3;
	}

	public String getAdFileNamePattern_4() {
		return adFileNamePattern_4;
	}

	public void setAdFileNamePattern_4(String adFileNamePattern_4) {
		this.adFileNamePattern_4 = adFileNamePattern_4;
	}

	public String getAdFileNameExtension_4() {
		return adFileNameExtension_4;
	}

	public void setAdFileNameExtension_4(String adFileNameExtension_4) {
		this.adFileNameExtension_4 = adFileNameExtension_4;
	}

	public String getAdFileSchemaID_4() {
		return adFileSchemaID_4;
	}

	public void setAdFileSchemaID_4(String adFileSchemaID_4) {
		this.adFileSchemaID_4 = adFileSchemaID_4;
	}

	public String getAdMappingID_4() {
		return adMappingID_4;
	}

	public void setAdMappingID_4(String adMappingID_4) {
		this.adMappingID_4 = adMappingID_4;
	}

	public String getEmailSubjectRBOMatch() {
		return emailSubjectRBOMatch;
	}

	public void setEmailSubjectRBOMatch(String emailSubjectRBOMatch) {
		this.emailSubjectRBOMatch = emailSubjectRBOMatch;
	}

	public String getEmailSubjectRBOMatchLocation() {
		return emailSubjectRBOMatchLocation;
	}

	public void setEmailSubjectRBOMatchLocation(String emailSubjectRBOMatchLocation) {
		this.emailSubjectRBOMatchLocation = emailSubjectRBOMatchLocation;
	}

	public boolean isEmailSubjectRBOMatchRequired() {
		return emailSubjectRBOMatchRequired;
	}

	public void setEmailSubjectRBOMatchRequired(boolean emailSubjectRBOMatchRequired) {
		this.emailSubjectRBOMatchRequired = emailSubjectRBOMatchRequired;
	}

	public String getEmailSubjectProductLineMatch() {
		return emailSubjectProductLineMatch;
	}

	public void setEmailSubjectProductLineMatch(String emailSubjectProductLineMatch) {
		this.emailSubjectProductLineMatch = emailSubjectProductLineMatch;
	}

	public String getEmailSubjectProductLineMatchLocation() {
		return emailSubjectProductLineMatchLocation;
	}

	public void setEmailSubjectProductLineMatchLocation(
			String emailSubjectProductLineMatchLocation) {
		this.emailSubjectProductLineMatchLocation = emailSubjectProductLineMatchLocation;
	}

	public boolean isEmailSubjectProductLineMatchRequired() {
		return emailSubjectProductLineMatchRequired;
	}

	public void setEmailSubjectProductLineMatchRequired(
			boolean emailSubjectProductLineMatchRequired) {
		this.emailSubjectProductLineMatchRequired = emailSubjectProductLineMatchRequired;
	}

	public String getFileRBOMatch() {
		return fileRBOMatch;
	}

	public void setFileRBOMatch(String fileRBOMatch) {
		this.fileRBOMatch = fileRBOMatch;
	}

	public String getFileMatchLocation() {
		return fileMatchLocation;
	}

	public void setFileMatchLocation(String fileMatchLocation) {
		this.fileMatchLocation = fileMatchLocation;
	}

	public boolean isFileMatchRequired() {
		return fileMatchRequired;
	}

	public void setFileMatchRequired(boolean fileMatchRequired) {
		this.fileMatchRequired = fileMatchRequired;
	}

	public String getFileProductLineMatch() {
		return fileProductLineMatch;
	}

	public void setFileProductLineMatch(String fileProductLineMatch) {
		this.fileProductLineMatch = fileProductLineMatch;
	}

	public String getFileProductLineMatchLocation() {
		return fileProductLineMatchLocation;
	}

	public void setFileProductLineMatchLocation(String fileProductLineMatchLocation) {
		this.fileProductLineMatchLocation = fileProductLineMatchLocation;
	}

	public boolean isFileProductLineMatchRequired() {
		return fileProductLineMatchRequired;
	}

	public void setFileProductLineMatchRequired(boolean fileProductLineMatchRequired) {
		this.fileProductLineMatchRequired = fileProductLineMatchRequired;
	}

	public String getFileOrderMatch() {
		return fileOrderMatch;
	}

	public void setFileOrderMatch(String fileOrderMatch) {
		this.fileOrderMatch = fileOrderMatch;
	}

	public String getFileOrderMatchLocation() {
		return fileOrderMatchLocation;
	}

	public void setFileOrderMatchLocation(String fileOrderMatchLocation) {
		this.fileOrderMatchLocation = fileOrderMatchLocation;
	}

	public boolean isFileOrderMatchRequired() {
		return fileOrderMatchRequired;
	}

	public void setFileOrderMatchRequired(boolean fileOrderMatchRequired) {
		this.fileOrderMatchRequired = fileOrderMatchRequired;
	}

	public String getAdFileRBOMatch() {
		return adFileRBOMatch;
	}

	public void setAdFileRBOMatch(String adFileRBOMatch) {
		this.adFileRBOMatch = adFileRBOMatch;
	}

	public String getFileAdMatchLocation() {
		return fileAdMatchLocation;
	}

	public void setFileAdMatchLocation(String fileAdMatchLocation) {
		this.fileAdMatchLocation = fileAdMatchLocation;
	}

	public boolean isAdFileMatchRequired() {
		return adFileMatchRequired;
	}

	public void setAdFileMatchRequired(boolean adFileMatchRequired) {
		this.adFileMatchRequired = adFileMatchRequired;
	}

	public String getFileADProductLineMatch() {
		return fileADProductLineMatch;
	}

	public void setFileADProductLineMatch(String fileADProductLineMatch) {
		this.fileADProductLineMatch = fileADProductLineMatch;
	}

	public String getAdFileProductLineMatchLocation() {
		return adFileProductLineMatchLocation;
	}

	public void setAdFileProductLineMatchLocation(
			String adFileProductLineMatchLocation) {
		this.adFileProductLineMatchLocation = adFileProductLineMatchLocation;
	}

	public boolean isAdFileProductLineMatchRequired() {
		return adFileProductLineMatchRequired;
	}

	public void setAdFileProductLineMatchRequired(
			boolean adFileProductLineMatchRequired) {
		this.adFileProductLineMatchRequired = adFileProductLineMatchRequired;
	}

	public String getAdFileOrderMatch() {
		return adFileOrderMatch;
	}

	public void setAdFileOrderMatch(String adFileOrderMatch) {
		this.adFileOrderMatch = adFileOrderMatch;
	}

	public String getAdFileOrderMatchLocation() {
		return adFileOrderMatchLocation;
	}

	public void setAdFileOrderMatchLocation(String adFileOrderMatchLocation) {
		this.adFileOrderMatchLocation = adFileOrderMatchLocation;
	}

	public boolean isAdFileOrderMatchRequired() {
		return adFileOrderMatchRequired;
	}

	public void setAdFileOrderMatchRequired(boolean adFileOrderMatchRequired) {
		this.adFileOrderMatchRequired = adFileOrderMatchRequired;
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

	public String getLastModifiedByName() {
		return lastModifiedByName;
	}

	public void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean isControlData() {
		return controlData;
	}

	public void setControlData(boolean controlData) {
		this.controlData = controlData;
	}
	

}
