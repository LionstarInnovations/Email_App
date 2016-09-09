package comp2449.cwk1;

/************************************
*									*
*	Marcus McFarlane				*
*	Networking and IT Management	*
*	University of Leeds				*
*									*
*************************************/

// Imports
import javax.mail.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/**
 * GUI class that consists of GUI components, which enable the user to log into an e-mail account
 * and view their most recent e-mail.
 * The GUI also enables the user to create and send a message to a particular e-mail address.
 * 
 * loginBtn - JButton used to log the user into their e-mail account.
 * userText - JTextField used to input text for the login username.
 * passwrdText - JPasswordField password handler for inputting the login password.
 * emailAddText - JTextField for inputting the recipients email address.
 * messageText - JTextArea used for inputting the message to send to the recipient.
 * sendMessageBtn - JButton used to send the message to the recipients e-mail address.
 */
public class EmailAppGUI implements Runnable{
	
    // Reading Email
	private JButton loginBtn;
	private JTextField userText;
	private JPasswordField passwordText;
	// Sending Email
	private JTextField emailAddText;
	private JTextArea messageText;
	private JButton sendMessageBtn;

   
	/**
	 * The class creates an action listener containing 
	 * behaviours for the JButton 'loginBtn'.
	 */
	private class CListen implements ActionListener {
		public void actionPerformed( ActionEvent event ) {
    	    
		    String username = userText.getText();	         
		    String password = passwordText.getText();
		         
		    ReadEmailInbox reader = new ReadEmailInbox(username, password);	         
		    reader.readingEmailAdd();	
		}
	}
  
  
	/**
	 * The class creates an action listener containing
	 * behaviours for the JButton 'SendMessageBtn'.
	 */
	private class CListen2 implements ActionListener{	  
		public void actionPerformed( ActionEvent event ){
	        	      		
			//button.setText("I've been clicked!");
			sendMessageBtn.setText("Clcked");
			String emailAdd = emailAddText.getText();
		  
			String message = messageText.getText();
			SendEmail reader = new SendEmail(emailAdd, message);
		  
			reader.sendingEmail(emailAdd, message);
		}
	}
  
  
	/**
	 * Method that creates the objects for the GUI, and assign the relevant action listener.
	 */
	public void guiObjects(JPanel panel) {
      
		panel.setLayout(null);

		//------------------Receiving Mail

		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);
		
		loginBtn = new JButton("login");
		loginBtn.setBounds(10, 80, 80, 25);
		
		// Registering an instance of the event handler class
		// as a listener on the component.
		loginBtn.addActionListener(new CListen());
		panel.add(loginBtn);
		
		//----------------Sending Mail
		
		JLabel emailAddLabel = new JLabel("Email Add:");
		emailAddLabel.setBounds(10, 120, 80, 25);
		panel.add(emailAddLabel);
		
		emailAddText = new JTextField(20);
		emailAddText.setBounds(100, 120, 160, 25);
		panel.add(emailAddText);

		JLabel messageLabel = new JLabel("Message:");
		messageLabel.setBounds(10, 150, 80, 25);
		panel.add(messageLabel);
		
		messageText = new JTextArea(20, 20);
		messageText.setBounds(100, 150, 160, 100);
		panel.add(messageText);
		
		sendMessageBtn = new JButton("Send Message");
		sendMessageBtn.setBounds(10, 250, 80, 25);
		
		// Registering an instance of the event handler class
		// as a listener on the component.
	    sendMessageBtn.addActionListener(new CListen2());
		panel.add(sendMessageBtn);
	}
  
  
	/**
	 * Method that is initialised when the program is executed, and creates the JFrame.  
	 */
	public void run() {
	  
		JFrame frame = new JFrame("Email Login");
	    frame.setSize(400,500);
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	   
	    // Creating JPanel 
	    JPanel panel = new JPanel();
		frame.add(panel);
		guiObjects(panel);
		
	    frame.setVisible(true);
	}

  
	/**
	 * Main method executes the Runnable on the AWT event-dispatching thread.
	 * 
	 */
	public static void main( String[] args) {
		SwingUtilities.invokeLater( new EmailAppGUI() );  
	}
}

