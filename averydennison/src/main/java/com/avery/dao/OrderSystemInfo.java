package com.avery.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="ordersysteminfo")
public class OrderSystemInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	int id;
	@Column(name="artworkHold",length=5)
	String artworkHold;
	@Column(name="csrName",length=250)
	String csrName;
	@Column(name="manufacturingNotes",length=500)
	String manufacturingNotes;
	@Column(name="packingInstruction",length=500)
	String packingInstruction;
	@Column(name="shippingMark",length=500)
	String shippingMark;
	@Column(name="discountOffer")
	boolean discountOffer;
	@Column(name="invoiceNote",length=500)
	String invoiceNote;
	@Column(name="splitShipSetBy",length=5)
	String splitShipSetBy;
	@Column(name="variableDataBreakdown",length=500)
	String variableDataBreakdown;
	@Column(name="createdBy",length=50)
	String createdBy;
	@Column(name="createdDate")
	Date createdDate;
	@Column(name="lastModifiedBy",length=50)
	String lastModifiedBy;
	@Column(name="lastModifiedDate")
	Date lastModifiedDate;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="productLineId")
	Partner_RBOProductLine varProductLine;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="systemId")
	SystemInfo varSystemInfo;
	@OneToMany(mappedBy="varOrderSystemInfo",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<OrgInfo> listOrgInfo=new ArrayList<OrgInfo>();
	
	
	public OrderSystemInfo() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getArtworkHold() {
		return artworkHold;
	}


	public void setArtworkHold(String artworkHold) {
		this.artworkHold = artworkHold;
	}


	public String getCsrName() {
		return csrName;
	}


	public void setCsrName(String csrName) {
		this.csrName = csrName;
	}


	public String getManufacturingNotes() {
		return manufacturingNotes;
	}


	public void setManufacturingNotes(String manufacturingNotes) {
		this.manufacturingNotes = manufacturingNotes;
	}


	public String getPackingInstruction() {
		return packingInstruction;
	}


	public void setPackingInstruction(String packingInstruction) {
		this.packingInstruction = packingInstruction;
	}


	public String getShippingMark() {
		return shippingMark;
	}


	public void setShippingMark(String shippingMark) {
		this.shippingMark = shippingMark;
	}


	public boolean isDiscountOffer() {
		return discountOffer;
	}


	public void setDiscountOffer(boolean discountOffer) {
		this.discountOffer = discountOffer;
	}


	public String getInvoiceNote() {
		return invoiceNote;
	}


	public void setInvoiceNote(String invoiceNote) {
		this.invoiceNote = invoiceNote;
	}


	public String getSplitShipSetBy() {
		return splitShipSetBy;
	}


	public void setSplitShipSetBy(String splitShipSetBy) {
		this.splitShipSetBy = splitShipSetBy;
	}


	public String getVariableDataBreakdown() {
		return variableDataBreakdown;
	}


	public void setVariableDataBreakdown(String variableDataBreakdown) {
		this.variableDataBreakdown = variableDataBreakdown;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getLastModifiedBy() {
		return lastModifiedBy;
	}


	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public Partner_RBOProductLine getVarProductLine() {
		return varProductLine;
	}


	public void setVarProductLine(Partner_RBOProductLine varProductLine) {
		this.varProductLine = varProductLine;
	}

	public SystemInfo getVarSystemInfo() {
		return varSystemInfo;
	}


	public void setVarSystemInfo(SystemInfo varSystemInfo) {
		this.varSystemInfo = varSystemInfo;
	}


	public List<OrgInfo> getListOrgInfo() {
		return listOrgInfo;
	}


	public void setListOrgInfo(List<OrgInfo> listOrgInfo) {
		this.listOrgInfo = listOrgInfo;
	}


	
	
}
