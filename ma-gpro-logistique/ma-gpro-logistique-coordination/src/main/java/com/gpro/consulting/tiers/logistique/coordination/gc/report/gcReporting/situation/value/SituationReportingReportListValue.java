package com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 *
 */
public class SituationReportingReportListValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Long regionId;
	private Long partieIntId;
	private Calendar dateMin;
	private Calendar dateMax;
	private Double soldeMin;
	private Double soldeMax;
	
	private String clientReference;
	private String clientAbreviation;
	private String regionDesignation;
	
	private List<SituationReportingValue> listSituation = new ArrayList<SituationReportingValue>();

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

	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
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

	public String getRegionDesignation() {
		return regionDesignation;
	}

	public void setRegionDesignation(String regionDesignation) {
		this.regionDesignation = regionDesignation;
	}

	public List<SituationReportingValue> getListSituation() {
		return listSituation;
	}

	public void setListSituation(List<SituationReportingValue> listSituation) {
		this.listSituation = listSituation;
	}

	public Double getSoldeMin() {
		return soldeMin;
	}

	public void setSoldeMin(Double soldeMin) {
		this.soldeMin = soldeMin;
	}

	public Double getSoldeMax() {
		return soldeMax;
	}

	public void setSoldeMax(Double soldeMax) {
		this.soldeMax = soldeMax;
	}
	
}
