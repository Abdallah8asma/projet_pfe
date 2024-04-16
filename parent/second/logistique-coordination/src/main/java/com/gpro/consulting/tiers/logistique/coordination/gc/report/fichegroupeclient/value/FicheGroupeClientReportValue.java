package com.gpro.consulting.tiers.logistique.coordination.gc.report.fichegroupeclient.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
public class FicheGroupeClientReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String clientReference;
	private String clientAbreviation;
	private String clientAdresse;
	private String clientTelephone;

	private String groupeClientDesignation;

	private Calendar dateMin;
	private Calendar dateMax;

	private Double debitTotal;
	private Double creditTotal;
	private Double soldeClient;
	private Double soldeInitial;

	private List<FicheGroupeClientElementValue> listElements = new ArrayList<FicheGroupeClientElementValue>();

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

	public List<FicheGroupeClientElementValue> getListElements() {
		return listElements;
	}

	public void setListElements(List<FicheGroupeClientElementValue> listElements) {
		this.listElements = listElements;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public Calendar getDateMin() {
		return dateMin;
	}

	public void setDateMin(Calendar dateMin) {
		this.dateMin = dateMin;
	}

	public Calendar getDateMax() {
		return dateMax;
	}

	public void setDateMax(Calendar dateMax) {
		this.dateMax = dateMax;
	}

	public Double getDebitTotal() {
		return debitTotal;
	}

	public void setDebitTotal(Double debitTotal) {
		this.debitTotal = debitTotal;
	}

	public Double getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(Double creditTotal) {
		this.creditTotal = creditTotal;
	}

	public Double getSoldeClient() {
		return soldeClient;
	}

	public void setSoldeClient(Double soldeClient) {
		this.soldeClient = soldeClient;
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

	public Double getSoldeInitial() {
		return soldeInitial;
	}

	public void setSoldeInitial(Double soldeInitial) {
		this.soldeInitial = soldeInitial;
	}

	public String getGroupeClientDesignation() {
		return groupeClientDesignation;
	}

	public void setGroupeClientDesignation(String groupeClientDesignation) {
		this.groupeClientDesignation = groupeClientDesignation;
	}

}
