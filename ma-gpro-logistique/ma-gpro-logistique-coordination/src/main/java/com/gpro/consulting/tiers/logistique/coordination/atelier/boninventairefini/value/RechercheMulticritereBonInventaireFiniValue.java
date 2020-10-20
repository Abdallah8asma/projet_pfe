package com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value;

import java.util.Calendar;

/**
 * DTO Request pour la recherche multicriteres pour les BonSortieFini 
 * 
 * @author Wahid Gazzah
 * @since 12/01/2016
 *
 */
public class RechercheMulticritereBonInventaireFiniValue {
	
	private String reference;
	private Calendar dateSortieMin;
	private Calendar dateSortieMax;
	private String emplacement;
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
	 * @return the dateSortieMin
	 */
	public Calendar getDateSortieMin() {
		return dateSortieMin;
	}
	/**
	 * @param dateSortieMin the dateSortieMin to set
	 */
	public void setDateSortieMin(Calendar dateSortieMin) {
		this.dateSortieMin = dateSortieMin;
	}
	/**
	 * @return the dateSortieMax
	 */
	public Calendar getDateSortieMax() {
		return dateSortieMax;
	}
	/**
	 * @param dateSortieMax the dateSortieMax to set
	 */
	public void setDateSortieMax(Calendar dateSortieMax) {
		this.dateSortieMax = dateSortieMax;
	}
	/**
	 * @return the emplacement
	 */
	public String getEmplacement() {
		return emplacement;
	}
	/**
	 * @param emplacement the emplacement to set
	 */
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	} 
	
	
}
