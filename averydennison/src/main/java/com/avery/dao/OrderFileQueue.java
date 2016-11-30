package com.avery.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderFileQueue")
public class OrderFileQueue {
	@Id 
    @GeneratedValue 
    @Column(name = "id",nullable=false)
	 int id;
	@Column(name="pId",length=100)
	 String pId;
	@Column(name="partnerId",nullable=false)
	 int partnerId;
	@Column(name="rboName",length=250)
	 String rboName;
	@Column(name="productLineId",nullable=false)
	 int productLineId;
	@Column(name="senderEmailId",length=100)
	 String senderEmailId;
	@Column(name="subject",length=50)
	 String subject;
	@Column(name="emailBody",length=250)
	 String emailBody;
	@Column(name="orderEmailAttachmentId",nullable=false)
	 int orderEmailAttachmentId;
	@Column(name="orderSource",length=50)
	 String orderSource;
	@Column(name="submittedBy",length=50)
	 String submittedBy;
	@Column(name="submittedDate")
	 Date submittedDate;
	@Column(name="status",length=100)
	 String status;
	@Column(name="error",length=50)
	 String error;
	@Column(name="createdDate")
	 Date createdDate;
	@Column(name="createdByName",length=50)
	 String createdByName;
	@Column(name="lastModifiedDate")
	 Date lastModifiedDate;
	@Column(name="lastModifiedByName",length=50)
	 String lastModifiedByName;
	@Column(name="receivedDate")
	 Date receivedDate;
	@Column(name="comment",length=100)
	 String comment;
	@Column(name="rboId",length=50,nullable=false)
	 String rboId;
	@Column(name="poNumber",length=100)
	 String poNumber;
	@Column(name="prevOrderQueueId")
	 int prevOrderQueueId;
	@Column(name="acknowledgementDate")
	 Date acknowledgementDate;
	 
	
	
	 
	public OrderFileQueue(String pid, String rboName) {
		this.pId = pid;
		this.rboName = rboName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getpId() {
		return pId;
	}


	public void setpId(String pId) {
		this.pId = pId;
	}


	public int getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}


	public String getRboName() {
		return rboName;
	}


	public void setRboName(String rboName) {
		this.rboName = rboName;
	}


	public int getProductLineId() {
		return productLineId;
	}


	public void setProductLineId(int productLineId) {
		this.productLineId = productLineId;
	}


	public String getSenderEmailId() {
		return senderEmailId;
	}


	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getEmailBody() {
		return emailBody;
	}


	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}


	public int getOrderEmailAttachmentId() {
		return orderEmailAttachmentId;
	}


	public void setOrderEmailAttachmentId(int orderEmailAttachmentId) {
		this.orderEmailAttachmentId = orderEmailAttachmentId;
	}


	public String getOrderSource() {
		return orderSource;
	}


	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}


	public String getSubmittedBy() {
		return submittedBy;
	}


	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}


	public Date getSubmittedDate() {
		return submittedDate;
	}


	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
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


	public Date getReceivedDate() {
		return receivedDate;
	}


	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getRboId() {
		return rboId;
	}


	public void setRboId(String rboId) {
		this.rboId = rboId;
	}


	public String getPoNumber() {
		return poNumber;
	}


	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}


	public int getPrevOrderQueueId() {
		return prevOrderQueueId;
	}


	public void setPrevOrderQueueId(int prevOrderQueueId) {
		this.prevOrderQueueId = prevOrderQueueId;
	}


	public Date getAcknowledgementDate() {
		return acknowledgementDate;
	}


	public void setAcknowledgementDate(Date acknowledgementDate) {
		this.acknowledgementDate = acknowledgementDate;
	}

	
	 
}
