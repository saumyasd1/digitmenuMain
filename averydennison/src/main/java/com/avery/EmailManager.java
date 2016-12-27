package com.avery;

import com.avery.bao.EmailFolderInformation;

public class EmailManager {
	
	
	public static String smtpHost;
	public static int smtpPort;
	public static String username;
	public static String password; 
	public static String directoryLocation;

	public void connectTOEmail(String Protocol, String Host, int Port, String UserName, String Password, String dir, String SmtpHost, int SmtpPort) throws Exception{
		String protocol = Protocol;
        String host = Host;
        int port = Port;  
        this.username = UserName;
        this.password = Password;
        this.smtpHost = SmtpHost;
        this.smtpPort = SmtpPort;
        this.directoryLocation = dir;
        EmailManager emailmanager = new EmailManager();
        emailmanager.connectToEmailServer(protocol, host, port);
	}
	
	
	public void connectToEmailServer(String protocol, String host, int port) throws Exception{
		EmailFolderInformation emailFolderInformation = new EmailFolderInformation();
		emailFolderInformation.receiveFolderInformation(this);
		
	}

}
