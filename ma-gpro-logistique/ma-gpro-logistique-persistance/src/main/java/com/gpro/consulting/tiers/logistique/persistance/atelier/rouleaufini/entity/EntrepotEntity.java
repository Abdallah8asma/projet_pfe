package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
@Entity
@Table(name=IConstanteLogistique.TABLE_GL_ENTREPOT)
public class EntrepotEntity implements Serializable{
	private static final long serialVersionUID = 2048039375975933274L;

	@Id
	@SequenceGenerator(name="ENTREPOT_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_ENTREPOT,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENTREPOT_ID_GENERATOR")
    private Long id;
	
	@Column(name="DESIGNATION")
	private String designation;
	
	@Column(name="PI_PARTINT_ID")
	private Long partintId;
	
	@Column(name="BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name="DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name="DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Calendar dateModification;
	
	@Column(name="VERSION")
	private String version;
	
	/** many-to-one association to {@link RouleauFiniEntity}. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="entrepot", cascade = CascadeType.MERGE)
	private Set<RouleauFiniEntity> rouleauFiniEntity;

	
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
	 * @return the partintId
	 */
	public Long getPartintId() {
		return partintId;
	}

	/**
	 * @param partintId the pi_partit_id to set
	 */
	public void setPartintId(Long partintId) {
		this.partintId = partintId;
	}

	/**
	 * @return the blSuppression
	 */
	public Boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * @param dateSuppression the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
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

	/**
	 * @return the rouleauFiniEntity
	 */
	public Set<RouleauFiniEntity> getRouleauFiniEntity() {
		return rouleauFiniEntity;
	}

	/**
	 * @param rouleauFiniEntity the rouleauFiniEntity to set
	 */
	public void setRouleauFiniEntity(Set<RouleauFiniEntity> rouleauFiniEntity) {
		this.rouleauFiniEntity = rouleauFiniEntity;
	}
	
}
