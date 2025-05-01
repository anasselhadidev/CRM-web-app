package facebook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InstagramLoginServlet")
public class InstagramLoginServlet extends HttpServlet {
    private static final String APP_ID = "988271613355761"; // Remplacez par votre App ID réel
    private static final String REDIRECT_URI = "http://localhost:8080/CRM/InstagramCallbackServlet";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String instagramLoginURL = "https://api.instagram.com/oauth/authorize?" +
                "client_id=" + APP_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&scope=user_profile" +
                "&response_type=code";

        response.sendRedirect(instagramLoginURL);
    }
}

