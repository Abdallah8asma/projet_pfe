package com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 */
public class ListProduitElementValue {

	private int nombreResultaRechercher;

	private Long partieIntId;

	private Calendar dateLivraison;

	// added on 25/02/2016, by Wahid Gazzah
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

	public void setListDetLivraisonVente(List<DetLivraisonVenteValue> listDetLivraisonVente) {
		this.listDetLivraisonVente = listDetLivraisonVente;
	}

	public Calendar getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

}
