package com.avery.bao;

import com.aspose.email.MailMessage;
import com.aspose.email.SecurityOptions;
import com.aspose.email.SmtpClient;

public class SendingAcknowledgement {
	
	public void sendAcknowledgement(String senderId, int emailqueueid){
    	String username = "vishal566802@gmail.com";
		String password = "yaadnahihai";
		SmtpClient client = new SmtpClient("smtp.gmail.com", 587, username, password);
		client.setSecurityOptions(SecurityOptions.Auto);
		String from = username;
		String to = senderId;
		String subject = "Order Acknowledgement";
		String body = "The Emailqueue id is : "+emailqueueid;
		MailMessage msg = new MailMessage(from, to, subject, body);
		System.out.println("Sending Message...");
		client.send(msg);
		System.out.println("Message sent...");
    }

}
