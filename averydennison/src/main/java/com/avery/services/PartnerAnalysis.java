package com.avery.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;

import com.adeptia.indigo.logging.Logger;
import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.dao.Partner_RBOProductLine;

public class PartnerAnalysis {
	// static Logger log = Logger.getLogger(PartnerAnalysis.class.getName());
	public static Logger log = Logger
			.getLogger(PartnerAnalysis.class.getName());
	FileSearch fs = new FileSearch();

	public ArrayList<Integer> partnerSearch(String email, String filePath,
			String FileName, int EmailQueueId, int AttachmentId)
			throws Exception {
		log.info("search partner method starts for id.\"" + AttachmentId + ".");
		//log.debug("get schema ids for email \"" + email + ".");
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		ArrayList<Integer> schemaIdListPartner = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListRbo = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListProductLine = new ArrayList<Integer>();
		ArrayList<Integer> schemaIdListEmail = new ArrayList<Integer>();
		try {
			log.debug("get schema ids for email \"" + email + ".");

			ArrayList<Object> partner_rboinfo = orderEmailQueue
					.getPartnerRbo_productlines(email);
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
				log.debug("unrecoginzed email \"" + email + ".");
				return schemaIdListPartner;
			}
			log.debug("got schema ids for  email \"" + email + ".");
			Partner_RBOProductLine schemaInfo = new Partner_RBOProductLine();
			Iterator<Object> iterator = partner_rboinfo.iterator();
			
			String FileExtString = FilenameUtils.getExtension(FileName);
			String FileNameString = FilenameUtils.getBaseName(FileName);
			//String[] FilNamePattern = FileName.split("\\.");
			
			//String FileNameString = FilNamePattern[0];
			//String FileExtString = FilNamePattern[1];
			while (iterator.hasNext()) {
				schemaInfo = (Partner_RBOProductLine) iterator.next();
				schemaIdListEmail.add(schemaInfo.getId());
				log.info("Processing attachment for productline id \""
						+ schemaInfo.getId() + "\".");
				if (FileNameString.contains("CompleteEmail")) {
					log.debug("Processing starts for file name \""
							+ FileNameString + "\".");
					if (schemaInfo.isEmailBodyRBOMatchRequired()) {
						String res = fs.searchStringInBody(filePath, FileName,
								schemaInfo.getEmailBodyRBOMatch());
						log.debug("rbo match list \""
								+ schemaInfo.getEmailBodyRBOMatch() + ".");
						if (!res.isEmpty() && res != "") {
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
						if (!res.isEmpty() && res != "") {
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
					log.info("Processing starts for file name \""
							+ FileNameString + "\".");
					if (schemaInfo.isFileProductLineMatchRequired()) {
						log.info("productline match list \""
								+ schemaInfo.getFileProductlineMatch() + ".");

						String res = fs.searchStringInFile(filePath, FileName,
								schemaInfo.getFileProductlineMatch());
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
					if (schemaInfo.isFileRBOMatchRequired()) {
						String res = fs.searchStringInFile(filePath, FileName,
								schemaInfo.getFileRBOMatch());
						log.info("rbo match list \""
								+ schemaInfo.getFileRBOMatch() + ".");

						if (!res.isEmpty() && res != "") {
							schemaIdListRbo.add(schemaInfo.getId());
							if (rboMatchResult == "") {
								rboMatchResult = res;
							} else {
								rboMatchResult = rboMatchResult + "," + res;
							}
						}
					}
					if (schemaInfo.isFileOrderPartnerRequired()) {
						log.info("Partner match list \""
								+ schemaInfo.getFileOrderPartnerMatch() + ".");

						String res = fs.searchStringInFile(filePath, FileName,
								schemaInfo.getFileOrderPartnerMatch());
						if (!res.isEmpty() && res != "") {
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
				schemaIdListPartner.retainAll(schemaIdListRbo);

				if (schemaIdListProductLine.size() != 0
						&& schemaIdListPartner.size() != 1) {
					schemaIdListPartner.retainAll(schemaIdListProductLine);

				}
			}
			return schemaIdListPartner;
		} else {
			return schemaIdListEmail;
		}

	}

}
