package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.enchere.bo.Utilisateur;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!Utilisateur.doFilter(request, response)) {
			response.sendRedirect(request.getContextPath() + "/Login");
		} else {
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

			// Check if user object is not null
			if (user != null) {
				// Set the user data as a request attribute like this you can have access to the
				// user data.
				request.setAttribute("user", user);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profile.jsp");
				rd.forward(request, response);
			} else {
				// If there's an error with the session and you can't find the user Object then
				// show this message error.
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User object not found in session");
			}
		}
	}
}
