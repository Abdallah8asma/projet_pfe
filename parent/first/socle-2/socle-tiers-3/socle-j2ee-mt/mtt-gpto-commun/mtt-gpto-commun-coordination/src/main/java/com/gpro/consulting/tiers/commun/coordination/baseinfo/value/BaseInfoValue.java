package com.gpro.consulting.tiers.commun.coordination.baseinfo.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */
public class BaseInfoValue implements Comparable<BaseInfoValue> {

	private Long id;
	private String designation;
	private String valeur;
	private String unite;
	private String logo;
	private boolean actif;

	private Double coutMinute;

	private String adresse;
	private String ville;
	private String codeTVA;
	private String telephoneFix;
	private String telephoneMoblie;
	private String fax;
	private String email;
	
	private Long guichetClient;
	private Long guichetFournisseur;
	
	
	private String prefixFacture;
	private String prefixBonLivraison;
	private String prefixAvoir;
	
	
	private String matriculeFiscal;
	
	private String prefixClient;
	private String prefixFournisseur;
	
	
	
	// used for disable update reference from front 
	

	private boolean disableFinance;
	private boolean disableParametrage;
	private boolean disableVente;
	private boolean disableAchat;
	
	
  //fin.  used for disable update reference from front  */
	
	
	private boolean hasStock;
	private boolean hasAchat;
	private boolean hasCaisse;
	
	
	private String archiveDirectory;
	
	
	
	private String logoPNG;
	
	private String excelDirectory;
	
	
	private boolean blackMode;
	
	
	private Calendar dateDemarrage;
	

	private Boolean fodec;
	

	private Boolean tva19;
	
	
	private Boolean refCommandeFactureObligatoire;
	

	private Boolean identifiantFactureObligatoire;
	
	
	private Boolean contrainteModificationBl;
	  private String details ;
	  private String special;
	  private Boolean reglementAvoir ;
	  private Boolean besoin ;
	  private Boolean reglementBl ;
	
	
	

	public Boolean getContrainteModificationBl() {
		return contrainteModificationBl;
	}

	public void setContrainteModificationBl(Boolean contrainteModificationBl) {
		this.contrainteModificationBl = contrainteModificationBl;
	}

	public Boolean getRefCommandeFactureObligatoire() {
		return refCommandeFactureObligatoire;
	}

	public void setRefCommandeFactureObligatoire(Boolean refCommandeFactureObligatoire) {
		this.refCommandeFactureObligatoire = refCommandeFactureObligatoire;
	}

	public Boolean getIdentifiantFactureObligatoire() {
		return identifiantFactureObligatoire;
	}

	public void setIdentifiantFactureObligatoire(Boolean identifiantFactureObligatoire) {
		this.identifiantFactureObligatoire = identifiantFactureObligatoire;
	}

	public Boolean getFodec() {
		return fodec;
	}

	public void setFodec(Boolean fodec) {
		this.fodec = fodec;
	}

	public Boolean getTva19() {
		return tva19;
	}

	public void setTva19(Boolean tva19) {
		this.tva19 = tva19;
	}

	public Calendar getDateDemarrage() {
		return dateDemarrage;
	}

	public void setDateDemarrage(Calendar dateDemarrage) {
		this.dateDemarrage = dateDemarrage;
	}

	public boolean isBlackMode() {
		return blackMode;
	}

	public void setBlackMode(boolean blackMode) {
		this.blackMode = blackMode;
	}

	public String getExcelDirectory() {
		return excelDirectory;
	}

	public void setExcelDirectory(String excelDirectory) {
		this.excelDirectory = excelDirectory;
	}

	public String getLogoPNG() {
		return logoPNG;
	}

	public void setLogoPNG(String logoPNG) {
		this.logoPNG = logoPNG;
	}

	public String getArchiveDirectory() {
		return archiveDirectory;
	}

	public void setArchiveDirectory(String archiveDirectory) {
		this.archiveDirectory = archiveDirectory;
	}

	public boolean isHasStock() {
		return hasStock;
	}

