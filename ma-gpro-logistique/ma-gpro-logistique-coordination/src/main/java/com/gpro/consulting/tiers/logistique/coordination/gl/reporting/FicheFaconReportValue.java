package com.gpro.consulting.tiers.logistique.coordination.gl.reporting;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Zeineb Medimagh
 * @since 24/10/2016
 *
 */
public class FicheFaconReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private List<FicheFaconReportElementValue> traitementList = new ArrayList <FicheFaconReportElementValue>();
	
	private String referenceBR;
	private String observations;
	private String client;
	private String referenceProduit;
	private String designationProduit;
	private String codeCouleur;
	private String methodeTeinture;
	private String listRefMise;
	
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}
	public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}
	public List<FicheFaconReportElementValue> getTraitementList() {
		return traitementList;
	}
	public void setTraitementList(List<FicheFaconReportElementValue> traitementList) {
		this.traitementList = traitementList;
	}
	public String getReferenceBR() {
		return referenceBR;
	}
	public void setReferenceBR(String referenceBR) {
		this.referenceBR = referenceBR;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getReferenceProduit() {
		return referenceProduit;
	}
	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}
	public String getDesignationProduit() {
		return designationProduit;
	}
	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}
	public String getCodeCouleur() {
		return codeCouleur;
	}
	public void setCodeCouleur(String codeCouleur) {
		this.codeCouleur = codeCouleur;
	}
	public String getMethodeTeinture() {
		return methodeTeinture;
	}
	public void setMethodeTeinture(String methodeTeinture) {
		this.methodeTeinture = methodeTeinture;
	}
	public String getListRefMise() {
		return listRefMise;
	}
	public void setListRefMise(String listRefMise) {
		this.listRefMise = listRefMise;
	}

}
