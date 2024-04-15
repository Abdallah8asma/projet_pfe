package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

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

import com.gpro.consulting.tiers.commun.coordination.IConstante;

@Entity
@Table(name = IConstante.TABLE_OPERATION_ARTICLE_PRODUIT)
public class OperationArticleProduitEntity implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -6199834431862073142L;

	@Id
	@SequenceGenerator(name = "OPERATION_ARTICLE_PRODUIT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_OPERATION_ARTICLE_PRODUIT)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_ARTICLE_PRODUIT_ID_GENERATOR")
	private Long id;

	@Column(name = "operation_article_id")
	private Long operationArticleId;

	@Column(name = "nom")
	private String nom;

	@Column(name = "designation")
	private String designation;

	@Column(name = "cout")
	private Double cout;

	@Column(name = "temps")
	private Double temps;

	@Column(name = "ordre")
	private Long ordre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eb_articleprod_id")
	private ArticleProduitEntity articleProduit;

	
	
	
	
	public Double getCout() {
		return cout;
	}

	public void setCout(Double cout) {
		this.cout = cout;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOperationArticleId() {
		return operationArticleId;
	}

	public void setOperationArticleId(Long operationArticleId) {
		this.operationArticleId = operationArticleId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArticleProduitEntity getArticleProduit() {
		return articleProduit;
	}

	public void setArticleProduit(ArticleProduitEntity articleProduit) {
		this.articleProduit = articleProduit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
