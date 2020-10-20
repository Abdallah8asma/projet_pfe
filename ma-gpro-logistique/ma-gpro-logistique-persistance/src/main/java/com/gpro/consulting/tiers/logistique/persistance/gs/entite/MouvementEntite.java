package com.gpro.consulting.tiers.logistique.persistance.gs.entite;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
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

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;


/**
 * The Class MouvementEntite.
 * 
 * @author mohamed
 */
@Entity
@Table(name = IConstante.TABLE_MOUVEMENT_STOCK)
public class MouvementEntite implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1734048583793616696L;

  /** The id. */
  @Id
  @SequenceGenerator(name = "GS_MOUVEMENT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_MOUVEMENT_STOCK)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GS_MOUVEMENT_ID_GENERATOR")
  private Long id;

  /** The cones. */
  @Column(name = "cones")
  private Long cones;

  /** The cones reel. */
  @Column(name = "cones_reel")
  private Long conesReel;

  /** The description. */
  @Column(name = "description")
  private String description;

  /** The fin cones. */
  @Column(name = "fincones")
  private Long finCones;

  /** The fin cones reel. */
  @Column(name = "fincones_reel")
  private Long finConesReel;

  /** The nb rouleaux. */
  @Column(name = "nb_rouleaux")
  private Long nbRouleaux;

  /** The nb rouleaux reel. */
  @Column(name = "nb_rouleaux_reel")
  private Long nbRouleauxReel;

  /** The poids. */
  @Column(name = "poids")
  private Double poids;

  /** The poids reel. */
  @Column(name = "poids_reel")
  private Double poidsReel;

  /** The quantite. */
  @Column(name = "quantite")
  private Double quantite;

  /** The quantite reelle. */
  @Column(name = "quantite_reelle")
  private Double quantiteReelle;

  /** The type. */
  @Column(name = "type")
  private String type;

  @Column(name = "details_mvt")
  private String detailsMouvement;

  // bi-directional many-to-one association to GsBonmouvement
  /** The bon mouvement. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "gs_bonmouvement_id")
  private BonMouvementEntite bonMouvement;

  // bi-directional many-to-one association to GsEntitestock
  /** The entite stock. */
  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinColumn(name = "gs_entitestock_id")
  private EntiteStockEntite entiteStock;

  @Column(name = "prix_unitaire")
  private Double prixUnitaire;

  @Column(name = "emplacement")
  private String emplacement;

  @Column(name = "affichage")
  private Boolean affichage;

  
  /** The bl suppression. */
  @Column(name = "bl_suppression")
  private Boolean blSuppression;

  /** The date creation. */
  @Column(name = "date_creation")
  private Calendar dateCreation;

  /** The date modification. */
  @Column(name = "date_modification")
  private Calendar dateModification;

  /** The date suppression. */
  @Column(name = "date_suppression")
  private Calendar dateSuppression;

//added on 13/04/2016, by Ameni Berrich
  /** The lot. */
  private String lot;
  
//added on 08/11/2016, by Zeineb
/**
 * Qte OF
 */
  @Column(name = "qte_of")
  private Long qteOF;
  
