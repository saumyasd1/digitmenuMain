package com.avery.services;

import java.io.File;
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

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.Model.SearchCell;
import com.avery.dao.OrderFileAttachment;
import com.avery.dao.Partner_RBOProductLine;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class OrderEmailService {
	public static String directory;
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
	//public static final String directoryLocation = "C:\\AveryDennisonFiles";

	public void OrderEmailSourceservice(String dir,int id) {
		this.directory = dir;
		String email = "";
		String subject = "";
		String body = "";
		String emailId = "";
		String Domain = "";
		String rboname = "";
		String sheet_name = "";
		int rbo_productline_id = 0;
		int partnerId = 0;
		Set final_productlines = new HashSet();

		List rbosubjectschemaid = new ArrayList();
		List rbobodyschemaid = new ArrayList();
		List productlineSubjectSchemaid = new ArrayList();
		List productlineBodySchemaid = new ArrayList();
		List subject_rbo = new ArrayList();
		List body_rbo = new ArrayList();
		List subject_productline = new ArrayList();
		List body_productline = new ArrayList();
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		try {
			// log.info("Enter method OrderEmailSourceservice  class OrderEmailService");
			
			HashMap emailinfo = orderEmailQueue.EmailSource(id);
			Iterator it = emailinfo.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();

				if (pair.getKey() == "source") {
					email = (String) pair.getValue();
				}
				if (pair.getKey() == "subject") {
					subject = (String) pair.getValue();
				}
				if (pair.getKey() == "body") {
					body = (String) pair.getValue();
				}
			}
			
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
					log.error("Partner id not found");
					orderEmailQueue.updateOrderEmail(id,"read but unidentified");
				}
				//////get data from partner rbo productlines
				ArrayList<Object> partner_rboinfo = orderEmailQueue.getPartnerRbo_productlines(partnerId);
				ArrayList<Object> rboproduclines = new ArrayList();
				rboproduclines = orderEmailQueue.getPartnerRbo_productlines(partnerId);
				Partner_RBOProductLine rbodetails = new Partner_RBOProductLine();
				Iterator<Object> iterator = partner_rboinfo.iterator();
				while (iterator.hasNext()) {
					rbodetails = (Partner_RBOProductLine) iterator.next();
					String rbolist = rbodetails.getEmailSubjectRBOMatch();
					String productlines = rbodetails.getEmailSubjectProductLineMatch();
					if (rbolist.contains("|")) {
						String[] rbos = rbolist.split("\\|");
						for (String prbo : rbos) {
							if (subject.toLowerCase().contains(prbo.toLowerCase())) {
								subject_rbo.add(prbo);
								rbosubjectschemaid.add(rbodetails.getId());
							}
						}
					}
					if (productlines.contains("|")) {
						String[] pline = productlines.split("\\|");
						for (String productline : pline) {
							if (subject.toLowerCase().contains(productline.toLowerCase())) {
								subject_productline.add(productline);
								productlineSubjectSchemaid.add(rbodetails.getId());
							}
						}
					}
					// ////read mail body to identify rbo
					String bodyproductlines = rbodetails
							.getFileProductlineMatch();
					String bodyrbolist = rbodetails.getFileRBOMatch();
					if (bodyrbolist.contains("|")) {
						String[] bodyrbo = bodyrbolist.split("\\|");
						for (String brbo : bodyrbo) {
							String path = "";
							if (searchpdf(body, brbo, path) != "") {
								body_rbo.add(brbo);
								rbobodyschemaid.add(rbodetails.getId());
							}
						}
					}
					if (bodyproductlines.contains("|")) {
						String[] bodyPlines = bodyproductlines.split("\\|");
						for (String bPLines : bodyPlines) {
							String path = "";
							if (searchpdf(body, bPLines, path) != "") {
								body_productline.add(bPLines);
								productlineBodySchemaid.add(rbodetails.getId());
							}
						}
					}

				}

				// log.info("Exit method OrderEmailSourceservice  class OrderEmailService");

			}
			// ////////////////////////////////////////
			final_productlines.addAll(rbosubjectschemaid);
			final_productlines.addAll(rbobodyschemaid);
			final_productlines.addAll(productlineSubjectSchemaid);
			final_productlines.addAll(productlineBodySchemaid);

			if (final_productlines.size() == 0) {
				orderEmailQueue.updateOrderEmail(id,"read but no match");
			} else if (final_productlines.size() == 1) {
				orderEmailQueue.updateOrderEmail(id,"identified");
				List final_productline_list = new ArrayList(final_productlines);
				readAttachment(id, final_productline_list);
			} else if (final_productlines.size() == 2) {
				List final_productline_list = new ArrayList(final_productlines);
				readAttachment(id, final_productline_list);
			}
			/*
			 * final_productlines.add(rbosubjectschemaid);
			 * final_productlines.add(rbobodyschemaid);
			 * final_productlines.add(productlineSubjectSchemaid);
			 * final_productlines.add(productlineBodySchemaid);
			 */

		} catch (HibernateException ex) {
			
			String error=ex.toString();
			orderEmailQueue.updateError("service hibernate error",error);
			 log.error(ex);
		} catch (Exception e) {
			String error=e.toString();
			orderEmailQueue.updateError("service hibernate error",error);
			 log.error(e);
		}
		System.out.println("productlines are " + final_productlines);
		System.out.println("partner id " + partnerId);
		System.out.println("rbo schema id for subject :");

		System.out.println("\t" + rbosubjectschemaid);
		System.out.println("rbo schema id for emailbody :");
		System.out.println("\t" + rbobodyschemaid);
		System.out.println("productline schema id for subject :");
		System.out.println("\t" + productlineSubjectSchemaid);
		System.out.println("productline schema id for emailbody :");
		System.out.println("\t" + productlineBodySchemaid);
		System.out.println("rbo in subject line :");
		System.out.println("\t" + subject_rbo);
		System.out.println("rbo in body :");
		System.out.println("\t" + body_rbo);
		System.out.println("producyline in subject line :");
		System.out.println("\t" + subject_productline);
		System.out.println("productline in body :");
		System.out.println("\t" + body_productline);
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
			String error=e.toString();
			orderEmailQueue.updateError("service hibernate error",error);
			System.out.println("searchpdf class error11");
			 log.error(e);
		}
		return result;
	}
	public void readAttachment(int emailId, List productlineId) {
		
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		int productline_rbo_id;
		String file_name="";
		String file_path="";
		String file_ext="";
		String productlines ="";
		String rbolist ="";
		String Sheetname="";
		String keyword_location="";
		int att_id =0;
		try{
			Partner_RBOProductLine p_info = new Partner_RBOProductLine();
			ArrayList<Object> rboproduclines = new ArrayList();
			//OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
			ArrayList<Object> email_list = orderEmailQueue.GetEmailAttachments(emailId);
			Iterator<Object> iterat = email_list.iterator();
			OrderFileAttachment email_att = new OrderFileAttachment();
			SearchCell read_att = new SearchCell();
			 
			while (iterat.hasNext()) {
				List rbo = new ArrayList();
				List productline = new ArrayList();
				
				List<Integer> productline_id = new ArrayList<Integer>();
				String Sheetinfo="";
				email_att = (OrderFileAttachment) iterat.next();
				file_name = email_att.getFileName();
				file_ext = email_att.getFileExtension();
				file_path = email_att.getFilePath();
				
				att_id = email_att.getId();
				for (int i = 0; i < productlineId.size(); i++) {
				//get data for partner rbo productline	
					productline_rbo_id=(Integer) productlineId.get(i);
					ArrayList<Object> productline_info = orderEmailQueue.getPartner_productline(productline_rbo_id);
					Iterator<Object> iterator = productline_info.iterator();
					while (iterator.hasNext()) {
						p_info = (Partner_RBOProductLine) iterator.next();
						rbolist = p_info.getFileRBOMatch();
						Sheetinfo=p_info.getAttachmentFileMatchLocation();
						
						if (Sheetinfo.contains(";")) {
							String[] Sheetdetails = Sheetinfo.split(";");
							for (String Sheetdetail : Sheetdetails) {
								if (Sheetdetail.contains("SheetName")) {
									String[] Sheetnames = Sheetdetail.split("=");
									
									Sheetname=Sheetnames[1];
								}
							}
						}
						if (rbolist.contains("|")) {
							String[] a_rbo = rbolist.split("\\|");
							for (String arbo : a_rbo) {
								if (file_ext.equals(".xls")||file_ext.equals(".xlsx")) {
									keyword_location = read_att.SearchXL(
										file_path, arbo, file_ext,
										Sheetname, file_name);
									if (keyword_location != "") {
										System.out.println("keyword_location"+"\t" + keyword_location);
										rbo.add(arbo);
										productline_id.add(productline_rbo_id);
									}
								}
								if (file_ext == ".pdf") {
									if (searchpdf(file_name, arbo, file_path) != "") {
										rbo.add(arbo);
										productline_id.add(productline_rbo_id);
									
									}
								}
	
							}
						}
						/////check product lines
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
										productline.add(pline);
										productline_id.add(productline_rbo_id);
									}
								}
								if (file_ext == ".pdf") {
									if (searchpdf(file_name, pline, file_path) != "") {
										productline.add(pline);
										productline_id.add(productline_rbo_id);
									}
								}
	
							}
						}
					}
					
					//System.out.println(productline_info.);
				}
				///////remove duplicate from list and pass it in function to update
				//orderEmailQueue.updateOrderEmailAttachment();
				//System.out.println("productline_id"+"\t" + productline);
				Set<Integer> uniqe_p_ids = new LinkedHashSet<Integer>(productline_id);
				// System.out.println("dsfsdfsdfsdfsdfsdf"+productline_id);
				
				 if(uniqe_p_ids.size()==0){
						
					        orderEmailQueue.updateOrderEmailAttachment(att_id, 0, "No match");
					      
						//only one productline id update attachment and orderemail queue tableas identified
						//orderEmailQueue.updateOrderEmail(emailId,"identified");
						//orderEmailQueue.updateOrderEmailAttachment();
				}else if(uniqe_p_ids.size()==1){
					for (Integer myVal : uniqe_p_ids) {
				       // System.out.println("dsfsdfsdfsdfsdfsdf234234"+myVal);
				        orderEmailQueue.updateOrderEmailAttachment(att_id, myVal, "identified");
				      }
					//only one productline id update attachment and orderemail queue tableas identified
					//orderEmailQueue.updateOrderEmail(emailId,"identified");
					//orderEmailQueue.updateOrderEmailAttachment();
				}else{
					orderEmailQueue.updateOrderEmailAttachment(att_id, 0, "multiple productlines match");
					
				}
				//make unidentified
			}
		}catch (Exception e) {
			String error=e.toString();
			orderEmailQueue.updateError("service hibernate error",error);
			System.out.println("readAttachment class error11");
			log.error(e);
		}
	}
		

}
