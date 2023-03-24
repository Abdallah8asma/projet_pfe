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

	private Boolean declarer;

	private Double montantBlRegle;
	private Double montantFactureRegle;
	private Double montantFactureAvoirRegle;
	private Long nombreBlRegle;
	private Long nombreFactureRegle;
	private Long nombreFactureAvoirRegle;

	private Double montantBlNonRegle;

	private Double montantFactureNonRegle;

	private Double montantFactureAvoirNonRegle;

	private Long nombreBlNonRegle;

	private Long nombreFactureNonRegle;

	private Long nombreFactureAvoirNonRegle;

	public Boolean getDeclarer() {
		return declarer;
	}

	public void setDeclarer(Boolean declarer) {
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

	public Double getMontantBlRegle() {
		return montantBlRegle;
	}

	public void setMontantBlRegle(Double montantBlRegle) {
		this.montantBlRegle = montantBlRegle;
	}

	public Double getMontantFactureRegle() {
		return montantFactureRegle;
	}

	public void setMontantFactureRegle(Double montantFactureRegle) {
		this.montantFactureRegle = montantFactureRegle;
	}

	public Double getMontantFactureAvoirRegle() {
		return montantFactureAvoirRegle;
	}

	public void setMontantFactureAvoirRegle(Double montantFactureAvoirRegle) {
		this.montantFactureAvoirRegle = montantFactureAvoirRegle;
	}

	public Long getNombreBlRegle() {
		return nombreBlRegle;
	}

	public void setNombreBlRegle(Long nombreBlRegle) {
		this.nombreBlRegle = nombreBlRegle;
	}

	public Long getNombreFactureRegle() {
		return nombreFactureRegle;
	}

	public void setNombreFactureRegle(Long nombreFactureRegle) {
		this.nombreFactureRegle = nombreFactureRegle;
	}

	public Long getNombreFactureAvoirRegle() {
		return nombreFactureAvoirRegle;
	}

	public void setNombreFactureAvoirRegle(Long nombreFactureAvoirRegle) {
		this.nombreFactureAvoirRegle = nombreFactureAvoirRegle;
	}

	public Double getMontantBlNonRegle() {
		return montantBlNonRegle;
	}

	public void setMontantBlNonRegle(Double montantBlNonRegle) {
		this.montantBlNonRegle = montantBlNonRegle;
	}

	public Double getMontantFactureNonRegle() {
		return montantFactureNonRegle;
	}

	public void setMontantFactureNonRegle(Double montantFactureNonRegle) {
		this.montantFactureNonRegle = montantFactureNonRegle;
	}

	public Double getMontantFactureAvoirNonRegle() {
		return montantFactureAvoirNonRegle;
	}

	public void setMontantFactureAvoirNonRegle(Double montantFactureAvoirNonRegle) {
		this.montantFactureAvoirNonRegle = montantFactureAvoirNonRegle;
	}

	public Long getNombreBlNonRegle() {
		return nombreBlNonRegle;
	}

	public void setNombreBlNonRegle(Long nombreBlNonRegle) {
		this.nombreBlNonRegle = nombreBlNonRegle;
	}

	public Long getNombreFactureNonRegle() {
		return nombreFactureNonRegle;
	}

	public void setNombreFactureNonRegle(Long nombreFactureNonRegle) {
		this.nombreFactureNonRegle = nombreFactureNonRegle;
	}

	public Long getNombreFactureAvoirNonRegle() {
		return nombreFactureAvoirNonRegle;
	}

	public void setNombreFactureAvoirNonRegle(Long nombreFactureAvoirNonRegle) {
		this.nombreFactureAvoirNonRegle = nombreFactureAvoirNonRegle;
	}

}
