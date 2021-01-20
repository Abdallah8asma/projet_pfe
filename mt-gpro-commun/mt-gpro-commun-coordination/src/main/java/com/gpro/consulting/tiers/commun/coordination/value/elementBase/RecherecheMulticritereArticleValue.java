package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;

public class RecherecheMulticritereArticleValue {
	/** The ref. */
	private String ref;
	/** The designation. */
	private String designation ;
	/** The type. */
	private String typeEntite;
	/** The sous famille. */
	private String sousFamilleEntite;
	/** The famille. */
	private String familleEntite;
	/** The site. */
	private String site;
	/** The prix inf. */
	private Double prix_inf;
	/** The prix sup. */
	private Double prix_sup;
	/** ID Magasin. */
	private Long idMAgasin;
	
	

	
	
	private String dimension;
	
	
	private String grammage;

	
	
	
	
	private String partieInteressee ;
	
	private Long FamillePiId;
	
	private Long getGroupeClientId;
	
	
	private Calendar dateCreation;
	
	
	private Calendar dateIntroduction;
	
	 private String nbrCouleur ;
	 
	 private String dimensionPapier;
		
		
		private String nbrPose ;
		
		private String fichier;
		
		 private Long produitId ;
	
		 private Long  piEntite;
		 
	public Long getPiEntite() {
			return piEntite;
		}
		public void setPiEntite(Long piEntite) {
			this.piEntite = piEntite;
		}
	public Long getProduitId() {
			return produitId;
		}
		public void setProduitId(Long produitId) {
			this.produitId = produitId;
		}
	public String getFichier() {
			return fichier;
		}
		public void setFichier(String fichier) {
			this.fichier = fichier;
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
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}
	public Long getFamillePiId() {
		return FamillePiId;
	}
	public void setFamillePiId(Long famillePiId) {
		FamillePiId = famillePiId;
	}
	public Long getGetGroupeClientId() {
		return getGroupeClientId;
	}
	public void setGetGroupeClientId(Long getGroupeClientId) {
		this.getGroupeClientId = getGroupeClientId;
	}
	public String getPartieInteressee() {
		return partieInteressee;
	}
	public void setPartieInteressee(String partieInteressee) {
		this.partieInteressee = partieInteressee;
	}
	/**
	 * @return the pIdMAgasin
	 */
	
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
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the typeEntite
	 */
	public String getTypeEntite() {
		return typeEntite;
	}
	/**
	 * @param typeEntite the typeEntite to set
	 */
	public void setTypeEntite(String typeEntite) {
		this.typeEntite = typeEntite;
	}
	/**
	 * @return the sousFamilleEntite
	 */
	public String getSousFamilleEntite() {
		return sousFamilleEntite;
	}
	/**
	 * @param sousFamilleEntite the sousFamilleEntite to set
	 */
	public void setSousFamilleEntite(String sousFamilleEntite) {
		this.sousFamilleEntite = sousFamilleEntite;
	}
	/**
	 * @return the familleEntite
	 */
	public String getFamilleEntite() {
		return familleEntite;
	}
	/**
	 * @param familleEntite the familleEntite to set
	 */
	public void setFamilleEntite(String familleEntite) {
		this.familleEntite = familleEntite;
	}
	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return the prix_inf
	 */
	public Double getPrix_inf() {
		return prix_inf;
	}
	/**
	 * @param prix_inf the prix_inf to set
	 */
	public void setPrix_inf(Double prix_inf) {
		this.prix_inf = prix_inf;
	}
	/**
	 * @return the prix_sup
	 */
	public Double getPrix_sup() {
		return prix_sup;
	}
	/**
	 * @param prix_sup the prix_sup to set
	 */
	public void setPrix_sup(Double prix_sup) {
		this.prix_sup = prix_sup;
	}
	public Long getIdMAgasin() {
		return idMAgasin;
	}
	public void setIdMAgasin(Long idMAgasin) {
		this.idMAgasin = idMAgasin;
	}
	
	
}
