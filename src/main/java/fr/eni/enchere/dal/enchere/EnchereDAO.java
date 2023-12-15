package fr.eni.enchere.dal.enchere;

import fr.eni.enchere.bo.Enchere;

public interface EnchereDAO {

	void insert(Enchere enchere);

	Enchere getEnchereById(Integer no_article);

	void deleteById(Integer no_article);

}
