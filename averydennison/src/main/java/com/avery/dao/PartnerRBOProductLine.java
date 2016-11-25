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
	int PartnerTableID;
	String partnerID;
    String RBOID;
    String RBOName;
    String ProductLineType;
    String CSRName;
    String CSREmail;
    String OrderEmailDomain;
    String ShippingInstructions;
    String PackingInstruction;
    String InvoiceLineInstruction;
    String VariableDataBreakdown;
    String ManufacturingNotes;
    String ShippingOnlyNotes;
    String SplitShipSetBy;
    String OrderSchemaID;
    String OrderSchemaType;
    String OrderMappingID;
    String IsAttachmentRequired;
    String AttachmentIdentifier_1;
    String AttachmentSchemaType_1;
    String AttachmentMappingID_1;
    String AttachmentIdentifier_2;
    String AttachmentSchemaID_2;
    String AttachmentSchemaType_2;
    String AttachmentMappingID_2;
    String AttachmentIdentifier_3;
    String AttachmentSchemaID_3;
    String AttachmentSchemaType_3;
    String AttachmentMappingID_3;
    String PreProcessPID;
    Date CreatedDate;
    String CreatedBy;
    Date LastModifiedDate;
    String LastModifiedBy;
    
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
	public int getPartnerTableID() {
		return PartnerTableID;
	}
	public void setPartnerTableID(int partnerTableID) {
		PartnerTableID = partnerTableID;
	}
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	public String getRBOID() {
		return RBOID;
	}
	public void setRBOID(String rBOID) {
		RBOID = rBOID;
	}
	public String getRBOName() {
		return RBOName;
	}
	public void setRBOName(String rBOName) {
		RBOName = rBOName;
	}
	public String getProductLineType() {
		return ProductLineType;
	}
	public void setProductLineType(String productLineType) {
		ProductLineType = productLineType;
	}
	public String getCSRName() {
		return CSRName;
	}
	public void setCSRName(String cSRName) {
		CSRName = cSRName;
	}
	public String getCSREmail() {
		return CSREmail;
	}
	public void setCSREmail(String cSREmail) {
		CSREmail = cSREmail;
	}
	public String getOrderEmailDomain() {
		return OrderEmailDomain;
	}
	public void setOrderEmailDomain(String orderEmailDomain) {
		OrderEmailDomain = orderEmailDomain;
	}
	public String getShippingInstructions() {
		return ShippingInstructions;
	}
	public void setShippingInstructions(String shippingInstructions) {
		ShippingInstructions = shippingInstructions;
	}
	public String getPackingInstruction() {
		return PackingInstruction;
	}
	public void setPackingInstruction(String packingInstruction) {
		PackingInstruction = packingInstruction;
	}
	public String getInvoiceLineInstruction() {
		return InvoiceLineInstruction;
	}
	public void setInvoiceLineInstruction(String invoiceLineInstruction) {
		InvoiceLineInstruction = invoiceLineInstruction;
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
	public String getShippingOnlyNotes() {
		return ShippingOnlyNotes;
	}
	public void setShippingOnlyNotes(String shippingOnlyNotes) {
		ShippingOnlyNotes = shippingOnlyNotes;
	}
	public String getSplitShipSetBy() {
		return SplitShipSetBy;
	}
	public void setSplitShipSetBy(String splitShipSetBy) {
		SplitShipSetBy = splitShipSetBy;
	}
	public String getOrderSchemaID() {
		return OrderSchemaID;
	}
	public void setOrderSchemaID(String orderSchemaID) {
		OrderSchemaID = orderSchemaID;
	}
	public String getOrderSchemaType() {
		return OrderSchemaType;
	}
	public void setOrderSchemaType(String orderSchemaType) {
		OrderSchemaType = orderSchemaType;
	}
	public String getOrderMappingID() {
		return OrderMappingID;
	}
	public void setOrderMappingID(String orderMappingID) {
		OrderMappingID = orderMappingID;
	}
	public String getIsAttachmentRequired() {
		return IsAttachmentRequired;
	}
	public void setIsAttachmentRequired(String isAttachmentRequired) {
		IsAttachmentRequired = isAttachmentRequired;
	}
	public String getAttachmentIdentifier_1() {
		return AttachmentIdentifier_1;
	}
	public void setAttachmentIdentifier_1(String attachmentIdentifier_1) {
		AttachmentIdentifier_1 = attachmentIdentifier_1;
	}
	public String getAttachmentSchemaType_1() {
		return AttachmentSchemaType_1;
	}
	public void setAttachmentSchemaType_1(String attachmentSchemaType_1) {
		AttachmentSchemaType_1 = attachmentSchemaType_1;
	}
	public String getAttachmentMappingID_1() {
		return AttachmentMappingID_1;
	}
	public void setAttachmentMappingID_1(String attachmentMappingID_1) {
		AttachmentMappingID_1 = attachmentMappingID_1;
	}
	public String getAttachmentIdentifier_2() {
		return AttachmentIdentifier_2;
	}
	public void setAttachmentIdentifier_2(String attachmentIdentifier_2) {
		AttachmentIdentifier_2 = attachmentIdentifier_2;
	}
	public String getAttachmentSchemaID_2() {
		return AttachmentSchemaID_2;
	}
	public void setAttachmentSchemaID_2(String attachmentSchemaID_2) {
		AttachmentSchemaID_2 = attachmentSchemaID_2;
	}
	public String getAttachmentSchemaType_2() {
		return AttachmentSchemaType_2;
	}
	public void setAttachmentSchemaType_2(String attachmentSchemaType_2) {
		AttachmentSchemaType_2 = attachmentSchemaType_2;
	}
	public String getAttachmentMappingID_2() {
		return AttachmentMappingID_2;
	}
	public void setAttachmentMappingID_2(String attachmentMappingID_2) {
		AttachmentMappingID_2 = attachmentMappingID_2;
	}
	public String getAttachmentIdentifier_3() {
		return AttachmentIdentifier_3;
	}
	public void setAttachmentIdentifier_3(String attachmentIdentifier_3) {
		AttachmentIdentifier_3 = attachmentIdentifier_3;
	}
	public String getAttachmentSchemaID_3() {
		return AttachmentSchemaID_3;
	}
	public void setAttachmentSchemaID_3(String attachmentSchemaID_3) {
		AttachmentSchemaID_3 = attachmentSchemaID_3;
	}
	public String getAttachmentSchemaType_3() {
		return AttachmentSchemaType_3;
	}
	public void setAttachmentSchemaType_3(String attachmentSchemaType_3) {
		AttachmentSchemaType_3 = attachmentSchemaType_3;
	}
	public String getAttachmentMappingID_3() {
		return AttachmentMappingID_3;
	}
	public void setAttachmentMappingID_3(String attachmentMappingID_3) {
		AttachmentMappingID_3 = attachmentMappingID_3;
	}
	public String getPreProcessPID() {
		return PreProcessPID;
	}
	public void setPreProcessPID(String preProcessPID) {
		PreProcessPID = preProcessPID;
	}
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public Date getLastModifiedDate() {
		return LastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}
    
    
    
    
}
