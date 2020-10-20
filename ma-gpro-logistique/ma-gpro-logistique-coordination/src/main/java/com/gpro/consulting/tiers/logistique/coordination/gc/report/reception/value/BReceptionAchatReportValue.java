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
public class BReceptionAchatReportValue {
	
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
	
	private List<BReceptionReportProductValue> listeProduitReceptions = new ArrayList <BReceptionReportProductValue>();

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
