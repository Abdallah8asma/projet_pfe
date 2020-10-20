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
