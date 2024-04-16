package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ReglementAchatValue implements Comparable<ReglementAchatValue> {

	private Long id;
	private Long partieIntId;
	private String reference;
	private Calendar date;
	private Double montantTotal;
	private String observations;

	private Long idDepot;

	private Long groupeClientId;

	private Long boutiqueId;

	private String refAvantChangement;
	
	private boolean declarer;
	
	
	

	public boolean isDeclarer() {
		return declarer;
	}

	public void setDeclarer(boolean declarer) {
		this.declarer = declarer;
	}

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
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

	private Set<DetailsReglementAchatValue> listDetailsReglement;
	private Set<ElementReglementAchatValue> listElementReglement;
	private Set<DocumentReglementAchatValue> listDocReglement = new HashSet<DocumentReglementAchatValue>();

	public int compareTo(ReglementAchatValue element) {
		return (element.getReference().compareTo(reference));
	}

	public Set<DocumentReglementAchatValue> getListDocReglement() {
		return listDocReglement;
	}

	public void setListDocReglement(Set<DocumentReglementAchatValue> listDocReglement) {
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

	public Set<DetailsReglementAchatValue> getListDetailsReglement() {
		return listDetailsReglement;
	}

	public void setListDetailsReglement(Set<DetailsReglementAchatValue> listDetailsReglement) {
		this.listDetailsReglement = listDetailsReglement;
	}

	public Set<ElementReglementAchatValue> getListElementReglement() {
		return listElementReglement;
	}

	public void setListElementReglement(Set<ElementReglementAchatValue> listElementReglement) {
		this.listElementReglement = listElementReglement;
	}

}
