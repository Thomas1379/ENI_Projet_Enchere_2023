package fr.eni.enchere.dal;

import fr.eni.enchere.dal.article.ArticleDAO;
import fr.eni.enchere.dal.article.ArticleDAOJDBC;
import fr.eni.enchere.dal.categorie.CategorieDAO;
import fr.eni.enchere.dal.categorie.CategorieDAOJDBC;
import fr.eni.enchere.dal.enchere.EnchereDAO;
import fr.eni.enchere.dal.enchere.EnchereDAOJDBC;
import fr.eni.enchere.dal.utilisateur.UtilisateurDAO;
import fr.eni.enchere.dal.utilisateur.UtilisateurDAOJDBC;

public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJDBC();
	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJDBC();
	}

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJDBC();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJDBC();
	}

}
