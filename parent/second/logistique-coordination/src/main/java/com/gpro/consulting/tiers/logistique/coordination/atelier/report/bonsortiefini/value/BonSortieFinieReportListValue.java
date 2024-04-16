package com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value;

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
public class BonSortieFinieReportListValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String reference;
	private Calendar dateSortieMin;
	private Calendar dateSortieMax;
	private String type; 
	private String partieInt;
	private String rempli;
	
	private Long partieIntId;
	
	private List<BonSortieReportElementValue> productList = new ArrayList <BonSortieReportElementValue>();

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

	public List<BonSortieReportElementValue> getProductList() {
		return productList;
	}

	public void setProductList(List<BonSortieReportElementValue> productList) {
		this.productList = productList;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDateSortieMin() {
		return dateSortieMin;
	}

	public void setDateSortieMin(Calendar dateSortieMin) {
		this.dateSortieMin = dateSortieMin;
	}

	public Calendar getDateSortieMax() {
		return dateSortieMax;
	}

	public void setDateSortieMax(Calendar dateSortieMax) {
		this.dateSortieMax = dateSortieMax;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPartieInt() {
		return partieInt;
	}

	public void setPartieInt(String partieInt) {
		this.partieInt = partieInt;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public String getRempli() {
		return rempli;
	}

	public void setRempli(String rempli) {
		this.rempli = rempli;
	}
	
	
}
