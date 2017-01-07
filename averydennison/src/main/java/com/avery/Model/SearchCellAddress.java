package com.avery.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;

//import com.aspose.email.system.exceptions.IOException;



public class SearchCellAddress {
	public String SearchXL(String path, String keyword, String filetype, String cell, String filename) {
		String content = keyword.trim();
		path = path.trim();
		filetype = filetype.trim();
		String cell_address = cell.trim();
		keyword=keyword.trim();
		/*System.out.println("path id33cc " + path);
		System.out.println("keyword id33cc " + keyword);
		System.out.println("filetype id33cc " + filetype);
		System.out.println("cell id33cc " + cell);
		System.out.println("filename id33cc " + filename);*/
		//System.out.println("keyword id33cc " + keyword);
		//System.out.println("filename id33cc " + path+filename);
		filename = filename.trim();
		String res=""; 
		String result=""; 
		try {
			InputStream inp;
			inp = new FileInputStream(path+"/"+filename);
			Workbook wb = WorkbookFactory.create(inp);
			int num = wb.getNumberOfSheets();
			for (int i = 0; i < num; i++) {
				
				if(filetype.equals(".xls") ){
					HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(i);
					res = this.findRow(sheet, content, cell);
					if(!res.isEmpty()){
						return res;
					}
					//System.out.println("partner id33cc112 " + res);
				}else{
				///for xlsx files
				//	System.out.println("partner id33cc1122 " );
					XSSFSheet sheet= (XSSFSheet)wb.getSheetAt(i);
					res = this.findRow(sheet, content, cell_address);
					if(!res.isEmpty()){
						return res;
					}
					//System.out.println("partner id33cc11 " + res);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private String findRow(HSSFSheet sheet, String cellContent, String cell_address) {
		int i = 0;
		int add_row=0;
		int add_column=0;
		cell_address=cell_address.trim();
		String address= "";
		//String array[] = new String[50];
		//CellAddress celladd[]=new CellAddress[50];
		//System.out.println("sheet name- "+sheet.getSheetName());
		try {
			for (Row row : sheet) {
				
				for (Cell cell : row) {
					
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						//System.out.println("Search Cell 324---");
						if (cell.getRichStringCellValue().getString().trim()
								.equals(cellContent)) {
							//System.out.println("Search Cell 324---++");
//						String aadd_row=cell.getRow().;
//						add_column =cell.getColumnIndex();
							
							CellRangeAddress cra = new  CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex());
							
							if(cell_address.equals(cra.formatAsString())){
								//System.out.println("CellRangeAddress::"+cra.formatAsString());
								address=cra.formatAsString();
							}
							
							//System.out.println("CellRangeAddress::"+cra.formatAsString());
							
							//address=String.valueOf(add_row)+String.valueOf(add_column);
							//address=add_row.toString(add_row)+add_column.toString(add_column);
							//System.out.println("address Cell 324"+address);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
	private String findRow(XSSFSheet sheet, String cellContent, String cell_address) {
		int i = 0;
		int add_row=0;
		int add_column=0;
		String address= "";
		//cellContent= "Under Armour- Woven Size Label Order Form";
		//String array[] = new String[50];
		//CellAddress celladd[]=new CellAddress[50];
		//System.out.println("Search Cell 731");
		try{
			//System.out.println("add_row Cell 324"+sheet.getSheetName());
			//System.out.println("cellContent 324"+cellContent);
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if(cell.getRichStringCellValue().getString().contains(cellContent)){
						//System.out.println("cellContent yes+++++");
					}
					if (cell.getRichStringCellValue().getString().trim()
							
							.equals(cellContent)) {
						//System.out.println("CellRangeAddress======");
						CellRangeAddress cra = new  CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex());
						
						if(cell_address.equals(cra.formatAsString())){
							//System.out.println("CellRangeAddress::"+cra.formatAsString());
							address=cra.formatAsString();
							//System.out.println("address   "+address);
						}
						//address=add_row.toString(add_row)+add_column.toString(add_column);
						
					}
				}
			}
		}
		} catch(NullPointerException e)
        {
			e.printStackTrace();
           // System.out.print("NullPointerException caught sheet  not found");
        }catch (Exception e) {
        	e.printStackTrace();
			//System.out.println("Search Cell 3");
			//log.error(e);
		}
		return address;
	}
}
