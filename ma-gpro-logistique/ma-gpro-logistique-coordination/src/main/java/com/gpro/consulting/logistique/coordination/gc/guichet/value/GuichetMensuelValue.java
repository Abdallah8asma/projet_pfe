package com.gpro.consulting.logistique.coordination.gc.guichet.value;

public class GuichetMensuelValue {

	/** id */
	private Long id;

	/** Annnée */
	private Long annee;

	/** Numéro Réference courant. */
	private Long numReferenceBonLivraisonCourant;

	private Long numReferenceBonSortieCourante;

	private Long NumReferenceBonComptoirCourant;

	private Long mois;
	
	
	
	private Long numReferenceBonCommandeCourante;
	
	private Long numReferenceBonReceptionCourante;
	
	private Long numReferenceFactureCourante;
	
	private Long numReferenceAvoirCourante;
	
	
	
	private String prefixeBcAchat;
	private String prefixeBonReception;
	
	
	
	private Long numReferenceBonReceptionNonDeclarerCourante;
	private String prefixeBonReceptionNonDeclarer;
	
	private Long numReferenceFactureAchatNonDeclarerCourante;
	private String prefixeFactureAchatNonDeclarer;
	
	
	
	
	public Long getNumReferenceFactureAchatNonDeclarerCourante() {
		return numReferenceFactureAchatNonDeclarerCourante;
	}

	public void setNumReferenceFactureAchatNonDeclarerCourante(Long numReferenceFactureAchatNonDeclarerCourante) {
		this.numReferenceFactureAchatNonDeclarerCourante = numReferenceFactureAchatNonDeclarerCourante;
	}

	public String getPrefixeFactureAchatNonDeclarer() {
		return prefixeFactureAchatNonDeclarer;
	}

	public void setPrefixeFactureAchatNonDeclarer(String prefixeFactureAchatNonDeclarer) {
		this.prefixeFactureAchatNonDeclarer = prefixeFactureAchatNonDeclarer;
	}

	public Long getNumReferenceBonReceptionNonDeclarerCourante() {
		return numReferenceBonReceptionNonDeclarerCourante;
	}

	public void setNumReferenceBonReceptionNonDeclarerCourante(Long numReferenceBonReceptionNonDeclarerCourante) {
		this.numReferenceBonReceptionNonDeclarerCourante = numReferenceBonReceptionNonDeclarerCourante;
	}

	public String getPrefixeBonReceptionNonDeclarer() {
		return prefixeBonReceptionNonDeclarer;
	}

	public void setPrefixeBonReceptionNonDeclarer(String prefixeBonReceptionNonDeclarer) {
		this.prefixeBonReceptionNonDeclarer = prefixeBonReceptionNonDeclarer;
	}

	public Long getNumReferenceBonCommandeCourante() {
		return numReferenceBonCommandeCourante;
	}

	public void setNumReferenceBonCommandeCourante(Long numReferenceBonCommandeCourante) {
		this.numReferenceBonCommandeCourante = numReferenceBonCommandeCourante;
	}

	public Long getNumReferenceBonReceptionCourante() {
		return numReferenceBonReceptionCourante;
	}

	public void setNumReferenceBonReceptionCourante(Long numReferenceBonReceptionCourante) {
		this.numReferenceBonReceptionCourante = numReferenceBonReceptionCourante;
	}

	public Long getNumReferenceFactureCourante() {
		return numReferenceFactureCourante;
	}

	public void setNumReferenceFactureCourante(Long numReferenceFactureCourante) {
		this.numReferenceFactureCourante = numReferenceFactureCourante;
	}

	public Long getNumReferenceAvoirCourante() {
		return numReferenceAvoirCourante;
	}

	public void setNumReferenceAvoirCourante(Long numReferenceAvoirCourante) {
		this.numReferenceAvoirCourante = numReferenceAvoirCourante;
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

	private String prefixeFacture;

	private String prefixeAvoir;

	
	
	
	/***************** 

	/**
	 * Constructeur
	 */
	public GuichetMensuelValue() {

	}

	/**
	 * Constructeur
	 * 
	 * @param annee
	 * @param numReferenceCourant
	 */
	public GuichetMensuelValue(Long annee, Long numReferenceCourant) {
		super();
		this.annee = annee;
		this.numReferenceBonLivraisonCourant = numReferenceCourant;
		this.numReferenceBonSortieCourante = numReferenceCourant;
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
	 * @return the numReferenceBonLivraisonCourant
	 */
	public Long getNumReferenceBonLivraisonCourant() {
		return numReferenceBonLivraisonCourant;
	}

	/**
	 * @param numReferenceBonLivraisonCourant the numReferenceBonLivraisonCourant to
	 *                                        set
	 */
	public void setNumReferenceBonLivraisonCourant(Long numReferenceBonLivraisonCourant) {
		this.numReferenceBonLivraisonCourant = numReferenceBonLivraisonCourant;
	}

	/**
	 * @return the numReferenceBonSortieCourante
	 */
	public Long getNumReferenceBonSortieCourante() {
		return numReferenceBonSortieCourante;
	}

	/**
	 * @param numReferenceBonSortieCourante the numReferenceBonSortieCourante to set
	 */
	public void setNumReferenceBonSortieCourante(Long numReferenceBonSortieCourante) {
		this.numReferenceBonSortieCourante = numReferenceBonSortieCourante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMois() {
		return mois;
	}

	public void setMois(Long mois) {
		this.mois = mois;
	}

	public Long getNumReferenceBonComptoirCourant() {
		return NumReferenceBonComptoirCourant;
	}

	public void setNumReferenceBonComptoirCourant(Long numReferenceBonComptoirCourant) {
		NumReferenceBonComptoirCourant = numReferenceBonComptoirCourant;
	}

}
