package instagramme;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/InstagramController")
public class InstagramController extends HttpServlet {
    private InstagramDAO instagramDAO;
    private static final String ACCOUNT_ID = "17841471755694499"; // ID du compte Instagram

    @Override
    public void init() throws ServletException {
        instagramDAO = new InstagramDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accessToken = request.getParameter("accessToken"); // Access token entré par l'utilisateur

        if (accessToken == null || accessToken.isEmpty()) {
            request.setAttribute("error", "Veuillez entrer un access token valide.");
            request.getRequestDispatcher("homerespomarketing.jsp").forward(request, response);
            return;
        }

        try {
            // Récupération des statistiques des publications
            List<InstagramPostStats> statsList = instagramDAO.getAllPostStats(ACCOUNT_ID, accessToken);

            // Stocker les statistiques dans la requête pour affichage
            request.setAttribute("statsList", statsList);

            // Forward vers la page des statistiques
            request.getRequestDispatcher("marketingHome.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la récupération des statistiques : " + e.getMessage());
            request.getRequestDispatcher("homerespomarketing.jsp").forward(request, response);
        }
    }
}
