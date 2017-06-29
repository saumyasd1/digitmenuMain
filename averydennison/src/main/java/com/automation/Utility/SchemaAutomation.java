package com.automation.Utility;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import com.adeptia.indigo.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;

import com.adeptia.indigo.services.ServiceException;


/**
 * @author Rakesh
 * Java Class to generate schema XSD based on comments
 *
 */
public abstract class SchemaAutomation implements AdvSchemaConstants , SchemaInterface{
	
	public StringBuffer sb = new StringBuffer();
	public List<String> sectionNameList=new ArrayList<String>();
	public ConcurrentHashMap<String, ArrayList<String>> commentMap=new ConcurrentHashMap<String, ArrayList<String>>();
	public HashMap<String, String> typeMap=new HashMap<String, String>();
	public static Logger log;
	
	
	/**
	 * Method to get HashSet<String> From Comma Separator String
	 * @param fileType
	 * @return HashSet<String>
	 * @author Rakesh
	 */
	public HashSet<String> getFieldsForCommentVerification(String fileType) {
		log.debug("Start method name=\"getFieldsForCommentVerification\"");
		String commentVerify[] = null;
		if(fileType.equals("PDF")){
			commentVerify=commentFieldsForPDF.split("\\|");
		}else if(fileType.equals("EXCEL")){
			commentVerify=commentFieldsForExcel.split("\\|");
		}
		List<String> list = Arrays.asList(commentVerify);
		log.debug("End method name=\"getFieldsForCommentVerification\"");
		return new HashSet<String>(list);
	}
	
	/**
	 * Method to compare commentValue with Standard fields
	 * @param compareValue
	 * @param fileType
	 * @return boolean
	 * @author Rakesh
	 */
	public boolean compareCommentValue(String compareValue, String fileType) {

		log.debug("Start method name=\"compareCommentValue\"");
		HashSet<String> hashSet = getFieldsForCommentVerification(fileType);
		Iterator<String> iterator = hashSet.iterator();
		while (iterator.hasNext()) {
			String field = iterator.next();
			if (field.equals(compareValue)) {
				log.info("compareValue =\"" + compareValue + "\" is find");
				log.debug("End method name=\"compareCommentValue\"");
				return true;
			}
		}
		log.info("compareValue =\"" + compareValue + "\" isn't find");
		log.debug("End method name=\"compareCommentValue\"");
		return false;
	}
	
	/**
	 * Method to check whether comment is valid
	 * @param comment
	 * @param fileType
	 * @return boolean
	 * @author Rakesh
	 */
	public boolean verifyComment(String comment, String fileType) {

		log.debug("Start method name=\"verifyComment\"");
		if (!comment.startsWith("Sheets") && !comment.startsWith("sectionName")) {
			log.info("Comment isn't valid !");
			return false;
		} else {
			log.info("Start to verify the Comment =\"" + comment + "\"");
			if (comment.contains(commentLineSeparator) || comment.contains(commentLineSeparator1)) {
				String commentArray[] =null;
					if(comment.contains(commentLineSeparator)){
						commentArray=comment.split(commentLineSeparator);
					}else if(comment.contains(commentLineSeparator1)){
						commentArray=comment.split(commentLineSeparator1);
					}
				for (int i = 0; i < commentArray.length; i++) {
					if(!commentArray[i].trim().isEmpty()){
					if(commentArray[i].contains(commentValueSeparator)){
						int count=getCount(commentArray[i], "=");
						if(count == 1){
							String elementArray[] = commentArray[i].split(commentValueSeparator);
							if (!compareCommentValue(elementArray[0], fileType)) {
								log.info("Comment isn't valid !");
								log.debug("End method name=\"verifyComment\"");
								return false;
							}
						}else{
							log.info("comment line contains multiple equal sign(=) and comment line=\""+commentArray[i]+"\"");
							return false;
						}
						}
					}else{
						if(!commentArray[i].trim().isEmpty()){
							return false;
						}
					}
				}
				log.info("End to verify the Comment");
				log.debug("End method name=\"verifyComment\"");
				return true;
			} else {
				log.info("End to verify the Comment");
				log.debug("End method name=\"verifyComment\"");
				return false;
			}

		}
	}
	
