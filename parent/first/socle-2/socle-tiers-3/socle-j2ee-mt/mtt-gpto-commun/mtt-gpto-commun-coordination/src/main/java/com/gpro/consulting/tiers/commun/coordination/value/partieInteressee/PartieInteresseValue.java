package com.gpro.consulting.tiers.commun.coordination.value.partieInteressee;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
/**
 * The Class PartieInteresseValue.
 */
public class PartieInteresseValue { 

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
	
	/** The document entites. */
	private Set<DocumentValue> documents = new HashSet<DocumentValue>();

	/** The represantent entites. */
	private Set<RepresentantValue> representants = new HashSet<RepresentantValue>();
	
	
	private Long groupeClientId;
	
	/** groupeClientDesignation */
	private String groupeClientDesignation;
	
	
	
	private Boolean passager;
	
	private Long boutiqueId;
	
	/** The telephone mobile. */

	private String telephoneMobile;
	
	/** The payement terme. */

	private String payementTerme;
	
	
	
	private Long compteComptablePartieInteressee;
	
	
	private String compteComptablePartieInteresseeDesignation;
	
	
	private Long banqueId;
	
	private Long banqueDesignation;
	
	
	private String codeBancaire;
	
	
	
	private String email2;
	

	private String codeProduit;
	
	
	
	private String nature;
	
	
	private Long deviseId;

	
	


	public Long getDeviseId() {
		return deviseId;
	}

	public void setDeviseId(Long deviseId) {
		this.deviseId = deviseId;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getCodeBancaire() {
		return codeBancaire;
	}

	public void setCodeBancaire(String codeBancaire) {
		this.codeBancaire = codeBancaire;
	}

	public Long getBanqueId() {
		return banqueId;
	}

	public void setBanqueId(Long banqueId) {
		this.banqueId = banqueId;
	}

	public Long getBanqueDesignation() {
		return banqueDesignation;
	}

	public void setBanqueDesignation(Long banqueDesignation) {
		this.banqueDesignation = banqueDesignation;
	}

	public Long getCompteComptablePartieInteressee() {
		return compteComptablePartieInteressee;
	}

	public void setCompteComptablePartieInteressee(Long compteComptablePartieInteressee) {
		this.compteComptablePartieInteressee = compteComptablePartieInteressee;
	}

	public String getCompteComptablePartieInteresseeDesignation() {
		return compteComptablePartieInteresseeDesignation;
	}

	public void setCompteComptablePartieInteresseeDesignation(String compteComptablePartieInteresseeDesignation) {
		this.compteComptablePartieInteresseeDesignation = compteComptablePartieInteresseeDesignation;
	}

	public String getTelephoneMobile() {
		return telephoneMobile;
	}

	public void setTelephoneMobile(String telephoneMobile) {
		this.telephoneMobile = telephoneMobile;
	}

	public String getPayementTerme() {
		return payementTerme;
	}

	public void setPayementTerme(String payementTerme) {
		this.payementTerme = payementTerme;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Boolean getPassager() {
		return passager;
	}

	public void setPassager(Boolean passager) {
		this.passager = passager;
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
	 * Accesseur en lecture de l'attribut <code>actif</code>.
	 * 
	 * @return boolean L'attribut actif à lire.
	 */
	public Boolean isActif() {
		return actif;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>actif</code>.
	 *
	 * @param actif
	 *            L'attribut actif à modifier.
	 */
	public void setActif(Boolean actif) {
		this.actif = actif;
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
	 * Accesseur en lecture de l'attribut <code>documents</code>.
	 * 
	 * @return Set<DocumentValue> L'attribut documents à lire.
	 */
	public Set<DocumentValue> getDocuments() {
		return documents;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>documents</code>.
	 *
	 * @param documents
	 *            L'attribut documents à modifier.
	 */
	public void setDocuments(Set<DocumentValue> documents) {
		this.documents = documents;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>representants</code>.
	 * 
	 * @return Set<RepresentantValue> L'attribut representants à lire.
	 */
	public Set<RepresentantValue> getRepresentants() {
		return representants;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>representants</code>.
	 *
	 * @param representants
	 *            L'attribut representants à modifier.
	 */
	public void setRepresentants(Set<RepresentantValue> representants) {
		this.representants = representants;
	}

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
	
	
	

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}
	
	

	public String getGroupeClientDesignation() {
		return groupeClientDesignation;
	}

	public void setGroupeClientDesignation(String groupeClientDesignation) {
		this.groupeClientDesignation = groupeClientDesignation;
	}
	
	
	

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	@Override
	public String toString() {
		String vSep = "";
		String vLspace = "\n\t \t";
		StringBuilder vTxt = new StringBuilder();

		vTxt.append("\n \t[Partie Interessee: id = ");
		vTxt.append(this.getId());
		vTxt.append(vLspace);

		vSep = "";

		return vTxt.append("\t]").toString();
	}

}
