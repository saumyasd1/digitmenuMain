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
	@Column(name="poNumber",length=1000)
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


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
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


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public Date getFeedbackAcknowledgementDate() {
		return feedbackAcknowledgementDate;
	}


	public void setFeedbackAcknowledgementDate(Date feedbackAcknowledgementDate) {
		this.feedbackAcknowledgementDate = feedbackAcknowledgementDate;
	}


	public Partner_RBOProductLine getVarProductLine() {
		return varProductLine;
	}


	public void setVarProductLine(Partner_RBOProductLine varProductLine) {
		this.varProductLine = varProductLine;
	}


	public OrderFileAttachment getVarOrderFileAttachment() {
		return varOrderFileAttachment;
	}


	public void setVarOrderFileAttachment(OrderFileAttachment varOrderFileAttachment) {
		this.varOrderFileAttachment = varOrderFileAttachment;
	}


	public List<OrderLine> getListOrderLine() {
		return listOrderLine;
	}


	public void setListOrderLine(List<OrderLine> listOrderLine) {
		this.listOrderLine = listOrderLine;
	}


	public List<SalesOrderLine> getListSalesOrderLine() {
		return listSalesOrderLine;
	}


	public void setListSalesOrderLine(List<SalesOrderLine> listSalesOrderLine) {
		this.listSalesOrderLine = listSalesOrderLine;
	}

	

	/*public List<AuditTrail> getListAuditTrail() {
		return listAuditTrail;
	}

	public void setListAuditTrail(List<AuditTrail> listAuditTrail) {
		this.listAuditTrail = listAuditTrail;
	}
*/
	

	
	
	
	 
}
