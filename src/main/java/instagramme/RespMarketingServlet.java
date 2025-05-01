package instagramme;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/RespMarketingServlet")
public class RespMarketingServlet extends HttpServlet {
    private static final String ACCOUNT_ID = "1784147175569449"; // ID du compte Instagram codé en dur
    private InstagramDAO instagramDAO;

    @Override
    public void init() throws ServletException {
        instagramDAO = new InstagramDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accessToken = (String) session.getAttribute("accessToken");

        // Vérifiez si le responsable marketing a fourni un access token
        if (accessToken == null || accessToken.isEmpty()) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Récupérer les statistiques des publications
            List<InstagramPostStats> statsList = instagramDAO.getAllPostStats(ACCOUNT_ID);

            // Passer les données à la page JSP
            request.setAttribute("statsList", statsList);
            request.getRequestDispatcher("marketingHome.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des statistiques.");
        }
    }
}
