package réclamation;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ResponsesAgentServlet") // Mapping pour accéder à cette servlet
public class ResponsesAgentServlet extends HttpServlet {
    private ReclamationDAO reclamationDAO;

    @Override
    public void init() {
        // Initialisation de l'objet DAO via la factory
        reclamationDAO = new ReclamationDAOImpl(DAOFactory.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[DEBUG] Execution de doGet dans ResponsesAgentServlet.");

        // Récupération de la session
        HttpSession session = request.getSession(false); // Utiliser false pour ne pas créer une nouvelle session
        if (session == null) {
            System.out.println("[INFO] Pas de session active. Redirection vers login.jsp.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Vérification de l'attribut idAgent dans la session
        Object idAgentObj = session.getAttribute("idAgent");
        if (idAgentObj == null) {
            System.out.println("[INFO] ID d'agent introuvable dans la session. Redirection vers login.jsp.");
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int idAgent = Integer.parseInt(idAgentObj.toString());
            System.out.println("[DEBUG] ID d'agent trouvé : " + idAgent);

            // Récupération des réponses liées à l'agent
            List<ResponseBean> responses = reclamationDAO.getResponsesByAgent(idAgent);
            System.out.println("[DEBUG] Nombre de réponses récupérées : " + responses.size());

            // Passage des données à la JSP
            request.setAttribute("responses", responses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("responses.jsp");
            dispatcher.forward(request, response);
            System.out.println("[INFO] Forward réussi vers responses.jsp.");
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] Erreur de conversion de l'ID d'agent : " + e.getMessage());
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            System.err.println("[ERROR] Erreur inattendue dans doGet : " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
