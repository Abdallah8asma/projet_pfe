package com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;

/** 
 * @author Zeineb Medimagh
 * @since 03/10/2016
 */
public class ListTraitFaconElementValue {
	
	private int nombreResultaRechercher;
	
	private Calendar dateSortie;
	
	private List<DetLivraisonVenteValue> listDetLivraisonVente;

	public int getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(int nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<DetLivraisonVenteValue> getListDetLivraisonVente() {
		return listDetLivraisonVente;
	}

	public void setListDetLivraisonVente(
			List<DetLivraisonVenteValue> listDetLivraisonVente) {
		this.listDetLivraisonVente = listDetLivraisonVente;
	}

	public Calendar getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	

	
}
