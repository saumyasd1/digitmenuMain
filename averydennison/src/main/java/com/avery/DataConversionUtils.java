package com.avery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Store;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
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
import com.avery.Model.OrderEmailQueueModel;
import com.avery.Model.SearchCellAddress;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.mail.util.BASE64DecoderStream;

public class DataConversionUtils {

	static Logger log = Logger.getLogger(DataConversionUtils.class.getName());
	Folder inbox = null;
	Store store = null;
	boolean exportImages = false;
	
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
		log.debug("generating pdf file from html");
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
			log.debug("generating pdf file from html finished");
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
		log.debug("generating html file : \""+fileName+"\".");
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
			if (exportImages) {
				exportImageFromEmail(message, location, "images");
			}
			msgContent = msgContent.trim();
			if(msgContent.startsWith("<div")){
				msgContent = "<html>\n<br>\n"+msgContent+"\n</html>";  
			}
			 fos = new FileOutputStream(location
					+ File.separatorChar + fileName + ".html");
			out = new OutputStreamWriter(fos, "UTF-8");
			out.write(msgContent);
			out.close();
			fos.close(); 
			if (exportImages) {
				replaceImageName(location, fileName + ".html", "images");
			}
			log.debug("generating html file finished : \""+fileName+"\".");
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
		log.debug("generate excel file  with aspose lic.");
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
			log.debug("generate excel file  finished.");
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
		log.debug("get message from email body.");
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
		
		String contentFinal = "";	    
		Multipart mp1 = (Multipart)part.getContent();
		int count = mp1.getCount(); 
		
		for (int i = 0; i < count; i++) {
			if (!mp1.getBodyPart(i).getContentType().toLowerCase()
					.contains("image")) {
				
				if(mp1.getBodyPart(i).getContent() instanceof Multipart){
				
					Multipart mp2 = (Multipart) mp1.getBodyPart(i).getContent();
					int count2 = mp2.getCount();
					for (int ii = 0; ii < count2; ii++) {
						if (mp2.getBodyPart(ii).getContentType().toLowerCase()
								.contains("text/html")) {
							exportImages = true;
							contentFinal = mp2.getBodyPart(ii).getContent()
									.toString();
						}
					}
				}
			}
		} 
		
		if (contentType == null) {
			throw new Exception("Content Type : Invalid Content Type\n");
		}
//		return IOUtils.toString(part.getInputStream(), systemencoding);
		return contentFinal;	
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
		log.debug("process sheets of excel file with poi api.");
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
		log.debug("process sheets of excel file with poi api.");
	}
	
	
	/**
	 * Method to export image from email
	 * 
	 * @param msg
	 * @param htmlFileLocation
	 * @param imageFolderLocation
	 */
	public void exportImageFromEmail(Message msg, String htmlFileLocation,
			String imageFolderLocation) {
		try {
			Multipart mPart = (Multipart) msg.getContent();
			int count0 = mPart.getCount();
			File imageFolder = new File(htmlFileLocation + File.separator
					+ imageFolderLocation);
			if (!imageFolder.exists()) {
				if (imageFolder.mkdirs()) {
					log.debug("Image folder directory:\""
							+ htmlFileLocation + File.separator
							+ imageFolderLocation + "\" is created.");
				} else {
					log.debug("Failed to create image folder directory:\""
									+ htmlFileLocation
									+ File.separator
									+ imageFolderLocation + "\".");
					log.debug("Failed to create directory!");
				}
			}

			for (int i = 0; i < count0; i++) {

				Part part0 = (Part) mPart.getBodyPart(i);
				String contentType01 = part0.getContentType();
				exportImageFromEmailRecur(contentType01, part0,
						htmlFileLocation, imageFolder);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inbox != null && inbox.isOpen())
					inbox.close(true);

				if (store != null && store.isConnected())
					store.close();

			} catch (MessagingException me) {
				me.printStackTrace();
			}
		}
	}

	/**
	 * Recursive method to store all the images for mail
	 * 
	 * @param contentType
	 * @param part0
	 * @param htmlFileLocation
	 * @param imageFolder
	 */
	public void exportImageFromEmailRecur(String contentType, Part part0,
			String htmlFileLocation, File imageFolder) {
		InputStream input = null;
		FileOutputStream fos = null;
		try {
			if (contentType.toLowerCase().contains("image")) {
				String imageName = "";
				Enumeration eNum = part0.getAllHeaders();
				while ((eNum != null) && (eNum.hasMoreElements())) {
					Header header = (Header) eNum.nextElement();
					if (header.getName().toLowerCase().trim()
							.equals("content-id")) {
						imageName = header.getValue();
						if (imageName.contains(">")) {
							imageName = imageName.replace(">", "");
						}
						if (imageName.contains("<")) {
							imageName = imageName.replace("<", "");
						}
					}
				}
				fos = new FileOutputStream(imageFolder.getPath()
						+ File.separator + imageName);
				input = part0.getInputStream();
				byte[] buffer = new byte[4096];
				int byteRead;
				while ((byteRead = input.read(buffer)) != -1) {
					fos.write(buffer, 0, byteRead);
				}
			} else {
				if (part0.getContent() instanceof Multipart) {
					Multipart mPart0 = (Multipart) part0.getContent();
					int count1 = mPart0.getCount();
					for (int p = 0; p < count1; p++) {
						Part part1 = (Part) mPart0.getBodyPart(p);
						String contentType1 = part1.getContentType();
						exportImageFromEmailRecur(contentType1, part1,
								htmlFileLocation, imageFolder);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// Ignore
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// Ignore
				}
			}
		}
	}

	/**
	 * Replace image name in html file
	 * 
	 * @param htmlFileLocation
	 * @param htmlFileName
	 * @param imageFolderLocation
	 */
	public void replaceImageName(String htmlFileLocation, String htmlFileName,
			String imageFolderLocation) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		try {
			String fileNameArray[] = null;
			File imageFolder = new File(htmlFileLocation + File.separator
					+ imageFolderLocation);
			if (imageFolder.exists()) {
				fileNameArray = imageFolder.list();
			}
			if (fileNameArray.length < 0) {
				return;
			}
			File htmlFile = new File(htmlFileLocation + File.separator
					+ htmlFileName);
			fileReader = new FileReader(htmlFile);
			bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			String fileContent = stringBuffer.toString();
			for (String name : fileNameArray) {
				if (fileContent.contains(name)) {
					String newContent = "." + File.separator
							+ imageFolderLocation + File.separator + name;
					String searchContent = "cid:" + name;
					String tempSubstring = fileContent.substring(fileContent
							.indexOf(searchContent));
					String oldContent = tempSubstring.substring(0,
							tempSubstring.indexOf("\""));
					fileContent = fileContent.replace(oldContent, newContent);
				}
			}
			fileWriter = new FileWriter(htmlFile);
			bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.write(fileContent);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferWriter != null) {
				try {
					bufferWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}
	}
}
