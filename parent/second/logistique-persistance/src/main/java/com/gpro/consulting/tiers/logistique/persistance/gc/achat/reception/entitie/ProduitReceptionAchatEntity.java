
package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;

/**
 * The Class ProduitReceptionAchatEntity.
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_PRODUITRECEPTIONACHAT)
public class ProduitReceptionAchatEntity {

	/** The id. */
	@Id
	@SequenceGenerator(name = "PRODUITRECEPTIONACHAT_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_PRODUITRECEPTIONACHAT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUITRECEPTIONACHAT_ID_GENERATOR")
	private Long id;

	/** many-to-one association to receptionAchat**. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GC_RECEPTIONACHAT_ID")
	private ReceptionAchatEntity receptionAchat;

	/** many-to-one association to Produit**. */

	@Column(name = "EB_ARTICLE_ID")
	private Long produit;

	/** The prix. */
	@Column(name = "PRIX")
	private Double prixUnitaire;

	/** The quantite. */
	@Column(name = "QUANTITE")
	private Double quantite;

	/** The date livraison. */
	@Column(name = "DATE_LIVRAISON")
	private Calendar dateLivraison;
	
	@Column(name = "serialisable")
	private boolean serialisable;
	
	
	@Column(name="numero_series")
	private String numeroSeries;
	
	
	
	
	
	//Ajoute le 09.04.20
	@Column(name="PUHT")
	private Double prixUnitaireHT;
	
	@Column(name="PTHT")
	private Double prixTotalHT;
	
	@Column(name="REMISE")
	private Double remise;

	@Column(name="taxe_id")
	private Long taxeId;
	
	
	@Column(name="reference_article")
	private String referenceArticle;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name="UNITE")
	private String unite;
	
	
	
	
	

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getReferenceArticle() {
		return referenceArticle;
	}

	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	public String getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

	public Double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	public void setPrixUnitaireHT(Double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}

	public Double getPrixTotalHT() {
		return prixTotalHT;
	}

	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	/**
	 * Instantiates a new produit reception achat entity.
	 */
	public ProduitReceptionAchatEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new produit reception achat entity.
	 *
	 * @param id
	 *            the id
	 * @param receptionAchat
	 *            the reception Achat
	 * @param produit
	 *            the produit
	 * @param prix
	 *            the prix
	 * @param quantite
	 *            the quantite
	 * @param dateLivraison
	 *            the date livraison
	 */
	public ProduitReceptionAchatEntity(Long id, ReceptionAchatEntity receptionAchat, Long produit, Double prix,
			Double quantite, Calendar dateLivraison) {
		super();
		this.id = id;
		this.receptionAchat = receptionAchat;
		this.produit = produit;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.dateLivraison = dateLivraison;
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
	 * Gets the reception Achat.
	 *
	 * @return the reception Achat
	 */
	public ReceptionAchatEntity getReceptionAchat() {
		return receptionAchat;
	}

	/**
	 * Sets the reception Achat.
	 *
	 * @param receptionAchat
	 *            the new reception Achat
	 */
	public void setReceptionAchat(ReceptionAchatEntity receptionAchat) {
		this.receptionAchat = receptionAchat;
	}

	/**
	 * Gets the produit.
	 *
	 * @return the produit
	 */
	public Long getProduit() {
		return produit;
	}

	/**
	 * Sets the produit.
	 *
	 * @param produit
	 *            the new produit
	 */
	public void setProduit(Long produit) {
		this.produit = produit;
	}

	/**
	 * Gets the prix unitaire.
	 *
	 * @return the prix unitaire
	 */
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * Sets the prix unitaire.
	 *
	 * @param prixUnitaire
	 *            the new prix unitaire
	 */
	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
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

}
