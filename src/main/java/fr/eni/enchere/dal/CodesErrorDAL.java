package fr.eni.enchere.dal;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesErrorDAL {

	/**
	 * Echec quand l'article est vide
	 */
	public static final int INSERT_OBJET_NULL = 30000;

	/**
	 * Echec SQL
	 */
	public static final int INSERT_OBJET_ECHEC = 30001;

}
