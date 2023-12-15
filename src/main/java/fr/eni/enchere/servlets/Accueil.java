package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class encheres
 */
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategorieManager cm = new CategorieManager();
	private final ArticleManager am = new ArticleManager();
	private final UtilisateurManager um = new UtilisateurManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/accueil.jsp");

		if (Utilisateur.doFilter(request, response)) {
			rd = request.getRequestDispatcher("/WEB-INF/views/accueilLogged.jsp");
		}
		// Récupération de toutes les catégorie
		ArrayList<Categorie> categories = cm.getAllCategories();

		request.setAttribute("categories", categories);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/accueil.jsp");

		if (Utilisateur.doFilter(request, response)) {
			rd = request.getRequestDispatcher("/WEB-INF/views/accueilLogged.jsp");
		}

		String search = request.getParameter("search");
		String categorie = request.getParameter("categorie");

		// Récupération de toutes les catégorie
		ArrayList<Categorie> categories = cm.getAllCategories();

		// Recupération des articles en fonction du nom et de la catégorie
		ArrayList<Article> articles = am.getAllArticlesByCategorie(search, categorie);

		// Récupération de tout les vendeur
		ArrayList<Utilisateur> users = um.selectAllSaufMDP();

		request.setAttribute("users", users);
		request.setAttribute("categories", categories);
		request.setAttribute("articles", articles);

		rd.forward(request, response);
	}
}
