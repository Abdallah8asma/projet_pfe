package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.List;

/**
 * 
 * @author Zeineb Medimagh
 * @since 25/11/2016
 */

public class MouvementStockSupprime {
	
	private Long typeArticle;
	private List<MouvementStockSupprimeElement> listeElementsSupprimes;
	
	
	public Long getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(Long typeArticle) {
		this.typeArticle = typeArticle;
	}
	public List<MouvementStockSupprimeElement> getListeElementsSupprimes() {
		return listeElementsSupprimes;
	}
	public void setListeElementsSupprimes(List<MouvementStockSupprimeElement> listeElementsSupprimes) {
		this.listeElementsSupprimes = listeElementsSupprimes;
	}
	@Override
	public String toString() {
		return "MouvementStockSupprime [typeArticle=" + typeArticle + ", listeElementsSupprimes="
				+ listeElementsSupprimes + "]";
	}
	
	

}
