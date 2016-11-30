package com.avery.bao;

import com.aspose.email.MailMessage;
import com.aspose.email.SecurityOptions;
import com.aspose.email.SmtpClient;
import com.avery.services.AcknowledgementService;

public class SendingAcknowledgement {
	
	public void sendAcknowledgement(int emailqueueid){
		String to = "";//get the email address by calling a method
		AcknowledgementService acknowledgementService = new AcknowledgementService();
		to = acknowledgementService.getAcknowledgementEmailID(emailqueueid);
    	String username = "vishal566802@gmail.com";
		String password = "yaadnahihai";
		SmtpClient client = new SmtpClient("smtp.gmail.com", 587, username, password);
		client.setSecurityOptions(SecurityOptions.Auto);
		String from = username;
		String subject = "Order Acknowledgement";
		String body = "The Emailqueue id is : "+emailqueueid;
		MailMessage msg = new MailMessage(from, to, subject, body);
		System.out.println("Sending Message...");
		client.send(msg);
		System.out.println("Message sent...");
    }

}
