package com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value;

import java.util.Calendar;
import java.util.List;

/**
 * The Class CommandeAchatValue.
 * 
 * @author Hamdi Etteieb
 * @since 11/09/2018
 */
public class CommandeAchatValue implements Comparable<CommandeAchatValue> {

	/** The id. */
	private Long id;

	/** The site id. */
	private Long siteId;

	/** The reference. */
	private String reference;

	private String refAvantChangement;

	/** The prix total. */
	private Double prixTotal;

	/** The date introduction. */
	private Calendar dateIntroduction;

	/** The date livraison. */
	private Calendar dateLivraison;

	/** The montant H taxe. */
	private Double montantHTaxe;

	/** The montant taxe. */
	// private Double montantTTC;
	private Double montantTaxe;

	/** The montant remise. */
	private Double montantRemise;

	/** The observations. */
	private String observations;

	/** The partie intersse id. */
	private Long partieIntersseId;

	/** The bl suppression. */
	private boolean blSuppression;

	/** The date suppression. */
	private Calendar dateSuppression;

	/** The date modification. */
	private Calendar dateModification;

	/** The date creation. */
	private Calendar dateCreation;

	/** The list produit commandes. */
	private List<ProduitCommandeAchatValue> listProduitCommandes;

	/** The list produit taxes. */
	private List<TaxeCommandeAchatValue> listProduitTaxes;

	/** The Partie intersse abbreviation. */
	private String PartieIntersseAbbreviation;

	/** The quantite. */
	private Double quantite;

	/** The type. */
	private String type;

	/** ***** ajout nouveaux champs 23 03 2018 *****. */
	private Double montantRemiseTotal;

	/** The montant HT total. */
	private Double montantHTTotal;

	/** The montant TTC. */
	private Double montantTTC;

	/** The taxe fodec. */
	// private Double montantTaxes ;
	private Double taxeFodec;

	/** The taux fodec. */
	private Double tauxFodec;

	/** The montant taxe fodec. */
	private Double montantTaxeFodec;

	/** The assiette fodec. */
	private Double assietteFodec;

	/** The taxe TVA. */
	private Double taxeTVA;

	/** The taux TVA. */
	private Double tauxTVA;

	/** The montant taxe TVA. */
	private Double montantTaxeTVA;

	/** The assiette TVA. */
	private Double assietteTVA;

	/** The taxe timbre. */
	private Double taxeTimbre;

	/** The montant taxe timbre. */
	private Double montantTaxeTimbre;

	/** The assiette timbre. */
	private Double assietteTimbre;

	/** The exist fodec. */
	private Boolean existFodec;

	/** The exist TVA. */
	private Boolean existTVA;

	/** The exist timbre. */
	private Boolean existTimbre;

	/** The montant TTC to words. */
	private String montantTTCToWords;

	/** The marche. */
	private String marche;

	/** The modepaiement. */
	private String modepaiement;

	/** The traitement facon list. */
	private String traitementFaconList;

	/** The taxe TVA 7. */
	private String taxeTVA7;

	/** The taxe TVA 13. */
	private String taxeTVA13;

	/** The taux TVA 7. */
	private Double tauxTVA7;

	/** The taux TVA 13. */
	private Double tauxTVA13;

	/** The assiette TVA 7. */
	private Double assietteTVA7;

	/** The assiette TVA 13. */
	private Double assietteTVA13;

	/** The exist TVA 7. */
	private Boolean existTVA7;

	/** The exist TVA 13. */
	private Boolean existTVA13;

	/** The montant taxe TVA 7. */
	private Double montantTaxeTVA7;

	/** The montant taxe TVA 13. */
	private Double montantTaxeTVA13;

	private Boolean reception;

	private Long boutiqueId;

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	public Boolean getReception() {
		return reception;
	}

	public void setReception(Boolean reception) {
		this.reception = reception;
	}

