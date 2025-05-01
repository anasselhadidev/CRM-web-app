package projet;

import java.util.Date;

public class ReclamationBean {
	    private String sujet;
	    private String description;
	    private Date dateReclamation;
	    private String statut;

	    // Constructor
	    public ReclamationBean(String sujet, String description, Date dateReclamation, String statut) {
	        this.sujet = sujet;
	        this.description = description;
	        this.dateReclamation = dateReclamation;
	        this.statut = statut;
	    }

	    // Getters and Setters
	    public String getSujet() {
	        return sujet;
	    }

	    public void setSujet(String sujet) {
	        this.sujet = sujet;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Date getDateReclamation() {
	        return dateReclamation;
	    }

	    public void setDateReclamation(Date dateReclamation) {
	        this.dateReclamation = dateReclamation;
	    }

	    public String getStatut() {
	        return statut;
	    }

	    public void setStatut(String statut) {
	        this.statut = statut;
	    }

	

}
