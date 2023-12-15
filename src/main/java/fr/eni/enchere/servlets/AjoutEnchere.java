package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.error.BusinessException;

/**
 * Servlet implementation class Enchere
 */
public class AjoutEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ArticleManager am = new ArticleManager();
	private final UtilisateurManager um = new UtilisateurManager();
	private final CategorieManager cm = new CategorieManager();
	private final EnchereManager em = new EnchereManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/enchere.jsp");

		if (!Utilisateur.doFilter(request, response)) {
			response.sendRedirect(request.getContextPath() + "/Login");
		} else {

			int id = Integer.parseInt(request.getParameter("id"));
			Enchere enchere = null;

			Article article = am.getArticleById(id);

			Utilisateur user = um.getUserById(String.valueOf(article.getNo_utilisateur()));

			Categorie categorie = cm.getCategorieById(article.getNo_categorie());

			enchere = em.getEnchereById(article.getNo_article());

			String duration = null;
			if (article.getDate_debut_encheres() != null && article.getDate_fin_encheres() != null) {
				LocalDateTime now = LocalDateTime.now();
				duration = formatDuration(Duration.between(now, article.getDate_fin_encheres()));
			}

			request.setAttribute("article", article);
			request.setAttribute("user", user);
			request.setAttribute("categorie", categorie);
			request.setAttribute("enchere", enchere);
			request.setAttribute("duration", duration);

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

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/enchere.jsp");
		HttpSession session = request.getSession();

		if (!Utilisateur.doFilter(request, response)) {
			response.sendRedirect(request.getContextPath() + "/Login");
		} else {

			int id = Integer.parseInt(request.getParameter("id"));

			Article article = am.getArticleById(id);

			Utilisateur user = um.getUserById(String.valueOf(article.getNo_utilisateur()));

			Categorie categorie = cm.getCategorieById(article.getNo_categorie());

			request.setAttribute("article", article);
			request.setAttribute("user", user);
			request.setAttribute("categorie", categorie);

			String montant = request.getParameter("montant");

			Utilisateur buyer = (Utilisateur) session.getAttribute("user");

			try {

				Enchere montant_enchere = em.getEnchereById(article.getNo_article());
				if (montant_enchere == null) {
					montant_enchere = new Enchere(buyer.getNo_utilisateur(), article.getNo_article(), null, 0);
				}

				Enchere enchere = em.getEnchereById(article.getNo_article());
				request.setAttribute("enchere", enchere);
				enchere = em.insert(buyer.getNo_utilisateur(), article.getNo_article(), montant,
						article.getPrix_initial(), montant_enchere.getMontant_enchere());
				if (enchere.getDate_enchere() != null) {
					request.setAttribute("succes", "true");
				}

			} catch (BusinessException e) {
				request.setAttribute("codesError", e.getListeCodesErreur());
				rd.forward(request, response);
			}

			if (request.getAttribute("codesError") == null) {
				response.sendRedirect(request.getContextPath() + "/Enchere?id=" + article.getNo_article());
			}
		}
	}

	public static String formatDuration(Duration duration) {
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}
}
