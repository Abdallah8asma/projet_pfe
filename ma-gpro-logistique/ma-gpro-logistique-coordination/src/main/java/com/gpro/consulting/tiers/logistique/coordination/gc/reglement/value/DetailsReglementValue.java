package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class DetailsReglementValue implements Comparable<DetailsReglementValue> {

	private Long id;
	private String numPiece;
	private String banque;
	private Double montant;
	private Calendar dateEmission;
	private Calendar dateEcheance;
	private String refFacture;
	private Long typeReglementId;
	private Long reglementId;
	private Boolean regle;
	private String observation;

	private String reference;

	private Calendar dateDepotBanque;

	private Double chargeBanque;

	private Double tvaBanque;
	
	private String banqueSociete;
	
	
	

	public String getBanqueSociete() {
		return banqueSociete;
	}

	public void setBanqueSociete(String banqueSociete) {
		this.banqueSociete = banqueSociete;
	}

	public Calendar getDateDepotBanque() {
		return dateDepotBanque;
	}

	public void setDateDepotBanque(Calendar dateDepotBanque) {
		this.dateDepotBanque = dateDepotBanque;
	}

	public Double getChargeBanque() {
		return chargeBanque;
	}

	public void setChargeBanque(Double chargeBanque) {
		this.chargeBanque = chargeBanque;
	}

	public Double getTvaBanque() {
		return tvaBanque;
	}

	public void setTvaBanque(Double tvaBanque) {
		this.tvaBanque = tvaBanque;
	}

	public int compareTo(DetailsReglementValue element) {
		return (element.getId().compareTo(id));
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Calendar getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Calendar dateEmission) {
		this.dateEmission = dateEmission;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public Long getTypeReglementId() {
		return typeReglementId;
	}

	public void setTypeReglementId(Long typeReglementId) {
		this.typeReglementId = typeReglementId;
	}

	public Long getReglementId() {
		return reglementId;
	}

	public void setReglementId(Long reglementId) {
		this.reglementId = reglementId;
	}

	public Boolean getRegle() {
		return regle;
	}

	public void setRegle(Boolean regle) {
		this.regle = regle;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public String toString() {
		return "DetailsReglementValue [id=" + id + ", numPiece=" + numPiece + ", banque=" + banque + ", montant="
				+ montant + ", dateEmission=" + dateEmission + ", dateEcheance=" + dateEcheance + ", refFacture="
				+ refFacture + ", typeReglementId=" + typeReglementId + ", reglementId=" + reglementId + ", regle="
				+ regle + ", observation=" + observation + "]";
	}

}
