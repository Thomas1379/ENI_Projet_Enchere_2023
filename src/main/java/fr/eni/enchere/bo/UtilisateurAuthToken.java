package fr.eni.enchere.bo;

public class UtilisateurAuthToken {

	private int id;
	private String selector;
	private String validator;
	private int no_utilisateur;

	// Constructor
	public UtilisateurAuthToken() {
	}

	public UtilisateurAuthToken(int id, String selector, String validator, int no_utilisateur) {
		this.id = id;
		this.selector = selector;
		this.validator = validator;
		this.no_utilisateur = no_utilisateur;
	}

	public UtilisateurAuthToken(String selector, String validator, int no_utilisateur) {
		this.selector = selector;
		this.validator = validator;
		this.no_utilisateur = no_utilisateur;
	}
	
	// get/set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}
}
