package fr.eni.enchere.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mysql.cj.util.StringUtils;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.dal.article.ArticleDAO;
import fr.eni.enchere.error.BusinessException;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	public Article insert(String name, String description, String categorie, String prix, String rue, String CP,
			String ville, int no_utilisateur, String debutTest, String finTest) throws BusinessException {
		BusinessException exception = new BusinessException();
		this.verifNull(name, description, categorie, prix, rue, CP, ville, no_utilisateur, debutTest, finTest,
				exception);
		this.verifDate(debutTest, finTest, exception);

		Article a = null;
		if (!exception.hasErreurs()) {
			LocalDateTime debut = LocalDateTime.parse(debutTest);
			LocalDateTime fin = LocalDateTime.parse(finTest);
			String image = null;
			// Création de l'article
			a = new Article(name, description, image, debut, fin, Integer.parseInt(prix), no_utilisateur,
					Integer.parseInt(categorie));
			this.articleDAO.insert(a);
		}

		if (exception.hasErreurs()) {
			throw exception;
		}
		return a;
	}

	private void verifDate(String debut, String fin, BusinessException exception) {
		if (!debut.equals("") || !fin.equals("")) {
			LocalDateTime debutTest = LocalDateTime.parse(debut);
			LocalDateTime finTest = LocalDateTime.parse(fin);
			if (debutTest.isAfter(finTest)) {
				exception.ajouterErreur(CodesErrorBLL.DATE_ERROR);
			}
		}
	}

	public ArrayList<Article> getAllArticlesByCategorie(String search, String categorie) {

		return this.articleDAO.getAllCategories(search, categorie);
	}

	private void verifNull(String name, String description, String categorie, String prix, String rue,
			String CP, String ville, int no_utilisateur, String debut, String fin, BusinessException exception) {
		if (name == null || name.equals("") || description == null || description.equals("") || categorie == null
				|| categorie.equals("") || rue == null || rue.equals("") || CP == null || CP.equals("") || ville == null
				|| ville.equals("") || prix == null || prix.equals("") || debut == null || debut.equals("")
				|| fin == null || fin.equals("")) {
			exception.ajouterErreur(CodesErrorBLL.INPUT_EMPTY_ERROR);
		}
		if (!StringUtils.isStrictlyNumeric(prix)) {
			exception.ajouterErreur(CodesErrorBLL.PRICE_EMPTY_ERROR);
		}
	}

	public Article getArticleById(int id) {
		return this.articleDAO.getArticleById(id);
	}
}