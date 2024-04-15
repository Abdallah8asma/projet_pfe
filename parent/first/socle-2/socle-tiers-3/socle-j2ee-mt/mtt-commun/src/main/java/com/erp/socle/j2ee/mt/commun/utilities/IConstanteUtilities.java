package com.erp.socle.j2ee.mt.commun.utilities;

public interface IConstanteUtilities {
	
	  /** Prefixe de tous les package de l'appli. */
	  String PACKAGE_NAME = "com.erp";
	  /** Suffixe des classes implémentations. */
	  String IMPL_SUFFIX = "Impl";

	  /** Erreur dans l''acces a la base de donnees : {0}. */
	  String ERR_APPEL_BD = "mt.commun.err.100";
	  /** Erreur de conccurrence d'accès. */
	  String ERR_CONCURRENCE_ACCES = "mt.commun.err.102";
	  /** Erreur de conccurrence d'accès. */
	  String ERR_NOT_ENTITY = "mt.commun.err.103";
	  /** Erreur dans l''acces au service : {0}. */
	  String ERR_APPEL_SERVICE = "mt.commun.err.200";
	  /** Erreur Générale, Veuillez prendre contact avec votre administrateur. */
	  String ERR_GENERALE_SERVEUR = "mt.commun.err.500";

	  /**
	   * La variable de session représentant l'identifiant present dans le header HTTP qui transmet les
	   * infos au MAS.
	   */
	  String HTTP_HEADER = "generic_id";

}
