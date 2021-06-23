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
@Table(name = IConstanteLogistique.TABLE_GENERATEUR_ANNUEL)
public class GuichetAnnuelEntity {
	/** L'id de la table: l'id */
	  @Id
	  private Long id;
	  
	  
	  /** Année courante. */
	  @Column(name = "annee")
	  private Long annee;


	
	  
	  /** Numéro Facture courant. */
	  @Column(name = "ref_facture")
	  private Long numReferenceFactureCourante;

	  /** Numéro Avoir courant. */
	  @Column(name = "ref_avoir")
	  private Long numReferenceAvoirCourante;
	  
	  /** Numéro BC courant. */
	  @Column(name = "ref_devis")
	  private Long numReferenceDevisCourante;

	  /** Numéro BC courant. */
	  @Column(name = "ref_bc")
	  private Long numReferenceCommandeCourante;
 
	  /** Numéro Reglement courant. */
	  @Column(name = "ref_reglement")
	  private Long numReferenceReglementCourante;
	  
	  
	  /** Numéro bon livraison courant. */
	  @Column(name = "ref_bon_livraison")
	  private Long numReferenceBonLivraisonCourante;
	  
	  /** Numéro bon livraison courant. */
	  @Column(name = "ref_bon_comptoir")
	  private Long numReferenceBonComptoirCourante;

	  @Column(name = "prefixe_devis")
	  private String prefixeDevis ;

	  /** prefixe_bc  */
	  @Column(name = "prefixe_bc ")
	  private String prefixeBC ;
	  
	  /** prefixe_bl  */
	  @Column(name = "prefixe_bl ")
	  private String prefixeBL ;
	  
	  /** prefixe_fact  */
	  @Column(name = "prefixe_facture ")
	  private String prefixeFacture ;

	  /** prefixe_avoir  */
	  @Column(name = "prefixe_avoir ")
	  private String prefixeAvoir ;
	  
	  
	  /** REFERNCE ACHAT  **/
	  

	  /** prefixe_bc  */
	  
	  @Column(name = "prefixe_devis_achat ")
	  private String prefixeDevisAchat ;
	  
	  @Column(name = "prefixe_bc_achat ")
	  private String prefixeBcAchat ;
	  
	  /** prefixe_fact Achat */
	  @Column(name = "prefixe_facture_achat ")
	  private String prefixeFactureAchat ;

	
	  @Column(name = "prefixe_bon_reception ")
	  private String prefixeBonReception ;

	  
	  /** Numéro BC courant. */
	  @Column(name = "ref_bc_achat")
	  private Long numReferenceCommandeAchatCourante;
	  
	  /** Numéro BC courant. */
	  @Column(name = "ref_devis_achat")
	  private Long numReferenceDevisAchatCourante;
	  
	  /** Numéro bon reception courant. */
	  @Column(name = "ref_bon_reception")
	  private Long numReferenceBonReceptionCourante;
	  
	  
	  /** Numéro Facture  achat courant. */
	  @Column(name = "ref_facture_achat")
	  private Long numReferenceFactureAchatCourante;
	  
	  
	  
	  /** Numéro Reglement Achat courant. */
	  @Column(name = "ref_reglement_achat")
	  private Long numReferenceReglementAchatCourante;
	    
	  

	  
	  
	  /** Numéro Facture avoir achat courant. */
	  @Column(name = "ref_facture_avoir_achat")
	  private Long numFactureAvoirAchatCourante;
	  
	
	  /** prefixe_fact avoir Achat */
	  @Column(name = "prefixe_facture_avoir_achat ")
	  private String prefixeFactureAvoirAchat ;
	  
	  
	  
	  
	  
	  /** Numéro ref_bon_stock. */
	  @Column(name = "ref_bon_stock")
	  private Long numBonStockCourante; 
	  
	  /** prefixe_bon_stock */
	  @Column(name = "prefixe_bon_stock ")
	  private String prefixeBonStock ;
	  
	  
	  
	  
	  /** prefixe_reglement  */
	  @Column(name = "prefixe_reglement ")
	  private String prefixeReglement; 
	  
	  /** prefixe_reglement_achat  */
	  @Column(name = "prefixe_reglement_achat ")
	  private String prefixeReglementAchat; 
	  
	  
	  @Column(name = "ref_bon_inventaire")
	  private Long numBonInventaireCourante; 
	  
	  @Column(name = "prefixe_bon_inventaire ")
	  private String prefixeBonInventaire ;
	  
	  
	  
	  //transfer
	  
	  
	  @Column(name = "ref_bon_transfert_sortie")
	  private Long numBonTransfertSortieCourante; 
	  
	  @Column(name = "prefixe_bon_transfert_sortie")
	  private String prefixeBonTransfertSortie ;
	  
	  @Column(name = "ref_bon_transfert_reception")
	  private Long numBonTransfertReceptionCourante; 
	  
	  @Column(name = "prefixe_bon_transfert_reception")
	  private String prefixeBonTransfertReception ;
	
	  
	  
