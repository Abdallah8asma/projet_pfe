package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheDetailReglementAchatElementValue implements Comparable<ResultatRechecheDetailReglementAchatElementValue>{

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
	
	private String refReglement;
	private String typeReglement;
	private Calendar dateReglement;
	
	private String clientAbreviation;
	private String clientRegion;
	private String modePaiement;
	
	private String referenceDetReglement;
	

	private Calendar dateDepotBanque;

	private Double chargeBanque;

	private Double tvaBanque;

	private String banqueSociete;
	
	
	
	
	
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

	public String getBanqueSociete() {
		return banqueSociete;
	}

	public void setBanqueSociete(String banqueSociete) {
		this.banqueSociete = banqueSociete;
	}

	public String getReferenceDetReglement() {
		return referenceDetReglement;
	}

	public void setReferenceDetReglement(String referenceDetReglement) {
		this.referenceDetReglement = referenceDetReglement;
	}

	public int compareTo(ResultatRechecheDetailReglementAchatElementValue element) {
		return (element.getId().compareTo(id));
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

	public String getRefReglement() {
		return refReglement;
	}

	public void setRefReglement(String refReglement) {
		this.refReglement = refReglement;
	}

	public String getTypeReglement() {
		return typeReglement;
	}

	public void setTypeReglement(String typeReglement) {
		this.typeReglement = typeReglement;
	}

	public Calendar getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(Calendar dateReglement) {
		this.dateReglement = dateReglement;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getClientRegion() {
		return clientRegion;
	}

	public void setClientRegion(String clientRegion) {
		this.clientRegion = clientRegion;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}
	
}
