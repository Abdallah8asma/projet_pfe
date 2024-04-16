package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheLivraisonVenteFnReportingVue {

	private Long nombreResultaRechercher;
	
	private List<LivraisonVenteFnReportingVue> list = new ArrayList <LivraisonVenteFnReportingVue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<LivraisonVenteFnReportingVue> getList() {
		return list;
	}

	public void setList(List<LivraisonVenteFnReportingVue> list) {
		this.list = list;
	}

}
