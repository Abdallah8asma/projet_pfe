package com.gpro.consulting.tiers.logistique.persistance.gs.transfert.entite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;;

/**
 * @author
 * @since
 *
 */
@Entity
@Table(name = IConstante.TABLE_DET_BON_TRANSFERT)
public class DetBonTransfertEntity implements Serializable {

	private static final long serialVersionUID = 569048653919573589L;

	@Id
	@SequenceGenerator(name = "DET_BON_TRANSFERT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_DET_BON_TRANSFERT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DET_BON_TRANSFERT_ID_GENERATOR")
	private Long id;

	@Column(name = "QUANTITE")
	private Double quantite;
	
	@Column(name = "QUANTITE_recu")
	private Double quantiteRecu;
	
	@Column(name = "QUANTITE_envoyer")
	private Double quantiteEnvoyer;

	@Column(name = "EB_PRODUIT_ID")
	private Long produitId;

	/** many-to-one association to {@link BonTransfertEntity}. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GS_BON_TRANSFERT_ID")
	private BonTransfertEntity bonTransfert;

	@Column(name = "serialisable")
	private boolean serialisable;

	@Column(name = "numero_series")
	private String numeroSeries;

	@Column(name = "description")
	private String description;
	
	
	
	

	public Double getQuantiteEnvoyer() {
		return quantiteEnvoyer;
	}

	public void setQuantiteEnvoyer(Double quantiteEnvoyer) {
		this.quantiteEnvoyer = quantiteEnvoyer;
	}

	public Double getQuantiteRecu() {
		return quantiteRecu;
	}

	public void setQuantiteRecu(Double quantiteRecu) {
		this.quantiteRecu = quantiteRecu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	

	public BonTransfertEntity getBonTransfert() {
		return bonTransfert;
	}

	public void setBonTransfert(BonTransfertEntity bonTransfert) {
		this.bonTransfert = bonTransfert;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public String getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
