package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value;

import java.util.Calendar;
import java.util.Set;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class DetLivraisonVenteValue implements Comparable<DetLivraisonVenteValue> {

	private Long id;
	private Long produitId;
	private Double quantite;
	private Double quantiteAncien;
	private String unite;
	private Long nombreColis;
	private Long livraisonVenteId;
	private Double remise;

	// this values used only on validate action
	private String produitDesignation;
	private String produitReference;
	private Double prixUnitaireHT;
	private Double prixTotalHT;

	// added on 18/02/2016, by Wahid Gazzah
	// updated on 24/02/2016, by Wahid Gazzah, from Long to String
	private String choix;
	private String typeRapport;

	// Added on 03/10/2016 - Zeineb Medimagh
	private Long traitementFaconId;

	// Added on 17/10/2016 - Zeineb Medimagh
	private Long ficheId;
	private String refMiseList;
	private boolean errorQte;

	private Double montantTaxeTVA;

	// Ajout Details Recherche 04/04/2018

	private String referenceBL;
	private String partieInteressee;
	private Calendar date;
	private String depotDesignation;

	// Ajout par Ghazi 23/05/2018
	private Boolean conversion;
	private String uniteSupplementaire;
	private Double quantiteConversion;
	private Double prixUnitaireHTConversion;

	// Ajout par Ghazi 24/05/2018 -conversion TTC TO HTAXE
	private Boolean nouveau;
	private Double prixTTC;
	private Double tauxTVA;

	private boolean serialisable;

	private String numeroSeries;

	private String description;

	private Long taxeId;
	
	
	
	
	
	
	//nouveau from thermo
	private String palette;
	
	private String numeroOF;
	
	
	
	private String couleurProduit;
	
	
	private String typeProduit;
	
	
	
	

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public String getNumeroOF() {
		return numeroOF;
	}

	public void setNumeroOF(String numeroOF) {
		this.numeroOF = numeroOF;
	}

	public String getCouleurProduit() {
		return couleurProduit;
	}

	public void setCouleurProduit(String couleurProduit) {
		this.couleurProduit = couleurProduit;
	}

	public String getTypeProduit() {
		return typeProduit;
	}

	public void setTypeProduit(String typeProduit) {
		this.typeProduit = typeProduit;
	}

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getQuantiteAncien() {
		return quantiteAncien;
	}

	public void setQuantiteAncien(Double quantiteAncien) {
		this.quantiteAncien = quantiteAncien;
	}

	public String getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

	private Set<ProduitSerialisableValue> produitsSerialisable;

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public Set<ProduitSerialisableValue> getProduitsSerialisable() {
		return produitsSerialisable;
	}

	public void setProduitsSerialisable(Set<ProduitSerialisableValue> produitsSerialisable) {
		this.produitsSerialisable = produitsSerialisable;
	}

	public Double getPrixTTC() {
		return prixTTC;
	}

	public void setPrixTTC(Double prixTTC) {
		this.prixTTC = prixTTC;
	}

	@Override
	public int compareTo(DetLivraisonVenteValue o) {
		// commente par samer le 09.06.20 afin de rendre le meme ordre de saisie
		DetLivraisonVenteValue element = (DetLivraisonVenteValue) o;
		return (element.getFicheId().compareTo(ficheId));
		// return 0;
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

	public Long getLivraisonVenteId() {
		return livraisonVenteId;
	}

	public void setLivraisonVenteId(Long livraisonVenteId) {
		this.livraisonVenteId = livraisonVenteId;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
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

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public String getTypeRapport() {
		return typeRapport;
	}

	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}

	public Long getTraitementFaconId() {
		return traitementFaconId;
	}

	public void setTraitementFaconId(Long traitementFaconId) {
		this.traitementFaconId = traitementFaconId;
	}

	public Long getFicheId() {
		return ficheId;
	}

	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}

	public String getRefMiseList() {
		return refMiseList;
	}

	public void setRefMiseList(String refMiseList) {
		this.refMiseList = refMiseList;
	}

	public boolean isErrorQte() {
		return errorQte;
	}

	public void setErrorQte(boolean errorQte) {
		this.errorQte = errorQte;
	}

	public Double getMontantTaxeTVA() {
		return montantTaxeTVA;
	}

	public void setMontantTaxeTVA(Double montantTaxeTVA) {
		this.montantTaxeTVA = montantTaxeTVA;
	}

	@Override
	public String toString() {
		return "DetLivraisonVenteValue [id=" + id + ", produitId=" + produitId + ", quantite=" + quantite + ", unite="
				+ unite + ", nombreColis=" + nombreColis + ", livraisonVenteId=" + livraisonVenteId + ", remise="
				+ remise + ", produitDesignation=" + produitDesignation + ", produitReference=" + produitReference
				+ ", prixUnitaireHT=" + prixUnitaireHT + ", prixTotalHT=" + prixTotalHT + ", choix=" + choix
				+ ", typeRapport=" + typeRapport + ", traitementFaconId=" + traitementFaconId + ", ficheId=" + ficheId
				+ ", refMiseList=" + refMiseList + "]";
	}

	public String getReferenceBL() {
		return referenceBL;
	}

	public void setReferenceBL(String referenceBL) {
		this.referenceBL = referenceBL;
	}

	public String getPartieInteressee() {
		return partieInteressee;
	}

	public void setPartieInteressee(String partieInteressee) {
		this.partieInteressee = partieInteressee;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getDepotDesignation() {
		return depotDesignation;
	}

	public void setDepotDesignation(String depotDesignation) {
		this.depotDesignation = depotDesignation;
	}

	public Boolean getConversion() {
		return conversion;
	}

	public void setConversion(Boolean conversion) {
		this.conversion = conversion;
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

	public Boolean getNouveau() {
		return nouveau;
	}

	public void setNouveau(Boolean nouveau) {
		this.nouveau = nouveau;
	}

	public Double getTauxTVA() {
		return tauxTVA;
	}

	public void setTauxTVA(Double tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	public Double getPrixUnitaireHTConversion() {
		return prixUnitaireHTConversion;
	}

	public void setPrixUnitaireHTConversion(Double prixUnitaireHTConversion) {
		this.prixUnitaireHTConversion = prixUnitaireHTConversion;
	}

}
