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
	
	private Double factureAvoirMontantTotal;
	private Double factureAvoirTotalRegle;
	private Double factureMontantAvoirTotalNonRegle;
	
	private Double blMontantTotal;
	private Double blMontantTotalRegle;
	private Double blMontantTotalNonRegle;
	
	private Set<FactureNonRegleValue> listFactureNonRegle;
	private Set<LivraisonNonRegleValue> listLivraisonNonRegle;
	private Set<FactureAvoirNonRegleValue> listFactureAvoirNonRegle;
	
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
	public Set<FactureAvoirNonRegleValue> getListFactureAvoirNonRegle() {
		return listFactureAvoirNonRegle;
	}
	public void setListFactureAvoirNonRegle(Set<FactureAvoirNonRegleValue> listFactureAvoirNonRegle) {
		this.listFactureAvoirNonRegle = listFactureAvoirNonRegle;
	}
	public Double getFactureAvoirMontantTotal() {
		return factureAvoirMontantTotal;
	}
	public void setFactureAvoirMontantTotal(Double factureAvoirMontantTotal) {
		this.factureAvoirMontantTotal = factureAvoirMontantTotal;
	}
	public Double getFactureAvoirTotalRegle() {
		return factureAvoirTotalRegle;
	}
	public void setFactureAvoirTotalRegle(Double factureAvoirTotalRegle) {
		this.factureAvoirTotalRegle = factureAvoirTotalRegle;
	}
	public Double getFactureMontantAvoirTotalNonRegle() {
		return factureMontantAvoirTotalNonRegle;
	}
	public void setFactureMontantAvoirTotalNonRegle(Double factureMontantAvoirTotalNonRegle) {
		this.factureMontantAvoirTotalNonRegle = factureMontantAvoirTotalNonRegle;
	}
	
	

}
