package réclamation;

public class ResponseBean {
    private int idResponse;
    private int idReclamation;
    private int idAgent;
    private String responseText;
    private String responseDate;
    private String nomClient;
    private String emailClient;
    private String sujet;  
    private String description; 

    // Getters et setters
    public int getIdResponse() {
        return idResponse;
    }

    public void setIdResponse(int idResponse) {
        this.idResponse = idResponse;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
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
}

