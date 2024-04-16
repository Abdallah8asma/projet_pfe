package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class ReceptionAchatFactureVue implements Comparable<ReceptionAchatFactureVue> {

	private Long id;
	private String reference;
	private String refexterne;

	private Calendar date;
	private Double montantHTaxe;
	private Double montantTTC;
	private Double montantTaxe;
	private Double montantRemise;
	private String observations;
	private String infoSortie;
	private Long partieIntId;
	private String partieIntAbreviation;
	private Double metrageTotal;
	private String etat;
	private String transporteur;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;

	//
	private Long idDepot;

	private String refCommande;

	// Information de produits 07/05/2018

	private String infoProduit;

	// private String marche;

	public String getInfoProduit() {
		return infoProduit;
	}

	public void setInfoProduit(String infoProduit) {
		this.infoProduit = infoProduit;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	// added on 18/02/2016, by Wahid Gazzah
	private Boolean modifier;

	// added on 19/02/2016, by Wahid Gazzah
	private Long modepaiementId;
	private Long marcheId;

	// added on 23/02/2016, by Wahid Gazzah
	private String modepaiement;
	private String marche;

	// added on 05/10/2016, by Zeineb Medimagh
	private String natureLivraison;

	private Double totalPourcentageRemise;

	// added on 16/03/2016
	private Integer tauxTvaArt;
	private String unite;

	// added on 29/03/2018
	private Boolean stock;

	// Added on 25/04/2018 By Ghazi Atroussi

	private Long idCamion;

	private Long idRemorque;

	private Long idChauffeur;

	// Added on 02/05/2018 By Ghazi Atroussi
	private String camion;
	private String chauffeur;
	private String remorque;

	public String getCamion() {
		return camion;
	}

	public void setCamion(String camion) {
		this.camion = camion;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public String getRemorque() {
		return remorque;
	}

	public void setRemorque(String remorque) {
		this.remorque = remorque;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public Boolean getStock() {
		return stock;
	}

	public void setStock(Boolean stock) {
		this.stock = stock;
	}

	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Integer getTauxTvaArt() {
		return tauxTvaArt;
	}

	public void setTauxTvaArt(Integer tauxTvaArt) {
		this.tauxTvaArt = tauxTvaArt;
	}

	public int compareTo(ReceptionAchatFactureVue element) {
		return (element.getReference().compareTo(reference));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantHTaxe() {
		return montantHTaxe;
	}

	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getInfoSortie() {
		return infoSortie;
	}

	public void setInfoSortie(String infoSortie) {
		this.infoSortie = infoSortie;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Double getMetrageTotal() {
		return metrageTotal;
	}

	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getTransporteur() {
		return transporteur;
	}

	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}

	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}

	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}

	public Boolean isModifier() {
		return modifier;
	}

	public void setModifier(Boolean modifier) {
		this.modifier = modifier;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}

	public Long getMarcheId() {
		return marcheId;
	}

	public void setMarcheId(Long marcheId) {
		this.marcheId = marcheId;
	}

	public String getModepaiement() {
		return modepaiement;
	}

	public void setModepaiement(String modepaiement) {
		this.modepaiement = modepaiement;
	}

	public String getMarche() {
		return marche;
	}

	public void setMarche(String marche) {
		this.marche = marche;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public Boolean getModifier() {
		return modifier;
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

	public Long getIdCamion() {
		return idCamion;
	}

	public void setIdCamion(Long idCamion) {
		this.idCamion = idCamion;
	}

	public Long getIdRemorque() {
		return idRemorque;
	}

	public void setIdRemorque(Long idRemorque) {
		this.idRemorque = idRemorque;
	}

	public Long getIdChauffeur() {
		return idChauffeur;
	}

	public void setIdChauffeur(Long idChauffeur) {
		this.idChauffeur = idChauffeur;
	}

	@Override
	public String toString() {
		return "ReceptionAchatFactureVue [id=" + id + ", reference=" + reference + ", date=" + date + ", montantHTaxe="
				+ montantHTaxe + ", montantTTC=" + montantTTC + ", montantTaxe=" + montantTaxe + ", montantRemise="
				+ montantRemise + ", observations=" + observations + ", infoSortie=" + infoSortie + ", partieIntId="
				+ partieIntId + ", partieIntAbreviation=" + partieIntAbreviation + ", metrageTotal=" + metrageTotal
				+ ", etat=" + etat + ", transporteur=" + transporteur + ", blSuppression=" + blSuppression
				+ ", dateSuppression=" + dateSuppression + ", dateCreation=" + dateCreation + ", dateModification="
				+ dateModification + ", version=" + version + ", modifier=" + modifier + ", modepaiementId="
				+ modepaiementId + ", marcheId=" + marcheId + ", modepaiement=" + modepaiement + ", marche=" + marche
				+ ", natureLivraison=" + natureLivraison + ", totalPourcentageRemise=" + totalPourcentageRemise + "]";
	}

}
