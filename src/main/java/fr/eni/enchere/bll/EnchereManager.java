package fr.eni.enchere.bll;

import com.mysql.cj.util.StringUtils;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.enchere.EnchereDAO;
import fr.eni.enchere.error.BusinessException;

public class EnchereManager {

	private EnchereDAO enchereDAO;

	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}

	public Enchere insert(Integer no_utilisateur, Integer no_article, String montant, int prix_initial,
			int montant_enchere) throws BusinessException {
		BusinessException exception = new BusinessException();
		boolean bool = this.verifNull(montant, exception);
		if (bool) {
			this.verifPrice(Integer.parseInt(montant), prix_initial, montant_enchere, exception);
		}

		if (!exception.hasErreurs()) {
			Enchere enchere = new Enchere(no_utilisateur, no_article, Integer.parseInt(montant));
			
			if(montant_enchere != 0) {
				this.enchereDAO.deleteById(no_article);
			}
			this.enchereDAO.insert(enchere);
			return enchere;
		}

		if (exception.hasErreurs()) {
			throw exception;
		}
		return null;
	}

	public Enchere getEnchereById(Integer no_article) {
		return this.enchereDAO.getEnchereById(no_article);
	}

	private boolean verifNull(String montant, BusinessException exception) {
		if (montant == null || montant.equals("")) {
			exception.ajouterErreur(CodesErrorBLL.INPUT_EMPTY_ERROR);
			return false;
		}
		if (!StringUtils.isStrictlyNumeric(montant)) {
			exception.ajouterErreur(CodesErrorBLL.PRICE_EMPTY_ERROR);
			return false;
		}
		return true;
	}

	private void verifPrice(int montant, int prix_initial, int montant_enchere, BusinessException exception) {
		if (montant <= montant_enchere || montant < prix_initial) {
			exception.ajouterErreur(CodesErrorBLL.PRICE_ERROR);
		}
	}
}
