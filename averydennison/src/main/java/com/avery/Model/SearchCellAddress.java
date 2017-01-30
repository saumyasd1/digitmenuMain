package com.avery.Model;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;   
import org.apache.poi.xssf.usermodel.XSSFCell;  
import org.apache.poi.xssf.usermodel.XSSFRow;  
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SearchCellAddress {

	
	
	private Workbook workbook;
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
	/**
	 * Method to get WorkBook object
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public Workbook getWorkbook(String filePath, String fileName)throws Exception {
		try {
			File file = new File(filePath + File.separatorChar + fileName);
			System.out.println("Create workbook object for file" +filePath + File.separatorChar + fileName+".");
			workbook = WorkbookFactory.create(file); 
		} catch (IOException e) {
			throw e;
		} catch (InvalidFormatException e) {
			throw e;
		}
		return workbook;
	}

	
	/**
	 * Method to search content in all Sheet
	 * @param filePath
	 * @param fileName
	 * @param matchingString
	 * @param cellPostion
	 * @param isCaseSensitive
	 * @return
	 */
	public boolean searchContentInAllSheet(String filePath, String fileName, String matchingString,
			String cellPostion)throws Exception {
		
		Workbook workbook = getWorkbook(filePath, fileName);
		boolean resultFlag=false;
		int noOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < noOfSheets; i++) {
			boolean isSheetHidden = workbook.isSheetHidden(i);
			if (!isSheetHidden) {
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println("searching for string \""+matchingString+"\" in sheet \""+sheet+"\".");
				//System.out.println(sheet.getSheetName() + "****Start");
				resultFlag= isContentPresent(sheet, cellPostion,
						matchingString);
				System.out.println("searching for string finished in sheet \""+sheet+"\".");
				System.out.println("resultFlag for string is \""+resultFlag+"\".");
				
			}
		}
		return resultFlag;
	}

	/**
	 * Method is Using content match at particular cell position
	 * 
	 * @param sheet
	 * @param cellPostion
	 * @param content
	 * @return boolean
	 */
	public boolean isContentPresent(Sheet sheet, String cellPostion,
			String content) {
		//System.out.println("content"+ content);
		CellReference cellReference = new CellReference(cellPostion);
		Row row = sheet.getRow(cellReference.getRow());
		if(row!=null){
			Cell cell = row.getCell(cellReference.getCol());
			if(cell!=null){
				DataFormatter dataFormatter = new DataFormatter();
				if(getCellValue(cell, dataFormatter)!=null){
					if(getCellValue(cell, dataFormatter).toUpperCase().trim().contains(content.toUpperCase())){
						return true;	
					}
						
				}
			}	
		}
		return false;
	}

	/**
	 * Method to get cell value
	 * 
	 * @param currentCell
	 * @param dataFormatter
	 * @return
	 */
	public String getCellValue(Cell currentCell, DataFormatter dataFormatter) {
		String value = null;
		try {
			if ((currentCell).getCellType() != Cell.CELL_TYPE_FORMULA) {
				value = dataFormatter.formatCellValue(currentCell);
				if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC
						&& currentCell.getCellStyle().getDataFormatString()
								.equalsIgnoreCase("yyyy/m/d\\ AM/PM\\ hh:mm")) {
					String dateFormat = currentCell.getCellStyle()
							.getDataFormatString();
					value = new CellDateFormatter(dateFormat)
							.format(currentCell.getDateCellValue());
				}
			} else {
				FormulaEvaluator evaluator = workbook.getCreationHelper()
						.createFormulaEvaluator();
				CellValue cellValue = evaluator.evaluate(currentCell);
				switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					value = Boolean.toString(cellValue.getBooleanValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = Double.toString(cellValue.getNumberValue());
					if (currentCell.getCellStyle().getDataFormatString() != null
							&& currentCell.getCellStyle().getDataFormatString()
									.equals("\"$\"#,##0.00")) {
						value = CellFormat.getInstance(
								currentCell.getCellStyle()
										.getDataFormatString()).apply(
								currentCell).text;
					}
					if (HSSFDateUtil.isCellDateFormatted(currentCell))
						value = dataFormatter.formatCellValue(currentCell,
								evaluator);
					break;
				case Cell.CELL_TYPE_STRING:
					value = cellValue.getStringValue();
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_ERROR: {
					try {
						byte errorCode = currentCell.getErrorCellValue();
						FormulaError formulaError = FormulaError
								.forInt(errorCode);
						value = formulaError.getString();
					} catch (RuntimeException e) {
						throw e;
					}
				}
					break;
				}
			}
		} catch (Exception e2) {
			value = "";
		}
		return value;
	}
	
}
