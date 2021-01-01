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
	  
	  
	  
	  
	  /************** Achat   ****************/
	  

	  /** Numéro BC courant. */
	  @Column(name = "ref_bc")
	  private Long numReferenceBonCommandeCourante;
 
	  
	  /** prefixe_bc  */
	  @Column(name = "prefixe_bc ")
	  private String prefixeBC ;
	  
	  /** Numéro bon reception courant. */
	  @Column(name = "ref_bon_reception")
	  private Long numReferenceBonReceptionCourante;
	  
		
	  @Column(name = "prefixe_bon_reception ")
	  private String prefixeBonReception ;
	  
	  
	  
	  /** Numéro Facture courant. */
	  @Column(name = "ref_facture")
	  private Long numReferenceFactureCourante;
	  
	  /** prefixe_fact  */
	  @Column(name = "prefixe_facture ")
	  private String prefixeFacture ;
	  
	  
	  /** Numéro Avoir courant. */
	  @Column(name = "ref_avoir")
	  private Long numReferenceAvoirCourante;
	  
	  /** prefixe_avoir  */
	  @Column(name = "prefixe_avoir ")
	  private String prefixeAvoir ;
	  
	  
	  
	  
	  /********* Fin Achat   ****************/
	  
	  

	  
	  
	  
	  
	/**
	 * @return the annee
	 */
	public Long getAnnee() {
		return annee;
	}

	public Long getNumReferenceBonCommandeCourante() {
		return numReferenceBonCommandeCourante;
	}

	public void setNumReferenceBonCommandeCourante(Long numReferenceCommandeCourante) {
		this.numReferenceBonCommandeCourante = numReferenceCommandeCourante;
	}

	public String getPrefixeBC() {
		return prefixeBC;
	}

	public void setPrefixeBC(String prefixeBC) {
		this.prefixeBC = prefixeBC;
	}

	public Long getNumReferenceBonReceptionCourante() {
		return numReferenceBonReceptionCourante;
	}

	public void setNumReferenceBonReceptionCourante(Long numReferenceBonReceptionCourante) {
		this.numReferenceBonReceptionCourante = numReferenceBonReceptionCourante;
	}

	public String getPrefixeBonReception() {
		return prefixeBonReception;
	}

	public void setPrefixeBonReception(String prefixeBonReception) {
		this.prefixeBonReception = prefixeBonReception;
	}

	public Long getNumReferenceFactureCourante() {
		return numReferenceFactureCourante;
	}

	public void setNumReferenceFactureCourante(Long numReferenceFactureCourante) {
		this.numReferenceFactureCourante = numReferenceFactureCourante;
	}

	public String getPrefixeFacture() {
		return prefixeFacture;
	}

	public void setPrefixeFacture(String prefixeFacture) {
		this.prefixeFacture = prefixeFacture;
	}

	public Long getNumReferenceAvoirCourante() {
		return numReferenceAvoirCourante;
	}

	public void setNumReferenceAvoirCourante(Long numReferenceAvoirCourante) {
		this.numReferenceAvoirCourante = numReferenceAvoirCourante;
	}

	public String getPrefixeAvoir() {
		return prefixeAvoir;
	}

	public void setPrefixeAvoir(String prefixeAvoir) {
		this.prefixeAvoir = prefixeAvoir;
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
