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

/**
 * @author Wahid Gazzah
 * @since 24/03/2016
 *
 */

@Entity
@Table(name=IConstanteLogistique.TABLE_GL_ELEMENTCONTROLE)
public class ElementControleEntity implements Serializable {

	private static final long serialVersionUID = -2122578559895742171L;

	@Id
	@SequenceGenerator(name="ELEMENTCONTROLE_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_ELEMENTCONTROLE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ELEMENTCONTROLE_ID_GENERATOR")
    private Long id;
	
	@Column(name = "VALEUR")
	private Double valeur;
	
	@Column(name = "VALEUR_CORRIGE")
	private Double valeurCorrige;
	
	@Column(name = "METHODE")
	private String methode;
	
	@Column(name = "OBSERVATIONS")
	private String observations;
	
	@Column(name = "VALEURS_THEORIQUE")
	private String valeurTheorique;
	
	@Column(name = "CONTROLE_ID")
	private Long controleId;
	
	@ManyToOne
	@JoinColumn(name = "GL_FICHE_ID")
	private FicheSuiveuseEntity ficheSuiveuse;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "TEMPS")
	private String temps;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public Double getValeurCorrige() {
		return valeurCorrige;
	}

	public void setValeurCorrige(Double valeurCorrige) {
		this.valeurCorrige = valeurCorrige;
	}

	public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getValeurTheorique() {
		return valeurTheorique;
	}

	public void setValeurTheorique(String valeurTheorique) {
		this.valeurTheorique = valeurTheorique;
	}

	public Long getControleId() {
		return controleId;
	}

	public void setControleId(Long controleId) {
		this.controleId = controleId;
	}

	public FicheSuiveuseEntity getFicheSuiveuse() {
		return ficheSuiveuse;
	}

	public void setFicheSuiveuse(FicheSuiveuseEntity ficheSuiveuse) {
		this.ficheSuiveuse = ficheSuiveuse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemps() {
		return temps;
	}

	public void setTemps(String temps) {
		this.temps = temps;
	}
	
	
	
}
