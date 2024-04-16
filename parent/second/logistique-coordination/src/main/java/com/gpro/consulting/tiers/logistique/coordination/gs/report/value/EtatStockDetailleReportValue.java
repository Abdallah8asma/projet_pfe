package com.gpro.consulting.tiers.logistique.coordination.gs.report.value;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Zeineb MEDIMAGH
 * @since 30/10/2016
 *
 */
public class EtatStockDetailleReportValue{
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String type; //type article
	private String typeRapport;
	
	Set<EtatStockDetailleElementReportValue> elementsList = new TreeSet<EtatStockDetailleElementReportValue>();

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

	public void setJRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<EtatStockDetailleElementReportValue> getElementsList() {
		return elementsList;
	}

	public void setElementsList(Set<EtatStockDetailleElementReportValue> elementsList) {
		this.elementsList = elementsList;
	}

	public String getTypeRapport() {
		return typeRapport;
	}

	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}

}
