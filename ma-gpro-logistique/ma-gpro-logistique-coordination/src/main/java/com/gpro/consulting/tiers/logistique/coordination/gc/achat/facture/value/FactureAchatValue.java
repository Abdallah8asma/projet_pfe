package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class FactureAchatValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class FactureAchatValue implements Comparable<FactureAchatValue> {

	/** The id. */
	private Long id;

	/** The reference. */
	private String reference;

	private String refAvantChangement;

	/** The date. */
	private Calendar date;

	/** The montant H taxe. */
	private Double montantHTaxe;

	/** The montant TTC. */
	private Double montantTTC;

	/** The montant taxe. */
	private Double montantTaxe;

	/** The montant remise. */
	private Double montantRemise;

	/** The observations. */
	private String observations;

	/** The info livraison. */
	private String infoLivraison;

	/** The partie int id. */
	private Long partieIntId;

	/** The metrage total. */
	private Double metrageTotal;

	/** The etat. */
	private String etat;

	/** The bl suppression. */
	private Boolean blSuppression;

	/** The date suppression. */
	private Calendar dateSuppression;

	/** The date creation. */
	private Calendar dateCreation;

	/** The date modification. */
	private Calendar dateModification;

	/** The version. */
	private String version;

	/** The list det facture achat. */
	private List<DetFactureAchatValue> listDetFactureAchat;

	/** The list taxe facture. */
	private List<TaxeFactureAchatValue> listTaxeFacture;

	private Set<DocumentFactureAchatValue> listDocFactureAchat = new HashSet<DocumentFactureAchatValue>();

	/** The modepaiement id. */
	private Long modepaiementId;

	/** The partie int abreviation. */
	private String partieIntAbreviation;

	/** The modepaiement. */
	private String modepaiement;

	/** The marche. */
	private String marche;

	/** The ref factures. */
	private String refFactures;

	/** The type. */
	private String type; // Normal, Avoir

	/** The nature livraison. */
	// Added on 11/10/2016 by Zeineb Medimagh
	private String natureLivraison;

	/** The fiche id. */
	// Added on 17/10/2016 by Zeineb Medimagh
	private Long ficheId;

	/** The info livraison externe. */
	// Added on 23/05/2018 By Ghazi
	private String infoLivraisonExterne;

	// Added on 25/05/2018

	/** The id marche. */
	private Long idMarche;

	private String raison;

	private String nature;

	private Calendar dateIntroduction;

	private Long boutiqueId;

	private boolean avecRetourStock;

	private Long idDepot;

	
	private boolean declarer;
	
	
	private boolean forcerCalculMontant;
	
	
	private String refexterne;
	
	
	
	
	
	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	public boolean isForcerCalculMontant() {
		return forcerCalculMontant;
	}

	public void setForcerCalculMontant(boolean forcerCalculMontant) {
		this.forcerCalculMontant = forcerCalculMontant;
	}

	public boolean isDeclarer() {
		return declarer;
	}

	public void setDeclarer(boolean declarer) {
		this.declarer = declarer;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public boolean isAvecRetourStock() {
		return avecRetourStock;
	}

	public void setAvecRetourStock(boolean avecRetourStock) {
		this.avecRetourStock = avecRetourStock;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public Set<DocumentFactureAchatValue> getListDocFactureAchat() {
		return listDocFactureAchat;
	}

	public void setListDocFactureAchat(Set<DocumentFactureAchatValue> listDocFactureAchat) {
		this.listDocFactureAchat = listDocFactureAchat;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * Gets the taux tva art.
	 *
	 * @return the taux tva art
	 */
	public Integer getTauxTvaArt() {
		return tauxTvaArt;
	}

	/**
	 * Sets the taux tva art.
	 *
	 * @param tauxTvaArt the new taux tva art
	 */
	public void setTauxTvaArt(Integer tauxTvaArt) {
		this.tauxTvaArt = tauxTvaArt;
	}

	/** The total pourcentage remise. */
	private Double totalPourcentageRemise;

	/** The taux tva art. */
	// taxe de chaque produit
	private Integer tauxTvaArt;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FactureAchatValue o) {
		FactureAchatValue element = (FactureAchatValue) o;
		return (element.getReference().compareTo(reference));
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
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Calendar date) {
		this.date = date;
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
	 * Gets the info livraison.
	 *
	 * @return the info livraison
	 */
	public String getInfoLivraison() {
		return infoLivraison;
	}

	/**
	 * Sets the info livraison.
	 *
	 * @param infoLivraison the new info livraison
	 */
	public void setInfoLivraison(String infoLivraison) {
		this.infoLivraison = infoLivraison;
	}

	/**
	 * Gets the partie int id.
	 *
	 * @return the partie int id
	 */
	public Long getPartieIntId() {
		return partieIntId;
	}

	/**
	 * Sets the partie int id.
	 *
	 * @param partieIntId the new partie int id
	 */
	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	/**
	 * Checks if is bl suppression.
	 *
	 * @return the boolean
	 */
	public Boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression the new bl suppression
	 */
	public void setBlSuppression(Boolean blSuppression) {
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
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the list det facture achat.
	 *
	 * @return the list det facture achat
	 */
	public List<DetFactureAchatValue> getListDetFactureAchat() {
		return listDetFactureAchat;
	}

	/**
	 * Sets the list det facture achat.
	 *
	 * @param listDetFactureAchat the new list det facture achat
	 */
	public void setListDetFactureAchat(List<DetFactureAchatValue> listDetFactureAchat) {
		this.listDetFactureAchat = listDetFactureAchat;
	}

	/**
	 * Gets the list taxe facture.
	 *
	 * @return the list taxe facture
	 */
	public List<TaxeFactureAchatValue> getListTaxeFacture() {
		return listTaxeFacture;
	}

	/**
	 * Sets the list taxe facture.
	 *
	 * @param listTaxeFacture the new list taxe facture
	 */
	public void setListTaxeFacture(List<TaxeFactureAchatValue> listTaxeFacture) {
		this.listTaxeFacture = listTaxeFacture;
	}

	/**
	 * Gets the metrage total.
	 *
	 * @return the metrage total
	 */
	public Double getMetrageTotal() {
		return metrageTotal;
	}

	/**
	 * Sets the metrage total.
	 *
	 * @param metrageTotal the new metrage total
	 */
	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}

	/**
	 * Gets the etat.
	 *
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Sets the etat.
	 *
	 * @param etat the new etat
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * Gets the partie int abreviation.
	 *
	 * @return the partie int abreviation
	 */
	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}

	/**
	 * Sets the partie int abreviation.
	 *
	 * @param partieIntAbreviation the new partie int abreviation
	 */
	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}

	/**
	 * Gets the modepaiement id.
	 *
	 * @return the modepaiement id
	 */
	public Long getModepaiementId() {
		return modepaiementId;
	}

	/**
	 * Sets the modepaiement id.
	 *
	 * @param modepaiementId the new modepaiement id
	 */
	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
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
	 * Gets the ref factures.
	 *
	 * @return the ref factures
	 */
	public String getRefFactures() {
		return refFactures;
	}

	/**
	 * Sets the ref factures.
	 *
	 * @param refFactures the new ref factures
	 */
	public void setRefFactures(String refFactures) {
		this.refFactures = refFactures;
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
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the bl suppression.
	 *
	 * @return the bl suppression
	 */
	public Boolean getBlSuppression() {
		return blSuppression;
	}

	/**
	 * Gets the nature livraison.
	 *
	 * @return the nature livraison
	 */
	public String getNatureLivraison() {
		return natureLivraison;
	}

	/**
	 * Sets the nature livraison.
	 *
	 * @param natureLivraison the new nature livraison
	 */
	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
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
	 * Gets the total pourcentage remise.
	 *
	 * @return the total pourcentage remise
	 */
	public Double getTotalPourcentageRemise() {
		return totalPourcentageRemise;
	}

	/**
	 * Sets the total pourcentage remise.
	 *
	 * @param totalPourcentageRemise the new total pourcentage remise
	 */
	public void setTotalPourcentageRemise(Double totalPourcentageRemise) {
		this.totalPourcentageRemise = totalPourcentageRemise;
	}

	/**
	 * Gets the info livraison externe.
	 *
	 * @return the info livraison externe
	 */
	public String getInfoLivraisonExterne() {
		return infoLivraisonExterne;
	}

	/**
	 * Sets the info livraison externe.
	 *
	 * @param infoLivraisonExterne the new info livraison externe
	 */
	public void setInfoLivraisonExterne(String infoLivraisonExterne) {
		this.infoLivraisonExterne = infoLivraisonExterne;
	}

	/**
	 * Gets the id marche.
	 *
	 * @return the id marche
	 */
	public Long getIdMarche() {
		return idMarche;
	}

	/**
	 * Sets the id marche.
	 *
	 * @param idMarche the new id marche
	 */
	public void setIdMarche(Long idMarche) {
		this.idMarche = idMarche;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "FactureAchatValue [id=" + id + ", reference=" + reference + ", date=" + date + ", montantHTaxe="
				+ montantHTaxe + ", montantTTC=" + montantTTC + ", montantTaxe=" + montantTaxe + ", montantRemise="
				+ montantRemise + ", observations=" + observations + ", infoLivraison=" + infoLivraison
				+ ", partieIntId=" + partieIntId + ", metrageTotal=" + metrageTotal + ", etat=" + etat
				+ ", blSuppression=" + blSuppression + ", dateSuppression=" + dateSuppression + ", dateCreation="
				+ dateCreation + ", dateModification=" + dateModification + ", version=" + version
				+ ", listDetFactureAchat=" + listDetFactureAchat + ", listTaxeFacture=" + listTaxeFacture
				+ ", modepaiementId=" + modepaiementId + ", partieIntAbreviation=" + partieIntAbreviation
				+ ", modepaiement=" + modepaiement + ", marche=" + marche + ", refFactures=" + refFactures + ", type="
				+ type + ", natureLivraison=" + natureLivraison + ", ficheId=" + ficheId + ", totalPourcentageRemise="
				+ totalPourcentageRemise + "]";
	}

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

}
