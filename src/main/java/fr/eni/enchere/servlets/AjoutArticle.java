package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.error.BusinessException;

/**
 * Servlet implementation class AjoutArticle
 */
public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategorieManager cm = new CategorieManager();
	private final ArticleManager am = new ArticleManager();
	BusinessException exception = new BusinessException();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutArticle.jsp");

		if (!Utilisateur.doFilter(request, response)) {
			response.sendRedirect(request.getContextPath() + "/Login");
		} else {
			// Récupération de toutes les catégorie
			ArrayList<Categorie> categories = cm.getAllCategories();

			request.setAttribute("categories", categories);
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutArticle.jsp");

		if (!Utilisateur.doFilter(request, response)) {
			response.sendRedirect(request.getContextPath() + "/Login");
		} else {

			try {
				HttpSession session = request.getSession();
				Utilisateur user = (Utilisateur) session.getAttribute("user");

				// Récupération de toutes les données en param
				String name = request.getParameter("article");
				String description = request.getParameter("description");
				String categorie = request.getParameter("categorie");
				String prix = request.getParameter("prix");
				String rue = request.getParameter("rue");
				String CP = request.getParameter("CP");
				String ville = request.getParameter("ville");
				String debut = request.getParameter("debut");
				String fin = request.getParameter("fin");

				int no_utilisateur = user.getNo_utilisateur();

				// Fonction insert dans la bdd
				Article article = am.insert(name, description, categorie, prix, rue, CP, ville, no_utilisateur, debut,
						fin);

				request.setAttribute("article", article);
			} catch (BusinessException e) {
				request.setAttribute("codesError", e.getListeCodesErreur());
			}
			// Récupération de toutes les catégorie
			ArrayList<Categorie> categories = cm.getAllCategories();

			request.setAttribute("categories", categories);

			rd.forward(request, response);
		}
	}
}