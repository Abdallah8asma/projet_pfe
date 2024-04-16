package com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonReception.value;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 17/12/2015
 *
 */
public class BonReceptionReportValue {

	private Long id;
	//
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String produit;
	private String reference;
	private Calendar dateIntroduction;
	private Calendar dateLivraison;
	private String observations;
	private String bcClient;
//	private Double metrageAnnonce;
//	private Double poidsAnnonce;
	private Double metrageTrouve;
	private Double poidsTrouve;
	private Long nombreRouleau;
	private Double laizeFini;
	private String etat;
	//
	private String partieInteressee;
	private List<RouleauEcruReportValue> listeRouleauxEcru;
	private List<ReceptionTraitementReportValue> listeTraitements;
	
	private String designationpartieInteresse;
	private String designationProduit;
	private String sousFamilleDesignation;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProduit() {
		return produit;
	}
	public void setProduit(String produit) {
		this.produit = produit;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}
	public Calendar getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getBcClient() {
		return bcClient;
	}
	public void setBcClient(String bcClient) {
		this.bcClient = bcClient;
	}
//	public Double getMetrageAnnonce() {
//		return metrageAnnonce;
//	}
//	public void setMetrageAnnonce(Double metrageAnnonce) {
//		this.metrageAnnonce = metrageAnnonce;
//	}
//	public Double getPoidsAnnonce() {
//		return poidsAnnonce;
//	}
//	public void setPoidsAnnonce(Double poidsAnnonce) {
//		this.poidsAnnonce = poidsAnnonce;
//	}
	public Double getMetrageTrouve() {
		return metrageTrouve;
	}
	public void setMetrageTrouve(Double metrageTrouve) {
		this.metrageTrouve = metrageTrouve;
	}
	public Double getPoidsTrouve() {
		return poidsTrouve;
	}
	public void setPoidsTrouve(Double poidsTrouve) {
		this.poidsTrouve = poidsTrouve;
	}
	public Long getNombreRouleau() {
		return nombreRouleau;
	}
	public void setNombreRouleau(Long nombreRouleau) {
		this.nombreRouleau = nombreRouleau;
	}
	public Double getLaizeFini() {
		return laizeFini;
	}
	public void setLaizeFini(Double laizeFini) {
		this.laizeFini = laizeFini;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getPartieInteressee() {
		return partieInteressee;
	}
	public void setPartieInteressee(String partieInteressee) {
		this.partieInteressee = partieInteressee;
	}
	public List<RouleauEcruReportValue> getListeRouleauxEcru() {
		return listeRouleauxEcru;
	}
	public void setListeRouleauxEcru(List<RouleauEcruReportValue> listeRouleauxEcru) {
		this.listeRouleauxEcru = listeRouleauxEcru;
	}
	public List<ReceptionTraitementReportValue> getListeTraitements() {
		return listeTraitements;
	}
	public void setListeTraitements(
			List<ReceptionTraitementReportValue> listeTraitements) {
		this.listeTraitements = listeTraitements;
	}
	public String getDesignationpartieInteresse() {
		return designationpartieInteresse;
	}
	public void setDesignationpartieInteresse(String designationpartieInteresse) {
		this.designationpartieInteresse = designationpartieInteresse;
	}
	public String getDesignationProduit() {
		return designationProduit;
	}
	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}
	public String getSousFamilleDesignation() {
		return sousFamilleDesignation;
	}
	public void setSousFamilleDesignation(String sousFamilleDesignation) {
		this.sousFamilleDesignation = sousFamilleDesignation;
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


	
	
}
