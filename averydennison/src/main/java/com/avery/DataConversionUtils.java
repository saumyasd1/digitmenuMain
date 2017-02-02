package com.avery;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

import com.aspose.cells.HTMLLoadOptions;
import com.aspose.cells.LoadFormat;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.avery.Model.SearchCellAddress;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.mail.util.BASE64DecoderStream;

public class DataConversionUtils {

	public static void main(String[] args) {
		String inputFileLocation = "C:\\Users\\Vishal\\Desktop\\Avery\\Email to PDF and Excel\\";
		String htmlFileName = "Under Armour Order AAN#00022912.html";
		generatePDFfromHTML(inputFileLocation, htmlFileName);
	}

	/**
	 * Method to generate pdf file from html file
	 * 
	 * @param inputFileLocation
	 * @param htmlFileName
	 */
	public static void generatePDFfromHTML(String inputFileLocation,
			String htmlFileName) {
		String pdfFileName = null;
		Document doc = null;
		FileOutputStream fos = null;
		try {
			if (htmlFileName.endsWith(".html")) {
				pdfFileName = htmlFileName.replaceAll(".html", ".pdf");
			} else {
				pdfFileName = htmlFileName.replaceAll(".htm", ".pdf");
			}

			String htmlText = readFile(inputFileLocation + File.separatorChar
					+ htmlFileName, StandardCharsets.UTF_8);
			fos = new FileOutputStream(inputFileLocation + File.separatorChar
					+ pdfFileName);

			doc = new Document(PageSize.A4);
			PdfWriter.getInstance(doc, fos);
			doc.open();
			HTMLWorker hw = new HTMLWorker(doc);
			hw.parse(new StringReader(htmlText));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// ignore;
				}
			}
		}
	}

	/**
	 * Method to return file content as String
	 * 
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	/**
	 * @param location
	 * @param msgContent
	 * @param fileName
	 */
	public void generateHTMLFile(String location, String fileName,
			Message message) {

		// write the html file at specified location
		FileWriter fileWriter = null;
		String msgContent = "";
		Writer out = null;
		FileOutputStream fos = null;
		try {
			Object objectContent = message.getContent();
			if (objectContent instanceof Multipart) {
				Multipart mp = (Multipart) objectContent;
				for (int ii = 0; ii < mp.getCount(); ii++) {
					BodyPart bp = mp.getBodyPart(ii);
					if (Pattern
							.compile(Pattern.quote("text/html"),
									Pattern.CASE_INSENSITIVE)
							.matcher(bp.getContentType()).find()) {
						msgContent = msgContent + (String) bp.getContent();
					}
				}
			}

			if (msgContent.trim().equals("")) {
				msgContent = getBodyMessage(message, "text/HTML", "UTF-8");
			}
			msgContent = msgContent.trim();
			if(msgContent.startsWith("<div")){
				msgContent = "<html>\n"+msgContent+"\n</html>";  
			}
			 fos = new FileOutputStream(location
					+ File.separatorChar + fileName + ".html");
			out = new OutputStreamWriter(fos, "UTF-8");
			out.write(msgContent);
			out.close();
			fos.close(); 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.flush();
				} catch (IOException e) {
					// Ignore;
				}
				try {
					fileWriter.close();
				} catch (IOException e) {
					// Ignore;
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		}

	}

	/**
	 * @param htmlFileLocation
	 * @param htmlFileName
	 * @param excelFileLocation
	 * @param excelFileName
	 * @param fileExtensionName
	 */
	public void generateExcelFile(String htmlFileLocation, String htmlFileName,
			String excelFileLocation, String excelFileName,
			String fileExtensionName) {
		// Load the licence
		com.aspose.cells.License license = new com.aspose.cells.License();
		license.setLicense(this.getClass().getClassLoader()
				.getResourceAsStream("Aspose.Cells.lic"));

		// Read and create the instance of HTML file from specified location
		HTMLLoadOptions options = new HTMLLoadOptions(LoadFormat.HTML);
		Workbook book;
		try {
			book = new Workbook(htmlFileLocation + File.separatorChar
					+ htmlFileName + ".html", options);
			// To check if the excel file should be .xls or .xlsx

			int saveFormat = 0;
			if (fileExtensionName != null
					&& !fileExtensionName.trim().equals("")) {
				if (fileExtensionName.trim().equalsIgnoreCase("xlsx")) {
					saveFormat = SaveFormat.XLSX;

				} else if (fileExtensionName.trim().equalsIgnoreCase("xls")) {
					saveFormat = SaveFormat.EXCEL_97_TO_2003;
				}
			}
			// save the excel file
			book.save(excelFileLocation + File.separatorChar + excelFileName
					+ "." + fileExtensionName, saveFormat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to get body message as html
	 * 
	 * @param msg
	 * @param bodyContentType
	 * @param systemencoding
	 * @return
	 * @throws Exception
	 */
	private String getBodyMessage(Message msg, String bodyContentType,
			String systemencoding) throws Exception {
		Multipart mp = null;
		String encoding = null;
		Object content = msg.getContent();
		String type1 = msg.getContentType();
		if ((content instanceof Multipart)) {
			mp = (Multipart) msg.getContent();
		} else {
			if ((content instanceof BASE64DecoderStream)) {
				BASE64DecoderStream base64DecoderStream = (BASE64DecoderStream) msg
						.getContent();
				StringWriter writer = new StringWriter();
				IOUtils.copy(base64DecoderStream, writer);
				String base64decodedString = writer.toString();
				return IOUtils.toString(new ByteArrayInputStream(
						base64decodedString.getBytes()));
			}
			return IOUtils.toString(new ByteArrayInputStream(((String) content)
					.getBytes()));
		}

		String contentType = mp.getContentType();
		Part part = null;

		part = mp.getBodyPart(0);
		Enumeration e = part.getAllHeaders();
		while ((e != null) && (e.hasMoreElements())) {
			Header header = (Header) e.nextElement();
			if ((header.getName() == null) || (header.getValue() == null))
				continue;
			if ((!header.getName().toLowerCase().startsWith("content-type"))
					|| (!header.getValue().toLowerCase()
							.startsWith("multipart/alternative")))
				continue;
			Multipart mpp = (Multipart) part.getContent();
			Part part0 = null;
			if ((bodyContentType == null)
					|| (bodyContentType.equalsIgnoreCase("")))
				bodyContentType = "text/plain";
			if (bodyContentType.equalsIgnoreCase("text/plain"))
				part0 = mpp.getBodyPart(0);
			else if (bodyContentType.equalsIgnoreCase("text/html"))
				part0 = mpp.getBodyPart(1);
			else
				part0 = mpp.getBodyPart(0);
			String type = part0.getContentType();
			String charset = type.substring(type.lastIndexOf("=") + 1,
					type.length());
			if (charset.equalsIgnoreCase(systemencoding))
				encoding = systemencoding;
			else
				encoding = charset;
			if ((type != null)
					&& ((type.trim().toLowerCase().startsWith("text/html")) || (type
							.trim().toLowerCase().startsWith("text/plain")))) {
				return IOUtils.toString(part0.getInputStream(), encoding);
				// return part0.getInputStream();
			}

		}

		part = mp.getBodyPart(0);
		if (contentType == null) {
			throw new Exception("Content Type : Invalid Content Type\n");
		}
		return IOUtils.toString(part.getInputStream(), systemencoding);
		// return part.getInputStream();
	}
	
	
	/**
	 * Method to update cell value
	 * 
	 * @param excelFilePath
	 * @param excelFilename
	 * @param sheetName
	 * @param isProcessAllSheet
	 * @param cellPosition
	 * @param updateValue
	 */
	public void updateCellValue(String excelFilePath, String excelFilename,
			String sheetName, boolean isProcessAllSheet, String cellPosition,
			String updateValue) {
		FileInputStream file = null;
		FileOutputStream fos = null;
		try {
			String excelFileLocation = excelFilePath + File.separatorChar
					+ excelFilename;
			file = new FileInputStream(excelFileLocation);
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory
					.create(file);

			if (isProcessAllSheet) {
				processAllSheet(workbook, cellPosition, updateValue);
			} else {
				Sheet sheet = workbook.getSheet(sheetName);

				Cell cell = null;
				SearchCellAddress searchCellAddress = new SearchCellAddress();
				CellReference cellReference = new CellReference(cellPosition);
				Row row = sheet.getRow(cellReference.getRow());
				if (row != null) {
					cell = row.getCell(cellReference.getCol());
				}
				if (cell != null) {
					String value = searchCellAddress.getCellValue(cell,
							new DataFormatter());
					if (value == null || value.trim().equals("")) {
						cell.setCellValue(updateValue);
					}
				}
			}
			file.close();
			fos = new FileOutputStream(excelFileLocation);
			workbook.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					// ignore
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}

	/**
	 * Method to process all sheets
	 * 
	 * @param workbook
	 */
	public void processAllSheet(org.apache.poi.ss.usermodel.Workbook workbook,
			String cellPosition, String updateValue) {
		int noOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < noOfSheets; i++) {
			boolean isSheetHidden = workbook.isSheetHidden(i);
			if (!isSheetHidden) {
				String sheetName = workbook.getSheetName(i);
				Sheet sheet = workbook.getSheet(sheetName);
				Cell cell = null;
				SearchCellAddress searchCellAddress = new SearchCellAddress();
				CellReference cellReference = new CellReference(cellPosition);
				Row row = sheet.getRow(cellReference.getRow());
				if (row != null) {
					cell = row.getCell(cellReference.getCol());
				}
				if (cell != null) {
					String value = searchCellAddress.getCellValue(cell,
							new DataFormatter());
					if (value == null || value.trim().equals("")) {
						cell.setCellValue(updateValue);
					}
				}
			}
		}
	}
}
