package com.avery.services;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;








import org.hibernate.HibernateException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;




/*aspose dependency removed
 * import com.aspose.pdf.Document;
import com.aspose.pdf.TextFragment;
import com.aspose.pdf.TextFragmentAbsorber;
import com.aspose.pdf.TextFragmentCollection;*/
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
import com.avery.utils.HibernateUtil;
/*created by Dipanshu
 * Date 22dec2016
 * scope to identify partner rbo productline from email body subject and attachment
 * update productline id and matches found in email
 * **/
public class OrderEmailQueueServices {
	
	///set directory path for attached files ///////////
	public static String directory;
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
	
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
			// log.info("Enter method OrderEmailSourceservice  class OrderEmailService");
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
			///diff email domain and name from source email
			/*if (email.contains("@")) {
				if (email.contains("<")) {
					String[] part = email.split("<");
					String[] s_email = part[1].split(">");
					email = s_email[0];
				}
				String[] p_email = email.split("@");
				emailId = p_email[0];
				Domain = p_email[1];
				partnerId = orderEmailQueue.getPartnerId(emailId, Domain);
				if (partnerId == 0) {
				//	log.error("Partner id not found");
					orderEmailQueue.updateOrderEmail(id,"4","","","","","unidentified partner");
					return;
				}
				
				if(email_list.size()<=0){
			
				orderEmailQueue.updateOrderEmail(id,"4","","","","","unidentified partner");
				return;
			}
				
				
			}*/
			ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(id);
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			if(email_list.size()<=0){
			//	log.error("No attachments found");
				orderEmailQueue.updateOrderEmail(id,"3","","","","","no attachment files");
				return;
			}
			total_attachments=email_list.size();
			productline_id=readEmailSubject(id, subject, email);
			if(productline_id==0){
				while (iterat.hasNext()) {
					email_att = (OrderFileAttachment) iterat.next();
					att_id = email_att.getId();
					String file_name = email_att.getFileName();
					String file_ext = email_att.getFileExtension();
					String file_path = email_att.getFilePath();
						if(file_name.contains("CompleteEmail.pdf")){
							/////read mailbody
							if(productline_id==0){
								productline_id = this.readEmailBody(id, file_path, file_name, email);
							}
						}else{
							/////read attachments
							if(productline_id==0){
								productline_id = this.readAttachment(id, att_id, file_path, file_name, file_ext, email);
						
							}
						}
				}
			}
			if(productline_id!=0){
				this.checkFileType(id, productline_id);	
			}
			
		} catch (HibernateException ex) {
			ex.printStackTrace();
			String error=ex.toString();
			orderEmailQueue.updateError("service hibernate error1",error);
			
		} catch (Exception e) {
			e.printStackTrace();
			String error=e.toString();
			orderEmailQueue.updateError("service hibernate error2",error);
			
		}
		
		//System.out.println("partner id " + partnerId);
		
	}
	/*
	 * read email subject to identify rbo product line
	 * done by Dipanshu Ahuja*/
	public int readEmailSubject(int id, String subject, String email){
		
		List<String> rbo_match = new ArrayList<String>();
		List<String> productline_match = new ArrayList<String>();
		List<Integer> schema_id = new ArrayList<Integer>();
		int productline_id=0;
		
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(email);
		Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
		Iterator<Object> iterator = partner_rboinfo.iterator();
		
		if(partner_rboinfo.size()<=0){
			
			orderEmailQueue.updateOrderEmail(id,"4","","","","","unidentified partner");
			orderEmailQueue.updateAllAttachment(id, productline_id, "6","unidentified  partner");
			return 0;
		}
		
		
		while (iterator.hasNext()) {
			rbodetails = (Partner_RBOProductLine) iterator.next();
			if(rbodetails.isEmailSubjectRBOMatchRequired()){
				String rbolist = rbodetails.getEmailSubjectRBOMatch();
				if (rbolist.contains("|")||!rbolist.isEmpty()) {
					String[] rbos = rbolist.split("\\|");
					
					for (String prbo : rbos) {
						if (subject.toLowerCase().contains(prbo.toLowerCase()) && !prbo.isEmpty()) {
							rbo_match.add(prbo);
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
							}else{
								schema_id.add(rbodetails.getId());
							}
						}
					}
				}
			}
			
			
		}
		schema_id = new ArrayList(new HashSet(schema_id));
		rbo_match = new ArrayList(new HashSet(rbo_match));
		productline_match = new ArrayList(new HashSet(productline_match));
		String rbo_string="";
		for (String s : rbo_match)
		{
		    rbo_string += s + "\t";
		}
		String pline_string="";
		for (String s : productline_match)
		{
			pline_string += s + "\t";
		}
		if (schema_id.size() == 0) {
			////no matches found
			orderEmailQueue.updateOrderEmail(id,"3","","","","","unidentified subject");
			orderEmailQueue.updateAllAttachment(id, productline_id, "6","unidentified  subject");
			return productline_id;
		}else if (schema_id.size() ==1) {
			////update id and match result
			orderEmailQueue.updateOrderEmail(id,"5",rbo_string,pline_string,"",""," from subject");
			////update attachment as identified
			Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
			for (Integer p_id : uniqe_p_ids) {
				orderEmailQueue.updateAllAttachment(id, p_id, "8","from subject");
				
				productline_id=p_id;
			}
			return productline_id;
		}else if (schema_id.size() == 2) {
			////2 matches found
			//System.out.println("11111");
			productline_id = this.read_attachment_ext(id, schema_id,rbo_string,pline_string);
			
			return productline_id;
		}else if (schema_id.size() >=2) {
			///multiple productline matches
			orderEmailQueue.updateOrderEmail(id,"3",rbo_string,pline_string,"","","identified multiple matches subject");
			orderEmailQueue.updateAllAttachment(id, productline_id, "6","identified multiple matches subject");
			return productline_id;
		}
			
		return productline_id;
	}
	/*
	 * read email body to identify rbo product line
	 * done by Dipanshu Ahuja*/
	public int readEmailBody(int id, String path,String filename, String email){
		List<String> rbo_match = new ArrayList<String>();
		List<String> productline_match = new ArrayList<String>();
		List<Integer> schema_id = new ArrayList<Integer>();
		int productline_id=0;
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(email);
		
		if(partner_rboinfo.size()<=0){
			
			orderEmailQueue.updateOrderEmail(id,"4","","","","","unidentified partner");
			return 0;
		}
		
		
		Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
		Iterator<Object> iterator = partner_rboinfo.iterator();
		
		while (iterator.hasNext()) {
			rbodetails = (Partner_RBOProductLine) iterator.next();
			if(rbodetails.isEmailBodyRBOMatchRequired()){
				String bodyrbolist = rbodetails.getFileRBOMatch();
				if (bodyrbolist.contains("|")) {
					String[] bodyrbo = bodyrbolist.split("\\|");
					for (String brbo : bodyrbo) {
						if (searchpdf(filename, brbo, path) != "") {
							rbo_match.add(brbo);
							if(rbodetails.isEmailBodyProductlineMatchRequired()){
								String bodyproductlines = rbodetails
										.getFileProductlineMatch();
								if (bodyproductlines.contains("|")) {
									String[] bodyPlines = bodyproductlines.split("\\|");
									for (String bPLines : bodyPlines) {
										if (searchpdf(filename, bPLines, path) != "") {
											productline_match.add(bPLines);
											schema_id.add(rbodetails.getId());
										}
									}
								}
							}else{
								schema_id.add(rbodetails.getId());
							}
						}
					}
				}
			}
			
			
		}
		schema_id = new ArrayList(new HashSet(schema_id));
		rbo_match = new ArrayList(new HashSet(rbo_match));
		productline_match = new ArrayList(new HashSet(productline_match));
		String rbo_string="";
		for (String s : rbo_match)
		{
		    rbo_string += s + "\t";
		}
		String pline_string="";
		for (String s : productline_match)
		{
			pline_string += s + "\t";
		}
		if (schema_id.size() == 0) {
			////no matches found
			orderEmailQueue.updateOrderEmail(id,"3","","","","","unidentified body");
			orderEmailQueue.updateAllAttachment(id, productline_id, "6","unidentified  body");
			return productline_id;
		}else if (schema_id.size() ==1) {
			////update id and match result
			
			orderEmailQueue.updateOrderEmail(id,"5",rbo_string,pline_string,"","","identified from body"+schema_id);
			////update attachment as identified
			Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
			for (Integer p_id : uniqe_p_ids) {
				orderEmailQueue.updateAllAttachment(id, p_id, "8","identified from body"+uniqe_p_ids);
				productline_id=p_id;
			}
			return productline_id;
		}else if (schema_id.size() == 2) {
			////2 matches found
			//System.out.print("1121542124");
			this.read_attachment_ext(id, schema_id,rbo_string,pline_string);
		}else if (schema_id.size() >=2) {
			///multiple productline matches
			orderEmailQueue.updateOrderEmail(id,"3",rbo_string,pline_string,"","","identified multiple matches in body");
			Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
			for (Integer p_id : uniqe_p_ids) {
				orderEmailQueue.updateAllAttachment(id, productline_id, "6"," multiple matches in body");
				
			}
			
			return productline_id;
		}
		//orderEmailQueue.updateAllAttachment(id, 0, "", "body");
		return productline_id;
	}
	/*
	 * read email attachment to identify rbo product line
	 * done by Dipanshu Ahuja*/
