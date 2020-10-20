/**
 * 
 */
package com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value;

import java.util.Calendar;
import java.util.Set;

/**
 * @author w.s.i
 *
 */
public class MiseValue {
	private Long id;
	private String reference;
	private Double metrage;
	private Double poids;
	private String refBonreception;
	private Calendar dateIntroduction;
	private String observations;
	private String codeBarre;
	private Long nombreRouleau;
	private Long produitId;
	private Long partieintId;
	private Set < TraitementMiseValue > listeTraitements;
	
	//added on 20/01/2016, by Wahid Gazzah
	private Boolean fini;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return the metrage
	 */
	public Double getMetrage() {
		return metrage;
	}
	/**
	 * @param metrage the metrage to set
	 */
	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}
	/**
	 * @return the poids
	 */
	public Double getPoids() {
		return poids;
	}
	/**
	 * @param poids the poids to set
	 */
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	/**
	 * @return the refBonreception
	 */
	public String getRefBonreception() {
		return refBonreception;
	}
	/**
	 * @param refBonreception the refBonreception to set
	 */
	public void setRefBonreception(String refBonreception) {
		this.refBonreception = refBonreception;
	}
	/**
	 * @return the dateIntroduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}
	/**
	 * @param dateIntroduction the dateIntroduction to set
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}
	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}
	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}
	/**
	 * @return the codeBarre
	 */
	public String getCodeBarre() {
		return codeBarre;
	}
	/**
	 * @param codeBarre the codeBarre to set
	 */
	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}
	/**
	 * @return the nombreRouleau
	 */
	public Long getNombreRouleau() {
		return nombreRouleau;
	}
	/**
	 * @param nombreRouleau the nombreRouleau to set
	 */
	public void setNombreRouleau(Long nombreRouleau) {
		this.nombreRouleau = nombreRouleau;
	}
	/**
	 * @return the produitId
	 */
	public Long getProduitId() {
		return produitId;
	}
	/**
	 * @param produitId the produitId to set
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	/**
	 * @return the partieintId
	 */
	public Long getPartieintId() {
		return partieintId;
	}
	/**
	 * @param partieintId the partieintId to set
	 */
	public void setPartieintId(Long partieintId) {
		this.partieintId = partieintId;
	}
	/**
	 * @return the listeTraitements
	 */
	public Set<TraitementMiseValue> getListeTraitements() {
		return listeTraitements;
	}
	/**
	 * @param listeTraitements the listeTraitements to set
	 */
	public void setListeTraitements(Set<TraitementMiseValue> listeTraitements) {
		this.listeTraitements = listeTraitements;
	}
	
	public Boolean isFini() {
		return fini;
	}

	public void setFini(Boolean fini) {
		this.fini = fini;
	}
	
}
