package com.avery.Model;

import java.util.ArrayList;
import java.util.HashMap;

import com.avery.dao.OrderFileAttachment;

/**
 * @author Dipanshu
 *
 */

public interface OrderEmailQueueInterface {
	/**
	 * @param email queue id
	 * @return email content
	 * @throws Exception 
	 */
	public HashMap<String, String> EmailSource(int id ) throws Exception;
	
	/**
	 * @param email
	 * @param domain
	 * @return
	 */
	public int getPartnerId(String email, String domain )throws Exception;
	/**
	 * @param email
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<Object> getPartnerRbo_productlines(String email) throws Exception;
	//public ArrayList<Object> getPartnerRbo_productlines(int partnerId );
	/**
	 * @param id
	 * @return
	 */
	public ArrayList<Object> GetEmailAttachments(int id)throws Exception;
	/**
	 * method GetEmailAttachments
	 * @param productlineId
	 * @return productline rbo details
	 */
	public ArrayList<Object> getPartner_productline(int productlineId )throws Exception;
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
	public int updateOrderEmail(int orderEmailId, String orderEmailStatus, String subject_rboMatch, String subject_productlineMatch, String body_rboMatch, String body_productlineMatch, String comment )throws Exception;
	
	/** method updateOrderEmailAttachment
	 * @param attachmentId
	 * @param productlineId
	 * @param Status
	 * @param rboMatch
	 * @param productlineMatch
	 * @param comment
	 * @param contentMatch
	 * @return
	 */
	public int updateOrderEmailAttachment(int attachmentId, int productlineId, String Status, String rboMatch, String productlineMatch, String comment,String contentMatch)throws Exception;
	/**
	 * @param ErrorCategory
	 * @param description
	 * @return
	 */
	public int updateError(String ErrorCategory, String description )throws Exception;
	/**
	 * @param email_id
	 * @param productlineId
	 * @param Status
	 * @param comment
	 * @return
	 */
	public boolean updateAllAttachment(int email_id, int productlineId, String Status, String comment)throws Exception;
	/**
	 * @param att_id
	 * @param contentType
	 * @return
	 */
	public boolean updateAttachmenttype(int att_id, String contentType)throws Exception;
	/**
	 * @param fileQueueId
	 * @return
	 */
	public int GeteAttachmentId(int fileQueueId)throws Exception;
	/**
	 * @param att_id
	 * @return
	 */
	public HashMap<String, Integer> GetOrderEmailQueueId(int att_id)throws Exception;
	/**
	 * @param orderEmailId
	 * @return
	 */
	public ArrayList<Object> GetEmailAttachmentDetail(int orderEmailId)throws Exception;
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
	public int updateOrderEmailAttachmentContent(int attachmentId, int productlineId, String Status, String rboMatch, String productlineMatch, String comment,String fileType)throws Exception;
	/**
	 * @param orderFileQueueId
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderFileQueueComment(int orderFileQueueId, String comment)throws Exception;
}
