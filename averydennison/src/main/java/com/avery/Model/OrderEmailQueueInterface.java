package com.avery.Model;

import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Blob;




public interface OrderEmailQueueInterface {
	public HashMap<String, String> EmailSource(int id );
	public int getPartnerId(String email, String domain );
	public ArrayList<Object> getPartnerRbo_productlines(int partnerId );
	//public int updateSchemaId(int orderEmailId, int productlineId,String attachmentStatus, String orderEmailStatus );
	public ArrayList<Object> GetEmailAttachments(int id);
	public int updateOrderEmailAttachment(int attachmentId, int productlineId, String Status);
	public int updateOrderEmail(int orderEmailId, String orderEmailStatus );
	public ArrayList<Object> getPartner_productline(int productlineId );
	public int updateError(String ErrorCategory, String description );
	
}
