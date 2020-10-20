package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 29/03/2016
 */
public class FicheSuiveuseReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String referenceMise;
	private Double metrageMise;
	private Double poids;
	private Double laizeDemFini;
	
	private Long produitId;
	private String produitReference;
	private String produitReferenceInterne;
	private String produitDesignation;
	private String produitComposition;
	private String produitCodeCouleur;
	
	private String typeLivraison;
	private Calendar dateLancement;
	private Double volume;
	private Double poidsSortie;
	private Double laizeSortie;
	private Calendar dateSortie;
	
	private Long clientId;
	private String clientAbreviation;
	private String clientReference;
	
	private String marcheDesignation;
	
	private String rapportBain;
	private Double metrageSortie;
	
	private String avecRecette;
	
	
	private Boolean showExtraData = false;
	private Double poidsPES;
	private Double volumePES;
	private Double poidsCoton;
	private Double volumeCoton;
	
	private List<ElementControleReportValue> listElementControleTintureCoton;
	private List<ElementControleReportValue> listElementControleTinturePES;
	private List<ElementControleReportValue> listElementControleFinissage;
	private List<ElementControleReportValue> listElementControlePreparation;
	
	private List<TraitementMiseReportValue> listTraitementsMiseTintureCoton;
	private List<TraitementMiseReportValue> listTraitementsMiseTinturePES;
	private List<TraitementMiseReportValue> listTraitementsMiseFinissage;
	private List<TraitementMiseReportValue> listTraitementsMisePreparation;
	
	
	private Boolean showListElementRecetteMiseCoton;
	private Boolean showListElementRecetteMisePES;
	private Boolean showListElementRecetteMiseFinissage;
	private Boolean showListElementRecetteMisePreparation;
	
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
	public String getReferenceMise() {
		return referenceMise;
	}
	public void setReferenceMise(String referenceMise) {
		this.referenceMise = referenceMise;
	}
	public Double getMetrageMise() {
		return metrageMise;
	}
	public void setMetrageMise(Double metrageMise) {
		this.metrageMise = metrageMise;
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	public Double getLaizeDemFini() {
		return laizeDemFini;
	}
	public void setLaizeDemFini(Double laizeDemFini) {
		this.laizeDemFini = laizeDemFini;
	}
	public String getProduitReference() {
		return produitReference;
	}
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}
	public String getProduitDesignation() {
		return produitDesignation;
	}
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	public String getProduitComposition() {
		return produitComposition;
	}
	public void setProduitComposition(String produitComposition) {
		this.produitComposition = produitComposition;
	}
	public String getProduitCodeCouleur() {
		return produitCodeCouleur;
	}
	public void setProduitCodeCouleur(String produitCodeCouleur) {
		this.produitCodeCouleur = produitCodeCouleur;
	}
	public String getTypeLivraison() {
		return typeLivraison;
	}
	public void setTypeLivraison(String typeLivraison) {
		this.typeLivraison = typeLivraison;
	}
	public Calendar getDateLancement() {
		return dateLancement;
	}
	public void setDateLancement(Calendar dateLancement) {
		this.dateLancement = dateLancement;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getPoidsSortie() {
		return poidsSortie;
	}
	public void setPoidsSortie(Double poidsSortie) {
		this.poidsSortie = poidsSortie;
	}
	public Double getLaizeSortie() {
		return laizeSortie;
	}
	public void setLaizeSortie(Double laizeSortie) {
		this.laizeSortie = laizeSortie;
	}
	public Calendar getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getClientAbreviation() {
		return clientAbreviation;
	}
	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}
	public String getClientReference() {
		return clientReference;
	}
	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public List<ElementControleReportValue> getListElementControleTintureCoton() {
		return listElementControleTintureCoton;
	}
	public void setListElementControleTintureCoton(
			List<ElementControleReportValue> listElementControleTintureCoton) {
		this.listElementControleTintureCoton = listElementControleTintureCoton;
	}
	public List<ElementControleReportValue> getListElementControleTinturePES() {
		return listElementControleTinturePES;
	}
	public void setListElementControleTinturePES(
			List<ElementControleReportValue> listElementControleTinturePES) {
		this.listElementControleTinturePES = listElementControleTinturePES;
	}
	public List<ElementControleReportValue> getListElementControleFinissage() {
		return listElementControleFinissage;
	}
	public void setListElementControleFinissage(
			List<ElementControleReportValue> listElementControleFinissage) {
		this.listElementControleFinissage = listElementControleFinissage;
	}
	public List<ElementControleReportValue> getListElementControlePreparation() {
		return listElementControlePreparation;
	}
	public void setListElementControlePreparation(
			List<ElementControleReportValue> listElementControlePreparation) {
		this.listElementControlePreparation = listElementControlePreparation;
	}
	public List<TraitementMiseReportValue> getListTraitementsMiseTintureCoton() {
		return listTraitementsMiseTintureCoton;
	}
	public void setListTraitementsMiseTintureCoton(
			List<TraitementMiseReportValue> listTraitementsMiseTintureCoton) {
		this.listTraitementsMiseTintureCoton = listTraitementsMiseTintureCoton;
	}
	public List<TraitementMiseReportValue> getListTraitementsMiseTinturePES() {
		return listTraitementsMiseTinturePES;
	}
	public void setListTraitementsMiseTinturePES(
			List<TraitementMiseReportValue> listTraitementsMiseTinturePES) {
		this.listTraitementsMiseTinturePES = listTraitementsMiseTinturePES;
	}
	public List<TraitementMiseReportValue> getListTraitementsMiseFinissage() {
		return listTraitementsMiseFinissage;
	}
	public void setListTraitementsMiseFinissage(
			List<TraitementMiseReportValue> listTraitementsMiseFinissage) {
		this.listTraitementsMiseFinissage = listTraitementsMiseFinissage;
	}
	public List<TraitementMiseReportValue> getListTraitementsMisePreparation() {
		return listTraitementsMisePreparation;
	}
	public void setListTraitementsMisePreparation(
			List<TraitementMiseReportValue> listTraitementsMisePreparation) {
		this.listTraitementsMisePreparation = listTraitementsMisePreparation;
	}
	public String getRapportBain() {
		return rapportBain;
	}
	public void setRapportBain(String rapportBain) {
		this.rapportBain = rapportBain;
	}
	public Double getMetrageSortie() {
		return metrageSortie;
	}
	public void setMetrageSortie(Double metrageSortie) {
		this.metrageSortie = metrageSortie;
	}
	public String getAvecRecette() {
		return avecRecette;
	}
	public void setAvecRecette(String avecRecette) {
		this.avecRecette = avecRecette;
	}
	public Boolean getShowListElementRecetteMiseCoton() {
		return showListElementRecetteMiseCoton;
	}
	public void setShowListElementRecetteMiseCoton(
			Boolean showListElementRecetteMiseCoton) {
		this.showListElementRecetteMiseCoton = showListElementRecetteMiseCoton;
	}
	public Boolean getShowListElementRecetteMisePES() {
		return showListElementRecetteMisePES;
	}
	public void setShowListElementRecetteMisePES(
			Boolean showListElementRecetteMisePES) {
		this.showListElementRecetteMisePES = showListElementRecetteMisePES;
	}
	public Boolean getShowListElementRecetteMiseFinissage() {
		return showListElementRecetteMiseFinissage;
	}
	public void setShowListElementRecetteMiseFinissage(
			Boolean showListElementRecetteMiseFinissage) {
		this.showListElementRecetteMiseFinissage = showListElementRecetteMiseFinissage;
	}
	public Boolean getShowListElementRecetteMisePreparation() {
		return showListElementRecetteMisePreparation;
	}
	public void setShowListElementRecetteMisePreparation(
			Boolean showListElementRecetteMisePreparation) {
		this.showListElementRecetteMisePreparation = showListElementRecetteMisePreparation;
	}
	public String getMarcheDesignation() {
		return marcheDesignation;
	}
	public void setMarcheDesignation(String marcheDesignation) {
		this.marcheDesignation = marcheDesignation;
	}
	public Boolean getShowExtraData() {
		return showExtraData;
	}
	public void setShowExtraData(Boolean showExtraData) {
		this.showExtraData = showExtraData;
	}
	public Double getPoidsPES() {
		return poidsPES;
	}
	public void setPoidsPES(Double poidsPES) {
		if(poidsPES != null)
			this.showExtraData = true;
		this.poidsPES = poidsPES;
	}
	public Double getVolumePES() {
		return volumePES;
	}
	public void setVolumePES(Double volumePES) {
		if(volumePES != null)
			this.showExtraData = true;
		this.volumePES = volumePES;
	}
	public Double getPoidsCoton() {
		return poidsCoton;
	}
	public void setPoidsCoton(Double poidsCoton) {
		if(poidsCoton != null)
			this.showExtraData = true;
		this.poidsCoton = poidsCoton;
	}
	public Double getVolumeCoton() {
		return volumeCoton;
	}
	public void setVolumeCoton(Double volumeCoton) {
		if(volumeCoton != null)
			this.showExtraData = true;
		this.volumeCoton = volumeCoton;
	}
	public String getProduitReferenceInterne() {
		return produitReferenceInterne;
	}
	public void setProduitReferenceInterne(String produitReferenceInterne) {
		this.produitReferenceInterne = produitReferenceInterne;
	}
}
