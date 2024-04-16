/**
 * 
 */
package com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * @author Ameni
 *
 */
@Entity
@Table(name = IConstanteLogistique.TABLE_ROULEAUECRU)
public class RouleauEcruEntity implements Serializable {
	private static final long serialVersionUID = -6182559328728507328L;

@Id
  @SequenceGenerator(name = "ROULEAUECRU_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_ROULEAUECRU, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROULEAUECRU_ID_GENERATOR")
  private Long id;

  /** The reference. */
  @Column(name = "reference")
  private String reference;

  /** The bonReceptionId. */
  @ManyToOne
  @JoinColumn(name = "gl_bonreception_id")
  private BonReceptionEntity bonReception;

  /** The metrageTrouve. */
  @Column(name = "metrage_trouv")
  private Long metrageTrouve;

  /** The metrageAnnonce. */
  @Column(name = "metrage_annonce")
  private Long metrageAnnonce;

  /** The poidsTrouve. */
  @Column(name = "poids_trouv")
  private Long poidsTrouve;

  /** The poidsAnnonce. */
  @Column(name = "poids_annonce")
  private Long poidsAnnonce;

  /** The bl suppression. */
  @Column(name = "bl_suppression")
  private boolean blSuppression;

  /** The date suppression. */
  @Column(name = "date_suppression")
  private Calendar dateSuppression;

  /** The date creation. */
  @Column(name = "date_creation")
  private Calendar dateCreation;

  /** The date modification. */
  @Column(name = "date_modification")
  private Calendar dateModification;

  /** Version */
  @Column(name = "version")
  private String version;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the reference
   */
  public String getReference() {
    return reference;
  }

  /**
   * @param reference
   *          the reference to set
   */
  public void setReference(String reference) {
    this.reference = reference;
  }

  /**
   * Accesseur en lecture de l'attribut <code>bonReception</code>.
   * 
   * @return BonReceptionEntity L'attribut bonReception à lire.
   */
  public BonReceptionEntity getBonReception() {
    return bonReception;
  }

  /**
   * Accesseur en écriture de l'attribut <code>bonReception</code>.
   *
   * @param bonReception
   *          L'attribut bonReception à modifier.
   */
  public void setBonReception(BonReceptionEntity bonReception) {
    this.bonReception = bonReception;
  }

  /**
   * @return the metrageTrouve
   */
  public Long getMetrageTrouve() {
    return metrageTrouve;
  }

  /**
   * @param metrageTrouve
   *          the metrageTrouve to set
   */
  public void setMetrageTrouve(Long metrageTrouve) {
    this.metrageTrouve = metrageTrouve;
  }

  /**
   * @return the metrageAnnonce
   */
  public Long getMetrageAnnonce() {
    return metrageAnnonce;
  }

  /**
   * @param metrageAnnonce
   *          the metrageAnnonce to set
   */
  public void setMetrageAnnonce(Long metrageAnnonce) {
    this.metrageAnnonce = metrageAnnonce;
  }

  /**
   * @return the poidsTrouve
   */
  public Long getPoidsTrouve() {
    return poidsTrouve;
  }

  /**
   * @param poidsTrouve
   *          the poidsTrouve to set
   */
  public void setPoidsTrouve(Long poidsTrouve) {
    this.poidsTrouve = poidsTrouve;
  }

  /**
   * @return the poidsAnnonce
   */
  public Long getPoidsAnnonce() {
    return poidsAnnonce;
  }

  /**
   * @param poidsAnnonce
   *          the poidsAnnonce to set
   */
  public void setPoidsAnnonce(Long poidsAnnonce) {
    this.poidsAnnonce = poidsAnnonce;
  }

  /**
   * @return the blSuppression
   */
  public boolean isBlSuppression() {
    return blSuppression;
  }

  /**
   * @param blSuppression
   *          the blSuppression to set
   */
  public void setBlSuppression(boolean blSuppression) {
    this.blSuppression = blSuppression;
  }

  /**
   * @return the dateSuppression
   */
  public Calendar getDateSuppression() {
    return dateSuppression;
  }

  /**
   * @param dateSuppression
   *          the dateSuppression to set
   */
  public void setDateSuppression(Calendar dateSuppression) {
    this.dateSuppression = dateSuppression;
  }

  /**
   * @return the dateCreation
   */
  public Calendar getDateCreation() {
    return dateCreation;
  }

  /**
   * @param dateCreation
   *          the dateCreation to set
   */
  public void setDateCreation(Calendar dateCreation) {
    this.dateCreation = dateCreation;
  }

  /**
   * @return the dateModification
   */
  public Calendar getDateModification() {
    return dateModification;
  }

  /**
   * @param dateModification
   *          the dateModification to set
   */
  public void setDateModification(Calendar dateModification) {
    this.dateModification = dateModification;
  }

  /**
   * @return the version
   */
  public String getVersion() {
    return version;
  }

  /**
   * @param version
   *          the version to set
   */
  public void setVersion(String version) {
    this.version = version;
  }

}
