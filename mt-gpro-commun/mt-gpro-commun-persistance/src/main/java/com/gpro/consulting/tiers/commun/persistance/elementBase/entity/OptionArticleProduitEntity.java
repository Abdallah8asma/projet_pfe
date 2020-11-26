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
@Table(name = IConstante.TABLE_OPTION_ARTICLE_PRODUIT)
public class OptionArticleProduitEntity implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 2359404174074018193L;

	@Id
	@SequenceGenerator(name = "OPTION_ARTICLE_PRODUIT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_OPTION_ARTICLE_PRODUIT)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPTION_ARTICLE_PRODUIT_ID_GENERATOR")
	private Long id;

	@Column(name = "option_article_id")
	private Long optionArticleId;
	
	
	
	
	
	@Column(name = "famille_option_article_designation")
	private String familleOptionArticleDesignation;

	
	@Column(name = "nom")
	private String nom;
	
	
	@Column(name = "designation")
	private String designation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eb_articleprod_id")
	private ArticleProduitEntity articleProduit;
	
	
	
	
	

	public String getFamilleOptionArticleDesignation() {
		return familleOptionArticleDesignation;
	}

	public void setFamilleOptionArticleDesignation(String familleOptionArticleDesignation) {
		this.familleOptionArticleDesignation = familleOptionArticleDesignation;
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

	public Long getOptionArticleId() {
		return optionArticleId;
	}

	public void setOptionArticleId(Long optionArticleId) {
		this.optionArticleId = optionArticleId;
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
