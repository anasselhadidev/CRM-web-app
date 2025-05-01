package facebook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@WebServlet("/InstagramCallbackServlet")
public class InstagramCallbackServlet extends HttpServlet {
    private static final String APP_ID = "988271613355761"; // Remplacez par votre App ID réel
    private static final String APP_SECRET = "adf3a87ee3d402d9c619678bd96c3109"; // Remplacez par votre App Secret
    private static final String REDIRECT_URI = "http://localhost:8080/CRM/InstagramCallbackServlet";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            response.getWriter().println("Erreur : Code d'autorisation manquant !");
            return;
        }

        String tokenURL = "https://api.instagram.com/oauth/access_token";
        String params = "client_id=" + APP_ID +
                        "&client_secret=" + APP_SECRET +
                        "&grant_type=authorization_code" +
                        "&redirect_uri=" + REDIRECT_URI +
                        "&code=" + code;

        URL url = new URL(tokenURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = connection.getOutputStream()) {
            os.write(params.getBytes());
            os.flush();
        }

        int status = connection.getResponseCode();
        if (status == 200) {
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder responseContent = new StringBuilder();
            while (scanner.hasNext()) {
                responseContent.append(scanner.nextLine());
            }
            scanner.close();
            response.getWriter().println("Réponse : " + responseContent.toString());
        } else {
            Scanner scanner = new Scanner(connection.getErrorStream());
            StringBuilder errorResponse = new StringBuilder();
            while (scanner.hasNext()) {
                errorResponse.append(scanner.nextLine());
            }
            scanner.close();
            response.getWriter().println("Erreur : " + errorResponse.toString());
        }
    }
}
