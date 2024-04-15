package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * The Class SeuilEntity.
 * @author $mohamed
 */
@Entity
 @Table(name=IConstante.TABLE_SEUIL)
public class SeuilArticleEntity implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2674296178209002032L;
	
	/** The id. */
	@Id
	@SequenceGenerator(name="SEUIL_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_SEUIL,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="SEUIL_ID_GENERATOR")
	private Long id;
	
	/** joincolumn Article. */
	@ManyToOne
	@JoinColumn(name = "eb_article_id" )
	private ArticleEntite article;
	
	/** The max seuil. */
	@Column(name="max")
	private Long maxSeuil;//double type
	
	/** The min seuil. */
	@Column(name="min")
	private Long minSeuil;
	
	/** The date debut. */
	@Column(name="date_debut")
	private Calendar dateDebut;
	
	/** The date fin. */
	@Column(name="date_fin")
	private Calendar dateFin;
	
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
	 * @return the article
	 */
	public ArticleEntite getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleEntite article) {
		this.article = article;
	}

	/**
	 * Gets the max seuil.
	 *
	 * @return the max seuil
	 */
	public Long getMaxSeuil() {
		return maxSeuil;
	}

	/**
	 * Sets the max seuil.
	 *
	 * @param maxSeuil the new max seuil
	 */
	public void setMaxSeuil(Long maxSeuil) {
		this.maxSeuil = maxSeuil;
	}

	/**
	 * Gets the min seuil.
	 *
	 * @return the min seuil
	 */
	public Long getMinSeuil() {
		return minSeuil;
	}

	/**
	 * Sets the min seuil.
	 *
	 * @param minSeuil the new min seuil
	 */
	public void setMinSeuil(Long minSeuil) {
		this.minSeuil = minSeuil;
	}

	/**
	 * Gets the date debut.
	 *
	 * @return the date debut
	 */
	public Calendar getDateDebut() {
		return dateDebut;
	}

	/**
	 * Sets the date debut.
	 *
	 * @param dateDebut the new date debut
	 */
	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Gets the date fin.
	 *
	 * @return the date fin
	 */
	public Calendar getDateFin() {
		return dateFin;
	}

	/**
	 * Sets the date fin.
	 *
	 * @param dateFin the new date fin
	 */
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
