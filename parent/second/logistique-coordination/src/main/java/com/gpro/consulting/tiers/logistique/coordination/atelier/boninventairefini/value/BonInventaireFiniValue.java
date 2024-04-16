package com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value;

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
public class BonInventaireFiniValue implements Comparable<BonInventaireFiniValue>{
	
    private Long id;
	private String reference;
	private Calendar dateSortie;
	private String observation;
	private Integer nbColis; 
	private Double metrageTotal;
	
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


	public List<RouleauFiniValue> getListeRouleauFini() {
		return listeRouleauFini;
	}
	public void setListeRouleauFini(List<RouleauFiniValue> listeRouleauFini) {
		this.listeRouleauFini = listeRouleauFini;
	}
	
	
	@Override
	public int compareTo(BonInventaireFiniValue o) {
		BonInventaireFiniValue element= (BonInventaireFiniValue)o;
		return (element.getReference().compareTo(reference));
	}
	
}
