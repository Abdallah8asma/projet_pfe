package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheArticleValue {

  private Long nombreResultaRechercher;

  private Set<ArticleValue> articleValues = new TreeSet<ArticleValue>();

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
   * Accesseur en lecture de l'attribut <code>articleValues</code>.
   * 
   * @return List<ArticleValue> L'attribut articleValues à lire.
   */
 
  public Set<ArticleValue> getArticleValues() {
		return articleValues;
	}
  
  /**
   * Accesseur en écriture de l'attribut <code>articleValues</code>.
   *
   * @param articleValues
   *          L'attribut partieInteresseValues à modifier.
   */
 
public void setArticleValues(Set<ArticleValue> articleValues) {
	this.articleValues = articleValues;
}
}
