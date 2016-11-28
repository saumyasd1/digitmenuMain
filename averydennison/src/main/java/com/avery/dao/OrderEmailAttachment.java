package com.avery.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderEmailAttachment")
public class OrderEmailAttachment {
	
	int id;
	String orderEmailQueueID;
	String mailBodyData;
	String fileName;
	String fileExtension;
	 String fileType;
	 String fileContentType;
	 String fileData;
	 String additionalData;
	 String fileSchemaID;
	 String partnerDataStructureID;
	 String orderFileStructureID;
	 String groupID;
	 String additionalDataFileIDs;
	 String additionalDataFileKey;
	 String filePath;
	 String status;
	 Date createdDate;
	 String createdByName;
	 Date lastModifiedDate;
	 String lastModifiedByName;
	 String comment;
	 
	 public OrderEmailAttachment() {
      	 
	    }
	    public OrderEmailAttachment(String lastModifiedByName, String createdByName) {
	        this.lastModifiedByName = lastModifiedByName;
	        this.createdByName = createdByName;
	        
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
	@Column(name="orderEmailQueueID" , length=100)
	public String getOrderEmailQueueID() {
		return orderEmailQueueID;
	}
	public void setOrderEmailQueueID(String orderEmailQueueID) {
		this.orderEmailQueueID = orderEmailQueueID;
	}
	@Column(name="mailBodyData" , length=500)
	public String getMailBodyData() {
		return mailBodyData;
	}
	public void setMailBodyData(String mailBodyData) {
		this.mailBodyData = mailBodyData;
	}
	@Column(name="fileName" , length=100)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="fileExtension" , length=10)
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	@Column(name="fileType" , length=100)
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name="fileContentType" , length=100)
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	@Column(name="fileData" , length=100)
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	@Column(name="additionalData" , length=100)
	public String getAdditionalData() {
		return additionalData;
	}
	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}
	@Column(name="fileSchemaID" , length=100)
	public String getFileSchemaID() {
		return fileSchemaID;
	}
	public void setFileSchemaID(String fileSchemaID) {
		this.fileSchemaID = fileSchemaID;
	}
	@Column(name="partnerDataStructureID" , length=100)
	public String getPartnerDataStructureID() {
		return partnerDataStructureID;
	}
	public void setPartnerDataStructureID(String partnerDataStructureID) {
		this.partnerDataStructureID = partnerDataStructureID;
	}
	@Column(name="orderFileStructureID" , length=100)
	public String getOrderFileStructureID() {
		return orderFileStructureID;
	}
	public void setOrderFileStructureID(String orderFileStructureID) {
		this.orderFileStructureID = orderFileStructureID;
	}
	@Column(name="groupID" , length=100)
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	@Column(name="additionalDataFileIDs" , length=100)
	public String getAdditionalDataFileIDs() {
		return additionalDataFileIDs;
	}
	public void setAdditionalDataFileIDs(String additionalDataFileIDs) {
		this.additionalDataFileIDs = additionalDataFileIDs;
	}
	@Column(name="additionalDataFileKey" , length=100)
	public String getAdditionalDataFileKey() {
		return additionalDataFileKey;
	}
	public void setAdditionalDataFileKey(String additionalDataFileKey) {
		this.additionalDataFileKey = additionalDataFileKey;
	}
	@Column(name="filePath" , length=250)
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name="status" , length=100)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="createdDate" )
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="createdByName" , length=100)
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	@Column(name="lastModifiedDate" )
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@Column(name="lastModifiedByName" , length=100)
	public String getLastModifiedByName() {
		return lastModifiedByName;
	}
	public void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}
	@Column(name="comment" , length=250)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	 
	 
	 
	 
}
