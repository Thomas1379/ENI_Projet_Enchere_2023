package fr.eni.enchere.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.UtilisateurAuthToken;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UtilisateurManager um = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!Utilisateur.doFilter(request, response)) {
			response.sendRedirect(request.getContextPath() + "/Login");
		} else {
			request.getSession().removeAttribute("user");

			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				String selector = "";

				for (Cookie aCookie : cookies) {
					if (aCookie.getName().equals("selector")) {
						selector = aCookie.getValue();
					}
				}

				if (!selector.isEmpty()) {
					// delete token from database
					UtilisateurAuthToken token = um.findBySelector(selector);

					if (token != null) {
						um.deleteAuth(token.getNo_utilisateur());

						Cookie cookieSelector = new Cookie("selector", "");
						cookieSelector.setMaxAge(0);

						Cookie cookieValidator = new Cookie("validator", "");
						cookieValidator.setMaxAge(0);
						response.addCookie(cookieSelector);
						response.addCookie(cookieValidator);
					}
				}
			}

			// Redirect vers la page d'Accueil
			response.sendRedirect(request.getContextPath() + "/Login");
		}
	}
}
