package com.avery.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partnerDataStructure")
public class PartnerDataStructure {
	int id;
	int partnerdatastructureid;
	int partnerid;
	int rboid;
	String rborName;
   	String site;
    String orgcode;
    String csrprimaryemail;
    String csrsecondrygroupemail;
    String productlinetype;
    String defaultbilltocode;
    String defaultshiptocode;
    String invoicelineinstruction;
    String packinginstruction;
    String variabledatabreakdown;
    String manufacturingnotes;
    String shippingonlynotes;
    String splitshipsetby;
    String artworkhold;
    String misccsrinstruction;
    String iswaivemoa;
    String iswaivemoq;
    String issizecheck;
    String isdiscountpricecheck;
    String isfabriccheck;
    String isshipmark;
    String isllkkcheck;
    String islocalbillingCheck;
    String isfactorytransfercheck;
    String isshipmentsample;
    String orderfilenamepatern;
    String orderfilenameextension;
    String orderfileschemaid;
    String orderfilemappingid;
    String isorderhasinternalitems;
    String isorderhaslocalitems;
    String IsOracleItem;
    String IsTrimItem;
    String IsSparrowItem;
    String IsADRequired;
    String ADFileNamePattern_1;
    String ADFileNameExtension_1;
    String ADFileSchemaID_1;
    String ADMappingID_1;
    String ADFileNamePattern_2;
    String ADFileNameExtension_2;
    String ADFileSchemaID_2;
    String ADMappingID_2;
    String ADFileNamePattern_3;
    String ADFileNameExtension_3;
    String ADFileSchemaID_3;
    String ADMappingID_3;
    String ADFileNamePattern_4;
    String ADFileNameExtension_4;
    String ADFileSchemaID_4;
    String ADMappingID_4;
    String EmailSubjectRBOMatch;
    String EmailSubjectRBOMatchLocation;
    String IsEmailSubjectRBOMatchRequired;
    String EmailSubjectProductLineMatch;
    String EmailSubjectProductLineMatchLocation;
    String IsEmailSubjectProductLineMatchRequired;
    String FileRBOMatch;
    String FileMatchLocation;
    String IsFileMatchRequired;
    String FileProductLineMatch;
    String FileProductLineMatchLocation;
    String IsFileProductLineMatchRequired;
    String FileOrderMatch;
    String FileOrderMatchLocation;
    String IsFileOrderMatchRequired;
    String ADFileRBOMatch;
    String FileADMatchLocation;
    String IsADFileMatchRequired;
    String FileADProductLineMatch;
    String ADFileProductLineMatchLocation;
    String IsADFileProductLineMatchRequired;
    String ADFileOrderMatch;
    String ADFileOrderMatchLocation;
    String IsADFileOrderMatchRequired;
    String IsActive;
    Date CreatedDate;
    String CreatedByName;
    Date LastModifiedDate;
    String LastModifiedByName;
    String Comment;
    
    
    public PartnerDataStructure() {
      	 
    }
    public PartnerDataStructure(String rborName, String site) {
        this.rborName = rborName;
        this.site = site;
        
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
    public int getPartnerdatastructureid() {
		return partnerdatastructureid;
	}
	public void setPartnerdatastructureid(int partnerdatastructureid) {
		this.partnerdatastructureid = partnerdatastructureid;
	}
	public int getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(int partnerid) {
		this.partnerid = partnerid;
	}
	public int getRboid() {
		return rboid;
	}
	public void setRboid(int rboid) {
		this.rboid = rboid;
	}
	public String getRborName() {
		return rborName;
	}
	public void setRborName(String rborName) {
		this.rborName = rborName;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getCsrprimaryemail() {
		return csrprimaryemail;
	}
	public void setCsrprimaryemail(String csrprimaryemail) {
		this.csrprimaryemail = csrprimaryemail;
	}
	public String getCsrsecondrygroupemail() {
		return csrsecondrygroupemail;
	}
	public void setCsrsecondrygroupemail(String csrsecondrygroupemail) {
		this.csrsecondrygroupemail = csrsecondrygroupemail;
	}
	public String getProductlinetype() {
		return productlinetype;
	}
	public void setProductlinetype(String productlinetype) {
		this.productlinetype = productlinetype;
	}
	public String getDefaultbilltocode() {
		return defaultbilltocode;
	}
	public void setDefaultbilltocode(String defaultbilltocode) {
		this.defaultbilltocode = defaultbilltocode;
	}
	public String getDefaultshiptocode() {
		return defaultshiptocode;
	}
	public void setDefaultshiptocode(String defaultshiptocode) {
		this.defaultshiptocode = defaultshiptocode;
	}
	public String getInvoicelineinstruction() {
		return invoicelineinstruction;
	}
	public void setInvoicelineinstruction(String invoicelineinstruction) {
		this.invoicelineinstruction = invoicelineinstruction;
	}
	public String getPackinginstruction() {
		return packinginstruction;
	}
	public void setPackinginstruction(String packinginstruction) {
		this.packinginstruction = packinginstruction;
	}
	public String getVariabledatabreakdown() {
		return variabledatabreakdown;
	}
	public void setVariabledatabreakdown(String variabledatabreakdown) {
		this.variabledatabreakdown = variabledatabreakdown;
	}
	public String getManufacturingnotes() {
		return manufacturingnotes;
	}
	public void setManufacturingnotes(String manufacturingnotes) {
		this.manufacturingnotes = manufacturingnotes;
	}
	public String getShippingonlynotes() {
		return shippingonlynotes;
	}
	public void setShippingonlynotes(String shippingonlynotes) {
		this.shippingonlynotes = shippingonlynotes;
	}
	public String getSplitshipsetby() {
		return splitshipsetby;
	}
	public void setSplitshipsetby(String splitshipsetby) {
		this.splitshipsetby = splitshipsetby;
	}
	public String getArtworkhold() {
		return artworkhold;
	}
	public void setArtworkhold(String artworkhold) {
		this.artworkhold = artworkhold;
	}
	public String getMisccsrinstruction() {
		return misccsrinstruction;
	}
	public void setMisccsrinstruction(String misccsrinstruction) {
		this.misccsrinstruction = misccsrinstruction;
	}
	public String getIswaivemoa() {
		return iswaivemoa;
	}
	public void setIswaivemoa(String iswaivemoa) {
		this.iswaivemoa = iswaivemoa;
	}
	public String getIswaivemoq() {
		return iswaivemoq;
	}
	public void setIswaivemoq(String iswaivemoq) {
		this.iswaivemoq = iswaivemoq;
	}
	public String getIssizecheck() {
		return issizecheck;
	}
	public void setIssizecheck(String issizecheck) {
		this.issizecheck = issizecheck;
	}
	public String getIsdiscountpricecheck() {
		return isdiscountpricecheck;
	}
	public void setIsdiscountpricecheck(String isdiscountpricecheck) {
		this.isdiscountpricecheck = isdiscountpricecheck;
	}
	public String getIsfabriccheck() {
		return isfabriccheck;
	}
	public void setIsfabriccheck(String isfabriccheck) {
		this.isfabriccheck = isfabriccheck;
	}
	public String getIsshipmark() {
		return isshipmark;
	}
	public void setIsshipmark(String isshipmark) {
		this.isshipmark = isshipmark;
	}
	public String getIsllkkcheck() {
		return isllkkcheck;
	}
	public void setIsllkkcheck(String isllkkcheck) {
		this.isllkkcheck = isllkkcheck;
	}
	public String getIslocalbillingCheck() {
		return islocalbillingCheck;
	}
	public void setIslocalbillingCheck(String islocalbillingCheck) {
		this.islocalbillingCheck = islocalbillingCheck;
	}
	public String getIsfactorytransfercheck() {
		return isfactorytransfercheck;
	}
	public void setIsfactorytransfercheck(String isfactorytransfercheck) {
		this.isfactorytransfercheck = isfactorytransfercheck;
	}
	public String getIsshipmentsample() {
		return isshipmentsample;
	}
	public void setIsshipmentsample(String isshipmentsample) {
		this.isshipmentsample = isshipmentsample;
	}
	public String getOrderfilenamepatern() {
		return orderfilenamepatern;
	}
	public void setOrderfilenamepatern(String orderfilenamepatern) {
		this.orderfilenamepatern = orderfilenamepatern;
	}
	public String getOrderfilenameextension() {
		return orderfilenameextension;
	}
	public void setOrderfilenameextension(String orderfilenameextension) {
		this.orderfilenameextension = orderfilenameextension;
	}
	public String getOrderfileschemaid() {
		return orderfileschemaid;
	}
	public void setOrderfileschemaid(String orderfileschemaid) {
		this.orderfileschemaid = orderfileschemaid;
	}
	public String getOrderfilemappingid() {
		return orderfilemappingid;
	}
	public void setOrderfilemappingid(String orderfilemappingid) {
		this.orderfilemappingid = orderfilemappingid;
	}
	public String getIsorderhasinternalitems() {
		return isorderhasinternalitems;
	}
	public void setIsorderhasinternalitems(String isorderhasinternalitems) {
		this.isorderhasinternalitems = isorderhasinternalitems;
	}
	public String getIsorderhaslocalitems() {
		return isorderhaslocalitems;
	}
	public void setIsorderhaslocalitems(String isorderhaslocalitems) {
		this.isorderhaslocalitems = isorderhaslocalitems;
	}
    
    
    
}
