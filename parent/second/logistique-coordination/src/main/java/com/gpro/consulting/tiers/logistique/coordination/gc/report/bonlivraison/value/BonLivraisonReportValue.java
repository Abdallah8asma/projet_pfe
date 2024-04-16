package com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Wahid Gazzah
 * @since 29/01/2016
 *
 */
public class BonLivraisonReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<BonLivraisonReportProductValue> productList = new ArrayList<BonLivraisonReportProductValue>();

	private List<BonLivraisonReportTaxeValue> taxeList;

	private Long clientId;

	private Calendar dateBl;
	private String matriculeFiscal;
	private Double montantRemiseTotal;
	private String reference;
	private String observations;
	private String client;
	private String adresse;
	private String telephone;
	private String fax;

	private Double montantTTC;
	private Double montantHTTotal;

	private String montantTTCToWords;

	// FODEC params
	private Boolean existFodec;
	private String taxeFodec;
	private Double tauxFodec;
	private Double montantTaxeFodec;
	private Double assietteFodec;

	// TVA19 params
	private Boolean existTVA;
	private String taxeTVA;
	private Double tauxTVA;
	private Double montantTaxeTVA;
	private Double assietteTVA;

	// TVA7 params
	private Boolean existTVA7;
	private String taxeTVA7;
	private Double tauxTVA7;
	private Double montantTaxeTVA7;
	private Double assietteTVA7;

	// TVA13 params
	private Boolean existTVA13;
	private String taxeTVA13;
	private Double tauxTVA13;
	private Double montantTaxeTVA13;
	private Double assietteTVA13;

	// TIMBRE params
	private Boolean existTimbre;
	private String taxeTimbre;
	private Double montantTaxeTimbre;
	private Double assietteTimbre;

	// total des daxes
	private Double montantTaxes;

	// added on 23/02/2016, by Wahid Gazzah
	private Long modepaiementId;
	private Long marcheId;
	private String modepaiement;
	private String marche;

	// added on 16/03/2018
	private Integer tauxTvaArt;

	// Added on 25/04/2018
	private Long idCamion;
	private Long idRemorque;
	private Long idChauffeur;

	private String camion;
	private String remorque;
	private String chauffeur;

	private String commande;

	private String magasin;

	// Added on 24/05/2018

	// Pour BL avec des articles converstis
	private Boolean conversion;

	// Pour Generation de BL avec montant TTC
	private Boolean ttc;

	private String adresseCompagnie;
	private String villeCompagnie;
	private String codeTVACompagnie;
	private String telephoneFixCompagnie;
	private String telephoneMoblieCompagnie;
	private String faxCompagnie;
	private String emailCompagnie;
	private String matriculeFiscalCompagnie;

	private String groupeClientDesignation;

	private Double montantRegler;

	private Double montantNonRegler;
	
	
	
	private String codePostalClient;

	private String villeClient;
	
	private String identifiant;
	
	
	
	
	public String getCodePostalClient() {
		return codePostalClient;
	}

	public void setCodePostalClient(String codePostalClient) {
		this.codePostalClient = codePostalClient;
	}

	public String getVilleClient() {
		return villeClient;
	}

	public void setVilleClient(String villeClient) {
		this.villeClient = villeClient;
	}

	public Double getMontantNonRegler() {
		return montantNonRegler;
	}

	public void setMontantNonRegler(Double montantNonRegler) {
		this.montantNonRegler = montantNonRegler;
	}

	public Double getMontantRegler() {
		return montantRegler;
	}

	public void setMontantRegler(Double montantRegler) {
		this.montantRegler = montantRegler;
	}

	public String getMatriculeFiscalCompagnie() {
		return matriculeFiscalCompagnie;
	}

	public void setMatriculeFiscalCompagnie(String matriculeFiscalCompagnie) {
		this.matriculeFiscalCompagnie = matriculeFiscalCompagnie;
	}

	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getAdresseCompagnie() {
		return adresseCompagnie;
	}

	public void setAdresseCompagnie(String adresseCompagnie) {
		this.adresseCompagnie = adresseCompagnie;
	}

	public String getVilleCompagnie() {
		return villeCompagnie;
	}

	public void setVilleCompagnie(String villeCompagnie) {
		this.villeCompagnie = villeCompagnie;
	}

	public String getCodeTVACompagnie() {
		return codeTVACompagnie;
	}

	public void setCodeTVACompagnie(String codeTVACompagnie) {
		this.codeTVACompagnie = codeTVACompagnie;
	}

	public String getTelephoneFixCompagnie() {
		return telephoneFixCompagnie;
	}

	public void setTelephoneFixCompagnie(String telephoneFixCompagnie) {
		this.telephoneFixCompagnie = telephoneFixCompagnie;
	}

	public String getTelephoneMoblieCompagnie() {
		return telephoneMoblieCompagnie;
	}

	public void setTelephoneMoblieCompagnie(String telephoneMoblieCompagnie) {
		this.telephoneMoblieCompagnie = telephoneMoblieCompagnie;
	}

	public String getFaxCompagnie() {
		return faxCompagnie;
	}

	public void setFaxCompagnie(String faxCompagnie) {
		this.faxCompagnie = faxCompagnie;
	}

	public String getEmailCompagnie() {
		return emailCompagnie;
	}

	public void setEmailCompagnie(String emailCompagnie) {
		this.emailCompagnie = emailCompagnie;
	}

	public Boolean getTtc() {
		return ttc;
	}

	public void setTtc(Boolean ttc) {
		this.ttc = ttc;
	}

	public Integer getTauxTvaArt() {
		return tauxTvaArt;
	}

	public void setTauxTvaArt(Integer tauxTvaArt) {
		this.tauxTvaArt = tauxTvaArt;
	}

	// added on 07/10/2016, by Zeineb Medimagh
	private List<BonLivraisonReportTraitementFaconValue> traitementFaconList = new ArrayList<BonLivraisonReportTraitementFaconValue>();

	public InputStream getReportStream() {
		return reportStream;
	}

	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
	}

	public HashMap<String, Object> getParams() {
		return params;
	}

	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDateBl() {
		return dateBl;
	}

	public void setDateBl(Calendar dateBl) {
		this.dateBl = dateBl;
	}

	public String getMatriculeFiscal() {
		return matriculeFiscal;
	}

	public void setMatriculeFiscal(String matriculeFiscal) {
		this.matriculeFiscal = matriculeFiscal;
	}

	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setJRBeanCollectionDataSourceProduct(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public List<BonLivraisonReportProductValue> getProductList() {
		return productList;
	}

	public void setProductList(List<BonLivraisonReportProductValue> productList) {
		this.productList = productList;
	}

	public List<BonLivraisonReportTaxeValue> getTaxeList() {
		return taxeList;
	}

	public void setTaxeList(List<BonLivraisonReportTaxeValue> taxeList) {
		this.taxeList = taxeList;
	}

	public Double getMontantRemiseTotal() {
		return montantRemiseTotal;
	}

	public void setMontantRemiseTotal(Double montantRemiseTotal) {
		this.montantRemiseTotal = montantRemiseTotal;
	}

	public Double getMontantHTTotal() {
		return montantHTTotal;
	}

	public void setMontantHTTotal(Double montantHTTotal) {
		this.montantHTTotal = montantHTTotal;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public String getTaxeTVA() {
		return taxeTVA;
	}

	public void setTaxeTVA(String taxeTVA) {
		this.taxeTVA = taxeTVA;
	}

	public Double getTauxTVA() {
		return tauxTVA;
	}

	public void setTauxTVA(Double tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	public Double getMontantTaxeTVA() {
		return montantTaxeTVA;
	}

	public void setMontantTaxeTVA(Double montantTaxeTVA) {
		this.montantTaxeTVA = montantTaxeTVA;
	}

	public Double getMontantTaxes() {
		return montantTaxes;
	}

	public void setMontantTaxes(Double montantTaxes) {
		this.montantTaxes = montantTaxes;
	}

	public String getTaxeFodec() {
		return taxeFodec;
	}

	public void setTaxeFodec(String taxeFodec) {
		this.taxeFodec = taxeFodec;
	}

	public Double getTauxFodec() {
		return tauxFodec;
	}

	public void setTauxFodec(Double tauxFodec) {
		this.tauxFodec = tauxFodec;
	}

	public Double getMontantTaxeFodec() {
		return montantTaxeFodec;
	}

	public void setMontantTaxeFodec(Double montantTaxeFodec) {
		this.montantTaxeFodec = montantTaxeFodec;
	}

	public Double getAssietteFodec() {
		return assietteFodec;
	}

	public void setAssietteFodec(Double assietteFodec) {
		this.assietteFodec = assietteFodec;
	}

	public Double getAssietteTVA() {
		return assietteTVA;
	}

	public void setAssietteTVA(Double assietteTVA) {
		this.assietteTVA = assietteTVA;
	}

	public String getTaxeTimbre() {
		return taxeTimbre;
	}

	public void setTaxeTimbre(String taxeTimbre) {
		this.taxeTimbre = taxeTimbre;
	}

	public Double getMontantTaxeTimbre() {
		return montantTaxeTimbre;
	}

	public void setMontantTaxeTimbre(Double montantTaxeTimbre) {
		this.montantTaxeTimbre = montantTaxeTimbre;
	}

	public Double getAssietteTimbre() {
		return assietteTimbre;
	}

	public void setAssietteTimbre(Double assietteTimbre) {
		this.assietteTimbre = assietteTimbre;
	}

	public Boolean getExistFodec() {
		return existFodec;
	}

	public void setExistFodec(Boolean existFodec) {
		this.existFodec = existFodec;
	}

	public Boolean getExistTVA() {
		return existTVA;
	}

	public void setExistTVA(Boolean existTVA) {
		this.existTVA = existTVA;
	}

	public Boolean getExistTimbre() {
		return existTimbre;
	}

	public void setExistTimbre(Boolean existTimbre) {
		this.existTimbre = existTimbre;
	}

	public String getMontantTTCToWords() {
		return montantTTCToWords;
	}

	public void setMontantTTCToWords(String montantTTCToWords) {
		this.montantTTCToWords = montantTTCToWords;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}

	public Long getMarcheId() {
		return marcheId;
	}

	public void setMarcheId(Long marcheId) {
		this.marcheId = marcheId;
	}

	public String getModepaiement() {
		return modepaiement;
	}

	public void setModepaiement(String modepaiement) {
		this.modepaiement = modepaiement;
	}

	public String getMarche() {
		return marche;
	}

	public void setMarche(String marche) {
		this.marche = marche;
	}

	public List<BonLivraisonReportTraitementFaconValue> getTraitementFaconList() {
		return traitementFaconList;
	}

	public void setTraitementFaconList(List<BonLivraisonReportTraitementFaconValue> traitementFaconList) {
		this.traitementFaconList = traitementFaconList;
	}

	public Boolean getExistTVA7() {
		return existTVA7;
	}

	public void setExistTVA7(Boolean existTVA7) {
		this.existTVA7 = existTVA7;
	}

	public String getTaxeTVA7() {
		return taxeTVA7;
	}

	public void setTaxeTVA7(String taxeTVA7) {
		this.taxeTVA7 = taxeTVA7;
	}

	public Double getTauxTVA7() {
		return tauxTVA7;
	}

	public void setTauxTVA7(Double tauxTVA7) {
		this.tauxTVA7 = tauxTVA7;
	}

	public Double getMontantTaxeTVA7() {
		return montantTaxeTVA7;
	}

	public void setMontantTaxeTVA7(Double montantTaxeTVA7) {
		this.montantTaxeTVA7 = montantTaxeTVA7;
	}

	public Double getAssietteTVA7() {
		return assietteTVA7;
	}

	public void setAssietteTVA7(Double assietteTVA7) {
		this.assietteTVA7 = assietteTVA7;
	}

	public Boolean getExistTVA13() {
		return existTVA13;
	}

	public void setExistTVA13(Boolean existTVA13) {
		this.existTVA13 = existTVA13;
	}

	public String getTaxeTVA13() {
		return taxeTVA13;
	}

	public void setTaxeTVA13(String taxeTVA13) {
		this.taxeTVA13 = taxeTVA13;
	}

	public Double getTauxTVA13() {
		return tauxTVA13;
	}

	public void setTauxTVA13(Double tauxTVA13) {
		this.tauxTVA13 = tauxTVA13;
	}

	public Double getMontantTaxeTVA13() {
		return montantTaxeTVA13;
	}

	public void setMontantTaxeTVA13(Double montantTaxeTVA13) {
		this.montantTaxeTVA13 = montantTaxeTVA13;
	}

	public Double getAssietteTVA13() {
		return assietteTVA13;
	}

	public void setAssietteTVA13(Double assietteTVA13) {
		this.assietteTVA13 = assietteTVA13;
	}

	public Long getIdCamion() {
		return idCamion;
	}

	public void setIdCamion(Long idCamion) {
		this.idCamion = idCamion;
	}

	public Long getIdRemorque() {
		return idRemorque;
	}

	public void setIdRemorque(Long idRemorque) {
		this.idRemorque = idRemorque;
	}

	public Long getIdChauffeur() {
		return idChauffeur;
	}

	public void setIdChauffeur(Long idChauffeur) {
		this.idChauffeur = idChauffeur;
	}

	public String getCamion() {
		return camion;
	}

	public void setCamion(String camion) {
		this.camion = camion;
	}

	public String getRemorque() {
		return remorque;
	}

	public void setRemorque(String remorque) {
		this.remorque = remorque;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public String getCommande() {
		return commande;
	}

	public void setCommande(String commande) {
		this.commande = commande;
	}

	public String getMagasin() {
		return magasin;
	}

	public void setMagasin(String magasin) {
		this.magasin = magasin;
	}

	public Boolean getConversion() {
		return conversion;
	}

	public void setConversion(Boolean conversion) {
		this.conversion = conversion;
	}

	public String getGroupeClientDesignation() {
		return groupeClientDesignation;
	}

	public void setGroupeClientDesignation(String groupeClientDesignation) {
		this.groupeClientDesignation = groupeClientDesignation;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

}
