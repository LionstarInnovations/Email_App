package comp2449.cwk1;

/************************************
*									*
*	Marcus McFarlane				*
*	Networking and IT Management	*
*	University of Leeds				*
*									*
*************************************/


import java.util.Properties;

import javax.mail.*;

	import java.util.Properties;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;


/**
 * A send mail class for creating a connection and sending an email 
 * to a specified email address.
 *
 *emailAdd - String variable containing the recipients email address.
 *message - String variable containing the message.
 */
public class SendEmail{
		
	private String emailAdd;
	private String message;
		
	/**
	 * Class constructor method.
	 * 
	 * @param emailAddress
	 * @param messageAdd
	 */
	public SendEmail(String emailAddress, String messageAdd){	
		emailAdd = emailAddress;
		message = messageAdd;
		
		//Testing to see if the class is reading the relevant information from the GUI text 
		//boxes 'email' and 'message', by outputting the values.
		//System.out.println(emailAdd + "   " + message);		
	}		
	
	
	/**
	 * Method that is used to send an email to a specified email address.
	 * 
	 * @param address
	 * @param message
	 */
	public static void sendingEmail(String address, String message){
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("marcusmcfarlane9101@gmail.com","University91");
				}
			});
		
		try {
		
			Message message1 = new MimeMessage(session);
			message1.setFrom(new InternetAddress("sc14mdm@leeds.ac.uk"));
			message1.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(address));
			message1.setSubject("Testing Subject");
			message1.setText(message);
			// line where the message comes through
			Transport.send(message1);
		
			System.out.println("Done");
		} 
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
