package com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value;


/**
 *Classe Valeur du guichet de bon de reception
 *
 * @author rkhaskho
 *
 */
public class GuichetBonReceptionValue {
	/** Annnée */
	 private Long annee;

	  /** Numéro Réference courant. */
	  private Long numReferenceCourant;

	  
	/**
	 * Constructeur
	 */
	public GuichetBonReceptionValue() {

	}

	/**
	 * Constructeur
	 * @param annee
	 * @param numReferenceCourant
	 */
	public GuichetBonReceptionValue(Long annee, Long numReferenceCourant) {
		super();
		this.annee = annee;
		this.numReferenceCourant = numReferenceCourant;
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
	 * @return the numReferenceCourant
	 */
	public Long getNumReferenceCourant() {
		return numReferenceCourant;
	}

	/**
	 * @param numReferenceCourant the numReferenceCourant to set
	 */
	public void setNumReferenceCourant(Long numReferenceCourant) {
		this.numReferenceCourant = numReferenceCourant;
	}

}
