package com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */
@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_TYPEREGLEMENT)
public class TypeReglementEntity implements Serializable{

	private static final long serialVersionUID = -5335660141724007171L;

	@Id
	@SequenceGenerator(name="TYPEREGLEMENT_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_TYPEREGLEMENT, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TYPEREGLEMENT_ID_GENERATOR")
    private Long id;
	
	@Column(name="DESIGNATION")
	private String designation;
	
	
	@Column(name="prefixe")
	private String prefixe;

	@Column(name="aterme")
	private Boolean aTerme;
	
	
	@Column(name="regle")
	private Boolean regle;
	
	
	
	
	public Boolean getRegle() {
		return regle;
	}

	public void setRegle(Boolean regle) {
		this.regle = regle;
	}

	public String getPrefixe() {
		return prefixe;
	}

	public void setPrefixe(String prefixe) {
		this.prefixe = prefixe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public Boolean getaTerme() {
		return aTerme;
	}

	public void setaTerme(Boolean aTerme) {
		this.aTerme = aTerme;
	}
	
}
