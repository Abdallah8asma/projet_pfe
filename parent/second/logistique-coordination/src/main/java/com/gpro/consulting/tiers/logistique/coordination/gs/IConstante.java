package com.gpro.consulting.tiers.logistique.coordination.gs;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConstante.
 */
public interface IConstante {

	/************************* constante *******************************/
	public static String OPERATEUR_DEFFERENT = "<>";
	public static String OPERATEUR_SUPERIEUR = ">";
	public static String OPERATEUR_INFERIEUR = "<";
	public static String OPERATEUR_INF_EGAL = "<=";
	public static String OPERATEUR_SUP_EGALE = ">=";
	public static String OPERATEUR_EGALE = "=";
	public static String SEPARATEUR = "/";
	public static String VIDE = "";
	public static String TYPE_MVT_ENT = "ENTREE";
	public static String TYPE_MVT_SORT = "SORTIE";
	public static String TYPE_MVT_RES = "RESERVATION";
	/** ******************* table name **********************. */

	public static String TABLE_ARTICLE = "eb_article";

	/** The table document article. */
	public static String TABLE_DOCUMENT_ARTICLE = "eb_documentart";

	/** The table seuil. */
	public static String TABLE_SEUIL = "eb_seuil";

	/** The table site. */
	public static String TABLE_SITE = "pi_com_site";

	/** The table blachat. */
	public static String TABLE_BLACHAT = "gc_blachat";

	/** The table bon mouvement. */
	public static String TABLE_BON_MOUVEMENT = "gs_bonmouvement";

	/** The table inventaire. */
	public static String TABLE_INVENTAIRE = "gs_inventaire";

	/** The table emplacement. */
	public static String TABLE_EMPLACEMENT = "gs_emplacement";

	/** The table entite stock. */
	public static String TABLE_ENTITE_STOCK = "gs_entitestock";

	/** The table detais inventaire. */
	public static String TABLE_DETAIS_INVENTAIRE = "gs_detailsinventaire";

	/** The table magasin. */
	public static String TABLE_MAGASIN = "gs_magasin";

	/** The table mouvement stock. */
	public static String TABLE_MOUVEMENT_STOCK = "gs_mouvement";

	/** The table raison mouvement. */
	public static String TABLE_RAISON_MOUVEMENT = "gs_raisonmouvement";

	/** The table bon stock. */
	public static String TABLE_BON_STOCK = "gs_bonstock";
	/** The table detail bon stock. */
	public static String TABLE_DET_BON_STOCK = "gs_det_bonstock";
	/** The table bon inventaire. */
	public static String TABLE_BON_INVENTAIRE = "gs_boninventaire";
	/** The table detail bon inventaire. */
	public static String TABLE_DET_BON_INVENTAIRE = "gs_det_boninventaire";

	/** The table bon TRANSFERT . */
	public static String TABLE_BON_TRANSFERT = "gs_bontransfert";
	/** The table detail bon TRANSFERT . */
	public static String TABLE_DET_BON_TRANSFERT = "gs_det_bontransfert";

	/** ********************* sequence name **********************. */

	public static String SEQUENCE_ARTICLE = "eb_article_id_seq";

	/** The sequence document article. */
	public static String SEQUENCE_DOCUMENT_ARTICLE = "ear_seq";

	/** The sequence seuil. */
	public static String SEQUENCE_SEUIL = "esu_seq";

	/** The sequence site. */
	public static String SEQUENCE_SITE = "seq_pcs";

	/** The sequence blachat. */
	public static String SEQUENCE_BLACHAT = "seq_cla";

	/** The sequence bon mouvement. */
	public static String SEQUENCE_BON_MOUVEMENT = "seq_smv";

	/** The sequence inventaire. */
	public static String SEQUENCE_INVENTAIRE = "seq_sin";

	/** The sequence emplacement. */
	public static String SEQUENCE_EMPLACEMENT = "seq_sem";

	/** The sequence entite stock. */
	public static String SEQUENCE_ENTITE_STOCK = "seq_ses";

	/** The sequence detais inventaire. */
	public static String SEQUENCE_DETAIS_INVENTAIRE = "seq_sdi";

	/** The sequence magasin. */
	public static String SEQUENCE_MAGASIN = "seq_sma";

	/** The sequence mouvement stock. */
	public static String SEQUENCE_MOUVEMENT_STOCK = "seq_smv";

	/** The sequence raison mouvement. */
	public static String SEQUENCE_RAISON_MOUVEMENT = "seq_srm";

	/** The sequence bon stock. */
	public static String SEQUENCE_BON_STOCK = "seq_bonstock";

	/** The sequence det bon stock. */
	public static String SEQUENCE_DET_BON_STOCK = "seq_det_bonstock";

	/** The sequence bon inventaire. */
	public static String SEQUENCE_BON_INVENTAIRE = "seq_boninventaire";

	/** The sequence det bon inventaire. */
	public static String SEQUENCE_DET_BON_INVENTAIRE = "seq_det_boninventaire";

	/** The sequence bon transfert. */
	public static String SEQUENCE_BON_TRANSFERT = "seq_bontransfert";

	/** The sequence det bon transfert. */
	public static String SEQUENCE_DET_BON_TRANSFERT = "seq_det_bontransfert";

	public static String YES = "oui";
	public static String NO = "non";
	public static String ALL = "tous";

}