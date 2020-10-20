package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;


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
