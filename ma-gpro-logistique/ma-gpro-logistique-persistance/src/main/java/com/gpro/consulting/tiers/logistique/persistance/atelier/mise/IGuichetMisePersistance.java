package com.gpro.consulting.tiers.logistique.persistance.atelier.mise;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.GuichetMiseValue;


/**
 * Générateur de référence de mise
 * @author rkhaskho
 *
 */
public interface IGuichetMisePersistance {

	
	  /**
	   * Méthode de récupération du numéro du misedans la persistance.
	   * 
	   * @return le numéro du prochain mise
	   */
	  public Long getNextNumReference();

	  /**
	   * Méthode de modification d'un numéro de référence dans le guichet du misepour l'année
	   * courante..
	   * @param pGuichetValeur le guichet à modifier
	   * @return l'année du guichet
	   */
	  public Long modifierGuichetMise(GuichetMiseValue pGuichetValeur);


}
