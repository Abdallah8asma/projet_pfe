/*
 * 
 */
package com.gpro.consulting.tiers.commun.coordination.value.partieInteressee;


// TODO: Auto-generated Javadoc
/**
 * The Class TypeDocumentValue.
 */
public class TypeDocumentValue {

  /** The id. */
  private Long id;

  /** The designation. */
  private String designation;
  
  private String module;

  public String getModule() {
	return module;
}

public void setModule(String module) {
	this.module = module;
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
   * @param id
   *          the new id
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
   * @param designation
   *          the new designation
   */
  public void setDesignation(String designation) {
    this.designation = designation;
  }

}
