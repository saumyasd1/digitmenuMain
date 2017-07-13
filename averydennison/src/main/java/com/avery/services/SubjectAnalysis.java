package com.avery.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.adeptia.indigo.logging.Logger;
import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.Model.ProductLineBean;
import com.avery.dao.Partner_RBOProductLine;

public class SubjectAnalysis {

	public static Logger log = OrderEmailQueueServices.log;
	FileSearch fs = new FileSearch();

	// /Partner search in subject
	// /Rbo search in emailbody
	// /Productline search in attachment
	/**
	 * @param id
	 * @param attachment_id
	 * @param productlineId
	 * @throws Exception
	 */
	public void subjectSearch(int id, int attachment_id,
			Set<Integer> productlineId) throws Exception {
		// log.debug("email subject analysis start.");

		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		HashMap<String, String> emailinfo = orderEmailQueue.emailSource(id);
		Iterator it = emailinfo.entrySet().iterator();
		String subject = "";
		String subjectRbo = "";
		String subjectProductline = "";
		String subjectPartner = "";
		// //get email subject subjectPartner
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
			if (pair.getKey() == "subjectPartner") {
				subjectPartner = (String) pair.getValue();
			}
		}
		if (subject == null || subject.trim().isEmpty()) {
			log.debug("Subject is empty for emailqueueid \"" + id + "\".");
			return;
		}
		if (subjectRbo == null) {
			subjectRbo = "";
		}
		if (subjectProductline == null) {
			subjectProductline = "";
		}
		if (subjectPartner == null) {
			subjectPartner = "";
		}

		// get product line id in loop and check
		for (Integer p_id : productlineId) {
			// fetch productline info
			//ArrayList<Object> partner_rboinfo = orderEmailQueue
			//		.getPartner_productline(p_id);
			Partner_RBOProductLine SchemaDetails = new Partner_RBOProductLine();
			//Iterator<Object> iterator = partner_rboinfo.iterator();
			//while (iterator.hasNext()) {
				SchemaDetails = ProductLineBean.productLineMap.get(p_id);
				if (SchemaDetails.isEmailSubjectRBOMatchRequired()) {
					String resultRbo = searchSubject(subject,
							SchemaDetails.getEmailSubjectRBOMatch());
					if (resultRbo.trim() != "" && !resultRbo.trim().isEmpty()) {
						if (subjectRbo == "") {
							subjectRbo = resultRbo;
						} else {
							subjectRbo = subjectRbo + "," + resultRbo;
						}
						
					}
					
				} else {
					log.debug("EmailSubjectRBOMatchRequired is false for productline id \""
							+ p_id + "\".");
				}
				if (SchemaDetails.isEmailSubjectProductlineMatchRequired()) {

					String resultproductLine = searchSubject(subject,
							SchemaDetails.getEmailSubjectProductLineMatch());
					if (resultproductLine.trim() != "" && !resultproductLine.trim().isEmpty()) {
						if (subjectProductline == "") {
							subjectProductline = resultproductLine;
						} else {
							subjectProductline = subjectProductline + "," + resultproductLine;
						}
					}

				} else {
					log.debug("EmailSubjectProductlineMatchRequired is false for productline id \""
							+ p_id + "\".");
				}
				// //update for partner
				if (SchemaDetails.isEmailSubjectPartnerRequired()) {
					String resultpartner = searchSubject(subject,
							SchemaDetails.getEmailSubjectPartnerMatch());
					if (resultpartner.trim() != "" && !resultpartner.trim().isEmpty()) {
						if (subjectPartner == "") {
							subjectPartner = resultpartner;
						} else {
							subjectPartner = subjectPartner + "," + resultpartner;
						}
					}
				} else {
					log.debug("EmailSubjectRBOMatchRequired is false for productline id \""
							+ p_id + "\".");
				}
			//}
		}

		log.debug("subject rbo. \"" + subjectRbo + "\".");
		log.debug("subject productline. \"" + subjectProductline + "\".");
		subjectProductline = fs.removeDup(subjectProductline);
		subjectPartner = fs.removeDup(subjectPartner);
		subjectRbo = fs.removeDup(subjectRbo);
		orderEmailQueue.updateOrderEmail(id, "", subjectRbo,
				subjectProductline, subjectPartner, "", "");
	}

	/**
	 * @param subject
	 * @param keyvalues
	 * @return
	 */
	public String searchSubject(String subject, String keyvalues) {
		String results = "";
		log.debug("searchSubject for value. \"" + keyvalues + "\".");
		if (keyvalues == null) {
			return results;
		}
		if (keyvalues.contains("|") || !keyvalues.isEmpty()) {
			String[] keyWords = keyvalues.split("\\|");
			for (String keyWord : keyWords) {
				if (!keyWord.isEmpty()) {
					if (subject.toLowerCase().contains(keyWord.toLowerCase())) {
						if (results == "") {
							results = keyWord;
						} else {

							results = results + ", " + keyWord;
						}

					}
				}
			}

		}
		results = fs.removeDup(results);
		return results;
	}

}
