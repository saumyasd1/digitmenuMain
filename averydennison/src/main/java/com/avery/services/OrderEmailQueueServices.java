package com.avery.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.Model.SearchCellAddress;
import com.avery.dao.OrderFileAttachment;
import com.avery.dao.Partner_RBOProductLine;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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
	
	///set directory path for attached files ///////////
	public static String directory;
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
	public static String AND_SEPERATOR= "_\\&\\&_";
	public static String OR_SEPERATOR= "_\\|\\|_";
	public static String VALUE_SEPARATOR="_\\._";
	public static String VALUE_SEPARATOR_WITHOUTESCAPE="_._";
	public String regexSupportString="\\b";
	/**method OrderEmailSourceservice
	 * @param dir
	 * @param id
	 * @throws Exception 
	 */
	public void OrderEmailSourceservice(String dir,int id) throws Exception {
		////assign value to directory path
		this.directory = dir;
		String email = "";
		int att_id=0;
		int result=0;
		///create object of model class to access its methods
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		try {
			//log.error("Enter method OrderEmailSourceservice  class OrderEmailService");
			log.debug("get email source for id : \""+id+"\".");
			HashMap<String, String> emailinfo = orderEmailQueue.EmailSource(id);
			Iterator it = emailinfo.entrySet().iterator();
			////get email subject and source 
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				if (pair.getKey() == "source") {
					email = (String) pair.getValue();
				}
			}
			log.debug("email source found: \""+email+"\".");
			ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(id);
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			if(email_list.size()<=0){
				log.debug("No attachments found for emailqueue id \""+id+"\".");
				orderEmailQueue.updateOrderEmail(id,"3","","","","","");
				return;
			}
			//loop to iterate all attachment and perform operation
			while (iterat.hasNext()) {
				email_att = (OrderFileAttachment) iterat.next();
				att_id =  email_att.getId();
				String file_name = email_att.getFileName();
				String file_ext = email_att.getFileExtension();
				String file_path = email_att.getFilePath();
				log.debug("identification attachment for attachment id \""+att_id+"\".");
				result =identifyAttachment(id, att_id, file_path, file_name, file_ext, email);
			}
			if(result==1){
				log.debug("identification additional data file.");
				identifyAdditionalDataFile(id);
			}
		} catch (Exception e) {
			log.error("Exception while getting mail data");
			throw e;
		}
	}
	/*
	 * read email subject to identify rbo product line
	 * method readEmailSubject
	 * @param id
	 * @param attachment_id
	 * @param productline_id
	 * @return
	 */
	public void readEmailSubject(int id, int attachment_id, List<Integer> productline_id ) throws Exception{
		log.debug("email subject analysis start.");
		List<String> rbo_match = new ArrayList<String>();
		List<String> productline_match = new ArrayList<String>();
		List<Integer> schema_id = new ArrayList<Integer>();
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		HashMap<String, String> emailinfo = orderEmailQueue.EmailSource(id);
		Iterator it = emailinfo.entrySet().iterator();
		String subject="";
		String subjectRbo="";
		String subjectProductline="";
		////get email subject  
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey() == "subject") {
				subject = (String) pair.getValue();
			}
			if (pair.getKey() == "subjectRbo") {
				subjectRbo = (String) pair.getValue();
			}
			if (pair.getKey() == "subjectProductline") {
				subjectProductline = (String) pair.getValue();
			}
		}
		if(subjectRbo==null){
			subjectRbo="";
		}
		if(subjectProductline==null){
			subjectProductline="";
		}
		if(subject==null||subject.trim().isEmpty()){
			log.debug("Subject is empty for emailqueueid \"" + id +"\".");
			return ;
		}
		//get product line id in loop and check
		for (Integer p_id : productline_id){
			//fetch productline info
			ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(p_id);
			Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
			Iterator<Object> iterator = partner_rboinfo.iterator();
			while (iterator.hasNext()) {
				rbodetails = (Partner_RBOProductLine) iterator.next();
				if(rbodetails.isEmailSubjectRBOMatchRequired()){
					String rbolist = rbodetails.getEmailSubjectRBOMatch();
					if (rbolist.contains("|")||!rbolist.isEmpty()) {
						String[] rbos = rbolist.split("\\|");
						for (String prbo : rbos) {
							if (subject.toLowerCase().contains(prbo.toLowerCase()) && !prbo.isEmpty()) {
								rbo_match.add(prbo);
								schema_id.add((int) rbodetails.getId());
							}
						}
					}else{
						log.debug("EmailSubjectRBOMatch is empty for productline id \"" + p_id+"\".");
					}
				}else{
					log.debug("EmailSubjectRBOMatchRequired is false for productline id \"" + p_id+"\".");
				}
				if(rbodetails.isEmailSubjectProductlineMatchRequired()){
					String productlines = rbodetails.getEmailSubjectProductLineMatch();
					if (productlines.contains("|")||!productlines.isEmpty()) {
						String[] pline = productlines.split("\\|");
						for (String productline : pline) {
							if(!productline.isEmpty()){
								if (subject.toLowerCase().contains(productline.toLowerCase())) {
									productline_match.add(productline);
									schema_id.add((int) rbodetails.getId());
								}
							}
						}
					}else{
						log.debug("EmailSubjectProductLineMatch is empty for productline id \"" + p_id +"\".");
					}
				}else{
					log.debug("EmailSubjectProductlineMatchRequired is false for productline id \"" + p_id +"\".");
				}
			}
		}
		//schema id list where match found in subject
		schema_id = new ArrayList(new HashSet(schema_id));
		//rbo list where match found in subject
		rbo_match = new ArrayList(new HashSet(rbo_match));
		//productline id list where match found in subject
		productline_match = new ArrayList(new HashSet(productline_match));
		
		subjectRbo=removeDup(subjectRbo);
		for (String s : rbo_match)
		{
			if(subjectRbo.length()<=85 && !s.isEmpty()){
				s=s.trim();
				subjectRbo += s + ",";
			}
		}
		subjectRbo=removeDup(subjectRbo);
		for (String s : productline_match)
		{
			if(subjectProductline.length()<=85 && !s.isEmpty()){
				subjectProductline += s.trim() + ",";
			}
		}
		log.debug("subject rbo. \"" + subjectRbo+"\".");
		log.debug("subject productline. \"" + subjectProductline+"\".");
		subjectProductline=removeDup(subjectProductline);
		orderEmailQueue.updateOrderEmail(id,"",subjectRbo,subjectProductline,"","","");
	}
	/**
	 * method removeDup
	 * @param s
	 * @return string without duplicate values
	 */
	public String removeDup(String s) {
		String ns = new LinkedHashSet<String>(Arrays.asList(s.split(","))).toString().trim().replaceAll("(^\\[|\\]$)", "");
	    ns=ns.replaceAll(", ", ",");
	    ns=ns.replaceAll(" ,", ",");
	    if(!ns.isEmpty()){
	    	ns=ns+",";
	    }
	    return ns;
	}
	/**
	 * method EmailBodyAnalysis
	 * @param id
	 * @param attachment_id
	 * @param productline_id
	 * @throws Exception 
	 */
	public void EmailBodyAnalysis(int id, int attachment_id, List<Integer> productline_id ) throws Exception{
			
		List<String> rbo_match = new ArrayList<String>();
		List<String> productline_match = new ArrayList<String>();
		List<Integer> schema_id = new ArrayList<Integer>();
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(id);
		log.debug("emailbody analysis : get attchments list for email id \"" + id+"\\.");
		Iterator<Object> iterat = email_list.iterator();
		OrderFileAttachment email_att = new OrderFileAttachment();
		String file_path="";
		String fileName="";
		String fileContentMatch="";
		String result="";
		String att_status="";
		String comment="";
		while (iterat.hasNext()) {
			email_att = (OrderFileAttachment) iterat.next();
			if(email_att.getFileName().trim().contains("CompleteEmail")){
				if( new Long(email_att.getId())!=null){
					attachment_id=(int)email_att.getId();
				}
				fileName=email_att.getFileName();
				file_path=email_att.getFilePath();
				fileContentMatch=email_att.getFileContentMatch();
				att_status = email_att.getStatus();
				comment=email_att.getComment();
				log.debug("EmailBodyAnalysis for attachment id \"" + attachment_id+"\".");
				if(fileContentMatch==null||fileContentMatch.trim().isEmpty()){
					fileContentMatch="";
				}
				if(comment==null||comment.trim().isEmpty()){
					comment="";
				}
			
				//get product line id in loop and check
				for (Integer p_id : productline_id){
					//fetch productline info
					ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(p_id);
					Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
					Iterator<Object> iterator = partner_rboinfo.iterator();
					while (iterator.hasNext()) {
						log.debug("data fetching for product line id \"" + p_id+"\".");
						rbodetails = (Partner_RBOProductLine) iterator.next();
						if(rbodetails.isEmailBodyRBOMatchRequired()){
							String rbolist = rbodetails.getEmailBodyRBOMatch();
							if (rbolist.contains("|")||!rbolist.isEmpty()) {
								String[] rbos = rbolist.split("\\|");
								for (String prbo : rbos) {
									result="";
									if(!prbo.trim().isEmpty()){
										prbo=prbo.trim();
										result= searchContentFromMailBody(file_path,fileName,prbo, false);
									}
									if (!result.isEmpty()) {
										rbo_match.add(prbo);
										if( new Long(rbodetails.getId())!=null){
											schema_id.add((int)rbodetails.getId());
										}
									}
								}
							}else{
								log.debug("EmailBodyRBOMatch is empty for productline id \"" + p_id+"\".");
							}
						}else{
							log.debug("EmailBodyRBOMatchRequired is false for productline id \"" + p_id+"\".");
						}
						if(rbodetails.isEmailBodyProductlineMatchRequired()){
							String productlines = rbodetails.getEmailBodyProductLineMatch();
							if (productlines.contains("|")||!productlines.isEmpty()) {
								String[] pline = productlines.split("\\|");
								for (String productline : pline) {
									if(!productline.trim().isEmpty()){
										result="";
										productline=productline.trim();
										result= searchContentFromMailBody(file_path,fileName,productline, false);
										if (!result.isEmpty()) {
											productline_match.add(productline);
											if( new Long(rbodetails.getId())!=null){
												schema_id.add((int)rbodetails.getId());
											}
										}
									}
								}
							}else{
								log.debug("EmailBodyProductLineMatch is empty for productline id \"" + p_id+"\".");
							}
						}else{
							log.debug("EmailBodyProductlineMatchRequired is false for productline id \"" + p_id+"\".");
						}
					}
				}
				schema_id = new ArrayList(new HashSet(schema_id));
				rbo_match = new ArrayList(new HashSet(rbo_match));
				productline_match = new ArrayList(new HashSet(productline_match));
				
				for (String s : rbo_match)
				{
					if(fileContentMatch.length()<=85  && !s.isEmpty()){
						fileContentMatch += s.trim() + ",";
						
					}
				}
				
				for (String s : productline_match)
				{
					if(fileContentMatch.length()<=85 && !s.isEmpty()){
						fileContentMatch += s.trim() + ",";
						
					}
				}
				fileContentMatch=removeDup(fileContentMatch);
				int att_productline_id=0;
				log.debug("Email body file Content Match. \"" + fileContentMatch+"\".");
				orderEmailQueue.updateOrderEmailAttachment(attachment_id, att_productline_id, "6","","",comment,fileContentMatch);
			}
		}
	}
	/*
	 * new method to search string in pdf file 
	 * with api itext
	 * done by Dipanshu Ahuja*/
	/**method searchpdf
	 * @param filename
	 * @param keyword
	 * @param filepath
	 * @return string keyword
	 * @throws Exception 
	 */
	public String searchpdf(String filename, String keyword, String filepath) throws Exception {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		String result = "";
		try{
			log.debug("search pdf for filename \""+filename+" \"for filepath \"" + filepath+"\".");
			if (filepath == "") {
					filepath = directory;
				}
				
				PdfReader reader = new PdfReader(filepath +"/"+ filename);
				
				PdfReaderContentParser parser = new PdfReaderContentParser(reader);
				
				PrintWriter out = new PrintWriter(new FileOutputStream(keyword)); 
				
				TextExtractionStrategy strategy;
				
				for (int i = 1; i <= reader.getNumberOfPages(); i++) {
					
					strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
				    String pdf_data = strategy.getResultantText(); 
				    if(pdf_data.toLowerCase().contains(keyword.toLowerCase())){
				    	result= keyword;
				    	log.debug("keyword found in pdf is \""+keyword+"\".");
				    }
				}
				reader.close();
				out.flush();
				out.close();
			} catch(FileNotFoundException e){
				log.error("File not found exception while searching pdf file");
				throw e;
			}catch (Exception e) {
				log.error("excepti while search pdf");
				throw e;
			}
			
		return result;
	}
	
	/**
	 * method getemaildetail
	 * @param orderfileQueueId
	 * @return boolean
	 * purpose to check revise cancel order in subject or email body
	 * @throws Exception 
	 */
	public boolean getemaildetail(int orderfileQueueId) throws Exception {
		int schema_id=0;
		int EmailQueueId=0;
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		//// get attachment id from orderfile queue 
		int att_id = orderEmailQueue.GeteAttachmentId(orderfileQueueId);
		if(att_id==0){
			String comment="return false because order file attachment id is null for file queue id \""+orderfileQueueId+"\".";
			orderEmailQueue.updateOrderFileQueueComment(orderfileQueueId, comment);
			log.debug("return false because order file attachment id is null for file queue id \""+orderfileQueueId+"\".");
			return true;
		}
		log.debug("get emailattachment id \""+att_id+" \"for filequeueid \"" + orderfileQueueId+"\".");
		HashMap<String, Integer> email_info =  orderEmailQueue.GetOrderEmailQueueId(att_id);
		Iterator it = email_info.entrySet().iterator();
		////get order email queue id and schema id from order file attachment 
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey() == "schema_id") {
				schema_id =  (int)pair.getValue();
			}
			
			if (pair.getKey() == "emailQueue_id") {
				EmailQueueId = (int)pair.getValue();
			}
		}
		if(schema_id==0){
			log.debug("return false because schema id is null for attachment id \""+att_id+"\".");
			String comment="return false because schema id is null for attachment id \""+att_id+"\".";
			orderEmailQueue.updateOrderFileQueueComment(orderfileQueueId, comment);
			return true;
		}
		if(EmailQueueId==0){
			String comment="return false because emailqueue id is null for attachment id \""+att_id+"\".";
			orderEmailQueue.updateOrderFileQueueComment(orderfileQueueId, comment);
			log.debug("return false because emailqueue id is null for attachment id \""+att_id+"\".");
			return true;
		}
		log.debug("get schema id\""+schema_id+"\" for fileattachment id \"" + att_id +"\".");
		String file_name="";
		String file_ext="";
		String file_path ="";
		String subject = this.getemailsubject(EmailQueueId);
		
		if(subject==null||subject.trim().isEmpty()){
			subject ="";
		}
		ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachmentDetail(EmailQueueId);
		Iterator<Object> iterat = email_list.iterator();
		OrderFileAttachment email_att = new OrderFileAttachment();
		while (iterat.hasNext()) {
			email_att = (OrderFileAttachment) iterat.next();
			if(email_att.getFileName().contains("CompleteEmail")){
				file_name = email_att.getFileName();
				file_ext = email_att.getFileExtension();
				file_path = email_att.getFilePath();
			}
		}
		log.debug("got emailbody details for email id \""+EmailQueueId+"\".");
		ArrayList<String> keywords= getkeyword(schema_id);
		Iterator iterator = keywords.iterator();
		while (iterator.hasNext()) {
			String keyword = (String) iterator.next();
			if(subject.toLowerCase().contains(keyword.toLowerCase())){
				log.debug("keyword found in subject \""+subject+"\"keyword."+keyword+"\".");
				return true;
			}
			if(!searchContentFromMailBody(file_path,file_name, keyword, false).isEmpty()){
				log.debug("keyword found in emailbody\""+keyword +"\"and email body at path \""+file_path+"\".");
				return true;
			}
		}
		return false;
	}
	/**
	 * method getemailsubject
	 * @param emailid
	 * @return email subject as string
	 * @throws Exception 
	 */
	public String getemailsubject(int emailid) throws Exception {
		String subject="";
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		HashMap<String, String> emailinfo = orderEmailQueue.EmailSource(emailid);
		Iterator it = emailinfo.entrySet().iterator();
			
			////get email subject  
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey() == "subject") {
				subject = (String) pair.getValue();
			}
		}
		log.debug("got subject \""+subject+"\" for email queue id \""+emailid+"\".");
		return subject;
	}
	
	/**method getkeyword
	 * @param schema_id
	 * @return list of keywords from partner rbo productline table
	 * @throws Exception 
	 */
	public ArrayList<String> getkeyword(int schema_id) throws Exception {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		log.debug("get keyword for schema id \""+schema_id+".");
		ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(schema_id);
		Iterator<Object> iterator = partner_rboinfo.iterator();
		Partner_RBOProductLine p_info = new Partner_RBOProductLine();
		ArrayList<String> keyword= new ArrayList<String>();
		
		while (iterator.hasNext()) {
			p_info = (Partner_RBOProductLine) iterator.next();
			String keyword_s=p_info.getRevisecancelorder();
			
			if(keyword_s.contains(",")){
				String[] keywords = keyword_s.split(",");
				for (String skeyword : keywords) {
					
					keyword.add(skeyword);
				}
			}else if(!keyword_s.isEmpty()){
				keyword.add(keyword_s);
			}
		}
		log.debug("keyword list for found \""+keyword+"\".");
		return keyword;
	}
	
