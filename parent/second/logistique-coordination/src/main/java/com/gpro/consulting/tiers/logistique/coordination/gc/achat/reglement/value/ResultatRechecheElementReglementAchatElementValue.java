package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ResultatRechecheElementReglementAchatElementValue
		implements Comparable<ResultatRechecheElementReglementAchatElementValue> {

	private Long id;

	private String reference;
	private Calendar date;
	private Double montantTotal;

	private Long partieIntId;
	private String partieIntAbreviation;
	private String partieIntReference;

	private Long groupeClientId;
	private String groupeClientDesignation;

	private Double montant;
	private String refFacture;
	private String refBL;
	private Double montantDemande;
	private Calendar dateEcheance;
	private Long reglementId;

	// column detail reglement

	private String numPiece;
	private String banque;
	private Calendar dateEmission;
	private Long typeReglementId;
	private Boolean regle;
	private String observation;
	
	private String referenceDetReglement;
	

	private Calendar dateDepotBanque;

	private Double chargeBanque;

	private Double tvaBanque;
	
	private String banqueSociete;
	

	

private boolean declarer;




	

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

	public boolean isDeclarer() {
	return declarer;
}

public void setDeclarer(boolean declarer) {
	this.declarer = declarer;
}

	public String getReferenceDetReglement() {
		return referenceDetReglement;
	}

	public void setReferenceDetReglement(String referenceDetReglement) {
		this.referenceDetReglement = referenceDetReglement;
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

	public Calendar getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Calendar dateEmission) {
		this.dateEmission = dateEmission;
	}

	public Long getTypeReglementId() {
		return typeReglementId;
	}

	public void setTypeReglementId(Long typeReglementId) {
		this.typeReglementId = typeReglementId;
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

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public String getGroupeClientDesignation() {
		return groupeClientDesignation;
	}

	public void setGroupeClientDesignation(String groupeClientDesignation) {
		this.groupeClientDesignation = groupeClientDesignation;
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

	public int compareTo(ResultatRechecheElementReglementAchatElementValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(Double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}

	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}

	public String getPartieIntReference() {
		return partieIntReference;
	}

	public void setPartieIntReference(String partieIntReference) {
		this.partieIntReference = partieIntReference;
	}

	@Override
	public String toString() {
		return "ResultatRechecheReglementElementValue [id=" + id + ", reference=" + reference + ", date=" + date
				+ ", montantTotal=" + montantTotal + ", partieIntId=" + partieIntId + ", partieIntAbreviation="
				+ partieIntAbreviation + ", partieIntReference=" + partieIntReference + "]";
	}

}
