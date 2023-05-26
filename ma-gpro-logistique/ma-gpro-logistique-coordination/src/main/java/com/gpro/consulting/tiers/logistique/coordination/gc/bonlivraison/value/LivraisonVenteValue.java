package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class LivraisonVenteValue implements Comparable<LivraisonVenteValue> {

	private Long id;
	private String reference;
	private String refAvantChangement;
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
	private List<DetLivraisonVenteValue> listDetLivraisonVente;
	private List<DetLivraisonVenteValue> listSuppDetLivraisonVente;
	private List<TaxeLivraisonValue> listTaxeLivraison;
	private Set<DocumentLivraisonVenteValue> listDocLivraisonVente = new HashSet<DocumentLivraisonVenteValue>();
	//
	private Long idDepot;
	private String depot;

	private Long typePartieInteressee;

	private Boolean declare;

	private Long groupeClientId;

	private String numTelPassager;
	private String emailPassager;
	private String adressePassager;

	private Long reglementId;

	private Long boutiqueId;
	
	
	
	private Long devise;
	private Double tauxConversion;
	private Double montantConverti;
	
	private String referenceBlManuel;
	
	private String identifiantLivraison;
	
	

	public String getReferenceBlManuel() {
		return referenceBlManuel;
	}

	public void setReferenceBlManuel(String referenceBlManuel) {
		this.referenceBlManuel = referenceBlManuel;
	}

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

	public Set<DocumentLivraisonVenteValue> getListDocLivraisonVente() {
		return listDocLivraisonVente;
	}

	public void setListDocLivraisonVente(Set<DocumentLivraisonVenteValue> listDocLivraisonVente) {
		this.listDocLivraisonVente = listDocLivraisonVente;
	}

	public String getNumTelPassager() {
		return numTelPassager;
	}

	public void setNumTelPassager(String numTelPassager) {
		this.numTelPassager = numTelPassager;
	}

	public String getEmailPassager() {
		return emailPassager;
	}

	public void setEmailPassager(String emailPassager) {
		this.emailPassager = emailPassager;
	}

	public String getAdressePassager() {
		return adressePassager;
	}

	public void setAdressePassager(String adressePassager) {
		this.adressePassager = adressePassager;
	}

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	public List<DetLivraisonVenteValue> getListSuppDetLivraisonVente() {
		return listSuppDetLivraisonVente;
	}

	public void setListSuppDetLivraisonVente(List<DetLivraisonVenteValue> listSuppDetLivraisonVente) {
		this.listSuppDetLivraisonVente = listSuppDetLivraisonVente;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public Boolean getDeclare() {
		return declare;
	}

	public void setDeclare(Boolean declare) {
		this.declare = declare;
	}

	public Long getTypePartieInteressee() {
		return typePartieInteressee;
	}

	public void setTypePartieInteressee(Long typePartieInteressee) {
		this.typePartieInteressee = typePartieInteressee;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	private String refCommande;

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

	private Boolean description;

	public Boolean getDescription() {
		return description;
	}

	public void setDescription(Boolean description) {
		this.description = description;
	}

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

	public int compareTo(LivraisonVenteValue element) {
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

	public List<DetLivraisonVenteValue> getListDetLivraisonVente() {
		return listDetLivraisonVente;
	}

	public void setListDetLivraisonVente(List<DetLivraisonVenteValue> listDetLivraisonVente) {
		this.listDetLivraisonVente = listDetLivraisonVente;
	}

	public List<TaxeLivraisonValue> getListTaxeLivraison() {
		return listTaxeLivraison;
	}

	public void setListTaxeLivraison(List<TaxeLivraisonValue> listTaxeLivraison) {
		this.listTaxeLivraison = listTaxeLivraison;
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



	public String getIdentifiantLivraison() {
		return identifiantLivraison;
	}

	public void setIdentifiantLivraison(String identifiantLivraison) {
		this.identifiantLivraison = identifiantLivraison;
	}

	@Override
	public String toString() {
		return "LivraisonVenteValue [id=" + id + ", reference=" + reference + ", date=" + date + ", montantHTaxe="
				+ montantHTaxe + ", montantTTC=" + montantTTC + ", montantTaxe=" + montantTaxe + ", montantRemise="
				+ montantRemise + ", observations=" + observations + ", infoSortie=" + infoSortie + ", partieIntId="
				+ partieIntId + ", partieIntAbreviation=" + partieIntAbreviation + ", metrageTotal=" + metrageTotal
				+ ", etat=" + etat + ", transporteur=" + transporteur + ", blSuppression=" + blSuppression
				+ ", dateSuppression=" + dateSuppression + ", dateCreation=" + dateCreation + ", dateModification="
				+ dateModification + ", version=" + version + ", listDetLivraisonVente=" + listDetLivraisonVente
				+ ", listTaxeLivraison=" + listTaxeLivraison + ", modifier=" + modifier + ", modepaiementId="
				+ modepaiementId + ", marcheId=" + marcheId + ", modepaiement=" + modepaiement + ", marche=" + marche
				+ ", natureLivraison=" + natureLivraison + ", totalPourcentageRemise=" + totalPourcentageRemise + "]";
	}

}
