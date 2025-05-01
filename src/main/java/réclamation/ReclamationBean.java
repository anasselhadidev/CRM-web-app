package réclamation;

import java.util.Date;  

public class ReclamationBean {  
    private int idReclamation;  
    private int idClient;  
    private int idAgent;  
    private String sujet;  
    private String description;  
    private String statut;  
    private Date dateReclamation; 
    private String nomClient;
    private String emailClient;

    // Constructeur par défaut  
    public ReclamationBean() {  
    }  

    // Constructeur paramétré  
    public ReclamationBean(int idReclamation, int idClient, int idAgent, String sujet, String description, String statut, Date dateReclamation) {  
        this.idReclamation = idReclamation;  
        this.idClient = idClient;  
        this.idAgent = idAgent;  
        this.sujet = sujet;  
        this.description = description;  
        this.statut = statut;  
        this.dateReclamation = dateReclamation;  
    }  
    
    public ReclamationBean(int idClient, String sujet, String description, String statut) {
        this.idClient = idClient;
        this.sujet = sujet;
        this.description = description;
        this.statut = statut;
        this.dateReclamation = new Date(); // Initialisation de la date actuelle
    }
    // Getters et Setters  
    public int getIdReclamation() {  
        return idReclamation;  
    }  

    public void setIdReclamation(int idReclamation) {  
        this.idReclamation = idReclamation;  
    }  

    public int getIdClient() {  
        return idClient;  
    }  

    public void setIdClient(int idClient) {  
        this.idClient = idClient;  
    }  

    public int getIdAgent() {  
        return idAgent;  
    }  

    public void setIdAgent(int idAgent) {  
        this.idAgent = idAgent;  
    }  

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

    public String getStatut() {  
        return statut;  
    }  

    public void setStatut(String statut) {  
        this.statut = statut;  
    }  

    public Date getDateReclamation() {  
        return dateReclamation;  
    }  

    public void setDateReclamation(Date dateReclamation) {  
        this.dateReclamation = dateReclamation;  
    }  
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    @Override  
    public String toString() {  
        return "ReclamationBean{" +  
                "idReclamation=" + idReclamation +  
                ", idClient=" + idClient +  
                ", idAgent=" + idAgent +  
                ", sujet='" + sujet + '\'' +  
                ", description='" + description + '\'' +  
                ", statut='" + statut + '\'' +  
                ", dateReclamation=" + dateReclamation +  
                '}';  
    }  
}