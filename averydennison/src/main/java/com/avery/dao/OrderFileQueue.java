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

	 int id;
	 String pid;
	 String partnerID;
	 String rboName;
	 String productLineId;
	 String senderEmailID;
	 String subject;
	 String emailBody;
	 String orderEmailQueueID;
	 String orderSource;
	 String submittedBy;
	 String submittedDate;
	 String status;
	 String error;
	 String createdDate;
	 String createdByName;
	 Date lastModifiedDate;
	 String lastModifiedByName;
	 String receivedDate;
	 String comment;
	 String rboID;
	 String poNumber;
	 String prevOrderQueueID;
	 Date acknowledgementDate;
	 
	 
	public OrderFileQueue(String pid, String rboName) {
		this.pid = pid;
		this.rboName = rboName;
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	public String getRboName() {
		return rboName;
	}
	public void setRboName(String rboName) {
		this.rboName = rboName;
	}
	public String getProductLineId() {
		return productLineId;
	}
	public void setProductLineId(String productLineId) {
		this.productLineId = productLineId;
	}
	public String getSenderEmailID() {
		return senderEmailID;
	}
	public void setSenderEmailID(String senderEmailID) {
		this.senderEmailID = senderEmailID;
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
	public String getOrderEmailQueueID() {
		return orderEmailQueueID;
	}
	public void setOrderEmailQueueID(String orderEmailQueueID) {
		this.orderEmailQueueID = orderEmailQueueID;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
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
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRboID() {
		return rboID;
	}
	public void setRboID(String rboID) {
		this.rboID = rboID;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getPrevOrderQueueID() {
		return prevOrderQueueID;
	}
	public void setPrevOrderQueueID(String prevOrderQueueID) {
		this.prevOrderQueueID = prevOrderQueueID;
	}
	public Date getAcknowledgementDate() {
		return acknowledgementDate;
	}
	public void setAcknowledgementDate(Date acknowledgementDate) {
		this.acknowledgementDate = acknowledgementDate;
	}
	 
	 
}
