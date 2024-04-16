package com.gpro.consulting.tiers.logistique.coordination.gc.reception.value;

/**
 * The Class DocumentValue.
 */
public class DocumentValue {

  /** The id. */
  private Long id;

  /** The path. */
  private String path;

  /** The uidDocument. */
  private String uidDocument;

  /** Id de la partie intéressée */
  private Long vIdPartieInteresse;

  /** The type document entite. */
  private Long typeDocument;

  /** UUID */
  private String vUUIDDocument;

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
   *          the new id
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
   * @return the vIdPartieInteresse
   */
  public Long getvIdPartieInteresse() {
    return vIdPartieInteresse;
  }

  /**
   * @param vIdPartieInteresse
   *          the vIdPartieInteresse to set
   */
  public void setvIdPartieInteresse(Long vIdPartieInteresse) {
    this.vIdPartieInteresse = vIdPartieInteresse;
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
   *          the new path
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * @return the typeDocument
   */
  public Long getTypeDocument() {
    return typeDocument;
  }

  /**
   * @param typeDocument
   *          the typeDocument to set
   */
  public void setTypeDocument(Long typeDocument) {
    this.typeDocument = typeDocument;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vUUIDDocument</code>.
   * 
   * @return String L'attribut vUUIDDocument à lire.
   */
  public String getvUUIDDocument() {
    return vUUIDDocument;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vUUIDDocument</code>.
   *
   * @param vUUIDDocument
   *          L'attribut vUUIDDocument à modifier.
   */
  public void setvUUIDDocument(String vUUIDDocument) {
    this.vUUIDDocument = vUUIDDocument;
  }

}
