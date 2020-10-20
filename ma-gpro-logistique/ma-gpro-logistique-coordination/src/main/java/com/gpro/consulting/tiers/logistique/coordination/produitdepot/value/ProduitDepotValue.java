package com.gpro.consulting.tiers.logistique.coordination.produitdepot.value;

public class ProduitDepotValue {

	private Long id;

	/** Idproduit */
	private Long idProduit;

	/** The depot_id. */
	private Long idDepot;

	/** The quantite */

	private Double quantite;

	private Double seuilMin;

	private Double seuilMax;

	private String referenceProduit;
	private String designationProduit;
	private String designationMagasin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getSeuilMin() {
		return seuilMin;
	}

	public void setSeuilMin(Double seuilMin) {
		this.seuilMin = seuilMin;
	}

	public Double getSeuilMax() {
		return seuilMax;
	}

	public void setSeuilMax(Double seuilMax) {
		this.seuilMax = seuilMax;
	}

	public String getReferenceProduit() {
		return referenceProduit;
	}

	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	public String getDesignationProduit() {
		return designationProduit;
	}

	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}

	public String getDesignationMagasin() {
		return designationMagasin;
	}

	public void setDesignationMagasin(String designationMagasin) {
		this.designationMagasin = designationMagasin;
	}

}
