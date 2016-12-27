package com.avery.Model;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;



public class SearchCell {
	static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
	public String SearchXL(String path, String keyword, String filetype, String Sheetname, String filename) {
		String content = keyword.trim();
		//String Sheetname1 = "dipanshu";
		path = path.trim();
		filetype = filetype.trim();
		Sheetname = Sheetname.trim();
		filename = filename.trim();
		String result=""; 
		//System.out.println("path "+path + filename);
		//System.out.println("keyword"+keyword);
		//System.out.println("filetype "+filetype);
		//System.out.println("Sheetname "+Sheetname);
		//System.out.println("filename "+filename);
		try{
			InputStream inp = new FileInputStream(path+filename);
			Workbook wb = WorkbookFactory.create(inp);
			//int num = wb.getNumberOfSheets();
			CellAddress[] res=new CellAddress[100];
			//for (int i = 0; i < num; i++) {
				///for xls files
				if(filetype.equals(".xls") ){
					HSSFSheet sheet = (HSSFSheet) wb.getSheet(Sheetname);
					res = SearchCell.findRow(sheet, content);
				}else{
				///for xlsx files
					XSSFSheet sheet= (XSSFSheet)wb.getSheet(Sheetname);
					res = SearchCell.findRow(sheet, content);
				}
				
				for (int j = 0; j < res.length; j++) {
					if(res[j]!=null){
						// res[j];
						  result= res[j].toString();
						//System.out.println("CellAddress : "+res[j]);
					}
					
				}

			//}
		} catch (Exception e) {
			System.out.println("Search Cell 1");
			log.error(e);
		}
		return result;
	}

	private static CellAddress[] findRow(HSSFSheet sheet, String cellContent) {
		int i = 0;
		//String array[] = new String[50];
		CellAddress celladd[]=new CellAddress[50];
		try{
			//System.out.println("filename11111111111 ");
		
		for (Row row : sheet) {
			//System.out.println("filename111111111110000 ");
			for (Cell cell : row) {
				//System.out.println("filename111111111110000 87");
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					//System.out.println("filename111111111110000 2323232");
					if (cell.getRichStringCellValue().getString().trim()
							.equals(cellContent)) {
						//System.out.println("filename11111111111 2222222222222");
						/*int colIdx = cell.getColumnIndex();
						int rowIdx = cell.getRowIndex();*/
						CellAddress add=new CellAddress(cell);
					//	System.out.println("filename11111111111 2222222222222 3333333333");
						//array[i++] = "ROW:"+rowIdx + " COl:" + colIdx+" CellAddress:"+add.toString() ;
						celladd[i++]=add;
					}
				}
			}
		}
		
		}catch (Exception e) {
			System.out.println("Search Cell 2");
			log.error(e);
		}
		return celladd;
	}
	private static CellAddress[] findRow(XSSFSheet sheet, String cellContent) {
		int i = 0;
		//String array[] = new String[50];
		CellAddress celladd[]=new CellAddress[50];
		try{
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim()
							.equals(cellContent)) {
						/*int colIdx = cell.getColumnIndex();
						int rowIdx = cell.getRowIndex();*/
						CellAddress add=new CellAddress(cell);
						//array[i++] = "ROW:"+rowIdx + " COl:" + colIdx+" CellAddress:"+add.toString() ;
						celladd[i++]=add;
					}
				}
			}
		}
		}catch (Exception e) {
			System.out.println("Search Cell 3");
			log.error(e);
		}
		return celladd;
	}

}
