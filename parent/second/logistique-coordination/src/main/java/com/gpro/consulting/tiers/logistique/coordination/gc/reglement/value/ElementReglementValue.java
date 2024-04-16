package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ElementReglementValue implements Comparable<ElementReglementValue> {

	private Long id;
	private Double montant;
	private String refFacture;
	private String refBL;
	private Double montantDemande;
	private Calendar dateEcheance;
	private Long reglementId;
	private String refAvoir ;

	public int compareTo(ElementReglementValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public String getRefBL() {
		return refBL;
	}

	public void setRefBL(String refBL) {
		this.refBL = refBL;
	}

	public Double getMontantDemande() {
		return montantDemande;
	}

	public void setMontantDemande(Double montantDemande) {
		this.montantDemande = montantDemande;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public Long getReglementId() {
		return reglementId;
	}

	public void setReglementId(Long reglementId) {
		this.reglementId = reglementId;
	}

	

	public String getRefAvoir() {
		return refAvoir;
	}

	public void setRefAvoir(String refAvoir) {
		this.refAvoir = refAvoir;
	}

	@Override
	public String toString() {
		return "ElementReglementValue [id=" + id + ", montant=" + montant + ", refFacture=" + refFacture + ", refBL="
				+ refBL + ", montantDemande=" + montantDemande + ", dateEcheance=" + dateEcheance + ", reglementId="
				+ reglementId + "]";
	}

}
