package com.avery.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "orderfilequeue")
public class OrderFileQueue {
	@Id 
    @GeneratedValue 
    @Column(name = "id",nullable=false)
	 int id;
	@Column(name="pId",length=50)
	 String pId;
	@Column(name="subject",length=50)
	 String subject;
	@Column(name="submittedBy",length=50)
	 String submittedBy;
	@Column(name="submittedDate")
	 Date submittedDate;
	@Column(name="status",length=100)
	 String status;
	@Column(name="createdDate")
	 Date createdDate;
	@Column(name="createdBy",length=50)
	 String createdBy;
	@Column(name="lastModifiedDate")
	Date lastModifiedDate;
	@Column(name="lastModifiedBy",length=50)
	 String lastModifiedBy;
	@Column(name="comment",length=250)
	 String comment;
	@Column(name="poNumber",length=50)
	 String poNumber;
	@Column(name="prevOrderQueueId")
	 int prevOrderQueueId;
	@Column(name="error",length=1000)
	String error;
	@Column(name="feedbackAcknowledgementDate")
	Date feedbackAcknowledgementDate;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="productLineId")
	Partner_RBOProductLine varProductLine;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="orderFileAttachmentId")
	OrderFileAttachment varOrderFileAttachment;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="varOrderFileQueue",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<OrderLine> listOrderLine=new ArrayList<OrderLine>();
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="varOrderFileQueue",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<SalesOrderLine> listSalesOrderLine=new ArrayList<SalesOrderLine>();
/*	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="varOrderFileQueue",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<AuditTrail> listAuditTrail=new ArrayList<AuditTrail>();*/
	
	
	public OrderFileQueue() {}

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	String getpId() {
		return pId;
	}

	void setpId(String pId) {
		this.pId = pId;
	}

	String getSubject() {
		return subject;
	}

	void setSubject(String subject) {
		this.subject = subject;
	}

	String getSubmittedBy() {
		return submittedBy;
	}

	void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	Date getSubmittedDate() {
		return submittedDate;
	}

	void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
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

	String getPoNumber() {
		return poNumber;
	}

	void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	int getPrevOrderQueueId() {
		return prevOrderQueueId;
	}

	void setPrevOrderQueueId(int prevOrderQueueId) {
		this.prevOrderQueueId = prevOrderQueueId;
	}

	String getError() {
		return error;
	}

	void setError(String error) {
		this.error = error;
	}

	Date getFeedbackAcknowledgementDate() {
		return feedbackAcknowledgementDate;
	}

	void setFeedbackAcknowledgementDate(Date feedbackAcknowledgementDate) {
		this.feedbackAcknowledgementDate = feedbackAcknowledgementDate;
	}

	Partner_RBOProductLine getVarProductLine() {
		return varProductLine;
	}

	void setVarProductLine(Partner_RBOProductLine varProductLine) {
		this.varProductLine = varProductLine;
	}

	OrderFileAttachment getVarOrderFileAttachment() {
		return varOrderFileAttachment;
	}

	void setVarOrderFileAttachment(OrderFileAttachment varOrderFileAttachment) {
		this.varOrderFileAttachment = varOrderFileAttachment;
	}

	List<OrderLine> getListOrderLine() {
		return listOrderLine;
	}

	void setListOrderLine(List<OrderLine> listOrderLine) {
		this.listOrderLine = listOrderLine;
	}

	List<SalesOrderLine> getListSalesOrderLine() {
		return listSalesOrderLine;
	}

	void setListSalesOrderLine(List<SalesOrderLine> listSalesOrderLine) {
		this.listSalesOrderLine = listSalesOrderLine;
	}

	/*List<AuditTrail> getListAuditTrail() {
		return listAuditTrail;
	}

	void setListAuditTrail(List<AuditTrail> listAuditTrail) {
		this.listAuditTrail = listAuditTrail;
	}
*/
	

	
	
	
	 
}
