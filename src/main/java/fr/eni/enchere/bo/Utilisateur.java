package fr.eni.enchere.bo;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import fr.eni.enchere.bll.UtilisateurManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Utilisateur {

	private Integer no_utilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String code_postal;
	private String ville;
	private String mot_de_passe;
	private int credit;
	private byte administrateur;
	private Set<UtilisateurAuthToken> utilisateurAuthTokens = new HashSet<>(0);

	// Constructor
	public Utilisateur() {
	}

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe, int credit, Byte admin) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = hashPwd(mot_de_passe);
		this.credit = credit;
		this.administrateur = admin;
	}

	public Utilisateur(Integer no_utilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String mot_de_passe, int credit, byte administrateur) {
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public Utilisateur(Integer no_utilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville) {
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	// get/set
	public Integer getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(Integer no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public byte getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(byte administrateur) {
		this.administrateur = administrateur;
	}

	public Set<UtilisateurAuthToken> getUtilisateurAuthTokens() {
		return utilisateurAuthTokens;
	}

	public void setUtilisateurAuthTokens(Set<UtilisateurAuthToken> utilisateurAuthTokens) {
		this.utilisateurAuthTokens = utilisateurAuthTokens;
	}

	public static String hashPwd(String motDePasse) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		md.update(motDePasse.getBytes());
		byte byteData[] = md.digest();

		// convertir le tableau en chaine
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public static String getRandomStr(int n) {
		// choisissez un caractére au hasard à partir de cette chaîne
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz" + "1234567890";

		StringBuilder s = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int index = (int) (str.length() * Math.random());
			s.append(str.charAt(index));
		}
		return s.toString();
	}

	public static boolean doFilter(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		boolean loggedIn = session != null && session.getAttribute("user") != null;

		Cookie[] cookies = request.getCookies();

		if (!loggedIn && cookies != null) {
			// process auto Login for remember me feature
			String selector = "";
			String rawValidator = "";

			for (Cookie aCookie : cookies) {
				if (aCookie.getName().equals("selector")) {
					selector = aCookie.getValue();
				} else if (aCookie.getName().equals("validator")) {
					rawValidator = aCookie.getValue();
				}
			}

			if (!"".equals(selector) && !"".equals(rawValidator)) {
				UtilisateurManager um = new UtilisateurManager();
				UtilisateurAuthToken token = um.findBySelector(selector);

				if (token != null) {
					String hashedValidatorDatabase = token.getValidator();
					String hashedValidatorCookie = Utilisateur.hashPwd(rawValidator);

					if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
						Utilisateur user = um.getUserById(String.valueOf(token.getNo_utilisateur()));

						session = request.getSession();
						session.setAttribute("user", user);

						// update new token in database
						String newSelector = Utilisateur.getRandomStr(12);
						String newRawValidator = Utilisateur.getRandomStr(64);

						String newHashedValidator = Utilisateur.hashPwd(newRawValidator);

						token.setSelector(newSelector);
						token.setValidator(newHashedValidator);
						um.updateAuth(token);

						// update cookie
						Cookie cookieSelector = new Cookie("selector", newSelector);
						Cookie cookieValidator = new Cookie("validator", newRawValidator);
						cookieSelector.setMaxAge(604800);
						cookieSelector.setMaxAge(604800);

						response.addCookie(cookieSelector);
						response.addCookie(cookieValidator);
						return true;
					}
				}
			}
		}
		return loggedIn;
	}
}