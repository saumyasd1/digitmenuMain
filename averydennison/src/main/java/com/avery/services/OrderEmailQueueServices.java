package com.avery.services;

import java.io.File;
import java.util.*;

import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.Model.ProductLineBean;
import com.avery.Model.SearchCellAddress;
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
	public static String email = "";
	public static String siteId = "";
	//added on 16-09-2017 for one order and multiple additional file.
	public static String orderFileID="";
	
	// public static Logger log;
	//code added by Rajo for logging related issue in cluster environment due to null value.
	public static Logger log=Logger.getLogger(OrderEmailQueueServices.class.getName());
	static ArrayList<Object> productLinesData = new ArrayList<Object>();
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
	public void orderEmailSourceservice(int id, Logger _log) throws Exception {

		log = _log;
		
		int AttachmentId = 0;
		// int result = 0;
		// Workbook workbook =null;
		log.info("identification sevice starts");
		long startTime_1 = System.nanoTime();

		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();

		try {

			log.debug("get email source for id : \"" + id + "\".");
			HashMap<String, String> emailinfo = orderEmailQueue.emailSource(id);
			Iterator it = emailinfo.entrySet().iterator();
			// //get email subject and source
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				if (pair.getKey() == "source") {
					email = (String) pair.getValue();
				}
				// added today 31-07-2017 Rajo for site filtration.
				if (pair.getKey() == "siteId") { 
					siteId = (String) pair.getValue();
				}
				
			}
			log.debug("email source found: \"" + email + "\".");
			// get productlines for email.
			ProductLineBean plb = new ProductLineBean();
			plb.setProductLinesForEmail(email,siteId);

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
				long startTime_2 = System.nanoTime();
				email_att = (OrderFileAttachment) iterat.next();
				AttachmentId = email_att.getId();
				String fileName = email_att.getFileName();
				String fileExt = email_att.getFileExtension();
				String filePath = email_att.getFilePath();
				log.debug("identification attachment for attachment id \""
						+ AttachmentId + "\" and file name \"" + fileName
						+ "\"" + ".");
				// getlist from partner analysis

				PartnerAnalysis PA = new PartnerAnalysis();
				OrderFileContentAnalysis ofa = new OrderFileContentAnalysis();
				long startTime_3 = System.nanoTime();
				ArrayList<Integer> SchemaIdList = PA.partnerSearch(plb,
						filePath, fileName, id, AttachmentId);
				long endTime_3 = System.nanoTime();
				double duration_3 = (double) (endTime_3 - startTime_3)
						/ (1000000 * 1000);
				log.debug("Total elapsed time in seconds. for partner rbo productline analysis--> "
						+ duration_3 + "s");
				log.debug("SchemaIdList for content analysis is \""
						+ SchemaIdList + ".");
				if (SchemaIdList.size() == 0 || SchemaIdList.isEmpty()
						|| SchemaIdList == null) {
					log.debug("SchemaIdList is empty .");
					return;
				}
				long startTime_4 = System.nanoTime();
				ofa.identifyAttachment(id, AttachmentId, filePath, fileName,
						fileExt, SchemaIdList);
				long endTime_4 = System.nanoTime();
				double duration_4 = (double) (endTime_4 - startTime_4)
						/ (1000000 * 1000);
				log.debug("Total elapsed time in seconds. for  File analysis--> "
						+ duration_4 + "s");
				
				long endTime_2 = System.nanoTime();
				double duration_2 = (double) (endTime_2 - startTime_2)
						/ (1000000 * 1000);
				log.debug("Total elapsed time in seconds--> \"" + duration_2
						+ "\" for file name \"" + fileName + "\"" + ".");
			}
			//added for additional file analysis with order file.
			getAdditionalFileMatchWithOrderFile(id,log);
			
		} catch (Exception e) {
			log.error("Exception while getting mail data");
			throw e;
		} finally {
			long endTime_1 = System.nanoTime();
			double duration_1 = (double) (endTime_1 - startTime_1)
					/ (1000000 * 1000);
			ProductLineBean.productLineMap.clear();
			orderFileID="";
			log.debug("Total elapsed time in seconds. for overall process--> "
					+ duration_1 + "s");

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
	public boolean getemaildetail(int orderfileQueueId, Logger _log) throws Exception {
		int schema_id = 0;
		log = _log;
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
	 * Rajo
	 * 16-09-2017
	 * This method will analyze order file's productLineId and replace with additional file id if any
	 * for single or same type of order file.
	 * @param orderfileQueueId
	 * @param _log
	 * @throws Exception
	 */
	public void getAdditionalFileMatchWithOrderFile(int id, Logger _log) throws Exception {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		log.debug("get emailattachment id \"" +id);
		ArrayList<OrderFileAttachment> order_list= new ArrayList<OrderFileAttachment>();
		ArrayList<OrderFileAttachment> additional_list= new ArrayList<OrderFileAttachment>();
		Set<Integer> orderProductLine=new HashSet<>();
		try{
			ArrayList<Object> email_list = orderEmailQueue
					.getEmailAttachments(id);
			if (email_list==null) {
				log.debug("No attachments found for emailqueue id \"" + id
						+ "\".");
				return;
			}
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			
			while (iterat.hasNext()) {
				email_att = (OrderFileAttachment) iterat.next();
				String fileType = email_att.getFileContentType();
				if(fileType!=null &&fileType.length()>0){
					if(fileType.equalsIgnoreCase("Order")){
						order_list.add(email_att);
					}
					else if(fileType.equalsIgnoreCase("AdditionalData")){
						additional_list.add(email_att);
					}
				}
				
			}	
			
			// here will check if only one order file present
			if(order_list.size()>0){
				Iterator<OrderFileAttachment> iterat_order = order_list.iterator();
				while (iterat_order.hasNext()) {
					Partner_RBOProductLine productLine = iterat_order.next().getVarProductLine();
					if( productLine!=null)
					orderProductLine.add(productLine.getId());
				}
				
				if(additional_list.size()>0 &&orderProductLine.size()==1){
					Iterator<OrderFileAttachment> iterat_additional = additional_list.iterator();
					int productLine=orderProductLine.iterator().next();
					while (iterat_additional.hasNext()) {
						OrderFileAttachment orderFileAttachment=iterat_additional.next();
						String comment=orderFileAttachment.getComment();
						if(comment!=null && comment.length()>0){
							Set<String> commentSet=new LinkedHashSet<String>(Arrays.asList(comment.split(",")));
							if(commentSet.contains(String.valueOf(productLine))){
								orderEmailQueue.updateOrderEmailAttachmentContent(
										orderFileAttachment.getId(), productLine, "8", "", "", " ",
										"AdditionalData", "@@");
							}
							else{
								log.debug("No common value  found in comment for additional data identification for  id \"" + productLine
										+ "\".");
							}
						}
						else{
							log.debug("No comment value  found in  for  id \"" + orderFileAttachment.getId()
									+ "\".");
						}
						
					}
					
				}
				else{
					return;
				}
			}
			else{
				log.debug("No order file found for emailqueue id \"" + id
						+ "\".");
				return;
			}
			
			
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			log.debug("Got additional file analysis details for email id \"" 
					+id + "\"...");
		}
		
		
		
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

	/**
	 * Method to get eml file location
	 * 
	 * @param emailQueueId
	 * @return
	 * @throws Exception
	 */
	public static String getEMLFileLocation(int emailQueueId) throws Exception {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Object> orderFileAttachments = new ArrayList<Object>();
		orderFileAttachments = orderEmailQueue.GetEmailBody(emailQueueId);
		Iterator<Object> iterat = orderFileAttachments.iterator();
		String MailbodyPath = "";
		String MailBodyFileName = "";
		while (iterat.hasNext()) {
			OrderFileAttachment orderFileAttachment = (OrderFileAttachment) iterat
					.next();
			if (orderFileAttachment != null) {
				MailbodyPath = orderFileAttachment.getFilePath();
				MailBodyFileName = orderFileAttachment.getFileName();
			} else {
				throw new Exception(
						"No entry is found in the table orderEmailQueue for emailQueueId:\""
								+ emailQueueId + "\".");
			}
		}
		return MailbodyPath + File.separatorChar + MailBodyFileName;
	}

}
