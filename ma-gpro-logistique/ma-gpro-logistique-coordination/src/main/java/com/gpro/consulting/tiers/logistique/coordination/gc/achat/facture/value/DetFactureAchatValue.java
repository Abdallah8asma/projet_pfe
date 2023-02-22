package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.Calendar;
import java.util.Set;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;

/**
 * The Class DetFactureAchatValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class DetFactureAchatValue implements Comparable<DetFactureAchatValue> {

	/** The id. */
	private Long id;

	/** The produit id. */
	private Long produitId;

	/** The quantite. */
	private Double quantite;

	/** The unite. */
	private String unite;

	/** The nombre colis. */
	private Long nombreColis;

	/** The facture achat id. */
	private Long factureAchatId;

	/** The remise. */
	private Double remise;

	/** The choix. */
	private String choix;

	/** The produit designation. */
	// this values used only on validate action
	private String produitDesignation;

	/** The produit reference. */
	private String produitReference;

	/** The prix unitaire HT. */
	private Double prixUnitaireHT;

	/** The prix total HT. */
	private Double prixTotalHT;

	/** The traitement facon id. */
	// Added on 11/10/2016 by Zeineb Medimagh
	private Long traitementFaconId;

	/** The ref mise list. */
	// Added on 17/10/2016 by Zeineb Medimagh
	private String refMiseList;

	/** The fiche id. */
	private Long ficheId;

	/** The montan taxe TVA. */
	private Double montanTaxeTVA;

	private boolean serialisable;

	private String numeroSeries;

	private Long taxeId;

	private Set<ProduitSerialisableValue> produitsSerialisable;

	private Long partieIntId;

	 private String clientAbreviation;

	 private String referenceBlExterne;
	 private String infoLivraison;

	private Calendar dateIntroduction;

	private String factureReference;

	

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	public Set<ProduitSerialisableValue> getProduitsSerialisable() {
		return produitsSerialisable;
	}

	public void setProduitsSerialisable(Set<ProduitSerialisableValue> produitsSerialisable) {
		this.produitsSerialisable = produitsSerialisable;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public String getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DetFactureAchatValue o) {
		DetFactureAchatValue element = (DetFactureAchatValue) o;
		return (element.getFicheId().compareTo(ficheId));
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
	 * Gets the unite.
	 *
	 * @return the unite
	 */
	public String getUnite() {
		return unite;
	}

	/**
	 * Sets the unite.
	 *
	 * @param unite the new unite
	 */
	public void setUnite(String unite) {
		this.unite = unite;
	}

	/**
	 * Gets the nombre colis.
	 *
	 * @return the nombre colis
	 */
	public Long getNombreColis() {
		return nombreColis;
	}

	/**
	 * Sets the nombre colis.
	 *
	 * @param nombreColis the new nombre colis
	 */
	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}

	/**
	 * Gets the facture achat id.
	 *
	 * @return the facture achat id
	 */
	public Long getFactureAchatId() {
		return factureAchatId;
	}

	/**
	 * Sets the facture achat id.
	 *
	 * @param factureAchatId the new facture achat id
	 */
	public void setFactureAchatId(Long factureAchatId) {
		this.factureAchatId = factureAchatId;
	}

	/**
	 * Gets the remise.
	 *
	 * @return the remise
	 */
	public Double getRemise() {
		return remise;
	}

	/**
	 * Sets the remise.
	 *
	 * @param remise the new remise
	 */
	public void setRemise(Double remise) {
		this.remise = remise;
	}

	/**
	 * Gets the produit designation.
	 *
	 * @return the produit designation
	 */
	public String getProduitDesignation() {
		return produitDesignation;
	}

	/**
	 * Sets the produit designation.
	 *
	 * @param produitDesignation the new produit designation
	 */
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	/**
	 * Gets the produit reference.
	 *
	 * @return the produit reference
	 */
	public String getProduitReference() {
		return produitReference;
	}

	/**
	 * Sets the produit reference.
	 *
	 * @param produitReference the new produit reference
	 */
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	/**
	 * Gets the prix unitaire HT.
	 *
	 * @return the prix unitaire HT
	 */
	public Double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	/**
	 * Sets the prix unitaire HT.
	 *
	 * @param prixUnitaireHT the new prix unitaire HT
	 */
	public void setPrixUnitaireHT(Double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}

	/**
	 * Gets the prix total HT.
	 *
	 * @return the prix total HT
	 */
	public Double getPrixTotalHT() {
		return prixTotalHT;
	}

	/**
	 * Sets the prix total HT.
	 *
	 * @param prixTotalHT the new prix total HT
	 */
	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}

	/**
	 * Gets the choix.
	 *
	 * @return the choix
	 */
	public String getChoix() {
		return choix;
	}

	/**
	 * Sets the choix.
	 *
	 * @param choix the new choix
	 */
	public void setChoix(String choix) {
		this.choix = choix;
	}

	/**
	 * Gets the traitement facon id.
	 *
	 * @return the traitement facon id
	 */
	public Long getTraitementFaconId() {
		return traitementFaconId;
	}

	/**
	 * Sets the traitement facon id.
	 *
	 * @param traitementFaconId the new traitement facon id
	 */
	public void setTraitementFaconId(Long traitementFaconId) {
		this.traitementFaconId = traitementFaconId;
	}

	/**
	 * Gets the ref mise list.
	 *
	 * @return the ref mise list
	 */
	public String getRefMiseList() {
		return refMiseList;
	}

	/**
	 * Sets the ref mise list.
	 *
	 * @param refMiseList the new ref mise list
	 */
	public void setRefMiseList(String refMiseList) {
		this.refMiseList = refMiseList;
	}

	/**
	 * Gets the fiche id.
	 *
	 * @return the fiche id
	 */
	public Long getFicheId() {
		return ficheId;
	}

	/**
	 * Sets the fiche id.
	 *
	 * @param ficheId the new fiche id
	 */
	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}

	/**
	 * Gets the montan taxe TVA.
	 *
	 * @return the montan taxe TVA
	 */
	public Double getMontanTaxeTVA() {
		return montanTaxeTVA;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}
   


	public String getReferenceBlExterne() {
		return referenceBlExterne;
	}

	public void setReferenceBlExterne(String referenceBlExterne) {
		this.referenceBlExterne = referenceBlExterne;
	}

	public String getInfoLivraison() {
		return infoLivraison;
	}

	public void setInfoLivraison(String infoLivraison) {
		this.infoLivraison = infoLivraison;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public void setFactureReference(String factureReference) {
		this.factureReference = factureReference;
	}

	/**
	 * Sets the montan taxe TVA.
	 *
	 * @param montanTaxeTVA the new montan taxe TVA
	 */
	public void setMontanTaxeTVA(Double montanTaxeTVA) {
		this.montanTaxeTVA = montanTaxeTVA;
	}

	public String getFactureReference() {
		return factureReference;
	}

}
