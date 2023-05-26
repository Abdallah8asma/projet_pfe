package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheArticleProduitValue {
	 private Long nombreResultaRechercher;

	  private List<ArticleProduitValue> list = new ArrayList<ArticleProduitValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<ArticleProduitValue> getList() {
		return list;
	}

	public void setList(List<ArticleProduitValue> list) {
		this.list = list;
	}

	

	

}
