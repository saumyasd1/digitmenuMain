package com.avery.bao;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import com.avery.EmailManager;

public class EmailFolderInformation {

	public void receiveFolderInformation(EmailManager emailManager)
			throws Exception {
		EmailFetch emailFetch = new EmailFetch();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// Connect using IMAPS
		emailManager.log.debug("Creating session  at:\""
				+ EmailManager.getDate() + "\".");
		Session session = Session.getDefaultInstance(props, null);
		emailManager.log.debug("Session created  at:\""
				+ EmailManager.getDate() + "\".");
		Message messages[] = null;
		Folder inbox = null;
		Store store;
		try {
			store = session.getStore("imaps");
			emailManager.log
					.debug("Trying to creating connection to emailid:\""
							+ emailManager.username + "\" at:\""
							+ EmailManager.getDate() + "\".");
			store.connect(emailManager.smtpHost, emailManager.username,
					emailManager.password);
			emailManager.log.debug("Connection etsablished  at:\""
					+ EmailManager.getDate() + "\".");
			// Get the all emails from INBOX
			inbox = store.getFolder("inbox");
			emailManager.log.debug("No of Unread Messages : "
					+ inbox.getUnreadMessageCount() + " at:\""
					+ EmailManager.getDate() + "\".");
			inbox.open(Folder.READ_WRITE);
			/* Get the messages which is unread in the Inbox */
			messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			String msgContent = "";
			Object objectContent;
			try {
				objectContent = message.getContent();
				emailManager.log.debug("Starting to process mail no:\"" + (i + 1)
						+ "\" at:\"" + EmailManager.getDate() + "\".");
				emailFetch.messageFetch(message, inbox, objectContent);
				message.setFlag(Flags.Flag.SEEN, true);
			} catch (IOException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					message.setFlag(Flags.Flag.SEEN, false);
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				throw e;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					message.setFlag(Flags.Flag.SEEN, false);
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				throw e;
			}
			emailManager.log.debug("All mails have been processed.");
		}
	}
}
