package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier;

import java.util.Calendar;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class RechercheMulticritereDetailReglementValue {

	private Long partieIntId;
	private Long groupeClientId;
	private String reference;
	private Calendar dateReglementDu;
	private Calendar dateReglementAu;
	private Long typeReglementId;
	private String numPiece;
	private Boolean regle;
	private Long regionId;

	private Calendar dateEmissionDu;
	private Calendar dateEmissionAu;

	private Calendar dateEcheanceDu;
	private Calendar dateEcheanceAu;

	private Boolean avecTerme;
	private String nomRapport;

	private String refFacture;
	private String refBL;

	private List<Long> reglementsId;

	private Long reglementId;
	
	
	private String referenceDetReglement;
	
	
	private Calendar dateDepotBanqueDe;
	
	private Calendar dateDepotBanqueA;
	
	private String declarerRech;
	
	
	
	
	
	

	public String getDeclarerRech() {
		return declarerRech;
	}

	public void setDeclarerRech(String declarerRech) {
		this.declarerRech = declarerRech;
	}

	public Calendar getDateDepotBanqueDe() {
		return dateDepotBanqueDe;
	}

	public void setDateDepotBanqueDe(Calendar dateDepotBanqueDe) {
		this.dateDepotBanqueDe = dateDepotBanqueDe;
	}

	public Calendar getDateDepotBanqueA() {
		return dateDepotBanqueA;
	}

	public void setDateDepotBanqueA(Calendar dateDepotBanqueA) {
		this.dateDepotBanqueA = dateDepotBanqueA;
	}

	public String getReferenceDetReglement() {
		return referenceDetReglement;
	}

	public void setReferenceDetReglement(String referenceDetReglement) {
		this.referenceDetReglement = referenceDetReglement;
	}

	public Long getReglementId() {
		return reglementId;
	}

	public void setReglementId(Long reglementId) {
		this.reglementId = reglementId;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public List<Long> getReglementsId() {
		return reglementsId;
	}

	public void setReglementsId(List<Long> reglementsId) {
		this.reglementsId = reglementsId;
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

	public Calendar getDateReglementDu() {
		return dateReglementDu;
	}

	public void setDateReglementDu(Calendar dateReglementDu) {
		this.dateReglementDu = dateReglementDu;
	}

	public Calendar getDateReglementAu() {
		return dateReglementAu;
	}

	public void setDateReglementAu(Calendar dateReglementAu) {
		this.dateReglementAu = dateReglementAu;
	}

	public Long getTypeReglementId() {
		return typeReglementId;
	}

	public void setTypeReglementId(Long typeReglementId) {
		this.typeReglementId = typeReglementId;
	}

	public String getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}

	public Boolean getRegle() {
		return regle;
	}

	public void setRegle(Boolean regle) {
		this.regle = regle;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Calendar getDateEmissionDu() {
		return dateEmissionDu;
	}

	public void setDateEmissionDu(Calendar dateEmissionDu) {
		this.dateEmissionDu = dateEmissionDu;
	}

	public Calendar getDateEmissionAu() {
		return dateEmissionAu;
	}

	public void setDateEmissionAu(Calendar dateEmissionAu) {
		this.dateEmissionAu = dateEmissionAu;
	}

	public Calendar getDateEcheanceDu() {
		return dateEcheanceDu;
	}

	public void setDateEcheanceDu(Calendar dateEcheanceDu) {
		this.dateEcheanceDu = dateEcheanceDu;
	}

	public Calendar getDateEcheanceAu() {
		return dateEcheanceAu;
	}

	public void setDateEcheanceAu(Calendar dateEcheanceAu) {
		this.dateEcheanceAu = dateEcheanceAu;
	}

	public Boolean getAvecTerme() {
		return avecTerme;
	}

	public void setAvecTerme(Boolean avecTerme) {
		this.avecTerme = avecTerme;
	}

	public String getNomRapport() {
		return nomRapport;
	}

	public void setNomRapport(String nomRapport) {
		this.nomRapport = nomRapport;
	}

}
