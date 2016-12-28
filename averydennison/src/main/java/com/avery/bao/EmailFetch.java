package com.avery.bao;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

//import com.aspose.email.ImapClient;
//import com.aspose.email.ImapMessageInfo;
//import com.aspose.email.MailMessage;
//import com.aspose.email.SaveOptions;
import com.avery.DataConversionUtils;
import com.avery.EmailManager;
import com.avery.dao.OrderEmailQueue;
import com.avery.dao.OrderFileAttachment;
//import com.avery.dao.UnreadEmailInformation;
import com.avery.services.EmailQueueService;
import com.avery.utils.HibernateUtil;
import com.sun.mail.imap.IMAPFolder;

public class EmailFetch {
	
	//String directoryLocation = ""; //Directory for storing emails and attachments
	String uniqueID;
	String messageId;
	
	/*public void messageFetch(ImapMessageInfo msgInfo, ImapClient client, int sequenceNumber) throws Exception{
		UnreadEmailInformation unreadEmailInformation = new UnreadEmailInformation(msgInfo);
		messageId = unreadEmailInformation.getMessageID();
		
		//System.out.println("Message Id : "+unreadEmailInformation.getMessageID());
		//System.out.println("Message Subject: " + msgInfo.getSubject());
		//System.out.println("From: " + msgInfo.getFrom());
		//System.out.println("To: " + msgInfo.getTo());
		//System.out.println("CC: " + msgInfo.getCC());
		//System.out.println("Sent Date: " + msgInfo.getSentDate());
		//System.out.println("IsRead: " + msgInfo.isRead());
	    //System.out.println("Mime Message Id: " + msgInfo.getMessageId());
	    //System.out.println("Unique Id: " + msgInfo.getUniqueId());
	    
	    
	    //Create the directory for saving the email information
	    uniqueID = messageId.substring(1, messageId.lastIndexOf(">"));
	    MailMessage eml = client.fetchMessage(sequenceNumber);
	    String dir = createDirectory(uniqueID, EmailManager.directoryLocation);
	    try{
			eml.save(dir + "/" + "CompleteEmail" + ".eml", SaveOptions.getDefaultEml()); // save as EML
			eml.save(dir + "/" + "CompleteEmail" + ".msg", SaveOptions.getDefaultMsg()); // save as MSG
		}
		catch(Exception e){
				
		}
	    //System.out.println(dir);
	    
	    
	    
	    //Save the Email body in pdf format
	    MailBodyToPDF mailBodyToPDF = new MailBodyToPDF();
	    mailBodyToPDF.convertMailToPDF(dir);
	    
	    Date currentDate = new Date();
	    String subject = msgInfo.getSubject();
	    String sender = msgInfo.getSender().toString();
	    String mailbodypath = dir+"/MailBody.pdf";
	    Date receivedDate = msgInfo.getDate();
	    String cc = msgInfo.getCC().toString();
	    String to = msgInfo.getTo().toString();
		EmailQueueService emailQueueService = new EmailQueueService();
		int emailqueueid = emailQueueService.insertData(subject,sender,mailbodypath,receivedDate, currentDate, cc, to);
		//System.out.println("Emailqueue Id is : "+emailqueueid);
	    //Write the code here for inserting into the emailqueue table and get the email queue id
	    
	    AttachmentHandling attachmentHandling = new AttachmentHandling();
	    attachmentHandling.extractAttachment(dir, emailqueueid);//pass the emailqueueid in place of '0' 
	    
	    
	}*/
	
	
	public void messageFetch(Message message, Folder folder, Object objectContent) throws Exception{
//		UnreadEmailInformation unreadEmailInformation = new UnreadEmailInformation(msgInfo);
//		messageId = unreadEmailInformation.getMessageID();
		UIDFolder uf = (UIDFolder)folder;
//		uniqueID = Long.toString(uf.getUID(message)); 
		String msgId[] =message.getHeader("Message-Id"); 
		uniqueID = msgId[0];
		
//		 IMAPFolder ifolder = (IMAPFolder)folder;
//		messageId = Long.toString(ifolder.getUID(message)); 
		
		
		//System.out.println("Message Id : "+unreadEmailInformation.getMessageID());
		//System.out.println("Message Subject: " + msgInfo.getSubject());
		//System.out.println("From: " + msgInfo.getFrom());
		//System.out.println("To: " + msgInfo.getTo());
		//System.out.println("CC: " + msgInfo.getCC());
		//System.out.println("Sent Date: " + msgInfo.getSentDate());
		//System.out.println("IsRead: " + msgInfo.isRead());
	    //System.out.println("Mime Message Id: " + msgInfo.getMessageId());
	    //System.out.println("Unique Id: " + msgInfo.getUniqueId());
	    
	    
	    //Create the directory for saving the email information
		uniqueID = uniqueID.replaceAll("[\\/:*?\"<>|]", "");  
//	    uniqueID = messageId.substring(1, messageId.lastIndexOf(">"));  
//	    MailMessage eml = client.fetchMessage(sequenceNumber);
	    String dir = createDirectory(uniqueID, EmailManager.directoryLocation);
	    try{
//			eml.save(dir + "/" + "CompleteEmail" + ".eml", SaveOptions.getDefaultEml()); // save as EML
//			eml.save(dir + "/" + "CompleteEmail" + ".msg", SaveOptions.getDefaultMsg()); // save as MSG
			message.writeTo(new FileOutputStream(new File(dir + "/" + "CompleteEmail" + ".eml")));
			message.writeTo(new FileOutputStream(new File(dir + "/" + "CompleteEmail" + ".msg")));
	    	
//	    	message.writeTo(new FileOutputStream(new File(dir + File.separatorChar+message.getSubject()+".eml")));
//			message.writeTo(new FileOutputStream(new File(dir + File.separatorChar+message.getSubject()+".msg")));
			
			DataConversionUtils dataConversionUtils = new DataConversionUtils();
//			writing html file
			dataConversionUtils.generateHTMLFile(dir, "CompleteEmail", objectContent); 
			
//			writing excel file
			dataConversionUtils.generateExcelFile(dir, "CompleteEmail", dir, "CompleteEmail", "xls");
			
//			writing pdf file
			dataConversionUtils.generatePDFfromHTML(dir, "CompleteEmail.html"); 
			
			
			
			
			
		}
		catch(Exception e){
				
		}
	    //System.out.println(dir);
	    
	    
	    
	    //Save the Email body in pdf format
//	    MailBodyToPDF mailBodyToPDF = new MailBodyToPDF();
//	    mailBodyToPDF.convertMailToPDF(dir);
	    
	    Date currentDate = new Date();
//	    String subject = msgInfo.getSubject();
	    String subject = message.getSubject();
//	    String sender = msgInfo.getSender().toString();
	    Address[] froms = message.getFrom();
	    String sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
	    String mailbodypath = dir+"/MailBody.pdf";
//	    Date receivedDate = msgInfo.getDate();
	    Date receivedDate = message.getReceivedDate();
//	    String cc = msgInfo.getCC().toString();
	    String cc = "";
		if (message.getRecipients(Message.RecipientType.CC) != null) {
			List ccList = Arrays.asList(message
					.getRecipients(Message.RecipientType.CC));
			for (Iterator iterator = ccList.iterator(); iterator.hasNext();) {
				InternetAddress ccAddress = (InternetAddress) iterator.next();
				cc = cc + ";" + ccAddress.getAddress(); 
			}
			if (cc.startsWith(";")) {
				cc = cc.substring(cc.indexOf(";") + 1);
			}
		}
//	    String to = msgInfo.getTo().toString();
	    
	    String to = "";
	    List toList = Arrays.asList(message.getRecipients(Message.RecipientType.TO));
		if (message.getRecipients(Message.RecipientType.TO) != null) {
			for (Iterator iterator = toList.iterator(); iterator.hasNext();) {
				
				InternetAddress toAddress = (InternetAddress) iterator.next();
				to = to + ";" + toAddress.getAddress();  
			}
			if (to.startsWith(";")) {
				to = to.substring(to.indexOf(";") + 1);
			}
		}
		EmailQueueService emailQueueService = new EmailQueueService(); 
		int emailqueueid = emailQueueService.insertData(subject,sender,mailbodypath,receivedDate, currentDate, cc, to);
		//System.out.println("Emailqueue Id is : "+emailqueueid);
	    //Write the code here for inserting into the emailqueue table and get the email queue id
	    
	    AttachmentHandling attachmentHandling = new AttachmentHandling();
	    
	    SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		OrderEmailQueue orderEmailQueue=(OrderEmailQueue)session.get(OrderEmailQueue.class, emailqueueid);
		//System.out.println("Content Type : "+contentType);
		OrderFileAttachment orderFileAttachment = new OrderFileAttachment(emailqueueid, "CompleteEmail", ".pdf", dir, "PDF"); 
		orderFileAttachment.setVarOrderEmailQueue(orderEmailQueue);
		session.persist(orderFileAttachment);
		session.getTransaction().commit();
		session.close();
		
		
	    attachmentHandling.extractAttachment(dir, emailqueueid, message);//pass the emailqueueid in place of '0' 
	    
	    
	}
	
	public String createDirectory(String uniqueID, String directoryLocation) {
		// TODO Auto-generated method stub
	
		File file = new File(directoryLocation + File.separatorChar + uniqueID);
	        if (!file.exists()) {
	            if (file.mkdirs()) { 
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
			return file.getAbsolutePath();
	}

}
