
package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value;

import java.util.Calendar;
import java.util.Set;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;

/**
 * The Class ProduitReceptionAchatValue.
 * 
 * @author Hamdi Etteieb
 */
public class ProduitReceptionAchatValue implements Comparable<ProduitReceptionAchatValue> {

	/** The id. */
	private Long id;

	/** The commande vente id. */
	private Long commandeAchatId;

	/** The produit id. */
	private Long produitId;

	/** The prix unitaire. */
	private Double prixUnitaire;

	/** The quantite. */
	private Double quantite;

	/** The date livraison. */
	private Calendar dateLivraison;

	/** The partie intersse id. */
	private Long partieIntersseId;

	/** The Partie intersse abbreviation. */
	private String PartieIntersseAbbreviation;

	private Set<ProduitSerialisableValue> produitsSerialisable;

	private boolean serialisable;

	private String numeroSeries;

	private ReceptionAchatValue receptionAchatValue;

	private ArticleValue articleValue;

	private Double quantiteAncien;

	// Ajouté par samer le 08.04.20

	private Double prixUnitaireHT;
	private Double prixTotalHT;
	private Double remise;
	private Long taxeId;
	

	private String referenceArticle;
	

	private String designation;
	
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

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Double getPrixTotalHT() {
		return prixTotalHT;
	}

	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}

	public Double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	public void setPrixUnitaireHT(Double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}

	public Double getQuantiteAncien() {
		return quantiteAncien;
	}

	public void setQuantiteAncien(Double quantiteAncien) {
		this.quantiteAncien = quantiteAncien;
	}

	

	public ArticleValue getArticleValue() {
		return articleValue;
	}

	public void setArticleValue(ArticleValue articleValue) {
		this.articleValue = articleValue;
	}

	public ReceptionAchatValue getReceptionAchatValue() {
		return receptionAchatValue;
	}

	public void setReceptionAchatValue(ReceptionAchatValue receptionAchatValue) {
		this.receptionAchatValue = receptionAchatValue;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public Set<ProduitSerialisableValue> getProduitsSerialisable() {
		return produitsSerialisable;
	}

	public void setProduitsSerialisable(Set<ProduitSerialisableValue> produitsSerialisable) {
		this.produitsSerialisable = produitsSerialisable;
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
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the produit id.
	 *
	 * @return the produit id
	 */
	public Long getProduitId() {
		return produitId;
	}

	/**
	 * Sets the produit id.
	 *
	 * @param produitId the new produit id
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
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
	 * @param prixUnitaire the new prix unitaire
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
	 * @param quantite the new quantite
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
	 * @param dateLivraison the new date livraison
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
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
	 * @param partieIntersseId the new partie intersse id
	 */
	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	/**
	 * Gets the partie intersse abbreviation.
	 *
	 * @return the partie intersse abbreviation
	 */
	public String getPartieIntersseAbbreviation() {
		return PartieIntersseAbbreviation;
	}

	/**
	 * Sets the partie intersse abbreviation.
	 *
	 * @param partieIntersseAbbreviation the new partie intersse abbreviation
	 */
	public void setPartieIntersseAbbreviation(String partieIntersseAbbreviation) {
		PartieIntersseAbbreviation = partieIntersseAbbreviation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProduitReceptionAchatValue [id=" + id + ", commandeAchatId=" + commandeAchatId + ", produitId="
				+ produitId + ", prixUnitaire=" + prixUnitaire + ", quantite=" + quantite + ", dateLivraison="
				+ dateLivraison + ", partieIntersseId=" + partieIntersseId + ", PartieIntersseAbbreviation="
				+ PartieIntersseAbbreviation + "]";
	}

	@Override
	public int compareTo(ProduitReceptionAchatValue o) {
		ProduitReceptionAchatValue element = (ProduitReceptionAchatValue) o;
		return element.getProduitId().compareTo(produitId);
	}

	public Long getCommandeAchatId() {
		return commandeAchatId;
	}

	public void setCommandeAchatId(Long commandeAchatId) {
		this.commandeAchatId = commandeAchatId;
	}

}
