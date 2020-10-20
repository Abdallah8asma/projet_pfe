package com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.entity;

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
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.RouleauFiniEntity;

/**
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Entity
@Table(name=IConstanteLogistique.TABLE_GL_BONINVENTAIREFINI)
public class BonInventaireFiniEntity implements Serializable{
	private static final long serialVersionUID = -8340674658325851879L;
	
	@Id
	@SequenceGenerator(name="BONINVENTAIREFINI_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_BONSORTIEFINI,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BONINVENTAIREFINI_ID_GENERATOR")
    private Long id;
	
	@Column(name="REFERENCE")
	private String reference;
	
	@Column(name="DATE_ENTREE")
	private Calendar dateEntree;
	
	@Column(name="OBSERVATIONS")
	private String observation;
	
	@Column(name="emplacement")
	private String emplacement;
	
	@Column(name="NB_COLIS")
	private Integer nbColis; 
	
	@Column(name="METRAGE_TOTAL")
	private Double metrageTotal;
	
	@Column(name="POIDS_TOTAL")
	private Double poidsTotal;
	
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
	
	@OneToMany(mappedBy = "bonInventaire", cascade = CascadeType.ALL)
	private Set<RouleauFiniEntity> listeRouleauFini;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDateSortie() {
		return dateEntree;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateEntree = dateSortie;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Integer getNbColis() {
		return nbColis;
	}

	public void setNbColis(Integer nbColis) {
		this.nbColis = nbColis;
	}

	public Double getMetrageTotal() {
		return metrageTotal;
	}

	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}


	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
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

	public Set<RouleauFiniEntity> getListeRouleauFini() {
		return listeRouleauFini;
	}

	public void setListeRouleauFini(Set<RouleauFiniEntity> listeRouleauFini) {
		this.listeRouleauFini = listeRouleauFini;
	}

	/**
	 * @return the dateEntree
	 */
	public Calendar getDateEntree() {
		return dateEntree;
	}

	/**
	 * @param dateEntree the dateEntree to set
	 */
	public void setDateEntree(Calendar dateEntree) {
		this.dateEntree = dateEntree;
	}

	/**
	 * @return the emplacement
	 */
	public String getEmplacement() {
		return emplacement;
	}

	/**
	 * @param emplacement the emplacement to set
	 */
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	/**
	 * @return the poidsTotal
	 */
	public Double getPoidsTotal() {
		return poidsTotal;
	}

	/**
	 * @param poidsTotal the poidsTotal to set
	 */
	public void setPoidsTotal(Double poidsTotal) {
		this.poidsTotal = poidsTotal;
	}

}
