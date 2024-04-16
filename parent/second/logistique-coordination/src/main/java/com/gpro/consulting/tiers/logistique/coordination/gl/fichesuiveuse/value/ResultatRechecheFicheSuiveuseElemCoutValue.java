package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value;

/**
 * @author Zeineb Medimagh
 * @since 21/09/2016
 *
 */
public class ResultatRechecheFicheSuiveuseElemCoutValue extends ResultatRechecheFicheSuiveuseElementValue {
	
	private Double coutRecette;
	private Double coutTraitement;
	private Double coutTotal;
	public Double getCoutRecette() {
		return coutRecette;
	}
	public void setCoutRecette(Double coutRecette) {
		this.coutRecette = coutRecette;
	}
	public Double getCoutTraitement() {
		return coutTraitement;
	}
	public void setCoutTraitement(Double coutTraitement) {
		this.coutTraitement = coutTraitement;
	}
	public Double getCoutTotal() {
		return coutTotal;
	}
	public void setCoutTotal(Double coutTotal) {
		this.coutTotal = coutTotal;
	}
	@Override
	public String toString() {
		return "ResultatRechecheFicheSuiveuseElemCoutValue [coutRecette=" + coutRecette + ", coutTraitement="
				+ coutTraitement + ", coutTotal=" + coutTotal + ", getId()=" + getId() + ", getProduitId()="
				+ getProduitId() + ", getTypeLivraison()=" + getTypeLivraison() + ", getPoidSortie()=" + getPoidSortie()
				+ ", getLaizeFini()=" + getLaizeFini() + ", getProduitReference()=" + getProduitReference()
				+ ", getProduitDesignation()=" + getProduitDesignation() + ", getClientReference()="
				+ getClientReference() + ", getClientAbreviation()=" + getClientAbreviation() + ", getReferenceMise()="
				+ getReferenceMise() + ", getPartieIntId()=" + getPartieIntId() + ", getDateLivraison()="
				+ getDateLivraison() + ", getDateLancement()=" + getDateLancement() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
}
