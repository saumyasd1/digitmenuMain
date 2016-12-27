package com.avery.bao;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;



//import com.aspose.email.ImapClient;
//import com.aspose.email.ImapFolderInfo;
//import com.aspose.email.ImapMessageInfo;
import com.avery.EmailManager;

public class EmailFolderInformation {
	
	/*public void receiveFolderInformation(String protocol, ImapClient client) throws Exception{
		if(protocol.equalsIgnoreCase("imap")){
			ImapFolderInfo folderInfo = client.getFolderInfo("INBOX");
			// New messages in the folder
			//System.out.println("New message count: " + folderInfo.getNewMessageCount());
			// Check whether its readonly
			//System.out.println("Is it readonly? " + folderInfo.getReadOnly());
			// Total number of messages
			//System.out.println("Total number of messages " + folderInfo.getTotalMessageCount());
			client.selectFolder("INBOX");
			if(folderInfo.getNewMessageCount() > 0){
				System.out.println(folderInfo.getNewMessageCount()+" new emails found in the inbox");
				System.out.println("Checking...");
				for(int i=1;i<=folderInfo.getTotalMessageCount();i++){
					ImapMessageInfo msgInfo = client.listMessage(i);
					Boolean isRead = msgInfo.isRead();
					if (isRead) {
						//System.out.println("IsRead: " + msgInfo.isRead());
					} 
					else {
						System.out.println("New mails found");
						EmailFetch emailFetch = new EmailFetch();
						emailFetch.messageFetch(msgInfo, client, i);
					}
				}
			}
			else
			{
				System.out.println("No new emails found");
			}
			
		}
	}*/
	
	
public void receiveFolderInformation(EmailManager emailManager) throws Exception{
		
		EmailFetch emailFetch = new EmailFetch();
		String propurl = "smtp.properties";
		Properties props = new Properties();
		// read the SMTP properties
		props.load(this.getClass().getClassLoader()
						.getResourceAsStream(propurl));

		// Connect using IMAPS
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect(emailManager.smtpHost, emailManager.username, emailManager.password);
		
		// Get the all emails from INBOX
		Folder inbox = store.getFolder("inbox");
		
		System.out.println("No of Unread Messages : " + inbox.getUnreadMessageCount()); 
		
		inbox.open(Folder.READ_WRITE); 
//		Message[] messages = inbox.getMessages();
		
		/*  Get the messages which is unread in the Inbox*/
		 Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
		 
		
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			String msgContent = "";
			Object objectContent = message.getContent();
			emailFetch.messageFetch( message,  inbox,  objectContent);
			message.setFlag(Flags.Flag.SEEN, true);
			System.out.println("Checked");
//			message.setFlag(Flag.SEEN, true); 
//			if (objectContent instanceof Multipart) {
//				Multipart mp = (Multipart) objectContent;
//				for (int ii = 0; ii < mp.getCount(); ii++) {
//					BodyPart bp = mp.getBodyPart(ii);
//					if (Pattern.compile(Pattern.quote("text/html"),Pattern.CASE_INSENSITIVE).matcher(bp.getContentType()).find()) {
//						msgContent = msgContent + (String) bp.getContent();
//						
//					}
//				}
//			} 
		}
		
			
	}

}	
