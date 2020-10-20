/**
 * 
 */
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
 * The Class MouvementCaisseEntity.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */
@Entity
@Table(name = IConstanteLogistique.TABLE_MOUVEMENT_CAISSE)

public class MouvementCaisseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -75280391725240939L;

	/** The id. */
	@Id
	@SequenceGenerator(name = "MVT_CAISSE_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_GL_MVT_CAISSE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MVT_CAISSE_ID_GENERATOR")
	private Long id;

	/** the date mouvenet. */
	@Column(name = "date")
	private Calendar date;

	/** the destinataire. */
	@Column(name = "destinataire")
	private String destinataire;

	/** the caisse id. */
	@Column(name = "caisse_id")
	private Long caisseId;

	/** The type mouvement. */
	@Column(name = "type_mouvement")
	private String typeMouvement;

	/** The montant mouvement. */
	@Column(name = "montant_mouvement")
	private Double montantMouvement;

	/**
	 * @return the montantMouvement
	 */
	public Double getMontantMouvement() {
		return montantMouvement;
	}

	/**
	 * @param montantMouvement
	 *            the montantMouvement to set
	 */
	public void setMontantMouvement(Double montantMouvement) {
		this.montantMouvement = montantMouvement;
	}

	/**
	 * Gets the type mouvement.
	 *
	 * @return the typeMouvement
	 */
	public String getTypeMouvement() {
		return typeMouvement;
	}

	/**
	 * Sets the type mouvement.
	 *
	 * @param typeMouvement
	 *            the typeMouvement to set
	 */
	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
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
	 * Gets the destinataire.
	 *
	 * @return the destinataire
	 */
	public String getDestinataire() {
		return destinataire;
	}

	/**
	 * Sets the destinataire.
	 *
	 * @param destinataire
	 *            the destinataire to set
	 */
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	/**
	 * Gets the caisse id.
	 *
	 * @return the caisse_id
	 */
	public Long getCaisseId() {
		return caisseId;
	}

	/**
	 * Sets the caisse id.
	 *
	 * @param caisseId
	 *            the new caisse id
	 */
	public void setCaisseId(Long caisseId) {
		this.caisseId = caisseId;
	}

}
