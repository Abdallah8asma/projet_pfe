package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class RechercheMulticritereBonLivraisonValue {

	private String referenceBl;
	private String referenceBs;
	private String refexterne;

	private Long partieIntId;
	private Calendar dateLivraison;
	private Calendar dateLivraisonMin;
	private Calendar dateLivraisonMax;
	private Double metrageMin;
	private Double metrageMax;
	private Double prixMin;
	private Double prixMax;
	private String etat;

	// added on 23/02/2016, by Wahid Gazzah
	private Long marcheId;

	// added on 07/10/2016, by Zeineb Medimagh
	private String natureLivraison;

	// added on 07/10/2016, by Zeineb Medimagh
	private String avecFacture; // avec ou sans facture

	private Boolean stock;

	private Long idDepot;

	private String declare;

	private Calendar dateIntroductionMin;
	private Calendar dateIntroductionMax;

	private boolean isOptimized;

	private Long groupeClientId;

	private String numTelPassager;
	private String emailPassager;
	private String adressePassager;
	private String transporteur;

	private Long boutiqueId;
	
	private Long devise;
	

private String numOF;

private String referenceBlManuel;





	public String getReferenceBlManuel() {
	return referenceBlManuel;
}

public void setReferenceBlManuel(String referenceBlManuel) {
	this.referenceBlManuel = referenceBlManuel;
}

	public String getNumOF() {
	return numOF;
}

public void setNumOF(String numOF) {
	this.numOF = numOF;
}

	public Long getDevise() {
		return devise;
	}

	public void setDevise(Long devise) {
		this.devise = devise;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getTransporteur() {
		return transporteur;
	}

	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
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

	public Calendar getDateIntroductionMin() {
		return dateIntroductionMin;
	}

	public void setDateIntroductionMin(Calendar dateIntroductionMin) {
		this.dateIntroductionMin = dateIntroductionMin;
	}

	public Calendar getDateIntroductionMax() {
		return dateIntroductionMax;
	}

	public void setDateIntroductionMax(Calendar dateIntroductionMax) {
		this.dateIntroductionMax = dateIntroductionMax;
	}

	public String getDeclare() {
		return declare;
	}

	public void setDeclare(String declare) {
		this.declare = declare;
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

	public Calendar getDateLivraisonMin() {
		return dateLivraisonMin;
	}

	public void setDateLivraisonMin(Calendar dateLivraisonMin) {
		this.dateLivraisonMin = dateLivraisonMin;
	}

	public Calendar getDateLivraisonMax() {
		return dateLivraisonMax;
	}

	public void setDateLivraisonMax(Calendar dateLivraisonMax) {
		this.dateLivraisonMax = dateLivraisonMax;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
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

	public String getReferenceBs() {
		return referenceBs;
	}

	public void setReferenceBs(String referenceBs) {
		this.referenceBs = referenceBs;
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

	public String getAvecFacture() {
		return avecFacture;
	}

	public void setAvecFacture(String avecFacture) {
		this.avecFacture = avecFacture;
	}

}
