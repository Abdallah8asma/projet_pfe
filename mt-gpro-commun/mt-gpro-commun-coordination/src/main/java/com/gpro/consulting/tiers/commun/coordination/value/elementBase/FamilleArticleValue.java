package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class FamilleArticleValue.
 * @author mohamed
 */
public class FamilleArticleValue {

	/** The id. */
	Long id;

	/** The designation. */
	private String designation;

	/** The id type article. */
	private Long idTypeArticle;
	
	/** List Sous Famille **/
	private Set<SousFamilleArticleValue> sousFamille;
	
	/** Designation Type **/
	private String designationType;
	
	
	/**
	 * 
	 * @return sousFamille
	 */
	
	public Set<SousFamilleArticleValue> getSousFamille() {
		return sousFamille;
	}
	
	/**
	 * 
	 * @param sousFamille
	 */

	public void setSousFamille(Set<SousFamilleArticleValue> sousFamille) {
		this.sousFamille = sousFamille;
	}

	/**
	 * 
	 * @return designationType 
	 */
	public String getDesignationType() {
		return designationType;
	}
   
	/**
	 * 
	 * @param designationType
	 */
	public void setDesignationType(String designationType) {
		this.designationType = designationType;
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
	 * Gets the id type article.
	 *
	 * @return the id type article
	 */
	public Long getIdTypeArticle() {
		return idTypeArticle;
	}

	/**
	 * Sets the id type article.
	 *
	 * @param idTypeArticle the new id type article
	 */
	public void setIdTypeArticle(Long idTypeArticle) {
		this.idTypeArticle = idTypeArticle;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
