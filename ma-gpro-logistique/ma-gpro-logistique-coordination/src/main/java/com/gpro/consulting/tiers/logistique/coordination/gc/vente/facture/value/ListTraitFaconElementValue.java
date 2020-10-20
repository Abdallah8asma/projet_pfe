package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Calendar;
import java.util.List;

/** 
 * @author Zeineb Medimagh
 * @since 03/10/2016
 */
public class ListTraitFaconElementValue {
	
	private int nombreResultaRechercher;
	
	private Calendar dateLivraison;
	
	private Long partieIntId;
	
	private List<DetFactureVenteValue> listDetFactureVente;

	public int getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(int nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public List<DetFactureVenteValue> getListDetFactureVente() {
		return listDetFactureVente;
	}

	public void setListDetFactureVente(List<DetFactureVenteValue> listDetFactureVente) {
		this.listDetFactureVente = listDetFactureVente;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}
	
}
