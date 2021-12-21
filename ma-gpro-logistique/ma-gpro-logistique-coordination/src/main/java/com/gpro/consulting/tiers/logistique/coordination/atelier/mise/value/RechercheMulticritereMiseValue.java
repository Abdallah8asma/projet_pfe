package com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value;

import java.util.Calendar;
import java.util.List;

public class RechercheMulticritereMiseValue {
	 /** Client */
	  private Long client;

	  /** Date Introduction */
	  private Calendar dateIntroduction;
	  
	  private String referenceBonReception ;
	  
	  
	  
	  //from thermo
	  private String referenceMise;

		private String poidFini;

		private String referenceProduit;
		private Calendar dateIntroductionDE;
		private Calendar dateIntroductionA;
		private Calendar dateLivraisonDE;
		private Calendar dateLivraisonA;
		private String referenceOF;
		private Integer quantiteDE;
		private Integer quantiteA;

		private String type;
		
		
		private String statut;
		private Calendar dateDebutProductionDe;
		private Calendar dateDebutProductionA;
		
		private Calendar dateFinProductionDe;
		private Calendar dateFinProductionA;
		
		
		private String etatProduced;
		
		private String etatShipped;
		
		
		private Long produitId;
		
		
		private List<String> statutList;
		
		private String typeEtiquette ;
		
		
		
		private String machine;
		
		private String refCommande;
		
		private boolean optimized;
		
		
		

	public boolean isOptimized() {
			return optimized;
		}

		public void setOptimized(boolean optimized) {
			this.optimized = optimized;
		}

	public String getRefCommande() {
			return refCommande;
		}

		public void setRefCommande(String refCommande) {
			this.refCommande = refCommande;
		}

	public String getReferenceMise() {
			return referenceMise;
		}

		public void setReferenceMise(String referenceMise) {
			this.referenceMise = referenceMise;
		}

		public String getPoidFini() {
			return poidFini;
		}

		public void setPoidFini(String poidFini) {
			this.poidFini = poidFini;
		}

		public String getReferenceProduit() {
			return referenceProduit;
		}

		public void setReferenceProduit(String referenceProduit) {
			this.referenceProduit = referenceProduit;
		}

		public Calendar getDateIntroductionDE() {
			return dateIntroductionDE;
		}

		public void setDateIntroductionDE(Calendar dateIntroductionDE) {
			this.dateIntroductionDE = dateIntroductionDE;
		}

		public Calendar getDateIntroductionA() {
			return dateIntroductionA;
		}

		public void setDateIntroductionA(Calendar dateIntroductionA) {
			this.dateIntroductionA = dateIntroductionA;
		}

		public Calendar getDateLivraisonDE() {
			return dateLivraisonDE;
		}

		public void setDateLivraisonDE(Calendar dateLivraisonDE) {
			this.dateLivraisonDE = dateLivraisonDE;
		}

		public Calendar getDateLivraisonA() {
			return dateLivraisonA;
		}

		public void setDateLivraisonA(Calendar dateLivraisonA) {
			this.dateLivraisonA = dateLivraisonA;
		}

		public String getReferenceOF() {
			return referenceOF;
		}

		public void setReferenceOF(String referenceOF) {
			this.referenceOF = referenceOF;
		}

		public Integer getQuantiteDE() {
			return quantiteDE;
		}

		public void setQuantiteDE(Integer quantiteDE) {
			this.quantiteDE = quantiteDE;
		}

		public Integer getQuantiteA() {
			return quantiteA;
		}

		public void setQuantiteA(Integer quantiteA) {
			this.quantiteA = quantiteA;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getStatut() {
			return statut;
		}

		public void setStatut(String statut) {
			this.statut = statut;
		}

		public Calendar getDateDebutProductionDe() {
			return dateDebutProductionDe;
		}

		public void setDateDebutProductionDe(Calendar dateDebutProductionDe) {
			this.dateDebutProductionDe = dateDebutProductionDe;
		}

		public Calendar getDateDebutProductionA() {
			return dateDebutProductionA;
		}

		public void setDateDebutProductionA(Calendar dateDebutProductionA) {
			this.dateDebutProductionA = dateDebutProductionA;
		}

		public Calendar getDateFinProductionDe() {
			return dateFinProductionDe;
		}

		public void setDateFinProductionDe(Calendar dateFinProductionDe) {
			this.dateFinProductionDe = dateFinProductionDe;
		}

		public Calendar getDateFinProductionA() {
			return dateFinProductionA;
		}

		public void setDateFinProductionA(Calendar dateFinProductionA) {
			this.dateFinProductionA = dateFinProductionA;
		}

		public String getEtatProduced() {
			return etatProduced;
		}

		public void setEtatProduced(String etatProduced) {
			this.etatProduced = etatProduced;
		}

		public String getEtatShipped() {
			return etatShipped;
		}

		public void setEtatShipped(String etatShipped) {
			this.etatShipped = etatShipped;
		}

		public Long getProduitId() {
			return produitId;
		}

		public void setProduitId(Long produitId) {
			this.produitId = produitId;
		}

		public List<String> getStatutList() {
			return statutList;
		}

		public void setStatutList(List<String> statutList) {
			this.statutList = statutList;
		}

		public String getTypeEtiquette() {
			return typeEtiquette;
		}

		public void setTypeEtiquette(String typeEtiquette) {
			this.typeEtiquette = typeEtiquette;
		}

		public String getMachine() {
			return machine;
		}

		public void setMachine(String machine) {
			this.machine = machine;
		}

	/**
	 * @return the client
	 */
	public Long getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Long client) {
		this.client = client;
	}

	/**
	 * @return the dateIntroduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * @param dateIntroduction the dateIntroduction to set
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	/**
	 * @return the referenceBonReception
	 */
	public String getReferenceBonReception() {
		return referenceBonReception;
	}

	/**
	 * @param referenceBonReception the referenceBonReception to set
	 */
	public void setReferenceBonReception(String referenceBonReception) {
		this.referenceBonReception = referenceBonReception;
	}
	  
	  

}
