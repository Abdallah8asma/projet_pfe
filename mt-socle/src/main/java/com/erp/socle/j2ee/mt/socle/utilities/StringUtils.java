package com.erp.socle.j2ee.mt.socle.utilities;

/**
 * Classe utilitaire permettant de manipuler des objets String
 * 
 * @author rkhaskhoussy
 */
public final class StringUtils {

	/**
	 * Ne pas instancier
	 */
	private StringUtils() {
	}

	/**
	 * Convertit un tableau de bytes en String
	 * 
	 * @param tabBytes
	 *            un tableau de bytes
	 * @return un objet String
	 */
	public static String byteArrayToString(final byte[] tabBytes) {
		StringBuilder hashString = new StringBuilder();
		for (int i = 0; i < tabBytes.length; ++i) {
			String hex = Integer.toHexString(tabBytes[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			} else {
				hashString.append(hex.substring(hex.length() - 2));
			}
		}
		return hashString.toString();
	}

	/**
	 * Cette méthode permet de supprimer les espaces [ \t\n\x0B\f\r] en début de
	 * chaine.
	 * 
	 * @param source
	 *            chaine dont il faut supprimer les espaces à gauche
	 * @return la chaine sans espaces en début
	 */
	public static String ltrim(final String source) {
		return source.replaceAll("^\\s+", "");
	}

	/**
	 * Cette méthode permet de supprimer les espaces [ \t\n\x0B\f\r] en fin de
	 * chaine.
	 * 
	 * @param source
	 *            chaine dont il faut supprimer les espaces à droite
	 * @return la chaine sans espaces à la fin
	 */
	public static String rtrim(final String source) {
		return source.replaceAll("\\s+$", "");
	}

	/**
	 * Retourne true si la chaine passée en paramètre est nulle ou vide, false
	 * sinon.
	 * 
	 * @param chaine
	 *            la chaine à tester
	 * @return true si la chaine est nulle ou vide, false sinon
	 */
	public static boolean isStringEmptyOrNull(final String chaine) {
		if ((chaine == null) || IConstantesUtilities.CHAINE_VIDE.equals(chaine)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Méthode permettant de remplacer les quotes par des doubles quotes pour
	 * permettre l'exécution de la requête SQL.
	 * 
	 * @param pChaine
	 *            la chaine de caractere à modifier
	 * @return le libelle pour la requête
	 */
	public static String gererApostrophe(final String pChaine) {
		String vRetour = null;

		if (pChaine != null) {
			vRetour = pChaine.replaceAll("'", "''");
		}
		return vRetour;
	}

	/**
	 * Cette méthode permet de compléter une chaine de caractère jusqu'a
	 * finalsize avec une lettre.<br>
	 * L'ajout se fait sur la gauche. Si la chaine fournie dépasse finalsize,
	 * elle est renvoyée telle quelle.
	 * 
	 * @param stringToComplete
	 *            chaine à compléter
	 * @param finalSize
	 *            taille finale de la chaine à obtenir
	 * @param letter
	 *            caractère à répéter n fois devant la chaine
	 * @return la chaine complétée par la gauche pour atteindre la taille
	 *         désirée
	 */
	public static String completerAGauche(final String stringToComplete,
			final int finalSize, final char letter) {
		return org.apache.commons.lang3.StringUtils.leftPad(stringToComplete,
				finalSize, letter);
	}

	/**
	 * Cette méthode permet de compléter une chaine de caractère jusqu'a
	 * finalsize avec une lettre.<br>
	 * L'ajout se fait sur la droite. Si la chaine fournie dépasse finalsize,
	 * elle est renvoyée telle quelle.
	 * 
	 * @param stringToComplete
	 *            chaine à compléter
	 * @param finalSize
	 *            taille finale de la chaine à obtenir
	 * @param letter
	 *            caractère à répéter n fois en fin de chaine
	 * @return la chaine complétée par la droite pour atteindre la taille
	 *         désirée
	 */
	public static String completerADroite(final String stringToComplete,
			final int finalSize, final char letter) {
		return org.apache.commons.lang3.StringUtils.rightPad(stringToComplete,
				finalSize, letter);
	}

	/**
	 * Méthode permettant la conversion en Majuscules
	 * 
	 * @param str
	 * @return
	 */
	public static String upperCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

}