/**method identifyAttachment and body
 * @param id
 * @param att_id
 * @param file_path
 * @param file_name
 * @param file_ext
 * @param email
 * @return schema id 
 * @throws Exception 
 */
	public int identifyAttachment(int id, int att_id, String file_path, String file_name, String file_ext, String email) throws Exception {
		
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		int productline_rbo_id;
		String keyword_location="";
		String cell_value ="";
		String schema_id_comment="";
		log.debug("identifyAttachment for fileattachment id \""+att_id+"\".");
		ArrayList<Integer> selected_schema= new ArrayList<Integer>();
		int productline_id=0;
			try {
				ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(email);
				log.debug("get productlines for email \""+email+"\".");
				log.debug("partnerlist \""+partner_rboinfo+"\" found for email source \""+email+"\".");
				log.debug("productlines size \""+partner_rboinfo.size()+"\".");
				if(partner_rboinfo.size()<=0){
					///update for unrecoginzed
					orderEmailQueue.updateOrderEmail(id,"4","","","","","");
					orderEmailQueue.updateAllAttachment(id, productline_id, "6","");
					log.debug("unrecoginzed email \""+email+".");
					return 0;
				}
				Partner_RBOProductLine p_info = new Partner_RBOProductLine();
				Iterator<Object> iterator = partner_rboinfo.iterator();
				String[] o_file_name_pattern = file_name.split("\\.");
				String order_file_name = o_file_name_pattern[0];
				String order_file_ext = o_file_name_pattern[1];
				while (iterator.hasNext()) {
					keyword_location="";
					p_info = (Partner_RBOProductLine) iterator.next();
					productline_rbo_id= p_info.getId();
					//System.out.println(productline_rbo_id);
					log.debug("Processing attachment for productline id \""+productline_rbo_id+"\".");
					if(schema_id_comment.isEmpty()){
						schema_id_comment = "" +productline_rbo_id;
					}else{
						schema_id_comment=schema_id_comment+","+productline_rbo_id;
					}
					
					if(p_info.isFileOrderMatchRequired()){
						
						if(p_info.getFileOrderMatchLocation().contains("FileName")){
							log.debug("Processing attachment identification from file name.");
							String filename_pattern = p_info.getFileOrderMatch();
							if(!filename_pattern.isEmpty()){
								if (filename_pattern.contains("|")) {
									String[] f_name_pattern = filename_pattern.split("\\|");
									for (String file_name_s : f_name_pattern) {
										String[] file_name_p = file_name_s.split("\\.");
										String p_file_Name = file_name_p[0].trim();
										String p_file_ext = file_name_p[1].trim();
										p_file_Name=p_file_Name.replace("*", "(.*)");
										
										if(order_file_name.matches(p_file_Name)&&order_file_ext.contains(p_file_ext)){
											selected_schema.add(productline_rbo_id);
											log.debug("file name match order_file_name   \""+ order_file_name+"\".");
										}else{
											log.debug("file name not match with order file name  \""+ order_file_name+"\".");
										}
									}
								}
							}else{
								log.debug("FileOrderMatch is empty.");
							}
						}else if(p_info.getFileOrderMatchLocation().equals("FileContent")){
							log.debug("Processing attachment identification from file Content.");
							String Sheetinfo = p_info.getFileOrderMatch();
							if(!Sheetinfo.isEmpty()){
								if(p_info.getOrderFileNameExtension().contains(file_ext)){
									if(p_info.getOrderFileNameExtension().contains("xls")){
										//method to search different values in excell at different location
										if(SearchContentInExcel(file_name,file_path,file_ext,Sheetinfo, log)){
											keyword_location="true";
										}
									
									}else if(p_info.getOrderFileNameExtension().contains("pdf")){ 
										log.debug("searchin file extension for pdf");
										String keyword = Sheetinfo.trim();
										String[] keyword_s;
										if (keyword.contains("Value")){ 
											keyword_s = keyword.split(":");
											cell_value=keyword_s[1];
										}
										if(!keyword.trim().isEmpty()){
											if(file_name.contains("CompleteEmail")){
												keyword_location =searchContentFromMailBody(file_path, file_name, cell_value, false);
											}else{
												keyword_location =searchpdf(file_name, cell_value,file_path);
											}
											log.debug("search in pdf for keyword\""+cell_value+"\" file name\"" +file_name+ "\"filepath\""+file_path+"\".");
										}else{
											log.debug("keyword is empty for pdf");
										}
									}
								}
								if(!keyword_location.trim().isEmpty()){
									log.debug("Match found for product line id \""+ productline_rbo_id+"\".");
									selected_schema.add(productline_rbo_id);
								}
							}
						}
					}else{
						log.debug("FileOrderMatchRequired is false for productline id \""+productline_rbo_id+"\".");
					}
				}
				//log.info(selected_schema);
				selected_schema = new ArrayList<Integer>(new LinkedHashSet<Integer>(selected_schema));
				if(selected_schema.size()==1){
					log.debug("match schema list \""+selected_schema+"\".");
					productline_rbo_id = selected_schema.get(0);
					//readEmailSubject(id, att_id, selected_schema);
					orderEmailQueue.updateOrderEmailAttachmentContent(att_id, productline_rbo_id, "8","","","","Order");
					readEmailSubject(id,att_id,selected_schema);
					EmailBodyAnalysis(id,att_id,selected_schema);
					//identifyAdditionalDataFile(id,selected_schema);
				}else if(selected_schema.size()==0){
					log.debug("match schema list \""+selected_schema+"\".");
					orderEmailQueue.updateOrderEmailAttachment(att_id, productline_id, "6","","",schema_id_comment,"");
					
					ArrayList<Integer> selected_schema_int= new ArrayList<Integer>();
					String[] slist_schema = schema_id_comment.split(",");
					for (String schema_ids : slist_schema)
					{
						int i_schema = Integer.parseInt(schema_ids);
						selected_schema_int.add(i_schema);
					}
					readEmailSubject(id,att_id,selected_schema_int);
					EmailBodyAnalysis(id,att_id,selected_schema_int);
					//identifyAdditionalDataFile(id,selected_schema_int);
				}else{
					log.debug("match schema list \""+selected_schema+"\".");
					String schema="";
					for (Integer ids : selected_schema)
					{
						schema += ids + ",";
					}
					orderEmailQueue.updateOrderEmailAttachment(att_id, productline_id, "6","","",schema,"");
					readEmailSubject(id,att_id,selected_schema);
					EmailBodyAnalysis(id,att_id,selected_schema);
					//identifyAdditionalDataFile(id,selected_schema);
				}
			} catch (Exception e) {
				throw e;
			}
		return 1;
	}
	
	/**
	 * @param FileName
	 * @param FilePath
	 * @param FileExt
	 * @param CellDetail
	 * @return
	 * @throws Exception 
	 */
	public boolean SearchContentInExcel(String FileName, String FilePath, String FileExt, String CellDetail, Logger log) throws Exception{
		
		String keywordLocation="";
		Boolean result=true;
		String searchContentList ="";
		String cellNo ="";
		SearchCellAddress read_att_cell = new SearchCellAddress();
		//add handling for CellDetail null value
		if(CellDetail.isEmpty()||CellDetail==null){
			log.info("fileOrderMatch column value is null in table.");
			return false;
		}
		log.info("Starting seacrhing process for file:\""+FilePath+File.separatorChar+FileName+"\".");		
		//if (CellDetail.contains(AND_SEPERATOR)) {
//		log.info("Starting seacrhing process for CellDetail:\""+CellDetail+"\".");
		log.info("Starting seacrhing process for CellDetail:\""+CellDetail+"\".");
			String[] searchIdentifierArray = CellDetail.split(AND_SEPERATOR);
			for (String searchIdentifier : searchIdentifierArray) {
				log.info("Starting seacrhing process for searchIdentifier:\""+searchIdentifier+"\".");
				if (searchIdentifier.contains(";") && !searchIdentifier.isEmpty()) {
					String[] searchDetails = searchIdentifier.split(";");
					//for (String Sheetdetail_search : Sheetdetails) {
						String[] cellValues;
						String[] cellNos;
						if (searchDetails[1].contains("Cell")){ 
							cellNos = searchDetails[1].split(":");
							cellNo=cellNos[1];
							log.debug("Starting seacrhing process for cellNo:\""+cellNo+"\".");
						}
						if(cellNo.isEmpty()){
							return false;
						}
						if (searchDetails[0].contains("Value")){ 
							cellValues = searchDetails[0].split(":");
							searchContentList=cellValues[1];
							if(searchContentList != null && searchContentList.indexOf("*") != -1){
								searchContentList=searchContentList.replace("*", "(.*)");
							}
							//if(searchContentList.trim().startsWith("Global C"))
								String[] searchContent = searchContentList.split(VALUE_SEPARATOR);
								for (String searchValue : searchContent) { 
									if(!searchValue.isEmpty()){
										log.debug("Starting seacrhing process for search content:\""+searchValue+"\".");
										//if(searchContentList.contains(VALUE_SEPARATOR_WITHOUTESCAPE)){
											Boolean cellPosition = read_att_cell.searchContentInAllSheet(
													FilePath,FileName, searchValue, 
													cellNo);
											
											if(cellPosition){
												result=true;
												break;
												
											}else{
												result=false;
											}
									}else{
										log.debug("Search value is empty, so returning false.");
										return false;
									}
								}
					}
				}
				if(!result){
					log.debug("Search text is not found for searchIdentifier:\""+searchIdentifier+"\"."); 
					return result;
				}
			}
		return result;
	 }
	
	/**
	 * method GetOrderEmailQueueStatus
	 * @param orderemailqueue_id
	 * @return
	 * purpose to update order emaile queue on the basis of attachment
	 * @throws Exception 
	 */
	public int GetOrderEmailQueueStatus(int orderemailqueue_id) throws Exception{
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		int status=0;
		ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(orderemailqueue_id);
		Iterator<Object> iterat = email_list.iterator();
		OrderFileAttachment email_att = new OrderFileAttachment();
		
		while (iterat.hasNext()) {
			email_att = (OrderFileAttachment) iterat.next();
			if( email_att.getStatus().equals("6")){
				status=6;
				orderEmailQueue.updateOrderEmail(orderemailqueue_id,"3","","","","","");
			}
		}
		return 0;
	}
	/**
	 * method updateOrderEmailStatus
	 * @param orderemailqueue_id
	 * @param Status
	 * @return
	 * @throws Exception 
	 */
	public void updateOrderEmailStatus(int orderemailqueue_id, int Status) throws Exception{
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		String Status_s =""+Status;
		orderEmailQueue.updateOrderEmail(orderemailqueue_id,Status_s,"","","","","");
		
	}
	/**
	 * method searchContentFromMailBody
	 * @param filePath
	 * @param fileName
	 * @param content
	 * @return
	 */
	public String searchContentFromMailBody(String filePath,String fileName,String content, boolean isCaseSensitive)throws Exception{
		try{
			if(fileName.contains("CompleteEmail")){
				String[] fileNameParts = fileName.split("\\.");
				fileName=fileNameParts[0]+".html";
			}else{
				log.debug("Email body file is wrong.");
				return "";
			}
			if(findMatchingString(generateRegexSupportString(content),readFile(filePath + File.separatorChar
				     + fileName, StandardCharsets.UTF_8), isCaseSensitive))
   		 {
   			 return content;
   			 
   		 }
	    
		}catch(FileNotFoundException e){
			log.debug("CompleteEmail.eml not found.");
			return "";
			//e.printStackTrace();
		}
		catch(IOException e){
			log.debug("IOException while searching in CompleteEmail.eml");
			return "";
			//e.printStackTrace();
		}
		return "";
	}
	/**
	 * Method to generate regex support to String content for word
	 * @param content
	 * @return regex support String
	 */
	public String generateRegexSupportString(String content){
		return regexSupportString+content+regexSupportString;	
	}



	/**Method to match string for defined regEx
	 * @param regExString
	 * @param inputString
	 * @param isCaseSensitive
	 */
	public boolean findMatchingString(String regExString, String inputString,
			boolean isCaseSensitive) {
		Matcher matcher = null;
		Pattern pattern = null;
		try {
			if (isCaseSensitive) {
				pattern = Pattern.compile(regExString);
			} else {
				pattern = Pattern
						.compile(regExString, Pattern.CASE_INSENSITIVE);
			}
			matcher = pattern.matcher(inputString);
			if (matcher.find()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String readFile(String path, Charset encoding) throws IOException {
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
		 }
	
	/**Method to get eml file location
	 * @param emailQueueId
	 * @return
	 * @throws Exception
	 */
	public static String getEMLFileLocation(int emailQueueId) throws Exception {
			OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
			ArrayList<Object> orderFileAttachments = new ArrayList<Object>();
			orderFileAttachments = orderEmailQueue.GetEmailBody(emailQueueId);
			Iterator<Object> iterat = orderFileAttachments.iterator();
			String MailbodyPath="";
			String MailBodyFileName="";
			while (iterat.hasNext()) {
				OrderFileAttachment orderFileAttachment = (OrderFileAttachment) iterat.next();
				if(orderFileAttachment!=null){
					MailbodyPath=orderFileAttachment.getFilePath();
					MailBodyFileName=orderFileAttachment.getFileName();
				}else{
					throw new Exception("No entry is found in the table orderEmailQueue for emailQueueId:\""+emailQueueId+"\".");
				}
			}
		  return MailbodyPath+File.separatorChar+MailBodyFileName;
	}
	
		
		/**
		 * method identifyAdditionalDataFile
		 * @param emailQueueId
		 * @param productline_id
		 * @throws Exception
		 */
		public void identifyAdditionalDataFile(int emailQueueId ) throws Exception {
			log.debug("identifyAdditionalDataFile for email queue\""+emailQueueId+"\".");
			OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
			ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(emailQueueId);
			log.debug("get attchments list for email id \"" + emailQueueId+"\".");
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment emailAttachments = new OrderFileAttachment();
			
			while (iterat.hasNext()) {
				
				ArrayList<Integer> selected_schema= new ArrayList<Integer>();
				String fileExt="";
				String fileName="";
				String keywordLocation="";
				String filePath="";
				emailAttachments = (OrderFileAttachment) iterat.next();
				fileName=emailAttachments.getFileName();
				fileExt=emailAttachments.getFileExtension();
				filePath=emailAttachments.getFilePath();
				int attachmentId =emailAttachments.getId();
				String productlineIdString =emailAttachments.getComment();
				if(productlineIdString==""||productlineIdString==null){
					return;
				}
				String[] productlineIdArr = productlineIdString.split(",");
				log.debug("processing attachment id \"" + attachmentId+"\".");
				for (String productlineIdStr : productlineIdArr) {
					if(productlineIdStr.isEmpty() || productlineIdStr == null){
						break;
					}
					int productlineId = Integer.parseInt(productlineIdStr);
					log.debug("productlineId \"" + productlineId+"\".");
				//for (String productlineIdArr : productlineId){
					//fetch product line info
					ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(productlineId);
					Partner_RBOProductLine produclineData = new Partner_RBOProductLine();
					Iterator<Object> iterator = partner_rboinfo.iterator();
					while (iterator.hasNext()) {
						log.debug("data fetching for product line id \"" + productlineId+"\".");
						produclineData = (Partner_RBOProductLine) iterator.next();
						
						if(produclineData.isAttachmentRequired() && produclineData.isAttachmentFileMatchRequired()){
							
							if(produclineData.getAttachmentFileOrderMatchLocation().contains("FileName")){
								log.debug("Processing attachment identification from file name.");
								String fileNamePattern = produclineData.getAttachmentFileOrderMatch();
								if(!fileNamePattern.isEmpty()){
									if (fileNamePattern.contains("|")||!fileNamePattern.isEmpty()) {
										String[] fileNamePattern_array = fileNamePattern.split("\\|");
										for (String completeFileName : fileNamePattern_array) {
											String[] fileName_array = completeFileName.split("\\.");
											String additionalFileName = fileName_array[0].trim();
											String fileNameExt = fileName_array[1].trim();
											additionalFileName=additionalFileName.replace("*", "(.*)");
											
											if(fileName.toLowerCase().matches(additionalFileName.toLowerCase()) && fileExt.contains(fileNameExt)){
												selected_schema.add(produclineData.getId());
												log.debug("file name match order_file_name   \""+ fileName+"\".");
											}else{
												log.debug("file name not match with order file name  \""+ fileName+"\".");
											}
										}
									}
								}else{
									log.debug("FileOrderMatch is empty.");
								}
							}else if(produclineData.getAttachmentFileOrderMatchLocation().equals("FileContent")){
								log.debug("Processing attachment identification from file Content.");
								String matchContent = produclineData.getAttachmentFileOrderMatch();
								if(!matchContent.isEmpty()){
									if(produclineData.getAttachmentFileNameExtension_1().contains(fileExt)){
										if(produclineData.getAttachmentFileNameExtension_1().contains("xls")){
											//method to search different values in excell at different location
											if(SearchContentInExcel(fileName,filePath,fileExt,matchContent, log)){
												keywordLocation="true";
											}
										
										}else if(produclineData.getAttachmentFileNameExtension_1().contains("pdf")){ 
											log.debug("searching file extension for pdf");
											String keyword = matchContent.trim();
											String[] keywordArray;
											if (keyword.contains("Value")){ 
												keywordArray = keyword.split(":");
												keyword=keywordArray[1];
												
											}
											if(!keyword.trim().isEmpty()){
												if(fileName.contains("CompleteEmail")){
													keywordLocation =searchContentFromMailBody(filePath, fileName, keyword, false);
												}else{
													keywordLocation = searchpdf(fileName, keyword, filePath);
												}
												log.debug("search in pdf for keyword\""+keyword+"\" file name\"" +fileName+ "\"filepath\""+filePath+"\".");
												log.debug("keywordLocation---"+keywordLocation);
											}else{
												log.debug("keyword is empty for pdf");
											}
										}
									}
									if(!keywordLocation.trim().isEmpty()){
										log.debug("Match found for product line id \""+ produclineData.getId()+"\".");
										selected_schema.add(produclineData.getId());
										keywordLocation="";
									}
								}
							}
						}else{
							log.debug("attachment required or attachment file match required is false for productline id \""+productlineId+"\".");
						}
					}
				}
				selected_schema = new ArrayList<Integer>(new LinkedHashSet<Integer>(selected_schema));
				if(selected_schema.size()==1){
					log.debug("match schema list \""+selected_schema+"\".");
					int schemaId = selected_schema.get(0);
					orderEmailQueue.updateOrderEmailAttachmentContent(attachmentId, schemaId, "8","","","","AdditionalData");
					selected_schema.clear();
				}else{
					log.debug("match schema list \""+selected_schema+"\".");
				}
			}
		}
}
