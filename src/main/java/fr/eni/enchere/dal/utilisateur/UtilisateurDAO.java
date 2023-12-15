package fr.eni.enchere.dal.utilisateur;

import java.util.ArrayList;
import java.util.List;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.UtilisateurAuthToken;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur);

	public String selectPseudoByPseudo(String pseudo);

	public String selectEmailByEmail(String email);

	public Utilisateur getUserByEmail(String userMail);

	public Utilisateur getUserByPseudo(String identifiant);

	public ArrayList<Utilisateur> selectAllSaufMDP();

	public Utilisateur getUserById(String userId);

	public void update(Utilisateur user);

	public void delete(int userId);

	public List<UtilisateurAuthToken> findBySelector(String selector);

	public void setTokenAuth(UtilisateurAuthToken authToken);

	public void updateAuth(UtilisateurAuthToken token);

	public void deleteAuth(int id);

}
