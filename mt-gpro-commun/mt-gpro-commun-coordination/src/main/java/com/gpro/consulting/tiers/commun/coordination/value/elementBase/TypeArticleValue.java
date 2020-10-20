package  com.gpro.consulting.tiers.commun.coordination.value.elementBase;

// TODO: Auto-generated Javadoc
/**
 * The Class TypeArticleValue.
 * @author mohamed
 */
public class TypeArticleValue   {
	
	/** The id. */
	private Long id;
	
	/** The designation. */
	private String designation;
	
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

	
@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeArticleValue [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (designation != null) {
			builder.append("designation=");
			builder.append(designation);
		}
		builder.append("]");
		return builder.toString();
	}

@Override
public boolean equals(Object obj) {
return super.equals(obj);
}
}
