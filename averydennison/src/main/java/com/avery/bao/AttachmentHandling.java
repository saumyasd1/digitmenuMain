package com.avery.bao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.aspose.email.Attachment;
import com.aspose.email.AttachmentCollection;
import com.aspose.email.EmlLoadOptions;
import com.aspose.email.MailMessage;

public class AttachmentHandling {
	
	private static final int BUFFER_SIZE = 4096;
	
	public void extractAttachment(String dir, int emailqueueid) throws IOException{
		MailMessage eml = MailMessage.load(dir + "/" + "CompleteEmail" + ".eml", new EmlLoadOptions());
		AttachmentCollection attachments = eml.getAttachments();
		String[] attachmentName = new String[attachments.size()];
		String newAttachmentName = null;
		String filePath = null;
		
		for (int index = 0; index < attachments.size(); index++) {
			//Initialize Attachment object and Get the indexed Attachment reference
			Attachment attachment = (Attachment) attachments.get_Item(index);
			//Display Attachment Name				
			//String attachmentName = attachment.getName();
			
			attachmentName[index] = attachment.getName();
			System.out.println(attachmentName[index]);
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
				//insertIntoEmailAttachmentTable(attachment, filePath, emailqueueid);
				String unzipDirectory = filePath.substring(0,filePath.lastIndexOf("."));
				System.out.println("Destination Directory : "+unzipDirectory);
				File folder = new File(unzipDirectory);
		        File[] listofFiles = folder.listFiles();
		        String path = "";
		        for(int i=0;i<listofFiles.length;i++){
		        	path = listofFiles[i].getAbsolutePath().replace("\\", "/");
		        	//insertUnzippedFiles(listofFiles[i].getName(), path, emailqueueid);
		        	System.out.println(listofFiles[i].getAbsolutePath()+" "+listofFiles[i].getName());
		        }
				continue;
			}
			System.out.println(filePath);
			//insertIntoEmailAttachmentTable(attachment, filePath, emailqueueid);
		}
		System.out.println("All Attachments Stored");
		//getAcknowledgementId(emailqueueid);
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

}
