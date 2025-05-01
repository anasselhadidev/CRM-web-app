package facebook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FacebookLoginServlet")
public class FacebookLoginServlet extends HttpServlet {
    private static final String APP_ID = "988271613355761"; // Remplacez par votre App ID réel
    private static final String REDIRECT_URI = "http://localhost:8080/CRM/CallbackServlet";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String facebookLoginURL = "https://www.facebook.com/v16.0/dialog/oauth?" +
                "client_id=" + APP_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&scope=pages_read_engagement,pages_manage_posts";

        response.sendRedirect(facebookLoginURL);
    }
}
