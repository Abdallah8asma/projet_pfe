package   com.gpro.consulting.tiers.commun.coordination.value.elementBase;

// TODO: Auto-generated Javadoc
/**
 * The Class OptionArticleValue.
 * @author samer 
 */
public class OperationProduitValue {

	/** The id. */
	private Long id;

	/** The designation. */
	private String designation;
	
	
	private String description;
	
	private String typesIds;
	
	
	
	private Double cout;

	private Double temps;

	private Long ordre;
	
	
	
	

	public Double getCout() {
		return cout;
	}

	public void setCout(Double cout) {
		this.cout = cout;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public String getTypesIds() {
		return typesIds;
	}

	public void setTypesIds(String typesIds) {
		this.typesIds = typesIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
