package fr.eni.enchere.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UtilisateurManager userManager = new UtilisateurManager();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve user object from the session
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if (!Utilisateur.doFilter(request, response)) {
			response.sendRedirect(request.getContextPath() + "/Login");
		} else {
			// Check if user object is not null
			if (user != null) {
				// Retrieve form parameters
				String newPseudo = request.getParameter("pseudo");
				String newNom = request.getParameter("nom");
				String newPrenom = request.getParameter("prenom");
				String newEmail = request.getParameter("email");
				String newTelephone = request.getParameter("telephone");
				String newRue = request.getParameter("rue");
				String newCodePostal = request.getParameter("code_postal");
				String newVille = request.getParameter("ville");
				String newMotDePasse = request.getParameter("mot_de_passe");

				// Update user information in the user object
				user.setPseudo(newPseudo);
				user.setNom(newNom);
				user.setPrenom(newPrenom);
				user.setEmail(newEmail);
				user.setTelephone(newTelephone);
				user.setRue(newRue);
				user.setCode_postal(newCodePostal);
				user.setVille(newVille);

				if (newMotDePasse != null && !newMotDePasse.isEmpty()) {
					// Hash the new password
					String hashedPassword = Utilisateur.hashPwd(newMotDePasse);
					user.setMot_de_passe(hashedPassword);
				}

				// Update user in the database
				userManager.updateUser(user);

				// Save the updated user object back to the session
				request.getSession().setAttribute("user", user);

				// Redirect back to the profile page after the update
				response.sendRedirect(request.getContextPath() + "/Profile");
			} else {
				// Handle the case where the user object is not found in the session
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User object not found in session");
			}
		}
	}
}
