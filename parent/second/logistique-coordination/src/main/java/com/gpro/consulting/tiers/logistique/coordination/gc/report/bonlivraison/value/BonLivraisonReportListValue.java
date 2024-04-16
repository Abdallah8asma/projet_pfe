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
 * @since 01/03/2016
 *
 */
public class BonLivraisonReportListValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String referenceBl;
	private String referenceBs;
	private String partieInt;
	private Calendar dateLivraisonMin;
	private Calendar dateLivraisonMax;
	private Double metrageMin;
	private Double metrageMax;
	private Double prixMin;
	private Double prixMax;
	private String etat;
	
	private Long partieIntId;
	
	private List<BonLivraisonReportElementValue> productList = new ArrayList <BonLivraisonReportElementValue>();
	
	//Added on 17/11/2016, by Zeineb Medimagh
	private String avecFacture;
	
	private Boolean stock;

	private String numOF;
	
	public String getNumOF() {
		return numOF;
	}

	public void setNumOF(String numOF) {
		this.numOF = numOF;
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

	public List<BonLivraisonReportElementValue> getProductList() {
		return productList;
	}

	public void setProductList(List<BonLivraisonReportElementValue> productList) {
		this.productList = productList;
	}

	public String getReferenceBl() {
		return referenceBl;
	}

	public void setReferenceBl(String referenceBl) {
		this.referenceBl = referenceBl;
	}

	public String getReferenceBs() {
		return referenceBs;
	}

	public void setReferenceBs(String referenceBs) {
		this.referenceBs = referenceBs;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateLivraisonMin() {
		return dateLivraisonMin;
	}

	public void setDateLivraisonMin(Calendar dateLivraisonMin) {
		this.dateLivraisonMin = dateLivraisonMin;
	}

	public Calendar getDateLivraisonMax() {
		return dateLivraisonMax;
	}

	public void setDateLivraisonMax(Calendar dateLivraisonMax) {
		this.dateLivraisonMax = dateLivraisonMax;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getPartieInt() {
		return partieInt;
	}

	public void setPartieInt(String partieInt) {
		this.partieInt = partieInt;
	}

	public String getAvecFacture() {
		return avecFacture;
	}

	public void setAvecFacture(String avecFacture) {
		this.avecFacture = avecFacture;
	}

	public Boolean getStock() {
		return stock;
	}

	public void setStock(Boolean stock) {
		this.stock = stock;
	}

}
