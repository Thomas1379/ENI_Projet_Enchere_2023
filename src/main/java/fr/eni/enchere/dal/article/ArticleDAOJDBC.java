package fr.eni.enchere.dal.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.dal.CodesErrorDAL;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.error.BusinessException;

public class ArticleDAOJDBC implements ArticleDAO {

	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, image, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";
	private static final String SELECT_ALL_ARTICLES_BY_CATEGORIES = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ? AND NOM_ARTICLE LIKE ?";
	private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	@Override
	public void insert(Article article) throws BusinessException {
		if(article == null ) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErrorDAL.INSERT_OBJET_NULL);
			throw be;
		}
		// récupération d'une connexion du pool de connexion
		try (Connection conn = ConnectionProvider.getConnection()) {
			// préparation de l'ajout dans la bdd
			PreparedStatement pstmt = conn.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNom_article());
			pstmt.setString(2, article.getDescription());
			pstmt.setString(3, article.getImage());
			pstmt.setTimestamp(4, Timestamp.valueOf(article.getDate_debut_encheres()));
			pstmt.setTimestamp(5, Timestamp.valueOf(article.getDate_fin_encheres()));
			pstmt.setInt(6, article.getPrix_initial());
			pstmt.setInt(7, article.getNo_utilisateur());
			pstmt.setInt(8, article.getNo_categorie());
			pstmt.executeUpdate();
			// récupération de ce que renvoi la bdd
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				// set le numéro de l'article pour la vérification dans le AjoutArticle.java
				article.setNo_article(rs.getInt(1));
			}

			// Fermeture de la connexion
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErrorDAL.INSERT_OBJET_ECHEC);
			throw be;
		}
	}

	@Override
	public ArrayList<Article> getAllCategories(String search, String categorie) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ARTICLES_BY_CATEGORIES);
			pstmt.setString(1, categorie);
			pstmt.setString(2, "%" + search + "%");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Article> articles = new ArrayList<Article>();
			Article article = null;
			while (rs.next()) {
				int no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				String description = rs.getString("description");
				String image = rs.getString("image");
				LocalDateTime date_debut_encheres = rs.getTimestamp("date_debut_encheres").toLocalDateTime();
				LocalDateTime date_fin_encheres = rs.getTimestamp("date_fin_encheres").toLocalDateTime();
				int prix_initial = rs.getInt("prix_initial");
				int prix_vente = rs.getInt("prix_vente");
				int no_utilisateur = rs.getInt("no_utilisateur");
				int no_categorie = rs.getInt("no_categorie");
				
				article = new Article(no_article, nom_article, description, image, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie);
				articles.add(article);
			}
			conn.close();
			return articles;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Article getArticleById(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SELECT_ARTICLE_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			Article article = null;
			if(rs.next()) {
				int no_article = id;
				String nom_article = rs.getString("nom_article");
				String description = rs.getString("description");
				String image = rs.getString("image");
				LocalDateTime date_debut_encheres = rs.getTimestamp("date_debut_encheres").toLocalDateTime();
				LocalDateTime date_fin_encheres = rs.getTimestamp("date_fin_encheres").toLocalDateTime();
				int prix_initial = rs.getInt("prix_initial");
				int prix_vente = rs.getInt("prix_vente");
				int no_utilisateur = rs.getInt("no_utilisateur");
				int no_categorie = rs.getInt("no_categorie");
				
				article = new Article(no_article, nom_article, description, image, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie);
			}
			conn.close();
			return article;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
