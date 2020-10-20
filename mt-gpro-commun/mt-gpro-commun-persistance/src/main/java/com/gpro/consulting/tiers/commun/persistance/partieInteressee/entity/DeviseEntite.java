package com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

@Entity
@Table(name = IConstante.TABLE_DEVISE)
public class DeviseEntite implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2703886914078520440L;
	
	@Id
	@SequenceGenerator(name="DEVISE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_DEVISE,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="DEVISE_ID_GENERATOR")
	private Long id;
	
	/** The designation. */
	@Column(name = "designation")
	private String designation;

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

	/************* Getters & Setters *****************/
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the blSuppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * @param dateSuppression the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (blSuppression ? 1231 : 1237);
		result = prime * result
				+ ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime
				* result
				+ ((dateModification == null) ? 0 : dateModification.hashCode());
		result = prime * result
				+ ((dateSuppression == null) ? 0 : dateSuppression.hashCode());
		result = prime * result
				+ ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviseEntite other = (DeviseEntite) obj;
		if (blSuppression != other.blSuppression)
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (dateModification == null) {
			if (other.dateModification != null)
				return false;
		} else if (!dateModification.equals(other.dateModification))
			return false;
		if (dateSuppression == null) {
			if (other.dateSuppression != null)
				return false;
		} else if (!dateSuppression.equals(other.dateSuppression))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviseEntite [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (designation != null) {
			builder.append("designation=");
			builder.append(designation);
			builder.append(", ");
		}
		builder.append("blSuppression=");
		builder.append(blSuppression);
		builder.append(", ");
		if (dateSuppression != null) {
			builder.append("dateSuppression=");
			builder.append(dateSuppression);
			builder.append(", ");
		}
		if (dateCreation != null) {
			builder.append("dateCreation=");
			builder.append(dateCreation);
			builder.append(", ");
		}
		if (dateModification != null) {
			builder.append("dateModification=");
			builder.append(dateModification);
		}
		builder.append("]");
		return builder.toString();
	}	
}
