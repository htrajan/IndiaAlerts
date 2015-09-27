package org.cfi.hackathon.alert;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class IndiaAlertService 
{
	private Session getSMTPSession()
	{
		String username = "code.india.alerts@gmail.com";
		String password = "test-1234";
		
		// Get system properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// Get the default Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		return session;
	}
	
	private Store getIMAPStore() throws MessagingException
	{
		String host = "imap.gmail.com";
		
		String username = "code.india.alerts@gmail.com";
		String password = "test-1234";
		
		// Get system properties
		Properties props = new Properties();
		props.put("mail.imaps.host", host);
		props.put("mail.imaps.port", "993");
		props.put("mail.imaps.ssl.trust", host);

		// Get the default Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
        
        Store store = session.getStore("imaps");
        store.connect();
	    
	    return store;
	}
	
	public void sendAmberAlert() throws MessagingException, IOException
	{
		Store store = getIMAPStore();
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);
		if (inbox.getMessageCount() > 0)
		{
			for (Message msg : inbox.getMessages())
			{
				Object content = msg.getContent();
				if (msg.getSubject().startsWith("SMS") && content.equals("SUBSCRIBE\r\n"))
				{
					System.out.println(msg.getSubject() + " " + msg.getContent());
					msg.setFlag(Flags.Flag.DELETED, true);
				}
			}
		}
		inbox.close(true);
		
		// Recipient's email ID needs to be mentioned.
		String to = "14083947724@tmomail.net";

		// Sender's email ID needs to be mentioned
		String from = "web@gmail.com";

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(getSMTPSession());

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("INDIA ALERT");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}
		catch (MessagingException mex) 
		{
			mex.printStackTrace();
		}
	}
}
