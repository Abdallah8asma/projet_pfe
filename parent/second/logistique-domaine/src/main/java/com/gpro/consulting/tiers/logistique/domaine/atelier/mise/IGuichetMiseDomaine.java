package com.gpro.consulting.tiers.logistique.domaine.atelier.mise;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.GuichetMiseValue;


/**
 * Générateur de référence du Mise
 * @author rkhaskho
 *
 */
public interface IGuichetMiseDomaine {

	
	  /**
	   * Méthode de récupération du numéro du mise dans la persistance.
	   * 
	   * @return le numéro du prochain mise 
	   */
	  public Long getNextNumReference();

	  /**
	   * Méthode de modification d'un numéro de référence dans le guichet du mise pour l'année
	   * courante..
	   * @param pGuichetValeur le guichet à modifier
	   * @return l'année du guichet
	   */
	  public Long modifierGuichetMise(GuichetMiseValue pGuichetValeur);


}
