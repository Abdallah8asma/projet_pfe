package com.gpro.consulting.tiers.logistique.persistance.gl.suivi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */

@Entity
@Table(name=IConstanteLogistique.TABLE_GL_ENGIN)
public class EnginEntity implements Serializable {
	
	private static final long serialVersionUID = 669811434041093587L;
	
	@Id
	@SequenceGenerator(name="ENGIN_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_ENGIN, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENGIN_ID_GENERATOR")
    private Long id;
	
	@Column(name = "IMMATRICULATION")
	private String immatriculation;
	
	@Column(name = "serie")
	private String serie;
	
	@Column(name = "MARQUE")
	private String marque;
	
	@Column(name = "TYPE")
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	
	
	

}
