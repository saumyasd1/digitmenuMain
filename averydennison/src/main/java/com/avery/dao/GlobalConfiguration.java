package com.avery.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "globalconfiguration")
public class GlobalConfiguration {
		
	int id;
	
	String unidentifiedOrderMailCSRMailID;
	 String prevOrderQueueId;
	 String orderDuration;
	 String duplicateOrderField;
	 String duplicateOrderDuration;
	 String defaultTimeZone;
	 String maxSOLine;
	 
	 public GlobalConfiguration() {
    	 
	    }
	 
	    public GlobalConfiguration(String orderDuration, String unidentifiedOrderMailCSRMailID) {
	        this.unidentifiedOrderMailCSRMailID = unidentifiedOrderMailCSRMailID;
	        this.orderDuration = orderDuration;
	       
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
	public String getUnidentifiedOrderMailCSRMailID() {
		return unidentifiedOrderMailCSRMailID;
	}
	public void setUnidentifiedOrderMailCSRMailID(
			String unidentifiedOrderMailCSRMailID) {
		this.unidentifiedOrderMailCSRMailID = unidentifiedOrderMailCSRMailID;
	}
	public String getPrevOrderQueueId() {
		return prevOrderQueueId;
	}
	public void setPrevOrderQueueId(String prevOrderQueueId) {
		this.prevOrderQueueId = prevOrderQueueId;
	}
	public String getOrderDuration() {
		return orderDuration;
	}
	public void setOrderDuration(String orderDuration) {
		this.orderDuration = orderDuration;
	}
	public String getDuplicateOrderField() {
		return duplicateOrderField;
	}
	public void setDuplicateOrderField(String duplicateOrderField) {
		this.duplicateOrderField = duplicateOrderField;
	}
	public String getDuplicateOrderDuration() {
		return duplicateOrderDuration;
	}
	public void setDuplicateOrderDuration(String duplicateOrderDuration) {
		this.duplicateOrderDuration = duplicateOrderDuration;
	}
	public String getDefaultTimeZone() {
		return defaultTimeZone;
	}
	public void setDefaultTimeZone(String defaultTimeZone) {
		this.defaultTimeZone = defaultTimeZone;
	}
	public String getMaxSOLine() {
		return maxSOLine;
	}
	public void setMaxSOLine(String maxSOLine) {
		this.maxSOLine = maxSOLine;
	}
	 
	 
}
