package com.gpro.consulting.tiers.commun.persistance.baseinfo.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;


/**
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */

@Entity
@Table(name=IConstante.TABLE_EB_BASEINFO)
public class BaseInfoEntity implements Serializable{

	private static final long serialVersionUID = -3893584995848949449L;

	@Id
	@SequenceGenerator(name="BASEINFO_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_EB_BASEINFO, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BASEINFO_ID_GENERATOR")
    private Long id;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "VALEUR")
	private String valeur;
	
	@Column(name = "UNITE")
	private String unite;
	
	@Column(name = "BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;
	
	@Column(name = "VERSION")
	private String version;

	@Column(name = "LOGO")
	private String logo;

	@Column(name = "ACTIF")
	private boolean actif;

	
	@Column(name = "cout_minute")
	private Double coutMinute;
	
	
	@Column(name = "adresse")
	private String adresse;
	
	@Column(name = "ville")
	private String ville;
	
	@Column(name = "code_tva")
	private String codeTVA;
	
	
	@Column(name = "telephone_fix")
	private String telephoneFix;
	
	@Column(name = "telephone_mobile")
	private String telephoneMoblie;
	
	@Column(name = "fax")
	private String fax;
	
	@Column(name = "email")
	private String email;
	
	
	@Column(name = "guichet_client")
	private Long guichetClient;
	
	@Column(name = "guichet_fournisseur")
	private Long guichetFournisseur;
	
	@Column(name = "prefix_facture")
	private String prefixFacture;
	
	@Column(name = "prefix_bon_livraison")
	private String prefixBonLivraison;
	
	@Column(name = "prefix_avoir")
	private String prefixAvoir;
	
	

	@Column(name = "matricule_fiscal")
	private String matriculeFiscal;
	
	
	@Column(name = "prefix_client")
	private String prefixClient;
	
	@Column(name = "prefix_fournisseur")
	private String prefixFournisseur;
	
	
	
	// used for disable update reference from front 
	
	@Column(name = "disable_finance")
	private boolean disableFinance;
	
	@Column(name = "disable_parametrage")
	private boolean disableParametrage;
	
	@Column(name = "disable_vente")
	private boolean disableVente;
	
	@Column(name = "disable_achat")
	private boolean disableAchat;
	
  //fin.  used for disable update reference from front  */
	
	
	// used for disable menu stock 
	@Column(name = "stock")
	private boolean hasStock;
	
	// used for disable menu achat  and finance fournisseur
	@Column(name = "achat")
	private boolean hasAchat;
	
	// used for disable sous menu caisse et mvtCaisse dans menu Vente
	@Column(name = "caisse")
	private boolean hasCaisse;

	
	
	
	@Column(name = "archive_directory")
	private String archiveDirectory;
	
	
	@Column(name = "LOGO_PNG")
	private String logoPNG;
	
	
	
	@Column(name = "excel_directory")
	private String excelDirectory;
	
	
	
	@Column(name = "black_mode")
	private boolean blackMode;
	
	
	@Column(name = "date_demarrage")
	private Calendar dateDemarrage;
	
	
	
	
	
	@Column(name = "fodec")
	private Boolean fodec;
	
	@Column(name = "tva_19")
	private Boolean tva19;
	
	
	@Column(name="ref_commande_facture_obligatoire")
	private Boolean refCommandeFactureObligatoire;
	
	
	@Column(name="identifiant_facture_obligatoire")
	private Boolean identifiantFactureObligatoire;
	
	
	
	@Column(name="contrainte_modification_bl")
	private Boolean contrainteModificationBl;
    @Column(name="details")
    private String details ;
    @Column(name="special")
    private String special;
    @Column(name="reglement_avoir")
    private Boolean reglementAvoir ;
    @Column(name="besoin ")
    private Boolean besoin ;
    @Column(name="reglement_bl")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getPrefixClient() {
		return prefixClient;
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



	public String getCodeTVA() {
		return codeTVA;
	}

	public void setCodeTVA(String codeTVA) {
		this.codeTVA = codeTVA;
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

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
