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
@Table(name=IConstanteLogistique.TABLE_GL_PERSONNEL)
public class PersonnelEntity implements Serializable {
	
	private static final long serialVersionUID = 669811434041093587L;
	
	@Id
	@SequenceGenerator(name="PERSONNEL_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_PERSONNEL, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONNEL_ID_GENERATOR")
    private Long id;
	
	@Column(name = "NOM")
	private String nom;
	
	@Column(name = "PRENOM")
	private String prenom;
	
	@Column(name = "MATRICULE")
	private String matricule;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	
	
	
	
	

}
