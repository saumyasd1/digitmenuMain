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
@Table(name="orderfileattachment")
public class OrderFileAttachment {
	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name = "id",nullable=false)
	 int id;
	@Column(name = "fileName", length = 250)
	String fileName;
	@Column(name = "fileExtension", length = 50)
	String fileExtension;
	@Column(name = "fileContentType", length = 1000)
	String fileContentType;
	@Column(name = "fileData", length = 100)
	String fileData;
	@Column(name = "additionalDataFileKey", length = 100)
	String additionalDataFileKey;
	@Column(name = "filePath", length=250)
	String filePath;
	@Column(name = "status", length = 100)
	String status;
	@Column(name = "createdDate")
	Date createdDate;
	@Column(name = "createdBy", length = 50)
	String createdBy;
	@Column(name = "lastModifiedDate")
	Date lastModifiedDate;
	@Column(name = "lastModifiedBy", length = 50)
	String lastModifiedBy;
	@Column(name = "comment", length = 250)
	String comment;
	@Column(name="error",length=50)
	String error;
	@Column(name="orderFileId")
	int orderFileId;
	
	@Column(name="productLineMatch",length=100)
	String productLineMatch;
	@Column(name="rboMatch",length=100)
	String rboMatch;
	@Column(name="fileContentMatch",length=100)
	String fileContentMatch;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "orderEmailQueueId")
	OrderEmailQueue varOrderEmailQueue;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "productLineId")
	Partner_RBOProductLine varProductLine;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "varOrderFileAttachment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<OrderFileQueue> listOrderFileQueue = new ArrayList<OrderFileQueue>();
	
	public OrderFileAttachment() {}
	
	public OrderFileAttachment(int orderEmailQueueID, String fileName, String fileExtension, String filePath, String contentType){
		//this.orderFileId = orderEmailQueueID;
    	this.fileName = fileName;
    	this.fileExtension = fileExtension;
    	this.filePath = filePath;
    	this.fileContentType = contentType;
    	this.createdBy = "Adeptia";
    	this.createdDate = new Date();
    	this.lastModifiedBy = "Adeptia";
    	this.lastModifiedDate = new Date();
    	this.status = "Unidentified";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getOrderFileId() {
		return orderFileId;
	}

	public void setOrderFileId(int orderFileId) {
		this.orderFileId = orderFileId;
	}

	public String getProductLineMatch() {
		return productLineMatch;
	}

	public void setProductLineMatch(String productLineMatch) {
		this.productLineMatch = productLineMatch;
	}

	public String getRboMatch() {
		return rboMatch;
	}

	public void setRboMatch(String rboMatch) {
		this.rboMatch = rboMatch;
	}

	public String getFileContentMatch() {
		return fileContentMatch;
	}

	public void setFileContentMatch(String fileContentMatch) {
		this.fileContentMatch = fileContentMatch;
	}

	public OrderEmailQueue getVarOrderEmailQueue() {
		return varOrderEmailQueue;
	}

	public void setVarOrderEmailQueue(OrderEmailQueue varOrderEmailQueue) {
		this.varOrderEmailQueue = varOrderEmailQueue;
	}

	public Partner_RBOProductLine getVarProductLine() {
		return varProductLine;
	}

	public void setVarProductLine(Partner_RBOProductLine varProductLine) {
		this.varProductLine = varProductLine;
	}

	public List<OrderFileQueue> getListOrderFileQueue() {
		return listOrderFileQueue;
	}

	public void setListOrderFileQueue(List<OrderFileQueue> listOrderFileQueue) {
		this.listOrderFileQueue = listOrderFileQueue;
	}

	
	
}
