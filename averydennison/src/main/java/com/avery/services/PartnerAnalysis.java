package com.avery.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.adeptia.indigo.logging.Logger;
import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.Model.ProductLineBean;
import com.avery.dao.Partner_RBOProductLine;

public class PartnerAnalysis {
	// static Logger log = Logger.getLogger(PartnerAnalysis.class.getName());
	public static Logger log = OrderEmailQueueServices.log;
	FileSearch fs = new FileSearch();
	public String rboMatchResult = "";
	public String productlineMatchResult = "";
	public String partnerMatchResult = "";

	
	
	
	
	public ArrayList<Integer> partnerSearch(ProductLineBean plb, String filePath,
			String FileName, int EmailQueueId, int AttachmentId)
			throws Exception {
		log.info("search partner method starts.");
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Integer> schemaIdListPartner = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListRbo = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListProductLine = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListEmail = new ArrayList<Integer>();
		int schemaId = 0;
		try {
			//HashMap<Integer, Partner_RBOProductLine> partner_rboinfo = plb.productLineMap(OrderEmailQueueServices.email);
			HashMap<Integer, Partner_RBOProductLine> partner_rboinfo = plb.getProductLinesForEmail();
			if (partner_rboinfo.size() <= 0) {
				// /update for unrecoginzed
				orderEmailQueue.updateOrderEmail(EmailQueueId, "4", "", "", "",
						"", "");
				orderEmailQueue.updateAllAttachment(EmailQueueId, schemaId,
						"6", "");
				log.debug("unrecoginzed emailqueue id  \"" + EmailQueueId + ".");
				return schemaIdListPartner;
			}
			log.debug("got schema ids for  emailqueue id \"" + EmailQueueId + ".");
			Partner_RBOProductLine schemaInfo = new Partner_RBOProductLine();
			
			String FileExtString = FilenameUtils.getExtension(FileName);
			String FileNameString = FilenameUtils.getBaseName(FileName);
			
			Iterator iterator = partner_rboinfo.entrySet().iterator();
			// //get email subject and source
			while (iterator.hasNext()) {
				Map.Entry pair = (Map.Entry) iterator.next();
				schemaInfo = (Partner_RBOProductLine) pair.getValue();
				schemaIdListEmail.add(schemaInfo.getId());
				log.debug("Processing attachment for productline id \""
						+ schemaInfo.getId() + "\".");
				if (FileNameString.contains("CompleteEmail")) {
					log.debug("Processing starts for file name \""
							+ FileNameString + "\".");
					
					
					if (schemaInfo.isEmailBodyPartnerRequired()) {
						log.debug("Partner match list \""
								+ schemaInfo.getEmailBodyPartnerMatch() + ".");

						String res = fs.searchStringInBody(filePath, FileName,
								schemaInfo.getEmailBodyPartnerMatch());
						if(res != null && !res.isEmpty()){
							schemaIdListPartner.add(schemaInfo.getId());
							if (partnerMatchResult == "") {
								partnerMatchResult = res;
							} else {
								partnerMatchResult = partnerMatchResult + ","
										+ res;
							}
						}
					}

				} else if (FileExtString.contains("xls")
						|| FileExtString.contains("pdf")) {
					log.debug("Processing starts for file name \""
							+ FileNameString + "\".");
					if (schemaInfo.isFileOrderPartnerRequired()) {
						log.debug("Partner match list \""
								+ schemaInfo.getFileOrderPartnerMatch() + ".");
						//condition added to handle and or conditions in keywords
						if(FileExtString.toLowerCase().contains("xls")){
							boolean res = fs.SearchContentInExcelFile(FileName,filePath,FileExtString,
									schemaInfo.getFileOrderPartnerMatch(), log);
							if(res){
								schemaIdListPartner.add(schemaInfo.getId());
								if (partnerMatchResult == "") {
									partnerMatchResult = schemaInfo.getFileOrderPartnerMatch();
								} else {
									partnerMatchResult = partnerMatchResult
											+ "," + schemaInfo.getFileOrderPartnerMatch();
								}
							}
						}else if(FileExtString.toLowerCase().contains("pdf")){
							String res = fs.searchStringInFile(filePath, FileName,
									schemaInfo.getFileOrderPartnerMatch());
								if(res != null && !res.isEmpty()){
									schemaIdListPartner.add(schemaInfo.getId());
									if (partnerMatchResult == "") {
										partnerMatchResult = res;
									} else {
										partnerMatchResult = partnerMatchResult
												+ "," + res;
									}

								}
						}
						
					}
				}

			}
			
			if (schemaIdListPartner.size() != 0) {
				schemaIdListRbo = rboSearch(plb, filePath,
					FileName,schemaIdListPartner);
			}else{
				schemaIdListRbo = rboSearch(plb, filePath,
						FileName,schemaIdListEmail);
			}
			if (schemaIdListRbo.size() != 0) {
				schemaIdListProductLine =productlineSearch(plb, filePath,
						FileName, schemaIdListRbo);
			}else{
				if(schemaIdListPartner.size() != 0){
					schemaIdListProductLine =productlineSearch(plb, filePath,
							FileName, schemaIdListPartner);
				}else{
					schemaIdListProductLine =productlineSearch(plb, filePath,
							FileName, schemaIdListEmail);
				}
				
			}
			
			
			
		} catch (Exception e) {
			log.error("Exception while Partner rbo productline analysis in EmailBody and Attachment.");
			throw e;
		}
		log.debug("Partner analysis finish");
		
		String Status = "";
		partnerMatchResult = fs.removeDup(partnerMatchResult);
		rboMatchResult = fs.removeDup(rboMatchResult);
		productlineMatchResult = fs.removeDup(productlineMatchResult);
		
		partnerMatchResult = partnerMatchResult.substring(0, Math.min(partnerMatchResult.length(), 240));
		productlineMatchResult = productlineMatchResult.substring(0, Math.min(productlineMatchResult.length(), 240));
		rboMatchResult = rboMatchResult.substring(0, Math.min(rboMatchResult.length(), 240));
		orderEmailQueue.updateOrderEmailAttachment(AttachmentId, schemaId,
				Status, rboMatchResult, productlineMatchResult, "", "",
				partnerMatchResult, "");
		
		if(schemaIdListProductLine.size() != 0){
			return schemaIdListProductLine;
		}
		else if(schemaIdListRbo.size() != 0){
			return schemaIdListRbo;
		}
		else if(schemaIdListPartner.size()!=0){
			return schemaIdListPartner;
		}
		else{
			return schemaIdListEmail;
		}
		/*if (schemaIdListPartner.size() != 0) {

			if (schemaIdListRbo.size() != 0 && schemaIdListPartner.size() != 1) {
				schemaIdListPartnerORG.addAll(schemaIdListPartner);
				schemaIdListPartner.retainAll(schemaIdListRbo);
				if(schemaIdListPartner.size()==0){
					schemaIdListPartner.addAll(schemaIdListPartnerORG);
				}
				if (schemaIdListProductLine.size() != 0
						&& schemaIdListPartner.size() != 1) {
					schemaIdListPartnerORG.clear();
					schemaIdListPartnerORG.addAll(schemaIdListPartner);
					schemaIdListPartner.retainAll(schemaIdListProductLine);
						if(schemaIdListPartner.size()==0){
							schemaIdListPartner.addAll(schemaIdListPartnerORG);
						}
				}
			}
			return schemaIdListPartner;
		}else if(schemaIdListRbo.size() != 0){
			//schemaIdListPartner.retainAll(schemaIdListRbo);

			if (schemaIdListProductLine.size() != 0
					&& schemaIdListRbo.size() != 1) {
				schemaIdListRboORG.addAll(schemaIdListRbo);
				schemaIdListRbo.retainAll(schemaIdListProductLine);
				if(schemaIdListRbo.size()==0){
					schemaIdListRbo.addAll(schemaIdListRboORG);
				}
				
			}
			return schemaIdListRbo;
		}else if(schemaIdListProductLine.size() != 0){
			
			return schemaIdListProductLine;
		} else {
			return schemaIdListEmail;
		}*/

	}

