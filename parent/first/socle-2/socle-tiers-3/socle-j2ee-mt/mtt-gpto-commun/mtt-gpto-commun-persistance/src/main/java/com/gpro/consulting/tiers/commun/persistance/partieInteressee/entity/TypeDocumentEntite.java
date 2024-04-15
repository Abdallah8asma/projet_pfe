package com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

/**
 * The Class TypeDocumentEntite.
 * 
 * @author $mohamed: $
 */
@Entity
@Table(name = IConstante.TABLE_TYPE_DOCUMENT)
public class TypeDocumentEntite implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -4283850258275886880L;

  /** Identifiant technique. */
  /** L'id de la table. */

  @Id
  @SequenceGenerator(name = "TYPE_DOCUMENT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_TYPE_DOCUMENT, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TYPE_DOCUMENT_ID_GENERATOR")
  private Long id;

  /** The designation. */
  @Column(name = "desingation") 
  private String designation;

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
  
  //@Column(name = "module")
  private String module;

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  public String getModule() {
	return module;
}

public void setModule(String module) {
	this.module = module;
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
   * Gets the designation.
   *
   * @return the designation
   */
  public String getDesignation() {
    return designation;
  }

  /**
   * Sets the designation.
   *
   * @param designation
   *          the new designation
   */
  public void setDesignation(String designation) {
    this.designation = designation;
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
   * Checks if is bl suppression.
   *
   * @return true, if is bl suppression
   */
  public boolean isBlSuppression() {
    return blSuppression;
  }

  /**
   * Sets the bl suppression.
   *
   * @param blSuppression
   *          the new bl suppression
   */
  public void setBlSuppression(boolean blSuppression) {
    this.blSuppression = blSuppression;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

}