	public void setHasStock(boolean hasStock) {
		this.hasStock = hasStock;
	}

	public boolean isHasAchat() {
		return hasAchat;
	}

	public void setHasAchat(boolean hasAchat) {
		this.hasAchat = hasAchat;
	}

	public boolean isHasCaisse() {
		return hasCaisse;
	}

	public void setHasCaisse(boolean hasCaisse) {
		this.hasCaisse = hasCaisse;
	}

	public String getPrefixClient() {
		return prefixClient;
	}

	public boolean isDisableFinance() {
		return disableFinance;
	}

	public void setDisableFinance(boolean disableFinance) {
		this.disableFinance = disableFinance;
	}

	public boolean isDisableParametrage() {
		return disableParametrage;
	}

	public void setDisableParametrage(boolean disableParametrage) {
		this.disableParametrage = disableParametrage;
	}

	public boolean isDisableVente() {
		return disableVente;
	}

	public void setDisableVente(boolean disableVente) {
		this.disableVente = disableVente;
	}

	public boolean isDisableAchat() {
		return disableAchat;
	}

	public void setDisableAchat(boolean disableAchat) {
		this.disableAchat = disableAchat;
	}

	public void setPrefixClient(String prefixClient) {
		this.prefixClient = prefixClient;
	}

	public String getPrefixFournisseur() {
		return prefixFournisseur;
	}

	public void setPrefixFournisseur(String prefixFournisseur) {
		this.prefixFournisseur = prefixFournisseur;
	}

	public String getMatriculeFiscal() {
		return matriculeFiscal;
	}

	public void setMatriculeFiscal(String matriculeFiscal) {
		this.matriculeFiscal = matriculeFiscal;
	}

	public String getPrefixFacture() {
		return prefixFacture;
	}

	public void setPrefixFacture(String prefixFacture) {
		this.prefixFacture = prefixFacture;
	}

	public String getPrefixBonLivraison() {
		return prefixBonLivraison;
	}

	public void setPrefixBonLivraison(String prefixBonLivraison) {
		this.prefixBonLivraison = prefixBonLivraison;
	}

	public String getPrefixAvoir() {
		return prefixAvoir;
	}

	public void setPrefixAvoir(String prefixAvoir) {
		this.prefixAvoir = prefixAvoir;
	}

	public Long getGuichetClient() {
		return guichetClient;
	}

	public void setGuichetClient(Long guichetClient) {
		this.guichetClient = guichetClient;
	}

	public Long getGuichetFournisseur() {
		return guichetFournisseur;
	}

	public void setGuichetFournisseur(Long guichetFournisseur) {
		this.guichetFournisseur = guichetFournisseur;
	}

	public int compareTo(BaseInfoValue element) {
		return (element.getId().compareTo(id));
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephoneFix() {
		return telephoneFix;
	}

	public void setTelephoneFix(String telephoneFix) {
		this.telephoneFix = telephoneFix;
	}

	public String getTelephoneMoblie() {
		return telephoneMoblie;
	}

	public void setTelephoneMoblie(String telephoneMoblie) {
		this.telephoneMoblie = telephoneMoblie;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Double getCoutMinute() {
		return coutMinute;
	}

	public void setCoutMinute(Double coutMinute) {
		this.coutMinute = coutMinute;
	}

	public String getCodeTVA() {
		return codeTVA;
	}

	public void setCodeTVA(String codeTVA) {
		this.codeTVA = codeTVA;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	
	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public Boolean getReglementAvoir() {
		return reglementAvoir;
	}

	public void setReglementAvoir(Boolean reglementAvoir) {
		this.reglementAvoir = reglementAvoir;
	}

	public Boolean getBesoin() {
		return besoin;
	}

	public void setBesoin(Boolean besoin) {
		this.besoin = besoin;
	}

	public Boolean getReglementBl() {
		return reglementBl;
	}

	public void setReglementBl(Boolean reglementBl) {
		this.reglementBl = reglementBl;
	}

}
