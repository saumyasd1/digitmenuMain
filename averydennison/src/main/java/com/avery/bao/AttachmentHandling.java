package com.avery.bao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.FilenameUtils;

import com.avery.EmailManager;
import com.avery.services.EmailAttachmentService;

public class AttachmentHandling {

	private static final int BUFFER_SIZE = 4096;

	public void extractAttachment(String dir, int emailqueueid, Message message)
			throws IOException, MessagingException {

		EmailAttachmentService emailAttachmentService = new EmailAttachmentService();
		String contentType = null;
		try {
			contentType = message.getContentType();
			if (contentType.contains("multipart")) {
				Multipart multiPart = (Multipart) message.getContent();
				int numberOfParts = multiPart.getCount();
				for (int partCount = 0; partCount < numberOfParts; partCount++) {
					MimeBodyPart part = (MimeBodyPart) multiPart
							.getBodyPart(partCount);
					if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
						// this part is attachment
						String attachmentFileName = MimeUtility.decodeText(part
								.getFileName());
						StringBuffer sb = new StringBuffer();
						checkFileName(dir,
								FilenameUtils.getBaseName(attachmentFileName),
								FilenameUtils.getExtension(attachmentFileName),
								0, sb);
						EmailManager.log.debug("Writing attachment file:\""
								+ sb.toString() + " at:\""
								+ EmailManager.getDate() + "\".");
						part.saveFile(dir + File.separatorChar + sb.toString());
						EmailManager.log.debug("Attachment file:\""
								+ sb.toString()
								+ " has been written successfully at:\""
								+ EmailManager.getDate() + "\".");
						EmailManager.log
								.debug("Inserting attachment details for the file:\""
										+ sb.toString()
										+ "\" in the table orderfileattachment having emailqueueid:\""
										+ emailqueueid
										+ "\" at:\""
										+ EmailManager.getDate() + "\".");
						emailAttachmentService.insertIntoEmailAttachment(part,
								dir, emailqueueid, sb.toString(),
								FilenameUtils.getExtension(sb.toString()));
						EmailManager.log
								.debug("Attachment details for the  file:\""
										+ sb.toString()
										+ "\" in the table orderfileattachment having emailqueueid:\""
										+ emailqueueid
										+ "\" has been inserted at:\""
										+ EmailManager.getDate() + "\".");
					}
				}
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MessagingException(
					"Error while reading attachement from email:" + message
							+ " for emailqueueid:" + emailqueueid + "."
							+ e.getMessage(), e);
		}
		EmailManager.log.debug("All Attachments Stored successfully.");
		EmailManager.log
				.debug("Sending acknowledgement mail for emailqueueid:\""
						+ emailqueueid + "\" at:\"" + EmailManager.getDate()
						+ "\".");
		SendingAcknowledgement sendingAcknowledgement = new SendingAcknowledgement();
		sendingAcknowledgement.sendAcknowledgement(emailqueueid);
		EmailManager.log.debug("Acknowledgement mail for emailqueueid:\""
				+ emailqueueid + "\" has been sent successfully at:\""
				+ EmailManager.getDate() + "\".");
	}

	public void unzip(String zipFilePath) throws IOException {

		File destDir = new File(zipFilePath.substring(0,
				zipFilePath.lastIndexOf(".")));
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(
				zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file

		while (entry != null) {
			String filePath = destDir + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, filePath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(filePath);
				dir.mkdir();
			}
			// zipIn.closeEntry();
			entry = zipIn.getNextEntry();

		}
		zipIn.close();
	}

	private void extractFile(ZipInputStream zipIn, String filePath)
			throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	/**
	 * Method to handle duplicate file
	 * 
	 * @param path
	 * @param fileName
	 * @param fileExtension
	 * @param i
	 * @param sb
	 */
	public void checkFileName(String path, String fileName,
			String fileExtension, int i, StringBuffer sb) {
		sb.delete(0, sb.length());
		String updatedFileExtension = fileExtension == null
				|| fileExtension.trim().equals("") ? "" : "." + fileExtension;
		if (i == 0) {
			sb.append(fileName + updatedFileExtension);
		} else {
			sb.append(fileName + "_" + i + updatedFileExtension);
		}
		File file = new File(path + File.separatorChar + sb.toString());

		if (file.exists()) {
			i++;
			checkFileName(path, fileName, fileExtension, i, sb);
		} else {
			return;
		}
	}

}
