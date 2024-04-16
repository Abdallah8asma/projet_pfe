package com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value;


/**
 * 
 * @author Zeineb Medimagh
 * @since 07/10/2016
 *
 */
public class BonLivraisonReportTraitementFaconValue implements Comparable<BonLivraisonReportTraitementFaconValue> {
	
	private Long traitementId;
	private String traitementDesignation;
	private String refMiseList;
	private Double quantite;
	private String unite;
	private Long nombreColis;
	private Double prixUHT;
	private Double remise;
	private Double montantHT;
	private Long detLivraisonVenteId;
	
	public String getRefMiseList() {
		return refMiseList;
	}
	public void setRefMiseList(String refMiseList) {
		this.refMiseList = refMiseList;
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
	public Long getDetLivraisonVenteId() {
		return detLivraisonVenteId;
	}
	public void setDetLivraisonVenteId(Long detLivraisonVenteId) {
		this.detLivraisonVenteId = detLivraisonVenteId;
	}
	public Long getTraitementId() {
		return traitementId;
	}
	public void setTraitementId(Long traitementId) {
		this.traitementId = traitementId;
	}
	public String getTraitementDesignation() {
		return traitementDesignation;
	}
	public void setTraitementDesignation(String traitementDesignation) {
		this.traitementDesignation = traitementDesignation;
	}
	@Override
	public int compareTo(BonLivraisonReportTraitementFaconValue o) {
		BonLivraisonReportTraitementFaconValue element = (BonLivraisonReportTraitementFaconValue)o;
		return (element.getRefMiseList().compareTo(refMiseList));
	}
}
