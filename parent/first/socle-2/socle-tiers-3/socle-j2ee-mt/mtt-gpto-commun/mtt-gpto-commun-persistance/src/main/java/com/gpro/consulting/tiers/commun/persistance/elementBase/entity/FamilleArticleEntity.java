package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

// TODO: Auto-generated Javadoc
/**
 * The Class FamilleArticleEntity.
 * @author $mohamed
 */
@Entity
@Table(name=IConstante.TABLE_FAMILLE_ARTICLE)
public class FamilleArticleEntity implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3246401764111312034L;

	/** The id. */
	@Id
	@SequenceGenerator(name="FAMILLE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_FAMILLE_ARTICLE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="FAMILLE_ID_GENERATOR")
	private Long id;
	
	/** The designation. */
	@Column(name = "designation")
	private String designation;
	
	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** The date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;
	
	/** The id type article. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_typeart_id")
	private TypeArticleEntity typeArticle;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
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
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * Checks if is bl suppression.
	 *
	 * @return true, if is bl suppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression the new bl suppression
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	
	
	public TypeArticleEntity getTypeArticle() {
		return typeArticle;
	}

	public void setTypeArticle(TypeArticleEntity typeArticle) {
		this.typeArticle = typeArticle;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
}