	  //fin transfert
	  
	  
	  
	  
	  
	  
	  
	  //GS
	  
	  
	  /** Numéro Bon de mouvement entre. */
	  @Column(name = "num_bonmouvement_entre")
	  private Long numBonMouvementEntre;
 
	  
	  /** Numéro Bon de mouvement de sortie. */
	  @Column(name = "num_bonmouvement_sortie")
	  private Long numBonMouvementSortie;
	  
	  
	  
	  
	  // BL Non Declare.
	  
	  
	  
	  /** Numéro bon livraison courant. */
	  @Column(name = "ref_bon_livraison_nd")
	  private Long numReferenceBonLivraisonNDCourante;
	  
	  /** prefixe_bl  */
	  @Column(name = "prefixe_bl_nd ")
	  private String prefixeBLND ;
	  
	  
	  
	  
	  
	  /** Numéro bon livraison courant. */
	  @Column(name = "ref_facture_nd")
	  private Long numReferenceFactureNDCourante;
	  
	  /** prefixe_bl  */
	  @Column(name = "prefixe_facture_nd ")
	  private String prefixeFAND ;
	  
	  
	  
	  
	  
	  /***reglement inverse vente **/
	  
	  
	  
	  /** prefixe_reglement  */
	  @Column(name = "prefixe_reglement_inverse ")
	  private String prefixeReglementInverse; 
	  
	  /** Numéro Reglement courant. */
	  @Column(name = "ref_reglement_inverse")
	  private Long numReferenceReglementInverseCourante;
	  
	  
	  
	  
	  //reglement non declarer vente
	  
	  @Column(name = "ref_reglement_non_declarer")
	  private Long numReferenceReglementNonDeclarerCourante;
	  
	  
	  @Column(name = "prefixe_reglement_non_declarer")
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

	public String getPrefixeFAND() {
		return prefixeFAND;
	}

	public void setPrefixeFAND(String prefixeFAND) {
		this.prefixeFAND = prefixeFAND;
	}

	public Long getNumReferenceFactureNDCourante() {
		return numReferenceFactureNDCourante;
	}

	public void setNumReferenceFactureNDCourante(Long numReferenceFactureNDCourante) {
		this.numReferenceFactureNDCourante = numReferenceFactureNDCourante;
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

	public Long getNumReferenceDevisCourante() {
		return numReferenceDevisCourante;
	}

	public void setNumReferenceDevisCourante(Long numReferenceDevisCourante) {
		this.numReferenceDevisCourante = numReferenceDevisCourante;
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

	public String getPrefixeBC() {
		return prefixeBC;
	}

	public void setPrefixeBC(String prefixeBC) {
		this.prefixeBC = prefixeBC;
	}

	public String getPrefixeBcAchat() {
		return prefixeBcAchat;
	}

	public void setPrefixeBcAchat(String prefixeBcAchat) {
		this.prefixeBcAchat = prefixeBcAchat;
	}

	public String getPrefixeFactureAchat() {
		return prefixeFactureAchat;
	}

	public void setPrefixeFactureAchat(String prefixeFactureAchat) {
		this.prefixeFactureAchat = prefixeFactureAchat;
	}

	public String getPrefixeBonReception() {
		return prefixeBonReception;
	}

	public void setPrefixeBonReception(String prefixeBonReception) {
		this.prefixeBonReception = prefixeBonReception;
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
	 * @return the numReferenceFactureCourante
	 */
	public Long getNumReferenceFactureCourante() {
		return numReferenceFactureCourante;
	}

	/**
	 * @param numReferenceFactureCourante the numReferenceFactureCourante to set
	 */
	public void setNumReferenceFactureCourante(Long numReferenceFactureCourante) {
		this.numReferenceFactureCourante = numReferenceFactureCourante;
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

	public Long getNumBonTransfertSortieCourante() {
		return numBonTransfertSortieCourante;
	}

	public void setNumBonTransfertSortieCourante(Long numBonTransfertSortieCourante) {
		this.numBonTransfertSortieCourante = numBonTransfertSortieCourante;
	}

	public String getPrefixeBonTransfertSortie() {
		return prefixeBonTransfertSortie;
	}

	public void setPrefixeBonTransfertSortie(String prefixeBonTransfertSortie) {
		this.prefixeBonTransfertSortie = prefixeBonTransfertSortie;
	}

	public Long getNumBonTransfertReceptionCourante() {
		return numBonTransfertReceptionCourante;
	}

	public void setNumBonTransfertReceptionCourante(Long numBonTransfertReceptionCourante) {
		this.numBonTransfertReceptionCourante = numBonTransfertReceptionCourante;
	}

	public String getPrefixeBonTransfertReception() {
		return prefixeBonTransfertReception;
	}

	public void setPrefixeBonTransfertReception(String prefixeBonTransfertReception) {
		this.prefixeBonTransfertReception = prefixeBonTransfertReception;
	}



	
}
