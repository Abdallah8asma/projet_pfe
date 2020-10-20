package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;

/**
 * The Class PartieInteresseValue.
 */
public class BoutiqueValue { 

	/** The id. */
	private Long id;

	/** The ref. */
	private String reference;
	
	
	private String refAvantChangement;

	/** The raison sociale. */
	private String raisonSociale;

	/** The abreviation. */
	private String abreviation;

	/** The activite. */
	private String activite;

	/** The observation. */
	private String observation;
	
	/** The devise. */
	private String devise;

	/** The date introduction. */
	private Calendar dateIntroduction;

	/** The matr fiscal. */
	private String matriculeFiscal;

	/** The regime commercial. */
	private String regimeCommercial;

	/** The code douane. */
	private String codeDouane;

	/** The adresse. */
	private String adresse;

	/** The email. */
	private String email;

	/** The telephone. */
	private String telephone;

	/** The fax. */
	private String fax;

  /** The actif. */
   private Boolean actif;

	/** The famille . */
	private Long famillePartieInteressee;

	/** designation FamillePartieInteressee */
	private String famillePartieInteresseeDesignation;

	/** The type . */
	private Long typePartieInteressee;
	
	/** typePartieInteresseeDesignation */
	private String typePartieInteresseeDesignation;

	/** The site . */
	private Long sitePartieInteressee;

	/** The categorie . */
	private Long categoriePartieInteressee;
	
	/** categoriePartieInteresseeDesignation */
	private String categoriePartieInteresseeDesignation;
	
	/** regionId **/
	private Long regionId;
	
	/** regionDesignation */
	private String regionDesignation;
	

	
	private Double objectif;
	
	
	
	
	private Long societeId;
	
	
	
	
	
	
	
   public Long getSocieteId() {
		return societeId;
	}


	public void setSocieteId(Long societeId) {
		this.societeId = societeId;
	}


public Double getObjectif() {
		return objectif;
	}


	public void setObjectif(Double objectif) {
		this.objectif = objectif;
	}


public BoutiqueValue() {
		
	
	}


