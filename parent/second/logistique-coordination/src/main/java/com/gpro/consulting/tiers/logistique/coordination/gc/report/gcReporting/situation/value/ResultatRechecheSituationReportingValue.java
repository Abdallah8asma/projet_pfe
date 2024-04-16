package com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */

public class ResultatRechecheSituationReportingValue {
	private Long nombreResultaRechercher;
	private List<SituationReportingValue> listSituationReporting = new ArrayList <SituationReportingValue>();
	
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}
	public List<SituationReportingValue> getListSituationReporting() {
		return listSituationReporting;
	}
	public void setListSituationReporting(
			List<SituationReportingValue> listSituationReporting) {
		this.listSituationReporting = listSituationReporting;
	}
	
}
