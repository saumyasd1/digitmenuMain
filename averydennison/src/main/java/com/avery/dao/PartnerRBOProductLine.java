package com.avery.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partnerRBOProductLine")
public class PartnerRBOProductLine {

	int id;
	int partnerTableID;
	String partnerID;
    String RBOID;
    String RBOName;
    String productLineType;
    String CSRName;
    String CSREmail;
    String orderEmailDomain;
    String shippingInstructions;
    String packingInstruction;
    String invoiceLineInstruction;
    String variableDataBreakdown;
    String manufacturingNotes;
    String shippingOnlyNotes;
    String splitShipSetBy;
    String orderSchemaID;
    String orderSchemaType;
    String orderMappingID;
    String attachmentRequired;
    String attachmentIdentifier_1;
    String attachmentSchemaType_1;
    String attachmentMappingID_1;
    String attachmentIdentifier_2;
    String attachmentSchemaID_2;
    String attachmentSchemaType_2;
    String attachmentMappingID_2;
    String attachmentIdentifier_3;
    String attachmentSchemaID_3;
    String attachmentSchemaType_3;
    String attachmentMappingID_3;
    String preProcessPID;
    Date createdDate;
    String createdBy;
    Date lastModifiedDate;
    String lastModifiedBy;
    
    public PartnerRBOProductLine() {
      	 
    }
    public PartnerRBOProductLine(String partnerID, String RBOID) {
        this.partnerID = partnerID;
        this.RBOID = RBOID;
        
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
	@Column(name="partnerTableID")
	public int getPartnerTableID() {
		return partnerTableID;
	}
	public void setPartnerTableID(int partnerTableID) {
		this.partnerTableID = partnerTableID;
	}
	@Column(name="partnerID" , length=100)
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	@Column(name="RBOID" , length=100)
	public String getRBOID() {
		return RBOID;
	}
	public void setRBOID(String rBOID) {
		RBOID = rBOID;
	}
	@Column(name="RBOName" , length=100)
	public String getRBOName() {
		return RBOName;
	}
	public void setRBOName(String rBOName) {
		RBOName = rBOName;
	}
	@Column(name="productLineType" , length=100)
	public String getProductLineType() {
		return productLineType;
	}
	public void setProductLineType(String productLineType) {
		this.productLineType = productLineType;
	}
	@Column(name="CSRName" , length=100)
	public String getCSRName() {
		return CSRName;
	}
	public void setCSRName(String cSRName) {
		CSRName = cSRName;
	}
	@Column(name="CSREmail" , length=100)
	public String getCSREmail() {
		return CSREmail;
	}
	public void setCSREmail(String cSREmail) {
		CSREmail = cSREmail;
	}
	@Column(name="orderEmailDomain" , length=100)
	public String getOrderEmailDomain() {
		return orderEmailDomain;
	}
	public void setOrderEmailDomain(String orderEmailDomain) {
		this.orderEmailDomain = orderEmailDomain;
	}
	@Column(name="shippingInstructions" , length=500)
	public String getShippingInstructions() {
		return shippingInstructions;
	}
	public void setShippingInstructions(String shippingInstructions) {
		this.shippingInstructions = shippingInstructions;
	}
	@Column(name="packingInstruction" , length=500)
	public String getPackingInstruction() {
		return packingInstruction;
	}
	public void setPackingInstruction(String packingInstruction) {
		this.packingInstruction = packingInstruction;
	}
	@Column(name="invoiceLineInstruction" , length=100)
	public String getInvoiceLineInstruction() {
		return invoiceLineInstruction;
	}
	public void setInvoiceLineInstruction(String invoiceLineInstruction) {
		this.invoiceLineInstruction = invoiceLineInstruction;
	}
	@Column(name="variableDataBreakdown" , length=100)
	public String getVariableDataBreakdown() {
		return variableDataBreakdown;
	}
	public void setVariableDataBreakdown(String variableDataBreakdown) {
		this.variableDataBreakdown = variableDataBreakdown;
	}
	@Column(name="manufacturingNotes" , length=250)
	public String getManufacturingNotes() {
		return manufacturingNotes;
	}
	public void setManufacturingNotes(String manufacturingNotes) {
		this.manufacturingNotes = manufacturingNotes;
	}
	@Column(name="shippingOnlyNotes" , length=250)
	public String getShippingOnlyNotes() {
		return shippingOnlyNotes;
	}
	public void setShippingOnlyNotes(String shippingOnlyNotes) {
		this.shippingOnlyNotes = shippingOnlyNotes;
	}
	@Column(name="splitShipSetBy" , length=100)
	public String getSplitShipSetBy() {
		return splitShipSetBy;
	}
	public void setSplitShipSetBy(String splitShipSetBy) {
		this.splitShipSetBy = splitShipSetBy;
	}
	@Column(name="orderSchemaID" , length=100)
	public String getOrderSchemaID() {
		return orderSchemaID;
	}
	public void setOrderSchemaID(String orderSchemaID) {
		this.orderSchemaID = orderSchemaID;
	}
	@Column(name="orderSchemaType" , length=100)
	public String getOrderSchemaType() {
		return orderSchemaType;
	}
	public void setOrderSchemaType(String orderSchemaType) {
		this.orderSchemaType = orderSchemaType;
	}
	@Column(name="orderMappingID" , length=100)
	public String getOrderMappingID() {
		return orderMappingID;
	}
	public void setOrderMappingID(String orderMappingID) {
		this.orderMappingID = orderMappingID;
	}
	@Column(name="attachmentRequired" , length=100)
	public String getAttachmentRequired() {
		return attachmentRequired;
	}
	public void setAttachmentRequired(String attachmentRequired) {
		this.attachmentRequired = attachmentRequired;
	}
	@Column(name="attachmentIdentifier_1" , length=100)
	public String getAttachmentIdentifier_1() {
		return attachmentIdentifier_1;
	}
	public void setAttachmentIdentifier_1(String attachmentIdentifier_1) {
		this.attachmentIdentifier_1 = attachmentIdentifier_1;
	}
	@Column(name="attachmentSchemaType_1" , length=100)
	public String getAttachmentSchemaType_1() {
		return attachmentSchemaType_1;
	}
	public void setAttachmentSchemaType_1(String attachmentSchemaType_1) {
		this.attachmentSchemaType_1 = attachmentSchemaType_1;
	}
	@Column(name="attachmentMappingID_1" , length=100)
	public String getAttachmentMappingID_1() {
		return attachmentMappingID_1;
	}
	public void setAttachmentMappingID_1(String attachmentMappingID_1) {
		this.attachmentMappingID_1 = attachmentMappingID_1;
	}
	@Column(name="attachmentIdentifier_2" , length=100)
	public String getAttachmentIdentifier_2() {
		return attachmentIdentifier_2;
	}
	public void setAttachmentIdentifier_2(String attachmentIdentifier_2) {
		this.attachmentIdentifier_2 = attachmentIdentifier_2;
	}
	@Column(name="attachmentSchemaID_2" , length=100)
	public String getAttachmentSchemaID_2() {
		return attachmentSchemaID_2;
	}
	public void setAttachmentSchemaID_2(String attachmentSchemaID_2) {
		this.attachmentSchemaID_2 = attachmentSchemaID_2;
	}
	@Column(name="attachmentSchemaType_2" , length=100)
	public String getAttachmentSchemaType_2() {
		return attachmentSchemaType_2;
	}
	public void setAttachmentSchemaType_2(String attachmentSchemaType_2) {
		this.attachmentSchemaType_2 = attachmentSchemaType_2;
	}
	@Column(name="attachmentMappingID_2" , length=100)
	public String getAttachmentMappingID_2() {
		return attachmentMappingID_2;
	}
	public void setAttachmentMappingID_2(String attachmentMappingID_2) {
		this.attachmentMappingID_2 = attachmentMappingID_2;
	}
	@Column(name="attachmentIdentifier_3" , length=100)
	public String getAttachmentIdentifier_3() {
		return attachmentIdentifier_3;
	}
	public void setAttachmentIdentifier_3(String attachmentIdentifier_3) {
		this.attachmentIdentifier_3 = attachmentIdentifier_3;
	}
	@Column(name="attachmentSchemaID_3" , length=100)
	public String getAttachmentSchemaID_3() {
		return attachmentSchemaID_3;
	}
	public void setAttachmentSchemaID_3(String attachmentSchemaID_3) {
		this.attachmentSchemaID_3 = attachmentSchemaID_3;
	}
	@Column(name="attachmentSchemaType_3" , length=100)
	public String getAttachmentSchemaType_3() {
		return attachmentSchemaType_3;
	}
	public void setAttachmentSchemaType_3(String attachmentSchemaType_3) {
		this.attachmentSchemaType_3 = attachmentSchemaType_3;
	}
	@Column(name="attachmentMappingID_3" , length=100)
	public String getAttachmentMappingID_3() {
		return attachmentMappingID_3;
	}
	public void setAttachmentMappingID_3(String attachmentMappingID_3) {
		this.attachmentMappingID_3 = attachmentMappingID_3;
	}
	@Column(name="preProcessPID")
	public String getPreProcessPID() {
		return preProcessPID;
	}
	public void setPreProcessPID(String preProcessPID) {
		this.preProcessPID = preProcessPID;
	}
	@Column(name="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="createdBy" , length=100)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="lastModifiedDate")
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@Column(name="lastModifiedBy" , length=100)
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
    
    
    
    
}
