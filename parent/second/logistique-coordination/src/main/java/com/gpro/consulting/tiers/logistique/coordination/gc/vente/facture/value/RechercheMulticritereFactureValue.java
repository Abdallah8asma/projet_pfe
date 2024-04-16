package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class RechercheMulticritereFactureValue {

	private String referenceFacture;
	private String referenceBl;
	private Long partieIntId;
	private Calendar dateFactureMin;
	private Calendar dateFactureMax;
	private Double metrageMin;
	private Double metrageMax;
	private Double prixMin;
	private Double prixMax;
	private String type;

	private String referenceBlExterne;
	private Long marcheId;

	// added on 12/10/2016, by Zeineb Medimagh
	private String natureLivraison;

	private boolean isOptimized;

	private Long groupeClientId;

	private String raison;

	private String observations;

	private String nature;

	private Long boutiqueId;

	private Calendar dateIntroductionDe;
	private Calendar dateIntroductionA;

	private Long idDepot;

	private Long devise;
	
	
	private String declarerecherche;
	
	
	private String forcerCalculMontantRech;
	
	
	private String modalitePaiement;
	
	

	private Calendar dateEcheanceDe;
	
	private Calendar dateEcheanceA;
	
	
	
	private String infoLivraison ;
	
	
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

	public String getInfoLivraison() {
		return infoLivraison;
	}

	public void setInfoLivraison(String infoLivraison) {
		this.infoLivraison = infoLivraison;
	}

	public String getModalitePaiement() {
		return modalitePaiement;
	}

	public void setModalitePaiement(String modalitePaiement) {
		this.modalitePaiement = modalitePaiement;
	}

	public Calendar getDateEcheanceDe() {
		return dateEcheanceDe;
	}

	public void setDateEcheanceDe(Calendar dateEcheanceDe) {
		this.dateEcheanceDe = dateEcheanceDe;
	}

	public Calendar getDateEcheanceA() {
		return dateEcheanceA;
	}

	public void setDateEcheanceA(Calendar dateEcheanceA) {
		this.dateEcheanceA = dateEcheanceA;
	}

	public String getForcerCalculMontantRech() {
		return forcerCalculMontantRech;
	}

	public void setForcerCalculMontantRech(String forcerCalculMontantRech) {
		this.forcerCalculMontantRech = forcerCalculMontantRech;
	}

	public String getDeclarerecherche() {
		return declarerecherche;
	}

	public void setDeclarerecherche(String declarerecherche) {
		this.declarerecherche = declarerecherche;
	}

	public Long getDevise() {
		return devise;
	}

	public void setDevise(Long devise) {
		this.devise = devise;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public Calendar getDateIntroductionDe() {
		return dateIntroductionDe;
	}

	public void setDateIntroductionDe(Calendar dateIntroductionDe) {
		this.dateIntroductionDe = dateIntroductionDe;
	}

	public Calendar getDateIntroductionA() {
		return dateIntroductionA;
	}

	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public boolean isOptimized() {
		return isOptimized;
	}

	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}

	public String getReferenceBl() {
		return referenceBl;
	}

	public void setReferenceBl(String referenceBl) {
		this.referenceBl = referenceBl;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateFactureMin() {
		return dateFactureMin;
	}

	public void setDateFactureMin(Calendar dateFactureMin) {
		this.dateFactureMin = dateFactureMin;
	}

	public Calendar getDateFactureMax() {
		return dateFactureMax;
	}

	public void setDateFactureMax(Calendar dateFactureMax) {
		this.dateFactureMax = dateFactureMax;
	}

	public Double getMetrageMin() {
		return metrageMin;
	}

	public void setMetrageMin(Double metrageMin) {
		this.metrageMin = metrageMin;
	}

	public Double getMetrageMax() {
		return metrageMax;
	}

	public void setMetrageMax(Double metrageMax) {
		this.metrageMax = metrageMax;
	}

	public Double getPrixMin() {
		return prixMin;
	}

	public void setPrixMin(Double prixMin) {
		this.prixMin = prixMin;
	}

	public Double getPrixMax() {
		return prixMax;
	}

	public void setPrixMax(Double prixMax) {
		this.prixMax = prixMax;
	}

	public String getReferenceFacture() {
		return referenceFacture;
	}

	public void setReferenceFacture(String referenceFacture) {
		this.referenceFacture = referenceFacture;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNatureLivraison() {
		return natureLivraison;
	}

	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}

	public String getReferenceBlExterne() {
		return referenceBlExterne;
	}

	public void setReferenceBlExterne(String referenceBlExterne) {
		this.referenceBlExterne = referenceBlExterne;
	}

	public Long getMarcheId() {
		return marcheId;
	}

	public void setMarcheId(Long marcheId) {
		this.marcheId = marcheId;
	}

}