	/**
	 * Method to get value or key from comment on basis of key
	 * @param comment
	 * @param key
	 * @return String
	 * @author Rakesh
	 */
	public String getValueFromComment(String comment, String key) {

		log.debug("Start method name=\"getValueFromComment\"");
		if (comment == null || comment.trim().isEmpty()) {
			log.error("Comment is null !");
			return null;
		} else {
			log.info("Comment : " + comment);
			log.info("Required fieldName form comment: " + key);
			String value = null;
			String commentArray[]=null;
			if(comment.contains(commentLineSeparator)){
				commentArray=comment.split(commentLineSeparator);
			}else if(comment.contains(commentLineSeparator1)){
				commentArray=comment.split(commentLineSeparator1);
			}
			for (int i = 0; i < commentArray.length; i++) {
				String elementArray[] = commentArray[i].split("=");
				if (elementArray.length == 2) {
					if (elementArray[elementArray.length - 2].equals(key)) {
						if (!elementArray[elementArray.length - 1].isEmpty()) {
							value = elementArray[elementArray.length - 1];
						}
					}
				}
			}
			log.debug("End method name=\"getValueFromComment\"");
			return value;
		}
	}
	
	/**
	 * Method to get count of particular field in comment
	 * @param comment
	 * @param field
	 * @return int
	 * @author Rakesh
	 */
	public int getCount(String comment,String field){
		log.debug("Start method name=\"getCount\"");
		int count = 0;
		while (comment.indexOf(field)>-1){
			comment = comment.replaceFirst(field, "");
		    count++;
		}
		log.debug("End method name=\"getCount\"");
		return count ;
	}
	
	/**
	 * Method to check whether list contains sectionName
	 * @param sectionName
	 * @return boolean
	 * @author Rakesh
	 */
	public boolean checkListContainSection(String sectionName){
		log.debug("Start method name=\"checkListContainSection\"");
		 for(int z=0;z<sectionNameList.size();z++){
			 String name=sectionNameList.get(z);
			 if(sectionName.equals(name)){
				 log.debug("End method name=\"checkListContainSection\"");
				 return true;
			 }
		 }
		log.debug("End method name=\"checkListContainSection\"");
		 return false;
	}
	
