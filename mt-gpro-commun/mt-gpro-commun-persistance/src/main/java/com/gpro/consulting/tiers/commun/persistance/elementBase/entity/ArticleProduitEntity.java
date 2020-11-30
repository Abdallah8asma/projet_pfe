package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

@Entity
@Table(name = IConstante.TABLE_ARTICLE_PRODUIT)
public class ArticleProduitEntity implements Serializable {

	private static final long serialVersionUID = 4350985648971998529L;

	@Id
	@SequenceGenerator(name = "ARTICLE_PRODUIT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_ARTICLE_PRODUIT)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_PRODUIT_ID_GENERATOR")
	private Long id;

	@Column(name = "article_id")
	private Long articleId;

	@Column(name = "qte")
	private Double qte;

	@Column(name = "produit_semi_fini")
	private String produitSemiFini;

	@Column(name = "reference_article")
	private String referenceArticle;

	@Column(name = "impression_produit_id")
	private Long impressionProduitId;
	
	@Column(name = "grammage")
	private String grammage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eb_produit_id")
	private ProduitEntity produit;
	
	@Column(name = "dimension")
	private String dimension;
	
	
	@Column(name = "sous_famille_article_id")
	private Long sousFamilleArticleId;
	
	
	@Column(name = "info_matiere")
	private String infoMatiere;
	
	
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "articleProduit", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OptionArticleProduitEntity> optionArticleProduits;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "articleProduit", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OperationArticleProduitEntity> operationArticleProduits;

	
	
	

	public String getInfoMatiere() {
		return infoMatiere;
	}


	public void setInfoMatiere(String infoMatiere) {
		this.infoMatiere = infoMatiere;
	}


	public Long getSousFamilleArticleId() {
		return sousFamilleArticleId;
	}


	public void setSousFamilleArticleId(Long sousFamilleArticleId) {
		this.sousFamilleArticleId = sousFamilleArticleId;
	}


	public String getDimension() {
		return dimension;
	}


	public void setDimension(String dimension) {
		this.dimension = dimension;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getArticleId() {
		return articleId;
	}


	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}


	public Double getQte() {
		return qte;
	}


	public void setQte(Double qte) {
		this.qte = qte;
	}


	public String getProduitSemiFini() {
		return produitSemiFini;
	}


	public void setProduitSemiFini(String produitSemiFini) {
		this.produitSemiFini = produitSemiFini;
	}


	public String getReferenceArticle() {
		return referenceArticle;
	}


	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}


	public Long getImpressionProduitId() {
		return impressionProduitId;
	}


	public void setImpressionProduitId(Long impressionProduitId) {
		this.impressionProduitId = impressionProduitId;
	}


	public String getGrammage() {
		return grammage;
	}


	public void setGrammage(String grammage) {
		this.grammage = grammage;
	}


	public ProduitEntity getProduit() {
		return produit;
	}


	public void setProduit(ProduitEntity produit) {
		this.produit = produit;
	}


	public Set<OptionArticleProduitEntity> getOptionArticleProduits() {
		return optionArticleProduits;
	}


	public void setOptionArticleProduits(Set<OptionArticleProduitEntity> optionArticleProduits) {
		this.optionArticleProduits = optionArticleProduits;
	}


	public Set<OperationArticleProduitEntity> getOperationArticleProduits() {
		return operationArticleProduits;
	}


	public void setOperationArticleProduits(Set<OperationArticleProduitEntity> operationArticleProduits) {
		this.operationArticleProduits = operationArticleProduits;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
