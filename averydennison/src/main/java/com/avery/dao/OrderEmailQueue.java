package com.avery.dao;

import java.util.Date;

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
	 boolean orderMail;
	 String assignee;
	 String status;
	 Date receivedDate;
	 Date readDate;
	 Date acknowledgementDate;
	 String tOMailID;
	 String ccMailID;
	 String assignCSR;
	 Date createdDate;
	 String createdByName;
	 Date lastModifiedDate;
	 String lastModifiedByName;
	 String comment;
	 
	 
	public OrderEmailQueue(){
		
	}
	 
	public OrderEmailQueue(String subject, String sender, String mailBody) {
		this.subject = subject;
		this.senderEmailID = sender;
		this.mailBody = mailBody;
	}
	
	@Id 
    @GeneratedValue 
    @Column(name = "id",nullable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="processID" , length=100)
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
	@Column(name="senderEmailID" , length=100)
	public String getSenderEmailID() {
		return senderEmailID;
	}
	public void setSenderEmailID(String senderEmailID) {
		this.senderEmailID = senderEmailID;
	}
	@Column(name="subject" , length=250)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Column(name="mailBody" , length=500)
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	@Column(name="orderMail" )
	public boolean getOrderMail() {
		return orderMail;
	}
	public void setOrderMail(boolean orderMail) {
		this.orderMail = orderMail;
	}
	@Column(name="assignee" , length=100)
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	@Column(name="status" , length=100)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="receivedDate")
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	@Column(name="readDate")
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	@Column(name="acknowledgementDate" )
	public Date getAcknowledgementDate() {
		return acknowledgementDate;
	}
	public void setAcknowledgementDate(Date acknowledgementDate) {
		this.acknowledgementDate = acknowledgementDate;
	}
	@Column(name="tOMailID" , length=100)
	public String gettOMailID() {
		return tOMailID;
	}
	public void settOMailID(String tOMailID) {
		this.tOMailID = tOMailID;
	}
	@Column(name="ccMailID" , length=100)
	public String getCcMailID() {
		return ccMailID;
	}
	public void setCcMailID(String ccMailID) {
		this.ccMailID = ccMailID;
	}
	@Column(name="assignCSR" , length=100)
	public String getAssignCSR() {
		return assignCSR;
	}
	public void setAssignCSR(String assignCSR) {
		this.assignCSR = assignCSR;
	}
	@Column(name="createdDate" )
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="createdByName" , length=50)
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
	@Column(name="lastModifiedByName" , length=50)
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
