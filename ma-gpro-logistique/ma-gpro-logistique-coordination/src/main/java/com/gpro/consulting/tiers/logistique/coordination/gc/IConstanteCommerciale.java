package com.gpro.consulting.tiers.logistique.coordination.gc;

/**
 * Interface présentant les noms des tables, des séquences, des constantes ...
 * utilisées dans le module Commerciale
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IConstanteCommerciale {

	/** Liste des Tables */
	public static String TABLE_GC_DETCOMPTOIRVENTE = "GC_DETCOMPTOIRVENTE";
	public static String TABLE_GC_COMPTOIRVENTE = "GC_COMPTOIRVENTE";
	public static String TABLE_GC_DOCUMENTS_LIVRAISONVENTE = "GC_DOCUMENTS_LIVRAISONVENTE";
	public static String TABLE_GC_DETLIVRAISONVENTE = "GC_DETLIVRAISONVENTE";
	public static String TABLE_GC_LIVRAISONVENTE = "GC_LIVRAISONVENTE";
	public static String TABLE_GC_TAXE = "GC_TAXE";
	public static String TABLE_GC_TAXELIVRAISON = "GC_TAXELIVRAISON";
	public static String TABLE_GC_TAXE_RECEPTION_ACHAT = "GC_TAXE_RECEPTION_ACHAT";
	public static String TABLE_GC_TAXEBCOMMANDE = "GC_TAXEBCOMMANDE";

	public static String TABLE_GC_MODEPAIEMENT = "GC_MODEPAIEMENT";
	public static String TABLE_GC_MARCHE = "GC_MARCHE";

	public static String TABLE_GC_DOCUMENTS_FACTURE_VENTE = "GC_DOCUMENTS_FACTUREVENTE";
	public static String TABLE_GC_DETFACTUREVENTE = "GC_DETFACTUREVENTE";
	public static String TABLE_GC_FACTUREVENTE = "GC_FACTUREVENTE";
	public static String TABLE_GC_TAXEFACTURE = "GC_TAXEFACTURE";

	public static String TABLE_GC_RAISON_FACTURE = "gc_raisonfacture";

	public static String TABLE_GC_DOCUMENTS_REGLEMENT = "GC_DOCUMENTS_REGLEMENT";
	public static String TABLE_GC_DETAILSREGLEMENT = "GC_DETAILSREGLEMENT";
	public static String TABLE_GC_ELEMENTREGLEMENT = "GC_ELEMENTREGLEMENT";
	public static String TABLE_GC_REGLEMENT = "GC_REGLEMENT";
	public static String TABLE_GC_TYPEREGLEMENT = "GC_TYPEREGLEMENT";
	public static String TABLE_GC_SOLDECLIENT = "GC_SOLDECLIENT";
	// Added on 22 03 2018
	public static String TABLE_GC_DOCUMENT_RECP = "GC_DOCUMENT_RECP";

	/************* Package bonCommande ***************/

	public static String TABLE_GC_PRODUITCOMMANDE = "GC_PRODUITCOMMANDE";
	public static String TABLE_GC_COMMANDEVENTE = "GC_COMMANDEVENTE";

	/************* Package bonCommande Achat ***************/

	public static String TABLE_GC_COMMANDEACHAT = "GC_COMMANDEACHAT";
	public static String TABLE_GC_PRODUITCOMMANDEACHAT = "GC_PRODUITCOMMANDEACHAT";
	public static String TABLE_GC_TAXEBCOMMANDEACHAT = "GC_TAXEBCOMMANDEACHAT";

	/************* Package Reception ***************/

	public static String TABLE_GC_PRODUITRECEPTION = "GC_PRODUITRECEPTION";
	public static String TABLE_GC_RECEPTIONVENTE = "GC_RECEPTIONVENTE";

	/************* Package Reception achat ***************/

	public static String TABLE_GC_DOCUMENTS_RECEPTIONACHAT = "GC_DOCUMENTS_RECEPTIONACHAT";
	public static String TABLE_GC_PRODUITRECEPTIONACHAT = "GC_PRODUITRECEPTIONACHAT";
	public static String TABLE_GC_RECEPTIONACHAT = "GC_RECEPTIONACHAT";

	/********** package depot produit *************/
	//
	public static String TABLE_EB_PRODUITDEPOT = "eb_produitdepot";
	
	
	
	/********* reglement inverse vente **********/
	public static String TABLE_GC_DOCUMENTS_REGLEMENT_INVERSE = "GC_DOCUMENTS_REGLEMENT_INVERSE";
	public static String TABLE_GC_DETAILSREGLEMENT_INVERSE = "GC_DETAILSREGLEMENT_INVERSE";
	public static String TABLE_GC_ELEMENTREGLEMENT_INVERSE = "GC_ELEMENTREGLEMENT_INVERSE";
	public static String TABLE_GC_REGLEMENT_INVERSE = "GC_REGLEMENT_INVERSE";
	
	

	/** Liste des séquences */
	//
	public static String SEQUENCE_GC_DOCUMENT_RECEPTION_SEQ = "GC_DOCUMENT_RECEPTION_ID_SEQ";
	//

	public static String SEQUENCE_GC_CDOCLV_SEQ = "CDOCLV_SEQ";
	public static String SEQUENCE_GC_CDLV_SEQ = "CDLV_SEQ";
	public static String SEQUENCE_GC_CLV_SEQ = "CLV_SEQ";
	public static String SEQUENCE_GC_CTA_SEQ = "CTA_SEQ";
	public static String SEQUENCE_GC_CTLV_SEQ = "CTLV_SEQ";
	public static String SEQUENCE_GC_CTRA_SEQ = "CTRA_SEQ";

	public static String SEQUENCE_GC_CDOCFV_SEQ = "CDOCFV_SEQ";
	public static String SEQUENCE_GC_CDFV_SEQ = "CDFV_SEQ";
	public static String SEQUENCE_GC_CFV_SEQ = "CFV_SEQ";
	public static String SEQUENCE_GC_CTF_SEQ = "CTF_SEQ";
	public static String SEQUENCE_GC_CFRV_SEQ = "CFRV_SEQ";

	public static String SEQUENCE_GC_CMA_SEQ = "CMA_SEQ";
	public static String SEQUENCE_GC_CMP_SEQ = "CMP_SEQ";

	public static String SEQUENCE_GC_DOCUMENTS_REGLEMENT_ACHAT = "CDOCRG_ACH_SEQ";
	public static String SEQUENCE_GC_DETAILSREGLEMENT_ACHAT = "CDR_ACHAT_SEQ";
	public static String SEQUENCE_GC_ELEMENTREGLEMENT_ACHAT = "CER_ACHAT_SEQ";
	public static String SEQUENCE_GC_REGLEMENT_ACHAT = "CRG_ACHAT_SEQ";
	public static String SEQUENCE_GC_TYPEREGLEMENT_ACHAT = "CTR_ACHAT_SEQ";

	public static String SEQUENCE_GC_DOCUMENTS_REGLEMENT = "CDOCRG_SEQ";
	public static String SEQUENCE_GC_DETAILSREGLEMENT = "CDR_SEQ";
	public static String SEQUENCE_GC_ELEMENTREGLEMENT = "CER_SEQ";
	public static String SEQUENCE_GC_REGLEMENT = "CRG_SEQ";
	public static String SEQUENCE_GC_TYPEREGLEMENT = "CTR_SEQ";

	public static final String ORDER_BY_PRODUIT_CODE = "produitCode";
	public static final String ORDER_BY_CHOIX = "choix";

	public static String SEQUENCE_GC_SOLDECLIENT = "CSC_SEQ";

	/************* Package bonComptoir ***************/
	public static final String SEQUENCE_GC_COMPTOIR_VENTE = "GC_COMP_V_SEQ";
	public static final String SEQUENCE_GC_DET_COMPTOIR_VENTE = "GC_DET_COMP_V_SEQ";

	/************* Package bonCommande ***************/

	public static final String SEQUENCE_GC_PRODUITCOMMANDE = "CPC_SEQ";
	public static final String SEQUENCE_GC_COMMANDEVENTE = "CCV_SEQ";

	/************* Package bonCommande Achat ***************/

	public static final String SEQUENCE_GC_COMMANDEACHAT = "CCA_SEQ";
	public static final String SEQUENCE_GC_PRODUITCOMMANDEACHAT = "CPCA_SEQ";
	public static final String SEQUENCE_GC_TAXEBCOMMANDEACHAT = "GC_TAXEBCOMMANDEACHAT_SEQ";

	/************* Package Reception achat ***************/
	public static final String SEQUENCE_GC_DOCUMENTS_RECEPTIONACHAT = "CDOCRACHAT_SEQ";
	public static final String SEQUENCE_GC_PRODUITRECEPTIONACHAT = "CPRACHAT_SEQ";
	public static final String SEQUENCE_GC_RECEPTIONACHAT = "CRACHAT_SEQ";

	/************* Package Reception ***************/
	public static final String SEQUENCE_GC_PRODUITRECEPTION = "CPR_SEQ";
	public static final String SEQUENCE_GC_RECEPTIONVENTE = "CRV_SEQ";
	/************* Package Facture Achat *****************/
	public static String SEQUENCE_GC_DOC_FA_SEQ = "DOC_FA_SEQ";
	public static String SEQUENCE_GC_CFA_SEQ = "CFA_SEQ";
	public static String SEQUENCE_GC_CDFA_SEQ = "CDFA_SEQ";
	public static String SEQUENCE_GC_CTFA_SEQ = "CTFA_SEQ";

	/************************** taxe b commande **********/
	public static final String SEQUENCE_GC_TAXEBCOMMANDE = "GC_TAXEBCOMMANDE_SEQ";

	public static String SEQUENCE_PRDE = "prde_seq";
	
	
	/*************sequence reglement inverse vente *****************/
	
	public static String SEQUENCE_GC_DOCUMENTS_REGLEMENT_INVERSE = "CDOCRG_INV_SEQ";
	public static String SEQUENCE_GC_DETAILSREGLEMENT_INVERSE = "CDR_INV_SEQ";
	public static String SEQUENCE_GC_ELEMENTREGLEMENT_INVERSE = "CER_INV_SEQ";
	public static String SEQUENCE_GC_REGLEMENT_INVERSE = "CRG_INV_SEQ";
	
	
	/*************sequence reglement inverse achat *****************/
	public static String SEQUENCE_GC_DOCUMENTS_REGLEMENT_INVERSE_ACHAT = "CDOCRG_INV_ACH_SEQ";
	public static String SEQUENCE_GC_DETAILSREGLEMENT_INVERSE_ACHAT = "CDR_INV_ACHAT_SEQ";
	public static String SEQUENCE_GC_ELEMENTREGLEMENT_INVERSE_ACHAT = "CER_INV_ACHAT_SEQ";
	public static String SEQUENCE_GC_REGLEMENT_INVERSE_ACHAT = "CRG_INV_ACHAT_SEQ";
	

	// Partie Achat

	public static String TABLE_GC_DOCUMENTS_FACTUREACHAT = "GC_DOCUMENTS_FACTUREACHAT";
	public static String TABLE_GC_DETFACTUREACHAT = "GC_DETFACTUREACHAT";
	public static String TABLE_GC_FACTUREACHAT = "GC_FACTUREACHAT";
	public static String TABLE_GC_TAXEFACTUREACHAT = "GC_TAXEFACTUREACHAT";

	public static String TABLE_GC_DOCUMENTS_REGLEMENT_ACHAT = "GC_DOCUMENTS_REGLEMENT_ACHAT";
	public static String TABLE_GC_DETAILSREGLEMENT_ACHAT = "GC_DETAILSREGLEMENT_ACHAT ";
	public static String TABLE_GC_ELEMENTREGLEMENT_ACHAT = "GC_ELEMENTREGLEMENT_ACHAT ";
	public static String TABLE_GC_REGLEMENT_ACHAT = "GC_REGLEMENT_ACHAT ";
	public static String TABLE_GC_TYPEREGLEMENT_ACHAT = "GC_TYPEREGLEMENT_ACHAT ";
	
	
	//table reglement inverse achat
	
	public static String TABLE_GC_DOCUMENTS_REGLEMENT_INVERSE_ACHAT = "GC_DOCUMENTS_REGLEMENT_INVERSE_ACHAT";
	public static String TABLE_GC_DETAILSREGLEMENT_INVERSE_ACHAT = "GC_DETAILSREGLEMENT_INVERSE_ACHAT ";
	public static String TABLE_GC_ELEMENTREGLEMENT_INVERSE_ACHAT = "GC_ELEMENTREGLEMENT_INVERSE_ACHAT ";
	public static String TABLE_GC_REGLEMENT_INVERSE_ACHAT = "GC_REGLEMENT_INVERSE_ACHAT ";
	
	

	public static String OK = "OK";

	/** Constante Reception Achat **/
	public static String RECEPTION_ACHAT_TYPE_ACHAT = "Achat";
	public static String RECEPTION_ACHAT_TYPE_RETOUR = "Retour";

	/** Constante facture **/
	public static String FACTURE_TYPE_AVOIR = "Avoir";
	public static String FACTURE_TYPE_NORMAL = "Normal";
	public static String FACTURE_NATURE_RETOUR = "Retour";

	/** Constante commande **/

	public static String COMMANDE_TYPE_COMMANDE = "Commande";
	public static String COMMANDE_TYPE_DEVIS = "Devis";

	/** Constante type de bons **/
	public static String BON_COMMANDE = "BON_COMMANDE";
	public static String BON_RECEPTION = "BON_RECEPTION";
	public static String BON_LIVRAISON = "BON_LIVRAISON";
	public static String BON_FACTURE = "BON_FACTURE";

	/** Constante ajout special reglement **/

	public static String REGLEMENT_AJOUT_SPECIAL_BC = "REGLEMENT_AJOUT_SPECIAL_BC";
	public static String REGLEMENT_AJOUT_SPECIAL_BL = "REGLEMENT_AJOUT_SPECIAL_BL";
	public static String REGLEMENT_AJOUT_SPECIAL_FACTURE = "REGLEMENT_AJOUT_SPECIAL_FACTURE";

	/** Constante code error facture vente **/

	public static String CODE_ERROR_DUPLICATE_BL_IN_FACTURE = "CODE_ERROR_DUPLICATE_BL_IN_FACTURE";

	/** Constante code error livraison vente **/

	public static String CODE_ERROR_EDITION_BL_HAS_FACTURE = "CODE_ERROR_EDITION_BL_HAS_FACTURE";

	public static String CODE_ERROR_EDITION_BL_HAS_BON_RETOUR = "CODE_ERROR_EDITION_BL_HAS_BON_RETOUR";
	
	public static String CODE_ERROR_CREATION_BL_BS_EXSIT = "CODE_ERROR_CREATION_BL_BS_EXSIT";

	/** Constante code error magasin **/
	public static String CODE_ERROR_CREATION_MAGASIN_INVALIDE_BOUTIQUE = "CODE_ERROR_CREATION_MAGASIN_INVALIDE_BOUTIQUE";
	public static String CODE_ERROR_EDITION_MAGASIN_INVALIDE_BOUTIQUE = "CODE_ERROR_EDITION_MAGASIN_INVALIDE_BOUTIQUE";

	/** Constante Bon trasfert **/

	public static String BON_TRANSFERT_TYPE_SORTIE = "Sortie";
	public static String BON_TRANSFERT_TYPE_RECEPTION = "Reception";

	public static String BON_TRANSFERT_STATUS_CONFORME = "Conforme";

	public static String BON_TRANSFERT_STATUS_NON_CONFORME = "Non Conforme";

	public static String CODE_ERROR_CREATION_BON_TRANSFERT_INVALIDE_TYPE = "CODE_ERROR_CREATION_BON_TRANSFERT_INVALIDE_TYPE";
	public static String CODE_ERROR_EDITION_BON_TRANSFERT_INVALIDE_TYPE = "CODE_ERROR_EDITION_BON_TRANSFERT_INVALIDE_TYPE";

	public static String CODE_ERROR_CREATION_BON_TRANSFERT_RECEPTION_INVALIDE_STOCK_IN_MAGASIN_ORIGINE = "CODE_ERROR_CREATION_BON_TRANSFERT_RECEPTION_INVALIDE_STOCK_IN_MAGASIN_ORIGINE";

	public static String CODE_ERROR_EDITION_BON_TRANSFERT_SORTIE_BTS_HAS_ALREADY_BEEN_RECEIVED = "CODE_ERROR_EDITION_BON_TRANSFERT_SORTIE_BTS_HAS_ALREADY_BEEN_RECEIVED";

	/** Constante Bon Stock **/

	public static String BON_STOCK_TYPE_SORTIE = "Sortie";
}
