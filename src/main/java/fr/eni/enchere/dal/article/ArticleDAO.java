package fr.eni.enchere.dal.article;

import java.util.ArrayList;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.error.BusinessException;

public interface ArticleDAO {
	
	public void insert( Article article ) throws BusinessException;

	public ArrayList<Article> getAllCategories(String search, String categorie);

	public Article getArticleById(int id);
	

}
