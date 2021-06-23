package com.gpro.consulting.logistique.coordination.gc.guichet.value;

public class GuichetAnnuelValue {

	/** id */
	private Long id;

	/** Annnée */
	private Long annee;

	/** Numéro RéferenceFacture courant. */

	private Long numReferenceFactureCourant;

	/** Numéro RéferenceAvoir courant. */

	private Long numReferenceAvoirCourante;

	/** Numéro RéferenceCommande courant. */

	private Long numReferenceCommandeCourante;

	/** Numéro RéferenceReglement courant. */

	private Long numReferenceReglementCourante;

	/** Numéro Réference Bon Livraison courant. */
	private Long numReferenceBonLivraisonCourante;

	private Long numReferenceBonComptoirCourante;

	private String prefixeFacture;

	private String prefixeAvoir;

	/** Numéro bon reception courant. */

	private Long numReferenceBonReceptionCourante;

	/** Numéro Facture achat courant. */

	private Long numReferenceFactureAchatCourante;

	/** Numéro RéferenceReglement Achat courant. */

	private Long numReferenceReglementAchatCourante;

	private String prefixeBcAchat;
	private String prefixeBonReception;
	private String prefixeFactureAchat;
	private String prefixeBC;

	private Long numReferenceCommandeAchatCourante;

	private String prefixeBL;

	private String prefixeDevis;
	private String prefixeDevisAchat;
	private Long numReferenceDevisCourante;
	private Long numReferenceDevisAchatCourante;

	private Long numFactureAvoirAchatCourante;

	private String prefixeFactureAvoirAchat;

	/** Numéro ref_bon_stock. */

	private Long numBonStockCourante;

	private Long numBonInventaireCourante;

	/** prefixe_bon_stock */

	private String prefixeBonStock;

	private String prefixeReglement;
	private String prefixeReglementAchat;

	private String prefixeBonInventaire;

	// Transfert

	private Long numBonTransfertReceptionCourante;
	private Long numBonTransfertSortieCourante;

	private String prefixeBonTransfertReception;
	private String prefixeBonTransfertSortie;

	// Fin Transfert

	// GS

	/** Numéro Bon de mouvement entre. */
	private Long numBonMouvementEntre;

	/** Numéro Bon de mouvement de sortie. */
	private Long numBonMouvementSortie;

	// BL ND
	/** Numéro Réference Bon Livraison ND courant. */
	private Long numReferenceBonLivraisonNDCourante;
	private String prefixeBLND;

	
	  private Long numReferenceFactureNDCourante;
	  private String prefixeFAND ;
	  
	  
	  
	  
	  /***reglement inverse ***/
	  
	  

	  private String prefixeReglementInverse; 
	  

	  private Long numReferenceReglementInverseCourante;
	  
	  
	  
	  
	  
	  //reglement non declarer 
	  
	  private Long numReferenceReglementNonDeclarerCourante;
	  
	  private String prefixeReglementNonDeclarer;
	  
	  
	  
	  
	
	public Long getNumReferenceReglementNonDeclarerCourante() {
		return numReferenceReglementNonDeclarerCourante;
	}

	public void setNumReferenceReglementNonDeclarerCourante(Long numReferenceReglementNonDeclarerCourante) {
		this.numReferenceReglementNonDeclarerCourante = numReferenceReglementNonDeclarerCourante;
	}

	public String getPrefixeReglementNonDeclarer() {
		return prefixeReglementNonDeclarer;
	}

	public void setPrefixeReglementNonDeclarer(String prefixeReglementNonDeclarer) {
		this.prefixeReglementNonDeclarer = prefixeReglementNonDeclarer;
	}

	public String getPrefixeReglementInverse() {
		return prefixeReglementInverse;
	}

	public void setPrefixeReglementInverse(String prefixeReglementInverse) {
		this.prefixeReglementInverse = prefixeReglementInverse;
	}

	public Long getNumReferenceReglementInverseCourante() {
		return numReferenceReglementInverseCourante;
	}

	public void setNumReferenceReglementInverseCourante(Long numReferenceReglementInverseCourante) {
		this.numReferenceReglementInverseCourante = numReferenceReglementInverseCourante;
	}

	public Long getNumReferenceFactureNDCourante() {
		return numReferenceFactureNDCourante;
	}

	public void setNumReferenceFactureNDCourante(Long numReferenceFactureNDCourante) {
		this.numReferenceFactureNDCourante = numReferenceFactureNDCourante;
	}

	public String getPrefixeFAND() {
		return prefixeFAND;
	}

	public void setPrefixeFAND(String prefixeFAND) {
		this.prefixeFAND = prefixeFAND;
	}

	public Long getNumReferenceBonLivraisonNDCourante() {
		return numReferenceBonLivraisonNDCourante;
	}

	public void setNumReferenceBonLivraisonNDCourante(Long numReferenceBonLivraisonNDCourante) {
		this.numReferenceBonLivraisonNDCourante = numReferenceBonLivraisonNDCourante;
	}

	public String getPrefixeBLND() {
		return prefixeBLND;
	}

