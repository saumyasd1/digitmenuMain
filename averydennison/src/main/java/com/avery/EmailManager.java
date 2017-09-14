package com.avery;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.adeptia.indigo.logging.Logger;
import com.avery.bao.EmailFolderInformation;

public class EmailManager {

	public String smtpHost;
	public int smtpPort;
	public String username;
	public String password;
	public String directoryLocation;
	public static Logger log;
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	

	public void connectTOEmail(String Protocol, String Host, int Port,
			String UserName, String Password, String dir, String SmtpHost,
			int SmtpPort, Logger _log) throws Exception {
		EmailManager emailmanager = new EmailManager();
		String protocol = Protocol;
		String host = Host;
		int port = Port;
		emailmanager.username = UserName;
		emailmanager.password = Password;
		emailmanager.smtpHost = SmtpHost;
		emailmanager.smtpPort = SmtpPort;
		emailmanager.directoryLocation = dir;
		log = _log;
		emailmanager.connectToEmailServer(protocol, host, port, emailmanager);
	}

	public void connectToEmailServer(String protocol, String host, int port, EmailManager emailmanager)
			throws Exception {
		log.debug("Connecting to Email Server at:\"" + getDate() + "\".");
		EmailFolderInformation emailFolderInformation = new EmailFolderInformation();
		emailFolderInformation.receiveFolderInformation(emailmanager);

	}

	public static String getDate() {
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;

	}

}
