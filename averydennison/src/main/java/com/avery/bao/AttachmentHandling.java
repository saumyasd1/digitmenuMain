package com.avery.bao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.apache.commons.io.FilenameUtils;

import com.avery.EmailManager;
import com.avery.services.EmailAttachmentService;
import com.avery.services.EmailQueueService;

public class AttachmentHandling {

	private static final int BUFFER_SIZE = 4096;
	private HashSet<String> zipFiles=new HashSet<String>();
	public String currentFile;
	
	public void extractAttachment(String dir, int emailqueueid, Message message)
			throws IOException, MessagingException {

		EmailAttachmentService emailAttachmentService = new EmailAttachmentService();
		EmailQueueService emailQueueService = new EmailQueueService();
		
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
						if(FilenameUtils.getExtension(attachmentFileName.trim()).equals("zip")){
							zipFiles.clear();
							unzip(attachmentFileName, dir, part, emailqueueid);
						}
						else{
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
			}
			
			emailQueueService.updateStatus(emailqueueid, "1");
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

	/*public void unzip(String zipFilePath) throws IOException {

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
*/
	
	/**
	 * Method to extract zip file
	 * @param zipFileName
	 * @param destinationDirectory
	 * @param part
	 * @param emailqueueid
	 * @author Rakesh
	 */
	private void unzip(String zipFileName, String destinationDirectory,
			MimeBodyPart part, int emailqueueid) {
		EmailManager.log.info("Start unZip of zipFile");
		File folder = new File(destinationDirectory);
		if (!folder.exists()) {
			EmailManager.log
					.info("Directory of destination folder isn't exist.So, we have create it.");
			folder.mkdirs();
		}
		FileInputStream fileInputStream = null;
		try {
			ZipFile zipFile = new ZipFile(destinationDirectory
					+ File.separatorChar + zipFileName);
			if (zipFile.isEncrypted()) {
				EmailManager.log.debug("zipFile along with it's path =\""
						+ zipFile.getFile().getPath()
						+ "\" is password protected");
			} else {
				fileInputStream = new FileInputStream(destinationDirectory
						+ File.separatorChar + zipFileName);
				ZipInputStream zipInputStream = new ZipInputStream(
						fileInputStream);
				ZipEntry zipEntry = zipInputStream.getNextEntry();
				while (zipEntry != null) {
					String fileName = FilenameUtils.getName(zipEntry.getName());
					extractFile(destinationDirectory, fileName, zipInputStream,
							part, emailqueueid, zipFileName);
					if (fileName.contains(".zip")) {
						EmailManager.log
								.info("ZipFile contains also another zip name as =\""
										+ fileName + "\"");
						if (checkZipIsExtracted(fileName)) {
							unzip(fileName, destinationDirectory, part,
									emailqueueid);
						}
						zipFiles.add(fileName);
					} else {
						EmailManager.log
								.info("ZipFile Contains filename as =\""
										+ fileName + "\"");
					}
					zipInputStream.closeEntry();
					zipEntry = zipInputStream.getNextEntry();
				}
				zipInputStream.closeEntry();
				zipInputStream.close();

			}
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			EmailManager.log
					.error("ZipFile must be curroupted or should be missing.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			EmailManager.log.error("File is missing from given location.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			EmailManager.log
					.error("File is missing from given location which you want to do read or write opertion.");
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				EmailManager.log
						.error("File is missing from given location which you want to close.");
				e.printStackTrace();
			}
		}
		EmailManager.log.info("End unZip of zipFile");
	}
	
	/**
	 * Method to generate extracted File at destination location 
	 * @param destinationDirectory
	 * @param fileName
	 * @param zipInputStream
	 * @param part
	 * @param emailqueueid
	 * @param zipFileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author Rakesh
	 */
	private void extractFile(String destinationDirectory, String fileName,
			ZipInputStream zipInputStream, MimeBodyPart part, int emailqueueid,String zipFileName)
			throws FileNotFoundException, IOException {
		EmailAttachmentService emailAttachmentService = new EmailAttachmentService();
		FileOutputStream fileOutputStream = null;
		try {
			if (!FilenameUtils.getExtension(fileName).trim().isEmpty()) {
				byte[] buffer = new byte[BUFFER_SIZE];
				File newFile = new File(destinationDirectory + File.separator
						+ fileName);
				int length;

				if (!fileName.endsWith(".zip")) {
					System.out.println(fileName);
					checkFileName(destinationDirectory,
							FilenameUtils.getBaseName(fileName),
							FilenameUtils.getExtension(fileName), 0,
							new StringBuffer());
					fileOutputStream = new FileOutputStream(currentFile);
					while ((length = zipInputStream.read(buffer)) > 0) {
						fileOutputStream.write(buffer, 0, length);
					}
					EmailManager.log.debug("Attachment file:\"" + currentFile
							+"\"containing by Zip:\""
							+zipFileName
							+ " has been written successfully at:\""
							+ EmailManager.getDate() + "\".");
					EmailManager.log
							.debug("Inserting attachment details for the file:\""
									+ currentFile
									+"\"containing by Zip:\""
									+zipFileName
									+ "\" in the table orderfileattachment having emailqueueid:\""
									+ emailqueueid
									+ "\" at:\""
									+ EmailManager.getDate() + "\".");
					emailAttachmentService.insertIntoEmailAttachment(part,
							destinationDirectory, emailqueueid,
							FilenameUtils.getName(currentFile),
							FilenameUtils.getExtension(currentFile));
					EmailManager.log
							.debug("Attachment details for the  file:\""
									+ currentFile
									+"\"containing by Zip:\""
									+zipFileName
									+ "\" in the table orderfileattachment having emailqueueid:\""
									+ emailqueueid
									+ "\" has been inserted at:\""
									+ EmailManager.getDate() + "\".");
				} else {
					fileOutputStream = new FileOutputStream(newFile);
					while ((length = zipInputStream.read(buffer)) > 0) {
						fileOutputStream.write(buffer, 0, length);
					}
				}
			}
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}

	}
	
	/**
	 * Method to check weather zip is Extracted before 
	 * @param fileName
	 * @return boolean
	 * @author Rakesh 
	 */
	public boolean checkZipIsExtracted(String fileName) {
		if (zipFiles.isEmpty()) {
			return true;
		} else {
			Iterator<String> iterator = zipFiles.iterator();
			while (iterator.hasNext()) {
				String savedZipFilesName = iterator.next();
				if (savedZipFilesName.equals(fileName)) {
					return false;
				}
			}
			return true;
		}
	}
	
	
	/**
	 * Method to handle duplicate file
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
			//Add to get new File if FIle is duplicate
			currentFile=path + File.separatorChar + sb.toString();
			return;
		}
	}

}
