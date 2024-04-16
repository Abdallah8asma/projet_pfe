package com.gpro.consulting.logistique.coordination.gc.boncomptoir.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class RechercheMulticritereBonComptoirValue {
	
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
	
	//added on 23/02/2016, by Wahid Gazzah
	private Long marcheId;
	
	//added on 07/10/2016, by Zeineb Medimagh
	private String natureLivraison;
	
	//added on 07/10/2016, by Zeineb Medimagh
	private String avecFacture; // avec ou sans facture
	
	private Boolean stock;
	
	private Long idDepot;
	
	
	
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
