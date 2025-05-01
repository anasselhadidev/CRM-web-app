package projet;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
@WebServlet("/testingemail")
public class TestEmailServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Replace with your SMTP server settings
    private final String SMTP_HOST = "smtp.gmail.com";  // Example: Gmail SMTP
    private final String SMTP_PORT = "465";  // TLS port
    private final String USERNAME = "abdoutrapswag@gmail.com";  // Your email address
    private final String PASSWORD = "qkkh aftq rqzr ubll";  // Your email password

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Print the response HTML
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Testing SMTP Email Sending</h1>");

        // Send test email
        try {
            sendTestEmail();
            out.println("<p>Email sent successfully!</p>");
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }

    private void sendTestEmail() throws MessagingException {
        // Set up properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST); // SMTP Host
        properties.put("mail.smtp.port", "465"); // SSL Port
        properties.put("mail.smtp.auth", "true"); // Enable Authentication
        properties.put("mail.smtp.ssl.enable", "true"); // Enable SSL (Use SSL for email)
        properties.put("mail.debug", "true"); // Enable debugging for detailed logs
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");


        // Get session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD); // Your Gmail credentials
            }
        });

        // Create a new email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME)); // Set the sender address
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("abdouzaza190@gmail.com")); // Set the recipient
        message.setSubject("Test Email from Servlet");
        message.setText("This is a test email sent from a Java Servlet!");

        try {
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();  // This will print detailed error messages
        }

    }

}
