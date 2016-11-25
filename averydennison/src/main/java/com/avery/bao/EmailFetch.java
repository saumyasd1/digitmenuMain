package com.avery.bao;

import java.io.File;

import com.aspose.email.ImapClient;
import com.aspose.email.ImapMessageInfo;
import com.aspose.email.MailMessage;
import com.aspose.email.SaveOptions;
import com.avery.dao.UnreadEmailInformation;

public class EmailFetch {
	
	private static final String directoryLocation = "C:\\AveryDennisonFiles"; //Directory for storing emails and attachments
	String uniqueID;
	String messageId;
	
	public void messageFetch(ImapMessageInfo msgInfo, ImapClient client, int sequenceNumber) throws Exception{
		UnreadEmailInformation unreadEmailInformation = new UnreadEmailInformation(msgInfo);
		messageId = unreadEmailInformation.getMessageID();
		
		System.out.println("Message Id : "+unreadEmailInformation.getMessageID());
		System.out.println("Message Subject: " + msgInfo.getSubject());
		System.out.println("From: " + msgInfo.getFrom());
		System.out.println("To: " + msgInfo.getTo());
		System.out.println("CC: " + msgInfo.getCC());
		//System.out.println("Sent Date: " + msgInfo.getSentDate());
		System.out.println("IsRead: " + msgInfo.isRead());
	    System.out.println("Mime Message Id: " + msgInfo.getMessageId());
	    System.out.println("Unique Id: " + msgInfo.getUniqueId());
	    
	    
	    //Create the directory for saving the email information
	    uniqueID = messageId.substring(1, messageId.lastIndexOf(">"));
	    MailMessage eml = client.fetchMessage(sequenceNumber);
	    String dir = createDirectory(uniqueID);
	    try{
			eml.save(dir + "/" + "CompleteEmail" + ".eml", SaveOptions.getDefaultEml()); // save as EML
			eml.save(dir + "/" + "CompleteEmail" + ".msg", SaveOptions.getDefaultMsg()); // save as MSG
		}
		catch(Exception e){
				
		}
	    System.out.println(dir);
	    
	    
	    
	    //Save the Email body in pdf format
	    MailBodyToPDF mailBodyToPDF = new MailBodyToPDF();
	    mailBodyToPDF.convertMailToPDF(dir);
	    AttachmentHandling attachmentHandling = new AttachmentHandling();
	    attachmentHandling.extractAttachment(dir, 0);
	    
	    
	}
	
	public String createDirectory(String uniqueID) {
		// TODO Auto-generated method stub
	
		File file = new File(directoryLocation + File.separator + uniqueID);
		//File file = new File("C:\\Directory");
	        if (!file.exists()) {
	            if (file.mkdir()) {
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
			return file.getAbsolutePath().replace("\\","/");
	}

}
