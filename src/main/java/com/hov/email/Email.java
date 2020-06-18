package com.hov.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email 
{
    String to;
    String subject;
    String text;

    public Email(String to, String subject, String text) 
    {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public void sendEmail() 
    {
    	try
    	{
			final String username = "pvt.robin@gmail.com";
			final String password = "ROD#KayTeaEm$55$";
	
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
	
			Session session = Session.getInstance(props, new javax.mail.Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication() 
				{
					return new PasswordAuthentication(username, password);
				}
			});
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,
								      InternetAddress.parse(to));
				message.setSubject(subject);
				message.setText(text);
	
				Transport.send(message);
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
}