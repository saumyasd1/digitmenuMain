package com.avery.services;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;

import org.apache.log4j.Logger;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.Model.SearchCell;
import com.avery.Model.SearchCellAddress;
import com.avery.dao.OrderFileAttachment;
import com.avery.dao.Partner_RBOProductLine;

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
	
	/**method OrderEmailSourceservice
	 * @param dir
	 * @param id
	 */
	public void OrderEmailSourceservice(String dir,int id) {
		////assign value to directory path
		this.directory = dir;
		String email = "";
		String subject = "";
		String emailId = "";
		String Domain = "";
		int partnerId=0;
		int total_attachments=0;
		int count=0;
		int att_id=0;
		int productline_id=0;
		///create object of model class to access its methods
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		try {
			//log.error("Enter method OrderEmailSourceservice  class OrderEmailService");
			///////get values of email 
			HashMap<String, String> emailinfo = orderEmailQueue.EmailSource(id);
			Iterator it = emailinfo.entrySet().iterator();
			////get email subject and source 
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();

				if (pair.getKey() == "source") {
					email = (String) pair.getValue();
				}
				if (pair.getKey() == "subject") {
					subject = (String) pair.getValue();
				}
			}
			
			ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(id);
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			if(email_list.size()<=0){
			//	log.error("No attachments found");
				orderEmailQueue.updateOrderEmail(id,"3","","","","","no attachment files");
				return;
			}
			total_attachments=email_list.size();
			//loop to iterate all attachment and perform operation
			while (iterat.hasNext()) {
				email_att = (OrderFileAttachment) iterat.next();
				att_id = email_att.getId();
				String file_name = email_att.getFileName();
				String file_ext = email_att.getFileExtension();
				String file_path = email_att.getFilePath();
				productline_id =identifyAttachment(id, att_id, file_path, file_name, file_ext, email);
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	public void readEmailSubject(int id, int attachment_id, List<Integer> productline_id ){
		
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
			log.info("subject is empty");
			return ;
		}
		System.out.println("subject "+subject);
		//get product line id in loop and check
		for (Integer p_id : productline_id){
			System.out.println("p_id "+p_id);
			//fetch productline info
			ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(p_id);
			Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
			Iterator<Object> iterator = partner_rboinfo.iterator();
			while (iterator.hasNext()) {
				rbodetails = (Partner_RBOProductLine) iterator.next();
				if(rbodetails.isEmailSubjectRBOMatchRequired()){
					String rbolist = rbodetails.getEmailSubjectRBOMatch();
					System.out.println("rbolist "+rbolist);
					if (rbolist.contains("|")||!rbolist.isEmpty()) {
						String[] rbos = rbolist.split("\\|");
						
						for (String prbo : rbos) {
							System.out.println("prbo "+prbo);
							if (subject.toLowerCase().contains(prbo.toLowerCase()) && !prbo.isEmpty()) {
								rbo_match.add(prbo);
								schema_id.add(rbodetails.getId());
							}
						}
					}
				}
				if(rbodetails.isEmailSubjectProductlineMatchRequired()){
					String productlines = rbodetails.getEmailSubjectProductLineMatch();
					if (productlines.contains("|")||!productlines.isEmpty()) {
						String[] pline = productlines.split("\\|");
						for (String productline : pline) {
							if(!productline.isEmpty()){
								if (subject.toLowerCase().contains(productline.toLowerCase())) {
									productline_match.add(productline);
									schema_id.add(rbodetails.getId());
									
								}
							}
						}
					}
				}
			}
		}
		schema_id = new ArrayList(new HashSet(schema_id));
		rbo_match = new ArrayList(new HashSet(rbo_match));
		productline_match = new ArrayList(new HashSet(productline_match));
		System.out.println("schema_id"+schema_id);
		System.out.println("rbo_match"+rbo_match);
		System.out.println("productline_match"+productline_match);
		
		for (String s : rbo_match)
		{
			subjectRbo += s + ",";
		}
		
		for (String s : productline_match)
		{
			
			subjectProductline += s + ",";
		}
		orderEmailQueue.updateOrderEmail(id,"3",subjectRbo,subjectProductline,"","","");
	}
	/**
	 * method EmailBodyAnalysis
	 * @param id
	 * @param attachment_id
	 * @param productline_id
	 */
	public void EmailBodyAnalysis(int id, int attachment_id, List<Integer> productline_id ){
			
		List<String> rbo_match = new ArrayList<String>();
		List<String> productline_match = new ArrayList<String>();
		List<Integer> schema_id = new ArrayList<Integer>();
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(id);
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
				attachment_id=email_att.getId();
				fileName=email_att.getFileName();
				file_path=email_att.getFilePath();
				fileContentMatch=email_att.getFileContentMatch();
				att_status = email_att.getStatus();
				comment=email_att.getComment();
				
			
				if(fileContentMatch==null||fileContentMatch.trim().isEmpty()){
					fileContentMatch="";
				}
				if(comment==null||comment.trim().isEmpty()){
					comment="";
				}
				System.out.println("fileName "+fileName);
				System.out.println("file_path "+file_path);
				System.out.println("comment "+comment);
				//get product line id in loop and check
				for (Integer p_id : productline_id){
					System.out.println("p_id "+p_id);
					//fetch productline info
					ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(p_id);
					Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
					Iterator<Object> iterator = partner_rboinfo.iterator();
					while (iterator.hasNext()) {
						rbodetails = (Partner_RBOProductLine) iterator.next();
						if(rbodetails.isEmailBodyRBOMatchRequired()){
							String rbolist = rbodetails.getEmailBodyRBOMatch();
							System.out.println("rbolist "+rbolist);
							if (rbolist.contains("|")||!rbolist.isEmpty()) {
								String[] rbos = rbolist.split("\\|");
								
								for (String prbo : rbos) {
									System.out.println("prbo "+prbo);
									result="";
									if(!prbo.trim().isEmpty()){
										prbo=prbo.trim();
										result= searchpdf(fileName,prbo,file_path);
									}
									if (!result.isEmpty()) {
										rbo_match.add(prbo);
										schema_id.add(rbodetails.getId());
									}
								}
							}
						}
						if(rbodetails.isEmailBodyProductlineMatchRequired()){
							String productlines = rbodetails.getEmailBodyProductLineMatch();
							if (productlines.contains("|")||!productlines.isEmpty()) {
								String[] pline = productlines.split("\\|");
								for (String productline : pline) {
									if(!productline.trim().isEmpty()){
										result="";
										productline=productline.trim();
										result= searchpdf(fileName,productline,file_path);
										if (!result.isEmpty()) {
											productline_match.add(productline);
											schema_id.add(rbodetails.getId());
											
										}
									}
								}
							}
						}
					}
				}
				schema_id = new ArrayList(new HashSet(schema_id));
				rbo_match = new ArrayList(new HashSet(rbo_match));
				productline_match = new ArrayList(new HashSet(productline_match));
				System.out.println("schema_id"+schema_id);
				System.out.println("rbo_match"+rbo_match);
				System.out.println("productline_match"+productline_match);
				
				for (String s : rbo_match)
				{
					fileContentMatch += s + ",";
				}
				
				for (String s : productline_match)
				{
					
					fileContentMatch += s + ",";
				}
				int att_productline_id=0;
				//System.out.println("attachment_id"+attachment_id);
				//System.out.println("comment"+comment);
				orderEmailQueue.updateOrderEmailAttachment(attachment_id, att_productline_id, "6","","",comment,fileContentMatch);
			}
		}//orderEmailQueue.updateOrderEmailAttachment(id,"3",subjectRbo,subjectProductline,"","","");
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
	 */
	public String searchpdf(String filename, String keyword, String filepath) {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		String result = "";
		try{
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
	            }
	        }
	        reader.close();
	        out.flush();
	        out.close();
			/*Document pdfDocument = new Document(filepath + filename);
	
			// Create TextAbsorber object to find all instances of the input search
			// phrase
			TextFragmentAbsorber textFragmentAbsorber = new TextFragmentAbsorber(
					keyword);
			// Accept the absorber for all the pages
			pdfDocument.getPages().accept(textFragmentAbsorber);
			// Get the extracted text fragments into collection
			TextFragmentCollection textFragmentCollection = textFragmentAbsorber
					.getTextFragments();
			// Loop through the fragments
			for (TextFragment textFragment : (Iterable<TextFragment>) textFragmentCollection) {
				result = textFragment.getText();
				
				 * System.out.println("Text :- " + textFragment.getText());
				 * System.out.println("Position :- " + textFragment.getPosition());
				 * System.out.println("XIndent :- " +
				 * textFragment.getPosition().getXIndent());
				 * System.out.println("YIndent :- " +
				 * textFragment.getPosition().getYIndent());
				 * System.out.println("Font - Name :- " +
				 * textFragment.getTextState().getFont().getFontName());
				 * System.out.println("Font - IsAccessible :- " +
				 * textFragment.getTextState().getFont().isAccessible());
				 * System.out.println("Font - IsEmbedded - " +
				 * textFragment.getTextState().getFont().isEmbedded());
				 * System.out.println("Font - IsSubset :- " +
				 * textFragment.getTextState().getFont().isSubset());
				 * System.out.println("Font Size :- " +
				 * textFragment.getTextState().getFontSize());
				 * System.out.println("Foreground Color :- " +
				 * textFragment.getTextState().getForegroundColor());
				 
			}*/
		}catch (Exception e) {
			e.printStackTrace();
			String error=e.toString();
			orderEmailQueue.updateError("service hibernate error",error);
		
		}
		return result;
	}
	
	/**identify ****/
	
	/**
	 * method getemaildetail
	 * @param orderfileQueueId
	 * @return boolean
	 * purpose to check revise cancel order in subject or email body
	 */
	public boolean getemaildetail(int orderfileQueueId) {
		Partner_RBOProductLine partner_RBOProductLine= new Partner_RBOProductLine();
		int schema_id=0;
		int EmailQueueId=0;
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		//// get attachment id from orderfile queue 
		int att_id = orderEmailQueue.GeteAttachmentId(orderfileQueueId);
		HashMap<String, Integer> email_info =  orderEmailQueue.GetOrderEmailQueueId(att_id);
		Iterator it = email_info.entrySet().iterator();
		////get order email queue id and schema id from order file attachment 
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			if (pair.getKey() == "emailQueue_id") {
				EmailQueueId = (int) pair.getValue();
			}
			if (pair.getKey() == "schema_id") {
				schema_id =  (int)pair.getValue();
			}
		}
		String file_name="";
		String file_ext="";
		String file_path ="";
		String subject = this.getemailsubject(EmailQueueId);
		if(subject==null||subject.trim().isEmpty()){
			return false;
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
				partner_RBOProductLine = email_att.getVarProductLine();
				schema_id= partner_RBOProductLine.getId();
			}
		}
		ArrayList<String> keywords= getkeyword(schema_id);
		Iterator iterator = keywords.iterator();
		while (iterator.hasNext()) {
			String keyword = (String) iterator.next();
			if(subject.toLowerCase().contains(keyword.toLowerCase())|| !searchpdf(file_name, keyword, file_path).isEmpty()){
				return true;
			}
		}
		return false;
	}
	/**
	 * method getemailsubject
	 * @param emailid
	 * @return email subject as string
	 */
	public String getemailsubject(int emailid) {
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
		return subject;
	}
	
	/**method getkeyword
	 * @param schema_id
	 * @return list of keywords from partner rbo productline table
	 */
	public ArrayList getkeyword(int schema_id) {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		
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
 */
	public int identifyAttachment(int id, int att_id, String file_path, String file_name, String file_ext, String email) {
		
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		int productline_rbo_id;
		String productlines ="";
		String rbolist ="";
		String Sheetname="";
		String keyword_location="";
		String cell_no ="";
		String cell_value ="";
		String status="unidentified";
		String schema_id_comment="";
		ArrayList<Integer> selected_schema= new ArrayList<Integer>();
		int productline_id=0;
			try {
				ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(email);
				if(partner_rboinfo.size()<=0){
					///update for unrecoginzed
					orderEmailQueue.updateOrderEmail(id,"4","","","","","");
					orderEmailQueue.updateAllAttachment(id, productline_id, "6","");
					return 0;
				}
				Partner_RBOProductLine p_info = new Partner_RBOProductLine();
				Iterator<Object> iterator = partner_rboinfo.iterator();
				SearchCell read_att = new SearchCell(); 
				SearchCellAddress read_att_cell = new SearchCellAddress(); 
				String[] o_file_name_pattern = file_name.split("\\.");
				String order_file_name = o_file_name_pattern[0];
				String order_file_ext = o_file_name_pattern[1];
				while (iterator.hasNext()) {
					keyword_location="";
					p_info = (Partner_RBOProductLine) iterator.next();
					productline_rbo_id=p_info.getId();
					//System.out.println("productline_rbo_id  "+ productline_rbo_id);
					if(schema_id_comment.isEmpty()){
						schema_id_comment = "" +productline_rbo_id;
					}else{
						schema_id_comment=schema_id_comment+","+productline_rbo_id;
					}
					if(p_info.isFileOrderMatchRequired()){
						
						if(p_info.getFileOrderMatchLocation().contains("FileName")){
							
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
											//System.out.println("shortlisted id1  "+ productline_rbo_id);
										}
									}
								}
							}
						}else if(p_info.getFileOrderMatchLocation().equals("FileContent")){
							String Sheetinfo = p_info.getFileOrderMatch();
							if(!Sheetinfo.isEmpty()){
								if(p_info.getOrderFileNameExtension().contains(file_ext)){
									if(p_info.getOrderFileNameExtension().contains("xls")){
										//System.out.println("p_info.getOrderFileNameExtension()  "+ p_info.getOrderFileNameExtension());
										
										if (Sheetinfo.contains(";")) {
											String[] Sheetdetails = Sheetinfo.split(";");
											for (String Sheetdetail : Sheetdetails) {
												String[] cell_values;
												if (Sheetdetail.contains("Value")){ 
													cell_values = Sheetdetail.split(":");
													cell_value=cell_values[1];
													cell_value=cell_value.replace("*", "(.*)");
												}
												String[] cell_nos;
												if (Sheetdetail.contains("Cell")){ 
													cell_nos = Sheetdetail.split(":");
													cell_no=cell_nos[1];
												}
											}
											if(!cell_value.isEmpty()&&!cell_no.isEmpty()){
												/*System.out.println("file_path"+file_path);
												System.out.println("cell_value"+cell_value);
												System.out.println("file_ext"+file_ext);
												System.out.println("cell_no"+cell_no);
												System.out.println("file_name  11  "+file_name);*/
												
												keyword_location = read_att_cell.SearchXL(
														file_path, cell_value, file_ext,
														cell_no, file_name);
												//System.out.println("keyword_location"+keyword_location);
											}else{
												log.info("cell value and call number are empty");
											}
											
										}
									
									}else if(file_ext.contains("pdf")){ 
										String keyword = Sheetinfo.trim();
										keyword=keyword.replace("*", "(.*)");
										
										if(!keyword_location.trim().isEmpty()){
											keyword_location =searchpdf(file_name, keyword, file_path);
											
										}else{
											log.info("keyword is empty for pdf");
										}
									}
								}
								if(!keyword_location.trim().isEmpty()){
									selected_schema.add(productline_rbo_id);
									/*System.out.println("keyword  "+ cell_value);
									System.out.println("file_name  "+ file_name);
									System.out.println("file_path  "+ file_path);
									System.out.println("selected_schema 22  "+ productline_rbo_id);
									System.out.println("keyword_location 22  "+ keyword_location);*/
									//orderEmailQueue.updateOrderEmailAttachment(att_id, productline_rbo_id, "8","","","attachment identified");
									//productline_id=productline_rbo_id;
								}
							}
						}
					}
				}
				if(selected_schema.size()==1){
					
					productline_rbo_id = selected_schema.get(0);
					//readEmailSubject(id, att_id, selected_schema);
					orderEmailQueue.updateOrderEmailAttachmentContent(att_id, productline_rbo_id, "8","","","","Order");
					readEmailSubject(id,att_id,selected_schema);
					EmailBodyAnalysis(id,att_id,selected_schema);
				}else if(selected_schema.size()==0){
					orderEmailQueue.updateOrderEmailAttachment(att_id, productline_id, "6","","",schema_id_comment,"");
				}else{
					String schema="";
					for (Integer ids : selected_schema)
					{
						schema += ids + ",";
					}
					//readEmailSubject(id, att_id, selected_schema);
					orderEmailQueue.updateOrderEmailAttachment(att_id, productline_id, "6","","",schema,"");
					readEmailSubject(id,att_id,selected_schema);
					EmailBodyAnalysis(id,att_id,selected_schema);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return 0;
	}
	
	
	/**
	 * method GetOrderEmailQueueStatus
	 * @param orderemailqueue_id
	 * @return
	 * purpose to update order emaile queue on the basis of attachment
	 */
	public int GetOrderEmailQueueStatus(int orderemailqueue_id){
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
	 */
	public void updateOrderEmailStatus(int orderemailqueue_id, int Status){
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		String Status_s =""+Status;
		orderEmailQueue.updateOrderEmail(orderemailqueue_id,Status_s,"","","","","");
		
	}
}
