package com.gpro.consulting.tiers.logistique.persistance.gc.guichet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;


/**
 * @author rkhaskho
 *
 */
@Entity 
@Table(name = IConstanteLogistique.TABLE_GENERATEUR_MENSUEL)
public class GuichetMensuelEntity {
	/** L'id de la table: l'id */
	  @Id
	  private Long id;
	  
	  
	  /** Année courante. */
	  @Column(name = "annee")
	  private Long annee;


	  /** Mois courant. */
	  @Column(name = "mois")
	  private Long mois;
	  
	  /** Numéro courant. */
	  @Column(name = "ref_bs")
	  private Long numReferenceBonSortieCourante;

	  /** Numéro courant. */
	  @Column(name = "ref_bl")
	  private Long numReferenceBonLivraisonCourante;

	  
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

	/**
	 * @return the numReferenceBonLivraisonCourante
	 */
	public Long getNumReferenceBonLivraisonCourante() {
		return numReferenceBonLivraisonCourante;
	}

	/**
	 * @param numReferenceBonLivraisonCourante the numReferenceBonLivraisonCourante to set
	 */
	public void setNumReferenceBonLivraisonCourante(
			Long numReferenceBonLivraisonCourante) {
		this.numReferenceBonLivraisonCourante = numReferenceBonLivraisonCourante;
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

}
