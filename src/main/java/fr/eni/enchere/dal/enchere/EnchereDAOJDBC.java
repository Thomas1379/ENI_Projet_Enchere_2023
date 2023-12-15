package fr.eni.enchere.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ConnectionProvider;

public class EnchereDAOJDBC implements EnchereDAO {

	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES(no_utilisateur, no_article, montant_enchere) VALUES(?,?,?);";
	private static final String GET_ENCHERE_BY_ID = "SELECT * FROM ENCHERES WHERE no_article = ?";
	private static final String DELETE_ENCHERE_BY_ID = "DELETE FROM ENCHERES WHERE no_article = ?";

	@Override
	public void insert(Enchere enchere) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, enchere.getNo_utilisateur());
			pstmt.setInt(2, enchere.getNo_article());
			pstmt.setInt(3, enchere.getMontant_enchere());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				enchere.setDate_enchere(rs.getTimestamp(1).toLocalDateTime());
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Enchere getEnchereById(Integer no_article) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(GET_ENCHERE_BY_ID);
			pstmt.setInt(1, no_article);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int no_utilisateur = rs.getInt("no_utilisateur");
				LocalDateTime date_enchere = rs.getTimestamp("date_enchere").toLocalDateTime();
				int montant_enchere = rs.getInt("montant_enchere");

				Enchere enchere = new Enchere(no_utilisateur, no_article, date_enchere, montant_enchere);
				return enchere;
			}

			conn.close();
			System.out.println("Select des encheres : succes");

		} catch (Exception e) {
			System.out.println("Select des encheres : echec");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteById(Integer no_article) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(DELETE_ENCHERE_BY_ID);
			pstmt.setInt(1, no_article);
			pstmt.executeUpdate();

			conn.close();
			System.out.println("Delete de l'enchere : succes");

		} catch (Exception e) {
			System.out.println("Delete de l'enchere : echec");
			e.printStackTrace();
		}
	}

}
