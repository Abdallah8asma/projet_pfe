package com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.ElementRecetteMiseEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.FicheSuiveuseEntity;

/**
 * @author gatroussi
 *
 */

@Entity
@Table(name = IConstanteLogistique.TABLE_TRAITEMENTMISE)
public class TraitementMiseEntity implements Serializable {

	private static final long serialVersionUID = -4639352968273047906L;

	@Id
	@SequenceGenerator(name = "TRRAITEMENTMISE_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_TRAITEMENTMISE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRRAITEMENTMISE_ID_GENERATOR")
	private Long id;

	@Column(name = "gl_traitement_id")
	private Long traitementId;
	
	@Column(name = "gl_machine_id")
	private Long machineId;
	
	@Column(name = "observations")
	private String observations;
	
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
	
	@ManyToOne
	@JoinColumn(name = "GL_FICHE_ID")
	private FicheSuiveuseEntity ficheSuiveuse;
	
	@Column(name = "ordre")
	private Long order;
	
	@Column(name = "TYPE")
	private String type;

	@OneToMany(mappedBy = "traitementMise", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ElementRecetteMiseEntity> listElementRecetteMise;
	
	/** PU: Prix Unitaire */
	@Column(name = "PU")
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

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
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

	public FicheSuiveuseEntity getFicheSuiveuse() {
		return ficheSuiveuse;
	}

	public void setFicheSuiveuse(FicheSuiveuseEntity ficheSuiveuse) {
		this.ficheSuiveuse = ficheSuiveuse;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<ElementRecetteMiseEntity> getListElementRecetteMise() {
		return listElementRecetteMise;
	}

	public void setListElementRecetteMise(
			Set<ElementRecetteMiseEntity> listElementRecetteMise) {
		this.listElementRecetteMise = listElementRecetteMise;
	}

	public Double getPu() {
		return pu;
	}

	public void setPu(Double pu) {
		this.pu = pu;
	}

}
