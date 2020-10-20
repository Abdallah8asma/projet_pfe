package com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * Entité de mise
 * 
 * @author $Author: gatroussi $
 * @version $Revision: 0 $
 */

@Entity
@Table(name = IConstanteLogistique.TABLE_MISE)
public class MiseEntity implements Serializable {
	private static final long serialVersionUID = 7470319800368796325L;
	
	@Id
	@SequenceGenerator(name = "MISE_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_MISE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MISE_ID_GENERATOR")
	private Long id;
	@Column(name = "reference")
	private String reference;
	@Column(name = "metrage")
	private Double metrage;
	@Column(name = "poids")
	private Double poids;
	@Column(name = "ref_bonreception")
	private String refBonreception;
	@Column(name = "date_introduction")
	private Calendar dateIntroduction;
	@Column(name = "observations")
	private String observations;
	@Column(name = "code_barre")
	private String codeBarre;
	@Column(name = "nombre_rouleau")
	private Long nombreRouleau;
	@Column(name = "eb_produit_id")
	private Long produitId;
	@Column(name = "pi_partieint_id")
	private Long partieintId;
	@Column(name = "bl_suppression")
	private Boolean blSuppression;
	@Column(name = "date_suppression")
	private Calendar dateSuppression;
	@Column(name = "date_creation")
	private Calendar dateCreation;
	@Column(name = "date_modification")
	private Calendar dateModification;
	@Column(name = "version")
	private String version;
	
	//added on 20/01/2016, by Wahid Gazzah
	@Column(name = "FINI")
	private Boolean fini;
	
	/* Les traitements. 
	@OneToMany(mappedBy = "mise", cascade = CascadeType.ALL)
	private Set<TraitementMiseEntity> listeTraitements;
	 */
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the metrage
	 */
	public Double getMetrage() {
		return metrage;
	}

	/**
	 * @param metrage
	 *            the metrage to set
	 */
	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}

	/**
	 * @return the poids
	 */
	public Double getPoids() {
		return poids;
	}

	/**
	 * @param poids
	 *            the poids to set
	 */
	public void setPoids(Double poids) {
		this.poids = poids;
	}

	/**
	 * @return the refBonreception
	 */
	public String getRefBonreception() {
		return refBonreception;
	}

	/**
	 * @param refBonreception
	 *            the refBonreception to set
	 */
	public void setRefBonreception(String refBonreception) {
		this.refBonreception = refBonreception;
	}

	/**
	 * @return the dateIntroduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * @param dateIntroduction
	 *            the dateIntroduction to set
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations
	 *            the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return the codeBarre
	 */
	public String getCodeBarre() {
		return codeBarre;
	}

	/**
	 * @param codeBarre
	 *            the codeBarre to set
	 */
	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	/**
	 * @return the nombreRouleau
	 */
	public Long getNombreRouleau() {
		return nombreRouleau;
	}

	/**
	 * @param nombreRouleau
	 *            the nombreRouleau to set
	 */
	public void setNombreRouleau(Long nombreRouleau) {
		this.nombreRouleau = nombreRouleau;
	}

	/**
	 * @return the produitId
	 */
	public Long getProduitId() {
		return produitId;
	}

	/**
	 * @param produitId
	 *            the produitId to set
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	/**
	 * @return the partieintId
	 */
	public Long getPartieintId() {
		return partieintId;
	}

	/**
	 * @param partieintId
	 *            the partieintId to set
	 */
	public void setPartieintId(Long partieintId) {
		this.partieintId = partieintId;
	}

	/**
	 * @return the blSuppression
	 */
	public Boolean getBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression
	 *            the blSuppression to set
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
	 * @param dateSuppression
	 *            the dateSuppression to set
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
	 * @param dateCreation
	 *            the dateCreation to set
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
	 * @param dateModification
	 *            the dateModification to set
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
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	public Boolean isFini() {
		return fini;
	}

	public void setFini(Boolean fini) {
		this.fini = fini;
	}

	public Boolean getFini() {
		return fini;
	}
	

}
