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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orderemailqueue")
public class OrderEmailQueue {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	int id;
	@Column(name = "processId", length = 100)
	String processId;
	@Column(name = "senderEmailId", length = 100)
	String senderEmailId;
	@Column(name = "subject", length = 250)
	String subject;
	@Column(name = "mailBody", length = 500)
	String mailBody;
	@Column(name = "orderMail")
	boolean orderMail;
	@Column(name = "assignee", length = 100)
	String assignee;
	@Column(name = "status", length = 100)
	String status;
	@Column(name = "receivedDate")
	Date receivedDate;
	@Column(name = "readDate")
	Date readDate;
	@Column(name = "acknowledgementDate")
	Date acknowledgementDate;
	@Column(name = "toMailId", length = 100)
	String toMailId;
	@Column(name = "ccMailId", length = 100)
	String ccMailId;
	@Column(name = "assignCSR", length = 100)
	String assignCSR;
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
	@Column(name = "orderSource", length = 50)
	String orderSource;
	
	@Column(name = "emailSubjectProductLineMatch", length = 100)
	String emailSubjectProductLineMatch;
	@Column(name = "emailSubjectRBOMatch", length = 100)
	String emailSubjectRBOMatch;
	@Column(name = "emailBodyProductLineMatch", length = 100)
	String emailBodyProductLineMatch;
	@Column(name = "emailBodyRBOMatch", length = 100)
	String emailBodyRBOMatch;
	
	@OneToMany(mappedBy = "varOrderEmailQueue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<OrderFileAttachment> listOrderFileAttachment = new ArrayList<OrderFileAttachment>();

	public OrderEmailQueue() {

	}

	public OrderEmailQueue(String subject, String sender, String mailBody) {
		this.subject = subject;
		this.senderEmailId = sender;
		this.mailBody = mailBody;
	}

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	String getProcessId() {
		return processId;
	}

	void setProcessId(String processId) {
		this.processId = processId;
	}

	String getSenderEmailId() {
		return senderEmailId;
	}

	void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}

	String getSubject() {
		return subject;
	}

	void setSubject(String subject) {
		this.subject = subject;
	}

	String getMailBody() {
		return mailBody;
	}

	void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	boolean isOrderMail() {
		return orderMail;
	}

	void setOrderMail(boolean orderMail) {
		this.orderMail = orderMail;
	}

	String getAssignee() {
		return assignee;
	}

	void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	String getStatus() {
		return status;
	}

	void setStatus(String status) {
		this.status = status;
	}

	Date getReceivedDate() {
		return receivedDate;
	}

	void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	Date getReadDate() {
		return readDate;
	}

	void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	Date getAcknowledgementDate() {
		return acknowledgementDate;
	}

	void setAcknowledgementDate(Date acknowledgementDate) {
		this.acknowledgementDate = acknowledgementDate;
	}

	String getToMailId() {
		return toMailId;
	}

	void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}

	String getCcMailId() {
		return ccMailId;
	}

	void setCcMailId(String ccMailId) {
		this.ccMailId = ccMailId;
	}

	String getAssignCSR() {
		return assignCSR;
	}

	void setAssignCSR(String assignCSR) {
		this.assignCSR = assignCSR;
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

	String getOrderSource() {
		return orderSource;
	}

	void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	String getEmailSubjectProductLineMatch() {
		return emailSubjectProductLineMatch;
	}

	void setEmailSubjectProductLineMatch(String emailSubjectProductLineMatch) {
		this.emailSubjectProductLineMatch = emailSubjectProductLineMatch;
	}

	String getEmailSubjectRBOMatch() {
		return emailSubjectRBOMatch;
	}

	void setEmailSubjectRBOMatch(String emailSubjectRBOMatch) {
		this.emailSubjectRBOMatch = emailSubjectRBOMatch;
	}

	String getEmailBodyProductLineMatch() {
		return emailBodyProductLineMatch;
	}

	void setEmailBodyProductLineMatch(String emailBodyProductLineMatch) {
		this.emailBodyProductLineMatch = emailBodyProductLineMatch;
	}

	String getEmailBodyRBOMatch() {
		return emailBodyRBOMatch;
	}

	void setEmailBodyRBOMatch(String emailBodyRBOMatch) {
		this.emailBodyRBOMatch = emailBodyRBOMatch;
	}

	List<OrderFileAttachment> getListOrderFileAttachment() {
		return listOrderFileAttachment;
	}

	void setListOrderFileAttachment(
			List<OrderFileAttachment> listOrderFileAttachment) {
		this.listOrderFileAttachment = listOrderFileAttachment;
	}

	
	
}
