/**
 * @author Zeineb
 * @since 16 nov. 2016 
 */

package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DocumentLivraisonVenteEntity;

@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_RECEPTIONACHAT)
public class ReceptionAchatEntity {

	/** The id. */
	@Id
	@SequenceGenerator(name = "RECEPTIONACHAT_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_RECEPTIONACHAT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEPTIONACHAT_ID_GENERATOR")
	private Long id;

	/** The com site id. */
	@Column(name = "PI_COM_SITE_ID")
	private Long siteId;

	/** The reference. */
	@Column(name = "REFERENCE")
	private String reference;

	/** The prix total */
	@Column(name = "PRIX_TOTAL")
	private Double prixTotal;

	/** The date introduction. */
	@Column(name = "DATE_INTRODUCTION")
	private Calendar dateIntroduction;

	/** The date livraison. */
	@Column(name = "DATE_LIVRAISON")
	private Calendar dateLivraison;

	/** The observations. */
	@Column(name = "OBSERVATIONS")
	private String observations;

	/** The pi pi id. */
	@Column(name = "PI_PI_ID")
	private Long partieIntersseId;

	/** The bl suppression. */
	@Column(name = "BL_SUPPRESSION")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;

	/** The date modification. */
	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;

	/** The date creation. */
	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	/** The date creation. */
	@Column(name = "QUANTITE")
	private Double quantite;

	/** many-to-one association to ProduitCommande. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receptionAchat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProduitReceptionAchatEntity> listProduitReceptions;
	
	/** many-to-one association to TaxeReceptionAchatEntity. */
	@OneToMany(mappedBy = "receptionAchat", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TaxeReceptionAchatEntity> listTaxeReceptionAchat;
	

	/** *** many-to-one association to DocumentReceptionAchat. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receptionAchat", cascade = CascadeType.ALL,orphanRemoval=true)
	private Set<DocumentReceptionAchatEntity> documentReceptionAchat;

	/** The reference. */
	@Column(name = "REFEXTERNE")
	private String refexterne;

	@Column(name = "id_depot")
	private Long idDepot;
	
	
	/** The facture. */
	@Column(name = "facture")
	private Boolean facture;

	
	@Column(name = "GC_MODEPAIEMENT_ID")
	private Long modepaiementId;
	
	
	
	@Column(name = "NATURE_LIVRAISON")
	private String natureLivraison;

	@Column(name = "TOTAL_POURCENTAGE_REMISE")
	private Double totalPourcentageRemise;

	@Column(name = "MONTANTHTAXE")
	private Double montantHTaxe;
	
	
	@Column(name = "MONTANT_TAXE")
	private Double montantTaxe;

	@Column(name = "MONTANT_REMISE")
	private Double montantRemise;
	
	
	@Column(name = "METRAGE_TOTAL")
	private Double metrageTotal;
	
	
	@Column(name = "MONTANTTTC")
	private Double montantTTC;

	
	@Column(name = "REF_COMMANDE")
	private String refCommande ;
	
	
	@Column(name = "type")
	private String type ;
	
	
	/**  refAvoirRetour est la ref facture vente Avoir Retour utilise pour  stock retour. */
	
	@Column(name = "REF_AVOIR_RETOUR")
	private String refAvoirRetour ;
	
	
	
	@Column(name = "REF_BL")
	private String refBL ;
	
	@Column(name = "REF_FACTURE")
	private String refFacture;
	
	
	  @Column(name = "boutique_id")
		private Long boutiqueId;
	  
	  @Column(name="bon_mouvement_entree_id")
	  private Long  bonMouvementEntreeId ;
	  
	
	
	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Set<DocumentReceptionAchatEntity> getDocumentReceptionAchat() {
		return documentReceptionAchat;
	}

	public void setDocumentReceptionAchat(Set<DocumentReceptionAchatEntity> documentReceptionAchat) {
		this.documentReceptionAchat = documentReceptionAchat;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public String getRefBL() {
		return refBL;
	}

	public void setRefBL(String refBL) {
		this.refBL = refBL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefAvoirRetour() {
		return refAvoirRetour;
	}

	public void setRefAvoirRetour(String refAvoirRetour) {
		this.refAvoirRetour = refAvoirRetour;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public String getNatureLivraison() {
		return natureLivraison;
	}

	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}

	public Double getTotalPourcentageRemise() {
		return totalPourcentageRemise;
	}

	public void setTotalPourcentageRemise(Double totalPourcentageRemise) {
		this.totalPourcentageRemise = totalPourcentageRemise;
	}

	public Double getMontantHTaxe() {
		return montantHTaxe;
	}

	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}

	public Double getMontantTaxe() {
		return montantTaxe;
	}

	public void setMontantTaxe(Double montantTaxe) {
		this.montantTaxe = montantTaxe;
	}

	public Double getMontantRemise() {
		return montantRemise;
	}

	public void setMontantRemise(Double montantRemise) {
		this.montantRemise = montantRemise;
	}

	public Double getMetrageTotal() {
		return metrageTotal;
	}

	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}

	public Boolean getFacture() {
		return facture;
	}

	public void setFacture(Boolean facture) {
		this.facture = facture;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}
	
	/** contructeur DropList & Cache */
	public ReceptionAchatEntity(String reference) {
		super();
		this.reference = reference;
	}

	/**
	 * Constructeur par défault
	 */
	public ReceptionAchatEntity() {
	}

	/**
	 * @param id
	 * @param siteId
	 * @param reference
	 * @param prixTotal
	 * @param dateIntroduction
	 * @param dateLivraison
	 * @param observations
	 * @param partieIntersseId
	 * @param blSuppression
	 * @param dateSuppression
	 * @param dateModification
	 * @param dateCreation
	 * @param produitCommandes
	 */
	public ReceptionAchatEntity(Long id, Long siteId, String reference, Double prixTotal, Calendar dateIntroduction,
			Calendar dateLivraison, String observations, Long partieIntersseId, boolean blSuppression,
			Calendar dateSuppression, Calendar dateModification, Calendar dateCreation,
			Set<ProduitReceptionAchatEntity> produitReceptions,
			Set<TaxeReceptionAchatEntity> listTaxeReceptionAchat) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.reference = reference;
		this.prixTotal = prixTotal;
		this.dateIntroduction = dateIntroduction;
		this.dateLivraison = dateLivraison;
		this.observations = observations;
		this.partieIntersseId = partieIntersseId;
		this.blSuppression = blSuppression;
		this.dateSuppression = dateSuppression;
		this.dateModification = dateModification;
		this.dateCreation = dateCreation;
		this.listProduitReceptions = listProduitReceptions;
		this.listTaxeReceptionAchat = listTaxeReceptionAchat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public List<ProduitReceptionAchatEntity> getListProduitReceptions() {
		return listProduitReceptions;
	}

	public void setListProduitReceptions(List<ProduitReceptionAchatEntity> listProduitReceptions) {
		this.listProduitReceptions = listProduitReceptions;
	}

	public Set<TaxeReceptionAchatEntity> getListTaxeReceptionAchat() {
		return listTaxeReceptionAchat;
	}

	public void setListTaxeReceptionAchat(Set<TaxeReceptionAchatEntity> listTaxeReceptionAchat) {
		this.listTaxeReceptionAchat = listTaxeReceptionAchat;
	}

	public Long getBonMouvementEntreeId() {
		return bonMouvementEntreeId;
	}

	public void setBonMouvementEntreeId(Long bonMouvementEntreeId) {
		this.bonMouvementEntreeId = bonMouvementEntreeId;
	}

	
	
	

}
