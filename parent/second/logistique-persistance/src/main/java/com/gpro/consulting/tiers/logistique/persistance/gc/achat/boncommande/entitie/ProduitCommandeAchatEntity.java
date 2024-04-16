/**
 * @author Zeineb
 * @since 16 nov. 2016 
 */

package com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.entitie;

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
@Table(name = IConstanteCommerciale.TABLE_GC_PRODUITCOMMANDEACHAT)
public class ProduitCommandeAchatEntity {

	@Id
	@SequenceGenerator(name = "PRODUITCOMMANDEACHAT_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_PRODUITCOMMANDEACHAT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUITCOMMANDEACHAT_ID_GENERATOR")
	private Long id;

	/** many-to-one association to CommandeAchat**. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GC_COMMANDEACHAT_ID")
	private CommandeAchatEntity commandeAchat;

	/** many-to-one association to Produit**. */

	@Column(name = "EB_ARTICLE_ID")
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
	
	@Column(name="taxe_id")
	private Long taxeId;
	
	
	@Column(name="UNITE")
	private String unite;
	
	
	

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	/**
	 * 
	 */
	public ProduitCommandeAchatEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param commandeAchat
	 * @param produit
	 * @param prix
	 * @param quantite
	 * @param dateLivraison
	 */
	public ProduitCommandeAchatEntity(Long id, CommandeAchatEntity commandeAchat, Long produit, Double prix,
			Double quantite, Calendar dateLivraison) {
		this.id = id;
		this.commandeAchat = commandeAchat;
		this.produit = produit;
		this.quantite = quantite;
		this.dateLivraison = dateLivraison;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommandeAchatEntity getCommandeAchat() {
		return commandeAchat;
	}

	public void setCommandeAchat(CommandeAchatEntity commandeAchat) {
		this.commandeAchat = commandeAchat;
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
		return "ProduitCommandeEntity [id=" + id + ", commandeAchat=" + commandeAchat + ", produit=" + produit
				+ ", prixUnitaire=" + prixUnitaire + ", quantite=" + quantite + ", dateLivraison=" + dateLivraison
				+ "]";
	}

}
