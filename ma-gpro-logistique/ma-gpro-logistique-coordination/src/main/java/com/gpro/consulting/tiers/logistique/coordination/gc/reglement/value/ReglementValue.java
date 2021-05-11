package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ReglementValue implements Comparable<ReglementValue> {

	private Long id;
	private Long partieIntId;
	private String reference;
	private Calendar date;
	private Double montantTotal;
	private String observations;

	private Long idDepot;

	private Long groupeClientId;

	private String refAvantChangement;

	// envoyer par le front seulement
	private String ajoutSpecial;

	private String clientPassager;

	private String refBC;

	private Long boutiqueId;
	
	private boolean declarer;
	
	
	

	public boolean isDeclarer() {
		return declarer;
	}

	public void setDeclarer(boolean declarer) {
		this.declarer = declarer;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getRefBC() {
		return refBC;
	}

	public void setRefBC(String refBC) {
		this.refBC = refBC;
	}

	public String getClientPassager() {
		return clientPassager;
	}

	public void setClientPassager(String clientPassager) {
		this.clientPassager = clientPassager;
	}

	public String getAjoutSpecial() {
		return ajoutSpecial;
	}

	public void setAjoutSpecial(String ajoutSpecial) {
		this.ajoutSpecial = ajoutSpecial;
	}

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	private Set<DetailsReglementValue> listDetailsReglement;
	private Set<ElementReglementValue> listElementReglement;
	private Set<DocumentReglementValue> listDocReglement = new HashSet<DocumentReglementValue>();

	public int compareTo(ReglementValue element) {
		return (element.getReference().compareTo(reference));
	}

	public Set<DocumentReglementValue> getListDocReglement() {
		return listDocReglement;
	}

	public void setListDocReglement(Set<DocumentReglementValue> listDocReglement) {
		this.listDocReglement = listDocReglement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(Double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Set<DetailsReglementValue> getListDetailsReglement() {
		return listDetailsReglement;
	}

	public void setListDetailsReglement(Set<DetailsReglementValue> listDetailsReglement) {
		this.listDetailsReglement = listDetailsReglement;
	}

	public Set<ElementReglementValue> getListElementReglement() {
		return listElementReglement;
	}

	public void setListElementReglement(Set<ElementReglementValue> listElementReglement) {
		this.listElementReglement = listElementReglement;
	}

}