	public ArrayList<Integer> rboSearch(ProductLineBean plb, String filePath,
			String FileName, ArrayList<Integer> schemaList)
			throws Exception {
		//HashMap<Integer, Partner_RBOProductLine> partner_rboinfo = plb.productLineMap(OrderEmailQueueServices.email);
		HashMap<Integer, Partner_RBOProductLine> partner_rboinfo = plb.getProductLinesForEmail();
		ArrayList<Integer> schemaIdListRbo = new ArrayList<Integer>();
		Partner_RBOProductLine schemaInfo = new Partner_RBOProductLine();
		
		String FileExtString = FilenameUtils.getExtension(FileName);
		String FileNameString = FilenameUtils.getBaseName(FileName);
		
		Iterator iterator = partner_rboinfo.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry pair = (Map.Entry) iterator.next();
			schemaInfo = (Partner_RBOProductLine) pair.getValue();
			if(schemaList.contains(schemaInfo.getId())){
			
				if (FileNameString.contains("CompleteEmail")) {
					log.debug("Processing starts for file name \""
							+ FileNameString + "\".");
					if (schemaInfo.isEmailBodyRBOMatchRequired()) {
						String res = fs.searchStringInBody(filePath, FileName,
								schemaInfo.getEmailBodyRBOMatch());
						log.debug("rbo match list \""
								+ schemaInfo.getEmailBodyRBOMatch() + ".");
						if(res != null && !res.isEmpty()){
							schemaIdListRbo.add(schemaInfo.getId());
							if (rboMatchResult == "") {
								rboMatchResult = res;
							} else {
								rboMatchResult = rboMatchResult + "," + res;
							}
						}
						// /search partner in email body
					}
				} else if (FileExtString.contains("xls")
						|| FileExtString.contains("pdf")) {
					log.debug("Processing starts for file name \""
							+ FileNameString + "\".");
					if (schemaInfo.isFileRBOMatchRequired()) {
						log.debug("rbo match list \""
								+ schemaInfo.getFileRBOMatch() + ".");
						//condition added to handle and or conditions in keywords
						if(FileExtString.toLowerCase().contains("xls")){
							boolean res = fs.SearchContentInExcelFile(FileName,filePath,FileExtString,
									schemaInfo.getFileRBOMatch(), log);
							if(res){
								schemaIdListRbo.add(schemaInfo.getId());
								if (rboMatchResult == "") {
									rboMatchResult = schemaInfo.getFileRBOMatch();
								} else {
									rboMatchResult = rboMatchResult
											+ "," + schemaInfo.getFileRBOMatch();
								}
							}
						}else if(FileExtString.toLowerCase().contains("pdf")){
							String res = fs.searchStringInFile(filePath, FileName,
									schemaInfo.getFileRBOMatch());
								if(res != null && !res.isEmpty()){
									schemaIdListRbo.add(schemaInfo.getId());
									if (rboMatchResult == "") {
										rboMatchResult = res;
									} else {
										rboMatchResult = rboMatchResult
												+ "," + res;
									}
	
								}
						}		
					}
				}
			}
		}
		return schemaIdListRbo;
		
		
	}
	public ArrayList<Integer> productlineSearch(ProductLineBean plb, String filePath,
			String FileName, ArrayList<Integer> schemaList)
			throws Exception {
		//HashMap<Integer, Partner_RBOProductLine> partner_rboinfo = plb.productLineMap(OrderEmailQueueServices.email);
		HashMap<Integer, Partner_RBOProductLine> partner_rboinfo = plb.getProductLinesForEmail();
		ArrayList<Integer> schemaIdListProductLine = new ArrayList<Integer>();
		Partner_RBOProductLine schemaInfo = new Partner_RBOProductLine();
		
		String FileExtString = FilenameUtils.getExtension(FileName);
		String FileNameString = FilenameUtils.getBaseName(FileName);
		
		Iterator iterator = partner_rboinfo.entrySet().iterator();
		while (iterator.hasNext()) {
		
			Map.Entry pair = (Map.Entry) iterator.next();
			schemaInfo = (Partner_RBOProductLine) pair.getValue();
			if(schemaList.contains(schemaInfo.getId())){
				if (FileNameString.contains("CompleteEmail")) {
					log.debug("Processing starts for file name \""
							+ FileNameString + "\".");
					if (schemaInfo.isEmailBodyProductlineMatchRequired()) {
						String res = fs.searchStringInBody(filePath, FileName,
								schemaInfo.getEmailBodyProductLineMatch());
						log.debug("productline match list \""
								+ schemaInfo.getEmailBodyProductLineMatch()
								+ ".");
						if (!res.isEmpty() && res != "") {
							schemaIdListProductLine.add(schemaInfo.getId());
							if (productlineMatchResult == "") {
								productlineMatchResult = res;
							} else {
								productlineMatchResult = productlineMatchResult
										+ "," + res;
							}
						}
					}
				}else if (FileExtString.contains("xls")
						|| FileExtString.contains("pdf")) {
					log.debug("Processing starts for file name \""
							+ FileNameString + "\".");
					if (schemaInfo.isFileProductLineMatchRequired()) {
						log.debug("productline match list \""
								+ schemaInfo.getFileProductlineMatch() + ".");
						//condition added to handle and or conditions in keywords
						if(FileExtString.toLowerCase().contains("xls")){
							boolean res = fs.SearchContentInExcelFile(FileName,filePath,FileExtString,
									schemaInfo.getFileProductlineMatch(), log);
							if(res){
								schemaIdListProductLine.add(schemaInfo.getId());
								if (productlineMatchResult.isEmpty()) {
									productlineMatchResult = schemaInfo.getFileProductlineMatch();
								} else {
									productlineMatchResult = productlineMatchResult
											+ "," + schemaInfo.getFileProductlineMatch();
								}
							}
						}else if(FileExtString.toLowerCase().contains("pdf")){
							String res = fs.searchStringInFile(filePath, FileName,
									schemaInfo.getFileProductlineMatch());
								if(res != null && !res.isEmpty()){
									schemaIdListProductLine.add(schemaInfo.getId());
									if (productlineMatchResult == "") {
										productlineMatchResult = res;
									} else {
										productlineMatchResult = productlineMatchResult
												+ "," + res;
									}
	
								}
						}
						
					}
				}
			}
		}
		
		return schemaIdListProductLine;
	}
	
}

