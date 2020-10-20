package com.gpro.consulting.tiers.logistique.persistance.caisse.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

// TODO: Auto-generated Javadoc
/**
 * The Class CaisseEntity.
 *
 * @author Hamdi Etteieb
 * @since 16/09/2018
 */
@Entity
@Table(name = IConstanteLogistique.TABLE_CAISSE)
public class CaisseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1654117725683378842L;

	/** The id. */
	@Id
	@SequenceGenerator(name = "CAISSE_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_GL_CAISSE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAISSE_ID_GENERATOR")
	private Long id;

	/** The date. */
	@Column(name = "date")
	private Calendar date;

	/** The reference. */
	@Column(name = "reference")
	private String reference;

	/** The date cloture. */
	@Column(name = "date_cloture")
	private Calendar dateCloture;

	/** The user id. */
	@Column(name = "user_id")
	private Long userId;

	/** The magasin id. */
	@Column(name = "magasin_id")
	private Long magasinId;

	/** The montant especes. */
	@Column(name = "montant_especes")
	private Double montantEspeces;

	/** The Montant cheque. */
	@Column(name = "montant_cheque")
	private Double MontantCheque;

	/** The cloture. */
	@Column(name = "cloture")
	private Boolean cloture;

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
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the date cloture.
	 *
	 * @return the dateCloture
	 */
	public Calendar getDateCloture() {
		return dateCloture;
	}

	/**
	 * Sets the date cloture.
	 *
	 * @param dateCloture
	 *            the dateCloture to set
	 */
	public void setDateCloture(Calendar dateCloture) {
		this.dateCloture = dateCloture;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user_id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the magasin id.
	 *
	 * @return the magasin_id
	 */
	public Long getMagasinId() {
		return magasinId;
	}

	/**
	 * Sets the magasin id.
	 *
	 * @param magasinId
	 *            the new magasin id
	 */
	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

	/**
	 * Gets the montant especes.
	 *
	 * @return the montantEspeces
	 */
	public Double getMontantEspeces() {
		return montantEspeces;
	}

	/**
	 * Sets the montant especes.
	 *
	 * @param montantEspeces
	 *            the montantEspeces to set
	 */
	public void setMontantEspeces(Double montantEspeces) {
		this.montantEspeces = montantEspeces;
	}

	/**
	 * Gets the montant cheque.
	 *
	 * @return the montantCheque
	 */
	public Double getMontantCheque() {
		return MontantCheque;
	}

	/**
	 * Sets the montant cheque.
	 *
	 * @param montantCheque
	 *            the montantCheque to set
	 */
	public void setMontantCheque(Double montantCheque) {
		MontantCheque = montantCheque;
	}

	/**
	 * Checks if is cloture.
	 *
	 * @return the cloture
	 */
	public Boolean isCloture() {
		return cloture;
	}

	/**
	 * Sets the cloture.
	 *
	 * @param cloture
	 *            the cloture to set
	 */
	public void setCloture(Boolean cloture) {
		this.cloture = cloture;
	}

}
