package com.gpro.consulting.tiers.logistique.coordination.atelier.report.rouleaufini.value;

import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class EtiquetteRouleauFiniReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	/** Client */
	private String partieInteresse;
		
	/** Tissu */
	private String produit;
				
	/** Composition	*/
	private String composition;
	
	/** Reference	*/
	private String reference;
				
	/** Laize */
	private Double laize;

	/** Choix */
	public String choix;

	/** Metrage */
	private Double metrage;

	/** Poids */
	private Double poids;
				
	/** BarreCode (Reference de Rouleau) */
	private String codeBarre;
	
	/** Reference Mise */
	private String referenceMise;
	
	
	
	/**
	 * Getters and settres methods
	 */
	

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

	public String getPartieInteresse() {
		return partieInteresse;
	}

	public void setPartieInteresse(String partieInteresse) {
		this.partieInteresse = partieInteresse;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public Double getLaize() {
		return laize;
	}

	public void setLaize(Double laize) {
		this.laize = laize;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public Double getMetrage() {
		return metrage;
	}

	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	/**
	 * @return the referenceMise
	 */
	public String getReferenceMise() {
		return referenceMise;
	}

	/**
	 * @param referenceMise the referenceMise to set
	 */
	public void setReferenceMise(String referenceMise) {
		this.referenceMise = referenceMise;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	
	
}
