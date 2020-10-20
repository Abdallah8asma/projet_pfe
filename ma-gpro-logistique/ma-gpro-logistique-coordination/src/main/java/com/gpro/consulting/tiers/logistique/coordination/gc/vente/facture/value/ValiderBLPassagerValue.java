package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Calendar;
import java.util.List;

public class ValiderBLPassagerValue {

	private List<String> refBL;
	private Long factureId;

	private Long piId;

	private Calendar date;

	public Long getPiId() {
		return piId;
	}

	public void setPiId(Long piId) {
		this.piId = piId;
	}

	public List<String> getRefBL() {
		return refBL;
	}

	public void setRefBL(List<String> refBL) {
		this.refBL = refBL;
	}

	public Long getFactureId() {
		return factureId;
	}

	public void setFactureId(Long factureId) {
		this.factureId = factureId;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

}
