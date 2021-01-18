/**
 * @author Zeineb
 * @since 16 nov. 2016 
 */

package com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie;

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

// TODO: Auto-generated Javadoc
/**
 * The Class CommandeVenteEntity.
 */
@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_COMMANDEVENTE)
public class CommandeVenteEntity {

	/** The id. */
	@Id
	@SequenceGenerator(name = "COMMANDEVENTE_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_COMMANDEVENTE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMANDEVENTE_ID_GENERATOR")
	private Long id;

	/** The com site id. */
	@Column(name = "PI_COM_SITE_ID")
	private Long siteId;

	/** The reference. */
	@Column(name = "REFERENCE")
	private String reference;

	/** The reference. */
	@Column(name = "TYPE")
	private String type;

	/** The prix total. */
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
	
	/** The livre. */
	@Column(name = "livre")
	private Boolean livre;
	
	
	@Column(name = "GC_REGLEMENT_ID")
	private Long reglementId;
	
	@Column(name = "boutique_id")
	private Long boutiqueId;
	
	

	

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Long getReglementId() {
		return reglementId;
	}

	public void setReglementId(Long reglementId) {
		this.reglementId = reglementId;
	}

	/**
	 * Gets the montant H taxe.
	 *
	 * @return the montant H taxe
	 */
	public Double getMontantHTaxe() {
		return montantHTaxe;
	}

