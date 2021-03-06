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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "partner_rboproductline")
public class Partner_RBOProductLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	int id;
	@Column(name = "active")
	Boolean active;
	@Column(name = "attachmentFileMatchLocation", length = 100)
	String attachmentFileMatchLocation;// 100
	@Column(name = "attachmentFileMatchRequired")
	Boolean attachmentFileMatchRequired;
	@Column(name = "attachmentFileNameExtension_1", length = 50)
	String attachmentFileNameExtension_1;// 50
	@Column(name = "attachmentFileNameExtension_2", length = 50)
	String attachmentFileNameExtension_2;// 50
	@Column(name = "attachmentFileNameExtension_3", length = 50)
	String attachmentFileNameExtension_3;// 50
	@Column(name = "attachmentFileNameExtension_4", length = 50)
	String attachmentFileNameExtension_4;// 50
	@Column(name = "attachmentFileNamePattern_1", length = 100)
	String attachmentFileNamePattern_1;// 100
	@Column(name = "attachmentFileNamePattern_2", length = 100)
	String attachmentFileNamePattern_2;// 100
	@Column(name = "attachmentFileNamePattern_3", length = 100)
	String attachmentFileNamePattern_3;// 100
	@Column(name = "attachmentFileNamePattern_4", length = 100)
	String attachmentFileNamePattern_4;// 100
	@Column(name = "attachmentFileOrderMatch", length = 100)
	String attachmentFileOrderMatch;// 100
	@Column(name = "attachmentFileOrderMatchLocation", length = 100)
	String attachmentFileOrderMatchLocation;// 100
	@Column(name = "productLineMatchFlag", length = 50)
	String productLineMatchFlag; 
	@Column(name = "attachmentFileOrderMatchRequired")
	Boolean attachmentFileOrderMatchRequired;
	@Column(name = "attachmentFileProductlineMatchLocation", length = 100)
	String attachmentFileProductlineMatchLocation;// 100
	@Column(name = "attachmentFileProductlineMatchRequired")
	Boolean attachmentFileProductlineMatchRequired;
	
	@Column(name = "coocheck ")
	Boolean coocheck;
	@Column(name = "factoryMOQCheck")
	Boolean factoryMOQCheck;
	@Column(name = "factoryMOQValue", length = 100)
	String factoryMOQValue;
	@Column(name = "assignCSRName")
	String assignCSRName;
	@Column(name = "fiberpercentagecheck")
	Boolean fiberpercentagecheck;
	 
	@Column(name = "attachmentFileRBOMatch", length = 100)
	String attachmentFileRBOMatch;// 100
	@Column(name = "attachmentIdentifier_1", length = 50)
	String attachmentIdentifier_1;// 50
	@Column(name = "attachmentIdentifier_2", length = 50)
	String attachmentIdentifier_2;// 50
	@Column(name = "attachmentIdentifier_3", length = 50)
	String attachmentIdentifier_3;// 50
	@Column(name = "attachmentIdentifier_4", length = 50)
	String attachmentIdentifier_4;// 50
	@Column(name = "attachmentMappingID_1", length = 50)
	String attachmentMappingID_1;// 50
	@Column(name = "attachmentMappingID_2", length = 50)
	String attachmentMappingID_2;// 50
	@Column(name = "attachmentMappingID_3", length = 50)
	String attachmentMappingID_3;// 50
	@Column(name = "attachmentMappingID_4", length = 50)
	String attachmentMappingID_4;// 50
	@Column(name = "attachmentProductlineMatch", length = 100)
	String attachmentProductlineMatch;// 100
	@Column(name = "attachmentRequired")
	Boolean attachmentRequired;
	@Column(name = "attachmentSchemaID_1", length = 50)
	String attachmentSchemaID_1;// 50
	@Column(name = "attachmentSchemaID_2", length = 50)
	String attachmentSchemaID_2;// 50
	@Column(name = "attachmentSchemaID_3", length = 50)
	String attachmentSchemaID_3;// 50
	@Column(name = "attachmentSchemaID_4", length = 50)
	String attachmentSchemaID_4;// 50
	@Column(name = "attachmentSchemaType_1", length = 50)
	String attachmentSchemaType_1;// 50
	@Column(name = "attachmentSchemaType_2", length = 50)
	String attachmentSchemaType_2;// 50
	@Column(name = "attachmentSchemaType_3", length = 50)
	String attachmentSchemaType_3;// 50
	@Column(name = "attachmentSchemaType_4", length = 50)
	String attachmentSchemaType_4;// 50
	@Column(name = "comment", length = 250)
	String comment;// 250
	@Column(name = "controlData")
	Boolean controlData;
	@Column(name = "createdBy", length = 50)
	String createdBy;// 50
	@Column(name = "createdDate")
	Date createdDate;
	
	
	@Column(name="email",length=1000)
	String email;
	
	@Column(name = "CSRPrimaryId", length = 250)
	String CSRPrimaryId;// 250
	@Column(name = "CSRSecondaryId", length = 250)
	String CSRSecondaryId;// 250
	@Column(name = "emailSubjectProductLineMatch", length = 100)
	String emailSubjectProductLineMatch;// 100
	@Column(name = "emailSubjectProductlineMatchLocation", length = 100)
	String emailSubjectProductlineMatchLocation;
	@Column(name = "emailSubjectProductlineMatchRequired")
	Boolean emailSubjectProductlineMatchRequired;
	@Column(name = "emailSubjectRBOMatch",length=100)
	String emailSubjectRBOMatch;
	@Column(name = "emailSubjectRBOMatchLocation", length = 100)
	String emailSubjectRBOMatchLocation;// 100
	@Column(name = "emailSubjectRBOMatchRequired")
	Boolean emailSubjectRBOMatchRequired;
	@Column(name = "emailBodyProductLineMatch",length=100)
	String  emailBodyProductLineMatch;	
	@Column(name = "emailBodyProductlineMatchLocation",length=100)
	String emailBodyProductlineMatchLocation;	
	@Column(name = "emailBodyProductlineMatchRequired")
	Boolean emailBodyProductlineMatchRequired;	
	@Column(name = "emailBodyRBOMatch",length=100)
	String  emailBodyRBOMatch;
	@Column(name = "emailBodyRBOMatchLocation",length=100)
	String emailBodyRBOMatchLocation;	
	@Column(name = "emailBodyRBOMatchRequired")
	Boolean emailBodyRBOMatchRequired;	
	@Column(name = "fileRBOMatchLocation",length=100)
	String fileRBOMatchLocation;
	@Column(name = "fileRBOMatchRequired")
	Boolean fileRBOMatchRequired;
	@Column(name = "factoryTransfer")
	Boolean factoryTransfer;
	@Column(name = "fileMatchLocation", length = 100)
	String fileMatchLocation;// 100
	@Column(name = "fileMatchRequired")
	Boolean fileMatchRequired;
	@Column(name = "fileOrderMatch", length = 100)
	String fileOrderMatch;// 100
	@Column(name = "fileOrderMatchLocation", length = 100)
	String fileOrderMatchLocation;// 100
	@Column(name = "fileOrderMatchRequired")
	Boolean fileOrderMatchRequired;
	@Column(name = "fileProductlineMatch", length = 100)
	String fileProductlineMatch;// 100
	@Column(name = "fileProductLineMatchLocation", length = 100)
	String fileProductLineMatchLocation;// 100
	@Column(name = "fileProductLineMatchRequired")
	Boolean fileProductLineMatchRequired;
	@Column(name = "fileRBOMatch", length = 100)
	String fileRBOMatch;// 100
	@Column(name = "invoicelineInstruction", length = 500)
	String invoicelineInstruction;// 500
	@Column(name = "lastModifiedBy", length = 50)
	String lastModifiedBy;// 50
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@Column(name = "LLKK")
	Boolean LLKK;
	@Column(name = "localBilling")
	Boolean localBilling;
	@Column(name = "miscCSRInstruction", length = 500)
	String miscCSRInstruction;// 500
	@Column(name = "orderFileNameExtension", length = 100)
	String orderFileNameExtension;// 100
	@Column(name = "orderFileNamePattern", length = 25)
	String orderFileNamePattern;// 25
	@Column(name = "orderMappingID", length = 50)
	String orderMappingID;// 50
	@Column(name = "orderSchemaID", length = 50)
	String orderSchemaID;// 50
	@Column(name = "orderSchemaType", length = 50)
	String orderSchemaType;// 50
	@Column(name = "others")
	Boolean others;// Others (pls specify)
	@Column(name = "preProcessPID", length = 50)
	String preProcessPID;// 50
	@Column(name = "productLineType", length = 25)
	String productLineType;// 25
	@Column(name = "shipmentSample")
	Boolean shipmentSample;
    @Column(name = "waiveMOA")
	Boolean waiveMOA;
	@Column(name = "waiveMOQ")
	Boolean waiveMOQ;
	@Column(name = "localItem")
	Boolean localItem;
	@Column(name = "averyItem")
	Boolean averyItem;	
	@Column(name = "customerItemIdentifierDescription",length=500)
	String customerItemIdentifierDescription;
	@Column(name = "defaultSystem",length=5)
	String defaultSystem;
	@Column(name = "orderInMailBody")
	Boolean orderInMailBody;
	@Column(name = "dataStructureName",length=100)
	String dataStructureName ;
	
	@Column(name = "revisecancelorder",length=100)
	String revisecancelorder;
	/*
    @Column(name = "packingInstruction", length = 500)
	String packingInstruction;// 500
	@Column(name = "manufacturingNotes", length = 500)
	String manufacturingNotes;// 500
    @Column(name = "shippingMark")
	boolean shippingMark;
	@Column(name = "shippingOnlyNotes", length = 500)
	String shippingOnlyNotes;// 500
	@Column(name = "splitShipSetBy", length = 5)
	String splitShipSetBy;// 5
	@Column(name = "variableDataBreakdown", length = 500)
	String variableDataBreakdown;// 500@Column(name = "artworkHold", length = 5)
	String artworkHold;// 5
	@Column(name = "defaultBillToCode", length = 255)
	String defaultBillToCode;// 255
	@Column(name = "defaultShipToCode", length = 255)
	String defaultShipToCode;// 255	
	@Column(name = "discountOffer")
	boolean discountOffer;
	*/
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rboId")
	RBO varRbo;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "partnerId")
	Partner varPartner;
	//@OneToOne(mappedBy = "varProductLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//OrderFileQueue listOrderFileQueue;
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@OneToMany(mappedBy="varProductLine",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	//List<OrderFileAttachment> listOrderFileAttachments=new ArrayList<OrderFileAttachment>();
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="varProductLine",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<OrderSystemInfo> listOrderSystemInfo=new ArrayList<OrderSystemInfo>();
	
	
	//////////new fields for identification process////////////////////
	@Column(name = "emailBodyPartnerRequired")
	boolean emailBodyPartnerRequired;
	@Column(name = "emailBodyPartnerMatch",length=250)
	String emailBodyPartnerMatch;
	@Column(name = "emailSubjectPartnerRequired")
	boolean emailSubjectPartnerRequired;
	@Column(name = "emailSubjectPartnerMatch",length=250)
	String emailSubjectPartnerMatch;
	@Column(name = "fileOrderPartnerMatch",length=250)
	String fileOrderPartnerMatch;
	@Column(name = "fileOrderPartnerRequired")
	boolean fileOrderPartnerRequired;
	@Column(name = "OrderInEmailBodyMatch",length=100)
	String OrderInEmailBodyMatch;
	@Column(name = "OrderInEmailSubjectMatch",length=100)
	String OrderInEmailSubjectMatch;
	
	@Column(name = "site",length=100)
	String site;
	
	
	/////////new fields finish/////////////////////////////////////////
	
	
	


	public Partner_RBOProductLine() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Boolean isActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public String getAttachmentFileMatchLocation() {
		return attachmentFileMatchLocation;
	}


	public void setAttachmentFileMatchLocation(String attachmentFileMatchLocation) {
		this.attachmentFileMatchLocation = attachmentFileMatchLocation;
	}


	public Boolean isAttachmentFileMatchRequired() {
		return attachmentFileMatchRequired;
	}


	public void setAttachmentFileMatchRequired(Boolean attachmentFileMatchRequired) {
		this.attachmentFileMatchRequired = attachmentFileMatchRequired;
	}


	public String getAttachmentFileNameExtension_1() {
		return attachmentFileNameExtension_1;
	}


	public void setAttachmentFileNameExtension_1(
			String attachmentFileNameExtension_1) {
		this.attachmentFileNameExtension_1 = attachmentFileNameExtension_1;
	}


	public String getAttachmentFileNameExtension_2() {
		return attachmentFileNameExtension_2;
	}


	public void setAttachmentFileNameExtension_2(
			String attachmentFileNameExtension_2) {
		this.attachmentFileNameExtension_2 = attachmentFileNameExtension_2;
	}


	public String getAttachmentFileNameExtension_3() {
		return attachmentFileNameExtension_3;
	}


	public void setAttachmentFileNameExtension_3(
			String attachmentFileNameExtension_3) {
		this.attachmentFileNameExtension_3 = attachmentFileNameExtension_3;
	}


	public String getAttachmentFileNameExtension_4() {
		return attachmentFileNameExtension_4;
	}


	public void setAttachmentFileNameExtension_4(
			String attachmentFileNameExtension_4) {
		this.attachmentFileNameExtension_4 = attachmentFileNameExtension_4;
	}


	public String getAttachmentFileNamePattern_1() {
		return attachmentFileNamePattern_1;
	}


	public void setAttachmentFileNamePattern_1(String attachmentFileNamePattern_1) {
		this.attachmentFileNamePattern_1 = attachmentFileNamePattern_1;
	}


	public String getAttachmentFileNamePattern_2() {
		return attachmentFileNamePattern_2;
	}


	public void setAttachmentFileNamePattern_2(String attachmentFileNamePattern_2) {
		this.attachmentFileNamePattern_2 = attachmentFileNamePattern_2;
	}


	public String getAttachmentFileNamePattern_3() {
		return attachmentFileNamePattern_3;
	}


	public void setAttachmentFileNamePattern_3(String attachmentFileNamePattern_3) {
		this.attachmentFileNamePattern_3 = attachmentFileNamePattern_3;
	}


	public String getAttachmentFileNamePattern_4() {
		return attachmentFileNamePattern_4;
	}


	public void setAttachmentFileNamePattern_4(String attachmentFileNamePattern_4) {
		this.attachmentFileNamePattern_4 = attachmentFileNamePattern_4;
	}


	public String getAttachmentFileOrderMatch() {
		return attachmentFileOrderMatch;
	}


	public void setAttachmentFileOrderMatch(String attachmentFileOrderMatch) {
		this.attachmentFileOrderMatch = attachmentFileOrderMatch;
	}


	public String getAttachmentFileOrderMatchLocation() {
		return attachmentFileOrderMatchLocation;
	}


	public void setAttachmentFileOrderMatchLocation(
			String attachmentFileOrderMatchLocation) {
		this.attachmentFileOrderMatchLocation = attachmentFileOrderMatchLocation;
	}


	public Boolean isAttachmentFileOrderMatchRequired() {
		return attachmentFileOrderMatchRequired;
	}


	public void setAttachmentFileOrderMatchRequired(
			Boolean attachmentFileOrderMatchRequired) {
		this.attachmentFileOrderMatchRequired = attachmentFileOrderMatchRequired;
	}


	public String getAttachmentFileProductlineMatchLocation() {
		return attachmentFileProductlineMatchLocation;
	}


	public void setAttachmentFileProductlineMatchLocation(
			String attachmentFileProductlineMatchLocation) {
		this.attachmentFileProductlineMatchLocation = attachmentFileProductlineMatchLocation;
	}


	public Boolean isAttachmentFileProductlineMatchRequired() {
		return attachmentFileProductlineMatchRequired;
	}


	public void setAttachmentFileProductlineMatchRequired(
			Boolean attachmentFileProductlineMatchRequired) {
		this.attachmentFileProductlineMatchRequired = attachmentFileProductlineMatchRequired;
	}


	public String getAttachmentFileRBOMatch() {
		return attachmentFileRBOMatch;
	}


	public void setAttachmentFileRBOMatch(String attachmentFileRBOMatch) {
		this.attachmentFileRBOMatch = attachmentFileRBOMatch;
	}


	public String getAttachmentIdentifier_1() {
		return attachmentIdentifier_1;
	}


	public void setAttachmentIdentifier_1(String attachmentIdentifier_1) {
		this.attachmentIdentifier_1 = attachmentIdentifier_1;
	}


	public String getAttachmentIdentifier_2() {
		return attachmentIdentifier_2;
	}


	public void setAttachmentIdentifier_2(String attachmentIdentifier_2) {
		this.attachmentIdentifier_2 = attachmentIdentifier_2;
	}


	public String getAttachmentIdentifier_3() {
		return attachmentIdentifier_3;
	}


	public void setAttachmentIdentifier_3(String attachmentIdentifier_3) {
		this.attachmentIdentifier_3 = attachmentIdentifier_3;
	}


	public String getAttachmentIdentifier_4() {
		return attachmentIdentifier_4;
	}


	public void setAttachmentIdentifier_4(String attachmentIdentifier_4) {
		this.attachmentIdentifier_4 = attachmentIdentifier_4;
	}


	public String getAttachmentMappingID_1() {
		return attachmentMappingID_1;
	}


	public void setAttachmentMappingID_1(String attachmentMappingID_1) {
		this.attachmentMappingID_1 = attachmentMappingID_1;
	}


	public String getAttachmentMappingID_2() {
		return attachmentMappingID_2;
	}


	public void setAttachmentMappingID_2(String attachmentMappingID_2) {
		this.attachmentMappingID_2 = attachmentMappingID_2;
	}


	public String getAttachmentMappingID_3() {
		return attachmentMappingID_3;
	}


	public void setAttachmentMappingID_3(String attachmentMappingID_3) {
		this.attachmentMappingID_3 = attachmentMappingID_3;
	}


	public String getAttachmentMappingID_4() {
		return attachmentMappingID_4;
	}


	public void setAttachmentMappingID_4(String attachmentMappingID_4) {
		this.attachmentMappingID_4 = attachmentMappingID_4;
	}


	public String getAttachmentProductlineMatch() {
		return attachmentProductlineMatch;
	}


	public void setAttachmentProductlineMatch(String attachmentProductlineMatch) {
		this.attachmentProductlineMatch = attachmentProductlineMatch;
	}


	public Boolean isAttachmentRequired() {
		return attachmentRequired;
	}


	public void setAttachmentRequired(Boolean attachmentRequired) {
		this.attachmentRequired = attachmentRequired;
	}


	public String getAttachmentSchemaID_1() {
		return attachmentSchemaID_1;
	}


	public void setAttachmentSchemaID_1(String attachmentSchemaID_1) {
		this.attachmentSchemaID_1 = attachmentSchemaID_1;
	}


	public String getAttachmentSchemaID_2() {
		return attachmentSchemaID_2;
	}


	public void setAttachmentSchemaID_2(String attachmentSchemaID_2) {
		this.attachmentSchemaID_2 = attachmentSchemaID_2;
	}


	public String getAttachmentSchemaID_3() {
		return attachmentSchemaID_3;
	}


	public void setAttachmentSchemaID_3(String attachmentSchemaID_3) {
		this.attachmentSchemaID_3 = attachmentSchemaID_3;
	}


	public String getAttachmentSchemaID_4() {
		return attachmentSchemaID_4;
	}


	public void setAttachmentSchemaID_4(String attachmentSchemaID_4) {
		this.attachmentSchemaID_4 = attachmentSchemaID_4;
	}


	public String getAttachmentSchemaType_1() {
		return attachmentSchemaType_1;
	}


	public void setAttachmentSchemaType_1(String attachmentSchemaType_1) {
		this.attachmentSchemaType_1 = attachmentSchemaType_1;
	}


	public String getAttachmentSchemaType_2() {
		return attachmentSchemaType_2;
	}


	public void setAttachmentSchemaType_2(String attachmentSchemaType_2) {
		this.attachmentSchemaType_2 = attachmentSchemaType_2;
	}


	public String getAttachmentSchemaType_3() {
		return attachmentSchemaType_3;
	}


	public void setAttachmentSchemaType_3(String attachmentSchemaType_3) {
		this.attachmentSchemaType_3 = attachmentSchemaType_3;
	}


	public String getAttachmentSchemaType_4() {
		return attachmentSchemaType_4;
	}


	public void setAttachmentSchemaType_4(String attachmentSchemaType_4) {
		this.attachmentSchemaType_4 = attachmentSchemaType_4;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Boolean isControlData() {
		return controlData;
	}


	public void setControlData(Boolean controlData) {
		this.controlData = controlData;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCSRPrimaryId() {
		return CSRPrimaryId;
	}


	public void setCSRPrimaryId(String cSRPrimaryId) {
		CSRPrimaryId = cSRPrimaryId;
	}


	public String getCSRSecondaryId() {
		return CSRSecondaryId;
	}


	public void setCSRSecondaryId(String cSRSecondaryId) {
		CSRSecondaryId = cSRSecondaryId;
	}


	public String getEmailSubjectProductLineMatch() {
		return emailSubjectProductLineMatch;
	}


	public void setEmailSubjectProductLineMatch(String emailSubjectProductLineMatch) {
		this.emailSubjectProductLineMatch = emailSubjectProductLineMatch;
	}


	public String getEmailSubjectProductlineMatchLocation() {
		return emailSubjectProductlineMatchLocation;
	}


	public void setEmailSubjectProductlineMatchLocation(
			String emailSubjectProductlineMatchLocation) {
		this.emailSubjectProductlineMatchLocation = emailSubjectProductlineMatchLocation;
	}


	public Boolean isEmailSubjectProductlineMatchRequired() {
		return emailSubjectProductlineMatchRequired;
	}


	public void setEmailSubjectProductlineMatchRequired(
			Boolean emailSubjectProductlineMatchRequired) {
		this.emailSubjectProductlineMatchRequired = emailSubjectProductlineMatchRequired;
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


	public Boolean isEmailSubjectRBOMatchRequired() {
		return emailSubjectRBOMatchRequired;
	}


	public void setEmailSubjectRBOMatchRequired(Boolean emailSubjectRBOMatchRequired) {
		this.emailSubjectRBOMatchRequired = emailSubjectRBOMatchRequired;
	}


	public String getEmailBodyProductLineMatch() {
		return emailBodyProductLineMatch;
	}


	public void setEmailBodyProductLineMatch(String emailBodyProductLineMatch) {
		this.emailBodyProductLineMatch = emailBodyProductLineMatch;
	}


	public String getEmailBodyProductlineMatchLocation() {
		return emailBodyProductlineMatchLocation;
	}


	public void setEmailBodyProductlineMatchLocation(
			String emailBodyProductlineMatchLocation) {
		this.emailBodyProductlineMatchLocation = emailBodyProductlineMatchLocation;
	}


	public Boolean isEmailBodyProductlineMatchRequired() {
		return emailBodyProductlineMatchRequired;
	}


	public void setEmailBodyProductlineMatchRequired(
			Boolean emailBodyProductlineMatchRequired) {
		this.emailBodyProductlineMatchRequired = emailBodyProductlineMatchRequired;
	}


	public String getEmailBodyRBOMatch() {
		return emailBodyRBOMatch;
	}


	public void setEmailBodyRBOMatch(String emailBodyRBOMatch) {
		this.emailBodyRBOMatch = emailBodyRBOMatch;
	}


	public String getEmailBodyRBOMatchLocation() {
		return emailBodyRBOMatchLocation;
	}


	public void setEmailBodyRBOMatchLocation(String emailBodyRBOMatchLocation) {
		this.emailBodyRBOMatchLocation = emailBodyRBOMatchLocation;
	}


	public Boolean isEmailBodyRBOMatchRequired() {
		return emailBodyRBOMatchRequired;
	}


	public void setEmailBodyRBOMatchRequired(Boolean emailBodyRBOMatchRequired) {
		this.emailBodyRBOMatchRequired = emailBodyRBOMatchRequired;
	}


	public String getFileRBOMatchLocation() {
		return fileRBOMatchLocation;
	}


	public void setFileRBOMatchLocation(String fileRBOMatchLocation) {
		this.fileRBOMatchLocation = fileRBOMatchLocation;
	}


	public Boolean isFileRBOMatchRequired() {
		return fileRBOMatchRequired;
	}


	public void setFileRBOMatchRequired(Boolean fileRBOMatchRequired) {
		this.fileRBOMatchRequired = fileRBOMatchRequired;
	}


	public Boolean isFactoryTransfer() {
		return factoryTransfer;
	}


	public void setFactoryTransfer(Boolean factoryTransfer) {
		this.factoryTransfer = factoryTransfer;
	}


	public String getFileMatchLocation() {
		return fileMatchLocation;
	}


	public void setFileMatchLocation(String fileMatchLocation) {
		this.fileMatchLocation = fileMatchLocation;
	}


	public Boolean isFileMatchRequired() {
		return fileMatchRequired;
	}


	public void setFileMatchRequired(Boolean fileMatchRequired) {
		this.fileMatchRequired = fileMatchRequired;
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


	public Boolean isFileOrderMatchRequired() {
		return fileOrderMatchRequired;
	}


	public void setFileOrderMatchRequired(Boolean fileOrderMatchRequired) {
		this.fileOrderMatchRequired = fileOrderMatchRequired;
	}


	public String getFileProductlineMatch() {
		return fileProductlineMatch;
	}


	public void setFileProductlineMatch(String fileProductlineMatch) {
		this.fileProductlineMatch = fileProductlineMatch;
	}


	public String getFileProductLineMatchLocation() {
		return fileProductLineMatchLocation;
	}


	public void setFileProductLineMatchLocation(String fileProductLineMatchLocation) {
		this.fileProductLineMatchLocation = fileProductLineMatchLocation;
	}


	public Boolean isFileProductLineMatchRequired() {
		return fileProductLineMatchRequired;
	}


	public void setFileProductLineMatchRequired(Boolean fileProductLineMatchRequired) {
		this.fileProductLineMatchRequired = fileProductLineMatchRequired;
	}


	public String getFileRBOMatch() {
		return fileRBOMatch;
	}


	public void setFileRBOMatch(String fileRBOMatch) {
		this.fileRBOMatch = fileRBOMatch;
	}


	public String getInvoicelineInstruction() {
		return invoicelineInstruction;
	}


	public void setInvoicelineInstruction(String invoicelineInstruction) {
		this.invoicelineInstruction = invoicelineInstruction;
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


	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public Boolean isLLKK() {
		return LLKK;
	}


	public void setLLKK(Boolean lLKK) {
		LLKK = lLKK;
	}


	public Boolean isLocalBilling() {
		return localBilling;
	}


	public void setLocalBilling(Boolean localBilling) {
		this.localBilling = localBilling;
	}


	public String getMiscCSRInstruction() {
		return miscCSRInstruction;
	}


	public void setMiscCSRInstruction(String miscCSRInstruction) {
		this.miscCSRInstruction = miscCSRInstruction;
	}


	public String getOrderFileNameExtension() {
		return orderFileNameExtension;
	}


	public void setOrderFileNameExtension(String orderFileNameExtension) {
		this.orderFileNameExtension = orderFileNameExtension;
	}


	public String getOrderFileNamePattern() {
		return orderFileNamePattern;
	}


	public void setOrderFileNamePattern(String orderFileNamePattern) {
		this.orderFileNamePattern = orderFileNamePattern;
	}


	public String getOrderMappingID() {
		return orderMappingID;
	}


	public void setOrderMappingID(String orderMappingID) {
		this.orderMappingID = orderMappingID;
	}


	public String getOrderSchemaID() {
		return orderSchemaID;
	}


	public void setOrderSchemaID(String orderSchemaID) {
		this.orderSchemaID = orderSchemaID;
	}


	public String getOrderSchemaType() {
		return orderSchemaType;
	}


	public void setOrderSchemaType(String orderSchemaType) {
		this.orderSchemaType = orderSchemaType;
	}


	public Boolean isOthers() {
		return others;
	}


	public void setOthers(Boolean others) {
		this.others = others;
	}


	public String getPreProcessPID() {
		return preProcessPID;
	}


	public void setPreProcessPID(String preProcessPID) {
		this.preProcessPID = preProcessPID;
	}


	public String getProductLineType() {
		return productLineType;
	}


	public void setProductLineType(String productLineType) {
		this.productLineType = productLineType;
	}


	public Boolean isShipmentSample() {
		return shipmentSample;
	}


	public void setShipmentSample(Boolean shipmentSample) {
		this.shipmentSample = shipmentSample;
	}


	public Boolean isWaiveMOA() {
		return waiveMOA;
	}


	public void setWaiveMOA(Boolean waiveMOA) {
		this.waiveMOA = waiveMOA;
	}


	public Boolean isWaiveMOQ() {
		return waiveMOQ;
	}


	public void setWaiveMOQ(Boolean waiveMOQ) {
		this.waiveMOQ = waiveMOQ;
	}


	public Boolean isLocalItem() {
		return localItem;
	}


	public void setLocalItem(Boolean localItem) {
		this.localItem = localItem;
	}


	public Boolean isAveryItem() {
		return averyItem;
	}


	public void setAveryItem(Boolean averyItem) {
		this.averyItem = averyItem;
	}


	public String getCustomerItemIdentifierDescription() {
		return customerItemIdentifierDescription;
	}


	public void setCustomerItemIdentifierDescription(
			String customerItemIdentifierDescription) {
		this.customerItemIdentifierDescription = customerItemIdentifierDescription;
	}


	public String getDefaultSystem() {
		return defaultSystem;
	}


	public void setDefaultSystem(String defaultSystem) {
		this.defaultSystem = defaultSystem;
	}


	public RBO getVarRbo() {
		return varRbo;
	}


	public void setVarRbo(RBO varRbo) {
		this.varRbo = varRbo;
	}


	public Partner getVarPartner() {
		return varPartner;
	}


	public void setVarPartner(Partner varPartner) {
		this.varPartner = varPartner;
	}
	

	/*public OrderFileQueue getListOrderFileQueue() {
		return listOrderFileQueue;
	}


	public void setListOrderFileQueue(OrderFileQueue listOrderFileQueue) {
		this.listOrderFileQueue = listOrderFileQueue;
	}*/


	public Boolean isFactoryMOQCheck() {
		return factoryMOQCheck;
	}


	public void setFactoryMOQCheck(Boolean factoryMOQCheck) {
		this.factoryMOQCheck = factoryMOQCheck;
	}


	

	public String getFactoryMOQValue() {
		return factoryMOQValue;
	}


	public void setFactoryMOQValue(String factoryMOQValue) {
		this.factoryMOQValue = factoryMOQValue;
	}


	public String getAssignCSRName() {
		return assignCSRName;
	}


	public void setAssignCSRName(String assignCSRName) {
		this.assignCSRName = assignCSRName;
	}


	/*public List<OrderFileAttachment> getListOrderFileAttachments() {
		return listOrderFileAttachments;
	}


	public void setListOrderFileAttachments(
			List<OrderFileAttachment> listOrderFileAttachments) {
		this.listOrderFileAttachments = listOrderFileAttachments;
	}
*/

	public List<OrderSystemInfo> getListOrderSystemInfo() {
		return listOrderSystemInfo;
	}


	public void setListOrderSystemInfo(List<OrderSystemInfo> listOrderSystemInfo) {
		this.listOrderSystemInfo = listOrderSystemInfo;
	}


	public Boolean isOrderInMailBody() {
		return orderInMailBody;
	}


	public void setOrderInMailBody(Boolean orderInMailBody) {
		this.orderInMailBody = orderInMailBody;
	}


	public String getDataStructureName() {
		return dataStructureName;
	}


	public void setDataStructureName(String dataStructureName) {
		this.dataStructureName = dataStructureName;
	}


	public String getRevisecancelorder() {
		return revisecancelorder;
	}


	public void setRevisecancelorder(String revisecancelorder) {
		this.revisecancelorder = revisecancelorder;
	}


	public Boolean isCoocheck() {
		return coocheck;
	}


	public void setCoocheck(Boolean coocheck) {
		this.coocheck = coocheck;
	}


	public Boolean isFiberpercentagecheck() {
		return fiberpercentagecheck;
	}


	public void setFiberpercentagecheck(Boolean fiberpercentagecheck) {
		this.fiberpercentagecheck = fiberpercentagecheck;
	}


	public String getProductLineMatchFlag() {
		return productLineMatchFlag;
	}


	public void setProductLineMatchFlag(String productLineMatchFlag) {
		this.productLineMatchFlag = productLineMatchFlag;
	}
	
	////////getter setter for new field of identification/////////////////////
	public boolean isEmailBodyPartnerRequired() {
		return emailBodyPartnerRequired;
	}


	public void setEmailBodyPartnerRequired(boolean emailBodyPartnerRequired) {
		this.emailBodyPartnerRequired = emailBodyPartnerRequired;
	}


	public String getEmailBodyPartnerMatch() {
		return emailBodyPartnerMatch;
	}


	public void setEmailBodyPartnerMatch(String emailBodyPartnerMatch) {
		this.emailBodyPartnerMatch = emailBodyPartnerMatch;
	}


	public boolean isEmailSubjectPartnerRequired() {
		return emailSubjectPartnerRequired;
	}


	public void setEmailSubjectPartnerRequired(boolean emailSubjectPartnerRequired) {
		this.emailSubjectPartnerRequired = emailSubjectPartnerRequired;
	}


	public String getEmailSubjectPartnerMatch() {
		return emailSubjectPartnerMatch;
	}


	public void setEmailSubjectPartnerMatch(String emailSubjectPartnerMatch) {
		this.emailSubjectPartnerMatch = emailSubjectPartnerMatch;
	}


	public String getFileOrderPartnerMatch() {
		return fileOrderPartnerMatch;
	}


	public void setFileOrderPartnerMatch(String fileOrderPartnerMatch) {
		this.fileOrderPartnerMatch = fileOrderPartnerMatch;
	}


	public boolean isFileOrderPartnerRequired() {
		return fileOrderPartnerRequired;
	}


	public void setFileOrderPartnerRequired(boolean fileOrderPartnerRequired) {
		this.fileOrderPartnerRequired = fileOrderPartnerRequired;
	}


	public String getOrderInEmailBodyMatch() {
		return OrderInEmailBodyMatch;
	}


	public void setOrderInEmailBodyMatch(String orderInEmailBodyMatch) {
		OrderInEmailBodyMatch = orderInEmailBodyMatch;
	}


	public String getOrderInEmailSubjectMatch() {
		return OrderInEmailSubjectMatch;
	}


	public void setOrderInEmailSubjectMatch(String orderInEmailSubjectMatch) {
		OrderInEmailSubjectMatch = orderInEmailSubjectMatch;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}
	
	
	/////////identification 2b fields finish////////////////////////////////

	

	/*
	
	public String getArtworkHold() {
	return artworkHold;
	}


	public void setArtworkHold(String artworkHold) {
	this.artworkHold = artworkHold;
	}
	
	public String getDefaultBillToCode() {
		return defaultBillToCode;
	}


	public void setDefaultBillToCode(String defaultBillToCode) {
		this.defaultBillToCode = defaultBillToCode;
	}


	public String getDefaultShipToCode() {
		return defaultShipToCode;
	}


	public void setDefaultShipToCode(String defaultShipToCode) {
		this.defaultShipToCode = defaultShipToCode;
	}


	public boolean isDiscountOffer() {
		return discountOffer;
	}


	public void setDiscountOffer(boolean discountOffer) {
		this.discountOffer = discountOffer;
	}
	
	public String getManufacturingNotes() {
		return manufacturingNotes;
	}


	public void setManufacturingNotes(String manufacturingNotes) {
		this.manufacturingNotes = manufacturingNotes;
	}
	public String getPackingInstruction() {
		return packingInstruction;
	}


	public void setPackingInstruction(String packingInstruction) {
		this.packingInstruction = packingInstruction;
	}

	public boolean isShippingMark() {
		return shippingMark;
	}


	public void setShippingMark(boolean shippingMark) {
		this.shippingMark = shippingMark;
	}


	public String getShippingOnlyNotes() {
		return shippingOnlyNotes;
	}


	public void setShippingOnlyNotes(String shippingOnlyNotes) {
		this.shippingOnlyNotes = shippingOnlyNotes;
	}


	public String getSplitShipSetBy() {
		return splitShipSetBy;
	}


	public void setSplitShipSetBy(String splitShipSetBy) {
		this.splitShipSetBy = splitShipSetBy;
	}


	public String getVariableDataBreakdown() {
		return variableDataBreakdown;
	}


	public void setVariableDataBreakdown(String variableDataBreakdown) {
		this.variableDataBreakdown = variableDataBreakdown;
	}
*/


	

}
