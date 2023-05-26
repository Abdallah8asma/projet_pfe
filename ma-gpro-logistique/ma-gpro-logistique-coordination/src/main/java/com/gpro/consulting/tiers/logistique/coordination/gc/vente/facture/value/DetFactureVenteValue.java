package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Calendar;
import java.util.Set;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;

/**
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class DetFactureVenteValue implements Comparable<DetFactureVenteValue> {

	private Long id;
	private Long produitId;
	private Double quantite;
	private String unite;
	private Long nombreColis;
	private Long factureVenteId;
	private Double remise;
	private String choix;
	private String referenceFacture;
	private String referenceBl;
	private Long partieIntId;
	private Calendar dateFacture;
    private  Calendar  DateEcheance;
	// this values used only on validate action
	private String produitDesignation;
	private String produitReference;
	private Double prixUnitaireHT;
	private Double prixTotalHT;
	 private String clientAbreviation;
	 private String  commandeReference;

	// Added on 11/10/2016 by Zeineb Medimagh
	private Long traitementFaconId;

	// Added on 17/10/2016 by Zeineb Medimagh
	private String refMiseList;
	private Long ficheId;
	private Double montanTaxeTVA;

	private boolean serialisable;
	private String numeroSeries;

	private Set<ProduitSerialisableValue> produitsSerialisable;

	private String description;

	private Long taxeId;
	
	
	private String numeroOF;
	
	
	

	public String getNumeroOF() {
		return numeroOF;
	}

	public void setNumeroOF(String numeroOF) {
		this.numeroOF = numeroOF;
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

	public String getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

@Override
	public int compareTo(DetFactureVenteValue o) {
		DetFactureVenteValue element = (DetFactureVenteValue) o;
		return (element.getFicheId().compareTo(ficheId));
	}
	
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

	public Long getFactureVenteId() {
		return factureVenteId;
	}

	public void setFactureVenteId(Long factureVenteId) {
		this.factureVenteId = factureVenteId;
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

	public Long getTraitementFaconId() {
		return traitementFaconId;
	}

	public void setTraitementFaconId(Long traitementFaconId) {
		this.traitementFaconId = traitementFaconId;
	}

	public String getRefMiseList() {
		return refMiseList;
	}

	public void setRefMiseList(String refMiseList) {
		this.refMiseList = refMiseList;
	}

	public Long getFicheId() {
		return ficheId;
	}

	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}

	public Double getMontanTaxeTVA() {
		return montanTaxeTVA;
	}

	public void setMontanTaxeTVA(Double montanTaxeTVA) {
		this.montanTaxeTVA = montanTaxeTVA;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getReferenceFacture() {
		return referenceFacture;
	}

	public void setReferenceFacture(String referenceFacture) {
		this.referenceFacture = referenceFacture;
	}

	public String getReferenceBl() {
		return referenceBl;
	}

	public void setReferenceBl(String referenceBl) {
		this.referenceBl = referenceBl;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Calendar dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Calendar getDateEcheance() {
		return DateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		DateEcheance = dateEcheance;
	}

	public String getCommandeReference() {
		return commandeReference;
	}

	public void setCommandeReference(String commandeReference) {
		this.commandeReference = commandeReference;
	}

}
