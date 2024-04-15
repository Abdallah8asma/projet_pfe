package com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity;

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

import com.gpro.consulting.tiers.commun.coordination.IConstante;

// TODO: Auto-generated Javadoc
/**
 * The Class RepresantentEntite.
 * 
 * @author $mohamed: $
 */
@Entity
@Table(name = IConstante.TABLE_REPRESENTANT)
public class RepresentantEntite implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -2462183882144296936L;

  /** Identifiant technique. */
  /** L'id de la table. */

  @Id
  @SequenceGenerator(name = "REPRESENTENT_PARTIE_INTERESSE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_REPRESENTANT)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPRESENTENT_PARTIE_INTERESSE_ID_GENERATOR")
  private Long id;

  /** joincolumn partie interesse. */
  @ManyToOne
  @JoinColumn(name = "pi_partieint_id")
  private PartieInteresseEntite partieInteresse;

  /** The nom. */
  @Column(name = "nom")
  private String nom;

  /** The fonction. */
  @Column(name = "fonction")
  private String fonction;

  /** The email. */
  @Column(name = "email")
  private String email;

  /** The telephone. */
  @Column(name = "tel")
  private String telephone;

  /** The fax. */
  @Column(name = "fax")
  private String fax;

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

  /**
   * Accesseur en lecture de l'attribut <code>id</code>.
   * 
   * @return Long L'attribut id à lire.
   */
  public Long getId() {
    return id;
  }

  /**
   * Accesseur en écriture de l'attribut <code>id</code>.
   *
   * @param id
   *          L'attribut id à modifier.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Accesseur en lecture de l'attribut <code>partieInteresse</code>.
   * 
   * @return PartieInteresseEntite L'attribut partieInteresse à lire.
   */
  public PartieInteresseEntite getPartieInteresse() {
    return partieInteresse;
  }

  /**
   * Accesseur en écriture de l'attribut <code>partieInteresse</code>.
   *
   * @param partieInteresse
   *          L'attribut partieInteresse à modifier.
   */
  public void setPartieInteresse(PartieInteresseEntite partieInteresse) {
    this.partieInteresse = partieInteresse;
  }

  /**
   * Accesseur en lecture de l'attribut <code>nom</code>.
   * 
   * @return String L'attribut nom à lire.
   */
  public String getNom() {
    return nom;
  }

  /**
   * Accesseur en écriture de l'attribut <code>nom</code>.
   *
   * @param nom
   *          L'attribut nom à modifier.
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Accesseur en lecture de l'attribut <code>fonction</code>.
   * 
   * @return String L'attribut fonction à lire.
   */
  public String getFonction() {
    return fonction;
  }

  /**
   * Accesseur en écriture de l'attribut <code>fonction</code>.
   *
   * @param fonction
   *          L'attribut fonction à modifier.
   */
  public void setFonction(String fonction) {
    this.fonction = fonction;
  }

  /**
   * Accesseur en lecture de l'attribut <code>email</code>.
   * 
   * @return String L'attribut email à lire.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Accesseur en écriture de l'attribut <code>email</code>.
   *
   * @param email
   *          L'attribut email à modifier.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Accesseur en lecture de l'attribut <code>telephone</code>.
   * 
   * @return String L'attribut telephone à lire.
   */
  public String getTelephone() {
    return telephone;
  }

  /**
   * Accesseur en écriture de l'attribut <code>telephone</code>.
   *
   * @param telephone
   *          L'attribut telephone à modifier.
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  /**
   * Accesseur en lecture de l'attribut <code>fax</code>.
   * 
   * @return String L'attribut fax à lire.
   */
  public String getFax() {
    return fax;
  }

  /**
   * Accesseur en écriture de l'attribut <code>fax</code>.
   *
   * @param fax
   *          L'attribut fax à modifier.
   */
  public void setFax(String fax) {
    this.fax = fax;
  }

  /**
   * Accesseur en lecture de l'attribut <code>blSuppression</code>.
   * 
   * @return boolean L'attribut blSuppression à lire.
   */
  public boolean isBlSuppression() {
    return blSuppression;
  }

  /**
   * Accesseur en écriture de l'attribut <code>blSuppression</code>.
   *
   * @param blSuppression
   *          L'attribut blSuppression à modifier.
   */
  public void setBlSuppression(boolean blSuppression) {
    this.blSuppression = blSuppression;
  }

  /**
   * Accesseur en lecture de l'attribut <code>dateSuppression</code>.
   * 
   * @return Calendar L'attribut dateSuppression à lire.
   */
  public Calendar getDateSuppression() {
    return dateSuppression;
  }

  /**
   * Accesseur en écriture de l'attribut <code>dateSuppression</code>.
   *
   * @param dateSuppression
   *          L'attribut dateSuppression à modifier.
   */
  public void setDateSuppression(Calendar dateSuppression) {
    this.dateSuppression = dateSuppression;
  }

  /**
   * Accesseur en lecture de l'attribut <code>dateCreation</code>.
   * 
   * @return Calendar L'attribut dateCreation à lire.
   */
  public Calendar getDateCreation() {
    return dateCreation;
  }

  /**
   * Accesseur en écriture de l'attribut <code>dateCreation</code>.
   *
   * @param dateCreation
   *          L'attribut dateCreation à modifier.
   */
  public void setDateCreation(Calendar dateCreation) {
    this.dateCreation = dateCreation;
  }

  /**
   * Accesseur en lecture de l'attribut <code>dateModification</code>.
   * 
   * @return Calendar L'attribut dateModification à lire.
   */
  public Calendar getDateModification() {
    return dateModification;
  }

  /**
   * Accesseur en écriture de l'attribut <code>dateModification</code>.
   *
   * @param dateModification
   *          L'attribut dateModification à modifier.
   */
  public void setDateModification(Calendar dateModification) {
    this.dateModification = dateModification;
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
