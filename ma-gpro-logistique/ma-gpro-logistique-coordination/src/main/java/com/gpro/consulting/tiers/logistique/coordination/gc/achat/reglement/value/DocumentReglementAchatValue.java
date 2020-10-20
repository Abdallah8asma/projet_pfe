package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentProduitValue.
 * 
 * @author med
 */
public class DocumentReglementAchatValue {

	/** The id. */
	private Long id;

	/** The type document id. */
	private Long typeDocumentId;

	/** The produit id. */
	private Long reglementId;

	/** The uidDocument. */
	private String uidDocument;

	/** The path. */
	private String path;

	public Long getReglementId() {
		return reglementId;
	}

	public void setReglementId(Long reglementId) {
		this.reglementId = reglementId;
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
	 * Gets the type document id.
	 *
	 * @return the type document id
	 */
	public Long getTypeDocumentId() {
		return typeDocumentId;
	}

	/**
	 * Sets the type document id.
	 *
	 * @param typeDocumentId the new type document id
	 */
	public void setTypeDocumentId(Long typeDocumentId) {
		this.typeDocumentId = typeDocumentId;
	}

	/**
	 * Gets the produit id.
	 *
	 * @return the produit id
	 */

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

}