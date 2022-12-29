package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.Calendar;

/**
 * DTO Request pour la recherche multicriteres pour les RouleauFini Standard 
 * 
 * @author Wahid Gazzah
 * @since 07/01/2016
 *
 */
public class RechercheMulticritereRouleauFiniStandardValue {
	
		private String numMise;
		//tissu
		private Long produitId;
		//client
		private Long partieInteresseeId;
		private Double poidMin;
		private Double poidMax;
		private Double metrageMin;
		private Double metrageMax;
		private Double laizeMin;
		private Double laizeMax;
		//choix
		private Long crQalite;
		private Long entrepot;
		private String emplacement;
		
		// RechercheMulticritere des RouleauFini existe dans des BonDeSortie ?
		// 'Oui', 'Non, oui bien 'Tous'
		private String inBonDeSortie; // oui, non , tous -- IConstant
		private String fini; // oui, non , tous -- IConstant
		
		//Added on 17/10/2016 bu Zeineb 
		private String reference;
		
		//Added on 15/11/2016 by Zeineb
		private String idProduitReference;
		
		
		
		
		
		private String username;
		
		private Calendar dateProductionDe;
		private Calendar dateProductionA;
		
		
		private String typeOf;
		
		
		
		private boolean optimized;
		
		
		
		
		
		public boolean isOptimized() {
			return optimized;
		}
		public void setOptimized(boolean optimized) {
			this.optimized = optimized;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Calendar getDateProductionDe() {
			return dateProductionDe;
		}
		public void setDateProductionDe(Calendar dateProductionDe) {
			this.dateProductionDe = dateProductionDe;
		}
		public Calendar getDateProductionA() {
			return dateProductionA;
		}
		public void setDateProductionA(Calendar dateProductionA) {
			this.dateProductionA = dateProductionA;
		}
		public String getTypeOf() {
			return typeOf;
		}
		public void setTypeOf(String typeOf) {
			this.typeOf = typeOf;
		}
		public String getNumMise() {
			return numMise;
		}
		public void setNumMise(String numMise) {
			this.numMise = numMise;
		}
		public Long getProduitId() {
			return produitId;
		}
		public void setProduitId(Long produitId) {
			this.produitId = produitId;
		}
		public Long getPartieInteresseeId() {
			return partieInteresseeId;
		}
		public void setPartieInteresseeId(Long partieInteresseeId) {
			this.partieInteresseeId = partieInteresseeId;
		}
		public Double getPoidMin() {
			return poidMin;
		}
		public void setPoidMin(Double poidMin) {
			this.poidMin = poidMin;
		}
		public Double getPoidMax() {
			return poidMax;
		}
		public void setPoidMax(Double poidMax) {
			this.poidMax = poidMax;
		}
		public Double getMetrageMin() {
			return metrageMin;
		}
		public void setMetrageMin(Double metrageMin) {
			this.metrageMin = metrageMin;
		}
		public Double getMetrageMax() {
			return metrageMax;
		}
		public void setMetrageMax(Double metrageMax) {
			this.metrageMax = metrageMax;
		}
		public Long getCrQalite() {
			return crQalite;
		}
		public void setCrQalite(Long crQalite) {
			this.crQalite = crQalite;
		}
		public Long getEntrepot() {
			return entrepot;
		}
		public void setEntrepot(Long entrepot) {
			this.entrepot = entrepot;
		}
		public String getEmplacement() {
			return emplacement;
		}
		public void setEmplacement(String emplacement) {
			this.emplacement = emplacement;
		}
		public Double getLaizeMin() {
			return laizeMin;
		}
		public void setLaizeMin(Double laizeMin) {
			this.laizeMin = laizeMin;
		}
		public Double getLaizeMax() {
			return laizeMax;
		}
		public void setLaizeMax(Double laizeMax) {
			this.laizeMax = laizeMax;
		}
		
		public String getInBonDeSortie() {
			return inBonDeSortie;
		}
		public void setInBonDeSortie(String inBonDeSortie) {
			this.inBonDeSortie = inBonDeSortie;
		}
		public String getFini() {
			return fini;
		}
		public void setFini(String fini) {
			this.fini = fini;
		}
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		public String getIdProduitReference() {
			return idProduitReference;
		}
		public void setIdProduitReference(String idProduitReference) {
			this.idProduitReference = idProduitReference;
		}
}
