package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.Calendar;

public class RechercheMulticritereDetFactureAchatValue 
{
	/** The id. */
	private Long id;
	private String factureReference;
	/** The produit id. */
	private Long produitId;

	/** The quantite. */
	private Double quantiteDe;
	private Double qunatiteA;

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
	private Long partieIntId;
	private Calendar dateFactureDe;
	private Calendar dateFactureA;
	private String referenceBlExterne;
	private String infoLivraison;
	private Double prixMax;
	private Double prixMin;
	private boolean isOptimized;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
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

	public Long getFactureAchatId() {
		return factureAchatId;
	}

	public void setFactureAchatId(Long factureAchatId) {
		this.factureAchatId = factureAchatId;
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

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	public Double getQuantiteDe() {
		return quantiteDe;
	}

	public void setQuantiteDe(Double quantiteDe) {
		this.quantiteDe = quantiteDe;
	}

	public Double getQunatiteA() {
		return qunatiteA;
	}

	public void setQunatiteA(Double qunatiteA) {
		this.qunatiteA = qunatiteA;
	}

	public String getFactureReference() {
		return factureReference;
	}

	public void setFactureReference(String factureReference) {
		this.factureReference = factureReference;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateFactureDe() {
		return dateFactureDe;
	}

	public void setDateFactureDe(Calendar dateFactureDe) {
		this.dateFactureDe = dateFactureDe;
	}

	public Calendar getDateFactureA() {
		return dateFactureA;
	}

	public void setDateFactureA(Calendar dateFactureA) {
		this.dateFactureA = dateFactureA;
	}

	

	public Double getPrixMax() {
		return prixMax;
	}

	public void setPrixMax(Double prixMax) {
		this.prixMax = prixMax;
	}

	public Double getPrixMin() {
		return prixMin;
	}

	public void setPrixMin(Double prixMin) {
		this.prixMin = prixMin;
	}

	public boolean isOptimized() {
		return isOptimized;
	}

	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	public static boolean checkForOptimization(RechercheMulticritereDetFactureAchatValue request) {

		return isNullOrEmpty(request.getPartieIntId())

				
				&& isNullOrEmpty(request.getPrixMin())
				&& isNullOrEmpty(request.getPrixMax())
				&& isNullOrEmpty(request.getProduitId()) 
				&& isNullOrEmpty(request.getQuantiteDe())
				&& isNullOrEmpty(request.getQunatiteA())
				&& isNullOrEmpty(request.getFactureReference())
				&& isNullOrEmpty(request.getDateFactureDe())
				&& isNullOrEmpty(request.getDateFactureA()) 
				&& isNullOrEmpty(request.getInfoLivraison())
				&& isNullOrEmpty(request.getReferenceBlExterne())
		        &&isNullOrEmpty(request.getProduitReference());
		    
		       
			
				
				

				
                
             	
	}
	public static boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}

}
