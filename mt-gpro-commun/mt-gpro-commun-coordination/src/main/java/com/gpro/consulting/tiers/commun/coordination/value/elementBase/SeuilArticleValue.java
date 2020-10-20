package  com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class SeuilValue.
 * @author mohamed
 */
public class SeuilArticleValue  {
	
	/** The id. */
	private Long id;
	
	/** The id article. */
	private Long idArticle;
	
	/** The max seuil. */
	private Long maxSeuil;
	
	/** The min seuil. */
	private Long minSeuil;
	
	/** The date debut. */
	private Calendar dateDebut;
	
	/** The date fin. */
	private Calendar dateFin;

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
	 * Gets the id article.
	 *
	 * @return the id article
	 */
	public Long getIdArticle() {
		return idArticle;
	}

	/**
	 * Sets the id article.
	 *
	 * @param idArticle the new id article
	 */
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
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
