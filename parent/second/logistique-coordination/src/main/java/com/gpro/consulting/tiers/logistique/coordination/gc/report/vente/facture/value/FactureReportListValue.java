package com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Wahid Gazzah
 * @since 01/03/2016
 *
 */
public class FactureReportListValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String referenceFacture;
	private String referenceBl;
	private String partieInt;
	private Calendar dateFactureMin;
	private Calendar dateFactureMax;
	private Double metrageMin;
	private Double metrageMax;
	private Double prixMin;
	private Double prixMax;
	
	private Long partieIntId;
	private String type; //Normal, Avoir
	
	private List<FactureReportElementValue> productList = new ArrayList <FactureReportElementValue>();

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

	public List<FactureReportElementValue> getProductList() {
		return productList;
	}

	public void setProductList(List<FactureReportElementValue> productList) {
		this.productList = productList;
	}

	public String getReferenceFacture() {
		return referenceFacture;
	}

	public void setReferenceFacture(String referenceFacture) {
		this.referenceFacture = referenceFacture;
	}

	public String getReferenceBl() {
		return referenceBl;
	}

	public void setReferenceBl(String referenceBl) {
		this.referenceBl = referenceBl;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateFactureMin() {
		return dateFactureMin;
	}

	public void setDateFactureMin(Calendar dateFactureMin) {
		this.dateFactureMin = dateFactureMin;
	}

	public Calendar getDateFactureMax() {
		return dateFactureMax;
	}

	public void setDateFactureMax(Calendar dateFactureMax) {
		this.dateFactureMax = dateFactureMax;
	}

	public Double getMetrageMin() {
		return metrageMin;
	}

	public void setMetrageMin(Double metrageMin) {
		this.metrageMin = metrageMin;
	}

	public Double getMetrageMax() {
		return metrageMax;
	}

	public void setMetrageMax(Double metrageMax) {
		this.metrageMax = metrageMax;
	}

	public Double getPrixMin() {
		return prixMin;
	}

	public void setPrixMin(Double prixMin) {
		this.prixMin = prixMin;
	}

	public Double getPrixMax() {
		return prixMax;
	}

	public void setPrixMax(Double prixMax) {
		this.prixMax = prixMax;
	}

	public String getPartieInt() {
		return partieInt;
	}

	public void setPartieInt(String partieInt) {
		this.partieInt = partieInt;
	}

	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
