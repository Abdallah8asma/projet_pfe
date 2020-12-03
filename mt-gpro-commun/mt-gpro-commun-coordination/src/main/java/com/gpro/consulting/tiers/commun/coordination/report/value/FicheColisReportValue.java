package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author hamdi etteieb
 * @since 08/12/2017
 *
 */
public class FicheColisReportValue {
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Long poidBrut;
	private Long poidNet;
	private String produitReference;
	private String numOrdreFabrication;
	
	
	private List<ColisValue> colisList = new ArrayList<ColisValue>();
	
	//private List<ColisValue>  cartonList=new ArrayList<ColisValue>();
	
	
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
	public Long getPoidBrut() {
		return poidBrut;
	}
	public void setPoidBrut(Long poidBrut) {
		this.poidBrut = poidBrut;
	}
	public Long getPoidNet() {
		return poidNet;
	}
	public void setPoidNet(Long poidNet) {
		this.poidNet = poidNet;
	}
	public String getProduitReference() {
		return produitReference;
	}
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}
	public String getNumOrdreFabrication() {
		return numOrdreFabrication;
	}
	public void setNumOrdreFabrication(String numOrdreFabrication) {
		this.numOrdreFabrication = numOrdreFabrication;
	}
	public List<ColisValue> getColisList() {
		return colisList;
	}
	public void setColisList(List<ColisValue> colisList) {
		this.colisList = colisList;
	}
	
	
	
	
	

}
