package com.avery.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderEmailQueue")
public class OrderFileQueue {
	@Id 
    @GeneratedValue 
    @Column(name = "id")
	 int id;
	@Column(name="pId",length=50)
	 String pId;
	@Column(name="partnerId",length=50)
	 String partnerId;
	@Column(name="rboName",length=50)
	 String rboName;
	@Column(name="productLineId")
	 int productLineId;
	@Column(name="senderEmailId",length=50)
	 String senderEmailId;
	@Column(name="subject",length=50)
	 String subject;
	@Column(name="emailBody",length=250)
	 String emailBody;
	@Column(name="orderEmailQueueId",length=50)
	 String orderEmailQueueId;
	@Column(name="orderSource",length=50)
	 String orderSource;
	@Column(name="submittedBy",length=50)
	 String submittedBy;
	@Column(name="submittedDate",length=50)
	 String submittedDate;
	@Column(name="status",length=50)
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
	@Column(name="rboId",length=50)
	 String rboId;
	@Column(name="poNumber",length=100)
	 String poNumber;
	@Column(name="prevOrderQueueId",length=50)
	 String prevOrderQueueId;
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


	public String getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(String partnerId) {
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


	public String getOrderEmailQueueId() {
		return orderEmailQueueId;
	}


	public void setOrderEmailQueueId(String orderEmailQueueId) {
		this.orderEmailQueueId = orderEmailQueueId;
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


	public String getSubmittedDate() {
		return submittedDate;
	}


	public void setSubmittedDate(String submittedDate) {
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


	public String getPrevOrderQueueId() {
		return prevOrderQueueId;
	}


	public void setPrevOrderQueueId(String prevOrderQueueId) {
		this.prevOrderQueueId = prevOrderQueueId;
	}


	public Date getAcknowledgementDate() {
		return acknowledgementDate;
	}


	public void setAcknowledgementDate(Date acknowledgementDate) {
		this.acknowledgementDate = acknowledgementDate;
	}
	
	
	
	 
}
