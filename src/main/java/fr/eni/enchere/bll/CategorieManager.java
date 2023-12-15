package fr.eni.enchere.bll;

import java.util.ArrayList;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.categorie.CategorieDAO;
import fr.eni.enchere.dal.DAOFactory;

public class CategorieManager {
	
	private CategorieDAO categorieDAO;

	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}

	public ArrayList<Categorie> getAllCategories() {
		
		return this.categorieDAO.getAllCategories();
	}

	public Categorie getCategorieById(int no_categorie) {
		return this.categorieDAO.getCategorieById(no_categorie);
	}
	
}
