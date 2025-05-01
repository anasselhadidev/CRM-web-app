package réclamation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ReclamationAgentServlet")
public class ReclamationAgentServlet extends HttpServlet {
    private ReclamationDAO reclamationDAO;

    @Override
    public void init() {
        reclamationDAO = new ReclamationDAOImpl(DAOFactory.getInstance());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object idAgentObj = session.getAttribute("idAgent");

        // Vérifiez si l'agent est connecté
        if (idAgentObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Récupérer toutes les réclamations
            List<ReclamationBean> reclamations = reclamationDAO.getAllReclamations();
            if (reclamations.isEmpty()) {
                // Si aucune réclamation n'est trouvée
                request.setAttribute("errorMessage", "Aucune réclamation trouvée.");
            }
            request.setAttribute("reclamations", reclamations);

            // Forward vers la page des réclamations pour l'agent
            request.getRequestDispatcher("agent_reclamation.jsp").forward(request, response);
        }
        finally {  
            // Code à exécuter après le bloc try/catch, si besoin pour libérer des ressources  
            // (dans ce cas précis, ce n'est pas nécessaire, mais reste dans l'exercice)  
        } 
    }
    
            // Enregistrez l'erreur et redirigez avec un message d'erreur général
            

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object idAgentObj = session.getAttribute("idAgent");

        // Vérifiez si l'agent est connecté
        if (idAgentObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("respond".equals(action)) {
            try {
                int idAgent = Integer.parseInt(idAgentObj.toString());
                int idReclamation = Integer.parseInt(request.getParameter("idReclamation"));
                String responseText = request.getParameter("responseText");

                // Ajouter une réponse à la réclamation
                reclamationDAO.respondToReclamation(idReclamation, idAgent, responseText);

                // Mettre à jour le statut de la réclamation

                // Rediriger après la réussite
                response.sendRedirect("ReclamationAgentServlet?success=true");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("ReclamationAgentServlet?error=true");
            }
        }
    }

}

