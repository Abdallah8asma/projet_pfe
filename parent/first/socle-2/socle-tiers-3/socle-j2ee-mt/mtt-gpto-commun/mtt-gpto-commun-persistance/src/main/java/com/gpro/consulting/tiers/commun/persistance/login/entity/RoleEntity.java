package com.gpro.consulting.tiers.commun.persistance.login.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * @author Nour
 *
 */

import com.gpro.consulting.tiers.commun.coordination.IConstante;

@Entity
@Table(name=IConstante.TABLE_EB_ROLE)
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 8608471365235241145L;
	@Id
	@SequenceGenerator(name="ROLE_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_EB_ROLE , allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_ID_GENERATOR")
	private Long id ;
	
	@Column(name = "code")
	 private String code ; 
	
	@Column(name = "designation")
	 private String designation ; 
	
	@Column(name = "module")
	 private String module ; 
	
	@Column(name ="bl_suppression")
	private Boolean blSuppression;
	
	@Column(name ="date_suppression")
	private Calendar dateSuppression;
	
	@Column(name ="date_creation")
	private Calendar dateCreation;

	@Column(name ="date_modification")
	private Calendar dateModification;

	@Column(name ="version")
	private String version;
	
	@Column(name ="description")
	private String description;
	
	


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	

	
	
	

}
