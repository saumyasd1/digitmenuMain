package com.avery.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;

//import com.aspose.email.system.exceptions.IOException;



public class SearchCell {
	//static Logger log = Logger.getLogger(OrderEmailQueueModel.class.getName());
	public String SearchXL(String path, String keyword, String filetype, String Sheetname, String filename) {
		String content = keyword.trim();
		path = path.trim();
		filetype = filetype.trim();
		Sheetname = Sheetname.trim();
		filename = filename.trim();
		String res=""; 
		String result=""; 
		
		
			try {
				InputStream inp;
					inp = new FileInputStream(path+filename);
				Workbook wb = WorkbookFactory.create(inp);
				int num = wb.getNumberOfSheets();
				if(checkSheetNameExist(wb, Sheetname)){
				//CellAddress[] res=new CellAddress[100];
				//System.out.println("Search Cell ");
				for (int i = 0; i < num; i++) {
					//System.out.println("Search Cell22 ");
					///for xls files
					if(filetype.equals(".xls") ){
						HSSFSheet sheet = (HSSFSheet) wb.getSheet(Sheetname);
						if(checkSheetNameExist(wb, Sheetname)){
							res = this.findRow(sheet, content);
						}else{
							System.out.println("sheet not found ");
						}
					}else{
					///for xlsx files
						XSSFSheet sheet= (XSSFSheet)wb.getSheet(Sheetname);
						if(checkSheetNameExist(wb, Sheetname)){
							res = this.findRow(sheet, content);
						}else{
							System.out.println("sheet not found ");
						}
					}
					

				}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		return res;
	}

	private  String findRow(HSSFSheet sheet, String cellContent) {
		int i = 0;
		int add_row=0;
		int add_column=0;
		String address= "";
		//String array[] = new String[50];
		//CellAddress celladd[]=new CellAddress[50];
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
						add_row =cell.getRowIndex();
						add_column =cell.getColumnIndex();
						//CellAddress add=new CellAddress(cell);
					//	System.out.println("filename11111111111 2222222222222 3333333333");
						//array[i++] = "ROW:"+rowIdx + " COl:" + colIdx+" CellAddress:"+add.toString() ;
						//celladd[i++]=add;
						address="Row"+add_row+"-column"+add_column;
						System.out.println("Search Cell 324"+address);
					}
				}
			}
		}
		
		}catch(NullPointerException en)
        {
			en.printStackTrace();
           // System.out.print("NullPointerException caught sheet  not found");
        }catch (Exception e) {
        	e.printStackTrace();
			//System.out.println("Search Cell 2");
			//log.error(e);
		}
		return address;
	}
	private String findRow(XSSFSheet sheet, String cellContent) {
		int i = 0;
		int add_row=0;
		int add_column=0;
		String address= "";
		//String array[] = new String[50];
		//CellAddress celladd[]=new CellAddress[50];
		//System.out.println("Search Cell 731");
		try{
			
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim()
							
							.equals(cellContent)) {
					//	System.out.println("Search Cell 323");
						/*int colIdx = cell.getColumnIndex();
						int rowIdx = cell.getRowIndex();*/
						//CellAddress add=new CellAddress(cell);
						add_row =cell.getRowIndex();
						add_column =cell.getColumnIndex();
					//	System.out.println("Search Cell 324");
						//array[i++] = "ROW:"+rowIdx + " COl:" + colIdx+" CellAddress:"+add.toString() ;
						//celladd[i++]=add;
						address="Row"+add_row+"-column"+add_column;
						System.out.println("Search Cell 324"+address);
					}
				}
			}
		}
		} catch(NullPointerException e)
        {
			e.printStackTrace();
            //System.out.print("NullPointerException caught sheet  not found");
        }catch (Exception e) {
        	e.printStackTrace();
			//System.out.println("Search Cell 3");
			//log.error(e);
		}
		return address;
	}
	
	
	public boolean checkSheetNameExist(Workbook workbook, String sheetName)
			{
		int noOfSheets = workbook.getNumberOfSheets();
		boolean isSheetExist = false;
		try {
			for (int i = 0; i < noOfSheets; i++) {
				if ((workbook.getSheetName(i)).equalsIgnoreCase(sheetName)) {
					isSheetExist = true;
					break;
				}
			}
			/*if (!isSheetExist) {
				if (workbook.getSheetAt(0) != null) {
					//log.error("Sheet name:\"" + sheetName
							//+ "\" defined in schema not found in Workbook.");
					//throw new ServiceException("Sheet name:\"" + sheetName
						//	+ "\" defined in schema not found in Workbook.");
				}

			}*/
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//log.error("sheet check."+e.getMessage(),e);
		}
		return isSheetExist;

	}
}
