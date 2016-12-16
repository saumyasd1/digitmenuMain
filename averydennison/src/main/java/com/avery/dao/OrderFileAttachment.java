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
	@Column(name = "fileContentType", length = 100)
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

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	String getFileName() {
		return fileName;
	}

	void setFileName(String fileName) {
		this.fileName = fileName;
	}

	String getFileExtension() {
		return fileExtension;
	}

	void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	String getFileContentType() {
		return fileContentType;
	}

	void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	String getFileData() {
		return fileData;
	}

	void setFileData(String fileData) {
		this.fileData = fileData;
	}

	String getAdditionalDataFileKey() {
		return additionalDataFileKey;
	}

	void setAdditionalDataFileKey(String additionalDataFileKey) {
		this.additionalDataFileKey = additionalDataFileKey;
	}

	String getFilePath() {
		return filePath;
	}

	void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	String getStatus() {
		return status;
	}

	void setStatus(String status) {
		this.status = status;
	}

	Date getCreatedDate() {
		return createdDate;
	}

	void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	String getCreatedBy() {
		return createdBy;
	}

	void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	String getLastModifiedBy() {
		return lastModifiedBy;
	}

	void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	String getComment() {
		return comment;
	}

	void setComment(String comment) {
		this.comment = comment;
	}

	String getError() {
		return error;
	}

	void setError(String error) {
		this.error = error;
	}

	int getOrderFileId() {
		return orderFileId;
	}

	void setOrderFileId(int orderFileId) {
		this.orderFileId = orderFileId;
	}

	String getProductLineMatch() {
		return productLineMatch;
	}

	void setProductLineMatch(String productLineMatch) {
		this.productLineMatch = productLineMatch;
	}

	String getRboMatch() {
		return rboMatch;
	}

	void setRboMatch(String rboMatch) {
		this.rboMatch = rboMatch;
	}

	String getFileContentMatch() {
		return fileContentMatch;
	}

	void setFileContentMatch(String fileContentMatch) {
		this.fileContentMatch = fileContentMatch;
	}

	OrderEmailQueue getVarOrderEmailQueue() {
		return varOrderEmailQueue;
	}

	void setVarOrderEmailQueue(OrderEmailQueue varOrderEmailQueue) {
		this.varOrderEmailQueue = varOrderEmailQueue;
	}

	Partner_RBOProductLine getVarProductLine() {
		return varProductLine;
	}

	void setVarProductLine(Partner_RBOProductLine varProductLine) {
		this.varProductLine = varProductLine;
	}

	List<OrderFileQueue> getListOrderFileQueue() {
		return listOrderFileQueue;
	}

	void setListOrderFileQueue(List<OrderFileQueue> listOrderFileQueue) {
		this.listOrderFileQueue = listOrderFileQueue;
	}
	
	
	
	
	
	
}
