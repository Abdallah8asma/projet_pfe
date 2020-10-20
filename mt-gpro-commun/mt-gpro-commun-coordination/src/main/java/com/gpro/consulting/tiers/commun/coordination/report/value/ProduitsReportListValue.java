package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
public class ProduitsReportListValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private List<ProduitReportElementValue> productList = new ArrayList <ProduitReportElementValue>();
	
	private String reference;
	private String designation ;
	private String famille; 
	private String sousfamille;
	private String partieInteressee ;
	private String etat;
	private String site;
	private Double prixInf;
	private Double prixSup; 

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

	

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public String getSousfamille() {
		return sousfamille;
	}

	public void setSousfamille(String sousfamille) {
		this.sousfamille = sousfamille;
	}

	public String getPartieInteressee() {
		return partieInteressee;
	}

	public void setPartieInteressee(String partieInteressee) {
		this.partieInteressee = partieInteressee;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Double getPrixInf() {
		return prixInf;
	}

	public void setPrixInf(Double prixInf) {
		this.prixInf = prixInf;
	}

	public Double getPrixSup() {
		return prixSup;
	}

	public void setPrixSup(Double prixSup) {
		this.prixSup = prixSup;
	}

	public List<ProduitReportElementValue> getProductList() {
		return productList;
	}

	public void setProductList(List<ProduitReportElementValue> productList) {
		this.productList = productList;
	}
	
	
}
