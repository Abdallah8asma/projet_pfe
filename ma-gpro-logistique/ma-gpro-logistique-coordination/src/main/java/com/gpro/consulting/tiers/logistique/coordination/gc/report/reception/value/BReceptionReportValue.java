package com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Hajer AMRI
 * @since 23/03/2017
 *
 */
public class BReceptionReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String reference;
	private Calendar dateReception;
	private Calendar dateLivraison;
	private String site;
	private Double prixTotal;
	private Double quantiteTotale;
	private String observations;

	/***** Infos client ********/
	private String clientRaisonSociale;
	private String clientAbbreviation;
	private String clientAdresse;
	private String clientTelephone;
	private String clientFax;
	private String clientMatriculeFiscal;

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

	private Double montantTTC;
	private Double montantHTTotal;
	private Double montantRemiseTotal;
	private Double montantTaxes;

	private String montantTTCToWords;

	private String adresseCompagnie;
	private String villeCompagnie;
	private String codeTVACompagnie;
	private String telephoneFixCompagnie;
	private String telephoneMoblieCompagnie;
	private String faxCompagnie;
	private String emailCompagnie;
	private String matriculeFiscalCompagnie;

	private List<BReceptionReportProductValue> listeProduitReceptions = new ArrayList<BReceptionReportProductValue>();

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

	public String getMatriculeFiscalCompagnie() {
		return matriculeFiscalCompagnie;
	}

	public void setMatriculeFiscalCompagnie(String matriculeFiscalCompagnie) {
		this.matriculeFiscalCompagnie = matriculeFiscalCompagnie;
	}

	public String getMontantTTCToWords() {
		return montantTTCToWords;
	}

	public void setMontantTTCToWords(String montantTTCToWords) {
		this.montantTTCToWords = montantTTCToWords;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public Double getMontantHTTotal() {
		return montantHTTotal;
	}

	public void setMontantHTTotal(Double montantHTTotal) {
		this.montantHTTotal = montantHTTotal;
	}

	public Double getMontantRemiseTotal() {
		return montantRemiseTotal;
	}

	public void setMontantRemiseTotal(Double montantRemiseTotal) {
		this.montantRemiseTotal = montantRemiseTotal;
	}

	public Double getMontantTaxes() {
		return montantTaxes;
	}

	public void setMontantTaxes(Double montantTaxes) {
		this.montantTaxes = montantTaxes;
	}

	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public Boolean getExistFodec() {
		return existFodec;
	}

	public void setExistFodec(Boolean existFodec) {
		this.existFodec = existFodec;
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

	public Boolean getExistTVA() {
		return existTVA;
	}

	public void setExistTVA(Boolean existTVA) {
		this.existTVA = existTVA;
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

	public Double getAssietteTVA() {
		return assietteTVA;
	}

	public void setAssietteTVA(Double assietteTVA) {
		this.assietteTVA = assietteTVA;
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

	public Boolean getExistTimbre() {
		return existTimbre;
	}

	public void setExistTimbre(Boolean existTimbre) {
		this.existTimbre = existTimbre;
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

	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setJRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<BReceptionReportProductValue> getListeProduitReceptions() {
		return listeProduitReceptions;
	}

	public void setListeProduitReceptions(List<BReceptionReportProductValue> listeProduitReceptions) {
		this.listeProduitReceptions = listeProduitReceptions;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDateReception() {
		return dateReception;
	}

	public void setDateReception(Calendar dateReception) {
		this.dateReception = dateReception;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Double getQuantiteTotale() {
		return quantiteTotale;
	}

	public void setQuantiteTotale(Double quantiteTotale) {
		this.quantiteTotale = quantiteTotale;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getClientAbbreviation() {
		return clientAbbreviation;
	}

	public void setClientAbbreviation(String clientAbbreviation) {
		this.clientAbbreviation = clientAbbreviation;
	}

	public String getClientRaisonSociale() {
		return clientRaisonSociale;
	}

	public void setClientRaisonSociale(String clientRaisonSociale) {
		this.clientRaisonSociale = clientRaisonSociale;
	}

	public String getClientAdresse() {
		return clientAdresse;
	}

	public void setClientAdresse(String clientAdresse) {
		this.clientAdresse = clientAdresse;
	}

	public String getClientTelephone() {
		return clientTelephone;
	}

	public void setClientTelephone(String clientTelephone) {
		this.clientTelephone = clientTelephone;
	}

	public String getClientFax() {
		return clientFax;
	}

	public void setClientFax(String clientFax) {
		this.clientFax = clientFax;
	}

	public String getClientMatriculeFiscal() {
		return clientMatriculeFiscal;
	}

	public void setClientMatriculeFiscal(String clientMatriculeFiscal) {
		this.clientMatriculeFiscal = clientMatriculeFiscal;
	}

}
