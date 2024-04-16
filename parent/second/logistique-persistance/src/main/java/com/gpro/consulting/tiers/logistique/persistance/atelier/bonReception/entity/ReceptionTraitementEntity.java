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
@Table(name = IConstanteLogistique.TABLE_RECEPTIONTRRAITEMENT)
public class ReceptionTraitementEntity implements Serializable {
	private static final long serialVersionUID = -5027852158376453278L;

@Id
  @SequenceGenerator(name = "RECEPTIONTRRAITEMENT_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_RECEPTIONTRAITEMENT, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEPTIONTRRAITEMENT_ID_GENERATOR")
  private Long id;

  /** The traitementId. */
  @Column(name = "gl_traitement_id")
  private Long traitementId;

  /** The bonReceptionId. */
  @ManyToOne
  @JoinColumn(name = "gl_bonreception_id")
  private BonReceptionEntity bonReception;

  /** The machineId. */
  @Column(name = "gl_machine_id")
  private Long machineId;

  /** The observations. */
  @Column(name = "observations")
  private String observations;

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
   * @return the traitementId
   */
  public Long getTraitementId() {
    return traitementId;
  }

  /**
   * @param traitementId
   *          the traitementId to set
   */
  public void setTraitementId(Long traitementId) {
    this.traitementId = traitementId;
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
   * @return the machineId
   */
  public Long getMachineId() {
    return machineId;
  }

  /**
   * @param machineId
   *          the machineId to set
   */
  public void setMachineId(Long machineId) {
    this.machineId = machineId;
  }

  /**
   * @return the observations
   */
  public String getObservations() {
    return observations;
  }

  /**
   * @param observations
   *          the observations to set
   */
  public void setObservations(String observations) {
    this.observations = observations;
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
