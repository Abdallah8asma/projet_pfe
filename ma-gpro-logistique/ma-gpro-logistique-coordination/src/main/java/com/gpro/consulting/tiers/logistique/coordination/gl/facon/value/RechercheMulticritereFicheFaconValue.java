package com.gpro.consulting.tiers.logistique.coordination.gl.facon.value;

/**
 * 
 * @author Zeineb Medimagh
 * @since 28/09/2016
 *
 */

public class RechercheMulticritereFicheFaconValue {
	
	private Long produitId;
	private Long partieIntId;
	private String refBonReception;
	
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getPartieIntId() {
		return partieIntId;
	}
	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}
	public String getRefBonReception() {
		return refBonReception;
	}
	public void setRefBonReception(String refBonReception) {
		this.refBonReception = refBonReception;
	}
}
