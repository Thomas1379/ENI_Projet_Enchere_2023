package fr.eni.enchere.bo;

public class Categorie {

	private Integer no_categorie;
	private String libelle;
	
	// Constructor
	public Categorie() {
	}

	public Categorie(Integer no_categorie, String libelle) {
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}

	// get/set
	public Integer getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(Integer no_categorie) {
		this.no_categorie = no_categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
