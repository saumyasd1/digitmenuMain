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


import org.apache.log4j.Logger;
/*import com.aspose.pdf.Document;
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
/*created by Dipanshu
 * Date 22dec2016
 * scope to identify partner rbo productline from email body subject and attachment
 * update productline id and matches found in email
 * **/
public class OrderEmailQueueServices {
	
	///set directory path for attached files ///////////
	public static String directory;
//	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
	
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
			if (email.contains("@")) {
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
					orderEmailQueue.updateOrderEmail(id,"unidentified partner","","","","");
					return;
				}
			}
			//System.out.println("partner id11 " + partnerId);
			ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(id);
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			//System.out.println("partner id22 " + partnerId);
			if(email_list.size()<=0){
			//	log.error("No attachments found");
				orderEmailQueue.updateOrderEmail(id,"no attachment files","","","","");
				return;
			}
			//System.out.println("partner id33 " + partnerId);
			total_attachments=email_list.size();
			//System.out.println("partner id3311 " + partnerId);
			productline_id=readEmailSubject(id, subject, partnerId);
			//System.out.println("partner id33 " + productline_id);
			if(productline_id==0){
				//System.out.println("partner id33xx " + partnerId);
				while (iterat.hasNext()) {
					email_att = (OrderFileAttachment) iterat.next();
					att_id = email_att.getId();
					String file_name = email_att.getFileName();
					String file_ext = email_att.getFileExtension();
					String file_path = email_att.getFilePath();
					//System.out.println("file_name id11 " + file_name);
						if(file_name.contains("MailBody.pdf")){
							/////read mailbody
							productline_id = this.readEmailBody(id, file_path, file_name, partnerId);
						}else{
							/////read attachments
							if(productline_id==0){
								productline_id = this.readAttachment(id, att_id, file_path, file_name, file_ext, partnerId);
						
							}
						}
						//System.out.println("partner id3355 " + partnerId);
						
						//System.out.println("partner id335511 " + partnerId);
				}
			}
			if(productline_id!=0){
				this.checkFileType(id, productline_id);	
			}
		//	System.out.println("partner id3355 " + productline_id);
			
		} catch (HibernateException ex) {
			ex.printStackTrace();
			String error=ex.toString();
			orderEmailQueue.updateError("service hibernate error1",error);
		//	 log.error(ex);
		} catch (Exception e) {
			e.printStackTrace();
			String error=e.toString();
			orderEmailQueue.updateError("service hibernate error2",error);
			// log.error(e);
		}
		
		System.out.println("partner id " + partnerId);
		
	}
	
	public int readEmailSubject(int id, String subject, int partnerId){
		
		List<String> rbo_match = new ArrayList<String>();
		List<String> productline_match = new ArrayList<String>();
		List<Integer> schema_id = new ArrayList<Integer>();
		int productline_id=0;
		
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		
		ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(partnerId);
		Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
		Iterator<Object> iterator = partner_rboinfo.iterator();
		
		while (iterator.hasNext()) {
			rbodetails = (Partner_RBOProductLine) iterator.next();
			
			if(rbodetails.isEmailSubjectRBOMatchRequired()){
				String rbolist = rbodetails.getEmailSubjectRBOMatch();
				//System.out.println("schema_id id3355 " + rbodetails.getId());
				if (rbolist.contains("|")||!rbolist.isEmpty()) {
					String[] rbos = rbolist.split("\\|");
					for (String prbo : rbos) {
						if (subject.toLowerCase().contains(prbo.toLowerCase())) {
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
						if (subject.toLowerCase().contains(productline.toLowerCase())) {
							productline_match.add(productline);
							schema_id.add(rbodetails.getId());
						}
					}
				}
			}
		}
		schema_id = new ArrayList(new HashSet(schema_id));
		rbo_match = new ArrayList(new HashSet(rbo_match));
		productline_match = new ArrayList(new HashSet(productline_match));
		
		if (schema_id.size() == 0) {
			////no matches found
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
			
			orderEmailQueue.updateOrderEmail(id,"identified from subject",rbo_string,pline_string,"","");
			////update attachment as identified
			Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
			for (Integer p_id : uniqe_p_ids) {
				orderEmailQueue.updateAllAttachment(id, p_id, "identified from subject");
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
			
			orderEmailQueue.updateOrderEmail(id,"identified multiple matches",rbo_string,pline_string,"","");
			return productline_id;
		}
			
		return productline_id;
	}
	public int readEmailBody(int id, String path,String filename, int partnerId){
		List<String> rbo_match = new ArrayList<String>();
		List<String> productline_match = new ArrayList<String>();
		List<Integer> schema_id = new ArrayList<Integer>();
		int productline_id=0;
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(partnerId);
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
							schema_id.add(rbodetails.getId());
						}
					}
				}
			}
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
			}
		}
		schema_id = new ArrayList(new HashSet(schema_id));
		rbo_match = new ArrayList(new HashSet(rbo_match));
		productline_match = new ArrayList(new HashSet(productline_match));
		
		if (schema_id.size() == 0) {
			////no matches found
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
			
			orderEmailQueue.updateOrderEmail(id,"identified from body",rbo_string,pline_string,"","");
			////update attachment as identified
			Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
			for (Integer p_id : uniqe_p_ids) {
				orderEmailQueue.updateAllAttachment(id, p_id, "identified from body");
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
			
			orderEmailQueue.updateOrderEmail(id,"identified multiple matches in body",rbo_string,pline_string,"","");
			return productline_id;
		}
		//orderEmailQueue.updateAllAttachment(id, 0, "", "body");
		return productline_id;
	}
