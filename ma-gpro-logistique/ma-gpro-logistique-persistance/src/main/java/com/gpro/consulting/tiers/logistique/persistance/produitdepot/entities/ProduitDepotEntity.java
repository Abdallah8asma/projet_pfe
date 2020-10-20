package com.gpro.consulting.tiers.logistique.persistance.produitdepot.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;

/**
 * The Class ProduitEntity.
 * 
 * @author med
 */
@Entity
@Table(name = IConstanteCommerciale.TABLE_EB_PRODUITDEPOT)
public class ProduitDepotEntity implements Serializable {

	private static final long serialVersionUID = 641467517751608746L;

	

	@Id
	@SequenceGenerator(name = "EB_DEPOTPRODUIT_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_PRDE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EB_DEPOTPRODUIT_ID_GENERATOR")
	private Long id;

	

	@Column(name = "idproduit")
	private Long idProduit;
	
	@Column(name = "iddepot")
	private Long idDepot;
	
	@Column(name = "quantite")
	private Double quantite;
	
	@Column(name = "seuil_min")
	private Double seuilMin;
	
	
	@Column(name = "seuil_max")
	private Double seuilMax;


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
	
	

}