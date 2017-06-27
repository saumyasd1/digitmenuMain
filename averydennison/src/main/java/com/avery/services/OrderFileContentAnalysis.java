package com.avery.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

import com.adeptia.indigo.logging.Logger;
import com.avery.Model.OrderEmailQueueInterface;
import com.avery.Model.OrderEmailQueueModel;
import com.avery.dao.Partner_RBOProductLine;

public class OrderFileContentAnalysis {
	static Logger log = Logger.getLogger(OrderFileContentAnalysis.class
			.getName());

	FileSearch fs = new FileSearch();

	/**
	 * method identifyAttachment and body
	 * 
	 * @param id
	 * @param att_id
	 * @param file_path
	 * @param file_name
	 * @param file_ext
	 * @param email
	 * @return schema id
	 * @throws Exception
	 */
	public void identifyAttachment(int emailQueueId, int orderFileAttachmentId,
			String filePath, String fileName, String fileExt,
			List<Integer> schemaIds) throws Exception {

		OrderEmailQueueInterface orderEmailQueue = new OrderEmailQueueModel();
		SubjectAnalysis sa = new SubjectAnalysis();
		Set<Integer> schemaId = new HashSet<Integer>();
		int productline_rbo_id = 0;
		try {
			String keyValue = "";
			String fileContentMatch = "";
			log.debug("identifyAttachment for fileattachment id \""
					+ orderFileAttachmentId + "\".");
			List<Integer> schemaIdList = schemaIds;
			Partner_RBOProductLine schemaInfo = new Partner_RBOProductLine();
			String orderFileExt = FilenameUtils.getExtension(fileName);
			String orderFileName = FilenameUtils.getBaseName(fileName);
			//String[] fileNamePattern = fileName.split("\\.");
			//String orderFileName = fileNamePattern[0];
			//String orderFileExt = fileNamePattern[1];
			for (int i = 0; i < schemaIdList.size(); i++) {
				schemaInfo = orderEmailQueue.getProductlineInfo(schemaIdList
						.get(i));
				productline_rbo_id = schemaInfo.getId();
				log.debug("Processing attachment for productline id \""
						+ productline_rbo_id + "\".");
				if (schemaInfo.isOrderInMailBody()
						&& fileName.contains("CompleteEmail")) {
					// ///check mail body content
					log.debug("emailbody match starts for  \""
							+ schemaInfo.getId() + "\".");
					String result = fs.searchStringInBody(filePath, fileName,
							schemaInfo.getOrderInEmailBodyMatch());

					if (result.trim() != "" && !result.trim().isEmpty()) {
						if (fileContentMatch == "") {
							fileContentMatch = schemaInfo
									.getOrderInEmailBodyMatch();
						} else {
							fileContentMatch = fileContentMatch + "," + result;
						}
						schemaId.add(schemaInfo.getId());
						log.debug("emailbody match for  results  \""
								+ schemaInfo.getId() + "\".");
					}
				} else if (schemaInfo.isFileOrderMatchRequired()
						&& !fileName.contains("CompleteEmail")) {
					if (schemaInfo.getFileOrderMatchLocation().contains(
							"FileName")) {
						// /check file name
						String res = FileNameMatch(orderFileName, orderFileExt,
								schemaInfo.getFileOrderMatch());
						if (!res.isEmpty() || res != "") {
							if (fileContentMatch == "") {
								fileContentMatch = schemaInfo
										.getFileOrderMatch();
							} else {
								fileContentMatch = fileContentMatch + "," + res;
							}
							schemaId.add(schemaInfo.getId());
						}
					} else if (schemaInfo.getFileOrderMatchLocation().contains(
							"FileContent")) {
						String Sheetinfo = schemaInfo.getFileOrderMatch();
						if (!Sheetinfo.isEmpty()) {
							if (schemaInfo.getOrderFileNameExtension()
									.contains(fileExt)
									|| schemaInfo.getOrderFileNameExtension()
											.equalsIgnoreCase(fileExt)) {
								if (schemaInfo.getOrderFileNameExtension()
										.contains("xls")) {
									// method to search different values in
									// excell at different location
									if (fs.SearchContentInExcel(fileName,
											filePath, fileExt, Sheetinfo, log)) {
										// /create file content string and
										// schema id
										// list
										log.debug("add schema id for Sheetinfo \""
												+ Sheetinfo + "\".");
										log.debug("add schema id  \""
												+ schemaInfo.getId() + "\".");

										schemaId.add(schemaInfo.getId());
										if (fileContentMatch == "") {
											fileContentMatch = Sheetinfo;
										} else {
											fileContentMatch = fileContentMatch
													+ "," + Sheetinfo;
										}
										// keyword_location = "true";
									}
								} else if (schemaInfo
										.getOrderFileNameExtension().contains(
												"pdf")) {
									log.debug("searchin file extension for pdf");
									String keyword = Sheetinfo.trim();
									String[] keyword_s;
									if (keyword.contains("Value")
											&& !keyword.trim().isEmpty()) {
										keyword_s = keyword.split(":");
										keyValue = keyword_s[1];
										if (!keyValue.trim().isEmpty()) {
											if (fs.searchpdf(fileName,
													keyValue, filePath) != "") {
												schemaId.add(schemaInfo.getId());
												if (fileContentMatch == "") {
													fileContentMatch = Sheetinfo;
												} else {
													fileContentMatch = fileContentMatch
															+ "," + Sheetinfo;
												}
											}
										}
									}

								}
							}
						}
					}

				}
			}
			fileContentMatch = fs.removeDup(fileContentMatch);
			//System.out.println("fileContentMatch   " + fileContentMatch);
			if (schemaId.size() == 1) {
				log.debug("match schema list \"" + schemaId + "\".");
				int schema = schemaId.iterator().next();
				orderEmailQueue.updateOrderEmailAttachmentContent(
						orderFileAttachmentId, schema, "8", "", "", "",
						"Order", fileContentMatch);
				sa.subjectSearch(emailQueueId, orderFileAttachmentId, schemaId);
			} else if (schemaId.size() == 0) {
				log.debug("match schema list \"" + schemaId + "\".");
				// /check additional data file
				String schema_id_comment = "";
				for (Integer s : schemaIdList) {
					if (schema_id_comment == "") {
						schema_id_comment = "" + s;
					} else {
						schema_id_comment = schema_id_comment + "," + s;
					}

				}
				AdditionalFileAnalysis afa = new AdditionalFileAnalysis();
				Set<Integer> addFileIds = afa.identifyAdditionalDataFile(
						emailQueueId, fileName, filePath, fileExt,
						schema_id_comment);
				if (addFileIds.size() == 0) {
					orderEmailQueue.updateOrderEmailAttachment(
							orderFileAttachmentId, 0, "6", "", "",
							schema_id_comment, fileContentMatch);
				} else if (addFileIds.size() == 1) {
					orderEmailQueue.updateOrderEmailAttachment(
							orderFileAttachmentId,
							addFileIds.iterator().next(), "8", "", "", "",
							"AdditionalData");
				}
				schemaId.addAll(schemaIdList);
				sa.subjectSearch(emailQueueId, orderFileAttachmentId, schemaId);

			} else {
				log.debug("match schema list \"" + schemaId + "\".");
				sa.subjectSearch(emailQueueId, orderFileAttachmentId, schemaId);
				String schema_id_comment = "";
				for (Integer s : schemaId) {
					if (schema_id_comment == "") {
						schema_id_comment = "" + s;
					} else {
						schema_id_comment = schema_id_comment + "," + s;
					}

				}
				orderEmailQueue.updateOrderEmailAttachment(
						orderFileAttachmentId, 0, "6", "", "",
						schema_id_comment, fileContentMatch, "", "Order");

				// orderEmailQueue.updateOrderEmailAttachment(orderFileAttachmentId,
				// 0, "6", "", "", schema_id_comment, fileContentMatch);

			}
		} catch (Exception e) {
			log.error("Exception while order file content analysis");
			throw e;
		}

	}

