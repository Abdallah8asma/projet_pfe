package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class FactureVenteValue implements Comparable<FactureVenteValue> {

	private Long id;
	private String reference;
	private String refAvantChangement;
	private Calendar date;
	private Double montantHTaxe;
	private Double montantTTC;
	private Double montantTaxe;
	private Double montantRemise;
	private String observations;
	private String infoLivraison;
	private Long partieIntId;
	private Double metrageTotal;
	private String etat;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	private List<DetFactureVenteValue> listDetFactureVente;
	private List<TaxeFactureValue> listTaxeFacture;
	private Set<DocumentFactureVenteValue> listDocFactureVente = new HashSet<DocumentFactureVenteValue>();
	private Long modepaiementId;

	private String partieIntAbreviation;
	private String modepaiement;
	private String marche;

	private String refFactures;
	private String type; // Normal, Avoir

	// Added on 11/10/2016 by Zeineb Medimagh
	private String natureLivraison;

	// Added on 17/10/2016 by Zeineb Medimagh
	private Long ficheId;

	// Added on 23/05/2018 By Ghazi
	private String infoLivraisonExterne;

	// Added on 25/05/2018

	private Long idMarche;

	private Double totalPourcentageRemise;

	private Boolean description;

	// taxe de chaque produit
	private Integer tauxTvaArt;

	private Long typePartieInteressee;

	private Long groupeClientId;

	private String raison;

	private String nature;

	private Long reglementId;

	private Calendar dateIntroduction;

	private Long boutiqueId;

	private boolean avecRetourStock;

	private Long idDepot;

	private Double montantOuvert;

	
	private Long devise;
	private Double tauxConversion;
	private Double montantConverti;
	
	private Boolean declarer;
	
	private boolean forcerCalculMontant;
	
	

	private String modalitePaiement;
	
	

	private Calendar dateEcheance;
	
	
	private String refCommande;
	
	
	private String identifiant;
	
	
	
	
	
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public String getModalitePaiement() {
		return modalitePaiement;
	}

	public void setModalitePaiement(String modalitePaiement) {
		this.modalitePaiement = modalitePaiement;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public boolean isForcerCalculMontant() {
		return forcerCalculMontant;
	}

	public void setForcerCalculMontant(boolean forcerCalculMontant) {
		this.forcerCalculMontant = forcerCalculMontant;
	}



	public Boolean getDeclarer() {
		return declarer;
	}

	public void setDeclarer(Boolean declarer) {
		this.declarer = declarer;
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

	public Double getMontantOuvert() {
		return montantOuvert;
	}

	public void setMontantOuvert(Double montantOuvert) {
		this.montantOuvert = montantOuvert;
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

	public Long getReglementId() {
		return reglementId;
	}

	public void setReglementId(Long reglementId) {
		this.reglementId = reglementId;
	}

	public Set<DocumentFactureVenteValue> getListDocFactureVente() {
		return listDocFactureVente;
	}

	public void setListDocFactureVente(Set<DocumentFactureVenteValue> listDocFactureVente) {
		this.listDocFactureVente = listDocFactureVente;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public Boolean getDescription() {
		return description;
	}

	public void setDescription(Boolean description) {
		this.description = description;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public Long getTypePartieInteressee() {
		return typePartieInteressee;
	}

	public void setTypePartieInteressee(Long typePartieInteressee) {
		this.typePartieInteressee = typePartieInteressee;
	}

	@Override
	public int compareTo(FactureVenteValue o) {
		FactureVenteValue element = (FactureVenteValue) o;
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

	public String getInfoLivraison() {
		return infoLivraison;
	}

	public void setInfoLivraison(String infoLivraison) {
		this.infoLivraison = infoLivraison;
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

	public List<DetFactureVenteValue> getListDetFactureVente() {
		return listDetFactureVente;
	}

	public void setListDetFactureVente(List<DetFactureVenteValue> listDetFactureVente) {
		this.listDetFactureVente = listDetFactureVente;
	}

	public List<TaxeFactureValue> getListTaxeFacture() {
		return listTaxeFacture;
	}

	public void setListTaxeFacture(List<TaxeFactureValue> listTaxeFacture) {
		this.listTaxeFacture = listTaxeFacture;
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

	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}

	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
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

	public String getRefFactures() {
		return refFactures;
	}

	public void setRefFactures(String refFactures) {
		this.refFactures = refFactures;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public String getNatureLivraison() {
		return natureLivraison;
	}

	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}

	public Long getFicheId() {
		return ficheId;
	}

	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}

	public Double getTotalPourcentageRemise() {
		return totalPourcentageRemise;
	}

	public void setTotalPourcentageRemise(Double totalPourcentageRemise) {
		this.totalPourcentageRemise = totalPourcentageRemise;
	}

	public String getInfoLivraisonExterne() {
		return infoLivraisonExterne;
	}

	public void setInfoLivraisonExterne(String infoLivraisonExterne) {
		this.infoLivraisonExterne = infoLivraisonExterne;
	}

	public Long getIdMarche() {
		return idMarche;
	}

	public void setIdMarche(Long idMarche) {
		this.idMarche = idMarche;
	}

	public Integer getTauxTvaArt() {
		return tauxTvaArt;
	}

	public void setTauxTvaArt(Integer tauxTvaArt) {
		this.tauxTvaArt = tauxTvaArt;
	}

	@Override
	public String toString() {
		return "FactureVenteValue [id=" + id + ", reference=" + reference + ", refAvantChangement=" + refAvantChangement
				+ ", date=" + date + ", montantHTaxe=" + montantHTaxe + ", montantTTC=" + montantTTC + ", montantTaxe="
				+ montantTaxe + ", montantRemise=" + montantRemise + ", observations=" + observations
				+ ", infoLivraison=" + infoLivraison + ", partieIntId=" + partieIntId + ", metrageTotal=" + metrageTotal
				+ ", etat=" + etat + ", blSuppression=" + blSuppression + ", dateSuppression=" + dateSuppression
				+ ", dateCreation=" + dateCreation + ", dateModification=" + dateModification + ", version=" + version
				+ ", listDetFactureVente=" + listDetFactureVente + ", listTaxeFacture=" + listTaxeFacture
				+ ", listDocFactureVente=" + listDocFactureVente + ", modepaiementId=" + modepaiementId
				+ ", partieIntAbreviation=" + partieIntAbreviation + ", modepaiement=" + modepaiement + ", marche="
				+ marche + ", refFactures=" + refFactures + ", type=" + type + ", natureLivraison=" + natureLivraison
				+ ", ficheId=" + ficheId + ", infoLivraisonExterne=" + infoLivraisonExterne + ", idMarche=" + idMarche
				+ ", totalPourcentageRemise=" + totalPourcentageRemise + ", description=" + description
				+ ", tauxTvaArt=" + tauxTvaArt + ", typePartieInteressee=" + typePartieInteressee + ", groupeClientId="
				+ groupeClientId + ", raison=" + raison + ", nature=" + nature + ", reglementId=" + reglementId
				+ ", dateIntroduction=" + dateIntroduction + ", boutiqueId=" + boutiqueId + ", avecRetourStock="
				+ avecRetourStock + ", idDepot=" + idDepot + ", montantOuvert=" + montantOuvert + ", devise=" + devise
				+ ", tauxConversion=" + tauxConversion + ", montantConverti=" + montantConverti + ", declarer="
				+ declarer + ", forcerCalculMontant=" + forcerCalculMontant + ", modalitePaiement=" + modalitePaiement
				+ ", dateEcheance=" + dateEcheance + ", refCommande=" + refCommande + ", identifiant=" + identifiant
				+ "]";
	}

	
}