	public void setPrefixeBLND(String prefixeBLND) {
		this.prefixeBLND = prefixeBLND;
	}

	public Long getNumBonMouvementEntre() {
		return numBonMouvementEntre;
	}

	public void setNumBonMouvementEntre(Long numBonMouvementEntre) {
		this.numBonMouvementEntre = numBonMouvementEntre;
	}

	public Long getNumBonMouvementSortie() {
		return numBonMouvementSortie;
	}

	public void setNumBonMouvementSortie(Long numBonMouvementSortie) {
		this.numBonMouvementSortie = numBonMouvementSortie;
	}

	public String getPrefixeBonInventaire() {
		return prefixeBonInventaire;
	}

	public void setPrefixeBonInventaire(String prefixeBonInventaire) {
		this.prefixeBonInventaire = prefixeBonInventaire;
	}

	public Long getNumBonInventaireCourante() {
		return numBonInventaireCourante;
	}

	public void setNumBonInventaireCourante(Long numBonInventaireCourante) {
		this.numBonInventaireCourante = numBonInventaireCourante;
	}

	public String getPrefixeReglement() {
		return prefixeReglement;
	}

	public void setPrefixeReglement(String prefixeReglement) {
		this.prefixeReglement = prefixeReglement;
	}

	public String getPrefixeReglementAchat() {
		return prefixeReglementAchat;
	}

	public void setPrefixeReglementAchat(String prefixeReglementAchat) {
		this.prefixeReglementAchat = prefixeReglementAchat;
	}

	public Long getNumBonStockCourante() {
		return numBonStockCourante;
	}

	public void setNumBonStockCourante(Long numBonStockCourante) {
		this.numBonStockCourante = numBonStockCourante;
	}

	public String getPrefixeBonStock() {
		return prefixeBonStock;
	}

	public void setPrefixeBonStock(String prefixeBonStock) {
		this.prefixeBonStock = prefixeBonStock;
	}

	public Long getNumFactureAvoirAchatCourante() {
		return numFactureAvoirAchatCourante;
	}

	public void setNumFactureAvoirAchatCourante(Long numFactureAvoirAchatCourante) {
		this.numFactureAvoirAchatCourante = numFactureAvoirAchatCourante;
	}

	public String getPrefixeFactureAvoirAchat() {
		return prefixeFactureAvoirAchat;
	}

	public void setPrefixeFactureAvoirAchat(String prefixeFactureAvoirAchat) {
		this.prefixeFactureAvoirAchat = prefixeFactureAvoirAchat;
	}

	public String getPrefixeDevis() {
		return prefixeDevis;
	}

	public void setPrefixeDevis(String prefixeDevis) {
		this.prefixeDevis = prefixeDevis;
	}

	public String getPrefixeDevisAchat() {
		return prefixeDevisAchat;
	}

	public void setPrefixeDevisAchat(String prefixeDevisAchat) {
		this.prefixeDevisAchat = prefixeDevisAchat;
	}

	public Long getNumReferenceDevisCourante() {
		return numReferenceDevisCourante;
	}

	public void setNumReferenceDevisCourante(Long numReferenceDevisCourante) {
		this.numReferenceDevisCourante = numReferenceDevisCourante;
	}

	public Long getNumReferenceDevisAchatCourante() {
		return numReferenceDevisAchatCourante;
	}

	public void setNumReferenceDevisAchatCourante(Long numReferenceDevisAchatCourante) {
		this.numReferenceDevisAchatCourante = numReferenceDevisAchatCourante;
	}

	public String getPrefixeBL() {
		return prefixeBL;
	}

	public void setPrefixeBL(String prefixeBL) {
		this.prefixeBL = prefixeBL;
	}

	public String getPrefixeBcAchat() {
		return prefixeBcAchat;
	}

	public void setPrefixeBcAchat(String prefixeBcAchat) {
		this.prefixeBcAchat = prefixeBcAchat;
	}

	public String getPrefixeBonReception() {
		return prefixeBonReception;
	}

	public void setPrefixeBonReception(String prefixeBonReception) {
		this.prefixeBonReception = prefixeBonReception;
	}

	public String getPrefixeFactureAchat() {
		return prefixeFactureAchat;
	}

	public void setPrefixeFactureAchat(String prefixeFactureAchat) {
		this.prefixeFactureAchat = prefixeFactureAchat;
	}

	public String getPrefixeBC() {
		return prefixeBC;
	}

	public void setPrefixeBC(String prefixeBC) {
		this.prefixeBC = prefixeBC;
	}

	public Long getNumReferenceCommandeAchatCourante() {
		return numReferenceCommandeAchatCourante;
	}

	public void setNumReferenceCommandeAchatCourante(Long numReferenceCommandeAchatCourante) {
		this.numReferenceCommandeAchatCourante = numReferenceCommandeAchatCourante;
	}

	public Long getNumReferenceReglementAchatCourante() {
		return numReferenceReglementAchatCourante;
	}

