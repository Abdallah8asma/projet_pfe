package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Calendar;

public class RechercheMulticritereDetFactureVenteValue 
{
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
	
	
	

	
	
	private String refCommande;
	
	private String identifiant;
	private Long produitId;
	private Double quantiteDe;
	private Double quantiteA;
	
	private String unite;
	private Long nombreColis;
	private Long factureVenteId;
	private Double remise;
	private String choix;

	// this values used only on validate action
	private String produitDesignation;
	private String produitReference;
	private Double prixUnitaireHT;
	private Double prixTotalHT;

	// Added on 11/10/2016 by Zeineb Medimagh
	private Long traitementFaconId;

	// Added on 17/10/2016 by Zeineb Medimagh
	private String refMiseList;
	private Long ficheId;
	private Double montanTaxeTVA;
	public String getReferenceFacture() {
		return referenceFacture;
	}
	public void setReferenceFacture(String referenceFacture) {
		this.referenceFacture = referenceFacture;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getNatureLivraison() {
		return natureLivraison;
	}
	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	public Long getGroupeClientId() {
		return groupeClientId;
	}
	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}
	public String getRaison() {
		return raison;
	}
	public void setRaison(String raison) {
		this.raison = raison;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public Long getBoutiqueId() {
		return boutiqueId;
	}
	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
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
	public Long getIdDepot() {
		return idDepot;
	}
	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}
	public Long getDevise() {
		return devise;
	}
	public void setDevise(Long devise) {
		this.devise = devise;
	}
	public String getDeclarerecherche() {
		return declarerecherche;
	}
	public void setDeclarerecherche(String declarerecherche) {
		this.declarerecherche = declarerecherche;
	}
	public String getForcerCalculMontantRech() {
		return forcerCalculMontantRech;
	}
	public void setForcerCalculMontantRech(String forcerCalculMontantRech) {
		this.forcerCalculMontantRech = forcerCalculMontantRech;
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

	public String getRefCommande() {
		return refCommande;
	}
	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	
	public Double getQuantiteDe() {
		return quantiteDe;
	}
	public void setQuantiteDe(Double quantiteDe) {
		this.quantiteDe = quantiteDe;
	}
	
	public Double getQuantiteA() {
		return quantiteA;
	}
	public void setQuantiteA(Double quantiteA) {
		this.quantiteA = quantiteA;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	public Long getNombreColis() {
		return nombreColis;
	}
	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}
	public Long getFactureVenteId() {
		return factureVenteId;
	}
	public void setFactureVenteId(Long factureVenteId) {
		this.factureVenteId = factureVenteId;
	}
	public Double getRemise() {
		return remise;
	}
	public void setRemise(Double remise) {
		this.remise = remise;
	}
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	public String getProduitDesignation() {
		return produitDesignation;
	}
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	public String getProduitReference() {
		return produitReference;
	}
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
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
	public Long getTraitementFaconId() {
		return traitementFaconId;
	}
	public void setTraitementFaconId(Long traitementFaconId) {
		this.traitementFaconId = traitementFaconId;
	}
	public String getRefMiseList() {
		return refMiseList;
	}
	public void setRefMiseList(String refMiseList) {
		this.refMiseList = refMiseList;
	}
	public Long getFicheId() {
		return ficheId;
	}
	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}
	public Double getMontanTaxeTVA() {
		return montanTaxeTVA;
	}
	public void setMontanTaxeTVA(Double montanTaxeTVA) {
		this.montanTaxeTVA = montanTaxeTVA;
	}
	public static boolean checkForOptimization(RechercheMulticritereDetFactureVenteValue request) {
	
		return isNullOrEmpty(request.getPartieIntId())
	
				
				&& isNullOrEmpty(request.getPrixMin())
				&& isNullOrEmpty(request.getPrixMax())
				&& isNullOrEmpty(request.getProduitId()) 
				&& isNullOrEmpty(request.getQuantiteDe())
				&& isNullOrEmpty(request.getQuantiteA())
				&& isNullOrEmpty(request.getDateIntroductionA())
				&& isNullOrEmpty(request.getDateEcheanceA()) 
				&& isNullOrEmpty(request.getDateEcheanceDe())
				&& isNullOrEmpty(request.getRefCommande())
				&& isNullOrEmpty(request.getReferenceFacture())
		        &&isNullOrEmpty(request.getDevise())
		        &&isNullOrEmpty(request.getBoutiqueId())
		        &&isNullOrEmpty(request.getReferenceBl());
			
				
				
	
				
	            
	         	
	}
	public static boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
	

}
