package projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDAO {
	private DAOFactory  daoFactory;

    public UserDAO( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
	public void RegisterUser(UserBean user) throws DAOException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;


	    // SQL query to insert a new person
	    String sql = "INSERT INTO users (NomUser,PrenomUser,Password,UserEmail,Role,contact,adresse) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    String sqlClient = "INSERT INTO clients (idUser) VALUES (?)";
	    String sqlagentsupport = "INSERT INTO agentsupport (idUser) VALUES (?)";
	    String sqlrespmarketing = "INSERT INTO respmarketing (idUser) VALUES (?)";
	    String sqlrepcommercial = "INSERT INTO repcommercial  (idUser) VALUES (?)";

	    try {
	        // Get a connection from the DAOFactory
	        connection = daoFactory.getConnection();
	        // Prepare the statement
	        preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        System.out.println("User: " + user.getPrenom() + " " + user.getNom() + " has just registered at ; " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	      
	        // Set parameters from the PersonBean object
	        preparedStatement.setString(1, user.getNom());
	        preparedStatement.setString(2, user.getPrenom());
	        preparedStatement.setString(3, user.getPass());
	        preparedStatement.setString(4, user.getEmail());
	        preparedStatement.setString(5, user.getRole());
	        preparedStatement.setString(6, user.getContact());
	        preparedStatement.setString(7, user.getAdresse());
	        
	        preparedStatement.executeUpdate();
	        
	        resultSet = preparedStatement.getGeneratedKeys();
	        if (resultSet.next()) {
	            int userId = resultSet.getInt(1);

	            // If role is client, insert into the client table
	            if ("Client".equalsIgnoreCase(user.getRole())) {
	                PreparedStatement clientStatement = connection.prepareStatement(sqlClient);
	                clientStatement.setInt(1, userId);
	                clientStatement.executeUpdate();
	                clientStatement.close();
	            }
	            if ("agentsupport".equalsIgnoreCase(user.getRole())) {
	                PreparedStatement clientStatement = connection.prepareStatement(sqlagentsupport);
	                clientStatement.setInt(1, userId);
	                clientStatement.executeUpdate();
	                clientStatement.close();
	            }
	            if ("respmarketing".equalsIgnoreCase(user.getRole())) {
	                PreparedStatement clientStatement = connection.prepareStatement(sqlrespmarketing);
	                clientStatement.setInt(1, userId);
	                clientStatement.executeUpdate();
	                clientStatement.close();
	            }
	            if ("repcommercial".equalsIgnoreCase(user.getRole())) {
	                PreparedStatement clientStatement = connection.prepareStatement(sqlrepcommercial);
	                clientStatement.setInt(1, userId);
	                clientStatement.executeUpdate();
	                clientStatement.close();
	            }
	            
	        }else {
	            throw new DAOException("Failed to retrieve generated user ID.");
	        }
	    } catch (SQLException e) {
	        throw new DAOException("Error creating user", e);
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new DAOException("Error closing resources", e);
	        }
	    }
	}
	public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}
	public String Login(String email,String pass) throws DAOException {
		    String query = "SELECT Role FROM users WHERE Password= ? AND UserEmail= ?";
		    Connection connexion = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;

		    try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = daoFactory.getConnection();
		        preparedStatement = initRequestPrepare( connexion,query,pass,email);
		        resultSet = preparedStatement.executeQuery();
		        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
		        if ( resultSet.next() ) {
		        	return resultSet.getString(1);
		        }
		        else {
		        	return null;
		        }
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        //ClosingAll( resultSet, preparedStatement, connexion );
		    }
		}
	public void UpdateUser(UserBean user,String nom) throws DAOException {
		// TODO Auto-generated method stub
		
		
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    // SQL query to insert a new person
	    String sql = "UPDATE persons SET NOM=?,PRENOM=?,EMAIL=?,Password=? WHERE NOM=?";

	    try {
	        // Get a connection from the DAOFactory
	        connection = daoFactory.getConnection();
	        // Prepare the statement
	        preparedStatement = connection.prepareStatement(sql);
	        
	        // Set parameters from the PersonBean object
	        preparedStatement.setString(1, user.getNom());
	        preparedStatement.setString(2, user.getPrenom());
	        preparedStatement.setString(3, user.getEmail());
	        preparedStatement.setString(4, user.getPass());
	        preparedStatement.setString(5, nom);
	        
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DAOException("Error updating user", e);
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new DAOException("Error closing resources", e);
	        }
	    }

	}
	public void DeleteUser(String nom,String email) throws DAOException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "DELETE FROM USERS WHERE NOM=? AND USEREMAIL=?";

	    try {

	        connection = daoFactory.getConnection();

	        preparedStatement = connection.prepareStatement(sql);
	        
	        preparedStatement.setString(1, nom);
	        preparedStatement.setString(2, email);
	        
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DAOException("Error deleting user", e);
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new DAOException("Error closing resources", e);
	        }
	    }

	}
	public int getUserIdByEmail(String email) {
	    String sql = "SELECT idUser FROM users WHERE UserEmail = ?";
	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setString(1, email);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("idUser");
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }
	    throw new IllegalArgumentException("User with email " + email + " not found");
	}
	public int getIdClientByUserId(int idUser) {
	    String sql = "SELECT idClient FROM clients WHERE idUser = ?";
	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, idUser);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("idClient");
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }
	    return -1; // Retourne -1 si l'idClient n'est pas trouvé
	}
	public int getIdAgentsupportByUserId(int idUser) {
	    String sql = "SELECT idAgent FROM agentsupport WHERE idUser = ?";
	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, idUser);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("idAgent");
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }
	    return -1; // Retourne -1 si l'idClient n'est pas trouvé
	}
	public int getIdRespomarketingByUserId(int idUser) {
	    String sql = "SELECT idRespMarketing FROM respmarketing WHERE idUser = ?";
	    try (Connection connection = daoFactory.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, idUser);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("idRespMarketing");
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    }
	    return -1; // Retourne -1 si l'idClient n'est pas trouvé
	}


}
