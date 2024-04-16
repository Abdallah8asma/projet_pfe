package com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity;

import java.io.Serializable;

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
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.TraitementMiseEntity;

/**
 * @author Wahid Gazzah
 * @since 24/03/2016
 *
 */

@Entity
@Table(name=IConstanteLogistique.TABLE_GL_ELEMENTRECETTEMISE)
public class ElementRecetteMiseEntity implements Serializable {

	private static final long serialVersionUID = 7349831890312560131L;

	@Id
	@SequenceGenerator(name="ELEMENTRECETTEMISE_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_ELEMENTRECETTEMISE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ELEMENTRECETTEMISE_ID_GENERATOR")
    private Long id;
	
	@Column(name = "EB_ARTICLE_ID")
	private Long articleId;
	
	@Column(name = "POURCENTAGE")
	private Double pourcentage;
	
	@Column(name = "POIDS")
	private Double poids;
	
	@Column(name = "OBSERVATIONS")
	private String observations;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "RAJOUT")
	private Double rajout;

	@Column(name = "TEMPS")
	private String temps;
	
	@Column(name = "TEMPERATURE")
	private Double temperature;
	
	@Column(name = "METHODE")
	private String methode;
	
	@Column(name = "ordre")
	private Long order;
	
	@Column(name = "ph")
	private String ph;
	
	@ManyToOne
	@JoinColumn(name = "GL_TRAITEMENTMISE_ID")
	private TraitementMiseEntity traitementMise;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Double getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getRajout() {
		return rajout;
	}

	public void setRajout(Double rajout) {
		this.rajout = rajout;
	}

	public String getTemps() {
		return temps;
	}

	public void setTemps(String temps) {
		this.temps = temps;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public TraitementMiseEntity getTraitementMise() {
		return traitementMise;
	}

	public void setTraitementMise(TraitementMiseEntity traitementMise) {
		this.traitementMise = traitementMise;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}
	
	
}
