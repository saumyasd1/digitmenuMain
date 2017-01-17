package com.avery.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Dipanshu
 *
 */

public interface OrderEmailQueueInterface {
	/**
	 * @param email queue id
	 * @return email content
	 */
	public HashMap<String, String> EmailSource(int id );
	
	/**
	 * @param email
	 * @param domain
	 * @return
	 */
	public int getPartnerId(String email, String domain );
	/**
	 * @param email
	 * @return
	 */
	public ArrayList<Object> getPartnerRbo_productlines(String email);
	//public ArrayList<Object> getPartnerRbo_productlines(int partnerId );
	/**
	 * @param id
	 * @return
	 */
	public ArrayList<Object> GetEmailAttachments(int id);
	/**
	 * method GetEmailAttachments
	 * @param productlineId
	 * @return productline rbo details
	 */
	public ArrayList<Object> getPartner_productline(int productlineId );
	/**
	 * mrthod updateOrderEmail
	 * @param orderEmailId
	 * @param orderEmailStatus
	 * @param subject_rboMatch
	 * @param subject_productlineMatch
	 * @param body_rboMatch
	 * @param body_productlineMatch
	 * @param comment
	 * @return
	 */
	public int updateOrderEmail(int orderEmailId, String orderEmailStatus, String subject_rboMatch, String subject_productlineMatch, String body_rboMatch, String body_productlineMatch, String comment );
	
	/** method updateOrderEmailAttachment
	 * @param attachmentId
	 * @param productlineId
	 * @param Status
	 * @param rboMatch
	 * @param productlineMatch
	 * @param comment
	 * @return
	 */
	public int updateOrderEmailAttachment(int attachmentId, int productlineId, String Status, String rboMatch, String productlineMatch, String comment);
	/**
	 * @param ErrorCategory
	 * @param description
	 * @return
	 */
	public int updateError(String ErrorCategory, String description );
	/**
	 * @param email_id
	 * @param productlineId
	 * @param Status
	 * @param comment
	 * @return
	 */
	public boolean updateAllAttachment(int email_id, int productlineId, String Status, String comment);
	/**
	 * @param att_id
	 * @param contentType
	 * @return
	 */
	public boolean updateAttachmenttype(int att_id, String contentType);
	/**
	 * @param fileQueueId
	 * @return
	 */
	public int GeteAttachmentId(int fileQueueId);
	/**
	 * @param att_id
	 * @return
	 */
	public HashMap<String, Integer> GetOrderEmailQueueId(int att_id);
	/**
	 * @param orderEmailId
	 * @return
	 */
	public ArrayList<Object> GetEmailAttachmentDetail(int orderEmailId);
	/**
	 * @param attachmentId
	 * @param productlineId
	 * @param Status
	 * @param rboMatch
	 * @param productlineMatch
	 * @param comment
	 * @param fileType
	 * @return
	 */
	public int updateOrderEmailAttachmentContent(int attachmentId, int productlineId, String Status, String rboMatch, String productlineMatch, String comment,String fileType);
}
