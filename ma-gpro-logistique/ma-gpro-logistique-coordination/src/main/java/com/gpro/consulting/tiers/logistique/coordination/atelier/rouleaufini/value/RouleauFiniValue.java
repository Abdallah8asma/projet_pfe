package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public class RouleauFiniValue implements Comparable<RouleauFiniValue>{
	
	private Long id;
	private String reference;
	private Double metrage;
	private Double poids;
	
	private Long choix;
	private String choixDesignation;
	private String codeBarre;
	private String emplacement;
	private Long entrepot;
	private String entrepotDesignation;
	private Long produitId;
	private String produitIdDesignation;
	private String produitReference;
	private String produitIdComposition;
	private String produitIdSousFamille;
	private String partieInteresseeIdDesignation;
	private Long partieInteresseeId;
	private String referenceMise;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	private Double laize;
	
	//added on 08/01/2016, by Wahid Gazzah
	private Long bonSortie;
	private Calendar dateSortie;
	private Calendar dateIntroduction;
	private Boolean metrageModif;
	private String infoModif;
	
	//added on 20/01/2016, by Wahid Gazzah
	private Boolean fini;
	private String info;
	
	//added on 18/12/2016, by Ghazi Atroussi
		private Long bonInventaire;
	
		private Calendar dateInventaire;
		
		private Double metrageAncien;
		
		
		
		


		private String typeOf;

		private Long userId;
		
		private String responsable;
		
		
		
		private Long qtyByBox;
		
		
		private Calendar dateSortieReel;
		
		
		private String responsableExpedition;
		
		private Long userIdExpedition;
		
		private String palette;
		
		
		private Long numberOfBox;
		
		
		private Double premierMetrage;
		
		
		
		
		
		

		
    public String getTypeOf() {
			return typeOf;
		}

		public void setTypeOf(String typeOf) {
			this.typeOf = typeOf;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getResponsable() {
			return responsable;
		}

		public void setResponsable(String responsable) {
			this.responsable = responsable;
		}

		public Long getQtyByBox() {
			return qtyByBox;
		}

		public void setQtyByBox(Long qtyByBox) {
			this.qtyByBox = qtyByBox;
		}

		public Calendar getDateSortieReel() {
			return dateSortieReel;
		}

		public void setDateSortieReel(Calendar dateSortieReel) {
			this.dateSortieReel = dateSortieReel;
		}

		public String getResponsableExpedition() {
			return responsableExpedition;
		}

		public void setResponsableExpedition(String responsableExpedition) {
			this.responsableExpedition = responsableExpedition;
		}

		public Long getUserIdExpedition() {
			return userIdExpedition;
		}

		public void setUserIdExpedition(Long userIdExpedition) {
			this.userIdExpedition = userIdExpedition;
		}

		public String getPalette() {
			return palette;
		}

		public void setPalette(String palette) {
			this.palette = palette;
		}

		public Long getNumberOfBox() {
			return numberOfBox;
		}

		public void setNumberOfBox(Long numberOfBox) {
			this.numberOfBox = numberOfBox;
		}

		public Double getPremierMetrage() {
			return premierMetrage;
		}

		public void setPremierMetrage(Double premierMetrage) {
			this.premierMetrage = premierMetrage;
		}

	public Double getMetrageAncien() {
			return metrageAncien;
		}

		public void setMetrageAncien(Double metrageAncien) {
			this.metrageAncien = metrageAncien;
		}

		public Boolean getBlSuppression() {
			return blSuppression;
		}

		public Boolean getMetrageModif() {
			return metrageModif;
		}

		public Boolean getFini() {
			return fini;
		}

	@Override
	  public int compareTo(RouleauFiniValue o) {
		RouleauFiniValue element= (RouleauFiniValue)o;
	    return (element.getId().compareTo(id));
	  }
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return the metrage
	 */
	public Double getMetrage() {
		return metrage;
	}
	/**
	 * @param metrage the metrage to set
	 */
	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}
	/**
	 * @return the poids
	 */
	public Double getPoids() {
		return poids;
	}
	/**
	 * @param poids the poids to set
	 */
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	/**
	 * @return the choix
	 */
	public Long getChoix() {
		return choix;
	}
	/**
	 * @param choix the choix to set
	 */
	public void setChoix(Long choix) {
		this.choix = choix;
	}
	/**
	 * @return the codeBarre
	 */
	public String getCodeBarre() {
		return codeBarre;
	}
	/**
	 * @param codeBarre the codeBarre to set
	 */
	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}
	/**
	 * @return the emplacement
	 */
	public String getEmplacement() {
		return emplacement;
	}
	/**
	 * @param emplacement the emplacement to set
	 */
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	/**
	 * @return the entrepot
	 */
	public Long getEntrepot() {
		return entrepot;
	}
	/**
	 * @param entrepot the entrepot to set
	 */
	public void setEntrepot(Long entrepot) {
		this.entrepot = entrepot;
	}
	/**
	 * @return the produitId
	 */
	public Long getProduitId() {
		return produitId;
	}
	/**
	 * @param produitId the produitId to set
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	/**
	 * @return the partieInteresseeId
	 */
	public Long getPartieInteresseeId() {
		return partieInteresseeId;
	}
	/**
	 * @param partieInteresseeId the partieInteresseeId to set
	 */
	public void setPartieInteresseeId(Long partieInteresseeId) {
		this.partieInteresseeId = partieInteresseeId;
	}
	/**
	 * @return the referenceMise
	 */
	public String getReferenceMise() {
		return referenceMise;
	}
	/**
	 * @param referenceMise the referenceMise to set
	 */
	public void setReferenceMise(String referenceMise) {
		this.referenceMise = referenceMise;
	}
	/**
	 * @return the blSuppression
	 */
	public Boolean isBlSuppression() {
		return blSuppression;
	}
	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}
	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}
	/**
	 * @param dateSuppression the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	/**
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}
	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}
	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the laize
	 */
	public Double getLaize() {
		return laize;
	}
	/**
	 * @param laize the laize to set
	 */
	public void setLaize(Double laize) {
		this.laize = laize;
	}
	/** Accesseur en lecture de l'attribut produitIdDesignation.
	 * @return the produitIdDesignation
	 */
	public String getProduitIdDesignation() {
		return produitIdDesignation;
	}
	/**
	 * @param produitIdDesignation the produitIdDesignation to set
	 */
	public void setProduitIdDesignation(String produitIdDesignation) {
		this.produitIdDesignation = produitIdDesignation;
	}
	/** Accesseur en lecture de l'attribut partieInteresseeIdDesignation.
	 * @return the partieInteresseeIdDesignation
	 */
	public String getPartieInteresseeIdDesignation() {
		return partieInteresseeIdDesignation;
	}
	/**
	 * @param partieInteresseeIdDesignation the partieInteresseeIdDesignation to set
	 */
	public void setPartieInteresseeIdDesignation(
			String partieInteresseeIdDesignation) {
		this.partieInteresseeIdDesignation = partieInteresseeIdDesignation;
	}
	public Long getBonSortie() {
		return bonSortie;
	}
	public void setBonSortie(Long bonSortie) {
		this.bonSortie = bonSortie;
	}
	public Calendar getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}
	public Boolean isMetrageModif() {
		return metrageModif;
	}
	public void setMetrageModif(Boolean metrageModif) {
		this.metrageModif = metrageModif;
	}
	public String getInfoModif() {
		return infoModif;
	}
	public void setInfoModif(String infoModif) {
		this.infoModif = infoModif;
	}

	/** Accesseur en lecture de l'attribut produitReference.
	 * @return the produitReference
	 */
	public String getProduitReference() {
		return produitReference;
	}
	/**
	 * @param produitReference the produitReference to set
	 */
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}
	
	/** Accesseur en lecture de l'attribut choixDesignation.
	 * @return the choixDesignation
	 */
	public String getChoixDesignation() {
		return choixDesignation;
	}
	/**
	 * @param choixDesignation the choixDesignation to set
	 */
	public void setChoixDesignation(String choixDesignation) {
		this.choixDesignation = choixDesignation;
	}
	/** Accesseur en lecture de l'attribut entrepotDesignation.
	 * @return the entrepotDesignation
	 */
	public String getEntrepotDesignation() {
		return entrepotDesignation;
	}
	/**
	 * @param entrepotDesignation the entrepotDesignation to set
	 */
	public void setEntrepotDesignation(String entrepotDesignation) {
		this.entrepotDesignation = entrepotDesignation;
	}
	/** Accesseur en lecture de l'attribut produitIdComposition.
	 * @return the produitIdComposition
	 */
	public String getProduitIdComposition() {
		return produitIdComposition;
	}
	/**
	 * @param produitIdComposition the produitIdComposition to set
	 */
	public void setProduitIdComposition(String produitIdComposition) {
		this.produitIdComposition = produitIdComposition;
	}
	/** Accesseur en lecture de l'attribut produitIdSousFamille.
	 * @return the produitIdSousFamille
	 */
	public String getProduitIdSousFamille() {
		return produitIdSousFamille;
	}
	/**
	 * @param produitIdSousFamille the produitIdSousFamille to set
	 */
	public void setProduitIdSousFamille(String produitIdSousFamille) {
		this.produitIdSousFamille = produitIdSousFamille;
	}

	public Boolean isFini() {
		return fini;
	}

	public void setFini(Boolean fini) {
		this.fini = fini;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "RouleauFiniValue [id=" + id + ", reference=" + reference + ", metrage=" + metrage + ", poids=" + poids
				+ ", choix=" + choix + ", choixDesignation=" + choixDesignation + ", codeBarre=" + codeBarre
				+ ", emplacement=" + emplacement + ", entrepot=" + entrepot + ", entrepotDesignation="
				+ entrepotDesignation + ", produitId=" + produitId + ", produitIdDesignation=" + produitIdDesignation
				+ ", produitReference=" + produitReference + ", produitIdComposition=" + produitIdComposition
				+ ", produitIdSousFamille=" + produitIdSousFamille + ", partieInteresseeIdDesignation="
				+ partieInteresseeIdDesignation + ", partieInteresseeId=" + partieInteresseeId + ", referenceMise="
				+ referenceMise + ", blSuppression=" + blSuppression + ", dateSuppression=" + dateSuppression
				+ ", dateCreation=" + dateCreation + ", dateModification=" + dateModification + ", version=" + version
				+ ", laize=" + laize + ", bonSortie=" + bonSortie + ", dateSortie=" + dateSortie + ", dateIntroduction="
				+ dateIntroduction + ", metrageModif=" + metrageModif + ", infoModif=" + infoModif + ", fini=" + fini
				+ ", info=" + info + "]";
	}

	/**
	 * @return the bonInventaire
	 */
	public Long getBonInventaire() {
		return bonInventaire;
	}

	/**
	 * @param bonInventaire the bonInventaire to set
	 */
	public void setBonInventaire(Long bonInventaire) {
		this.bonInventaire = bonInventaire;
	}

	/**
	 * @return the dateInventaire
	 */
	public Calendar getDateInventaire() {
		return dateInventaire;
	}

	/**
	 * @param dateInventaire the dateInventaire to set
	 */
	public void setDateInventaire(Calendar dateInventaire) {
		this.dateInventaire = dateInventaire;
	}
	
}