	/**
	 * Method to generate XSD for Section 
	 * @param comment
	 * @param fileType
	 * @author Rakesh
	 */
	public void generateSectionXSD(String comment , String fileType) {

		log.debug("Start method name=\"generateSectionXSD\"");
		if (comment == null || comment.trim().isEmpty()) {
			log.error("Comment is null !");
		} else {
			String dataExtractionMode=null;
			if (comment.contains("sectionOccurrenceLevel")) {
				String sectionoccurrenceLevel = getValueFromComment(comment,"sectionOccurrenceLevel");
				if (sectionoccurrenceLevel != null) {
					sb.append("<occurrenceLevel><![CDATA["
							+ sectionoccurrenceLevel
							+ "]]></occurrenceLevel>\n");
				}
			}
			if (comment.contains("primaryFieldNames")) {
				String primaryFieldNames = getValueFromComment(comment,"primaryFieldNames");
				if (primaryFieldNames != null) {
					sb.append("<primaryFieldNames><![CDATA["
							+ primaryFieldNames + "]]></primaryFieldNames>\n");
				}
			}
			if (comment.contains("ignoreBlankRows")) {
				String ignoreBlankRows = getValueFromComment(comment,"ignoreBlankRows");
				if (ignoreBlankRows != null) {
					sb.append("<ignoreBlankRows><![CDATA[" + ignoreBlankRows
							+ "]]></ignoreBlankRows>\n");
				}
			}
			if (comment.contains("uniqueFieldNames")) {
				String uniqueFieldNames = getValueFromComment(comment,"uniqueFieldNames");
				if (uniqueFieldNames != null) {
					sb.append("<uniqueFieldNames><![CDATA[" + uniqueFieldNames
							+ "]]></uniqueFieldNames>\n");
				}
			}
			if (comment.contains("sectionIdentifier")) {
				String sectionIdentifier = getValueFromComment(comment,"sectionIdentifier");
				if (sectionIdentifier != null) {
					sb.append("<sectionIdentifier><![CDATA[" + sectionIdentifier
							+ "]]></sectionIdentifier>\n");
				}
			}
			if (comment.contains("valueMatchOpertaor")) {
				String valueMatchOpertaor = getValueFromComment(comment,"valueMatchOpertaor");
				if (valueMatchOpertaor != null) {
					sb.append("<valueMatchOpertaor><![CDATA[" + valueMatchOpertaor
							+ "]]></valueMatchOpertaor>\n");
				}
			}
			if (comment.contains("includeIdentifier")) {
				boolean includeIdentifier = Boolean.parseBoolean(getValueFromComment(comment,"includeIdentifier"));
				if (includeIdentifier) {
					sb.append("<includeIdentifier><![CDATA[" + includeIdentifier
							+ "]]></includeIdentifier>\n");
				}
			}
			if (comment.contains("endTokenIdentifier")) {
				String endTokenIdentifier = getValueFromComment(comment,"endTokenIdentifier");
				if (endTokenIdentifier != null) {
					sb.append("<endTokenIdentifier><![CDATA[" + endTokenIdentifier
							+ "]]></endTokenIdentifier>\n");
				}else{
					sb.append("<endTokenIdentifier><![CDATA[]]></endTokenIdentifier>\n");
				}
			}
			if (comment.contains("sectionEndTokenOperator")) {
				String sectionEndTokenOperator = getValueFromComment(comment,"sectionEndTokenOperator");
				if (sectionEndTokenOperator != null) {
					sb.append("<sectionEndTokenOperator><![CDATA[" + sectionEndTokenOperator
							+ "]]></sectionEndTokenOperator>\n");
				}
			}
			if (comment.contains("numOfSectionPart")) {
				String numOfSectionPart = getValueFromComment(comment,"numOfSectionPart");
				if (numOfSectionPart != null) {
					sb.append("<numOfSectionPart><![CDATA[" + numOfSectionPart
							+ "]]></numOfSectionPart>\n");
				}
			}
			if (comment.contains("whichSectionPart")) {
				String whichSectionPart = getValueFromComment(comment,"whichSectionPart");
				if (whichSectionPart != null) {
					sb.append("<whichSectionPart><![CDATA[" + whichSectionPart
							+ "]]></whichSectionPart>\n");
				}
			}
			if (comment.contains("dataExtractionMode")) {
				dataExtractionMode = getValueFromComment(comment,"dataExtractionMode");
				if (dataExtractionMode != null) {
					sb.append("<dataExtractionMode><![CDATA[" + dataExtractionMode
							+ "]]></dataExtractionMode>\n");
				}
			}
			
			if (comment.contains("nextRecordIdentifier")) {
				String nextRecordIdentifier = getValueFromComment(comment,"nextRecordIdentifier");
				if (nextRecordIdentifier != null) {
					sb.append("<nextRecordIdentifier><![CDATA[" + nextRecordIdentifier
							+ "]]></nextRecordIdentifier>\n");
				}
			}
			if (comment.contains("nextRecordValidationString")) {
				String nextRecordValidationString = getValueFromComment(comment,"nextRecordValidationString");
				if (nextRecordValidationString != null) {
					sb.append("<nextRecordValidationString><![CDATA[" + nextRecordValidationString
							+ "]]></nextRecordValidationString>\n");
				}
			}
			if (comment.contains("recordValidationString")) {
				String recordValidationString = getValueFromComment(comment,"recordValidationString");
				if (recordValidationString != null) {
					sb.append("<recordValidationString><![CDATA[" + recordValidationString
							+ "]]></recordValidationString>\n");
				}
			}
			if (comment.contains("isDataInTable")) {
				boolean isDataInTable =	Boolean.parseBoolean(getValueFromComment(comment,"isDataInTable"));
				if (isDataInTable) {
					sb.append("<isDataInTable><![CDATA[" + isDataInTable
							+ "]]></isDataInTable>\n");
				}
			}
			if(fileType != null && fileType.equals("PDF")){
				if(dataExtractionMode != null){
					if (comment.contains("originalName")) {
						String originalName = getValueFromComment(comment,"originalName");
						if (originalName != null) {
							sb.append("<originalName><![CDATA[" + originalName.trim()
									+ "]]></originalName>\n");
						}else{
							sb.append("<originalName><![CDATA[ ]]></originalName>\n");
						}
					}
				}
			}
		}
		log.debug("End method name=\"generateSectionXSD\"");
	}
	
