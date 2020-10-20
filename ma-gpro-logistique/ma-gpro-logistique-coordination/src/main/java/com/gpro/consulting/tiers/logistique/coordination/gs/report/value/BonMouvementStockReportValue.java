package com.gpro.consulting.tiers.logistique.coordination.gs.report.value;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 18/05/2016
 */
public class BonMouvementStockReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String type;
	
	private String numero;
	private Calendar date;
	private String responsable;
	
	private Long raisonMouvementId;
	private String raisonMouvementDesignation;
	
	private Double valeur;
	private String description;
	
	private Long ofId;
	private String ofNumero;
	
	private Long magasinId;
	private String magasinDesignation;
	
	private String produitReference;
	private String produitDesignation;
	private String clientAbreviation;
	
	//MvtStock
	private Long partieintId;
	
	private Calendar dateReservation;
	private String refReservation;
	
	private Set<MouvementStockReportValue> elementsList1 = new TreeSet<MouvementStockReportValue>();
	private Set<MouvementStockReportValue> elementsList2 = new TreeSet<MouvementStockReportValue>();
	private Set<MouvementStockReportValue> elementsList3 = new TreeSet<MouvementStockReportValue>();
	
	private Set<MouvementStockReportValue> elementsList1Sortie = new TreeSet<MouvementStockReportValue>();
	private Set<MouvementStockReportValue> elementsList2Sortie = new TreeSet<MouvementStockReportValue>();
	private Set<MouvementStockReportValue> elementsList3Sortie = new TreeSet<MouvementStockReportValue>();

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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Long getRaisonMouvementId() {
		return raisonMouvementId;
	}

	public void setRaisonMouvementId(Long raisonMouvementId) {
		this.raisonMouvementId = raisonMouvementId;
	}

	public String getRaisonMouvementDesignation() {
		return raisonMouvementDesignation;
	}

	public void setRaisonMouvementDesignation(String raisonMouvementDesignation) {
		this.raisonMouvementDesignation = raisonMouvementDesignation;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOfId() {
		return ofId;
	}

	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}

	public String getOfNumero() {
		return ofNumero;
	}

	public void setOfNumero(String ofNumero) {
		this.ofNumero = ofNumero;
	}

	public Long getMagasinId() {
		return magasinId;
	}

	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

	public String getMagasinDesignation() {
		return magasinDesignation;
	}

	public void setMagasinDesignation(String magasinDesignation) {
		this.magasinDesignation = magasinDesignation;
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

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public Set<MouvementStockReportValue> getElementsList1() {
		return elementsList1;
	}

	public void setElementsList1(Set<MouvementStockReportValue> elementsList1) {
		this.elementsList1 = elementsList1;
	}

	public Set<MouvementStockReportValue> getElementsList2() {
		return elementsList2;
	}

	public void setElementsList2(Set<MouvementStockReportValue> elementsList2) {
		this.elementsList2 = elementsList2;
	}

	public Set<MouvementStockReportValue> getElementsList3() {
		return elementsList3;
	}

	public void setElementsList3(Set<MouvementStockReportValue> elementsList3) {
		this.elementsList3 = elementsList3;
	}

	public Set<MouvementStockReportValue> getElementsList1Sortie() {
		return elementsList1Sortie;
	}

	public void setElementsList1Sortie(Set<MouvementStockReportValue> elementsList1Sortie) {
		this.elementsList1Sortie = elementsList1Sortie;
	}

	public Set<MouvementStockReportValue> getElementsList2Sortie() {
		return elementsList2Sortie;
	}

	public void setElementsList2Sortie(Set<MouvementStockReportValue> elementsList2Sortie) {
		this.elementsList2Sortie = elementsList2Sortie;
	}

	public Set<MouvementStockReportValue> getElementsList3Sortie() {
		return elementsList3Sortie;
	}

	public void setElementsList3Sortie(Set<MouvementStockReportValue> elementsList3Sortie) {
		this.elementsList3Sortie = elementsList3Sortie;
	}

	public Calendar getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Calendar dateReservation) {
		this.dateReservation = dateReservation;
	}

	public String getRefReservation() {
		return refReservation;
	}

	public void setRefReservation(String refReservation) {
		this.refReservation = refReservation;
	}

}
