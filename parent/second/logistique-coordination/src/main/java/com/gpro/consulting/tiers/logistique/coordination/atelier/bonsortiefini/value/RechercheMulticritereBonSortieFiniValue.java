package com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value;

import java.util.Calendar;

/**
 * DTO Request pour la recherche multicriteres pour les BonSortieFini 
 * 
 * @author Wahid Gazzah
 * @since 12/01/2016
 *
 */
public class RechercheMulticritereBonSortieFiniValue {
	
	private String reference;
	private Calendar dateSortieMin;
	private Calendar dateSortieMax;
	private String type; 
	private Long partieInt;
	private String fini;
	private String rempli; // oui ou non
	
	private boolean optimized;
	
	
	
	public boolean isOptimized() {
		return optimized;
	}
	public void setOptimized(boolean optimized) {
		this.optimized = optimized;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
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
	public Calendar getDateSortieMin() {
		return dateSortieMin;
	}
	public void setDateSortieMin(Calendar dateSortieMin) {
		this.dateSortieMin = dateSortieMin;
	}
	public Calendar getDateSortieMax() {
		return dateSortieMax;
	}
	public void setDateSortieMax(Calendar dateSortieMax) {
		this.dateSortieMax = dateSortieMax;
	}
	public String getFini() {
		return fini;
	}
	public void setFini(String fini) {
		this.fini = fini;
	}
	public String getRempli() {
		return rempli;
	}
	public void setRempli(String rempli) {
		this.rempli = rempli;
	}
	
	
}
