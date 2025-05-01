package projet;

public class QuestionBean {
	private String texte;
	private ReponseBean reponse;
	
	public QuestionBean(String texte,ReponseBean reponse) {
		this.texte = texte;
		this.reponse = reponse;
	}
	
	public void setReponse(ReponseBean reponse) {
		this.reponse = reponse; 
	}
	public void setTexte(String texte) {
		this.texte = texte; 
	}
	
	public ReponseBean getReponse() {
		return this.reponse;
	}
	
	public String getTexte() {
		return this.texte;
	}
	
}
