package com.gpro.consulting.tiers.logistique.persistance.gs.entite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;


// TODO: Auto-generated Javadoc
/**
 * The Class BLAchatEntite.
 */
@Entity
@Table(name=IConstante.TABLE_BLACHAT)
public class BLAchatEntite implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5345002817558366037L;


	/** The id. */
	@Id
	@SequenceGenerator(name="GC_BLACHAT_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_BLACHAT)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GC_BLACHAT_ID_GENERATOR")
	private Long id;

	/** The reference. */
	@Column(name="reference")
	private String reference;

    
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return this.reference;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

}