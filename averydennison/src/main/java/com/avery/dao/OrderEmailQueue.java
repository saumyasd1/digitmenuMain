package com.avery.dao;

import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Column(name = "mailBody", length = 1000)
	String mailBody;
	@Column(name = "orderMail")
	Boolean orderMail;
	@Column(name = "assignee", length = 100)
	String assignee;
	@Column(name = "status", length = 100)
	String status;
	
	@Column(name = "receivedDate")
	@Temporal(TemporalType.TIMESTAMP)
	Date receivedDate;
	@Column(name = "readDate")
	@Temporal(TemporalType.TIMESTAMP)
	Date readDate;
	@Column(name = "acknowledgementDate")
	@Temporal(TemporalType.TIMESTAMP)
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
	@OneToMany(mappedBy = "varOrderEmailQueue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<OrderFileAttachment> varOrderFileAttachment = new ArrayList<OrderFileAttachment>();
	///new fields added modifed in length///
	@Column(name = "emailSubjectProductLineMatch", length = 250)
	String emailSubjectProductLineMatch;
	@Column(name = "emailSubjectRBOMatch", length = 250)
	String emailSubjectRBOMatch;
	@Column(name = "emailBodyProductLineMatch", length = 100)
	String emailBodyProductLineMatch;
	@Column(name = "emailBodyRBOMatch", length = 100)
	String emailBodyRBOMatch;
	@Column(name = "emailSubjectPartnerMatch", length = 250)
	String emailSubjectPartnerMatch;
	//////end/////////////
	@Column(name = "siteId")
	int siteId;
	
	public OrderEmailQueue() {
	}

	public OrderEmailQueue(String subject, String sender, String mailBody, Date receivedDate, Date readDate, String cc, String to, String assignCSR) {
		this.subject = subject;
		this.senderEmailId = sender;
//		this.mailBody = mailBody;
		this.receivedDate = receivedDate;
		this.readDate = readDate;
		this.ccMailId = cc;
		this.toMailId = to;
		this.createdBy = "Adeptia";
		this.createdDate = new Date();
		this.lastModifiedBy = "Adeptia";
		this.lastModifiedDate = new Date();
		this.orderSource = "Email";
//		this.status = "1";
		this.assignCSR = assignCSR;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
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

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public Boolean isOrderMail() {
		return orderMail;
	}

	public void setOrderMail(Boolean orderMail) {
		this.orderMail = orderMail;
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

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Date getAcknowledgementDate() {
		return acknowledgementDate;
	}

	public void setAcknowledgementDate(Date acknowledgementDate) {
		this.acknowledgementDate = acknowledgementDate;
	}

	public String getToMailId() {
		return toMailId;
	}

	public void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}

	public String getCcMailId() {
		return ccMailId;
	}

	public void setCcMailId(String ccMailId) {
		this.ccMailId = ccMailId;
	}

	public String getAssignCSR() {
		return assignCSR;
	}

	public void setAssignCSR(String assignCSR) {
		this.assignCSR = assignCSR;
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

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public List<OrderFileAttachment> getVarOrderFileAttachment() {
		return varOrderFileAttachment;
	}

	public void setVarOrderFileAttachment(
			List<OrderFileAttachment> varOrderFileAttachment) {
		this.varOrderFileAttachment = varOrderFileAttachment;
	}

	public String getEmailSubjectProductLineMatch() {
		return emailSubjectProductLineMatch;
	}

	public void setEmailSubjectProductLineMatch(String emailSubjectProductLineMatch) {
		this.emailSubjectProductLineMatch = emailSubjectProductLineMatch;
	}

	public String getEmailSubjectRBOMatch() {
		return emailSubjectRBOMatch;
	}

	public void setEmailSubjectRBOMatch(String emailSubjectRBOMatch) {
		this.emailSubjectRBOMatch = emailSubjectRBOMatch;
	}

	public String getEmailBodyProductLineMatch() {
		return emailBodyProductLineMatch;
	}

	public void setEmailBodyProductLineMatch(String emailBodyProductLineMatch) {
		this.emailBodyProductLineMatch = emailBodyProductLineMatch;
	}

	public String getEmailBodyRBOMatch() {
		return emailBodyRBOMatch;
	}

	public void setEmailBodyRBOMatch(String emailBodyRBOMatch) {
		this.emailBodyRBOMatch = emailBodyRBOMatch;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	////new getter setter for new field/////////////
	public String getEmailSubjectPartnerMatch() {
		return emailSubjectPartnerMatch;
	}

	public void setEmailSubjectPartnerMatch(String emailSubjectPartnerMatch) {
		this.emailSubjectPartnerMatch = emailSubjectPartnerMatch;
	}
	
	
}
