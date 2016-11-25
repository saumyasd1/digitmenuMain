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
	 String isAdditionalData;
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
	public String getOrderEmailQueueID() {
		return orderEmailQueueID;
	}
	public void setOrderEmailQueueID(String orderEmailQueueID) {
		this.orderEmailQueueID = orderEmailQueueID;
	}
	public String getMailBodyData() {
		return mailBodyData;
	}
	public void setMailBodyData(String mailBodyData) {
		this.mailBodyData = mailBodyData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	public String getIsAdditionalData() {
		return isAdditionalData;
	}
	public void setIsAdditionalData(String isAdditionalData) {
		this.isAdditionalData = isAdditionalData;
	}
	public String getFileSchemaID() {
		return fileSchemaID;
	}
	public void setFileSchemaID(String fileSchemaID) {
		this.fileSchemaID = fileSchemaID;
	}
	public String getPartnerDataStructureID() {
		return partnerDataStructureID;
	}
	public void setPartnerDataStructureID(String partnerDataStructureID) {
		this.partnerDataStructureID = partnerDataStructureID;
	}
	public String getOrderFileStructureID() {
		return orderFileStructureID;
	}
	public void setOrderFileStructureID(String orderFileStructureID) {
		this.orderFileStructureID = orderFileStructureID;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getAdditionalDataFileIDs() {
		return additionalDataFileIDs;
	}
	public void setAdditionalDataFileIDs(String additionalDataFileIDs) {
		this.additionalDataFileIDs = additionalDataFileIDs;
	}
	public String getAdditionalDataFileKey() {
		return additionalDataFileKey;
	}
	public void setAdditionalDataFileKey(String additionalDataFileKey) {
		this.additionalDataFileKey = additionalDataFileKey;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	 
	 
	 
	 
}
