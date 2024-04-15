package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;
import java.util.Set;


/**
 * The Class ArticleValue.
 *
 * @author $Ghazi
 */

public class ArticleValue implements Comparable<ArticleValue>{

	private Long id;

	/** The ref. */
	
	private String ref;

	/** The dedignation. */
	private String designation;
	
	/** uidImage */
	private String uidImage;

	/** The prix unitaire. */
	
	private Double pu;

	/** The prix moyen pondere. */
	
	private Double pmp;

	/** The producteur. */
	
	private String producteur;

	/** The date introduction. */
	
	private Calendar dateIntroduction;

	/** The laize. */
	
	private Double laize;

	/** The poids. */
	private Double poids;
	
	/** The tare. */
	
	private Double tare;

	/** The poids_brut. */
	
	private Double poidsBrut;


	/** The observation. */
	
	private String observation;

	/** The pi entite. */
	
	private Long  piEntite;

	/** The sous famille entite. */
	
	private Long sousFamilleArtEntite;
	
/** The sous famille entite Designation. */
	
	private String sousFamilleArtEntiteDesignation;
	
/** The famille entite Designation. */
	
	private String familleArticleDesignation;

/** The TypeArticle entite Designation. */
	
	private String typeArticleDesignation;
	
	/** The site entite. */
	
	private Long siteEntite;
	
/** The site entite Designation. */
	
	private String siteEntiteDesignation;

	/** The grosseur entite. */
	
	private Long grosseurEntite;

	/** The metrage entite. */
	
	private Long metrageEntite;

	/** The unite entite. */
	
	private Long uniteEntite;
	
	/** The matiere entite. */
	
	private Long matiereEntite;	
	
	/**Emplacement **-/
	 */
	
	private String emplacement ;
	
	/**Methode de gestion *****/
	
	private String methodeGestion;
	
	
	private Long unite2Entite;
	
	
	
	private String uniteDesignation;
	
	private String unite2Designation;
	/*** Documents **//////
	private Set<DocumentArticleValue> documentEntites;
	
	/*** Seuils d'approvisionnement **//////
	private Set<SeuilArticleValue> seuilEntities;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	

	private String dimension;
	
	
	private String grammage;

	
	
	private String couleur ;
	

	
	private String codeFournisseur ;


	
	private Double tva;
	
	private Double puTTC;
	private Long idTaxe;
	
	
	private boolean serialisable;
	private Long partieIntersseId;
	
	private Double prixSpecial;
	private Double remise;
	private Long groupeClientId;
	
	private Long sousFamilleId;
	
	 private Long produitId ;
	 
	 private String nbrCouleur ;
	 
	 private String dimensionPapier;
		private Calendar dateCreation;
		
		private String nbrPose ;
		
		private String fichier;
		private Double stockMin;
		
	public String getFichier() {
			return fichier;
		}

		public void setFichier(String fichier) {
			this.fichier = fichier;
		}

	public String getNbrPose() {
			return nbrPose;
		}

		public void setNbrPose(String nbrPose) {
			this.nbrPose = nbrPose;
		}

	public Calendar getDateCreation() {
			return dateCreation;
		}

		public void setDateCreation(Calendar dateCreation) {
			this.dateCreation = dateCreation;
		}


		

	public String getNbrCouleur() {
			return nbrCouleur;
		}

		public void setNbrCouleur(String nbrCouleur) {
			this.nbrCouleur = nbrCouleur;
		}




	public String getDimensionPapier() {
		return dimensionPapier;
	}

