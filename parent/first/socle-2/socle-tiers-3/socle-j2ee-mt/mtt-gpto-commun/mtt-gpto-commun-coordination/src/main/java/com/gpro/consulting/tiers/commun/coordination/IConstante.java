package com.gpro.consulting.tiers.commun.coordination;

public interface IConstante {

	/********* Partie Interesse Constante **********/

	public static Long PI_TYPE_EXONORE = 2L;
	public static Long PI_FAMILLE_CLIENT = 1L;
	public static Long PI_FAMILLE_FOURNISSEUR = 2L;

	/******************** Constante **************************/
	public static String OUI = "oui";
	public static String NON = "non";
	public static String ALL = "tous";
	public static String TOUS = "tous";

	/********************* table name ***********************/
	/* Produit */
	public static String TABLE_DOCUMENT_PRODUIT = "eb_documentprod";
	public static String TABLE_FAMILLE_PRODUIT = "eb_familleprod";
	public static String TABLE_SUPER_FAMILLE_PRODUIT = "eb_superfamilleprod";
	public static String TABLE_PRODUIT = "eb_produit";
	public static String TABLE_PRODUIT_SERIALISABLE = "eb_produit_serialisable";
	public static String TABLE_SOUS_FAMILLE_PRODUIT = "eb_sousfamilleprod";

	// added on 25 03 2018
	public static String TABLE_EB_PRIX_CLIENT = "eb_prixclient";
	/* PartieInteressee */
	public static String TABLE_PARTIE_INTERESSEE = "pi_partieint";
	public static String TABLE_CATEHGORIE_PARTIE_INTERESSEE = "pi_categorie";
	public static String TABLE_GROUPE_CLIENT = "pi_groupe_client";
	public static String TABLE_TYPE_PARTIE_INTERESSEE = "pi_typepi";
	public static String TABLE_SITE_PARTIE_INTERESSEE = "pi_com_site";
	public static String TABLE_FAMILLE_PARTIE_INTERESSEE = "pi_famillepi";
	public static String TABLE_DOCUMENT = "pi_document";
	public static String TABLE_REPRESENTANT = "pi_representant";
	public static String TABLE_TYPE_DOCUMENT = "pi_com_typedoc";
	public static String TABLE_DEVISE = "pi_devise";
	public static String TABLE_REGION_PARTIE_INTERESSEE = "pi_region";
	public static String TABLE_COMPTE_COMPTABLE_PARTIE_INTERESSEE = "pi_compte_comptable";
	public static String TABLE_BANQUE_PARTIE_INTERESSEE = "pi_banque";
	/* Article */
	public static String TABLE_EB_DETPROD_ARTICLE = "eb_detprodarticle";
	public static String TABLE_EB_COULEUR = "eb_couleur";
	public static String TABLE_EB_GROSSEUR = "eb_grosseur";
	public static String TABLE_EB_MATIERE = "eb_matiere";
	public static String TABLE_EB_METRAGE = "eb_metrage";
	public static String TABLE_FAMILLE_ARTICLE = "eb_familleart";
	public static String TABLE_SEUIL = "eb_seuil";
	public static String TABLE_SOUS_FAMILLE = "eb_sousfamilleart";
	public static String TABLE_TYPE_ARTICLE = "eb_typearticle";
	public static String TABLE_UNITE = "eb_unite";
	public static String TABLE_ARTICLE = "eb_article";
	public static String TABLE_DOCUMENT_ARTICLE = "eb_documentart";

	public static String TABLE_EB_USER = "EB_USER";
	public static String TABLE_EB_ROLE = "eb_role";
	public static String TABLE_EB_BASEINFO = "EB_BASEINFO";
	public static String TABLE_EB_BOUTIQUE = "eb_boutique";
	public static String TABLE_EB_REMISE = "eb_remise";
	public static String TABLE_EB_PACKAGE = "eb_package";
	public static String TABLE_EB_DETAIL_PACKAGE = "eb_detpackage";
	public static String TABLE_EB_SOCIETE = "eb_societe";
	
	
	public static String TABLE_IMPRESSION_PRODUIT = "eb_impression";
	public static String TABLE_COMPTE_COMPTABLE = "eb_compte_comptable";
	public static String TABLE_OPTION_PRODUIT = "eb_option_produit";
	public static String TABLE_OPERATION_PRODUIT = "eb_operation_produit";
	public static String TABLE_ARTICLE_PRODUIT="eb_articleprod";
	public static String TABLE_OPTION_ARTICLE_PRODUIT="eb_option_articleprod";
	public static String TABLE_OPERATION_ARTICLE_PRODUIT="eb_operation_articleprod";
	
	public static String TABLE_EB_UTILS = "eb_utils";
	
	
	  /*Moule*/
	  public static String TABLE_MOULE = "eb_moule";
	  public static String SEQUENCE_MOULE="eml_seq";
	//
	// public static String TABLE_EB_PRODUITDEPOT="eb_produitdepot";