	/**
	 * Sets the montant H taxe.
	 *
	 * @param montantHTaxe
	 *            the new montant H taxe
	 */
	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}

	/**
	 * Gets the montant TTC.
	 *
	 * @return the montant TTC
	 */
	public Double getMontantTTC() {
		return montantTTC;
	}

	/**
	 * Sets the montant TTC.
	 *
	 * @param montantTTC
	 *            the new montant TTC
	 */
	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	/**
	 * Gets the montant taxe.
	 *
	 * @return the montant taxe
	 */
	public Double getMontantTaxe() {
		return montantTaxe;
	}

	/**
	 * Sets the montant taxe.
	 *
	 * @param montantTaxe
	 *            the new montant taxe
	 */
	public void setMontantTaxe(Double montantTaxe) {
		this.montantTaxe = montantTaxe;
	}

	/**
	 * Gets the montant remise.
	 *
	 * @return the montant remise
	 */
	public Double getMontantRemise() {
		return montantRemise;
	}

	/**
	 * Sets the montant remise.
	 *
	 * @param montantRemise
	 *            the new montant remise
	 */
	public void setMontantRemise(Double montantRemise) {
		this.montantRemise = montantRemise;
	}

	/** many-to-one association to ProduitCommande. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commandeVente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProduitCommandeEntity> listProduitCommandes;

	/** The montant H taxe. */
	@Column(name = "MONTANTHTAXE")
	private Double montantHTaxe;

	/** The montant TTC. */
	@Column(name = "MONTANTTTC")
	private Double montantTTC;

	/** The montant taxe. */
	@Column(name = "MONTANT_TAXE")
	private Double montantTaxe;

	/** The montant remise. */
	@Column(name = "MONTANT_REMISE")
	private Double montantRemise;
	

	
	
	@Column(name = "devise")
	private Long devise;
	
	
	@Column(name = "taux_conversion")
	private Double tauxConversion;
	 
	@Column(name = "montant_converti")
	private Double montantConverti;
	
	
	
	 
	public Long getDevise() {
		return devise;
	}

	public void setDevise(Long devise) {
		this.devise = devise;
	}

	public Double getTauxConversion() {
		return tauxConversion;
	}

	public void setTauxConversion(Double tauxConversion) {
		this.tauxConversion = tauxConversion;
	}

	public Double getMontantConverti() {
		return montantConverti;
	}

	public void setMontantConverti(Double montantConverti) {
		this.montantConverti = montantConverti;
	}

	/** many-to-one association to TaxeCommande. added on 27/03/2018 */
	@OneToMany(mappedBy = "commandeVente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TaxeCommandeEntity> listTaxeCommande;

	/**
	 * Gets the list taxe commande.
	 *
	 * @return the list taxe commande
	 */
	public List<TaxeCommandeEntity> getListTaxeCommande() {
		return listTaxeCommande;
	}

	/**
	 * Sets the list taxe commande.
	 *
	 * @param listTaxeCommande
	 *            the new list taxe commande
	 */
	public void setListTaxeCommande(List<TaxeCommandeEntity> listTaxeCommande) {
		this.listTaxeCommande = listTaxeCommande;
	}

	/**
	 * Constructeur par défault.
	 */
	public CommandeVenteEntity() {
	}

	/**
	 * Instantiates a new commande vente entity.
	 *
	 * @param id
	 *            the id
	 * @param siteId
	 *            the site id
	 * @param reference
	 *            the reference
	 * @param prixTotal
	 *            the prix total
	 * @param dateIntroduction
	 *            the date introduction
	 * @param dateLivraison
	 *            the date livraison
	 * @param observations
	 *            the observations
	 * @param partieIntersseId
	 *            the partie intersse id
	 * @param blSuppression
	 *            the bl suppression
	 * @param dateSuppression
	 *            the date suppression
	 * @param dateModification
	 *            the date modification
	 * @param dateCreation
	 *            the date creation
	 * @param produitCommandes
	 *            the produit commandes
	 */
	public CommandeVenteEntity(Long id, Long siteId, String reference, Double prixTotal, Calendar dateIntroduction,
			Calendar dateLivraison, String observations, Long partieIntersseId, boolean blSuppression,
			Calendar dateSuppression, Calendar dateModification, Calendar dateCreation,
			Set<ProduitCommandeEntity> produitCommandes) {
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
		this.listProduitCommandes = listProduitCommandes;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the site id.
	 *
	 * @return the site id
	 */
	public Long getSiteId() {
		return siteId;
	}

	/**
	 * Sets the site id.
	 *
	 * @param siteId
	 *            the new site id
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference
	 *            the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the prix total.
	 *
	 * @return the prix total
	 */
	public Double getPrixTotal() {
		return prixTotal;
	}

	/**
	 * Sets the prix total.
	 *
	 * @param prixTotal
	 *            the new prix total
	 */
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	/**
	 * Gets the date introduction.
	 *
	 * @return the date introduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * Sets the date introduction.
	 *
	 * @param dateIntroduction
	 *            the new date introduction
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	/**
	 * Gets the date livraison.
	 *
	 * @return the date livraison
	 */
	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	/**
	 * Sets the date livraison.
	 *
	 * @param dateLivraison
	 *            the new date livraison
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	/**
	 * Gets the observations.
	 *
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * Sets the observations.
	 *
	 * @param observations
	 *            the new observations
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * Gets the partie intersse id.
	 *
	 * @return the partie intersse id
	 */
	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	/**
	 * Sets the partie intersse id.
	 *
	 * @param partieIntersseId
	 *            the new partie intersse id
	 */
	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	/**
	 * Checks if is bl suppression.
	 *
	 * @return true, if is bl suppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression
	 *            the new bl suppression
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression
	 *            the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification
	 *            the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation
	 *            the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the list produit commandes.
	 *
	 * @return the list produit commandes
	 */
	public List<ProduitCommandeEntity> getListProduitCommandes() {
		return listProduitCommandes;
	}

	/**
	 * Sets the list produit commandes.
	 *
	 * @param listProduitCommandes
	 *            the new list produit commandes
	 */
	public void setListProduitCommandes(List<ProduitCommandeEntity> listProduitCommandes) {
		this.listProduitCommandes = listProduitCommandes;
	}

	/**
	 * Gets the quantite.
	 *
	 * @return the quantite
	 */
	public Double getQuantite() {
		return quantite;
	}

	/**
	 * Sets the quantite.
	 *
	 * @param quantite
	 *            the new quantite
	 */
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommandeVenteEntity [id=" + id + ", siteId=" + siteId + ", reference=" + reference + ", prixTotal="
				+ prixTotal + ", dateIntroduction=" + dateIntroduction + ", dateLivraison=" + dateLivraison
				+ ", observations=" + observations + ", partieIntersseId=" + partieIntersseId + ", blSuppression="
				+ blSuppression + ", dateSuppression=" + dateSuppression + ", dateModification=" + dateModification
				+ ", dateCreation=" + dateCreation + ", quantite=" + quantite + ", listProduitCommandes="
				+ listProduitCommandes + "]";
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public Boolean getLivre() {
		return livre;
	}

	public void setLivre(Boolean livre) {
		this.livre = livre;
	}
	
	
	

}