//added on 08/11/2016, by Zeineb
/**
 * Besoin OF
 */
  @Column(name = "besoin_of")
  private Double besoinOF;
  
  
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
   * @param id
   *          the new id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the cones.
   *
   * @return the cones
   */
  public Long getCones() {
    return cones;
  }

  /**
   * Sets the cones.
   *
   * @param cones
   *          the new cones
   */
  public void setCones(Long cones) {
    this.cones = cones;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description
   *          the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  public Long getConesReel() {
    return conesReel;
  }

  public void setConesReel(Long conesReel) {
    this.conesReel = conesReel;
  }

  public Long getFinCones() {
    return finCones;
  }

  public void setFinCones(Long finCones) {
    this.finCones = finCones;
  }

  /**
   * Gets the fin cones reel.
   *
   * @return the fin cones reel
   */
  public Long getFinConesReel() {
    return finConesReel;
  }

  /**
   * Sets the fin cones reel.
   *
   * @param finConesReel
   *          the new fin cones reel
   */
  public void setFinConesReel(Long finConesReel) {
    this.finConesReel = finConesReel;
  }

  /**
   * Gets the nb rouleaux.
   *
   * @return the nb rouleaux
   */
  public Long getNbRouleaux() {
    return nbRouleaux;
  }

  /**
   * Sets the nb rouleaux.
   *
   * @param nbRouleaux
   *          the new nb rouleaux
   */
  public void setNbRouleaux(Long nbRouleaux) {
    this.nbRouleaux = nbRouleaux;
  }

  /**
   * Gets the nb rouleaux reel.
   *
   * @return the nb rouleaux reel
   */
  public Long getNbRouleauxReel() {
    return nbRouleauxReel;
  }

  /**
   * Sets the nb rouleaux reel.
   *
   * @param nbRouleauxReel
   *          the new nb rouleaux reel
   */
  public void setNbRouleauxReel(Long nbRouleauxReel) {
    this.nbRouleauxReel = nbRouleauxReel;
  }

  /**
   * Gets the poids.
   *
   * @return the poids
   */
  public Double getPoids() {
    return poids;
  }

  /**
   * Sets the poids.
   *
   * @param poids
   *          the new poids
   */
  public void setPoids(Double poids) {
    this.poids = poids;
  }

  /**
   * Gets the poids reel.
   *
   * @return the poids reel
   */
  public Double getPoidsReel() {
    return poidsReel;
  }

  /**
   * Sets the poids reel.
   *
   * @param poidsReel
   *          the new poids reel
   */
  public void setPoidsReel(Double poidsReel) {
    this.poidsReel = poidsReel;
  }

  /**
   * Gets the quantite.
   *
   * @return the quantite
   */
  public Double getQuantite() {
    return quantite;
  }

  /**
   * Sets the quantite.
   *
   * @param quantite
   *          the new quantite
   */
  public void setQuantite(Double quantite) {
    this.quantite = quantite;
  }

  /**
   * Gets the quantite reelle.
   *
   * @return the quantite reelle
   */
  public Double getQuantiteReelle() {
    return quantiteReelle;
  }

  /**
   * Sets the quantite reelle.
   *
   * @param quantiteReelle
   *          the new quantite reelle
   */
  public void setQuantiteReelle(Double quantiteReelle) {
    this.quantiteReelle = quantiteReelle;
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type
   *          the new type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the bon mouvement.
   *
   * @return the bon mouvement
   */
  public BonMouvementEntite getBonMouvement() {
    return bonMouvement;
  }

  /**
   * Sets the bon mouvement.
   *
   * @param bonMouvement
   *          the new bon mouvement
   */
  public void setBonMouvement(BonMouvementEntite bonMouvement) {
    this.bonMouvement = bonMouvement;
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
   * @param blSuppression
   *          the new bl suppression
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
   * @param dateCreation
   *          the new date creation
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
   * @param dateModification
   *          the new date modification
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
   * @param dateSuppression
   *          the new date suppression
   */
  public void setDateSuppression(Calendar dateSuppression) {
    this.dateSuppression = dateSuppression;
  }

  public String getDetailsMouvement() {
    return detailsMouvement;
  }

  public void setDetailsMouvement(String detailsMouvement) {
    this.detailsMouvement = detailsMouvement;
  }

  public EntiteStockEntite getEntiteStock() {
    return entiteStock;
  }

  public void setEntiteStock(EntiteStockEntite entiteStock) {
    this.entiteStock = entiteStock;
  }

  public Double getPrixUnitaire() {
    return prixUnitaire;
  }

  public void setPrixUnitaire(Double prixUnitaire) {
    this.prixUnitaire = prixUnitaire;
  }

  public String getEmplacement() {
    return emplacement;
  }

  public void setEmplacement(String emplacement) {
    this.emplacement = emplacement;
  }

/**
 * @return the affichage
 */
public Boolean getAffichage() {
	return affichage;
}

/**
 * @param affichage the affichage to set
 */
public void setAffichage(Boolean affichage) {
	this.affichage = affichage;
}

public String getLot() {
	return lot;
}

public void setLot(String lot) {
	this.lot = lot;
}

public Long getQteOF() {
	return qteOF;
}

public void setQteOF(Long qteOF) {
	this.qteOF = qteOF;
}

public Double getBesoinOF() {
	return besoinOF;
}

public void setBesoinOF(Double besoinOF) {
	this.besoinOF = besoinOF;
}


}