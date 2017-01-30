package com.avery;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.adeptia.indigo.logging.Logger;
import com.avery.bao.EmailFolderInformation;

public class EmailManager {

	public static String smtpHost;
	public static int smtpPort;
	public static String username;
	public static String password;
	public static String directoryLocation;
	public static Logger log;
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");

	public void connectTOEmail(String Protocol, String Host, int Port,
			String UserName, String Password, String dir, String SmtpHost,
			int SmtpPort, Logger _log) throws Exception {
		String protocol = Protocol;
		String host = Host;
		int port = Port;
		this.username = UserName;
		this.password = Password;
		this.smtpHost = SmtpHost;
		this.smtpPort = SmtpPort;
		this.directoryLocation = dir;
		this.log = _log;
		EmailManager emailmanager = new EmailManager();
		emailmanager.connectToEmailServer(protocol, host, port);
	}

	public void connectToEmailServer(String protocol, String host, int port)
			throws Exception {
		log.debug("Connecting to Email Server at:\"" + getDate() + "\".");
		EmailFolderInformation emailFolderInformation = new EmailFolderInformation();
		emailFolderInformation.receiveFolderInformation(this);

	}

	public static String getDate() {
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;

	}

}