	/**
	 * Gets the montant remise total.
	 *
	 * @return the montant remise total
	 */
	public Double getMontantRemiseTotal() {
		return montantRemiseTotal;
	}

	/**
	 * Sets the montant remise total.
	 *
	 * @param montantRemiseTotal the new montant remise total
	 */
	public void setMontantRemiseTotal(Double montantRemiseTotal) {
		this.montantRemiseTotal = montantRemiseTotal;
	}

	/**
	 * Gets the montant HT total.
	 *
	 * @return the montant HT total
	 */
	public Double getMontantHTTotal() {
		return montantHTTotal;
	}

	/**
	 * Sets the montant HT total.
	 *
	 * @param montantHTTotal the new montant HT total
	 */
	public void setMontantHTTotal(Double montantHTTotal) {
		this.montantHTTotal = montantHTTotal;
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
	 * @param montantTTC the new montant TTC
	 */
	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	/*
	 * public Double getMontantTaxes() { return montantTaxes; }
	 * 
	 * public void setMontantTaxes(Double montantTaxes) { this.montantTaxes =
	 * montantTaxes; }
	 */

	/**
	 * Gets the taxe fodec.
	 *
	 * @return the taxe fodec
	 */
	public Double getTaxeFodec() {
		return taxeFodec;
	}

	/**
	 * Sets the taxe fodec.
	 *
	 * @param taxeFodec the new taxe fodec
	 */
	public void setTaxeFodec(Double taxeFodec) {
		this.taxeFodec = taxeFodec;
	}

	/**
	 * Gets the taux fodec.
	 *
	 * @return the taux fodec
	 */
	public Double getTauxFodec() {
		return tauxFodec;
	}

	/**
	 * Sets the taux fodec.
	 *
	 * @param tauxFodec the new taux fodec
	 */
	public void setTauxFodec(Double tauxFodec) {
		this.tauxFodec = tauxFodec;
	}

	/**
	 * Gets the montant taxe fodec.
	 *
	 * @return the montant taxe fodec
	 */
	public Double getMontantTaxeFodec() {
		return montantTaxeFodec;
	}

	/**
	 * Sets the montant taxe fodec.
	 *
	 * @param montantTaxeFodec the new montant taxe fodec
	 */
	public void setMontantTaxeFodec(Double montantTaxeFodec) {
		this.montantTaxeFodec = montantTaxeFodec;
	}

	/**
	 * Gets the assiette fodec.
	 *
	 * @return the assiette fodec
	 */
	public Double getAssietteFodec() {
		return assietteFodec;
	}

	/**
	 * Sets the assiette fodec.
	 *
	 * @param assietteFodec the new assiette fodec
	 */
	public void setAssietteFodec(Double assietteFodec) {
		this.assietteFodec = assietteFodec;
	}

	/**
	 * Gets the taxe TVA.
	 *
	 * @return the taxe TVA
	 */
	public Double getTaxeTVA() {
		return taxeTVA;
	}

	/**
	 * Sets the taxe TVA.
	 *
	 * @param taxeTVA the new taxe TVA
	 */
	public void setTaxeTVA(Double taxeTVA) {
		this.taxeTVA = taxeTVA;
	}

	/**
	 * Gets the taux TVA.
	 *
	 * @return the taux TVA
	 */
	public Double getTauxTVA() {
		return tauxTVA;
	}

	/**
	 * Sets the taux TVA.
	 *
	 * @param tauxTVA the new taux TVA
	 */
	public void setTauxTVA(Double tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	/**
	 * Gets the montant taxe TVA.
	 *
	 * @return the montant taxe TVA
	 */
	public Double getMontantTaxeTVA() {
		return montantTaxeTVA;
	}

	/**
	 * Sets the montant taxe TVA.
	 *
	 * @param montantTaxeTVA the new montant taxe TVA
	 */
	public void setMontantTaxeTVA(Double montantTaxeTVA) {
		this.montantTaxeTVA = montantTaxeTVA;
	}

	/**
	 * Gets the assiette TVA.
	 *
	 * @return the assiette TVA
	 */
	public Double getAssietteTVA() {
		return assietteTVA;
	}

	/**
	 * Sets the assiette TVA.
	 *
	 * @param assietteTVA the new assiette TVA
	 */
	public void setAssietteTVA(Double assietteTVA) {
		this.assietteTVA = assietteTVA;
	}

	/**
	 * Gets the taxe timbre.
	 *
	 * @return the taxe timbre
	 */
	public Double getTaxeTimbre() {
		return taxeTimbre;
	}

	/**
	 * Sets the taxe timbre.
	 *
	 * @param taxeTimbre the new taxe timbre
	 */
	public void setTaxeTimbre(Double taxeTimbre) {
		this.taxeTimbre = taxeTimbre;
	}

	/**
	 * Gets the montant taxe timbre.
	 *
	 * @return the montant taxe timbre
	 */
	public Double getMontantTaxeTimbre() {
		return montantTaxeTimbre;
	}

	/**
	 * Sets the montant taxe timbre.
	 *
	 * @param montantTaxeTimbre the new montant taxe timbre
	 */
	public void setMontantTaxeTimbre(Double montantTaxeTimbre) {
		this.montantTaxeTimbre = montantTaxeTimbre;
	}

	/**
	 * Gets the assiette timbre.
	 *
	 * @return the assiette timbre
	 */
	public Double getAssietteTimbre() {
		return assietteTimbre;
	}

	/**
	 * Sets the assiette timbre.
	 *
	 * @param assietteTimbre the new assiette timbre
	 */
	public void setAssietteTimbre(Double assietteTimbre) {
		this.assietteTimbre = assietteTimbre;
	}

	/**
	 * Gets the exist fodec.
	 *
	 * @return the exist fodec
	 */
	public Boolean getExistFodec() {
		return existFodec;
	}

	/**
	 * Sets the exist fodec.
	 *
	 * @param existFodec the new exist fodec
	 */
	public void setExistFodec(Boolean existFodec) {
		this.existFodec = existFodec;
	}

	/**
	 * Gets the exist TVA.
	 *
	 * @return the exist TVA
	 */
	public Boolean getExistTVA() {
		return existTVA;
	}

	/**
	 * Sets the exist TVA.
	 *
	 * @param existTVA the new exist TVA
	 */
	public void setExistTVA(Boolean existTVA) {
		this.existTVA = existTVA;
	}

	/**
	 * Gets the exist timbre.
	 *
	 * @return the exist timbre
	 */
	public Boolean getExistTimbre() {
		return existTimbre;
	}

	/**
	 * Sets the exist timbre.
	 *
	 * @param existTimbre the new exist timbre
	 */
	public void setExistTimbre(Boolean existTimbre) {
		this.existTimbre = existTimbre;
	}

	/**
	 * Gets the montant TTC to words.
	 *
	 * @return the montant TTC to words
	 */
	public String getMontantTTCToWords() {
		return montantTTCToWords;
	}

	/**
	 * Sets the montant TTC to words.
	 *
	 * @param montantTTCToWords the new montant TTC to words
	 */
	public void setMontantTTCToWords(String montantTTCToWords) {
		this.montantTTCToWords = montantTTCToWords;
	}

	/**
	 * Gets the marche.
	 *
	 * @return the marche
	 */
	public String getMarche() {
		return marche;
	}

	/**
	 * Sets the marche.
	 *
	 * @param marche the new marche
	 */
	public void setMarche(String marche) {
		this.marche = marche;
	}

	/**
	 * Gets the modepaiement.
	 *
	 * @return the modepaiement
	 */
	public String getModepaiement() {
		return modepaiement;
	}

	/**
	 * Sets the modepaiement.
	 *
	 * @param modepaiement the new modepaiement
	 */
	public void setModepaiement(String modepaiement) {
		this.modepaiement = modepaiement;
	}

	/**
	 * Gets the traitement facon list.
	 *
	 * @return the traitement facon list
	 */
	public String getTraitementFaconList() {
		return traitementFaconList;
	}

	/**
	 * Sets the traitement facon list.
	 *
	 * @param traitementFaconList the new traitement facon list
	 */
	public void setTraitementFaconList(String traitementFaconList) {
		this.traitementFaconList = traitementFaconList;
	}

	/**
	 * Gets the taxe TVA 7.
	 *
	 * @return the taxe TVA 7
	 */
	public String getTaxeTVA7() {
		return taxeTVA7;
	}

	/**
	 * Sets the taxe TVA 7.
	 *
	 * @param taxeTVA7 the new taxe TVA 7
	 */
	public void setTaxeTVA7(String taxeTVA7) {
		this.taxeTVA7 = taxeTVA7;
	}

	/**
	 * Gets the taxe TVA 13.
	 *
	 * @return the taxe TVA 13
	 */
	public String getTaxeTVA13() {
		return taxeTVA13;
	}

	/**
	 * Sets the taxe TVA 13.
	 *
	 * @param taxeTVA13 the new taxe TVA 13
	 */
	public void setTaxeTVA13(String taxeTVA13) {
		this.taxeTVA13 = taxeTVA13;
	}

	/**
	 * Gets the taux TVA 7.
	 *
	 * @return the taux TVA 7
	 */
	public Double getTauxTVA7() {
		return tauxTVA7;
	}

	/**
	 * Sets the taux TVA 7.
	 *
	 * @param tauxTVA7 the new taux TVA 7
	 */
	public void setTauxTVA7(Double tauxTVA7) {
		this.tauxTVA7 = tauxTVA7;
	}

	/**
	 * Gets the taux TVA 13.
	 *
	 * @return the taux TVA 13
	 */
	public Double getTauxTVA13() {
		return tauxTVA13;
	}

	/**
	 * Sets the taux TVA 13.
	 *
	 * @param tauxTVA13 the new taux TVA 13
	 */
	public void setTauxTVA13(Double tauxTVA13) {
		this.tauxTVA13 = tauxTVA13;
	}

	/**
	 * Gets the assiette TVA 7.
	 *
	 * @return the assiette TVA 7
	 */
	public Double getAssietteTVA7() {
		return assietteTVA7;
	}

	/**
	 * Sets the assiette TVA 7.
	 *
	 * @param assietteTVA7 the new assiette TVA 7
	 */
	public void setAssietteTVA7(Double assietteTVA7) {
		this.assietteTVA7 = assietteTVA7;
	}

	/**
	 * Gets the assiette TVA 13.
	 *
	 * @return the assiette TVA 13
	 */
	public Double getAssietteTVA13() {
		return assietteTVA13;
	}

	/**
	 * Sets the assiette TVA 13.
	 *
	 * @param assietteTVA13 the new assiette TVA 13
	 */
	public void setAssietteTVA13(Double assietteTVA13) {
		this.assietteTVA13 = assietteTVA13;
	}

	/**
	 * Gets the exist TVA 7.
	 *
	 * @return the exist TVA 7
	 */
	public Boolean getExistTVA7() {
		return existTVA7;
	}

	/**
	 * Sets the exist TVA 7.
	 *
	 * @param existTVA7 the new exist TVA 7
	 */
	public void setExistTVA7(Boolean existTVA7) {
		this.existTVA7 = existTVA7;
	}

	/**
	 * Gets the exist TVA 13.
	 *
	 * @return the exist TVA 13
	 */
	public Boolean getExistTVA13() {
		return existTVA13;
	}

	/**
	 * Sets the exist TVA 13.
	 *
	 * @param existTVA13 the new exist TVA 13
	 */
	public void setExistTVA13(Boolean existTVA13) {
		this.existTVA13 = existTVA13;
	}

	/**
	 * Gets the montant taxe TVA 7.
	 *
	 * @return the montant taxe TVA 7
	 */
	public Double getMontantTaxeTVA7() {
		return montantTaxeTVA7;
	}

	/**
	 * Sets the montant taxe TVA 7.
	 *
	 * @param montantTaxeTVA7 the new montant taxe TVA 7
	 */
	public void setMontantTaxeTVA7(Double montantTaxeTVA7) {
		this.montantTaxeTVA7 = montantTaxeTVA7;
	}

	/**
	 * Gets the montant taxe TVA 13.
	 *
	 * @return the montant taxe TVA 13
	 */
	public Double getMontantTaxeTVA13() {
		return montantTaxeTVA13;
	}

	/**
	 * Sets the montant taxe TVA 13.
	 *
	 * @param montantTaxeTVA13 the new montant taxe TVA 13
	 */
	public void setMontantTaxeTVA13(Double montantTaxeTVA13) {
		this.montantTaxeTVA13 = montantTaxeTVA13;
	}

	/**
	 * ***************************.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @param siteId the new site id
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
	 * @param reference the new reference
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
	 * @param prixTotal the new prix total
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
	 * @param dateIntroduction the new date introduction
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
	 * @param dateLivraison the new date livraison
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
	 * @param observations the new observations
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
	 * @param partieIntersseId the new partie intersse id
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
	 * @param blSuppression the new bl suppression
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
	 * @param dateSuppression the new date suppression
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
	 * @param dateModification the new date modification
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
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the list produit commandes.
	 *
	 * @return the list produit commandes
	 */
	public List<ProduitCommandeAchatValue> getListProduitCommandes() {
		return listProduitCommandes;
	}

	/**
	 * Sets the list produit commandes.
	 *
	 * @param listProduitCommandes the new list produit commandes
	 */
	public void setListProduitCommandes(List<ProduitCommandeAchatValue> listProduitCommandes) {
		this.listProduitCommandes = listProduitCommandes;
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

	/**
	 * Gets the quantite.
	 *
	 * @return the quantite
	 */
	public Double getQuantite() {
		return quantite;
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
	 * @param montantHTaxe the new montant H taxe
	 */
	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
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
	 * @param montantTaxe the new montant taxe
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
	 * @param montantRemise the new montant remise
	 */
	public void setMontantRemise(Double montantRemise) {
		this.montantRemise = montantRemise;
	}

	/**
	 * Gets the list produit taxes.
	 *
	 * @return the list produit taxes
	 */
	public List<TaxeCommandeAchatValue> getListProduitTaxes() {
		return listProduitTaxes;
	}

	/**
	 * Sets the list produit taxes.
	 *
	 * @param listProduitTaxes the new list produit taxes
	 */
	public void setListProduitTaxes(List<TaxeCommandeAchatValue> listProduitTaxes) {
		this.listProduitTaxes = listProduitTaxes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommandeAchatValue [id=" + id + ", siteId=" + siteId + ", reference=" + reference + ", prixTotal="
				+ prixTotal + ", dateIntroduction=" + dateIntroduction + ", dateLivraison=" + dateLivraison
				+ ", observations=" + observations + ", partieIntersseId=" + partieIntersseId + ", blSuppression="
				+ blSuppression + ", dateSuppression=" + dateSuppression + ", dateModification=" + dateModification
				+ ", dateCreation=" + dateCreation + ", listProduitCommandes=" + listProduitCommandes
				+ ", PartieIntersseAbbreviation=" + PartieIntersseAbbreviation + ", quantite=" + quantite + "]";
	}

	/**
	 * Sets the quantite.
	 *
	 * @param quantite the new quantite
	 */
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CommandeAchatValue o) {
		CommandeAchatValue commandeAchatValue = (CommandeAchatValue) o;
		return (commandeAchatValue.getReference().compareTo(reference));
	}

}
