package com.avery.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

import com.adeptia.indigo.logging.Logger;
import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.dao.Partner_RBOProductLine;

/**
 * @author Dipanshu
 * 
 */
public class AdditionalFileAnalysis {
	static Logger log = Logger
			.getLogger(AdditionalFileAnalysis.class.getName());
	FileSearch fs = new FileSearch();

	/**
	 * @param emailQueueId
	 * @param fileName
	 * @param filePath
	 * @param fileExt
	 * @param schemaIdString
	 * @return
	 * @throws Exception
	 */
	public Set<Integer> identifyAdditionalDataFile(int orderFileAttachmentId,
			String fileName, String filePath, String fileExt,
			String schemaIdString) throws Exception {
		log.debug("identifyAdditionalDataFile for email queue\"" + orderFileAttachmentId
				+ "\".");
		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		OrderFileContentAnalysis ofca = new OrderFileContentAnalysis();
		Set<Integer> schemaId = new HashSet<Integer>();
		String fileContentMatch="";
		try {
			//if (schemaIdString == "" || schemaIdString == null) {
				if ( schemaIdString == null || schemaIdString.isEmpty() ) {
				return schemaId;
			}
			String[] productlineIdArr = schemaIdString.split(",");
			// log.debug("processing attachment id \"" + attachmentId + "\".");
			for (String productlineIdStr : productlineIdArr) {
				//if (productlineIdStr.isEmpty() || productlineIdStr == null) {
				if ( productlineIdStr == null || productlineIdStr.isEmpty() ) {
					break;
				}
				int productlineId = Integer.parseInt(productlineIdStr.trim());
				log.debug("productlineId \"" + productlineId + "\".");
				// fetch product line info
				ArrayList<Object> partner_rboinfo = orderEmailQueue
						.getPartner_productline(productlineId);
				Partner_RBOProductLine produclineData = new Partner_RBOProductLine();
				Iterator<Object> iterator = partner_rboinfo.iterator();
				while (iterator.hasNext()) {
					log.debug("data fetching for product line id \""
							+ productlineId + "\".");
					produclineData = (Partner_RBOProductLine) iterator.next();

					if (produclineData.isAttachmentRequired()
							&& produclineData.isAttachmentFileMatchRequired()) {

						if (produclineData
								.getAttachmentFileOrderMatchLocation()
								.contains("FileName")) {
							log.debug("Processing attachment identification from file name.");
							String fileNamePattern = produclineData
									.getAttachmentFileOrderMatch();
							
							if (fileNamePattern!=null && !fileNamePattern.isEmpty()) {
							//if (!fileNamePattern.isEmpty()) {
								
								String orderFileExt = FilenameUtils.getExtension(fileName);
								String orderFileName = FilenameUtils.getBaseName(fileName);
								
								//String[] PatterenfileName = fileName
								//		.split("\\.");
								//String orderFileName = PatterenfileName[0];
								//String orderFileExt = PatterenfileName[1];
								String res = ofca.FileNameMatch(orderFileName,
										orderFileExt,
										produclineData.getAttachmentFileOrderMatch());
								if (!res.isEmpty() || res != "") {
									schemaId.add(produclineData.getId());
									if (fileContentMatch == "") {
										fileContentMatch = produclineData.getAttachmentFileOrderMatch();
									} else {
										fileContentMatch = fileContentMatch
												+ "," + produclineData.getAttachmentFileOrderMatch();
									}
								}
							} else {
								log.debug("FileOrderMatch is empty.");
							}
						} else if (produclineData
								.getAttachmentFileOrderMatchLocation().equals(
										"FileContent")) {
							log.debug("Processing attachment identification from file Content.");
							String matchContent = produclineData
									.getAttachmentFileOrderMatch();
							if (!matchContent.isEmpty()) {
								if (produclineData
										.getAttachmentFileNameExtension_1()
										.contains(fileExt)) {
									if (produclineData
											.getAttachmentFileNameExtension_1()
											.contains("xls")) {
										if (fs.SearchContentInExcel(fileName,
												filePath, fileExt,
												matchContent, log)) {
											schemaId.add(produclineData.getId());
											if (fileContentMatch == "") {
												fileContentMatch = matchContent;
											} else {
												fileContentMatch = fileContentMatch
														+ "," +matchContent;
											}
										}

									} else if (produclineData
											.getAttachmentFileNameExtension_1()
											.contains("pdf")) {
										log.debug("searching file extension for pdf");
										String keyword = matchContent.trim();
										String[] keywordArray;
										if (keyword.contains("Value")) {
											keywordArray = keyword.split(":");
											keyword = keywordArray[1];

										}
										if (!keyword.trim().isEmpty()) {
											if (!fileName
													.contains("CompleteEmail")) {
												if (fs.searchpdf(fileName,
														keyword, filePath) != "" || !fs.searchpdf(fileName,
																keyword, filePath).isEmpty()) {
													schemaId.add(produclineData
															.getId());
													if (fileContentMatch == "") {
														fileContentMatch = matchContent;
													} else {
														fileContentMatch = fileContentMatch
																+ "," +matchContent;
													}
												}

											}
											log.debug("search in pdf for keyword\""
													+ keyword
													+ "\" file name\""
													+ fileName
													+ "\"filepath\""
													+ filePath + "\".");

										} else {
											log.debug("keyword is empty for pdf");
										}
									}
								}

							}
						}
					} else {
						log.debug("attachment required or attachment file match required is false for productline id \""
								+ productlineId + "\".");
					}
				}

			}
			fileContentMatch = fs.removeDup(fileContentMatch);
			if (schemaId.size() == 1) {
				
				orderEmailQueue.updateOrderEmailAttachmentContent(
						orderFileAttachmentId, schemaId.iterator().next(), "8", "", "", "",
						"AdditionalData", fileContentMatch);
				
			}else if(schemaId.size() > 1){
				String schema_id_comment="";
				for (Integer s : schemaId) {
					if (schema_id_comment == "") {
						schema_id_comment = "" + s;
					} else {
						schema_id_comment = schema_id_comment + "," + s;
					}

				}
				orderEmailQueue.updateOrderEmailAttachmentContent(
						orderFileAttachmentId, 0, "8", "", schema_id_comment, "",
						"AdditionalData", fileContentMatch);
				/*orderEmailQueue.updateOrderEmailAttachmentContent(
						orderFileAttachmentId, schemaId.iterator().next(), "8", "", "", "",
						"AdditionalData", fileContentMatch);
				orderEmailQueue.updateOrderEmailAttachment(
						orderFileAttachmentId, 0, "6", "", "",
						schema_id_comment, fileContentMatch, "", "AdditionalData");*/
			}
		} catch (Exception e) {
			log.error("Exception while additional file analysis.");
			throw e;
		}
		log.debug("return schema id for additional file.");
		return schemaId;
	}

}