	public void setNumReferenceReglementAchatCourante(Long numReferenceReglementAchatCourante) {
		this.numReferenceReglementAchatCourante = numReferenceReglementAchatCourante;
	}

	public Long getNumReferenceBonReceptionCourante() {
		return numReferenceBonReceptionCourante;
	}

	public void setNumReferenceBonReceptionCourante(Long numReferenceBonReceptionCourante) {
		this.numReferenceBonReceptionCourante = numReferenceBonReceptionCourante;
	}

	public Long getNumReferenceFactureAchatCourante() {
		return numReferenceFactureAchatCourante;
	}

	public void setNumReferenceFactureAchatCourante(Long numReferenceFactureAchatCourante) {
		this.numReferenceFactureAchatCourante = numReferenceFactureAchatCourante;
	}

	public String getPrefixeFacture() {
		return prefixeFacture;
	}

	public void setPrefixeFacture(String prefixeFacture) {
		this.prefixeFacture = prefixeFacture;
	}

	public String getPrefixeAvoir() {
		return prefixeAvoir;
	}

	public void setPrefixeAvoir(String prefixeAvoir) {
		this.prefixeAvoir = prefixeAvoir;
	}

	/**
	 * Constructeur
	 */
	public GuichetAnnuelValue() {

	}

	/**
	 * Constructeur
	 * 
	 * @param annee
	 * @param numReferenceCourant
	 */
	public GuichetAnnuelValue(Long annee, Long numReferenceCourant) {
		super();
		this.annee = annee;

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the annee
	 */
	public Long getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(Long annee) {
		this.annee = annee;
	}

	/**
	 * @return the numReferenceFactureCourant
	 */
	public Long getNumReferenceFactureCourant() {
		return numReferenceFactureCourant;
	}

	/**
	 * @param numReferenceFactureCourant the numReferenceFactureCourant to set
	 */
	public void setNumReferenceFactureCourant(Long numReferenceFactureCourant) {
		this.numReferenceFactureCourant = numReferenceFactureCourant;
	}

	/**
	 * @return the numReferenceAvoirCourante
	 */
	public Long getNumReferenceAvoirCourante() {
		return numReferenceAvoirCourante;
	}

	/**
	 * @param numReferenceAvoirCourante the numReferenceAvoirCourante to set
	 */
	public void setNumReferenceAvoirCourante(Long numReferenceAvoirCourante) {
		this.numReferenceAvoirCourante = numReferenceAvoirCourante;
	}

	/**
	 * @return the numReferenceCommandeCourante
	 */
	public Long getNumReferenceCommandeCourante() {
		return numReferenceCommandeCourante;
	}

	/**
	 * @param numReferenceCommandeCourante the numReferenceCommandeCourante to set
	 */
	public void setNumReferenceCommandeCourante(Long numReferenceCommandeCourante) {
		this.numReferenceCommandeCourante = numReferenceCommandeCourante;
	}

	/**
	 * @return the numReferenceReglementCourante
	 */
	public Long getNumReferenceReglementCourante() {
		return numReferenceReglementCourante;
	}

	/**
	 * @param numReferenceReglementCourante the numReferenceReglementCourante to set
	 */
	public void setNumReferenceReglementCourante(Long numReferenceReglementCourante) {
		this.numReferenceReglementCourante = numReferenceReglementCourante;
	}

	public Long getNumReferenceBonLivraisonCourante() {
		return numReferenceBonLivraisonCourante;
	}

	public void setNumReferenceBonLivraisonCourante(Long numReferenceBonLivraisonCourante) {
		this.numReferenceBonLivraisonCourante = numReferenceBonLivraisonCourante;
	}

	public Long getNumReferenceBonComptoirCourante() {
		return numReferenceBonComptoirCourante;
	}

	public void setNumReferenceBonComptoirCourante(Long numReferenceBonComptoirCourante) {
		this.numReferenceBonComptoirCourante = numReferenceBonComptoirCourante;
	}

	public Long getNumBonTransfertReceptionCourante() {
		return numBonTransfertReceptionCourante;
	}

	public void setNumBonTransfertReceptionCourante(Long numBonTransfertReceptionCourante) {
		this.numBonTransfertReceptionCourante = numBonTransfertReceptionCourante;
	}

	public Long getNumBonTransfertSortieCourante() {
		return numBonTransfertSortieCourante;
	}

	public void setNumBonTransfertSortieCourante(Long numBonTransfertSortieCourante) {
		this.numBonTransfertSortieCourante = numBonTransfertSortieCourante;
	}

	public String getPrefixeBonTransfertReception() {
		return prefixeBonTransfertReception;
	}

	public void setPrefixeBonTransfertReception(String prefixeBonTransfertReception) {
		this.prefixeBonTransfertReception = prefixeBonTransfertReception;
	}

	public String getPrefixeBonTransfertSortie() {
		return prefixeBonTransfertSortie;
	}

	public void setPrefixeBonTransfertSortie(String prefixeBonTransfertSortie) {
		this.prefixeBonTransfertSortie = prefixeBonTransfertSortie;
	}

}
