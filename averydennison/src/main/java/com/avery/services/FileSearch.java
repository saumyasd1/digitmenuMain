package com.avery.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adeptia.indigo.logging.Logger;
import com.avery.Model.SearchCellAddress;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class FileSearch {

	static Logger log = Logger.getLogger(FileSearch.class.getName());
	public String regexSupportString = "\\b";
	public static String AND_SEPERATOR = "_\\&\\&_";
	public static String OR_SEPERATOR = "_\\|\\|_";
	public static String VALUE_SEPARATOR = "_\\._";
	public static String VALUE_SEPARATOR_WITHOUTESCAPE = "_._";

	/**
	 * method searchContentFromMailBody
	 * 
	 * @param filePath
	 * @param fileName
	 * @param content
	 * @return
	 */
	public String searchContentFromMailBody(String filePath, String fileName,
			String content, boolean isCaseSensitive) throws Exception {
		try {
			if(content==null||content==""|| content.isEmpty()){
				return "";
			}
			log.info("search in emailbody path :\"" + filePath + "for content"
					+ content + "\".");
			if (fileName.contains("CompleteEmail")) {
				String[] fileNameParts = fileName.split("\\.");
				fileName = fileNameParts[0] + ".html";
			} else {
				log.debug("Email body file is wrong.");
				return "";
			}
			if (findMatchingString(
					generateRegexSupportString(content),
					readFile(filePath + File.separatorChar + fileName,
							StandardCharsets.UTF_8), isCaseSensitive)) {
				log.debug("content matches.");
				return content;
			}

		} catch (FileNotFoundException e) {
			log.debug("CompleteEmail.eml not found.");
			return "";
			// e.printStackTrace();
		} catch (IOException e) {
			log.debug("IOException while searching in CompleteEmail.eml");
			return "";
			// e.printStackTrace();
		}
		return "";
	}

	/**
	 * Method to generate regex support to String content for word
	 * 
	 * @param content
	 * @return regex support String
	 */
	public String generateRegexSupportString(String content) {
		return regexSupportString + content + regexSupportString;
	}

	/**
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path, Charset encoding)
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	/**
	 * Method to match string for defined regEx
	 * 
	 * @param regExString
	 * @param inputString
	 * @param isCaseSensitive
	 */
	public boolean findMatchingString(String regExString, String inputString,
			boolean isCaseSensitive) {
		Matcher matcher = null;
		Pattern pattern = null;
		try {
			if (isCaseSensitive) {
				pattern = Pattern.compile(regExString);
			} else {
				pattern = Pattern
						.compile(regExString, Pattern.CASE_INSENSITIVE);
			}
			matcher = pattern.matcher(inputString);
			if (matcher.find()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param FileName
	 * @param FilePath
	 * @param FileExt
	 * @param CellDetail
	 * @return
	 * @throws Exception
	 */
	public boolean SearchContentInExcel(String FileName, String FilePath,
			String FileExt, String CellDetail, Logger log) throws Exception {

		// String keywordLocation = "";
		Boolean result = true;
		String searchContentList = "";
		String cellNo = "";
		try {
			SearchCellAddress read_att_cell = new SearchCellAddress();
			// add handling for CellDetail null value
			if(CellDetail==null||CellDetail==""|| CellDetail.isEmpty()){
				log.info("fileOrderMatch column value is null in table.");
				return false;
			}
			
			log.info("Starting seacrhing process for file:\"" + FilePath
					+ File.separatorChar + FileName + "\".");
			// if (CellDetail.contains(AND_SEPERATOR)) {
			// log.info("Starting seacrhing process for CellDetail:\""+CellDetail+"\".");
			log.info("Starting seacrhing process for CellDetail:\""
					+ CellDetail + "\".");
			String[] searchIdentifierArray = CellDetail.split(AND_SEPERATOR);
			for (String searchIdentifier : searchIdentifierArray) {
				log.info("Starting seacrhing process for searchIdentifier:\""
						+ searchIdentifier + "\".");
				if (searchIdentifier.contains(";")
						&& !searchIdentifier.isEmpty()) {
					String[] searchDetails = searchIdentifier.split(";");
					// for (String Sheetdetail_search : Sheetdetails) {
					String[] cellValues;
					String[] cellNos;
					if (searchDetails[1].contains("Cell")) {
						cellNos = searchDetails[1].split(":");
						cellNo = cellNos[1];
						log.debug("Starting seacrhing process for cellNo:\""
								+ cellNo + "\".");
					}
					if (cellNo.isEmpty()) {
						return false;
					}
					if (searchDetails[0].contains("Value")) {
						cellValues = searchDetails[0].split(":");
						searchContentList = cellValues[1];
						if (searchContentList != null
								&& searchContentList.indexOf("*") != -1) {
							searchContentList = searchContentList.replace("*",
									"(.*)");
						}
						// if(searchContentList.trim().startsWith("Global C"))
						String[] searchContent = searchContentList
								.split(VALUE_SEPARATOR);
						for (String searchValue : searchContent) {
							if (!searchValue.isEmpty()) {
								log.debug("Starting seacrhing process for search content:\""
										+ searchValue + "\".");
								// if(searchContentList.contains(VALUE_SEPARATOR_WITHOUTESCAPE)){
								Boolean cellPosition = read_att_cell
										.searchContentInAllSheet(FilePath,
												FileName, searchValue, cellNo);

								if (cellPosition) {
									result = true;
									break;

								} else {
									result = false;
								}
							} else {
								log.debug("Search value is empty, so returning false.");
								return false;
							}
						}
					}
				}
				if (!result) {
					log.debug("Search text is not found for searchIdentifier:\""
							+ searchIdentifier + "\".");
					return result;
				}
			}
		} catch (Exception e) {
			log.error("Exception while excel file content search");
			throw e;
		}
		return result;
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
			if(keyvalues==null||keyvalues==""|| keyvalues.isEmpty()){
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
										+ keyWord;
							}
						}

					}

				}
			} else {
				log.debug("EmailBodyMatch is empty for filepath \"" + filePath
						+ "\".");
			}
			responseResults = removeDup(responseResults);
		} catch (Exception e) {
			log.error("Exception while email body content search");
			throw e;
		}
		return responseResults;
	}

	/**
	 * @param filePath
	 * @param fileName
	 * @param keyvalues
	 * @return
	 * @throws Exception
	 */
	public String searchStringInFile(String filePath, String fileName,
			String keyvalues) throws Exception {

		SearchCellAddress readExcel = new SearchCellAddress();
		String result = "";
		log.info("search value in file \"" + keyvalues
				+ "\".");
		
		try {
			if(keyvalues==null||keyvalues==""|| keyvalues.isEmpty()){
				return result;
			}
			if (keyvalues.contains("|") || !keyvalues.isEmpty()) {
				String[] keyWords = keyvalues.split("\\|");
				for (String keyWord : keyWords) {
					String searchContent = "";
					String cellNo = "";
					if (keyWord.contains(";") && fileName.contains("xls")) {
						String[] cellValues;
						String[] cellNos;
						String[] searchDetails = keyWord.split(";");
						if (searchDetails[1].contains("Cell")) {

							cellNos = searchDetails[1].split(":");
							cellNo = cellNos[1].trim();
							log.debug("Starting seacrhing process for cellNo:\""
									+ cellNo + "\".");
						}
						if (cellNo.isEmpty()) {
							log.debug("found cellNo empty :\"" + cellNo + "\".");
							return "";
						}
						if (searchDetails[0].contains("Value")) {
							cellValues = searchDetails[0].split(":");
							searchContent = cellValues[1].trim();
						}
						if (readExcel.searchContentInAllSheet(filePath,
								fileName, searchContent, cellNo)) {
							if (result.isEmpty() || result == "") {
								result = searchContent;
							} else {
								result = result + "," + searchContent;
							}
						}
					} else if (keyvalues.contains(":")
							&& fileName.contains("pdf")) {
						if (keyvalues.contains("Value")) {
							String[] cellValues = keyvalues.split(":");
							searchContent = cellValues[1].trim();
						}
						String pdfResult = searchpdf(fileName, searchContent,
								filePath);
						if (pdfResult != "") {
							if (result.isEmpty() || result == "") {
								result = pdfResult;
							} else {
								result = result + "," + pdfResult;
							}
						}
					} else {
						log.info("search content :\"" + keyvalues
								+ "and file name" + fileName
								+ "\"are not in sync.");
					}
				}
			} else {
				log.info("fileOrderMatch column value is null in table.");
				return "";
			}
		} catch (Exception e) {
			log.error("Exception while search string in file.");
			throw e;
		}
		return result;
	}

	/**
	 * method removeDup
	 * 
	 * @param s
	 * @return string without duplicate values
	 */

	public String removeDup(String s) {
		String ns = new LinkedHashSet<String>(Arrays.asList(s.split(",")))
				.toString().trim().replaceAll("(^\\[|\\]$)", "");
		ns = ns.replaceAll(", ", ",");
		ns = ns.replaceAll(" ,", ",");
		return ns;
	}

	/**
	 * method searchpdf
	 * 
	 * @param filename
	 * @param keyword
	 * @param filepath
	 * @return string keyword
	 * @throws Exception
	 */
	public String searchpdf(String filename, String keyword, String filepath)
			throws Exception {
		// OrderEmailQueueInterface orderEmailQueue = new
		// OrderEmailQueueModel();
		String result = "";
		try {
			if(keyword==null||keyword==""|| keyword.isEmpty()){
				return "";
			}
			log.debug("search pdf for filename \"" + filename
					+ " \"for filepath \"" + filepath + "\".");
			if (filepath == "") {
				log.debug("search pdf for filename \"" + filename
						+ " \"for empty filepath .");
				return "";
			}

			PdfReader reader = new PdfReader(filepath + "/" + filename);

			PdfReaderContentParser parser = new PdfReaderContentParser(reader);

			//PrintWriter out = new PrintWriter(new FileOutputStream(keyword));

			TextExtractionStrategy strategy;

			for (int i = 1; i <= reader.getNumberOfPages(); i++) {

				strategy = parser.processContent(i,
						new SimpleTextExtractionStrategy());
				String pdf_data = strategy.getResultantText();
				if (pdf_data.toLowerCase().contains(keyword.toLowerCase())) {
					result = keyword;
					log.debug("keyword found in pdf is \"" + keyword + "\".");
				}
			}
			reader.close();
			//out.flush();
			//out.close();
		} catch (FileNotFoundException e) {
			log.error("File not found exception while searching pdf file");
			throw e;
		} catch (Exception e) {
			log.error("excepti while search pdf");
			throw e;
		}

		return result;
	}

	/**
	 * method searchContentFromMailBody
	 * 
	 * @param filePath
	 * @param fileName
	 * @param content
	 * @return
	 */
	public String searchMultipleContentFromMailBody(String filePath,
			String fileName, String content, boolean isCaseSensitive)
			throws Exception {
		try {
			log.info("search in emailbody path :\"" + filePath + "for content"
					+ content + "\".");
			if (fileName.contains("CompleteEmail")) {
				String[] fileNameParts = fileName.split("\\.");
				fileName = fileNameParts[0] + ".html";
			} else {
				log.debug("Email body file is wrong.");
				return "";
			}
			if (content == null || content == "" || content.isEmpty()) {
				log.debug("search content is wrong.");
				return "";
			}
			if (findMatchingString(
					generateRegexSupportString(content),
					readFile(filePath + File.separatorChar + fileName,
							StandardCharsets.UTF_8), isCaseSensitive)) {
				log.debug("content matches.");
				return content;
			}

		} catch (FileNotFoundException e) {
			log.debug("CompleteEmail.eml not found.");
			return "";
			// e.printStackTrace();
		} catch (IOException e) {
			log.debug("IOException while searching in CompleteEmail.eml");
			return "";
			// e.printStackTrace();
		}
		return "";
	}

}
