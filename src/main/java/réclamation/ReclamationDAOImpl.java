package réclamation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;  
import java.util.ArrayList;  
import java.util.List;  

import java.io.IOException;

public class ReclamationDAOImpl implements ReclamationDAO {
    private DAOFactory daoFactory;

    public ReclamationDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void createReclamation(ReclamationBean reclamation) {
        String sql = "INSERT INTO reclamation (idClient, sujet, description, statut, dateReclamation) VALUES (?, ?, ?, ?, NOW())";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reclamation.getIdClient());
            ps.setString(2, reclamation.getSujet());
            ps.setString(3, reclamation.getDescription());
            ps.setString(4, reclamation.getStatut());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<ReclamationBean> getAllReclamations() {
    	String sql = "SELECT r.idReclamation AS id_reclamation, u.NomUser AS client_nom, u.UserEmail AS client_email, " +  
                "r.sujet, r.description, r.dateReclamation, r.statut " +  
                "FROM reclamation r " +  
                "JOIN clients c ON r.idClient = c.idClient " +  
                "JOIN users u ON c.idUser = u.idUser " +  // Ajout d'un espace ici
                "WHERE r.statut = 'En attente'";

        List<ReclamationBean> reclamations = new ArrayList<>();
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                // Création du bean avec les données récupérées
                ReclamationBean reclamation = new ReclamationBean();
                reclamation.setIdReclamation(rs.getInt("id_reclamation")); // Correction du nom de colonne
                reclamation.setNomClient(rs.getString("client_nom"));      // Ajout pour nom du client
                reclamation.setEmailClient(rs.getString("client_email"));  // Ajout pour email du client
                reclamation.setSujet(rs.getString("sujet"));
                reclamation.setDescription(rs.getString("description"));
                reclamation.setDateReclamation(rs.getDate("dateReclamation"));
                reclamation.setStatut(rs.getString("statut"));
                
                reclamations.add(reclamation);
            }
            
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des réclamations.", e);
        }
        
        return reclamations;
    }


    @Override
    public void respondToReclamation(int idReclamation, int idAgent, String responseText) {
        String sqlReclamation = "UPDATE reclamation SET statut = 'Répondu', idAgent = ? WHERE idReclamation = ?";
        String sqlResponse = "INSERT INTO response (idReclamation, idAgent, responseText) VALUES (?, ?, ?)";
        try (Connection connection = daoFactory.getConnection()) {
            try (PreparedStatement ps1 = connection.prepareStatement(sqlReclamation);
                 PreparedStatement ps2 = connection.prepareStatement(sqlResponse)) {
                connection.setAutoCommit(false);

                ps1.setInt(1, idAgent);
                ps1.setInt(2, idReclamation);
                ps1.executeUpdate();

                ps2.setInt(1, idReclamation);
                ps2.setInt(2, idAgent);
                ps2.setString(3, responseText);
                ps2.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DAOException(e);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    @Override
    public List<ResponseBean> getResponsesByClientId(int idClient) {
        List<ResponseBean> responses = new ArrayList<>();
        String sql = """
        	    SELECT u.NomUser, u.UserEmail, r.sujet, r.description, re.responseText, re.responseDate
        	    FROM response re
        	    JOIN reclamation r ON r.idReclamation = re.idReclamation
        	    JOIN agentsupport a ON a.idAgent = re.idAgent
        	    JOIN users u ON u.idUser = a.idUser
        	    WHERE r.idClient = ?;
        	""";


        try (Connection connection = daoFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idClient);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ResponseBean response = new ResponseBean();
                    response.setNomClient(rs.getString("NomUser"));
                    response.setEmailClient(rs.getString("UserEmail"));
                    response.setSujet(rs.getString("sujet"));
                    response.setDescription(rs.getString("description"));
                    response.setResponseText(rs.getString("responseText"));
                    response.setResponseDate(rs.getString("responseDate"));
                    responses.add(response);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return responses;
    }
    @Override
    public List<ResponseBean> getResponsesByAgent(int idAgent) {
        List<ResponseBean> responses = new ArrayList<>();
        String sql = "SELECT r.responseText, r.responseDate, u.NomUser, u.UserEmail, rec.sujet, rec.description " +
                     "FROM response AS r " +
                     "JOIN reclamation AS rec ON r.idReclamation = rec.idReclamation " +
                     "JOIN clients AS c ON rec.idClient = c.idClient " +
                     "JOIN users AS u ON c.idUser = u.idUser " +
                     "WHERE r.idAgent = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idAgent); // Passer l'ID de l'agent comme paramètre
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ResponseBean response = new ResponseBean();
                    response.setResponseText(rs.getString("responseText")); // Texte de la réponse
                    response.setResponseDate(rs.getString("responseDate")); // Date de la réponse
                    response.setNomClient(rs.getString("NomUser")); // Nom de l'utilisateur (client)
                    response.setEmailClient(rs.getString("UserEmail")); // Email de l'utilisateur (client)
                    response.setSujet(rs.getString("sujet")); // Sujet de la réclamation
                    response.setDescription(rs.getString("description")); // Description de la réclamation
                    responses.add(response); // Ajouter la réponse à la liste
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des réponses", e);
        }
        return responses;
    }

}

