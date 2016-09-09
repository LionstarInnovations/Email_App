package comp2449.cwk1;

/************************************
*									*
*	Marcus McFarlane				*
*	Networking and IT Management	*
*	University of Leeds				*
*									*
*************************************/



import java.util.*;
import javax.mail.*;


/**
 * A ReadingMail class for creating a connection to a specified
 * email address and reading the mail.
 *
 *username - String variable containing the email address used to login to the inbox.
 *password - String variable containing the password used to log into the inbox.
 */
public class ReadEmailInbox{
	
	private String username;
	private String password;
	
	
	/**
	 * Constructor method.
	 *  
	 * @param user
	 * @param passwd
	 */
	public ReadEmailInbox(String user, String passwd){
		
		username = user;
		password = passwd;
		
		// Testng reading in!
		System.out.println(username);
		System.out.println(password);
		
		readingEmailAdd();
	}
	
	
	/**
	 * Method used to read a recent e-mail from a specified email addresses inbox.	 * 
	 */
	public void readingEmailAdd(){
            
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Session session = Session.getInstance(props, null);
		    Store store = session.getStore();
		    store.connect("imap.gmail.com", 993, username, password);
		    Folder inbox = store.getFolder("INBOX");
		    //inbox.getmessagecount(1);
		    // java mail folder class argument for getting last 10 messages
		    // documentation for folder
		    // get message method
		    inbox.open(Folder.READ_ONLY);
		    Message msg = inbox.getMessage(inbox.getMessageCount());
		    Address[] in = msg.getFrom();
		    for (Address address : in) {
		        System.out.println("FROM:" + address.toString());
		    }
		    Multipart mp = (Multipart) msg.getContent();
		    BodyPart bp = mp.getBodyPart(0);
		    System.out.println("SENT DATE:" + msg.getSentDate());
		    System.out.println("SUBJECT:" + msg.getSubject());
		    System.out.println("CONTENT:" + bp.getContent());
		} 
		catch (Exception mex) {
		    mex.printStackTrace();
		}
	}
}