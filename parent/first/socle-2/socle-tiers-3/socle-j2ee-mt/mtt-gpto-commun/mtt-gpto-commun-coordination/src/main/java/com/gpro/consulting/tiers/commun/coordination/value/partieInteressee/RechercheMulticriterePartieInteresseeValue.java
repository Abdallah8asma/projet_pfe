package com.gpro.consulting.tiers.commun.coordination.value.partieInteressee;

import java.util.Calendar;

public class RechercheMulticriterePartieInteresseeValue {

  /** The ref. */
  private String vReference;

  /** The raison sociale. */
  private String vRaisonSociale;

  /** The famille entite. */
  private String vFamillePartieInteressee;

  /** The categorie entite. */
  private String vCategoriePartieInteressee;

  /** Type Partie Interessee **/
  private String vTypePartieInteressee;

  /** Activité Partie Interessee **/
  private String vActivite;

  private String actif;
  
  
  private String passager;
  
  
  private Long groupeClientId;
  
  private Long boutiqueId;
  
  
	/** The telephone mobile. */

	private String telephoneMobile;
	
	/** The payement terme. */

	private String payementTerme;
	
	
	
	private String nature;
  
	private Long deviseId;

	

	private Calendar dateIntroductionDe;
	private Calendar dateIntroductionA;
	
	
	


public Calendar getDateIntroductionDe() {
		return dateIntroductionDe;
	}

	public void setDateIntroductionDe(Calendar dateIntroductionDe) {
		this.dateIntroductionDe = dateIntroductionDe;
	}

	public Calendar getDateIntroductionA() {
		return dateIntroductionA;
	}

	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}

public Long getDeviseId() {
		return deviseId;
	}

	public void setDeviseId(Long deviseId) {
		this.deviseId = deviseId;
	}

public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

public String getTelephoneMobile() {
		return telephoneMobile;
	}

	public void setTelephoneMobile(String telephoneMobile) {
		this.telephoneMobile = telephoneMobile;
	}

	public String getPayementTerme() {
		return payementTerme;
	}

	public void setPayementTerme(String payementTerme) {
		this.payementTerme = payementTerme;
	}

public Long getBoutiqueId() {
	return boutiqueId;
}

public void setBoutiqueId(Long boutiqueId) {
	this.boutiqueId = boutiqueId;
}

public String getPassager() {
	return passager;
}

public void setPassager(String passager) {
	this.passager = passager;
}

/**
   * @return the reference
   */

  public Long getGroupeClientId() {
	return groupeClientId;
}

public void setGroupeClientId(Long groupeClientId) {
	this.groupeClientId = groupeClientId;
}

/**
   * Accesseur en lecture de l'attribut <code>vReference</code>.
   * 
   * @return String L'attribut vReference à lire.
   */
  public String getvReference() {
    return vReference;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vReference</code>.
   *
   * @param vReference
   *          L'attribut vReference à modifier.
   */
  public void setvReference(String vReference) {
    this.vReference = vReference;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vRaisonSociale</code>.
   * 
   * @return String L'attribut vRaisonSociale à lire.
   */
  public String getvRaisonSociale() {
    return vRaisonSociale;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vRaisonSociale</code>.
   *
   * @param vRaisonSociale
   *          L'attribut vRaisonSociale à modifier.
   */
  public void setvRaisonSociale(String vRaisonSociale) {
    this.vRaisonSociale = vRaisonSociale;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vFamillePartieInteressee</code>.
   * 
   * @return String L'attribut vFamillePartieInteressee à lire.
   */
  public String getvFamillePartieInteressee() {
    return vFamillePartieInteressee;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vFamillePartieInteressee</code>.
   *
   * @param vFamillePartieInteressee
   *          L'attribut vFamillePartieInteressee à modifier.
   */
  public void setvFamillePartieInteressee(String vFamillePartieInteressee) {
    this.vFamillePartieInteressee = vFamillePartieInteressee;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vCategoriePartieInteressee</code>.
   * 
   * @return String L'attribut vCategoriePartieInteressee à lire.
   */
  public String getvCategoriePartieInteressee() {
    return vCategoriePartieInteressee;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vCategoriePartieInteressee</code>.
   *
   * @param vCategoriePartieInteressee
   *          L'attribut vCategoriePartieInteressee à modifier.
   */
  public void setvCategoriePartieInteressee(String vCategoriePartieInteressee) {
    this.vCategoriePartieInteressee = vCategoriePartieInteressee;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vTypePartieInteressee</code>.
   * 
   * @return String L'attribut vTypePartieInteressee à lire.
   */
  public String getvTypePartieInteressee() {
    return vTypePartieInteressee;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vTypePartieInteressee</code>.
   *
   * @param vTypePartieInteressee
   *          L'attribut vTypePartieInteressee à modifier.
   */
  public void setvTypePartieInteressee(String vTypePartieInteressee) {
    this.vTypePartieInteressee = vTypePartieInteressee;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vActivite</code>.
   * 
   * @return String L'attribut vActivite à lire.
   */
  public String getvActivite() {
    return vActivite;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vActivite</code>.
   *
   * @param vActivite
   *          L'attribut vActivite à modifier.
   */
  public void setvActivite(String vActivite) {
    this.vActivite = vActivite;
  }

		public String getActif() {
			return actif;
		}
		
		public void setActif(String actif) {
			this.actif = actif;
		}

}
