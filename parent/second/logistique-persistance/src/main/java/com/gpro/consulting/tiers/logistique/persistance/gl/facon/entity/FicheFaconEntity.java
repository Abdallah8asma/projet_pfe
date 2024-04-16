package com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Entity
@Table(name=IConstanteLogistique.TABLE_FICHE_FACON)
public class FicheFaconEntity implements Serializable {
	
	private static final long serialVersionUID = 669811434041093587L;
	
	@Id
	@SequenceGenerator(name="FICHE_FACON_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_FICHE_FACON, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FICHE_FACON_ID_GENERATOR")
    private Long id;
	
	@Column(name = "PRODUIT_ID")
	private Long produitId;
	
	@Column(name = "PARTINT_ID")
	private Long partieIntId;
	
	@Column(name = "REF_BONRECEPTION")
	private String refBonReception;
	
	@Column(name = "METHODE_TEINTURE")
	private String methodeTeinture;
	
	@Column(name = "OBSERVATIONS")
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
	
	@Column(name = "poids_ecru")
	private Double poidsEcru;
	
	@Column(name = "poids_fini")
	private Double poidsFini;
	
	@OneToMany(mappedBy = "ficheFacon", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<TraitementFicheEntity> listeTraitementsFiche;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public String getRefBonReception() {
		return refBonReception;
	}

	public void setRefBonReception(String refBonReception) {
		this.refBonReception = refBonReception;
	}

	public String getMethodeTeinture() {
		return methodeTeinture;
	}

	public void setMethodeTeinture(String methodeTeinture) {
		this.methodeTeinture = methodeTeinture;
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
	
	public Double getPoidsEcru() {
		return poidsEcru;
	}

	public void setPoidsEcru(Double poidsEcru) {
		this.poidsEcru = poidsEcru;
	}

	public Double getPoidsFini() {
		return poidsFini;
	}

	public void setPoidsFini(Double poidsFini) {
		this.poidsFini = poidsFini;
	}

	public Set<TraitementFicheEntity> getListeTraitementsFiche() {
		return listeTraitementsFiche;
	}

	public void setListeTraitementsFiche(Set<TraitementFicheEntity> listeTraitementsFiche) {
		this.listeTraitementsFiche = listeTraitementsFiche;
	}

	@Override
	public String toString() {
		return "FicheFaconEntity [id=" + id + ", produitId=" + produitId + ", partieIntId=" + partieIntId
				+ ", refBonReception=" + refBonReception + ", methodeTeinture=" + methodeTeinture + ", poidsEcru="
				+ poidsEcru + ", poidsFini=" + poidsFini + ", version=" + version + ", listeTraitementsFiche="
				+ listeTraitementsFiche + "]";
	}

	
}
