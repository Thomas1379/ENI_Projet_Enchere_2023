package fr.eni.enchere.bo;

import java.time.LocalDateTime;

public class Enchere {
	
	int no_utilisateur;
	int no_article;
	LocalDateTime date_enchere;
	int montant_enchere;
	
	// Constructor
	public Enchere() {
	}
	
	public Enchere(int no_utilisateur, int no_article, int montant_enchere) {
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.montant_enchere = montant_enchere;
	}
	
	public Enchere(int no_utilisateur, int no_article, LocalDateTime date_enchere, int montant_enchere) {
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
	}

	// get/set
	public int getNo_utilisateur() {
		return no_utilisateur;
	}
	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}
	public int getNo_article() {
		return no_article;
	}
	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}
	public LocalDateTime getDate_enchere() {
		return date_enchere;
	}
	public void setDate_enchere(LocalDateTime date_enchere) {
		this.date_enchere = date_enchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
}
