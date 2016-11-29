package com.avery.bao;

import com.aspose.email.ImapClient;
import com.aspose.email.ImapFolderInfo;
import com.aspose.email.ImapMessageInfo;

public class EmailFolderInformation {
	
	public void receiveFolderInformation(String protocol, ImapClient client) throws Exception{
		if(protocol.equalsIgnoreCase("imap")){
			ImapFolderInfo folderInfo = client.getFolderInfo("INBOX");
			// New messages in the folder
			System.out.println("New message count: " + folderInfo.getNewMessageCount());
			// Check whether its readonly
			System.out.println("Is it readonly? " + folderInfo.getReadOnly());
			// Total number of messages
			System.out.println("Total number of messages " + folderInfo.getTotalMessageCount());
			client.selectFolder("INBOX");
			for(int i=1;i<=folderInfo.getTotalMessageCount();i++){
				ImapMessageInfo msgInfo = client.listMessage(i);
				Boolean isRead = msgInfo.isRead();
				if (isRead) {
					System.out.println("IsRead: " + msgInfo.isRead());
				} 
				else {
					EmailFetch emailFetch = new EmailFetch();
					emailFetch.messageFetch(msgInfo, client, i);
				}
			}
		}
	}

}	
