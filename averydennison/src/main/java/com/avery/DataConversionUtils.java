package com.avery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import com.aspose.cells.HTMLLoadOptions;
import com.aspose.cells.LoadFormat;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;

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
			Object objectContent) {

		// write the html file at specified location
		FileWriter fileWriter = null;
		String msgContent = "";
		try {
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

			File file = new File(location + File.separatorChar + fileName + ".html");
			fileWriter = new FileWriter(file);
			fileWriter.write(msgContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
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
			book = new Workbook(htmlFileLocation + File.separatorChar + htmlFileName+".html", options); 
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
			book.save(excelFileLocation + File.separatorChar+excelFileName+"." + fileExtensionName,
					saveFormat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
