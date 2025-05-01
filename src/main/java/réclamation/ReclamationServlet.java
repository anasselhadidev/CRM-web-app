package réclamation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ReclamationServlet")
public class ReclamationServlet extends HttpServlet {
    private ReclamationDAO reclamationDAO;

    @Override
    public void init() {
        reclamationDAO = new ReclamationDAOImpl(DAOFactory.getInstance());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object idClientObj = session.getAttribute("idClient");

        // Vérifiez si le client est connecté
        if (idClientObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            try {
                int idClient = Integer.parseInt(idClientObj.toString());
                String sujet = request.getParameter("sujet");
                String description = request.getParameter("description");

                // Créer une nouvelle réclamation
                ReclamationBean reclamation = new ReclamationBean(idClient, sujet, description, "En attente");
                reclamationDAO.createReclamation(reclamation);

                // Redirection après réussite
                response.sendRedirect("client_reclamation.jsp?success=true");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("client_reclamation.jsp?error=true");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object idClientObj = session.getAttribute("idClient");

        // Vérifiez si le client est connecté
        if (idClientObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("viewResponses".equals(action)) {
            try {
                int idClient = Integer.parseInt(idClientObj.toString());
                List<ResponseBean> responses = reclamationDAO.getResponsesByClientId(idClient);
                request.setAttribute("responses", responses);

                // Forward vers la JSP
                request.getRequestDispatcher("client_reclamation.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("client_reclamation.jsp?error=true");
            }
        }

    }
}
