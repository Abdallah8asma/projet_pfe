package com.gpro.consulting.tiers.logistique.persistance.gs.entite;

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

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Entity
@Table(name=IConstante.TABLE_DET_BON_STOCK)
public class DetBonStockEntity implements Serializable{

	private static final long serialVersionUID = 569048653919573589L;
	
	@Id
	@SequenceGenerator(name="DET_BON_STOCK_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_DET_BON_STOCK, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DET_BON_STOCK_ID_GENERATOR")
    private Long id;
	
	@Column(name="QUANTITE")
	private Double quantite;
	
	@Column(name="UNITE")
	private String unite;
	
	@Column(name="NOMBRE_COLIS")
	private Long nombreColis;
	
	@Column(name="EB_PRODUIT_ID")
	private Long produitId;
	
	@Column(name="REMISE")
	private Double remise;
	
	/** many-to-one association to {@link BonStockEntity}. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "GS_BON_STOCK_ID")
	private BonStockEntity bonStock;

	//added on 18/02/2016, by Wahid Gazzah
	//updated on 24/02/2016, by Wahid Gazzah from Long to String
	@Column(name="CHOIX")
	private String choix;

	//Added on 03/10/2016 - Zeineb Medimagh
	@Column(name="GL_TRAITEMENTFACON_ID")
	private Long traitementFaconId;
	
	//Added on 11/10/2016 - Zeineb Medimagh
	@Column(name="PUHT")
	private Double prixUnitaireHT;
	
	@Column(name="PTHT")
	private Double prixTotalHT;
	
	//Added on 17/10/2016 - Zeineb Medimagh
	@Column(name="FICHE_ID")
	private Long ficheId;
	
	// Added on 24/05/2018 - Ghazi ATROUSSI
	@Column(name="unite_supplementaire")
	private String  uniteSupplementaire;
	
	@Column(name="quantite_conversion")
	private Double  quantiteConversion;
	
	@Column(name="conversion")
	private Boolean conversion;

	@Column(name="puht_conversion")
	private Double prixUnitaireHTConversion;
	
	
	@Column(name="taux_tva")
	private Double tauxTva;
	
	
	@Column(name="prix_ttc")
	private Double prixTTC;
	
	@Column(name = "serialisable")
	private boolean serialisable;
	
	@Column(name="numero_series")
	private String numeroSeries;
	
	
	@Column(name="description")
	private String description;
	
	
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
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

	

	public BonStockEntity getBonStock() {
		return bonStock;
	}

	public void setBonStock(BonStockEntity bonStock) {
		this.bonStock = bonStock;
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

	public Long getTraitementFaconId() {
		return traitementFaconId;
	}

	public void setTraitementFaconId(Long traitementFaconId) {
		this.traitementFaconId = traitementFaconId;
	}

	public Double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	public void setPrixUnitaireHT(Double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
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

	public String getUniteSupplementaire() {
		return uniteSupplementaire;
	}

	public void setUniteSupplementaire(String uniteSupplementaire) {
		this.uniteSupplementaire = uniteSupplementaire;
	}

	public Double getQuantiteConversion() {
		return quantiteConversion;
	}

	public void setQuantiteConversion(Double quantiteConversion) {
		this.quantiteConversion = quantiteConversion;
	}

	public Boolean getConversion() {
		return conversion;
	}

	public void setConversion(Boolean conversion) {
		this.conversion = conversion;
	}

	public Double getPrixUnitaireHTConversion() {
		return prixUnitaireHTConversion;
	}

	public void setPrixUnitaireHTConversion(Double prixUnitaireHTConversion) {
		this.prixUnitaireHTConversion = prixUnitaireHTConversion;
	}

	public Double getTauxTva() {
		return tauxTva;
	}

	public void setTauxTva(Double tauxTva) {
		this.tauxTva = tauxTva;
	}

	public Double getPrixTTC() {
		return prixTTC;
	}

	public void setPrixTTC(Double prixTTC) {
		this.prixTTC = prixTTC;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
