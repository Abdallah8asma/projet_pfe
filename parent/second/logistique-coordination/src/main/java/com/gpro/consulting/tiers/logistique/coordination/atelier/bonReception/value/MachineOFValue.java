package com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value;

import java.util.Calendar;

/**
 * @author Ameni
 *
 */
public class MachineOFValue {

	/** the Id. */
	private Long id;

	/** The designation. */
	private String designation;

	/** Version. */
	private String version;

	private String numeroOF;

	private String produitReference;

	private String produitDesignation;

	private String status;

	private Calendar dateDebut;

	private Calendar dateFin;

	private Double qteOF;
	
	private Long qteProduite;
	
	
	
	

	public Double getQteOF() {
		return qteOF;
	}

	public void setQteOF(Double qteOF) {
		this.qteOF = qteOF;
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

	public Long getQteProduite() {
		return qteProduite;
	}

	public void setQteProduite(Long qteProduite) {
		this.qteProduite = qteProduite;
	}

	public String getNumeroOF() {
		return numeroOF;
	}

	public void setNumeroOF(String numeroOF) {
		this.numeroOF = numeroOF;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Calendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

}
