package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.List;

public class ResultatRechecheArticleEntreeValue {

	private Long nombreResultaRechercher;

	private List<ArticleEntreeValue> articleEntree;

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<ArticleEntreeValue> getArticleEntree() {
		return articleEntree;
	}

	public void setArticleEntree(List<ArticleEntreeValue> articleEntree) {
		this.articleEntree = articleEntree;
	}

}