	public BoutiqueValue(Long boutiqueId) {
		
		this.id = boutiqueId;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>id</code>.
	 * 
	 * @return Long L'attribut id à lire.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>id</code>.
	 *
	 * @param id
	 *            L'attribut id à modifier.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>reference</code>.
	 * 
	 * @return String L'attribut reference à lire.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>reference</code>.
	 *
	 * @param reference
	 *            L'attribut reference à modifier.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>raisonSociale</code>.
	 * 
	 * @return String L'attribut raisonSociale à lire.
	 */
	public String getRaisonSociale() {
		return raisonSociale;
	}
	
	/**
	 * @return the devise
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * @param devise the devise to set
	 */
	public void setDevise(String devise) {
		this.devise = devise;
	}

	/**
	 * @return the actif
	 */
	public Boolean getActif() {
		return actif;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>raisonSociale</code>.
	 *
	 * @param raisonSociale
	 *            L'attribut raisonSociale à modifier.
	 */
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>abreviation</code>.
	 * 
	 * @return String L'attribut abreviation à lire.
	 */
	public String getAbreviation() {
		return abreviation;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>abreviation</code>.
	 *
	 * @param abreviation
	 *            L'attribut abreviation à modifier.
	 */
	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>activite</code>.
	 * 
	 * @return String L'attribut activite à lire.
	 */
	public String getActivite() {
		return activite;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>activite</code>.
	 *
	 * @param activite
	 *            L'attribut activite à modifier.
	 */
	public void setActivite(String activite) {
		this.activite = activite;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>observation</code>.
	 * 
	 * @return String L'attribut observation à lire.
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>observation</code>.
	 *
	 * @param observation
	 *            L'attribut observation à modifier.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>dateIntroduction</code>.
	 * 
	 * @return Calendar L'attribut dateIntroduction à lire.
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>dateIntroduction</code>.
	 *
	 * @param dateIntroduction
	 *            L'attribut dateIntroduction à modifier.
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>matriculeFiscal</code>.
	 * 
	 * @return String L'attribut matriculeFiscal à lire.
	 */
	public String getMatriculeFiscal() {
		return matriculeFiscal;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>matriculeFiscal</code>.
	 *
	 * @param matriculeFiscal
	 *            L'attribut matriculeFiscal à modifier.
	 */
	public void setMatriculeFiscal(String matriculeFiscal) {
		this.matriculeFiscal = matriculeFiscal;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>regimeCommercial</code>.
	 * 
	 * @return String L'attribut regimeCommercial à lire.
	 */
	public String getRegimeCommercial() {
		return regimeCommercial;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>regimeCommercial</code>.
	 *
	 * @param regimeCommercial
	 *            L'attribut regimeCommercial à modifier.
	 */
	public void setRegimeCommercial(String regimeCommercial) {
		this.regimeCommercial = regimeCommercial;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>codeDouane</code>.
	 * 
	 * @return String L'attribut codeDouane à lire.
	 */
	public String getCodeDouane() {
		return codeDouane;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>codeDouane</code>.
	 *
	 * @param codeDouane
	 *            L'attribut codeDouane à modifier.
	 */
	public void setCodeDouane(String codeDouane) {
		this.codeDouane = codeDouane;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>adresse</code>.
	 * 
	 * @return String L'attribut adresse à lire.
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>adresse</code>.
	 *
	 * @param adresse
	 *            L'attribut adresse à modifier.
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>email</code>.
	 * 
	 * @return String L'attribut email à lire.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>email</code>.
	 *
	 * @param email
	 *            L'attribut email à modifier.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>telephone</code>.
	 * 
	 * @return String L'attribut telephone à lire.
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>telephone</code>.
	 *
	 * @param telephone
	 *            L'attribut telephone à modifier.
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>fax</code>.
	 * 
	 * @return String L'attribut fax à lire.
	 */
	public String getFax() {
		return fax;
	}


	/**
	 * Accesseur en écriture de l'attribut <code>fax</code>.
	 *
	 * @param fax
	 *            L'attribut fax à modifier.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	
	/**
	 * Accesseur en lecture de l'attribut <code>famillePartieInteressee</code>.
	 * 
	 * @return Long L'attribut famillePartieInteressee à lire.
	 */
	public Long getFamillePartieInteressee() {
		return famillePartieInteressee;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>famillePartieInteressee</code>.
	 *
	 * @param famillePartieInteressee
	 *            L'attribut famillePartieInteressee à modifier.
	 */
	public void setFamillePartieInteressee(Long famillePartieInteressee) {
		this.famillePartieInteressee = famillePartieInteressee;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>typePartieInteressee</code>.
	 * 
	 * @return Long L'attribut typePartieInteressee à lire.
	 */
	public Long getTypePartieInteressee() {
		return typePartieInteressee;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>typePartieInteressee</code>.
	 *
	 * @param typePartieInteressee
	 *            L'attribut typePartieInteressee à modifier.
	 */
	public void setTypePartieInteressee(Long typePartieInteressee) {
		this.typePartieInteressee = typePartieInteressee;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>sitePartieInteressee</code>.
	 * 
	 * @return Long L'attribut sitePartieInteressee à lire.
	 */
	public Long getSitePartieInteressee() {
		return sitePartieInteressee;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>sitePartieInteressee</code>.
	 *
	 * @param sitePartieInteressee
	 *            L'attribut sitePartieInteressee à modifier.
	 */
	public void setSitePartieInteressee(Long sitePartieInteressee) {
		this.sitePartieInteressee = sitePartieInteressee;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>categoriePartieInteressee</code>
	 * .
	 * 
	 * @return Long L'attribut categoriePartieInteressee à lire.
	 */
	public Long getCategoriePartieInteressee() {
		return categoriePartieInteressee;
	}

	/**
	 * Accesseur en écriture de l'attribut
	 * <code>categoriePartieInteressee</code>.
	 *
	 * @param categoriePartieInteressee
	 *            L'attribut categoriePartieInteressee à modifier.
	 */
	public void setCategoriePartieInteressee(Long categoriePartieInteressee) {
		this.categoriePartieInteressee = categoriePartieInteressee;
	}

	

	/**
	 * Accesseur en lecture de l'attribut <code>representants</code>.
	 * 
	 * @return Set<RepresentantValue> L'attribut representants à lire.
	 */


	/**
	 * @return the famillePartieInteresseeDesignation
	 */
	public String getFamillePartieInteresseeDesignation() {
		return famillePartieInteresseeDesignation;
	}

	/**
	 * @param famillePartieInteresseeDesignation
	 *            the famillePartieInteresseeDesignation to set
	 */
	public void setFamillePartieInteresseeDesignation(
			String famillePartieInteresseeDesignation) {
		this.famillePartieInteresseeDesignation = famillePartieInteresseeDesignation;
	}

	
	/**
	 * @return the typePartieInteresseeDesignation
	 */
	public String getTypePartieInteresseeDesignation() {
		return typePartieInteresseeDesignation;
	}

	/**
	 * @param typePartieInteresseeDesignation the typePartieInteresseeDesignation to set
	 */
	public void setTypePartieInteresseeDesignation(
			String typePartieInteresseeDesignation) {
		this.typePartieInteresseeDesignation = typePartieInteresseeDesignation;
	}

	/**
	 * @return the categoriePartieInteresseeDesignation
	 */
	public String getCategoriePartieInteresseeDesignation() {
		return categoriePartieInteresseeDesignation;
	}

	/**
	 * @param categoriePartieInteresseeDesignation the categoriePartieInteresseeDesignation to set
	 */
	public void setCategoriePartieInteresseeDesignation(
			String categoriePartieInteresseeDesignation) {
		this.categoriePartieInteresseeDesignation = categoriePartieInteresseeDesignation;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionDesignation() {
		return regionDesignation;
	}

	public void setRegionDesignation(String regionDesignation) {
		this.regionDesignation = regionDesignation;
	}
	
	
	


	
	
	

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	@Override
	public String toString() {
		String vSep = "";
		String vLspace = "\n\t \t";
		StringBuilder vTxt = new StringBuilder();

		vTxt.append("\n \t[Boutique : id = ");
		vTxt.append(this.getId());
		vTxt.append(vLspace);

		vSep = "";

		return vTxt.append("\t]").toString();
	}

}
