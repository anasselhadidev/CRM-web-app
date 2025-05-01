package projet;

public class UserBean {
	// Fields (matching the database columns in the query)
    private String nom;
    private String pass;
    private String email;
    private String prenom;
    private String role;
    private String contact;
    private String adresse;
    

    // Constructors
    public UserBean() {
    }

    public UserBean(String nom,String prenom,String email,String pass,String role,String contact,String adresse) {
        this.nom = nom;
        this.email = email;
        this.pass = pass;
        this.prenom = prenom;
        this.role = role;
        this.contact = contact;
        this.adresse = adresse;
    }

    // Getters and Setters for each field
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
    	return this.pass;
    }
    public String getPrenom() {
    	return this.prenom;
    }
    public String getRole() {
    	return this.role;
    }
    public void SetPass(String pass) {
    	this.pass = pass;
    }
    public void SetPrenom(String prenom) {
    	this.prenom = prenom;
    }
    public void SetRole(String role) {
    	this.role = role;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
