package fr.eni.enchere.dal.categorie;

import java.util.ArrayList;

import fr.eni.enchere.bo.Categorie;

public interface CategorieDAO {

	ArrayList<Categorie> getAllCategories();

	Categorie getCategorieById(int no_categorie);

}
