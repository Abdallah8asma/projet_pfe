package com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value;

/**
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class FactureReportProductValue {

	private Long produitId;
	private String produitCode;
	private String produitDesignation;
	private String mise;
	private Double quantite;
	private String unite;
	private Long nombreColis;
	private Double prixUHT;
	private Double remise;
	private Double montantHT;

	private Long detLivraisonVenteId;
	private String choix;
	private Double montantTaxeTVA;

	// added on 16 03 2018
	private Integer tauxTvaArt;

	private Long ficheId;

	public Long getFicheId() {
		return ficheId;
	}

	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}

	public Integer getTauxTvaArt() {
		return tauxTvaArt;
	}

	public void setTauxTvaArt(Integer tauxTvaArt) {
		this.tauxTvaArt = tauxTvaArt;
	}

	public Double getMontantTaxeTVA() {
		return montantTaxeTVA;
	}

	public void setMontantTaxeTVA(Double montantTaxeTVA) {
		this.montantTaxeTVA = montantTaxeTVA;
	}

	public String getProduitCode() {
		return produitCode;
	}

	public void setProduitCode(String produitCode) {
		this.produitCode = produitCode;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public String getMise() {
		return mise;
	}

	public void setMise(String mise) {
		this.mise = mise;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
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

	public Double getPrixUHT() {
		return prixUHT;
	}

	public void setPrixUHT(Double prixUHT) {
		this.prixUHT = prixUHT;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Double getMontantHT() {
		return montantHT;
	}

	public void setMontantHT(Double montantHT) {
		this.montantHT = montantHT;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Long getDetLivraisonVenteId() {
		return detLivraisonVenteId;
	}

	public void setDetLivraisonVenteId(Long detLivraisonVenteId) {
		this.detLivraisonVenteId = detLivraisonVenteId;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

}
