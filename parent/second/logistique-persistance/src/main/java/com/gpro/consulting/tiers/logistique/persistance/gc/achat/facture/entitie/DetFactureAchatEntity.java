package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie;

import java.io.Serializable;

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

/**
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_DETFACTUREACHAT)
public class DetFactureAchatEntity implements Serializable {

	private static final long serialVersionUID = -8020749244291010596L;

	@Id
	@SequenceGenerator(name = "DETFACTUREAchat_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_CDFA_SEQ, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETFACTUREAchat_ID_GENERATOR")
	private Long id;

	@Column(name = "QUANTITE")
	private Double quantite;

	@Column(name = "UNITE")
	private String unite;

	@Column(name = "NOMBRE_COLIS")
	private Long nombreColis;

	@Column(name = "EB_ARTICLE_ID")
	private Long produitId;

	@Column(name = "REMISE")
	private Double remise;
	
	@Column(name="taxe_id")
	private Long taxeId;

	/** many-to-one association to {@link FactureAchatEntity}. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GC_FACTAchat_ID")
	private FactureAchatEntity factureAchat;

	@Column(name = "CHOIX")
	private String choix;

	@Column(name = "prixUnitaireHT")
	private Double prixUnitaireHT;

	// Added on 11/10/2016 by Zeineb Medimagh
	@Column(name = "GL_TRAITEMENTFACON_ID")
	private Long traitementFaconId;

	// Added on 12/10/2016 by Zeineb Medimagh
	@Column(name = "PRIX_TOTAL_HT")
	private Double prixTotalHT;

	// Added on 17/10/2016 by Zeineb Medimagh
	@Column(name = "FICHE_ID")
	private Long ficheId;
	
	@Column(name = "serialisable")
	private boolean serialisable;
	
	
	@Column(name="numero_series")
	private String numeroSeries;
	
	
	
	@Column(name="reference_article")
	private String referenceArticle;
	
	@Column(name="designation")
	private String designation;
	

	public String getReferenceArticle() {
		return referenceArticle;
	}

	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public String getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Long getNombreColis() {
		return nombreColis;
	}

	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}

	public FactureAchatEntity getFactureAchat() {
		return factureAchat;
	}

	public void setFactureAchat(FactureAchatEntity factureAchat) {
		this.factureAchat = factureAchat;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public Double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	public void setPrixUnitaireHT(Double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}

	public Long getTraitementFaconId() {
		return traitementFaconId;
	}

	public void setTraitementFaconId(Long traitementFaconId) {
		this.traitementFaconId = traitementFaconId;
	}

	public Double getPrixTotalHT() {
		return prixTotalHT;
	}

	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}

	public Long getFicheId() {
		return ficheId;
	}

	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}
}
