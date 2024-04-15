package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;
import java.util.Date;

public class PrixClientValue{
	
	/*id*/
	private Long id;
	
	/*prix vente*/
	private Double prixvente;
	
	/*datedeb*/
	private Calendar datedeb;
	
	/*datesupp*/
	private Date datesuppresion;
	
	/*datemodif*/
	private Calendar  datemodification;
	
	private Calendar  datecreation;
	
	private Boolean blsuppression;
	
	private Long ebproduitid;
	
	private Long idpi;
	
	
	private Long famillePartieInteressee;
	
	
	private Double remise;
	
	
	

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Long getFamillePartieInteressee() {
		return famillePartieInteressee;
	}

	public void setFamillePartieInteressee(Long famillePartieInteressee) {
		this.famillePartieInteressee = famillePartieInteressee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrixvente() {
		return prixvente;
	}

	public void setPrixvente(Double prixvente) {
		this.prixvente = prixvente;
	}

	public Calendar getDatedeb() {
		return datedeb;
	}

	public void setDatedeb(Calendar datedeb) {
		this.datedeb = datedeb;
	}

	public Date getDatesuppresion() {
		return datesuppresion;
	}

	public void setDatesuppresion(Date datesuppresion) {
		this.datesuppresion = datesuppresion;
	}

	public Calendar getDatemodification() {
		return datemodification;
	}

	public void setDatemodification(Calendar datemodification) {
		this.datemodification = datemodification;
	}

	public Calendar getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Calendar datecreation) {
		this.datecreation = datecreation;
	}

	public Boolean getBlsuppression() {
		return blsuppression;
	}

	public void setBlsuppression(Boolean blsuppression) {
		this.blsuppression = blsuppression;
	}

	public Long getEbproduit() {
		return ebproduitid;
	}

	public void setEbproduit(Long ebproduit) {
		this.ebproduitid = ebproduit;
	}

	public Long getIdpi() {
		return idpi;
	}

	public void setIdpi(Long idpi) {
		this.idpi = idpi;
	}
	
	

}
