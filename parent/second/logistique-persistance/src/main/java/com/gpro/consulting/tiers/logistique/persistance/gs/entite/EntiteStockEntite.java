package com.gpro.consulting.tiers.logistique.persistance.gs.entite;

import java.io.Serializable;
import java.util.Calendar;

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

import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleEntite;
import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;

/**
 * The Class EntiteStockEntite.
 * @author mohamed
 */
@Entity
@Table(name=IConstante.TABLE_ENTITE_STOCK)
public class EntiteStockEntite implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7299516575537789538L;

	/** The id. */
	@Id
	@SequenceGenerator(name="GS_ENTITESTOCK_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_ENTITE_STOCK)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GS_ENTITESTOCK_ID_GENERATOR")
	private Long id;

	/** The cone reserve. */
	@Column(name="cone_reserve")
	private Long coneReserve;

	/** The cones actuel. */
	@Column(name="cones_actuel")
	private Long conesActuel;

	/** The date entree. */
	@Column(name="date_entree")
	private Calendar dateEntree;

	/** The date lot. */
	@Column(name="date_lot")
	private Calendar dateLot;

	/** The emplacement. */
	@Column(name="emplacement")
	private String emplacement;

	/** The fincone actuel. */
	@Column(name="fincone_actuel")
	private Long finconeActuel;

	/** The fincone reserve. */
	@Column(name="fincone_reserve")
	private Long finconeReserve;

	/** The libelle article. */
	@Column(name="libelle_article")
	private String libelleArticle;

	/** The poids actuel. */
	@Column(name="poids_actuel")
	private Double poidsActuel;

	/** The poids reserve. */
	@Column(name="poids_reserve")
	private Double poidsReserve;

	/** The qte actuelle. */
	@Column(name="qte_actuelle")
	private Double qteActuelle;

	/** The qte resrvee. */
	@Column(name="qte_reservee")
	private Double qteResrvee;

	/** The reference article. */
	@Column(name="reference_article")
	private String referenceArticle;

	/** The reference contenaire. */
	@Column(name="reference_contenaire")
	private String referenceContenaire;

	/** The reference lot. */
	@Column(name="reference_lot")
	private String referenceLot;

	/** The rouleaux actuel. */
	@Column(name="rouleaux_actuel")
	private Long rouleauxActuel;

	/** The rouleaux reserve. */
	@Column(name="rouleaux_reserve")
	private Long rouleauxReserve;
	
	@Column(name="equivalence_cone")
	private Double equivalenceCone;
	
	@Column(name="prix_unitaire")
	private Double prixUnitaire;
	
	@Column(name="pmp")
	private Double pmp;
	
	
	
	//bi-directional many-to-one association to EbArticle
	/** The article. */
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="eb_article_id")
	private ArticleEntite article;

	//bi-directional many-to-one association to GsMagasin
	/** The magasin. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gs_magasin_id")
	private MagasinEntite magasin;

//	//bi-directional many-to-one association to GsMouvement
//	/** The mouvements. */
//	@OneToMany(mappedBy="entiteStock")
//	private Set<MouvementEntite> mouvements;

	/** The bl suppression. */
	@Column(name="bl_suppression")
	private Boolean blSuppression;
	
	/** The date creation. */
	@Column(name="date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name="date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	
	
	
	/** The numero_bon_entree. */
	@Column(name = "numero_bon_entree")
	private String numeroBonEntree;
	
	
	

	/** The qte actuelle. */
	@Column(name="qte_entree")
	private Double qteEntree;

	
	
	

	public Double getQteEntree() {
		return qteEntree;
	}

	public void setQteEntree(Double qteEntree) {
		this.qteEntree = qteEntree;
	}

	public String getNumeroBonEntree() {
		return numeroBonEntree;
	}

	public void setNumeroBonEntree(String numeroBonEntree) {
		this.numeroBonEntree = numeroBonEntree;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getConeReserve() {
		return coneReserve;
	}

	public void setConeReserve(Long coneReserve) {
		this.coneReserve = coneReserve;
	}

	public Long getConesActuel() {
		return conesActuel;
	}

	public void setConesActuel(Long conesActuel) {
		this.conesActuel = conesActuel;
	}

	public Calendar getDateEntree() {
		return dateEntree;
	}

	/**
	 * Sets the date entree.
	 *
	 * @param dateEntree the new date entree
	 */
	public void setDateEntree(Calendar dateEntree) {
		this.dateEntree = dateEntree;
	}

	/**
	 * Gets the date lot.
	 *
	 * @return the date lot
	 */
	public Calendar getDateLot() {
		return dateLot;
	}

	/**
	 * Sets the date lot.
	 *
	 * @param dateLot the new date lot
	 */
	public void setDateLot(Calendar dateLot) {
		this.dateLot = dateLot;
	}

	/**
	 * Gets the emplacement.
	 *
	 * @return the emplacement
	 */
	public String getEmplacement() {
		return emplacement;
	}

	/**
	 * Sets the emplacement.
	 *
	 * @param emplacement the new emplacement
	 */
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	
	public Long getFinconeActuel() {
		return finconeActuel;
	}

	public void setFinconeActuel(Long finconeActuel) {
		this.finconeActuel = finconeActuel;
	}

	public Long getFinconeReserve() {
		return finconeReserve;
	}

	public void setFinconeReserve(Long finconeReserve) {
		this.finconeReserve = finconeReserve;
	}

	/**
	 * Gets the libelle article.
	 *
	 * @return the libelle article
	 */
	public String getLibelleArticle() {
		return libelleArticle;
	}

	/**
	 * Sets the libelle article.
	 *
	 * @param libelleArticle the new libelle article
	 */
	public void setLibelleArticle(String libelleArticle) {
		this.libelleArticle = libelleArticle;
	}

	/**
	 * Gets the poids actuel.
	 *
	 * @return the poids actuel
	 */
	public Double getPoidsActuel() {
		return poidsActuel;
	}

	/**
	 * Sets the poids actuel.
	 *
	 * @param poidsActuel the new poids actuel
	 */
	public void setPoidsActuel(Double poidsActuel) {
		this.poidsActuel = poidsActuel;
	}

	/**
	 * Gets the poids reserve.
	 *
	 * @return the poids reserve
	 */
	public Double getPoidsReserve() {
		return poidsReserve;
	}

	/**
	 * Sets the poids reserve.
	 *
	 * @param poidsReserve the new poids reserve
	 */
	public void setPoidsReserve(Double poidsReserve) {
		this.poidsReserve = poidsReserve;
	}

	/**
	 * Gets the qte actuelle.
	 *
	 * @return the qte actuelle
	 */
	public Double getQteActuelle() {
		return qteActuelle;
	}

	/**
	 * Sets the qte actuelle.
	 *
	 * @param qteActuelle the new qte actuelle
	 */
	public void setQteActuelle(Double qteActuelle) {
		this.qteActuelle = qteActuelle;
	}

	/**
	 * Gets the qte resrvee.
	 *
	 * @return the qte resrvee
	 */
	public Double getQteResrvee() {
		return qteResrvee;
	}

	/**
	 * Sets the qte resrvee.
	 *
	 * @param qteResrvee the new qte resrvee
	 */
	public void setQteResrvee(Double qteResrvee) {
		this.qteResrvee = qteResrvee;
	}

	/**
	 * Gets the reference article.
	 *
	 * @return the reference article
	 */
	public String getReferenceArticle() {
		return referenceArticle;
	}

	/**
	 * Sets the reference article.
	 *
	 * @param referenceArticle the new reference article
	 */
	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}

	/**
	 * Gets the reference contenaire.
	 *
	 * @return the reference contenaire
	 */
	public String getReferenceContenaire() {
		return referenceContenaire;
	}

	/**
	 * Sets the reference contenaire.
	 *
	 * @param referenceContenaire the new reference contenaire
	 */
	public void setReferenceContenaire(String referenceContenaire) {
		this.referenceContenaire = referenceContenaire;
	}

	/**
	 * Gets the reference lot.
	 *
	 * @return the reference lot
	 */
	public String getReferenceLot() {
		return referenceLot;
	}

	/**
	 * Sets the reference lot.
	 *
	 * @param referenceLot the new reference lot
	 */
	public void setReferenceLot(String referenceLot) {
		this.referenceLot = referenceLot;
	}

	/**
	 * Gets the article.
	 *
	 * @return the article
	 */


	/**
	 * Gets the magasin.
	

	public Long getArticle() {
		return article;
	}

	public void setArticle(Long article) {
		this.article = article;
	}

   



	public Long getArticle() {
		return article;
	}

	public void setArticle(Long article) {
		this.article = article;
	}

	public Long getMagasin() {
		return magasin;
	}

	public void setMagasin(Long magasin) {
		this.magasin = magasin;
	}

	

	/**
	 * Gets the bl suppression.
	 *
	 * @return the bl suppression
	 */
	public Boolean getBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression the new bl suppression
	 */
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Long getRouleauxActuel() {
		return rouleauxActuel;
	}

	public void setRouleauxActuel(Long rouleauxActuel) {
		this.rouleauxActuel = rouleauxActuel;
	}

	public Long getRouleauxReserve() {
		return rouleauxReserve;
	}

	public void setRouleauxReserve(Long rouleauxReserve) {
		this.rouleauxReserve = rouleauxReserve;
	}

	public Double getEquivalenceCone() {
		return equivalenceCone;
	}

	public void setEquivalenceCone(Double equivalenceCone) {
		this.equivalenceCone = equivalenceCone;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getPmp() {
		return pmp;
	}

	public void setPmp(Double pmp) {
		this.pmp = pmp;
	}
    

	public ArticleEntite getArticle() {
		return article;
	}

	public void setArticle(ArticleEntite article) {
		this.article = article;
	}

	/**
	 * @return the magasin
	 */
	public MagasinEntite getMagasin() {
		return magasin;
	}

	/**
	 * @param magasin the magasin to set
	 */
	public void setMagasin(MagasinEntite magasin) {
		this.magasin = magasin;
	}

	@Override
	public String toString() {
		return "EntiteStockEntite [id=" + id + ", emplacement=" + emplacement + ", libelleArticle=" + libelleArticle
				+ ", referenceArticle=" + referenceArticle + ", referenceLot=" + referenceLot + ", pmp=" + pmp
				+ ", article=" + article + "]";
	}

	

	


}