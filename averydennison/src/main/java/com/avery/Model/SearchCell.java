package com.avery.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


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
	// static Logger log =
	// Logger.getLogger(OrderEmailQueueModel.class.getName());
	public String SearchXL(String path, String keyword, String filetype,
			String Sheetname, String filename) {
		String content = keyword.trim();
		path = path.trim();
		filetype = filetype.trim();
		Sheetname = Sheetname.trim();
		filename = filename.trim();
		String res = "";
		String result = "";

		try {
			InputStream inp;
			inp = new FileInputStream(path + "/" + filename);
			Workbook wb = WorkbookFactory.create(inp);
			int num = wb.getNumberOfSheets();
			if (checkSheetNameExist(wb, Sheetname)) {
				// CellAddress[] res=new CellAddress[100];
				// System.out.println("Search Cell ");
				for (int i = 0; i < num; i++) {
					// System.out.println("Search Cell22 ");
					// /for xls files
					if (filetype.equals(".xls")) {
						HSSFSheet sheet = (HSSFSheet) wb.getSheet(Sheetname);
						if (checkSheetNameExist(wb, Sheetname)) {
							res = this.findRow(sheet, content);
						} else {
							//System.out.println("sheet not found ");
						}
					} else {
						// /for xlsx files
						XSSFSheet sheet = (XSSFSheet) wb.getSheet(Sheetname);
						if (checkSheetNameExist(wb, Sheetname)) {
							res = this.findRow(sheet, content);
						} else {
							//System.out.println("sheet not found ");
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

	private String findRow(HSSFSheet sheet, String cellContent) {
		int i = 0;
		int add_row = 0;
		int add_column = 0;
		String address = "";
		// String array[] = new String[50];
		// CellAddress celladd[]=new CellAddress[50];
		try {

			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim()
								.equals(cellContent)) {
							add_row = cell.getRowIndex();
							add_column = cell.getColumnIndex();
							address = "Row" + add_row + "-column" + add_column;
							//System.out.println("Search Cell 324" + address);
						}
					}
				}
			}

		} catch (NullPointerException en) {
			en.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	private String findRow(XSSFSheet sheet, String cellContent) {
		int i = 0;
		int add_row = 0;
		int add_column = 0;
		String address = "";
		try {

			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim()

						.equals(cellContent)) {
							add_row = cell.getRowIndex();
							add_column = cell.getColumnIndex();
							address = "Row" + add_row + "-column" + add_column;
						//	System.out.println("Search Cell 324" + address);
						}
					}
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	public boolean checkSheetNameExist(Workbook workbook, String sheetName) {
		int noOfSheets = workbook.getNumberOfSheets();
		boolean isSheetExist = false;
		try {
			for (int i = 0; i < noOfSheets; i++) {
				if ((workbook.getSheetName(i)).equalsIgnoreCase(sheetName)) {
					isSheetExist = true;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return isSheetExist;

	}
}
