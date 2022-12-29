package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public class RechercheMulticritereRouleauFiniValue {
	
	private String reference;
	private String codeBarre;
	private String emplacement;
	private String refMise;
	private Long produitId;
	private Long partieInteresseeId;
	private String infoModif;
	
	
	
	
	private Long numberOfBox;
	
	private Double metrage;	
	
	private List<Long> ids;
	
	
	
	private boolean optimized;
	
	
	
	
	
	
	
	public boolean isOptimized() {
		return optimized;
	}
	public void setOptimized(boolean optimized) {
		this.optimized = optimized;
	}
	public Long getNumberOfBox() {
		return numberOfBox;
	}
	public void setNumberOfBox(Long numberOfBox) {
		this.numberOfBox = numberOfBox;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Double getMetrage() {
		return metrage;
	}
	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getCodeBarre() {
		return codeBarre;
	}
	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getPartieInteresseeId() {
		return partieInteresseeId;
	}
	public void setPartieInteresseeId(Long partieInteresseeId) {
		this.partieInteresseeId = partieInteresseeId;
	}
	/** Accesseur en lecture de l'attribut refMise.
	 * @return the refMise
	 */
	public String getRefMise() {
		return refMise;
	}
	/**
	 * @param refMise the refMise to set
	 */
	public void setRefMise(String refMise) {
		this.refMise = refMise;
	}
	
	public String getInfoModif() {
		return infoModif;
	}
	
	public void setInfoModif(String infoModif) {
		this.infoModif = infoModif;
	}


}
