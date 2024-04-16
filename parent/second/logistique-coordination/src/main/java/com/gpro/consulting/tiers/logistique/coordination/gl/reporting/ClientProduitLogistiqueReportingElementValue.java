package com.gpro.consulting.tiers.logistique.coordination.gl.reporting;

/**
 * @author Zeineb Medimagh
 * @since 19/10/2016
 *
 */
public class ClientProduitLogistiqueReportingElementValue implements Comparable<ClientProduitLogistiqueReportingElementValue> {

	private String typeRapport;
	private String produitReference;
	private String produitAbreviation;
	private Double metrage;
	private Double poids;

	public String getTypeRapport() {
		return typeRapport;
	}

	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getProduitAbreviation() {
		return produitAbreviation;
	}

	public void setProduitAbreviation(String produitAbreviation) {
		this.produitAbreviation = produitAbreviation;
	}

	public Double getMetrage() {
		return metrage;
	}

	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	@Override
	public int compareTo(ClientProduitLogistiqueReportingElementValue o) {
		ClientProduitLogistiqueReportingElementValue element =(ClientProduitLogistiqueReportingElementValue) o;
		return (element.getProduitReference().compareTo(produitReference));
	}
}
