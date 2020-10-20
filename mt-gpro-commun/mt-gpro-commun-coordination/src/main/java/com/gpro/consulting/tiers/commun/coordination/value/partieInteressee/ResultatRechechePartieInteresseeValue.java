package com.gpro.consulting.tiers.commun.coordination.value.partieInteressee;

import java.util.List;

public class ResultatRechechePartieInteresseeValue {

  private Long nombreResultaRechercher;

  private List < PartieInteresseValue > partieInteresseValues;

  /**
   * Accesseur en lecture de l'attribut <code>nombreResultaRechercher</code>.
   * 
   * @return Long L'attribut nombreResultaRechercher à lire.
   */
  public Long getNombreResultaRechercher() {
    return nombreResultaRechercher;
  }

  /**
   * Accesseur en écriture de l'attribut <code>nombreResultaRechercher</code>.
   *
   * @param nombreResultaRechercher
   *          L'attribut nombreResultaRechercher à modifier.
   */
  public void setNombreResultaRechercher(Long nombreResultaRechercher) {
    this.nombreResultaRechercher = nombreResultaRechercher;
  }

  /**
   * Accesseur en lecture de l'attribut <code>partieInteresseValues</code>.
   * 
   * @return List<PartieInteresseValue> L'attribut partieInteresseValues à lire.
   */
  public List < PartieInteresseValue > getPartieInteresseValues() {
    return partieInteresseValues;
  }

  /**
   * Accesseur en écriture de l'attribut <code>partieInteresseValues</code>.
   *
   * @param partieInteresseValues
   *          L'attribut partieInteresseValues à modifier.
   */
  public void setPartieInteresseValues(List < PartieInteresseValue > partieInteresseValues) {
    this.partieInteresseValues = partieInteresseValues;
  }

}
