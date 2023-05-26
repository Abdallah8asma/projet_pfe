package com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value;

import java.util.Calendar;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereDetFactureVenteValue;

public class RechercheMulticritereProduitBonCommandeValue {

	/** The ref. */
	private String reference;

	/** partie Interessee Id **/
	private String partieInteresseId;

	/** designation produit **/
	private Long idProduit;

	/** dateIntroduction debut */
	private Calendar dateIntroductionDu;

	/** dateIntroduction fin */
	private Calendar dateIntroductionA;

	/** dateLivraison debut */
	private Calendar dateLivraisonDu;

	/** dateLivraison fin */
	private Calendar dateLivraisonA;

	private Double quantiteDu;

	private Double quantiteA;

	private Double coutDu;

	private Double coutA;

	private String type;

	private String livre;

	private Long boutiqueId;
	
	
	private Long devise;
	private Double remise;
	private Long taxeId;
	private Double prixUnitaireDe;
	private Double prixUnitaireA;
	private boolean isOptimized;
	

	public Long getDevise() {
		return devise;
	}

	public void setDevise(Long devise) {
		this.devise = devise;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getLivre() {
		return livre;
	}

	public void setLivre(String livre) {
		this.livre = livre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPartieInteresseId() {
		return partieInteresseId;
	}

	public void setPartieInteresseId(String partieInteresseId) {
		this.partieInteresseId = partieInteresseId;
	}

	

	

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public Calendar getDateIntroductionDu() {
		return dateIntroductionDu;
	}

	public void setDateIntroductionDu(Calendar dateIntroductionDu) {
		this.dateIntroductionDu = dateIntroductionDu;
	}

	public Calendar getDateIntroductionA() {
		return dateIntroductionA;
	}

	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}

	public Calendar getDateLivraisonDu() {
		return dateLivraisonDu;
	}

	public void setDateLivraisonDu(Calendar dateLivraisonDu) {
		this.dateLivraisonDu = dateLivraisonDu;
	}

	public Calendar getDateLivraisonA() {
		return dateLivraisonA;
	}

	public void setDateLivraisonA(Calendar dateLivraisonA) {
		this.dateLivraisonA = dateLivraisonA;
	}

	public Double getQuantiteDu() {
		return quantiteDu;
	}

	public void setQuantiteDu(Double quantiteDu) {
		this.quantiteDu = quantiteDu;
	}

	public Double getQuantiteA() {
		return quantiteA;
	}

	public void setQuantiteA(Double quantiteA) {
		this.quantiteA = quantiteA;
	}

	public Double getCoutDu() {
		return coutDu;
	}

	public void setCoutDu(Double coutDu) {
		this.coutDu = coutDu;
	}

	public Double getCoutA() {
		return coutA;
	}

	public void setCoutA(Double coutA) {
		this.coutA = coutA;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	public Double getPrixUnitaireDe() {
		return prixUnitaireDe;
	}

	public void setPrixUnitaireDe(Double prixUnitaireDe) {
		this.prixUnitaireDe = prixUnitaireDe;
	}

	public Double getPrixUnitaireA() {
		return prixUnitaireA;
	}

	public void setPrixUnitaireA(Double prixUnitaireA) {
		this.prixUnitaireA = prixUnitaireA;
	}
	
	public boolean isOptimized() {
		return isOptimized;
	}

	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}

	public static boolean checkForOptimization(RechercheMulticritereProduitBonCommandeValue request) {
		
		return isNullOrEmpty(request.getReference())
	
				
				&& isNullOrEmpty(request.getPartieInteresseId())
				&& isNullOrEmpty(request.getDateIntroductionA())
				&& isNullOrEmpty(request.getDateIntroductionDu()) 
				&& isNullOrEmpty(request.getDateLivraisonA())
				&& isNullOrEmpty(request.getDateLivraisonDu())
				&& isNullOrEmpty(request.getQuantiteA()) 
				&& isNullOrEmpty(request.getQuantiteDu())
				&& isNullOrEmpty(request.getPrixUnitaireA()) 
				&& isNullOrEmpty(request.getPrixUnitaireDe())
				&& isNullOrEmpty(request.getIdProduit())
				&& isNullOrEmpty(request.getTaxeId())
				&& isNullOrEmpty(request.getRemise())
		        &&isNullOrEmpty(request.getCoutA())
		        &&isNullOrEmpty(request.getCoutDu());
			
				
				
	
				
	            
	         	
	}
	public static boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
	

}
