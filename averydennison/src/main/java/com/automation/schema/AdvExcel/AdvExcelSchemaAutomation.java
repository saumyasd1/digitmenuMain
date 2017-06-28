package com.automation.schema.AdvExcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.ListIterator;
import java.util.Map;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.adeptia.indigo.logging.Logger;
import com.adeptia.indigo.services.ServiceException;
import com.automation.Utility.SchemaAutomation;


/**
 * @author Rakesh
 * Java Class to generate schema XSD based excel comments
 *
 */
public class AdvExcelSchemaAutomation extends SchemaAutomation{
	public final static String fileType="EXCEL";
	

	public static  String sheetName = null;
	public static Workbook workbook=null;
	
	
	
	/**
	 * Method to get Workbook object from file path 
	 * @param file i.e. file along with it's path
	 * @return Workbook Object
	 * @throws InvalidFormatException 
	 * @author Rakesh
	 */
	public Workbook getWorkbook(String file) throws ServiceException, InvalidFormatException {
		log.debug("Start method name=\"getWorkbook\"");
		try {
			workbook = WorkbookFactory.create(new File(file));
		}catch(IllegalStateException e){
			e.printStackTrace();
			throw new ServiceException(
					"Error while creating workbook object for file:\"" + file
							+ "\"." + e.getMessage(), e);
		}catch (IllegalFormatException e) {
			e.printStackTrace();
			throw new ServiceException(
					"Error while creating workbook object for file:\"" + file
							+ "\"." + e.getMessage(), e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(
					"Error while creating workbook object for file:\"" + file
							+ "\"." + e.getMessage(), e);
		}
		log.debug("End method name=\"getWorkbook\"");
		return workbook;
	}

	/**
	 * Method to get Cell from CellReference 
	 * @param cellReference
	 * @param sheet
	 * @return Cell
	 * @author Rakesh
	 */
	public Cell getCellFromCellReference(CellReference cellReference,
			Sheet sheet) {

		log.debug("Start method name=\"getCellFromCellReference\"");
		if (cellReference == null) {
			log.error("CellReference is null !");
			return null;
		} else if (sheet == null) {
			log.error("Sheet is null !");
			return null;
		} else {
			Cell cell = null;
			Row row = sheet.getRow(cellReference.getRow());
			if (row != null) {
				cell = row.getCell(cellReference.getCol());
			}
			log.debug("End method name=\"getCellFromCellReference\"");
			return cell;
		}
	}
	
	/**
	 * Method to get comment from cell 
	 * @param cell
	 * @return String
	 * @author Rakesh
	 */
	public String getComment(Cell cell) {
		log.debug("Start method name=\"getComment\"");
		log.debug("End method name=\"getComment\"");
		return cell.getCellComment().getString().toString().trim();
	}
	
	/**
	 * Method to generate CommentMap and typeMap
	 * @param file
	 * @param sheetName
	 * @author Rakesh
	 */
	public void readComment() throws ServiceException{
		log.debug("Start method name=\"readComment\"");
		if (sheetName == null || sheetName.trim().isEmpty()) {
			log.error("Error while reading SchemaDefinationSheetName");
			throw new ServiceException("Error while reading SchemaDefinationSheetName");
		}else {
		Sheet sheet=workbook.getSheet(sheetName);
		for (int j = 0; j <= sheet.getLastRowNum(); j++) {
			Row rowValue = sheet.getRow(j);
			if (rowValue != null) {
				for (int k = 0; k <= rowValue.getLastCellNum(); k++) {
					Cell cell = rowValue.getCell(k);
					if (cell != null) {
						if (cell.getCellComment() != null) {
							String comment=getComment(cell);
							if(verifyComment(comment, fileType)){
								generateMapFromComment(comment, fileType, cell);
							}else{
								log.error("Comment at cellRefernce :\""+new CellReference(cell.getRowIndex(), cell.getColumnIndex())+"\" isn't valid.");
							}
						}
						//reading cell comment end
					}
				}//end of for loop for column count
			}
		}//end of for loop for row count
		}
		log.debug("End method name=\"readComment\"");
	}
	
	
	/**
	 * Method to generate XSD from GlobalProp Sheet
	 * @param workbook
	 * @param sheetName
	 * @author Rakesh
	 */
	public void getSchemaDefiniation() throws ServiceException{

		log.debug("Start method name=\"getSchemaDefiniation\"");
			int noOfSheets = workbook.getNumberOfSheets();
			for (int i = 0; i < noOfSheets; i++) {
				boolean isSheetHidden = workbook.isSheetHidden(i);
				if (!isSheetHidden) {
					Sheet sheet = workbook.getSheetAt(i);
					String name = sheet.getSheetName();
					if (name != null && name.equals(globalSheetName)) {
						CellReference cellReference = getCellPositionFromSchemaDefinition(sheet);
						if (cellReference == null) {
							log.error("Schema Definition Sheet doesn't have any comment.");
						} else {
							Cell cell = getCellFromCellReference(cellReference, sheet);
							if (cell == null || cell.getCellComment() == null) {
								log.error("Cell Position doesn't have any comment.");
							} else {
								String comment = getComment(cell);
								if (comment != null && verifyComment(comment,fileType)) {
									generateSchemaDefinitionXSD(comment);
								}else{
									log.error("Comment at cellRefernce :\""+cellReference+"\" isn't valid.");
								}
							}
						}
					}
				}
			}
			log.debug("End method name=\"getSchemaDefiniation\"");
	}

	/**
	 * Method to get Set of CellRefence having comment in GlobalProp Sheet
	 * @param sheet
	 * @return LinkedHashSet<CellReference>
	 * @author Rakesh
	 */
	public CellReference getCellPositionFromSchemaDefinition(Sheet sheet) {

		log.debug("Start method name=\"getCellPositionFromSchemaDefinition\"");
			for (int j = 0; j <= sheet.getLastRowNum(); j++) {
				Row rowValue = sheet.getRow(j);
				if (rowValue != null) {
					for (int k = 0; k <= rowValue.getLastCellNum(); k++) {
						Cell cell = rowValue.getCell(k);
						if (cell != null) {
							if (cell.getCellComment() != null) {
								return new CellReference(cell.getRowIndex(),
										cell.getColumnIndex());
							}
						}
					}
				}
			}
			log.debug("End method name=\"getCellPositionFromSchemaDefinition\"");
			return null;
	}

	/**
	 * Method to generate XSD from Schema Definition Sheet comment
	 * @param comment
	 * @author Rakesh
	 */
	public void generateSchemaDefinitionXSD(String comment) {

		log.debug("Start method name=\"generateSchemaDefinitionXSD\"");
			sb.append(commonSchemaDefinition);
			if (comment.contains("Sheets")) {
				sb.append("<Sheets><![CDATA["
						+ getValueFromComment(comment, "Sheets")
						+ "]]></Sheets>\n");
			}
			if (comment.contains("allowDiscontinousOrder")) {
				boolean allowDiscontinousOrder = Boolean.parseBoolean(getValueFromComment(comment,"allowDiscontinousOrder"));
				if (allowDiscontinousOrder) {
					sb.append("<allowDiscontinousOrder><![CDATA["
							+ allowDiscontinousOrder
							+ "]]></allowDiscontinousOrder>\n");
				}else {
					sb.append("<allowDiscontinousOrder><![CDATA[false]]></allowDiscontinousOrder>\n");
				}
			} else {
				sb.append("<allowDiscontinousOrder><![CDATA[true]]></allowDiscontinousOrder>\n");
			}

			if (comment.contains("allowLessFields")) {
				boolean allowLessFields = Boolean.parseBoolean(getValueFromComment(comment,"allowLessFields"));
				if (allowLessFields) {
					sb.append("<allowLessFields><![CDATA[" + allowLessFields
							+ "]]></allowLessFields>\n");
				}else {
					sb.append("<allowLessFields><![CDATA[false]]></allowLessFields>\n");
				}
			} else {
				sb.append("<allowLessFields><![CDATA[true]]></allowLessFields>\n");
			}

			if (comment.contains("processAllSheets")) {
				boolean processAllSheets = Boolean.parseBoolean(getValueFromComment(comment,"processAllSheets"));
				if (processAllSheets) {
					sb.append("<processAllSheets><![CDATA[" + processAllSheets
							+ "]]></processAllSheets>\n");
				}else {
					sb.append("<processAllSheets><![CDATA[false]]></processAllSheets>\n");
				}
			} else {
				sb.append("<processAllSheets><![CDATA[true]]></processAllSheets>\n");
			}

			if (comment.contains("trimSpacesToReadFieldName")) {
				boolean trimSpacesToReadFieldName = Boolean.parseBoolean(getValueFromComment(comment,"trimSpacesToReadFieldName"));
				if (trimSpacesToReadFieldName) {
					sb.append("<trimSpacesToReadFieldName><![CDATA["
							+ trimSpacesToReadFieldName
							+ "]]></trimSpacesToReadFieldName>\n");
				}else {
					sb.append("<trimSpacesToReadFieldName><![CDATA[false]]></trimSpacesToReadFieldName>\n");
				}
			} else {
				sb.append("<trimSpacesToReadFieldName><![CDATA[true]]></trimSpacesToReadFieldName>\n");
			}

			if (comment.contains("allowIgnoreCase")) {
				boolean allowIgnoreCase = Boolean.parseBoolean(getValueFromComment(comment,"allowIgnoreCase"));
				if (allowIgnoreCase) {
					sb.append("<allowIgnoreCase><![CDATA[" + allowIgnoreCase
							+ "]]></allowIgnoreCase>\n");
				} else {
					sb.append("<allowIgnoreCase><![CDATA[false]]></allowIgnoreCase>\n");
				}
			} else {
				sb.append("<allowIgnoreCase><![CDATA[true]]></allowIgnoreCase>\n");
			}

			if (comment.contains("isRegexInSheet")) {
				boolean isRegexInSheet = Boolean.parseBoolean(getValueFromComment(comment,"isRegexInSheet"));
				if (isRegexInSheet) {
					sb.append("<isRegexInSheet><![CDATA[" + isRegexInSheet
							+ "]]></isRegexInSheet>\n");
				}else {
					sb.append("<isRegexInSheet><![CDATA[false]]></isRegexInSheet>\n");
				}
			} else {
				sb.append("<isRegexInSheet><![CDATA[false]]></isRegexInSheet>\n");
			}
			
			if (comment.contains("SchemaDefinitionSheetName")) {
				sheetName = getValueFromComment(comment,"SchemaDefinitionSheetName");
			}
			log.info("Comment =\"" + comment + "\"");
			log.debug("End method name=\"generateSchemaDefinitionXSD\"");
	}
	
	
	
	/**
	 * Method to generate XSD for Control Data
	 * @param sectionType
	 * @param sectionName
	 * @param commentList
	 * @author Rakesh
	 */
	public void generateControlDataXSD(String sectionType, String sectionName ,
			 ArrayList<String> commentList) {

		log.debug("Start method name=\"generateControlDataXSD\"");
		if (commentList == null) {
			log.error("HashMap is null !");
		} else {
				ListIterator<String> iterator = commentList.listIterator();
				String firstComment = iterator.next();
					sb.append("<xs:element maxOccurs=\"unbounded\"  minOccurs=\"1\" name=\"" + sectionName.trim() + "\">\n");
					sb.append(APPINFOHeader);
					sb.append("<sectionType><![CDATA[" + sectionType + "]]></sectionType>\n");
					generateSectionXSD(firstComment, fileType);
					sb.append(APPINFOFooter+ComplexHeader+SequenceHeader);
				iterator.previous();
				while (iterator.hasNext()) {
					String comment =iterator.next();
						generateElementXSD(comment, fileType);
				}
					sb.append(SequenceFooter+SectionElementAttribute+ComplexFooter+ElementFooter);
		}
		log.debug("End method name=\"generateControlDataXSD\"");
	}
	
	
	
	/**
	 * Method to XSD for excel file
	 * @param file i.e. file along with it's path
	 * @author Rakesh
	 */
	public void generateSchemaXSD(String file,Logger _log) throws ServiceException {
		log=_log;
		log.debug("Start method name=\"generateSchemaXSD\"");
		if(file == null || file.trim().isEmpty()){
			log.error("File :\""+file+"\" isn't valid.");
			throw new ServiceException("File :\""+file+"\" isn't valid.");
		} else {
			try {
				
				workbook = getWorkbook(file);
				sb = new StringBuffer();
				sb.append(XSDHeader + RootElementHeader + APPINFOHeader);
				getSchemaDefiniation();
				sb.append(APPINFOFooter + ComplexHeader + SequenceHeader
						+ SheetElementHeader + ComplexHeader + SequenceHeader);
				readComment();
				for (Map.Entry<String, String> typeEntry : super.typeMap.entrySet()) {
					String type = typeEntry.getValue();
					String name = typeEntry.getKey();
					for (Map.Entry<String, ArrayList<String>> commentEntry : super.commentMap
							.entrySet()) {
						String section_Name = commentEntry.getKey();
						if(type != null){
							if (type.equals("keyvalues")) {
								if (name.equals(section_Name)) {
									generateKeyValuesXSD(type, section_Name,
											super.commentMap.get(section_Name), fileType);
								}
							}
							if (type.equals("controlData")) {
								if (name.equals(section_Name)) {
									generateControlDataXSD(type, section_Name,
											super.commentMap.get(section_Name));
								}
							}
							if (type.equals("table")) {
								if (name.equals(section_Name)) {
									generateTableXSD(type, section_Name,
											super.commentMap.get(section_Name), fileType);
								}
							}
						}
					
					}
				}
				sb.append(SequenceFooter + SheetElementAttribute
						+ ComplexFooter + ElementFooter + SequenceFooter
						+ ComplexFooter + ElementFooter + XSDFooter);
				generateXSDFromStringBuffer(file, sb);
				log.debug("End method name=\"generateSchemaXSD\"");
			} catch (Exception e) {
				log.error("Error while generating XSD." + e.getMessage(), e);
				throw new ServiceException("Error while generating XSD."
						+ e.getMessage(), e);

			}
		}
	}

	/*public static void main(String[] args) throws IndigoException, ServiceException {
		//String file = "D:\\SchemaAutomationTest\\KumarAnjan\\GAPissue7_commentFile.xlsx";
		//String file = "D:\\SchemaAutomationTest\\Anubhav\\test\\new gap.xls";
		String file = "D:\\SchemaAutomationTest\\Phase2B\\JIRA_ID_AOC_529_WI_13\\UA Logo Order form for AOC(update on 2017-4-6).xls";
		//log.debug("In console");
		new AdvExcelSchemaAutomation().generateSchemaXSD(file);
		System.out.println("End of XSD creations..");
	}*/


}
