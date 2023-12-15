package fr.eni.enchere.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.UtilisateurAuthToken;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.utilisateur.UtilisateurDAO;
import fr.eni.enchere.dal.utilisateur.UtilisateurDAOJDBC;
import fr.eni.enchere.error.BusinessException;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur ajouter(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String CP, String ville, String psw, String pswconfirm, int credit, byte admin) throws BusinessException {
		BusinessException exception = new BusinessException();
		this.VerifSamePassword(psw, pswconfirm, exception);
		this.verifNull(pseudo, prenom, CP, psw, nom, email, rue, ville, exception);
		this.selectPseudoByPseudo(pseudo, exception);
		this.selectEmailByEmail(email, exception);
		this.verifRegexPseudo(pseudo, exception);
		Utilisateur u = null;

		if (!exception.hasErreurs()) {
			u = new Utilisateur(pseudo, nom, prenom, email, tel, rue, CP, ville, psw, credit, admin);
			this.utilisateurDAO.insert(u);

		}
		if (exception.hasErreurs()) {
			throw exception;
		}
		return u;
	}

	public Utilisateur getUserDetails(String identifiant, String password) throws BusinessException {
		BusinessException exception = new BusinessException();

		password = Utilisateur.hashPwd(password);

		Utilisateur user = this.utilisateurDAO.getUserByEmail(identifiant);

		if (user == null) {
			user = this.utilisateurDAO.getUserByPseudo(identifiant);
		}

		this.verifIdentifiant(user, identifiant, password, exception);

		if (!exception.hasErreurs()) {
			return user;
		} else {
			throw exception;
		}
	}

	public ArrayList<Utilisateur> selectAllSaufMDP() {
		return this.utilisateurDAO.selectAllSaufMDP();
	}

	public Utilisateur getUserById(String userId) {
		return this.utilisateurDAO.getUserById(userId);
	}

	public void updateUser(Utilisateur user) {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBC();
		utilisateurDAO.update(user);
	}

	private void verifIdentifiant(Utilisateur user, String identifiant, String password, BusinessException exception) {
		if (user == null || !password.equals(user.getMot_de_passe())) {
			exception.ajouterErreur(CodesErrorBLL.IDENTIFIANT_MDP_ERROR);
		}
	}

  public void deleteUser(int userId) {
        utilisateurDAO.delete(userId);
    }

	public void setTokenAuth(String selector, String hashedValidator, Integer no_utilisateur) {

		UtilisateurAuthToken authToken = new UtilisateurAuthToken(selector, hashedValidator, no_utilisateur);
		utilisateurDAO.setTokenAuth(authToken);

	}

	public UtilisateurAuthToken findBySelector(String selector) {
		List<UtilisateurAuthToken> list = utilisateurDAO.findBySelector(selector);

		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	private void VerifSamePassword(String psw, String pswconfirm, BusinessException exception) {
		if (psw == null || !psw.equals(pswconfirm)) {
			exception.ajouterErreur(CodesErrorBLL.SAME_PASSWORD_ERROR);
		}
	}

	private void verifNull(String pseudo, String prenom, String CP, String psw, String nom, String email, String rue,
			String ville, BusinessException exception) {
		if (pseudo == null || pseudo.equals("") || prenom == null || prenom.equals("") || CP == null || CP.equals("")
				|| psw == null || psw.equals("") || nom == null || nom.equals("") || email == null || email.equals("")
				|| rue == null || rue.equals("") || ville == null || ville.equals("")) {
			exception.ajouterErreur(CodesErrorBLL.INPUT_EMPTY_ERROR);
		}
	}

	public void selectPseudoByPseudo(String pseudo, BusinessException exception) {
		String pseudoBDD = this.utilisateurDAO.selectPseudoByPseudo(pseudo);

		if (pseudo.equals(pseudoBDD)) {
			exception.ajouterErreur(CodesErrorBLL.CREATE_PSEUDO_ERROR);
		}

	}

	public void selectEmailByEmail(String email, BusinessException exception) {
		String emailBDD = this.utilisateurDAO.selectEmailByEmail(email);

		if (email.equals(emailBDD)) {
			exception.ajouterErreur(CodesErrorBLL.CREATE_EMAIL_ERROR);
		}
	}

	public void updateAuth(UtilisateurAuthToken token) {
		if(token != null) {
			this.utilisateurDAO.updateAuth(token);
		}
	}

	public void deleteAuth(int id) {
		this.utilisateurDAO.deleteAuth(id);
	}
	
	private void verifRegexPseudo(String pseudo, BusinessException exception) {
		Pattern pattern = Pattern.compile(("^[a-zA-Z0-9]*$"));
		Matcher matcher = pattern.matcher(pseudo);
		
		if(!matcher.matches()) {
			exception.ajouterErreur(CodesErrorBLL.PSEUDO_ERROR_ALPHA);
		}		
	}
}
