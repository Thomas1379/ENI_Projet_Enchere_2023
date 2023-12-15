package fr.eni.enchere.bo;

import java.time.LocalDateTime;

public class Article {
	Integer no_article;
	String nom_article;
	String description;
	String image;
	LocalDateTime date_debut_encheres;
	LocalDateTime date_fin_encheres;
	int prix_initial;
	int prix_vente;
	int no_utilisateur;
	int no_categorie;

	// Constructor
	public Article() {
	}

	public Article(String nom_article, String description, String image, LocalDateTime date_debut_encheres,
			LocalDateTime date_fin_encheres, int prix_initial, int no_utilisateur, int no_categorie) {
		this.nom_article = nom_article;
		this.description = description;
		this.image = image;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}

	public Article(Integer no_article, String nom_article, String description, String image,
			LocalDateTime date_debut_encheres, LocalDateTime date_fin_encheres, int prix_initial, int prix_vente,
			int no_utilisateur, int no_categorie) {
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.image = image;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}

	// get/set
	public Integer getNo_article() {
		return no_article;
	}

	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
	}

	public String getNom_article() {
		return nom_article;
	}

	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDateTime getDate_debut_encheres() {
		return date_debut_encheres;
	}

	public void setDate_debut_encheres(LocalDateTime date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}

	public LocalDateTime getDate_fin_encheres() {
		return date_fin_encheres;
	}

	public void setDate_fin_encheres(LocalDateTime date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}

	public int getPrix_initial() {
		return prix_initial;
	}

	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

	public int getPrix_vente() {
		return prix_vente;
	}

	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
}
