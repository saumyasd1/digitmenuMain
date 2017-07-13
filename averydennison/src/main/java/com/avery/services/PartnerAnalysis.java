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

	public ArrayList<Integer> partnerSearch(ProductLineBean plb, String filePath,
			String FileName, int EmailQueueId, int AttachmentId)
			throws Exception {
		log.info("search partner method starts.");
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Integer> schemaIdListPartner = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListRbo = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListProductLine = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListEmail = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListPartnerORG = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListRboORG = new ArrayList<Integer>();
		try {
			
			HashMap<Integer, Partner_RBOProductLine> partner_rboinfo = plb.getProductLinesForEmail();
			String rboMatchResult = "";
			String productlineMatchResult = "";
			String partnerMatchResult = "";
			int schemaId = 0;

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
					if (schemaInfo.isFileProductLineMatchRequired()) {
						log.debug("productline match list \""
								+ schemaInfo.getFileProductlineMatch() + ".");

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
					if (schemaInfo.isFileRBOMatchRequired()) {
						String res = fs.searchStringInFile(filePath, FileName,
								schemaInfo.getFileRBOMatch());
						log.debug("rbo match list \""
								+ schemaInfo.getFileRBOMatch() + ".");

						if(res != null && !res.isEmpty()){
							schemaIdListRbo.add(schemaInfo.getId());
							if (rboMatchResult == "") {
								rboMatchResult = res;
							} else {
								rboMatchResult = rboMatchResult + "," + res;
							}
						}
					}
					if (schemaInfo.isFileOrderPartnerRequired()) {
						log.debug("Partner match list \""
								+ schemaInfo.getFileOrderPartnerMatch() + ".");

						String res = fs.searchStringInFile(filePath, FileName,
								schemaInfo.getFileOrderPartnerMatch());
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
				}

			}
			String Status = "";
			partnerMatchResult = fs.removeDup(partnerMatchResult);
			rboMatchResult = fs.removeDup(rboMatchResult);
			productlineMatchResult = fs.removeDup(productlineMatchResult);
			// log.debug("partnerMatchResult "+rboMatchResult);
			// log.debug("partnerMatchResult----------------- "+partnerMatchResult);
			orderEmailQueue.updateOrderEmailAttachment(AttachmentId, schemaId,
					Status, rboMatchResult, productlineMatchResult, "", "",
					partnerMatchResult, "");
		} catch (Exception e) {
			log.error("Exception while Partner rbo productline analysis in EmailBody and Attachment.");
			throw e;
		}
		log.debug("Partner analysis finish");
		if (schemaIdListPartner.size() != 0) {

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
		}

	}

	
	
}

