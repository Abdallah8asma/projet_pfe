package com.gpro.consulting.tiers.logistique.coordination.gc;

/**
 * Constant Report parameters
 * 
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
public interface IConstanteCommercialeReport {

	public static final String REPORT_NAME_BONLIVRAISON = "bon de livraison";
	public static final String REPORT_NAME_FACTURE = "facture";
	public static final String REPORT_NAME_FACTURE_LIST = "factures";
	public static final String REPORT_NAME_BONLIVRAISON_LIST = "bons de livraison";
	public static final String REPORT_NAME_ECHEANCIER_LIST = "Echeancier Client";
	public static final String REPORT_NAME_FICHE_CLIENT = "Fiche Client";
	public static final String REPORT_NAME_FICHE_GROUPE = "Fiche Groupe";
	public static final String REPORT_NAME_RELEVE_CLIENT = "Relevé Client";
	public static final String REPORT_NAME_RELEVE_GROUPE = "Relevé Groupe";
	public static final String REPORT_NAME_SOLDE_CLIENT = "Solde Client";
	public static final String REPORT_NAME_SITUATION_REPORTING = "Situation Reporting";

	public static final String PATH_LOGO = "PATH_LOGO";
	public static final String LOGO_STIT = "/report/logoSTIT.png";

	public static final String PATH_JRXML_FICHE_CLIENT = "C://ERP/Lib/STIT_FicheClient/fiche_client_report.jrxml";
	public static final String PATH_JRXML_FICHE_GROUPE = "C://ERP/Lib/COM_FicheGroupe/fiche_groupe_report.jrxml";
	public static final String PATH_JRXML_RELEVE_CLIENT = "C://ERP/Lib/STIT_FicheClient/releve_client_report.jrxml";
	public static final String PATH_JRXML_RELEVE_GROUPE = "C://ERP/Lib/COM_FicheGroupe/releve_groupe_report.jrxml";
	public static final String PATH_JRXML_SOLDE_CLIENT = "C://ERP/Lib/STIT_SoldeClient/solde_client_List_report.jrxml";
	public static final String PATH_JRXML_SITUATION_REPORTING = "C://ERP/Lib/STIT_Reporting/situation/situation_report.jrxml";
	public static final String PATH_JRXML_SITUATION_ACHAT_REPORTING = "C://ERP/Lib/STIT_Reporting/situationAchat/situation_report.jrxml";

	public static final Long ZEROL = 0L;
	public static final Double ZEROD = 0D;
	public static final int FIRST_INDEX = 0;

	// ACHAT

	public static final String REPORT_NAME_FICHE_FOURNISSEUR = "Fiche Fournisseur";
	public static final String REPORT_NAME_RELEVE_FOURNISSEUR = "Relevé Fournisseur";

	public static final String PATH_JRXML_FICHE_FOURNISSEUR = "C://ERP/Lib/COM_Achat_FicheFournisseur/fiche_fournisseur_report.jrxml";
	public static final String PATH_JRXML_RELEVE_FOURNISSEUR = "C://ERP/Lib/COM_Achat_FicheFournisseur/releve_fournisseur_report.jrxml";

	public static final String PATH_JRXML_COM_Achat_SITUATION_REPORTING = "C://ERP/Lib/COM_Achat_Situation/situation_report.jrxml";

}
