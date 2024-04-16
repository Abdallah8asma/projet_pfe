package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.ArrayList;
import java.util.List;

/**
 * Résultat de recherche 
 * @author rkhaskho
 *
 */
public class ResultatRechecheRouleauStandardValue {
  
  /** Liste des objets retournées */

  List <  ElementResultatRechecheRouleauStandardValue > listeElementResultatRechecheRouleauStandardValue = new ArrayList < ElementResultatRechecheRouleauStandardValue >();

  /**
   * @return the listeElementResultatRechecheRouleauStandardValue
   */
  public List < ElementResultatRechecheRouleauStandardValue > getListeElementResultatRechecheRouleauStandardValue() {
    return listeElementResultatRechecheRouleauStandardValue;
  }

  /**
   * @param listeElementResultatRechecheRouleauStandardValue the listeElementResultatRechecheRouleauStandardValue to set
   */
  public void setListeElementResultatRechecheRouleauStandardValue(
    List < ElementResultatRechecheRouleauStandardValue > listeElementResultatRechecheRouleauStandardValue) {
    this.listeElementResultatRechecheRouleauStandardValue = listeElementResultatRechecheRouleauStandardValue;
  }

 
}
