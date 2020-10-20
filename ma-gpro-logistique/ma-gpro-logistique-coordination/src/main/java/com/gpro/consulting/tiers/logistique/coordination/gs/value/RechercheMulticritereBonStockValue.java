package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class RechercheMulticritereBonStockValue {

	private String referenceBl;
	private String referenceBs;
	private String refexterne;

	private Long partieIntId;
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

	private String refAvoirRetour;

	private String refFacture;

	private String refFactureRetour;

	private String refBR;

	private String type;

	private Calendar dateConcerneeDe;
	private Calendar dateConcerneeA;

	public String getRefAvoirRetour() {
		return refAvoirRetour;
	}

	public void setRefAvoirRetour(String refAvoirRetour) {
		this.refAvoirRetour = refAvoirRetour;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public String getRefFactureRetour() {
		return refFactureRetour;
	}

	public void setRefFactureRetour(String refFactureRetour) {
		this.refFactureRetour = refFactureRetour;
	}

	public String getRefBR() {
		return refBR;
	}

	public void setRefBR(String refBR) {
		this.refBR = refBR;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Calendar getDateConcerneeDe() {
		return dateConcerneeDe;
	}

	public void setDateConcerneeDe(Calendar dateConcerneeDe) {
		this.dateConcerneeDe = dateConcerneeDe;
	}

	public Calendar getDateConcerneeA() {
		return dateConcerneeA;
	}

	public void setDateConcerneeA(Calendar dateConcerneeA) {
		this.dateConcerneeA = dateConcerneeA;
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
