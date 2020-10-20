package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value;

import java.util.Set;

/**
 * @author Wahid Gazzah
 * @since 12/07/2016
 */
public class ValidateReglementResultValue {
	
	private Double factureMontantTotal;
	private Double factureMontantTotalRegle;
	private Double factureMontantTotalNonRegle;
	
	private Double blMontantTotal;
	private Double blMontantTotalRegle;
	private Double blMontantTotalNonRegle;
	
	private Set<FactureNonRegleValue> listFactureNonRegle;
	private Set<LivraisonNonRegleValue> listLivraisonNonRegle;
	
	public Double getFactureMontantTotal() {
		return factureMontantTotal;
	}
	public void setFactureMontantTotal(Double factureMontantTotal) {
		this.factureMontantTotal = factureMontantTotal;
	}
	public Double getFactureMontantTotalRegle() {
		return factureMontantTotalRegle;
	}
	public void setFactureMontantTotalRegle(Double factureMontantTotalRegle) {
		this.factureMontantTotalRegle = factureMontantTotalRegle;
	}
	public Double getFactureMontantTotalNonRegle() {
		return factureMontantTotalNonRegle;
	}
	public void setFactureMontantTotalNonRegle(Double factureMontantTotalNonRegle) {
		this.factureMontantTotalNonRegle = factureMontantTotalNonRegle;
	}
	public Double getBlMontantTotal() {
		return blMontantTotal;
	}
	public void setBlMontantTotal(Double blMontantTotal) {
		this.blMontantTotal = blMontantTotal;
	}
	public Double getBlMontantTotalRegle() {
		return blMontantTotalRegle;
	}
	public void setBlMontantTotalRegle(Double blMontantTotalRegle) {
		this.blMontantTotalRegle = blMontantTotalRegle;
	}
	public Double getBlMontantTotalNonRegle() {
		return blMontantTotalNonRegle;
	}
	public void setBlMontantTotalNonRegle(Double blMontantTotalNonRegle) {
		this.blMontantTotalNonRegle = blMontantTotalNonRegle;
	}
	public Set<FactureNonRegleValue> getListFactureNonRegle() {
		return listFactureNonRegle;
	}
	public void setListFactureNonRegle(Set<FactureNonRegleValue> listFactureNonRegle) {
		this.listFactureNonRegle = listFactureNonRegle;
	}
	public Set<LivraisonNonRegleValue> getListLivraisonNonRegle() {
		return listLivraisonNonRegle;
	}
	public void setListLivraisonNonRegle(
			Set<LivraisonNonRegleValue> listLivraisonNonRegle) {
		this.listLivraisonNonRegle = listLivraisonNonRegle;
	}
	
	

}
