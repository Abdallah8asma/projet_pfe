package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;


public class RechercheMulticritereBonMouvementStockValue {
	private String typeBonMouvement;
	private String nBE ;
	private String nBLDae; 
	private String fournisseur;
	private Calendar dateDu ;
	private Calendar dateA;
	private Double valeurInf;
	private Double valeurSupp;
	private String raisonEntre;
	private String magasin;
	private String etat;
	private Long ofId;
	private boolean mouvementOF;
	
	public Long getOfId() {
		return ofId;
	}
	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}
	public String getTypeBonMouvement() {
		return typeBonMouvement;
	}
	public void setTypeBonMouvement(String typeBonMouvement) {
		this.typeBonMouvement = typeBonMouvement;
	}
	public String getnBE() {
		return nBE;
	}
	public void setnBE(String nBE) {
		this.nBE = nBE;
	}
	public String getnBLDae() {
		return nBLDae;
	}
	public void setnBLDae(String nBLDae) {
		this.nBLDae = nBLDae;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	public Calendar getDateDu() {
		return dateDu;
	}
	public void setDateDu(Calendar dateDu) {
		this.dateDu = dateDu;
	}
	public Calendar getDateA() {
		return dateA;
	}
	public void setDateA(Calendar dateA) {
		this.dateA = dateA;
	}
	public Double getValeurInf() {
		return valeurInf;
	}
	public void setValeurInf(Double valeurInf) {
		this.valeurInf = valeurInf;
	}
	public Double getValeurSupp() {
		return valeurSupp;
	}
	public void setValeurSupp(Double valeurSupp) {
		this.valeurSupp = valeurSupp;
	}
	public String getRaisonEntre() {
		return raisonEntre;
	}
	public void setRaisonEntre(String raisonEntre) {
		this.raisonEntre = raisonEntre;
	}
	public String getMagasin() {
		return magasin;
	}
	public void setMagasin(String magasin) {
		this.magasin = magasin;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public boolean isMouvementOF() {
		return mouvementOF;
	}
	public void setMouvementOF(boolean mouvementOF) {
		this.mouvementOF = mouvementOF;
	}

}