	public void setDimensionPapier(String dimensionPapier) {
		this.dimensionPapier = dimensionPapier;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Long getSousFamilleId() {
		return sousFamilleId;
	}

	public void setSousFamilleId(Long sousFamilleId) {
		this.sousFamilleId = sousFamilleId;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public Double getPrixSpecial() {
		return prixSpecial;
	}

	public void setPrixSpecial(Double prixSpecial) {
		this.prixSpecial = prixSpecial;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public Long getIdTaxe() {
		return idTaxe;
	}

	public void setIdTaxe(Long idTaxe) {
		this.idTaxe = idTaxe;
	}

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

	public Double getPuTTC() {
		return puTTC;
	}

	public void setPuTTC(Double puTTC) {
		this.puTTC = puTTC;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getGrammage() {
		return grammage;
	}

	public void setGrammage(String grammage) {
		this.grammage = grammage;
	}

	public Long getId() {
		return id;
	}

	public String getUniteDesignation() {
		return uniteDesignation;
	}

	public void setUniteDesignation(String uniteDesignation) {
		this.uniteDesignation = uniteDesignation;
	}

	public String getUnite2Designation() {
		return unite2Designation;
	}

	public void setUnite2Designation(String unite2Designation) {
		this.unite2Designation = unite2Designation;
	}

	public Long getUnite2Entite() {
		return unite2Entite;
	}

	public void setUnite2Entite(Long unite2Entite) {
		this.unite2Entite = unite2Entite;
	}

	@Override
	public String toString() {
		return "ArticleValue [id=" + id + ", ref=" + ref + ", designation=" + designation + ", uidImage=" + uidImage
				+ ", pu=" + pu + ", pmp=" + pmp + ", producteur=" + producteur + ", dateIntroduction="
				+ dateIntroduction + ", laize=" + laize + ", poids=" + poids + ", tare=" + tare + ", poidsBrut="
				+ poidsBrut + ", observation=" + observation + ", piEntite=" + piEntite + ", sousFamilleArtEntite="
				+ sousFamilleArtEntite + ", sousFamilleArtEntiteDesignation=" + sousFamilleArtEntiteDesignation
				+ ", familleArticleDesignation=" + familleArticleDesignation + ", typeArticleDesignation="
				+ typeArticleDesignation + ", siteEntite=" + siteEntite + ", siteEntiteDesignation="
				+ siteEntiteDesignation + ", grosseurEntite=" + grosseurEntite + ", metrageEntite=" + metrageEntite
				+ ", uniteEntite=" + uniteEntite + ", matiereEntite=" + matiereEntite + ", emplacement=" + emplacement
				+ ", methodeGestion=" + methodeGestion + ", documentEntites=" + documentEntites + ", seuilEntities="
				+ seuilEntities + "]";
	}

	/**
	 * @return the uidImage
	 */
	public String getUidImage() {
		return uidImage;
	}

	/**
	 * @param uidImage the uidImage to set
	 */
	public void setUidImage(String uidImage) {
		this.uidImage = uidImage;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getRef() {
		return ref;
	}



	public void setRef(String ref) {
		this.ref = ref;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public Double getPu() {
		return pu;
	}



	public void setPu(Double pu) {
		this.pu = pu;
	}



	public Double getPmp() {
		return pmp;
	}



	public void setPmp(Double pmp) {
		this.pmp = pmp;
	}



	public String getProducteur() {
		return producteur;
	}



	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}



	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}



	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}



	public Double getLaize() {
		return laize;
	}



	public void setLaize(Double laize) {
		this.laize = laize;
	}



	public Double getPoids() {
		return poids;
	}



	public void setPoids(Double poids) {
		this.poids = poids;
	}



	public Double getTare() {
		return tare;
	}



	public void setTare(Double tare) {
		this.tare = tare;
	}



	public Double getPoidsBrut() {
		return poidsBrut;
	}



	public void setPoidsBrut(Double poidsBrut) {
		this.poidsBrut = poidsBrut;
	}



	public String getObservation() {
		return observation;
	}



	public void setObservation(String observation) {
		this.observation = observation;
	}



	public Long getPiEntite() {
		return piEntite;
	}



	public void setPiEntite(Long piEntite) {
		this.piEntite = piEntite;
	}



	public Long getSousFamilleArtEntite() {
		return sousFamilleArtEntite;
	}



	public void setSousFamilleArtEntite(Long sousFamilleArtEntite) {
		this.sousFamilleArtEntite = sousFamilleArtEntite;
	}


	/**
	 * @return the sousFamilleArtEntiteDesignation
	 */
	public String getSousFamilleArtEntiteDesignation() {
		return sousFamilleArtEntiteDesignation;
	}



	/**
	 * @param sousFamilleArtEntiteDesignation the sousFamilleArtEntiteDesignation to set
	 */
	public void setSousFamilleArtEntiteDesignation(
			String sousFamilleArtEntiteDesignation) {
		this.sousFamilleArtEntiteDesignation = sousFamilleArtEntiteDesignation;
	}


	/**
	 * @return the familleArticleDesignation
	 */
	public String getFamilleArticleDesignation() {
		return familleArticleDesignation;
	}



	/**
	 * @param familleArticleDesignation the familleArticleDesignation to set
	 */
	public void setFamilleArticleDesignation(String familleArticleDesignation) {
		this.familleArticleDesignation = familleArticleDesignation;
	}



	/**
	 * @return the typeArticleDesignation
	 */
	public String getTypeArticleDesignation() {
		return typeArticleDesignation;
	}



	/**
	 * @param typeArticleDesignation the typeArticleDesignation to set
	 */
	public void setTypeArticleDesignation(String typeArticleDesignation) {
		this.typeArticleDesignation = typeArticleDesignation;
	}



	/**
	 * @return the siteEntite
	 */
	public Long getSiteEntite() {
		return siteEntite;
	}



	/**
	 * @param siteEntite the siteEntite to set
	 */
	public void setSiteEntite(Long siteEntite) {
		this.siteEntite = siteEntite;
	}



	/**
	 * @return the siteEntiteDesignation
	 */
	public String getSiteEntiteDesignation() {
		return siteEntiteDesignation;
	}



	/**
	 * @param siteEntiteDesignation the siteEntiteDesignation to set
	 */
	public void setSiteEntiteDesignation(String siteEntiteDesignation) {
		this.siteEntiteDesignation = siteEntiteDesignation;
	}



	public Long getGrosseurEntite() {
		return grosseurEntite;
	}



	public void setGrosseurEntite(Long grosseurEntite) {
		this.grosseurEntite = grosseurEntite;
	}



	public Long getMetrageEntite() {
		return metrageEntite;
	}



	public void setMetrageEntite(Long metrageEntite) {
		this.metrageEntite = metrageEntite;
	}



	public Long getUniteEntite() {
		return uniteEntite;
	}



	public void setUniteEntite(Long uniteEntite) {
		this.uniteEntite = uniteEntite;
	}



	public Long getMatiereEntite() {
		return matiereEntite;
	}



	public void setMatiereEntite(Long matiereEntite) {
		this.matiereEntite = matiereEntite;
	}



	public String getEmplacement() {
		return emplacement;
	}



	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}



	public String getMethodeGestion() {
		return methodeGestion;
	}



	public void setMethodeGestion(String methodeGestion) {
		this.methodeGestion = methodeGestion;
	}



	public Set<DocumentArticleValue> getDocumentEntites() {
		return documentEntites;
	}



	public void setDocumentEntites(Set<DocumentArticleValue> documentEntites) {
		this.documentEntites = documentEntites;
	}



	public Set<SeuilArticleValue> getSeuilEntities() {
		return seuilEntities;
	}



	public void setSeuilEntities(Set<SeuilArticleValue> seuilEntities) {
		this.seuilEntities = seuilEntities;
	}

	@Override
	public int compareTo(ArticleValue element) {
		
		return (element.getId().compareTo(id));
	}

	public Double getStockMin() {
		return stockMin;
	}

	public void setStockMin(Double stockMin) {
		this.stockMin = stockMin;
	}

	
	
}
