package com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * Classe Valeur permettant le retour de la recheche d'un bon de reception
 * 
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public class ResultatRechecheBonReceptionValue {

  /** Liste des objets retournées */

	Set < ElementRechecheBonReceptionValue > listeElementRechecheBonReceptionValeur = new TreeSet < ElementRechecheBonReceptionValue >();
  
	/**
   * Accesseur en lecture de l'attribut <code>listeElementRechecheBonReceptionValeur</code>.
   * 
   * @return Set<ElementRechecheBonReceptionValeur> L'attribut
   *         listeElementRechecheBonReceptionValeur à lire.
   */
  public Set < ElementRechecheBonReceptionValue > getListeElementRechecheBonReceptionValeur() {
    return listeElementRechecheBonReceptionValeur;
  }

  /**
   * Accesseur en écriture de l'attribut <code>listeElementRechecheBonReceptionValeur</code>.
   *
   * @param listeElementRechecheBonReceptionValeur
   *          L'attribut listeElementRechecheBonReceptionValeur à modifier.
   */
  public void setListeElementRechecheBonReceptionValeur(
    Set < ElementRechecheBonReceptionValue > listeElementRechecheBonReceptionValeur) {
    this.listeElementRechecheBonReceptionValeur = listeElementRechecheBonReceptionValeur;
  }

}
