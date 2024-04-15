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
 * The Class DocumentEntite.
 * @author $Ghazi: $
 */
@Entity
@Table(name = IConstante.TABLE_DOCUMENT_ARTICLE)
public class DocumentArticleEntite implements Serializable{
	private static final long serialVersionUID = 8263323760620903599L;

	/** Identifiant technique. */
	  /** L'id de la table. */
	@Id
	@SequenceGenerator(name ="DOCUMENT_ARTICLE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_DOCUMENT_ARTICLE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="DOCUMENT_ARTICLE_ID_GENERATOR")
	private Long id;

	/** joincolumn partie interesse. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eb_article_id" )
	private ArticleEntite article;
	
	/** The path. */
	@Column(name = "path")
	private String path;

	/** The path. */
	@Column(name = "uid_document")
	private String uidDocument;

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

	/** The type document entite. */
	@Column(name = "com_typedoc_id")
	private Long typeDocumentEntite;
	
	
	/**
	 * Gets the type document entite.
	 *
	 * @return the type document entite
	 */
	public Long getTypeDocumentEntite() {
		return typeDocumentEntite;
	}

	/**
	 * Sets the type document entite.
	 *
	 * @param typeDocumentEntite the new type document entite
	 */
	public void setTypeDocumentEntite(Long typeDocumentEntite) {
		this.typeDocumentEntite = typeDocumentEntite;
	}

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
	 * @return the uidDocument
	 */
	public String getUidDocument() {
		return uidDocument;
	}

	/**
	 * @param uidDocument the uidDocument to set
	 */
	public void setUidDocument(String uidDocument) {
		this.uidDocument = uidDocument;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
