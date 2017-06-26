package com.avery.services;

import java.util.*;

import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.dao.OrderFileAttachment;
import com.avery.dao.Partner_RBOProductLine;
import com.adeptia.indigo.logging.Logger;

/*created by Dipanshu
 * Date 22dec2016
 * scope to identify partner rbo productline from email body subject and attachment
 * update productline id and matches found in email
 * **/
/**
 * @author Dipanshu
 * 
 */
public class OrderEmailQueueServices {

	// /set directory path for attached files ///////////
	public static String directory;
	// public static Logger log;
	static Logger log = Logger
			.getLogger(OrderEmailQueueServices.class.getName());
	public static String AND_SEPERATOR = "_\\&\\&_";
	public static String OR_SEPERATOR = "_\\|\\|_";
	public static String VALUE_SEPARATOR = "_\\._";
	public static String VALUE_SEPARATOR_WITHOUTESCAPE = "_._";
	public String regexSupportString = "\\b";

	/**
	 * method OrderEmailSourceservice
	 * 
	 * @param dir
	 * @param id
	 * @throws Exception
	 */
	public void orderEmailSourceservice(int id) throws Exception {
		// //assign value to directory path
		// this.directory = dir;
		String email = "";

		int AttachmentId = 0;
		int result = 0;
		log.info("identification sevice starts");
		// /create object of model class to access its methods
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		try {
			// log.error("Enter method OrderEmailSourceservice  class OrderEmailService");
			log.debug("get email source for id : \"" + id + "\".");
			// ArrayList<Integer> SchemaIdList = new ArrayList;
			HashMap<String, String> emailinfo = orderEmailQueue.emailSource(id);
			Iterator it = emailinfo.entrySet().iterator();
			// //get email subject and source
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				if (pair.getKey() == "source") {
					email = (String) pair.getValue();
				}
			}
			log.debug("email source found: \"" + email + "\".");
			ArrayList<Object> email_list = orderEmailQueue
					.getEmailAttachments(id);
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			if (email_list.size() <= 0) {
				log.debug("No attachments found for emailqueue id \"" + id
						+ "\".");
				orderEmailQueue.updateOrderEmail(id, "3", "", "", "", "", "");
				return;
			}
			// loop to iterate all attachment and perform operation
			while (iterat.hasNext()) {
				email_att = (OrderFileAttachment) iterat.next();
				AttachmentId = email_att.getId();
				String FileName = email_att.getFileName();
				String fileExt = email_att.getFileExtension();
				String filePath = email_att.getFilePath();
				log.debug("identification attachment for attachment id \""
						+ AttachmentId + "\" and file name \"" + FileName
						+ "\"" + ".");
				// getlist from partner analysis
				PartnerAnalysis PA = new PartnerAnalysis();
				OrderFileContentAnalysis ofa = new OrderFileContentAnalysis();
				ArrayList<Integer> SchemaIdList = PA.partnerSearch(email,
						filePath, FileName, id, AttachmentId);
				log.debug("SchemaIdList for content analysis is \""
						+ SchemaIdList + ".");
				if (SchemaIdList.size() == 0 || SchemaIdList.isEmpty()
						|| SchemaIdList == null) {
					log.debug("SchemaIdList is empty .");
					return;
				}
				ofa.identifyAttachment(id, AttachmentId, filePath, FileName,
						fileExt, SchemaIdList);
				// ffile content analysis among list given for partner
				// result = identifyAttachment(id, AttachmentId, filePath,
				// FileName,
				// fileExt, email);
			}

		} catch (Exception e) {
			log.error("Exception while getting mail data");
			throw e;
		}
	}

	/**
	 * method getemaildetail
	 * 
	 * @param orderfileQueueId
	 * @return boolean purpose to check revise cancel order in subject or email
	 *         body
	 * @throws Exception
	 */
	public boolean getemaildetail(int orderfileQueueId) throws Exception {
		int schema_id = 0;
		int EmailQueueId = 0;
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		FileSearch fs = new FileSearch();
		// // get attachment id from orderfile queue
		int att_id = orderEmailQueue.GeteAttachmentId(orderfileQueueId);
		if (att_id == 0) {
			String comment = "return false because order file attachment id is null for file queue id \""
					+ orderfileQueueId + "\".";
			orderEmailQueue.updateOrderFileQueueComment(orderfileQueueId,
					comment);
			log.debug("return false because order file attachment id is null for file queue id \""
					+ orderfileQueueId + "\".");
			return true;
		}
		log.debug("get emailattachment id \"" + att_id
				+ " \"for filequeueid \"" + orderfileQueueId + "\".");
		HashMap<String, Integer> email_info = orderEmailQueue
				.GetOrderEmailQueueId(att_id);
		Iterator it = email_info.entrySet().iterator();
		// //get order email queue id and schema id from order file attachment
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey() == "schema_id") {
				schema_id = (int) pair.getValue();
			}

			if (pair.getKey() == "emailQueue_id") {
				EmailQueueId = (int) pair.getValue();
			}
		}
		if (schema_id == 0) {
			log.debug("return false because schema id is null for attachment id \""
					+ att_id + "\".");
			String comment = "return false because schema id is null for attachment id \""
					+ att_id + "\".";
			orderEmailQueue.updateOrderFileQueueComment(orderfileQueueId,
					comment);
			return true;
		}
		if (EmailQueueId == 0) {
			String comment = "return false because emailqueue id is null for attachment id \""
					+ att_id + "\".";
			orderEmailQueue.updateOrderFileQueueComment(orderfileQueueId,
					comment);
			log.debug("return false because emailqueue id is null for attachment id \""
					+ att_id + "\".");
			return true;
		}
		log.debug("get schema id\"" + schema_id + "\" for fileattachment id \""
				+ att_id + "\".");
		String file_name = "";
		String file_ext = "";
		String file_path = "";
		String subject = this.getemailsubject(EmailQueueId);

		if (subject == null || subject.trim().isEmpty()) {
			subject = "";
		}
		ArrayList<Object> email_list = orderEmailQueue
				.GetEmailAttachmentDetail(EmailQueueId);
		Iterator<Object> iterat = email_list.iterator();
		OrderFileAttachment email_att = new OrderFileAttachment();
		while (iterat.hasNext()) {
			email_att = (OrderFileAttachment) iterat.next();
			if (email_att.getFileName().contains("CompleteEmail")) {
				file_name = email_att.getFileName();
				file_ext = email_att.getFileExtension();
				file_path = email_att.getFilePath();
			}
		}
		log.debug("got emailbody details for email id \"" + EmailQueueId
				+ "\".");
		ArrayList<String> keywords = getkeyword(schema_id);
		Iterator iterator = keywords.iterator();
		while (iterator.hasNext()) {
			String keyword = (String) iterator.next();
			if (subject.toLowerCase().contains(keyword.toLowerCase())) {
				log.debug("keyword found in subject \"" + subject
						+ "\"keyword." + keyword + "\".");
				return true;
			}
			if (!fs.searchContentFromMailBody(file_path, file_name, keyword,
					false).isEmpty()) {
				log.debug("keyword found in emailbody\"" + keyword
						+ "\"and email body at path \"" + file_path + "\".");
				return true;
			}
		}
		return false;
	}

	/**
	 * method getemailsubject
	 * 
	 * @param emailid
	 * @return email subject as string
	 * @throws Exception
	 */
	public String getemailsubject(int emailid) throws Exception {
		String subject = "";
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		HashMap<String, String> emailinfo = orderEmailQueue
				.emailSource(emailid);
		Iterator it = emailinfo.entrySet().iterator();

		// //get email subject
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey() == "subject") {
				subject = (String) pair.getValue();
			}
		}
		log.debug("got subject \"" + subject + "\" for email queue id \""
				+ emailid + "\".");
		return subject;
	}

	/**
	 * method getkeyword
	 * 
	 * @param schema_id
	 * @return list of keywords from partner rbo productline table
	 * @throws Exception
	 */
	public ArrayList<String> getkeyword(int schema_id) throws Exception {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		log.debug("get keyword for schema id \"" + schema_id + ".");
		ArrayList<Object> partner_rboinfo = orderEmailQueue
				.getPartner_productline(schema_id);
		Iterator<Object> iterator = partner_rboinfo.iterator();
		Partner_RBOProductLine p_info = new Partner_RBOProductLine();
		ArrayList<String> keyword = new ArrayList<String>();

		while (iterator.hasNext()) {
			p_info = (Partner_RBOProductLine) iterator.next();
			String keyword_s = p_info.getRevisecancelorder();

			if (keyword_s.contains(",")) {
				String[] keywords = keyword_s.split(",");
				for (String skeyword : keywords) {

					keyword.add(skeyword);
				}
			} else if (!keyword_s.isEmpty()) {
				keyword.add(keyword_s);
			}
		}
		log.debug("keyword list for found \"" + keyword + "\".");
		return keyword;
	}

}