	/**
	 * Method to generate XSD for Element
	 * @param comment
	 * @param fileType
	 * @author Rakesh
	 */
	public void generateElementXSD(String comment, String fileType) {

		log.debug("Start method name=\"generateElementXSD\"");
		if (comment == null || comment.trim().isEmpty()) {
			log.error("Comment is null !");
		}else {
			if (comment.contains("elementName")) {
				String elementName = getValueFromComment(comment,"elementName");
				String elementType = getValueFromComment(comment,"elementType");
				if(fileType.equals("PDF")){
					if (elementName != null && elementType != null) {
						elementName=elementName.trim();
						if(elementType.trim().equals("Integer")){
							sb.append("<xs:element maxOccurs=\"1\"  minOccurs=\"0\" name=\""
									+ elementName + "\" type=\"xs:integer\">\n");
						}else{
							sb.append("<xs:element maxOccurs=\"1\"  minOccurs=\"0\" name=\""
									+ elementName + "\" type=\"xs:string\">\n");
						}
						sb.append(APPINFOHeader);
					}
				}else if(fileType.equals("EXCEL")){
					if (elementName != null){
						sb.append("<xs:element maxOccurs=\"1\"  minOccurs=\"0\" name=\""
								+ elementName + "\" type=\"xs:string\">\n");
					}
					sb.append(APPINFOHeader);
				}
			} 
			if (comment.contains("occurrenceLevel")) {
				String occurrenceLevel = getValueFromComment(comment,"occurrenceLevel");
				if (occurrenceLevel != null) {
					sb.append("<occurrenceLevel><![CDATA[" + occurrenceLevel
							+ "]]></occurrenceLevel>\n");
				}
			}

			if (comment.contains("relativeRowRanges")) {
				String relativeRowRanges = getValueFromComment(comment,"relativeRowRanges");
				if (relativeRowRanges != null) {
					sb.append("<relativeRowRanges><![CDATA["
							+ relativeRowRanges + "]]></relativeRowRanges>\n");
				}
			}

			if (comment.contains("relativeColumnRanges")) {
				String relativeColumnRanges = getValueFromComment(comment,"relativeColumnRanges");
				if (relativeColumnRanges != null) {
					sb.append("<relativeColumnRanges><![CDATA["
							+ relativeColumnRanges
							+ "]]></relativeColumnRanges>\n");
				}
			}
			
			if (comment.contains("ignoreField")) {
				boolean ignoreField = Boolean.parseBoolean(getValueFromComment(comment,"ignoreField"));
				if (ignoreField) {
					sb.append("<ignoreField><![CDATA["
							+ ignoreField
							+ "]]></ignoreField>\n");
				}
			}
			
			if (comment.contains("originalName")) {
				String originalName = getValueFromComment(comment,"originalName");
				if (originalName != null) {
					sb.append("<originalName><![CDATA["
							+ originalName.trim()
							+ "]]></originalName>\n");
				}else{
					sb.append("<originalName><![CDATA[ ]]></originalName>\n");
				}
			}
			
			sb.append(FormatTag+DataModeTag+APPINFOFooter+ElementFooter);
		}
		log.debug("Start method name=\"generateElementXSD\"");
	}
	
