/**
 * @author Hajer
 * @since 19/03/2017 
 */

package com.gpro.consulting.tiers.logistique.persistance.gc.reception.entitie;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;

@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_PRODUITRECEPTION)
public class ProduitReceptionEntity {

	@Id
	@SequenceGenerator(name = "PRODUITRECEPTION_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_PRODUITRECEPTION, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUITRECEPTION_ID_GENERATOR")
	private Long id;

	/** many-to-one association to receptionVente**. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GC_RECEPTIONVENTE_ID")
	private ReceptionVenteEntity receptionVente;

	/** many-to-one association to Produit**. */

	@Column(name = "EB_PRODUIT_ID")
	private Long produit;

	/** The prix. */
	@Column(name = "PRIX")
	private Double prixUnitaire;

	/** The quantite. */
	@Column(name = "QUANTITE")
	private Double quantite;

	/** The date livraison. */
	@Column(name = "DATE_LIVRAISON")
	private Calendar dateLivraison;

	/**
	 * 
	 */
	public ProduitReceptionEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param commandeVente
	 * @param produit
	 * @param prix
	 * @param quantite
	 * @param dateLivraison
	 */
	public ProduitReceptionEntity(Long id, ReceptionVenteEntity receptionVente, Long produit, Double prix,
			Double quantite, Calendar dateLivraison) {
		super();
		this.id = id;
		this.receptionVente = receptionVente;
		this.produit = produit;
		this.prixUnitaire = prix;
		this.quantite = quantite;
		this.dateLivraison = dateLivraison;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReceptionVenteEntity getReceptionVente() {
		return receptionVente;
	}

	public void setReceptionVente(ReceptionVenteEntity receptionVente) {
		this.receptionVente = receptionVente;
	}

	public Long getProduit() {
		return produit;
	}

	public void setProduit(Long produit) {
		this.produit = produit;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

}
