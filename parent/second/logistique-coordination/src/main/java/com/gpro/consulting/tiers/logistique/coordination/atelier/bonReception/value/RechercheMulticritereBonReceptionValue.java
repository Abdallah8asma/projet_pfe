package com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value;

import java.util.Calendar;

/**
 * Critére de recherche d'un bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public class RechercheMulticritereBonReceptionValue {

  /** Client */
  private Long client;

  /** Date Introduction */
  private Calendar dateIntroduction;
  
  private Long produitId;

/**
   * Accesseur en lecture de l'attribut <code>client</code>.
   * 
   * @return Long L'attribut client à lire.
   */
  public Long getClient() {
    return client;
  }

  /**
   * Accesseur en écriture de l'attribut <code>client</code>.
   *
   * @param client
   *          L'attribut client à modifier.
   */
  public void setClient(Long client) {
    this.client = client;
  }

  /**
   * Accesseur en lecture de l'attribut <code>dateIntroduction</code>.
   * 
   * @return Calendar L'attribut dateIntroduction à lire.
   */
  public Calendar getDateIntroduction() {
    return dateIntroduction;
  }

  /**
   * Accesseur en écriture de l'attribut <code>dateIntroduction</code>.
   *
   * @param dateIntroduction
   *          L'attribut dateIntroduction à modifier.
   */
  public void setDateIntroduction(Calendar dateIntroduction) {
    this.dateIntroduction = dateIntroduction;
  }

  
	public Long getProduitId() {
		return produitId;
	}
	
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
}