	/**
	 * Method to generate final XSD from String buffer at same location of file
	 * @param inputFile
	 * @param sb
	 * @throws ServiceException
	 * @author Rakesh
	 */
	public void generateXSDFromStringBuffer(String inputFile, StringBuffer sb) throws ServiceException{
		log.debug("Start method name=\"generateXSDFromStringBuffer\"");
		if (sb == null || sb.length() == 0) {
			log.error("Either StringBuffer of comment is null or StringBuffer is empty!");
			throw new ServiceException("Error in StringBuffer while creating XSD.");
		} else {
			inputFile = inputFile.substring(0, inputFile.length()-FilenameUtils.getExtension(inputFile).length()) + "xsd";
			log.info("Output file along with it's location =\"" + inputFile
					+ "\"");
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(inputFile),
								StandardCharsets.UTF_8));
				bufferedWriter.write(sb.toString());
				bufferedWriter.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new ServiceException(
						"Error while creating XSD for file:\"" + inputFile
								+ "\"." + e.getMessage(), e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ServiceException(
						"Error while creating XSD for file:\"" + inputFile
								+ "\"." + e.getMessage(), e);
			}
			log.debug("End method name=\"generateXSDFromStringBuffer\"");
		}
	}
	
	/**
	 * Method to get element name from OriginalName
	 * @param originalName
	 * @return String
	 * @author Rakesh
	 */
	public String getElementNameUsingRegex(String originalName) {

		log.debug("Start method name=\"getElementNameUsingRegex\"");
		String elementName = null;
		if (originalName == null || originalName.trim().isEmpty()) {
			log.error("Either originalName of comment is null or originalName is empty!");
		} else {
			log.info("This method is call when elementName isn't present in comment. So, we have to generate elementName from originalName");
			Pattern pattern = Pattern.compile(regexPatternOfElement);
			Matcher matcher = pattern.matcher(originalName.trim());
			elementName = matcher.replaceAll("").trim().replaceAll(" ", "_");
			if (elementName.isEmpty()) {
				log.error("originalName of comment isn't contains any english alphabets !");
				elementName = "chineseElement";
			}
		}
		log.info("OriginalName=\"" + originalName
				+ "\" and generated elementName=\"" + elementName + "\"");
		log.debug("End method name=\"getElementNameUsingRegex\"");
		return elementName;
	}
	

	/**
	 * Method to add elementName and originalName in comment
	 * @param comment
	 * @param cell
	 * @return String
	 * @author Rakesh
	 */
	public String modifyComment(String comment, Cell cell){
		log.debug("Start method name=\"modifyComment\"");
		String originalValue=cell.getStringCellValue().replaceAll("\n", " ");
		/*if(!comment.contains("elementName")){
			String elementName=getElementNameUsingRegex(originalValue);
			comment=comment.trim()+"\nelementName="+elementName;
		}*/
		log.debug("End method name=\"modifyComment\"");
		return comment+"\noriginalName="+originalValue;
	}
	
	
	/**
	 * Method to generate XSD for key-values 
	 * @param sectionType
	 * @param sectionName
	 * @param commentList
	 * @param fileType
	 * @author Rakesh
	 */
	public void generateKeyValuesXSD(String sectionType, String sectionName,
			List<String> commentList, String fileType) {
		log.debug("Start method name=\"generateKeyValuesXSD\"");
		if (commentList == null) {
			log.error("HashMap is null !");
		} else {
				ListIterator<String> iterator = commentList.listIterator();
				String firstComment = iterator.next();
					sb.append("<xs:element maxOccurs=\"unbounded\"  minOccurs=\"1\" name=\"" + sectionName.trim() + "\">\n");
					sb.append(APPINFOHeader);
					sb.append("<sectionType><![CDATA[" + sectionType + "]]></sectionType>\n");
					String dataExtractionMode=getValueFromComment(firstComment,"dataExtractionMode");
					generateSectionXSD(firstComment , fileType);
					if(dataExtractionMode == null && fileType.equals("PDF")){
						sb.append(FormatTag + DataModeTag);
					}
					sb.append(APPINFOFooter);
					if(dataExtractionMode != null || fileType.equals("EXCEL")){
						sb.append(ComplexHeader+SequenceHeader);
						iterator.previous();
						while (iterator.hasNext()) {
							String comment =iterator.next();
							if(getCount(comment, "elementName") > 1){
								String c[]=comment.split("elementName");
								for(int i=1;i<c.length;i++){
									comment="elementName"+c[i];
									generateElementXSD(comment, fileType);
								}
							}else{
								generateElementXSD(comment, fileType);
							}
						}
						sb.append(SequenceFooter+SectionElementAttribute+ComplexFooter);
					}
				sb.append(ElementFooter);
		}
		log.debug("End method name=\"generateKeyValuesXSD\"");
	}
	
	/**
	 * Method to generate XSD for table
	 * @param sectionType
	 * @param sectionName
	 * @param commentList
	 * @param fileType
	 * @author Rakesh
	 */
	public void generateTableXSD(String sectionType, String sectionName ,
			ArrayList<String> commentList, String fileType) {

		log.debug("Start method name=\"generateTableXSD\"");
		if (commentList == null) {
			log.error("HashMap is null !");
		} else {
				ListIterator<String> iterator = commentList.listIterator();
				String firstComment =iterator.next();
					sb.append("<xs:element maxOccurs=\"unbounded\"  minOccurs=\"1\" name=\"" + sectionName.trim() + "\">\n");
					sb.append(APPINFOHeader);
					sb.append("<sectionType><![CDATA[" + sectionType + "]]></sectionType>\n");
					generateSectionXSD(firstComment , fileType);
					sb.append(APPINFOFooter+ComplexHeader+SequenceHeader+RecordElementHeader+APPINFOHeader+RecordElementValue+APPINFOFooter+ComplexHeader+SequenceHeader);
				iterator.previous();
				while (iterator.hasNext()) {
					String comment = iterator.next();
						if(getCount(comment, "elementName") > 1){
							String c[]=comment.split("elementName");
							for(int i=1;i<c.length;i++){
								comment="elementName"+c[i];
								generateElementXSD(comment, fileType);
							}
						}else{
							generateElementXSD(comment, fileType);
						}
				}
					sb.append(SequenceFooter+ComplexFooter+ElementFooter+SequenceFooter+SectionElementAttribute+ComplexFooter+ElementFooter);
		}
		log.debug("End method name=\"generateTableXSD\"");
	}
	
	
	


	/**
	 * Method to generate typeMap and commentMap
	 * @param comment
	 * @param fileType
	 * @author Rakesh
	 */
	public void generateMapFromComment(String comment,String fileType, Cell cell){
		log.debug("Start method name=\"generateMapFromComment\"");
		String sectionName=getValueFromComment(comment, "sectionName");
		String sectionType=getValueFromComment(comment, "sectionType");
		if(sectionName != null){
			 if(! sectionNameList.isEmpty()){
					if(checkListContainSection(sectionName)){
						for (Map.Entry<String, ArrayList<String>> entry : commentMap.entrySet()) {
							String keyName = entry.getKey();
							if(keyName.equals(sectionName)){
								if(fileType.equals("EXCEL")){
									if(!comment.contains("originalName")){
										comment=modifyComment(comment, cell);
									}
								}
								ArrayList<String> updateList=entry.getValue();
								updateList.add(comment);
								commentMap.put(keyName, updateList);
							}
						}
					}else{
						sectionNameList.add(sectionName);
						typeMap.put(sectionName, sectionType);
						if(fileType.equals("EXCEL")){
							if(!comment.contains("originalName")){
								comment=modifyComment(comment, cell);
							}
						}
						ArrayList<String> list = new ArrayList<String>();
						list.add(comment);
						commentMap.put(sectionName, list);
					}
			}
			 else{
				sectionNameList.add(sectionName);
				if(sectionType != null){
					typeMap.put(sectionName, sectionType);
				}
				if(fileType.equals("EXCEL")){
					if(!comment.contains("originalName")){
						comment=modifyComment(comment, cell);
					}
				}
				ArrayList<String> list = new ArrayList<String>();
				list.add(comment);
				commentMap.put(sectionName, list);
			}
		}
		log.debug("End method name=\"generateMapFromComment\"");
	}
	
	
}
