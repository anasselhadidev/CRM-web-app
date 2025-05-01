package facebook;



import facebook.MediaStatistics;
import facebook.InstagramDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/getStatistics")
public class InstagramServlet extends HttpServlet {
    private InstagramDAO instagramDAO = new InstagramDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mediaId = request.getParameter("mediaId");

        try {
            // Récupérer les statistiques
            MediaStatistics stats = instagramDAO.getMediaStatistics(mediaId);

            // Passer les statistiques à la vue
            request.setAttribute("stats", stats);
            request.getRequestDispatcher("/statistics.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des statistiques.");
        }
    }
}
