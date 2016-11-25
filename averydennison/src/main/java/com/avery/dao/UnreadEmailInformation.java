package com.avery.dao;

import com.aspose.email.ImapMessageInfo;

public class UnreadEmailInformation {
	
	public UnreadEmailInformation(){
		
	}

	public UnreadEmailInformation(ImapMessageInfo msgInfo) {
		// TODO Auto-generated constructor stub
		this.messageID = msgInfo.getMessageId();
	}
	
	private String messageID;

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

}
