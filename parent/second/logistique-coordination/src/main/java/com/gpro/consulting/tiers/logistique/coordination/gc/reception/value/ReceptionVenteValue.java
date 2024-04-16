/**
 * @author Zeineb
 * @since 16 nov. 2016 
 */
package com.gpro.consulting.tiers.logistique.coordination.gc.reception.value;

import java.util.Calendar;
import java.util.List;

public class ReceptionVenteValue implements Comparable<ReceptionVenteValue> {

	private Long id;

	private Long siteId;

	private String reference;

	private String refexterne;

	private Double prixTotal;

	private Calendar dateIntroduction;

	private Calendar dateLivraison;

	private String observations;

	private Long partieIntersseId;

	private boolean blSuppression;

	private Calendar dateSuppression;

	private Calendar dateModification;

	private Calendar dateCreation;

	private List<ProduitReceptionValue> listProduitReceptions;

	private String PartieIntersseAbbreviation;

	private Double quantite;

	private Long idDepot;

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
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

	public String getPartieIntersseAbbreviation() {
		return PartieIntersseAbbreviation;
	}

	public void setPartieIntersseAbbreviation(String partieIntersseAbbreviation) {
		PartieIntersseAbbreviation = partieIntersseAbbreviation;
	}

	public Double getQuantite() {
		return quantite;
	}

	public List<ProduitReceptionValue> getListProduitReceptions() {
		return listProduitReceptions;
	}

	public void setListProduitReceptions(List<ProduitReceptionValue> listProduitReceptions) {
		this.listProduitReceptions = listProduitReceptions;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	@Override
	public int compareTo(ReceptionVenteValue o) {
		ReceptionVenteValue commandeVenteValue = (ReceptionVenteValue) o;
		return (commandeVenteValue.getReference().compareTo(reference));
	}

}
