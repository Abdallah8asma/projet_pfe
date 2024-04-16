package com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.entity;

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
@Table(name=IConstanteLogistique.TABLE_GL_BONSORTIEFINI)
public class BonSortieFiniEntity implements Serializable{
	private static final long serialVersionUID = -8340674658325851879L;
	
	@Id
	@SequenceGenerator(name="BONSORTIEFINI_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_BONSORTIEFINI,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BONSORTIEFINI_ID_GENERATOR")
    private Long id;
	
	@Column(name="REFERENCE")
	private String reference;
	
	@Column(name="DATE_SORTIE")
	private Calendar dateSortie;
	
	@Column(name="OBSERVATIONS")
	private String observation;
	
	@Column(name="NB_COLIS")
	private Integer nbColis; 
	
	@Column(name="METRAGE_TOTAL")
	private Double metrageTotal;
	
	@Column(name="TYPE")
	private String type; 
	
	@Column(name="PARTIEINT")
	private Long partieInt;
	
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
	
	@Column(name="POIDS_FINI")
	private Double poidsFini;
	
	@Column(name="POIDS_ECRU")
	private Double poidsEcru;
	
	@OneToMany(mappedBy = "bonSortie", cascade = CascadeType.ALL)
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
		return dateSortie;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPartieInt() {
		return partieInt;
	}

	public void setPartieInt(Long partieInt) {
		this.partieInt = partieInt;
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

	public Double getPoidsFini() {
		return poidsFini;
	}

	public void setPoidsFini(Double poidsFini) {
		this.poidsFini = poidsFini;
	}

	public Double getPoidsEcru() {
		return poidsEcru;
	}

	public void setPoidsEcru(Double poidsEcru) {
		this.poidsEcru = poidsEcru;
	}
	
	

}
