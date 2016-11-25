package com.avery.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderEmailQueue")
public class OrderEmailQueue {
	
	 int id;
	 String processID;
	 String senderEmailID;
	 String subject;
	 String mailBody;
	 String isOrderMail;
	 String assignee;
	 String status;
	 String receivedDate;
	 String readDate;
	 String acknowledgementDate;
	 String tOMailID;
	 String ccMailID;
	 String assignCSR;
	 String createdDate;
	 String createdByName;
	 String lastModifiedDate;
	 String lastModifiedByName;
	 String comment;
	 
	 
	public OrderEmailQueue(String subject, String mailBody) {
		this.subject = subject;
		this.mailBody = mailBody;
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
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
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
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getIsOrderMail() {
		return isOrderMail;
	}
	public void setIsOrderMail(String isOrderMail) {
		this.isOrderMail = isOrderMail;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getReadDate() {
		return readDate;
	}
	public void setReadDate(String readDate) {
		this.readDate = readDate;
	}
	public String getAcknowledgementDate() {
		return acknowledgementDate;
	}
	public void setAcknowledgementDate(String acknowledgementDate) {
		this.acknowledgementDate = acknowledgementDate;
	}
	public String gettOMailID() {
		return tOMailID;
	}
	public void settOMailID(String tOMailID) {
		this.tOMailID = tOMailID;
	}
	public String getCcMailID() {
		return ccMailID;
	}
	public void setCcMailID(String ccMailID) {
		this.ccMailID = ccMailID;
	}
	public String getAssignCSR() {
		return assignCSR;
	}
	public void setAssignCSR(String assignCSR) {
		this.assignCSR = assignCSR;
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
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
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
