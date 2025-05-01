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

@WebServlet("/testingemail2")
public class TestEmailServlet2 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Replace with your SMTP server settings
    private final String SMTP_HOST = "smtp.gmail.com";  // Example: Gmail SMTP
    private final String SMTP_PORT = "465";            // SSL port
    private final String USERNAME = "abdoutrapswag@gmail.com";  // Your email address
    private final String PASSWORD = "qkkh aftq rqzr ubll";      // Your email password

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

    private void sendTestEmail() throws MessagingException, IOException {
        // Set up properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);  // SMTP Host
        properties.put("mail.smtp.port", SMTP_PORT); // SSL Port
        properties.put("mail.smtp.auth", "true");    // Enable Authentication
        properties.put("mail.smtp.ssl.enable", "true"); // Enable SSL
        properties.put("mail.debug", "true");        // Enable debugging for detailed logs
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Get session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD); // Your Gmail credentials
            }
        });

        // Load HTML content from a file
        String htmlFilePath = getServletContext().getRealPath("/test.html");
        String htmlContent = readHtmlFile(htmlFilePath);

        // Create a new email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME)); // Set the sender address
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("anasselhadi34@gmail.com")); // Set the recipient
        message.setSubject("Test Email with HTML Content");

        // Set email content as HTML
        message.setContent(htmlContent, "text/html");

        // Send the email
        Transport.send(message);
        System.out.println("Email sent successfully with HTML content!");
    }

    private String readHtmlFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }
}
