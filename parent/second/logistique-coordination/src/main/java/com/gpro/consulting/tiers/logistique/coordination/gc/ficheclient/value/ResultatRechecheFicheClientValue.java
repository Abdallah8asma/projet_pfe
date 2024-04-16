package com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value;

import java.util.ArrayList;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientElementValue;

public class ResultatRechecheFicheClientValue {

	private int nombreResultaRechercher;

	private Double debitTotal;
	private Double creditTotal;
	private Double soldeClient;
	private Double soldeInitial;

	private List<FicheClientElementValue> listElements = new ArrayList<FicheClientElementValue>();

	public int getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(int nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<FicheClientElementValue> getListElements() {
		return listElements;
	}

	public void setListElements(List<FicheClientElementValue> listElements) {
		this.listElements = listElements;
	}

	public Double getDebitTotal() {
		return debitTotal;
	}

	public void setDebitTotal(Double debitTotal) {
		this.debitTotal = debitTotal;
	}

	public Double getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(Double creditTotal) {
		this.creditTotal = creditTotal;
	}

	public Double getSoldeClient() {
		return soldeClient;
	}

	public void setSoldeClient(Double soldeClient) {
		this.soldeClient = soldeClient;
	}

	public Double getSoldeInitial() {
		return soldeInitial;
	}

	public void setSoldeInitial(Double soldeInitial) {
		this.soldeInitial = soldeInitial;
	}

}
