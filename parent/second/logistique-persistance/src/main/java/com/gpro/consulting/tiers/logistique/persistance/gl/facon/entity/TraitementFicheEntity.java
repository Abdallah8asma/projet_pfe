package com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Entity
@Table(name=IConstanteLogistique.TABLE_TRAITEMENT_FICHE)
public class TraitementFicheEntity implements Serializable {
	
	private static final long serialVersionUID = 669811434041093587L;
	
	@Id
	@SequenceGenerator(name="TRAITEMENT_FICHE_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_TRAITEMENT_FICHE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRAITEMENT_FICHE_ID_GENERATOR")
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "FICHE_ID")
	private FicheFaconEntity ficheFacon;
	
	@Column(name = "traitement_id")
	private Long traitementId;
	
	@Column(name = "bl_suppression")
	private boolean blSuppression;
	
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	@Column(name = "date_creation")
	private Calendar dateCreation;

	@Column(name = "date_modification")
	private Calendar dateModification;
	
	@Column(name = "version")
	private String version;
	
	@Column(name = "pu")
	private Double pu;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTraitementId() {
		return traitementId;
	}

	public void setTraitementId(Long traitementId) {
		this.traitementId = traitementId;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Double getPu() {
		return pu;
	}

	public void setPu(Double pu) {
		this.pu = pu;
	}

	public FicheFaconEntity getFicheFacon() {
		return ficheFacon;
	}

	public void setFicheFacon(FicheFaconEntity ficheFacon) {
		this.ficheFacon = ficheFacon;
	}

	@Override
	public String toString() {
		return "TraitementFicheEntity [id=" + id + ", ficheFacon=" + ficheFacon.toString() + ", traitementId=" + traitementId
				+ ", blSuppression=" + blSuppression + ", dateSuppression=" + dateSuppression + ", dateCreation="
				+ dateCreation + ", dateModification=" + dateModification + ", version=" + version + ", pu=" + pu + "]";
	}
}
