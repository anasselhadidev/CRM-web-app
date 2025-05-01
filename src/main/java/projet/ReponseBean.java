package projet;

public class ReponseBean {
	private String texte;
	private int nbrvotes;
	
	public ReponseBean(String texte) {
		this.texte = texte;
		this.nbrvotes = 0;
	}
	
	public void setnbrvotes() {
		this.nbrvotes++; 
	}
	public void setTexte(String texte) {
		this.texte = texte; 
	}
	
	public int getNbrVotes() {
		return this.nbrvotes;
	}
	
	public String getTexte() {
		return this.texte;
	}
	
	
	
	
}
