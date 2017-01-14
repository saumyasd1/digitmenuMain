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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.avery.services.AcknowledgementService;
import com.avery.services.EmailAttachmentService;

public class AttachmentHandling {
	
	private static final int BUFFER_SIZE = 4096;
	
	/*public void extractAttachment(String dir, int emailqueueid) throws IOException{
		MailMessage eml = MailMessage.load(dir + "/" + "CompleteEmail" + ".eml", new EmlLoadOptions());
		AttachmentCollection attachments = eml.getAttachments();
		
		
		EmailAttachmentService emailAttachmentService = new EmailAttachmentService();
		SendingAcknowledgement acknowledgement = new SendingAcknowledgement();
		String[] attachmentName = new String[attachments.size()];
		String newAttachmentName = null;
		String filePath = null;
		String fileName;
		String fileExtension = "";
		
		for (int index = 0; index < attachments.size(); index++) {
			//Initialize Attachment object and Get the indexed Attachment reference
			Attachment attachment = (Attachment) attachments.get_Item(index);
			//Display Attachment Name				
			//String attachmentName = attachment.getName();
			
			attachmentName[index] = attachment.getName();
			//System.out.println(attachmentName[index]);
			//Save Attachment to disk
			for (int i = 0; i < index; i++) {
			if(attachmentName[index].equalsIgnoreCase(attachmentName[i]))
			{
				newAttachmentName = attachmentName[index].substring(0,attachmentName[index].lastIndexOf(".")) + "-Copy" + ++i + attachmentName[index].substring(attachmentName[index].lastIndexOf(".")) ;
				attachment.save(dir + "/" + newAttachmentName);
				if(newAttachmentName.endsWith(".zip"))
				{						
					unzip(dir + "/" + newAttachmentName);
					filePath = dir + "/" + newAttachmentName;
				}
				filePath = dir + "/" + newAttachmentName;
			}
			
			}				
			attachment.save(dir + "/" + attachmentName[index]);							
			filePath = dir + "/" + attachmentName[index];
			if(attachment.getName().endsWith(".zip"))
			{						
				unzip(dir + "/" + attachmentName[index]);
				filePath = dir + "/" + attachmentName[index];
				emailAttachmentService.insertIntoEmailAttachment(attachment, filePath, emailqueueid);
				//insertIntoEmailAttachmentTable(attachment, filePath, emailqueueid);
				String unzipDirectory = filePath.substring(0,filePath.lastIndexOf("."));
				//System.out.println("Destination Directory : "+unzipDirectory);
				File folder = new File(unzipDirectory);
		        File[] listofFiles = folder.listFiles();
		        String path = "";
		        for(int i=0;i<listofFiles.length;i++){
		        	path = listofFiles[i].getAbsolutePath().replace("\\", "/");
		        	emailAttachmentService.insertUnzippedFile(listofFiles[i].getName(), path, emailqueueid);
		        	//insertUnzippedFiles(listofFiles[i].getName(), path, emailqueueid);
		        	//System.out.println(listofFiles[i].getAbsolutePath()+" "+listofFiles[i].getName());
		        }
				continue;
			}
			//System.out.println(filePath);
			fileName = attachment.getName();
			if(fileName.contains(".")){
				fileExtension = fileName.substring(fileName.lastIndexOf("."));
			}
			//insertIntoEmailAttachmentTable(attachment, filePath, emailqueueid);
			emailAttachmentService.insertIntoEmailAttachment(attachment, filePath, emailqueueid);
		}
		System.out.println("All Attachments Stored successfully");
		SendingAcknowledgement sendingAcknowledgement = new SendingAcknowledgement();
		sendingAcknowledgement.sendAcknowledgement(emailqueueid);
	}*/
	
	
	