public int readAttachment(int id, int att_id, String file_path, String file_name, String file_ext, int partnerId) {
		
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
				ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(partnerId);
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
													System.out.println("keyword_location"+"\t" + keyword_location);
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
													System.out.println("keyword_location"+"\t" + keyword_location);
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
						
							//System.out.println("\t" + schema_id);
							//System.out.println(productline_info.);
					//	}
				}
				//System.out.println("test123" + schema_id);
					///////remove duplicate from list and pass it in function to update
					//orderEmailQueue.updateOrderEmailAttachment();
				schema_id = new ArrayList(new HashSet(schema_id));
				rbo_match = new ArrayList(new HashSet(rbo_match));
				productline_match = new ArrayList(new HashSet(productline_match));
				
				if (schema_id.size() == 0) {
					////no matches found
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
					
					orderEmailQueue.updateOrderEmail(id,"identified from attachment",rbo_string,pline_string,"","");
					////update attachment as identified
					Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(schema_id);
					for (Integer p_id : uniqe_p_ids) {
						orderEmailQueue.updateAllAttachment(id, p_id, "identified from attachment");
						productline_id=0;
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
					
					orderEmailQueue.updateOrderEmail(id,"identified multiple matchesfrom attachment",rbo_string,pline_string,"","");
					return productline_id;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//log.error("Search Cell 1."+e.getMessage(),e);
			}
			return productline_id;
		
	}
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
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		try {
			ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartner_productline(productline_id);
			Partner_RBOProductLine p_info = new Partner_RBOProductLine();
			Iterator<Object> iterator = partner_rboinfo.iterator();
			//System.out.println("info size "+partner_rboinfo.size());
			///get productline details
			while (iterator.hasNext()) {
				p_info = (Partner_RBOProductLine) iterator.next();
				attachmentRequired=p_info.isAttachmentRequired();
				orderfilename=p_info.getOrderFileNamePattern();
				orderfileext=p_info.getOrderFileNameExtension();
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
			//System.out.println("attachmentRequired size "+attachmentRequired);
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			while (iterat.hasNext()) {
				file_status="";
				email_att = (OrderFileAttachment) iterat.next();
				att_id = email_att.getId();
				//System.out.println("email_list size "+email_att.getFileName());
				if(email_att.getFileName().contains(orderfilename) || email_att.getFileExtension().contains(orderfileext)){
				////update file as order file
					file_status= "order file";
				}else if(attachmentRequired){
					if(email_att.getFileName().contains(attachmentfilename1)&& email_att.getFileExtension().contains(attachmentfileext1)){
					////update file as attachment file1
						file_status= "attachment file1";
					}else if(email_att.getFileName().contains(attachmentfilename2)&& email_att.getFileExtension().contains(attachmentfileext2)){
					////update file as attachment file2
						file_status= "attachment file2";
					}else if(email_att.getFileName().contains(attachmentfilename3)&& email_att.getFileExtension().contains(attachmentfileext3)){
					////update file as attachment file3
						file_status= "attachment file3";
					}else if(email_att.getFileName().contains(attachmentfilename4)&& email_att.getFileExtension().contains(attachmentfileext4)){
					////update file as attachment file4
						file_status= "attachment file4";
					}
				}
			//	System.out.println("file_status size 1144"+file_status);
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
		//System.out.println("file_status size 11"+file_status);
		return;
	}
	public String searchpdf(String filename, String keyword, String filepath) {
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		String result = "";
		try{
			if (filepath == "") {
				filepath = directory;
			}
			
			PdfReader reader = new PdfReader(filepath + filename);
			
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
			
			PrintWriter out = new PrintWriter(new FileOutputStream(keyword)); 
			
			TextExtractionStrategy strategy;
			
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
	            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
	            result = strategy.getResultantText(); 
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
		}  catch (Exception e) {
			e.printStackTrace();
			String error=e.toString();
			orderEmailQueue.updateError("service hibernate error",error);
			//System.out.println("searchpdf class error11");
			// log.error(e);
		}
		return result;
	}
	
}
