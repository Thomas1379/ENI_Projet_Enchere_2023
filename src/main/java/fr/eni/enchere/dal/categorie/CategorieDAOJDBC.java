package fr.eni.enchere.dal.categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.ConnectionProvider;

public class CategorieDAOJDBC implements CategorieDAO {
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";

	@Override
	public ArrayList<Categorie> getAllCategories() {

		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_CATEGORIES);
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Categorie> categories = new ArrayList<Categorie>();
			Categorie categorie = null;
			while (rs.next()) {
				int no_categorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");

				categorie = new Categorie(no_categorie, libelle);
				categories.add(categorie);
			}
			conn.close();
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Categorie getCategorieById(int id) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SELECT_CATEGORIE_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			Categorie categorie = null;
			while (rs.next()) {
				int no_categorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");

				categorie = new Categorie(no_categorie, libelle);
			}
			conn.close();
			return categorie;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