	/*********************** sequence name ***********************/
	/* Produit */
	public static String SEQUENCE_DOCUMENT_PRODUIT = "efp_seq";
	public static String SEQUENCE_FAMILLE_PRODUIT = "efp_seq";
	public static String SEQUENCE_PRODUIT = "epr_seq";
	public static String SEQUENCE_PRODUIT_SERIALISABLE = "epr_serialisable_seq";
	public static String SEQUENCE_SOUS_FAMILLE_PRODUIT = "esf_seq";
	public static String SEQUENCE_SUPER_FAMILLE_PRODUIT = "supefp_seq";
	public static String SEQUENCE_ARTICLE_PRODUIT="eap_seq";
	public static String SEQUENCE_OPTION_ARTICLE_PRODUIT="opt_eap_seq";
	public static String SEQUENCE_OPERATION_ARTICLE_PRODUIT="oper_eap_seq";

	/* PartieInterssée */
	public static String SEQUENCE_PARTIE_INTERESSEE = "seq_ppi";
	public static String SEQUENCE_FAMILLE_PARTIE_INTERESSEE = "seq_pfp";
	public static String SEQUENCE_TYPE_PARTIE_INTERESSEE = "seq_ptp";
	public static String SEQUENCE_CATEHGORIE_PARTIE_INTERESSEE = "seq_pcg";
	public static String SEQUENCE_GROUPE_CLIENT = "seq_pigcl";
	public static String SEQUENCE_SITE_PARTIE_INTERESSEE = "seq_pcs";
	public static String SEQUENCE_DOCUMENT = "seq_pdc";
	public static String SEQUENCE_REPRESENTANT = "seq_prp";
	public static String SEQUENCE_TYPE_DOCUMENT = "seq_pct";
	public static String SEQUENCE_DEVISE = "pdv_seq";
	public static String SEQUENCE_REGION_PARTIE_INTERESSEE = "pre_seq";
	public static String SEQUENCE_COMPTE_COMPTABLE_PARTIE_INTERESSEE = "pi_cc_seq";
	public static String SEQUENCE_BANQUE_PARTIE_INTERESSEE = "pi_bq_seq";

	/* Article */
	public static String SEQUENCE_EB_GROSSEUR = "egr_seq";
	public static String SEQUENCE_EB_MATIERE = "emt_seq";
	public static String SEQUENCE_EB_METRAGE = "emg_seq";
	public static String SEQUENCE_EB_COULEUR = "ecl_seq";
	public static String SEQUENCE_EB_PRODUIT_COULEUR = "epc_seq";
	public static String SEQUENCE_EB_TAILLE = "etl_seq";
	public static String SEQUENCE_EB_PRODUIT_TAILLE = "ept_seq";
	public static String SEQUENCE_EB_PHASE = "eph_seq";
	public static String SEQUENCE_EB_DETPROD_ARTICLE = "epa_seq";
	public static String SEQUENCE_ARTICLE = "eb_article_id_seq";
	public static String SEQUENCE_DOCUMENT_ARTICLE = "ear_seq";
	public static String SEQUENCE_FAMILLE_ARTICLE = "efa_seq";
	public static String SEQUENCE_SEUIL = "esu_seq";
	public static String SEQUENCE_SOUS_FAMILLE = "sfa_seq";
	public static String SEQUENCE_TYPE_ARTICLE = "eta_seq";
	

	public static String TABLE_EB_PRODUIT_COULEUR = "eb_produitcouleur";
	public static String TABLE_EB_PRODUIT_TAILLE = "eb_produittaille";

	public static String SEQUENCE_EB_USER = "EBUSR_SEQ";
	public static String SEQUENCE_EB_ROLE = "ebro_seq";
	public static String SEQUENCE_EB_BASEINFO = "EBBI_SEQ";
	public static String SEQUENCE_EB_BOUTIQUE = "EBQE_SEQ";
	public static String SEQUENCE_EB_REMISE = "eb_rem_SEQ";
	public static String SEQUENCE_EB_PACKAGE = "eb_pge_SEQ";
	public static String SEQUENCE_EB_DETAIL_PACKAGE = "eb_det_pge_SEQ";
	public static String SEQUENCE_EB_SOCIETE = "EBSOC_SEQ";
	
	
	public static String SEQUENCE_UNITE = "eun_seq";
	public static String SEQUENCE_IMPRESSION_PRODUIT = "eb_impr_seq";
	public static String SEQUENCE_COMPTE_COMPTABLE = "eb_cpt_com_seq";
	public static String SEQUENCE_OPTION_PRODUIT = "eb_op_prod_seq";
	public static String SEQUENCE_OPERATION_PRODUIT = "eb_oper_prod_seq";
	public static String SEQUENCE_EB_UTILS = "eb_util_seq";
	
	

	// added on 25 03 2018
	public static String SEQUENCE_EB_PRIXCLIENT = "pc_seq";

	//
	// public static String SEQUENCE_PRDE="prde_seq";

	public static String LOGO_BASE_URL = "C:/ERP/logos_clients/";

}
