package com.avery.bao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.aspose.email.MailMessage;
import com.aspose.email.SaveOptions;
import com.aspose.pdf.LoadFormat;
import com.aspose.words.Document;
import com.aspose.words.LoadOptions;
import com.aspose.words.SaveFormat;

public class MailBodyToPDF {
	
	public void convertMailToPDF(String dir) throws Exception{
		
		FileInputStream fin = new FileInputStream(dir + "/" + "CompleteEmail" + ".eml");
		MailMessage eml = MailMessage.load(fin);

		// Save the Message to output stream in MHTML format
		ByteArrayOutputStream emlStream = new ByteArrayOutputStream();
		eml.save(emlStream, SaveOptions.getDefaultMhtml());

		// Load the stream in Word document

		LoadOptions lo = new LoadOptions();
		lo.setLoadFormat(LoadFormat.MHT);
		Document doc = new Document(new ByteArrayInputStream(emlStream.toByteArray()), lo);

		// Save to disc
		doc.save(dir + "/" + "MailBody" + ".pdf", SaveFormat.PDF);

		// or Save to stream
		// ByteArrayOutputStream foStream = new ByteArrayOutputStream();
		// doc.save(foStream, SaveFormat.Pdf);
		
	}

}
