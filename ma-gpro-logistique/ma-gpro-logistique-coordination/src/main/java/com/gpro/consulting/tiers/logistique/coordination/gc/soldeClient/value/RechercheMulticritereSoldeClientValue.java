package com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class RechercheMulticritereSoldeClientValue {

	private Long partieIntId;
	private Boolean bloque;
	private Calendar dateMin;
	private Calendar dateMax;

	private Long partieIntFamilleId;
	
	
	private Long deviseId;
	
	
	

	public Long getDeviseId() {
		return deviseId;
	}

	public void setDeviseId(Long deviseId) {
		this.deviseId = deviseId;
	}

	public Long getPartieIntFamilleId() {
		return partieIntFamilleId;
	}

	public void setPartieIntFamilleId(Long partieIntFamilleId) {
		this.partieIntFamilleId = partieIntFamilleId;
	}

	/**
	 * 
	 */
	public RechercheMulticritereSoldeClientValue() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param partieIntId
	 * @param bloque
	 */
	public RechercheMulticritereSoldeClientValue(Long partieIntId, Boolean bloque) {
		super();
		this.partieIntId = partieIntId;
		this.bloque = bloque;
	}

	/**
	 * @param partieIntId
	 * @param dateMin
	 * @param dateMax
	 */
	public RechercheMulticritereSoldeClientValue(Long partieIntId, Calendar dateMin, Calendar dateMax) {
		super();
		this.partieIntId = partieIntId;
		this.dateMin = dateMin;
		this.dateMax = dateMax;
	}

	/**
	 * @param partieIntId
	 * @param bloque
	 * @param dateMin
	 * @param dateMax
	 */

	public RechercheMulticritereSoldeClientValue(Long partieIntId, Boolean bloque, Calendar dateMin, Calendar dateMax) {
		super();
		this.partieIntId = partieIntId;
		this.bloque = bloque;
		this.dateMin = dateMin;
		this.dateMax = dateMax;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Boolean getBloque() {
		return bloque;
	}

	public void setBloque(Boolean bloque) {
		this.bloque = bloque;
	}

	public Calendar getDateMin() {
		return dateMin;
	}

	public void setDateMin(Calendar dateMin) {
		this.dateMin = dateMin;
	}

	public Calendar getDateMax() {
		return dateMax;
	}

	public void setDateMax(Calendar dateMax) {
		this.dateMax = dateMax;
	}

}
