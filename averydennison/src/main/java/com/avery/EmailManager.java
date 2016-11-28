package com.avery;

import com.aspose.email.ImapClient;
import com.aspose.email.Pop3Client;
import com.aspose.email.SecurityOptions;
import com.avery.bao.EmailFolderInformation;

public class EmailManager {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String protocol = "imap";
        String host = "imap.gmail.com";
        int port = 993;  
        String userName = "vishal566802@gmail.com";//this is a demo email id for testing.............change accordingly
        String password = "yaadnahihai";
        
        EmailManager emailmanager = new EmailManager();
        emailmanager.connectToEmailServer(protocol, host, userName, password, port);

	}
	
	public void connectToEmailServer(String protocol, String host, String userName, String password, int port) throws Exception{
		if(protocol.equalsIgnoreCase("pop3")){
			Pop3Client client = new Pop3Client();
			client.setHost("pop.domain.com");
			client.setPort(port); //This can be different from server to server
			client.setUsername(userName);
			client.setPassword(password);
			client.setSecurityOptions(SecurityOptions.Auto);
		}
		else{
			ImapClient client = new ImapClient();
			client.setHost("imap.gmail.com");
			client.setPort(port);
			client.setUsername(userName);
			client.setPassword(password);
			client.setSecurityOptions(SecurityOptions.Auto);
			EmailFolderInformation emailFolderInformation = new EmailFolderInformation();
			emailFolderInformation.receiveFolderInformation(protocol, client);
		}
		
	}

}
