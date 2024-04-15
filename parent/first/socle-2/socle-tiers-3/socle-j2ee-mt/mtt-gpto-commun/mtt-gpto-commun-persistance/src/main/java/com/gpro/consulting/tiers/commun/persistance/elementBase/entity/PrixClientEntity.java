package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;

//TODO: Auto-generated Javadoc
/**
* The Class PrixClientEntity.
* @author $El Araichi Oussama
*/
@Entity
@Table(name=IConstante.TABLE_EB_PRIX_CLIENT)


public class PrixClientEntity implements Serializable  {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3246401764111312034L;
	
	/** The id. */
	@Id
	@SequenceGenerator(name="EB_PRIXCLIENT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_PRIXCLIENT)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="EB_PRIXCLIENT_ID_GENERATOR")
	private Long id;
	
	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blsuppression;
	
	/** The prixvente. */
	@Column(name = "prix_vente")
	private Double prixvente;
	
	/** The prixvente. */
	@Column(name = "date_deb")
	private Calendar datedeb;
	
	/** the datesuppression.*/
	@Column(name = "date_suppression")
	private Date datesuppresion;
	
	/** the datemodification.*/
	@Column(name = "date_modification")
	private Calendar datemodification;
	
	/**the date creation*/
	@Column(name = "date_creation")
	private Calendar datecreation;

	@Column(name = "eb_produit_id")
	private Long ebproduitid;
		
	
	@Column(name = "pi_pi_id")
	private Long partieinteresse;
	
	
	@Column(name = "pi_famillepi_id")
	private Long famillePartieInteressee;
	
	
	/** The remise. */
	@Column(name = "remise")
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

	public boolean isBlsuppression() {
		return blsuppression;
	}

	public void setBlsuppression(boolean blsuppression) {
		this.blsuppression = blsuppression;
	}

	public Double isPrixvente() {
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

	



	public Double getPrixvente() {
		return prixvente;
	}

	public Long getEbproduitid() {
		return ebproduitid;
	}

	public void setEbproduitid(Long ebproduitid) {
		this.ebproduitid = ebproduitid;
	}

	public Long getPartieinteresse() {
		return partieinteresse;
	}

	public void setPartieinteresse(Long partieinteresse) {
		this.partieinteresse = partieinteresse;
	}
	
	

	
	
	
	
	
	

}
