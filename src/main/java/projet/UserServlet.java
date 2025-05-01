package projet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/reglog")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO UserDAO;
	private DAOFactory dao;
	public void init() {
	dao = DAOFactory.getInstance();
	UserDAO = new UserDAO(dao);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher("/Home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("create".equals(action)) {
		String	name = request.getParameter("nom");
		String  prenom = request.getParameter("prenom");
		String	pass = request.getParameter("pass");
		String	email = request.getParameter("email");
		String  role = request.getParameter("role");
		String  contact = request.getParameter("contact");
		String  adresse = request.getParameter("adresse");
		pass = PasswordClass.hash(pass);
		UserBean user = new UserBean(name,prenom,email,pass,role,contact,adresse);
		UserDAO.RegisterUser(user);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		if ("login".equals(action)) {
			String access;
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			pass = PasswordClass.hash(pass);
			if ("Client".equals(UserDAO.Login(email, pass))) {
			    String role = "Client";
			    HttpSession session = request.getSession(true);
			    int idUser = UserDAO.getUserIdByEmail(email); // Méthode pour récupérer l'idUser
			    int idClient = UserDAO.getIdClientByUserId(idUser);

			    if (idClient == -1) {
			        // Si aucun idClient trouvé, rediriger avec un message d'erreur
			        response.sendRedirect("login.jsp?error=clientNotFound");
			        return;
			    }

			    session.setAttribute("idUser", idUser);
			    session.setAttribute("idClient", idClient); // Stocker l'idClient
			    session.setAttribute("role", role);
			    session.setMaxInactiveInterval(600);

			    request.getRequestDispatcher("/client_reclamation.jsp?action=create").forward(request, response);
			}


			
			else if("agentsupport".equals(UserDAO.Login(email, pass))) {
				String role = "agentsupport";
				HttpSession session = request.getSession(true);
			    int idUser = UserDAO.getUserIdByEmail(email); // Méthode pour récupérer l'idUser
			    int idAgent = UserDAO.getIdAgentsupportByUserId(idUser);

			    if (idAgent == -1) {
			        // Si aucun idClient trouvé, rediriger avec un message d'erreur
			        response.sendRedirect("login.jsp?error=clientNotFound");
			        return;
			    }

			    session.setAttribute("idUser", idUser);
			    session.setAttribute("idAgent", idAgent); // Stocker l'idAgent
				session.setAttribute("role",role);
				session.setMaxInactiveInterval(600);
				response.sendRedirect(request.getContextPath() + "/ReclamationAgentServlet");
			}
			else if("respmarketing".equals(UserDAO.Login(email, pass))) {
				String role = "respmarketing";
				HttpSession session = request.getSession(true);
			    int idUser = UserDAO.getUserIdByEmail(email); // Méthode pour récupérer l'idUser
			    int idRespMarketing = UserDAO.getIdRespomarketingByUserId(idUser);

			    if (idRespMarketing == -1) {
			        // Si aucun idClient trouvé, rediriger avec un message d'erreur
			        response.sendRedirect("login.jsp?error=clientNotFound");
			        return;
			    }

			    session.setAttribute("idUser", idUser);
			    session.setAttribute("idRespMarketing", idRespMarketing); // Stocker l'idAgent
				session.setAttribute("role",role);
				session.setMaxInactiveInterval(600);
				response.sendRedirect(request.getContextPath() + "/InstagramController");
			}
			
			else {
				access = "Login Refused";
				request.setAttribute("access",access);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			};


			
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

}
