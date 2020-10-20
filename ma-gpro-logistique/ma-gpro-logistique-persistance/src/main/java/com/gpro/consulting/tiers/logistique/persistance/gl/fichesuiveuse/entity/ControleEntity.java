package com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity;

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
 * @author Wahid Gazzah
 * @since 24/03/2016
 *
 */

@Entity
@Table(name=IConstanteLogistique.TABLE_GL_CONTROLE)
public class ControleEntity implements Serializable {

	private static final long serialVersionUID = -2639578721870148938L;
	
	@Id
	@SequenceGenerator(name="CONTROLE_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_CONTROLE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTROLE_ID_GENERATOR")
    private Long id;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
