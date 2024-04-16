/**
 * @author Zeineb
 * @since 16 nov. 2016 
 */

package com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie;

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
@Table(name=IConstanteCommerciale.TABLE_GC_PRODUITCOMMANDE)
public class ProduitCommandeEntity {
	
	@Id
	@SequenceGenerator(name="PRODUITCOMMANDE_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_PRODUITCOMMANDE,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUITCOMMANDE_ID_GENERATOR")
    private Long id;
	
	/** many-to-one association to CommandeVente**. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "GC_COMMANDEVENTE_ID")
	private CommandeVenteEntity commandeVente;
	
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
	@Column(name="DATE_LIVRAISON")
	private Calendar dateLivraison;
	
	/** The quantite. */
	@Column(name = "QUANTITE_LIVREE")
	private Double quantiteLivree;
	
	@Column(name = "remise")
	private Double remise;
	
	@Column(name="taxe_id")
	private Long taxeId;
	
	
	
	
	 


	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Double getQuantiteLivree() {
		return quantiteLivree;
	}

	public void setQuantiteLivree(Double quantiteLivree) {
		this.quantiteLivree = quantiteLivree;
	}

	/**
	 * 
	 */
	public ProduitCommandeEntity() {
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
	public ProduitCommandeEntity(Long id, CommandeVenteEntity commandeVente, Long produit, Double prix,
			Double quantite, Calendar dateLivraison) {
		super();
		this.id = id;
		this.commandeVente = commandeVente;
		this.produit = produit;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.dateLivraison = dateLivraison;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommandeVenteEntity getCommandeVente() {
		return commandeVente;
	}

	public void setCommandeVente(CommandeVenteEntity commandeVente) {
		this.commandeVente = commandeVente;
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

	@Override
	public String toString() {
		return "ProduitCommandeEntity [id=" + id + ", commandeVente=" + commandeVente + ", produit=" + produit
				+ ", prixUnitaire=" + prixUnitaire + ", quantite=" + quantite + ", dateLivraison=" + dateLivraison + "]";
	}
	
}
