/**
 * @author Zeineb
 * @since 16 nov. 2016 
 */

package com.gpro.consulting.tiers.logistique.persistance.gc.reception.entitie;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;

@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_RECEPTIONVENTE)
public class ReceptionVenteEntity {

	/** The id. */
	@Id
	@SequenceGenerator(name = "RECEPTIONVENTE_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_RECEPTIONVENTE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEPTIONVENTE_ID_GENERATOR")
	private Long id;

	/** The com site id. */
	@Column(name = "PI_COM_SITE_ID")
	private Long siteId;

	/** The reference. */
	@Column(name = "REFERENCE")
	private String reference;

	/** The prix total */
	@Column(name = "PRIX_TOTAL")
	private Double prixTotal;

	/** The date introduction. */
	@Column(name = "DATE_INTRODUCTION")
	private Calendar dateIntroduction;

	/** The date livraison. */
	@Column(name = "DATE_LIVRAISON")
	private Calendar dateLivraison;

	/** The observations. */
	@Column(name = "OBSERVATIONS")
	private String observations;

	/** The pi pi id. */
	@Column(name = "PI_PI_ID")
	private Long partieIntersseId;

	/** The bl suppression. */
	@Column(name = "BL_SUPPRESSION")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;

	/** The date modification. */
	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;

	/** The date creation. */
	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	/** The date creation. */
	@Column(name = "QUANTITE")
	private Double quantite;

	/** many-to-one association to ProduitCommande. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receptionVente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProduitReceptionEntity> listProduitReceptions;
	
	/** The reference. */
	@Column(name = "REFEXTERNE")
	private String refexterne;
	
	@Column(name = "id_depot")
	private Long idDepot;

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	/**
	 * Constructeur par défault
	 */
	public ReceptionVenteEntity() {
	}

	/**
	 * @param id
	 * @param siteId
	 * @param reference
	 * @param prixTotal
	 * @param dateIntroduction
	 * @param dateLivraison
	 * @param observations
	 * @param partieIntersseId
	 * @param blSuppression
	 * @param dateSuppression
	 * @param dateModification
	 * @param dateCreation
	 * @param produitCommandes
	 */
	public ReceptionVenteEntity(Long id, Long siteId, String reference, Double prixTotal, Calendar dateIntroduction,
			Calendar dateLivraison, String observations, Long partieIntersseId, boolean blSuppression,
			Calendar dateSuppression, Calendar dateModification, Calendar dateCreation,
			Set<ProduitReceptionEntity> produitReceptions) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.reference = reference;
		this.prixTotal = prixTotal;
		this.dateIntroduction = dateIntroduction;
		this.dateLivraison = dateLivraison;
		this.observations = observations;
		this.partieIntersseId = partieIntersseId;
		this.blSuppression = blSuppression;
		this.dateSuppression = dateSuppression;
		this.dateModification = dateModification;
		this.dateCreation = dateCreation;
		this.listProduitReceptions = listProduitReceptions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public List<ProduitReceptionEntity> getListProduitReceptions() {
		return listProduitReceptions;
	}

	public void setListProduitReceptions(List<ProduitReceptionEntity> listProduitReceptions) {
		this.listProduitReceptions = listProduitReceptions;
	}

}
