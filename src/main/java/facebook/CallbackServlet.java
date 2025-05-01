package facebook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

@WebServlet("/CallbackServlet")
public class CallbackServlet extends HttpServlet {
    private static final String APP_ID = "988271613355761"; // Remplacez par votre App ID
    private static final String APP_SECRET = "adf3a87ee3d402d9c619678bd96c3109"; // Remplacez par votre App Secret
    private static final String REDIRECT_URI = "http://localhost:8080/CRM/callback";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code != null) {
            String tokenURL = "https://graph.facebook.com/v16.0/oauth/access_token?" +
                    "client_id=" + APP_ID +
                    "&redirect_uri=" + REDIRECT_URI +
                    "&client_secret=" + APP_SECRET +
                    "&code=" + code;

            try {
                // Récupération de l'access token depuis l'URL
                URL url = new URL(tokenURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder jsonResponse = new StringBuilder();

                while (scanner.hasNext()) {
                    jsonResponse.append(scanner.nextLine());
                }
                scanner.close();

                // Parsing de la réponse JSON
                JSONObject json = new JSONObject(jsonResponse.toString());
                String accessToken = json.getString("access_token");

                // Stocker l'access token dans la session
                HttpSession session = request.getSession();
                session.setAttribute("accessToken", accessToken);

                // Redirection vers la page de succès (ou de statistique)
                response.sendRedirect("callback.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
