package fr.eni.enchere.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UtilisateurManager um = new UtilisateurManager();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		try {
			if (user != null && user.getNo_utilisateur() != null) {

				// Invalidate the session (log out)
				request.getSession().invalidate();

				um.deleteUser(user.getNo_utilisateur());

				request.getSession().setAttribute("successMessage", "Utilisateur supprimé avec succès!");

				// Redirect to the home page or login page
				response.sendRedirect(request.getContextPath() + "/Login");
			}
		} catch (Exception e) {
			// Log or handle the exception as needed
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
		}
	}

}
