package com.avery.Model;

import java.util.ArrayList;
import java.util.HashMap;

public interface OrderEmailQueueInterface {
	public HashMap<String, String> EmailSource(int id );
	public int getPartnerId(String email, String domain );
	public ArrayList<Object> getPartnerRbo_productlines(int partnerId );
	public ArrayList<Object> GetEmailAttachments(int id);
	public ArrayList<Object> getPartner_productline(int productlineId );
	public int updateOrderEmail(int orderEmailId, String orderEmailStatus, String subject_rboMatch, String subject_productlineMatch, String body_rboMatch, String body_productlineMatch );
	public int updateOrderEmailAttachment(int attachmentId, int productlineId, String Status, String rboMatch, String productlineMatch);
	public int updateError(String ErrorCategory, String description );
	public boolean updateAllAttachment(int email_id, int productlineId, String Status);
	public boolean updateAttachmenttype(int att_id, String contentType);
}
