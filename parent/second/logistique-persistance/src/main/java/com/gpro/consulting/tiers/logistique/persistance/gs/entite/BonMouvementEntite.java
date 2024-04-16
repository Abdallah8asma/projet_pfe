package com.gpro.consulting.tiers.logistique.persistance.gs.entite;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;

// TODO: Auto-generated Javadoc
/**
 * The Class BonMouvementEntite.
 * 
 * @author mohamed
 */
@Entity
@Table(name = IConstante.TABLE_BON_MOUVEMENT)
public class BonMouvementEntite implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3188463288940118609L;

	/** The id. */
	@Id
	@SequenceGenerator(name = "GS_BONMOUVEMENT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_BON_MOUVEMENT)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GS_BONMOUVEMENT_ID_GENERATOR")
	private Long id;

	/** The date. */
	@Column(name = "date")
	private Calendar date;

	/** The description. */
	@Column(name = "description")
	private String description;

	/** The numero. */
	@Column(name = "numero")
	private String numero;

	/** The responsable. */
	@Column(name = "responsable")
	private String responsable;

	/** The type. */
	@Column(name = "type")
	private String type;

	/** The valeur. */
	@Column(name = "valeur")
	private Double valeur;
	/** The valeur. */
	@Column(name = "etat")
	private String etat;

	/** The raisonMouvementId. */
	@Column(name = "gs_raisonmouvement_id")
	private Long raisonMouvementId;

	// bi-directional many-to-one association to GsMouvement
	/** The mouvements. */
	@OneToMany(mappedBy = "bonMouvement", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MouvementEntite> mouvements;

	/** The gc blachat id. */
	@Column(name = "pi_partieint_id")
	private Long partieInteresseeId;

	@ManyToOne
	@JoinColumn(name = "gc_blachat_id")
	private BLAchatEntite blachat;

	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private Boolean blSuppression;

	/** The date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	@Column(name = "of_id")
	private Long ofId;

	// Added on 10/11/2016, by Zeineb Medimagh
	// Rempli pour le bon de type sortie
	// Qui relie avec le bonReservation
	@Column(name = "numero_bon_reservation")
	private String numBRSortie;

	/**
	 * @author Hajer Amri
	 * @since 01/02/2017
	 */

	/** The origineFSuiveuse. */
	@Column(name = "origine_f_suiveuse")
	private boolean origineFSuiveuse;
	@Column(name="reference_bon_reception")
	private String referenceBonReception;


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
	 *            the new id
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
	 *            the new date
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRaisonMouvementId() {
		return raisonMouvementId;
	}

	public void setRaisonMouvementId(Long raisonMouvementId) {
		this.raisonMouvementId = raisonMouvementId;
	}

	/**
	 * Gets the gc blachat id.
	 *
	 * @return the gc blachat id
	 */

	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the responsable.
	 *
	 * @return the responsable
	 */
	public String getResponsable() {
		return responsable;
	}

	/**
	 * Sets the responsable.
	 *
	 * @param responsable
	 *            the new responsable
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the valeur.
	 *
	 * @return the valeur
	 */
	public Double getValeur() {
		return valeur;
	}

	/**
	 * Sets the valeur.
	 *
	 * @param valeur
	 *            the new valeur
	 */
	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	/**
	 * Gets the bl suppression.
	 *
	 * @return the bl suppression
	 */
	public Boolean getBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression
	 *            the new bl suppression
	 */
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation
	 *            the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification
	 *            the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Set<MouvementEntite> getMouvements() {
		return mouvements;
	}

	public void setMouvements(Set<MouvementEntite> mouvements) {
		this.mouvements = mouvements;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression
	 *            the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Long getPartieInteresseeId() {
		return partieInteresseeId;
	}

	public void setPartieInteresseeId(Long partieInteresseeId) {
		this.partieInteresseeId = partieInteresseeId;
	}

	public BLAchatEntite getBlachat() {
		return blachat;
	}

	public void setBlachat(BLAchatEntite blachat) {
		this.blachat = blachat;
	}

	public Long getOfId() {
		return ofId;
	}

	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}

	public String getNumBRSortie() {
		return numBRSortie;
	}

	public void setNumBRSortie(String numBRSortie) {
		this.numBRSortie = numBRSortie;
	}

	public boolean isOrigineFSuiveuse() {
		return origineFSuiveuse;
	}

	public void setOrigineFSuiveuse(boolean origineFSuiveuse) {
		this.origineFSuiveuse = origineFSuiveuse;
	}

	public String getReferenceBonReception() {
		return referenceBonReception;
	}

	public void setReferenceBonReception(String referenceBonReception) {
		this.referenceBonReception = referenceBonReception;
	}

}