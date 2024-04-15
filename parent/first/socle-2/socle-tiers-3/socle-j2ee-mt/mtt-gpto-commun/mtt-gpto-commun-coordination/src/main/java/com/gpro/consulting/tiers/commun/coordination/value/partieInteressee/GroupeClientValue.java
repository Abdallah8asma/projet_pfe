package com.gpro.consulting.tiers.commun.coordination.value.partieInteressee;


/**
 * The Class CategorieValue
 * 
 * @author $mohamed
 */
public class GroupeClientValue {

  /** The id. */
  private Long id;

  /** The designation. */
  private String designation;
  
	private Long famillePartieInteressee;
	
	
	private String codeEntreprise;
	
	
	

  public String getCodeEntreprise() {
		return codeEntreprise;
	}

	public void setCodeEntreprise(String codeEntreprise) {
		this.codeEntreprise = codeEntreprise;
	}

public Long getFamillePartieInteressee() {
		return famillePartieInteressee;
	}

	public void setFamillePartieInteressee(Long famillePartieInteressee) {
		this.famillePartieInteressee = famillePartieInteressee;
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

public GroupeClientValue(Long id) {
	super();
	this.id = id;
}

public GroupeClientValue() {
	super();
}



  
}
