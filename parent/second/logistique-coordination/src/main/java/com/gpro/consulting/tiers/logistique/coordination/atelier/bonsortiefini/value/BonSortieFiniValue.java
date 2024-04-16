package com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;

/**
 * 
 * @author Wahid Gazzah
 * @since 08/01/2015
 *
 */
public class BonSortieFiniValue implements Comparable<BonSortieFiniValue>{
	
    private Long id;
	private String reference;
	private Calendar dateSortie;
	private String observation;
	private Integer nbColis; 
	private Double metrageTotal;
	private String type; 
	private Long partieInt;
	private String partieIntDesignation;
	private Double poidsFini;
	private Double poidsEcru;
	
	
	private String refAvantChangement;
	

	public String getRefAvantChangement() {
		return refAvantChangement;
	}
	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	private List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Calendar getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public Integer getNbColis() {
		return nbColis;
	}
	public void setNbColis(Integer nbColis) {
		this.nbColis = nbColis;
	}
	public Double getMetrageTotal() {
		return metrageTotal;
	}
	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPartieInt() {
		return partieInt;
	}
	public void setPartieInt(Long partieInt) {
		this.partieInt = partieInt;
	}

	public Double getPoidsFini() {
		return poidsFini;
	}
	public void setPoidsFini(Double poidsFini) {
		this.poidsFini = poidsFini;
	}
	public Double getPoidsEcru() {
		return poidsEcru;
	}
	public void setPoidsEcru(Double poidsEcru) {
		this.poidsEcru = poidsEcru;
	}
	public List<RouleauFiniValue> getListeRouleauFini() {
		return listeRouleauFini;
	}
	public void setListeRouleauFini(List<RouleauFiniValue> listeRouleauFini) {
		this.listeRouleauFini = listeRouleauFini;
	}
	/** Accesseur en lecture de l'attribut partieIntDesignation.
	 * @return the partieIntDesignation
	 */
	public String getPartieIntDesignation() {
		return partieIntDesignation;
	}
	/**
	 * @param partieIntDesignation the partieIntDesignation to set
	 */
	public void setPartieIntDesignation(String partieIntDesignation) {
		this.partieIntDesignation = partieIntDesignation;
	}
	
	@Override
	public int compareTo(BonSortieFiniValue o) {
		BonSortieFiniValue element= (BonSortieFiniValue)o;
		return (element.getReference().compareTo(reference));
	}
	
}