	/**
	 * @param filePath
	 * @param fileName
	 * @param keyvalues
	 * @return
	 * @throws Exception
	 */
	public String searchStringInBody(String filePath, String fileName,
			String keyvalues) throws Exception {
		String responseResults = "";
		try {
			FileSearch fs = new FileSearch();
			log.debug("search for value" + keyvalues);
			log.debug("search for filePath" + filePath);
			log.debug("search for fileName" + fileName);
			if(keyvalues==null||keyvalues==""|| keyvalues.isEmpty()){
				log.debug("data match valuse is null");
				return responseResults;
			}
			if (keyvalues.contains("|") || !keyvalues.isEmpty()) {
				String[] keyWords = keyvalues.split("\\|");
				for (String keyWord : keyWords) {
					if (!keyWord.trim().isEmpty()) {
						keyWord = keyWord.trim();
						String result = fs.searchContentFromMailBody(filePath,
								fileName, keyWord, false);
						if (result != "") {
							if (responseResults == "") {
								responseResults = result;
							} else {

								responseResults = responseResults + ","
										+ result;
							}
						}

					}

				}
			} else {
				log.debug("EmailBodyMatch is empty for filepath \"" + filePath
						+ "\".");
			}
			log.debug("EmailBodyMatch result is  \"" + responseResults + "\".");
			responseResults = fs.removeDup(responseResults);
		} catch (Exception e) {
			log.error("Exception while searching string in emailbody.");
			throw e;
		}
		return responseResults;
	}

