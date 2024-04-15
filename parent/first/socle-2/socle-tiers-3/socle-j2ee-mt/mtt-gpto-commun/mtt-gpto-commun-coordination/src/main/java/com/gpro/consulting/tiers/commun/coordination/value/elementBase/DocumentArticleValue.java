/*
 * 
 */
package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentValue.
 */
public class DocumentArticleValue {

	/** The id. */
	private Long id;

	/** The uidDocument. */
	private String uidDocument;

	/** The path. */
	private String path;

	/** The type document entite. */
	private Long typeDocumentEntite;

	/*** The Id Article */
	private Long vIdArticle;

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
	 * @param id
	 *            the new id
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
	 * @param path
	 *            the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public Long getTypeDocumentEntite() {
		return typeDocumentEntite;
	}

	public void setTypeDocumentEntite(Long typeDocumentEntite) {
		this.typeDocumentEntite = typeDocumentEntite;
	}

	/**
	 * @return the vIdArticle
	 */
	public Long getvIdArticle() {
		return vIdArticle;
	}

	/**
	 * @param vIdArticle
	 *            the vIdArticle to set
	 */
	public void setvIdArticle(Long vIdArticle) {
		this.vIdArticle = vIdArticle;
	}

}
