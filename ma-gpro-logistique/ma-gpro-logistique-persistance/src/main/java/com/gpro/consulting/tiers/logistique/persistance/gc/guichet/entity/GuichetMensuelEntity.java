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
	  
	  
	  /** Numéro bon reception courant. */
	  @Column(name = "ref_bon_reception_nd")
	  private Long numReferenceBonReceptionNonDeclarerCourante;
	  
		
	  @Column(name = "prefixe_bon_reception_nd ")
	  private String prefixeBonReceptionNonDeclarer ;
	  
	  
	  
	  
	  @Column(name = "ref_factureachat_nd")
	  private Long numReferenceFactureAchatNDCourante;
	  

	  @Column(name = "prefixe_factureachat_nd ")
	  private String prefixeFactureAchatND ;
	  
	  
	  

	  
	  /** Numéro reg courant. */
	  @Column(name = "ref_reglement_achat")
	  private Long numReferenceReglementAchatCourante;
	  
	  /** prefixe_fact  */
	  @Column(name = "prefixe_reglement_achat ")
	  private String prefixeReglementAchat ;
	  

	  
	  
	  /** ref_det_reglement_achat. */
	  @Column(name = "ref_det_reglement_achat")
	  private Long numReferenceDetReglementAchatCourante;
	  
	  /** prefixe_det_reglement_achat  */
	  @Column(name = "prefixe_det_reglement_achat ")
	  private String prefixeDetReglementAchat ;
	  
	  
	  
	  /** ref_det_reglement. */
	  @Column(name = "ref_det_reglement")
	  private Long numReferenceDetReglementCourante;
	  
	  /** prefixe_det_reglement_achat  */
	  @Column(name = "prefixe_det_reglement ")
	  private String prefixeDetReglement ;
	  
	  
	  
	  /** cible_ca */
	  @Column(name = "cible_ca")
	  private Double cibleCA;
	  
	  
	  
	  
	  //reglement inverse achat
	  
	  
	  /** ref_det_reglement_achat. */
	  @Column(name = "ref_det_reglement_inverse_achat")
	  private Long numReferenceDetReglementInverseAchatCourante;
	  
	  /** prefixe_det_reglement_achat  */
	  @Column(name = "prefixe_det_reglement_inverse_achat ")
	  private String prefixeDetReglementInverseAchat ;
	  
	  
	  /** Numéro reg inverse achat courant. */
	  @Column(name = "ref_reglement_inverse_achat")
	  private Long numReferenceReglementInverseAchatCourante;
	  
	  /** prefixe_fact  */
	  @Column(name = "prefixe_reglement_inverse_achat ")
	  private String prefixeReglementInverseAchat ;
	  
	  
	  //reglement inverse vente
	  
	  
	  /** Numéro reg inverse achat courant. */
	  @Column(name = "ref_reglement_inverse")
	  private Long numReferenceReglementInverseCourante;
	  
	  /** prefixe_fact  */
	  @Column(name = "prefixe_reglement_inverse ")
	  private String prefixeReglementInverse ;
	  
	  
	  
	  
	  /** ref_det_reglement. */
	  @Column(name = "ref_det_reglement_inverse")
	  private Long numReferenceDetReglementInverseCourante;
	  
	  /** prefixe_det_reglement_achat  */
	  @Column(name = "prefixe_det_reglement_inverse ")
	  private String prefixeDetReglementInverse ;
	  
	  
	  
	  
	  
	public Long getNumReferenceReglementInverseCourante() {
		return numReferenceReglementInverseCourante;
	}

	public void setNumReferenceReglementInverseCourante(Long numReferenceReglementInverseCourante) {
		this.numReferenceReglementInverseCourante = numReferenceReglementInverseCourante;
	}

	public String getPrefixeReglementInverse() {
		return prefixeReglementInverse;
	}

	public void setPrefixeReglementInverse(String prefixeReglementInverse) {
		this.prefixeReglementInverse = prefixeReglementInverse;
	}

	public Long getNumReferenceDetReglementInverseCourante() {
		return numReferenceDetReglementInverseCourante;
	}

	public void setNumReferenceDetReglementInverseCourante(Long numReferenceDetReglementInverseCourante) {
		this.numReferenceDetReglementInverseCourante = numReferenceDetReglementInverseCourante;
	}

	public String getPrefixeDetReglementInverse() {
		return prefixeDetReglementInverse;
	}

	public void setPrefixeDetReglementInverse(String prefixeDetReglementInverse) {
		this.prefixeDetReglementInverse = prefixeDetReglementInverse;
	}

	public Long getNumReferenceDetReglementInverseAchatCourante() {
		return numReferenceDetReglementInverseAchatCourante;
	}

	public void setNumReferenceDetReglementInverseAchatCourante(Long numReferenceDetReglementInverseAchatCourante) {
		this.numReferenceDetReglementInverseAchatCourante = numReferenceDetReglementInverseAchatCourante;
	}

	public String getPrefixeDetReglementInverseAchat() {
		return prefixeDetReglementInverseAchat;
	}

	public void setPrefixeDetReglementInverseAchat(String prefixeDetReglementInverseAchat) {
		this.prefixeDetReglementInverseAchat = prefixeDetReglementInverseAchat;
	}

	public Long getNumReferenceReglementInverseAchatCourante() {
		return numReferenceReglementInverseAchatCourante;
	}

	public void setNumReferenceReglementInverseAchatCourante(Long numReferenceReglementInverseAchatCourante) {
		this.numReferenceReglementInverseAchatCourante = numReferenceReglementInverseAchatCourante;
	}

	public String getPrefixeReglementInverseAchat() {
		return prefixeReglementInverseAchat;
	}

	public void setPrefixeReglementInverseAchat(String prefixeReglementInverseAchat) {
		this.prefixeReglementInverseAchat = prefixeReglementInverseAchat;
	}

	public Double getCibleCA() {
		return cibleCA;
	}

	public void setCibleCA(Double cibleCA) {
		this.cibleCA = cibleCA;
	}

	public Long getNumReferenceDetReglementCourante() {
		return numReferenceDetReglementCourante;
	}

	public void setNumReferenceDetReglementCourante(Long numReferenceDetReglementCourante) {
		this.numReferenceDetReglementCourante = numReferenceDetReglementCourante;
	}

	public String getPrefixeDetReglement() {
		return prefixeDetReglement;
	}

	public void setPrefixeDetReglement(String prefixeDetReglement) {
		this.prefixeDetReglement = prefixeDetReglement;
	}

	public Long getNumReferenceReglementAchatCourante() {
		return numReferenceReglementAchatCourante;
	}

	public void setNumReferenceReglementAchatCourante(Long numReferenceReglementAchatCourante) {
		this.numReferenceReglementAchatCourante = numReferenceReglementAchatCourante;
	}

	public String getPrefixeReglementAchat() {
		return prefixeReglementAchat;
	}

	public void setPrefixeReglementAchat(String prefixeReglementAchat) {
		this.prefixeReglementAchat = prefixeReglementAchat;
	}

	public Long getNumReferenceDetReglementAchatCourante() {
		return numReferenceDetReglementAchatCourante;
	}

	public void setNumReferenceDetReglementAchatCourante(Long numReferenceDetReglementAchatCourante) {
		this.numReferenceDetReglementAchatCourante = numReferenceDetReglementAchatCourante;
	}

	public String getPrefixeDetReglementAchat() {
		return prefixeDetReglementAchat;
	}

	public void setPrefixeDetReglementAchat(String prefixeDetReglementAchat) {
		this.prefixeDetReglementAchat = prefixeDetReglementAchat;
	}

	public Long getNumReferenceFactureAchatNDCourante() {
		return numReferenceFactureAchatNDCourante;
	}

	public void setNumReferenceFactureAchatNDCourante(Long numReferenceFactureAchatNDCourante) {
		this.numReferenceFactureAchatNDCourante = numReferenceFactureAchatNDCourante;
	}

	public String getPrefixeFactureAchatND() {
		return prefixeFactureAchatND;
	}

	public void setPrefixeFactureAchatND(String prefixeFactureAchatND) {
		this.prefixeFactureAchatND = prefixeFactureAchatND;
	}

	/**
	 * @return the annee
	 */
	public Long getAnnee() {
		return annee;
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