	public void extractAttachment(String dir, int emailqueueid, Message message) throws IOException, MessagingException{
		
		
		EmailAttachmentService emailAttachmentService = new EmailAttachmentService();
		SendingAcknowledgement acknowledgement = new SendingAcknowledgement();
//		String[] attachmentName = new String[attachments.size()];
		String newAttachmentName = null;
		String filePath = null;
		String fileName;
		String fileExtension = "";
		
		
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
						String attachmentFileName =  MimeUtility.decodeText( part.getFileName());
						StringBuffer sb = new StringBuffer();
						checkFileName(dir,
								FilenameUtils.getBaseName(attachmentFileName), 
								FilenameUtils.getExtension(attachmentFileName),
								0, sb);
						part.saveFile(dir + File.separatorChar
								+ sb.toString());

						emailAttachmentService.insertIntoEmailAttachment(part,
								dir, emailqueueid, 
								sb.toString(), 
								FilenameUtils.getExtension( sb.toString()));   
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
		
		
		
		/*for (int index = 0; index < attachments.size(); index++) {
			//Initialize Attachment object and Get the indexed Attachment reference
			Attachment attachment = (Attachment) attachments.get_Item(index);
			//Display Attachment Name				
			//String attachmentName = attachment.getName();
			
			attachmentName[index] = attachment.getName();
			//System.out.println(attachmentName[index]);
			//Save Attachment to disk
			for (int i = 0; i < index; i++) {
			if(attachmentName[index].equalsIgnoreCase(attachmentName[i]))
			{
				newAttachmentName = attachmentName[index].substring(0,attachmentName[index].lastIndexOf(".")) + "-Copy" + ++i + attachmentName[index].substring(attachmentName[index].lastIndexOf(".")) ;
				attachment.save(dir + "/" + newAttachmentName);
				if(newAttachmentName.endsWith(".zip"))
				{						
					unzip(dir + "/" + newAttachmentName);
					filePath = dir + "/" + newAttachmentName;
				}
				filePath = dir + "/" + newAttachmentName;
			}
			
			}				
			attachment.save(dir + "/" + attachmentName[index]);							
			filePath = dir + "/" + attachmentName[index];
			if(attachment.getName().endsWith(".zip"))
			{						
				unzip(dir + "/" + attachmentName[index]);
				filePath = dir + "/" + attachmentName[index];
				emailAttachmentService.insertIntoEmailAttachment(attachment, filePath, emailqueueid);
				//insertIntoEmailAttachmentTable(attachment, filePath, emailqueueid);
				String unzipDirectory = filePath.substring(0,filePath.lastIndexOf("."));
				//System.out.println("Destination Directory : "+unzipDirectory);
				File folder = new File(unzipDirectory);
		        File[] listofFiles = folder.listFiles();
		        String path = "";
		        for(int i=0;i<listofFiles.length;i++){
		        	path = listofFiles[i].getAbsolutePath().replace("\\", "/");
		        	emailAttachmentService.insertUnzippedFile(listofFiles[i].getName(), path, emailqueueid);
		        	//insertUnzippedFiles(listofFiles[i].getName(), path, emailqueueid);
		        	//System.out.println(listofFiles[i].getAbsolutePath()+" "+listofFiles[i].getName());
		        }
				continue;
			}
			//System.out.println(filePath);
			fileName = attachment.getName();
			if(fileName.contains(".")){
				fileExtension = fileName.substring(fileName.lastIndexOf("."));
			}
			//insertIntoEmailAttachmentTable(attachment, filePath, emailqueueid);
			emailAttachmentService.insertIntoEmailAttachment(attachment, filePath, emailqueueid);
		}*/
		System.out.println("All Attachments Stored successfully");
		SendingAcknowledgement sendingAcknowledgement = new SendingAcknowledgement();
		sendingAcknowledgement.sendAcknowledgement(emailqueueid);
	}
	
    public void unzip(String zipFilePath) throws IOException {
    	
        File destDir = new File(zipFilePath.substring(0,zipFilePath.lastIndexOf(".")));
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
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
       //     zipIn.closeEntry();
            entry = zipIn.getNextEntry();
            
        }
        zipIn.close();
    }
    
    
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
    
	/**Method to handle duplicate file
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
		}
		else{
			sb.append(fileName+ "_" + i+ updatedFileExtension);
		}
		File file = new File(path+File.separatorChar+sb.toString());

		if (file.exists()) {
			i++;
			checkFileName(path, fileName, fileExtension, i, sb);  
		} else {
			return; 
		}
	}

}
