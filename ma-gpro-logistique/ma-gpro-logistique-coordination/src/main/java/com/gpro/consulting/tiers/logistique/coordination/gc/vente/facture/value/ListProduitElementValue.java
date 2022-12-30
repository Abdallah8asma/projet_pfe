package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;

/**
 * @author Wahid Gazzah
 * @since 29/02/2016
 */
public class ListProduitElementValue {

	private int nombreResultaRechercher;

	private Calendar dateLivrison;
	private Long partieIntId;
	private Long idMarche;

	private Long idDepot;
	
	private String refexterne;
	
	

	private List<DetFactureVenteValue> listDetFactureVente;

	private List<DetFactureAchatValue> listDetFactureAchat;
	
	
	private String refCommande;
	
	
	

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public int getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(int nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<DetFactureVenteValue> getListDetFactureVente() {
		return listDetFactureVente;
	}

	public void setListDetFactureVente(List<DetFactureVenteValue> listDetFactureVente) {
		this.listDetFactureVente = listDetFactureVente;
	}

	public Calendar getDateLivrison() {
		return dateLivrison;
	}

	public void setDateLivrison(Calendar dateLivrison) {
		this.dateLivrison = dateLivrison;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Long getIdMarche() {
		return idMarche;
	}

	public void setIdMarche(Long idMarche) {
		this.idMarche = idMarche;
	}

	public List<DetFactureAchatValue> getListDetFactureAchat() {
		return listDetFactureAchat;
	}

	public void setListDetFactureAchat(List<DetFactureAchatValue> listDetFactureAchat) {
		this.listDetFactureAchat = listDetFactureAchat;
	}

}