public int readAttachment(int id, int att_id, String file_path, String file_name, String file_ext, String email) {
		
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		int productline_rbo_id;
		String productlines ="";
		String rbolist ="";
		String Sheetname="";
		String keyword_location="";
		String cell_no ="";
		String cell_value ="";
		int productline_id=0;
		List<String> rbo_match = new ArrayList<String>();
		List<String> productline_match = new ArrayList<String>();
		List<Integer> schema_id = new ArrayList<Integer>();
			try {
				ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(email);
				if(partner_rboinfo.size()<=0){
					
					orderEmailQueue.updateOrderEmail(id,"4","","","","","unidentified partner");
					return 0;
				}
				Partner_RBOProductLine p_info = new Partner_RBOProductLine();
				Iterator<Object> iterator = partner_rboinfo.iterator();
				SearchCell read_att = new SearchCell(); 
				SearchCellAddress read_att_cell = new SearchCellAddress(); 
				while (iterator.hasNext()) {
					p_info = (Partner_RBOProductLine) iterator.next();
					//if(p_info.isAttachmentFileOrderMatchRequired()){
						//get data for partner rbo productline	
						productline_rbo_id=p_info.getId();
						if(p_info.isAttachmentFileOrderMatchRequired()){
							String Sheetinfo=p_info.getAttachmentFileOrderMatchLocation();
							if(!Sheetinfo.isEmpty()){
								if (Sheetinfo.contains(";")) {
									String[] Sheetdetails = Sheetinfo.split(";");
									for (String Sheetdetail : Sheetdetails) {
										String[] sheet_details;
										if (Sheetdetail.contains("SheetName")){ 
											sheet_details = Sheetdetail.split(":");
											Sheetname=sheet_details[1];
										}
										String[] cell_values;
										if (Sheetdetail.contains("Value")){ 
											cell_values = Sheetdetail.split(":");
											cell_value=cell_values[1];
										}
										String[] cell_nos;
										if (Sheetdetail.contains("Cell")){ 
											cell_nos = Sheetdetail.split(":");
											cell_no=cell_nos[1];
										}
									}
								}
								/////search value at a location/////////////////////
								keyword_location = read_att_cell.SearchXL(
										file_path, cell_value, file_ext,
										cell_no, file_name);
								if(!keyword_location.isEmpty()){
									schema_id.add(productline_rbo_id);
								}
							}
							if(p_info.isFileRBOMatchRequired()){
								rbolist = p_info.getFileRBOMatch();
									if (rbolist.contains("|")) {
										String[] a_rbo = rbolist.split("\\|");
										for (String arbo : a_rbo) {
											if (file_ext.equals(".xls")||file_ext.equals(".xlsx")) {
												keyword_location = read_att.SearchXL(
													file_path, arbo, file_ext,
													Sheetname, file_name);
												if (keyword_location != "") {
													rbo_match.add(arbo);
													schema_id.add(productline_rbo_id);
												}
											}
											if (file_ext == ".pdf") {
												if (searchpdf(file_name, arbo, file_path) != "") {
													rbo_match.add(arbo);
													schema_id.add(productline_rbo_id);
												
												}
											}
	
										}
									}
								}
							
								/////check product lines
								if(p_info.isFileProductLineMatchRequired()){
									productlines= p_info.getFileProductlineMatch();
									if (productlines.contains("|")) {
										String[] p_list = productlines.split("\\|");
										for (String pline : p_list) {
											if (file_ext.equals(".xls")||file_ext.equals(".xlsx")) {
												keyword_location = read_att.SearchXL(
													file_path, pline, file_ext,
													Sheetname, file_name);
												if (keyword_location != "") {
													productline_match.add(pline);
													schema_id.add(productline_rbo_id);
												}
											}
											if (file_ext == ".pdf") {
												if (searchpdf(file_name, pline, file_path) != "") {
													productline_match.add(pline);
													schema_id.add(productline_rbo_id);
												}
											}
	
										}
									}
								}
								
							}
						
				}
					///////remove duplicate from list and pass it in function to update
					//orderEmailQueue.updateOrderEmailAttachment();
				schema_id = new ArrayList(new HashSet(schema_id));
				rbo_match = new ArrayList(new HashSet(rbo_match));
				productline_match = new ArrayList(new HashSet(productline_match));
				
				if (schema_id.size() == 0) {
					////no matches found
					//System.out.println("789654");
					orderEmailQueue.updateOrderEmail(id,"3","","","","","unidentified attachment");
					orderEmailQueue.updateAllAttachment(id, productline_id, "6","unidentified  attachment");
					return productline_id;
				}else if (schema_id.size() ==1) {
					////update id and match result
					String rbo_string="";
					for (String s : rbo_match)
					{
					    rbo_string += s + "\t";
					}
					String pline_string="";
					for (String s : productline_match)
					{
						pline_string += s + "\t";
					}
					
					orderEmailQueue.updateOrderEmail(id,"5",rbo_string,pline_string,"","","identified from attachment");
					////update attachment as identified
					Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
					for (Integer p_id : uniqe_p_ids) {
						orderEmailQueue.updateAllAttachment(id, p_id, "8","identified from attachment");
						productline_id=p_id;
					}
					return productline_id;
				}else if (schema_id.size() >=1) {
					///multiple productline matches
					 
					String rbo_string="";
					for (String s : rbo_match)
					{
					    rbo_string += s + "\t";
					}
					String pline_string="";
					for (String s : productline_match)
					{
						pline_string += s + "\t";
					}
					
					orderEmailQueue.updateOrderEmail(id,"3",rbo_string,pline_string,"","","identified multiple matchesfrom attachment");
					Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
					for (Integer p_id : uniqe_p_ids) {
						orderEmailQueue.updateAllAttachment(id, productline_id,"6", "identified multiple matchesfrom attachment");
						
					}
					return productline_id;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return productline_id;
		
	}
/*
 * removed aspose dependency
 * discussed by all
 * done by Dipanshu Ahuja*/
	/*public String searchpdf(String filename, String keyword, String filepath) {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		String result = "";
		try{
			if (filepath == "") {
				filepath = directory;
			}
			Document pdfDocument = new Document(filepath + filename);
	
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
				/*
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
				 *  /
			}
		}  catch (Exception e) {
			String error=e.toString();
			orderEmailQueue.updateError("service hibernate error3",error);
			System.out.println("searchpdf class error11");
			// log.error(e);
		}
		return result;
	}*/
/*
 * identify order email and attachments
 * done by Dipanshu Ahuja*/
	public void checkFileType(int email_id, int productline_id) {
		String orderfilename="";
		String orderfileext="";
		//System.out.println("info "+productline_id);
		String file_status="unidentified";
		int att_id=0;
		String attachmentfilename1="";
		String attachmentfilename2="";
		String attachmentfilename3="";
		String attachmentfilename4="";
		String attachmentfileext1="";
		String attachmentfileext2="";
		String attachmentfileext3="";
		String attachmentfileext4="";
		Boolean attachmentRequired=false;
		Boolean mailbody=false;
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		try {
			ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(productline_id);
			Partner_RBOProductLine p_info = new Partner_RBOProductLine();
			Iterator<Object> iterator = partner_rboinfo.iterator();
			///get productline details
			while (iterator.hasNext()) {
				p_info = (Partner_RBOProductLine) iterator.next();
				attachmentRequired=p_info.isAttachmentRequired();
				orderfilename=p_info.getOrderFileNamePattern();
				orderfileext=p_info.getOrderFileNameExtension();
				mailbody=p_info.isOrderInMailBody();
				attachmentfilename1=p_info.getAttachmentFileNamePattern_1();
				attachmentfilename2=p_info.getAttachmentFileNamePattern_2();
				attachmentfilename3=p_info.getAttachmentFileNamePattern_3();
				attachmentfilename4=p_info.getAttachmentFileNamePattern_4();
				attachmentfileext1=p_info.getAttachmentFileNameExtension_1();
				attachmentfileext2=p_info.getAttachmentFileNameExtension_2();
				attachmentfileext3=p_info.getAttachmentFileNameExtension_3();
				attachmentfileext4=p_info.getAttachmentFileNameExtension_4();
			}
			///get all attachments for a email  and update accordingly
			ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(email_id);
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			while (iterat.hasNext()) {
				file_status="";
				email_att = (OrderFileAttachment) iterat.next();
				att_id = email_att.getId();
				
				if(!email_att.getFileName().contains("CompleteEmail")){
					if(!orderfilename.isEmpty()){
						if(email_att.getFileName().contains(orderfilename) || orderfileext.contains(email_att.getFileExtension())){
							////update file as order file
								file_status= "Order";
						}
					}else{
						//System.out.println("ext     "+email_att.getFileExtension());
						if(orderfileext.contains(email_att.getFileExtension())){
							////update file as order file
								file_status= "Order";
						}
					}
					
					 if(attachmentRequired){
						 
						if(email_att.getFileName().contains(attachmentfilename1)&& attachmentfileext1.contains(email_att.getFileExtension())&& !attachmentfilename1.isEmpty()&&!attachmentfileext1.isEmpty()){
						////update file as attachment file1
							file_status= "AdditionalData";
						}else if(email_att.getFileName().contains(attachmentfilename2)&& attachmentfileext2.contains(email_att.getFileExtension())&& !attachmentfilename2.isEmpty()&&!attachmentfileext2.isEmpty()){
						////update file as attachment file2
							file_status= "AdditionalData";
						}else if(email_att.getFileName().contains(attachmentfilename3)&& attachmentfileext3.contains(email_att.getFileExtension())&& !attachmentfilename3.isEmpty()&&!attachmentfileext3.isEmpty()){
						////update file as attachment file3
							file_status= "AdditionalData";
						}else if(email_att.getFileName().contains(attachmentfilename4)&& attachmentfileext4.contains(email_att.getFileExtension())&& !attachmentfilename4.isEmpty()&&!attachmentfileext4.isEmpty()){
						////update file as attachment file4
							file_status= "AdditionalData";
						}
					}
					
					
				}else if(mailbody){
					////update file as order file
						file_status= "Order";
				}else if(email_att.getFileName().contains("CompleteEmail")&& !mailbody){
					file_status= "Disregarded";
				}
				if(!orderEmailQueue.updateAttachmenttype(att_id, file_status)){
					/////log error for id
					//log.error("error while update attachment type for attachment id "+att_id);
				}
			}
		}catch(NullPointerException e){
			e.printStackTrace();
           // System.out.print("NullPointerException caught");
        }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	/*
	 * new method to search string in pdf file 
	 * with api itext
	 * done by Dipanshu Ahuja*/
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
	/*
	 * read email ext to identify email productline_id
	 * done by Dipanshu Ahuja*/
	public int read_attachment_ext(int orderEmailId, List schema_id,String rbo_string,String pline_string) {
		List<Integer> ext_schema_id = new ArrayList<Integer>();
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		int productline_id=0;
		ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(orderEmailId);
		Iterator<Object> iterat = email_list.iterator();
		OrderFileAttachment email_att = new OrderFileAttachment();
		while (iterat.hasNext()) {
			email_att = (OrderFileAttachment) iterat.next();
			int att_id = email_att.getId();
			//System.out.println("att_id"+att_id);
			String file_name = email_att.getFileName();
			String file_ext = email_att.getFileExtension();
			Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
			for (Integer p_id : uniqe_p_ids) {
				ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(p_id);
				Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
				Iterator<Object> iterator = partner_rboinfo.iterator();
				Partner_RBOProductLine p_info = new Partner_RBOProductLine();
				while (iterator.hasNext()) {
					p_info = (Partner_RBOProductLine) iterator.next();
					if(!p_info.isOrderInMailBody() && !file_name.contains("CompleteEmail") ){
						if(p_info.getOrderFileNameExtension().contains(file_ext)){
							ext_schema_id.add(p_info.getId());
						}
					}
				}
			}
		}
		ext_schema_id = new ArrayList(new HashSet(ext_schema_id));
		
		if (ext_schema_id.size() == 0) {
			////no matches found
			//System.out.println("1111");
			productline_id=0;
			orderEmailQueue.updateOrderEmail(orderEmailId,"3","","","","","unidentified attachment");
			orderEmailQueue.updateAllAttachment(orderEmailId, productline_id, "6","unidentified  attachment");
			return productline_id;
		}else if (ext_schema_id.size() ==1) {
			//System.out.println(ext_schema_id);
			////update id and match result
			orderEmailQueue.updateOrderEmail(orderEmailId,"5",rbo_string,pline_string,"","","identified by order file ext");
			////update attachment as identified
			Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(ext_schema_id);
			for (Integer p_id : uniqe_p_ids) {
				orderEmailQueue.updateAllAttachment(orderEmailId, p_id,"8", "identified by order file ext");
				this.checkFileType(orderEmailId, p_id);
				productline_id=p_id;
			}
			return productline_id;
		}else if (ext_schema_id.size() >=1) {
			///multiple productline matches
			orderEmailQueue.updateOrderEmail(orderEmailId,"3",rbo_string,pline_string,"","","identified multiple matches extention");
			orderEmailQueue.updateAllAttachment(orderEmailId, productline_id,"6", "identified multiple matches extention");
			return productline_id;
		}
		return productline_id;
		//return true;
	}
	/**identify ****/
	
	public boolean getemaildetail(int orderfileQueueId) {
		Partner_RBOProductLine partner_RBOProductLine= new Partner_RBOProductLine();
		
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		int att_id = orderEmailQueue.GeteAttachmentId(orderfileQueueId);
		int EmailQueueId = orderEmailQueue.GetOrderEmailQueueId(att_id);
		int schema_id=0;
		String file_name="";
		String file_ext="";
		String file_path ="";
		String subject = this.getemailsubject(EmailQueueId);
		//ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(EmailQueueId);
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
		ArrayList<String> keywords= this.getkeyword(schema_id);
		Iterator iterator = keywords.iterator();
		while (iterator.hasNext()) {
			String keyword = (String) iterator.next();
			if(subject.toLowerCase().contains(keyword.toLowerCase())|| !searchpdf(file_name, keyword, file_path).isEmpty()){
				return true;
			}
		}
		return false;
	}
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
}