	/**
	 * @param FileName
	 * @param FileExt
	 * @param MatchString
	 * @return
	 * @throws Exception
	 */
	public String FileNameMatch(String FileName, String FileExt,
			String MatchString) throws Exception {
		log.debug("Processing attachment identification from file name.");
		String result = "";
		try {
			if(MatchString==null||MatchString==""|| MatchString.isEmpty()){
				return result;
			}
			if (!MatchString.isEmpty()) {
				if (MatchString.contains("|")) {
					String[] fileNamePattrens = MatchString.split("\\|");
					for (String fileNamePattren : fileNamePattrens) {
						String[] fileNameMatch = fileNamePattren.split("\\.");
						String fileNameP = fileNameMatch[0].trim();
						String fileExtP = fileNameMatch[1].trim();
						fileNameP = fileNameP.replace("*", "(.*)");

						if (FileName.matches(fileNameP)
								&& FileExt.contains(fileExtP)) {
							result = fileNameP;
							log.debug("file name match order_file_name   \""
									+ FileName + "\".");
						} else {
							log.debug("file name not match with order file name  \""
									+ FileName + "\".");
						}
					}
				}
			} else {
				log.debug("FileOrderMatch is empty.");
			}
		} catch (Exception e) {
			log.error("Exception while match file Name.");
			throw e;
		}
		return result;
	}

	/**
	 * @param FileName
	 * @param FileExt
	 * @param MatchString
	 * @return
	 * @throws Exception
	 *             / public String FileContentMatch(String FileName, String
	 *             FileExt, String MatchString) throws Exception {
	 *             log.debug("Processing attachment identification from file name."
	 *             ); String result = ""; try{ if (!MatchString.isEmpty()) { if
	 *             (MatchString.contains("|")) { String[] fileNamePattrens =
	 *             MatchString.split("\\|"); for (String fileNamePattren :
	 *             fileNamePattrens) { String[] fileNameMatch =
	 *             fileNamePattren.split("\\."); String fileNameP =
	 *             fileNameMatch[0].trim(); String fileExtP =
	 *             fileNameMatch[1].trim(); fileNameP = fileNameP.replace("*",
	 *             "(.*)");
	 * 
	 *             if (FileName.matches(fileNameP) &&
	 *             FileExt.contains(fileExtP)) { result = fileNameP;
	 *             log.debug("file name match order_file_name   \"" + FileName +
	 *             "\"."); } else {
	 *             log.debug("file name not match with order file name  \"" +
	 *             FileName + "\"."); } } } } else {
	 *             log.debug("FileOrderMatch is empty."); } } catch (Exception
	 *             e) { log.error("Exception while filecontent match."); throw
	 *             e; } return result; }
	 */
}
