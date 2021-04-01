package com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

// TODO: Auto-generated Javadoc
/**
 * The Class PartieInteresseEntite.
 *
 * @author $mohamed
 */

@Entity
@Table(name = IConstante.TABLE_PARTIE_INTERESSEE)
public class PartieInteresseEntite implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9085241173499739361L;

	/** Identifiant technique. */
	/** L'id de la table. */
	@Id
	@SequenceGenerator(name = "PARTIE_INTERESSE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_PARTIE_INTERESSEE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTIE_INTERESSE_ID_GENERATOR")
	private Long id;

	/** The ref. */
	@Column(name = "ref")
	private String reference;

	/** The raison sociale. */
	@Column(name = "raison_sociale")
	private String raisonSociale;

	/** The abreviation. */
	@Column(name = "abreviation")
	private String abreviation;

	/** The activite. */
	@Column(name = "activite")
	private String activite;

	/** The observation. */
	@Column(name = "observation")
	private String observation;

	/** The devise. */
	@Column(name = "devise")
	private String devise;

	/** The date introduction. */
	@Column(name = "date_introduction")
	private Calendar dateIntroduction;

	/** The matr fiscal. */
	@Column(name = "matr_fiscal")
	private String matrFiscal;

	/** The regime commercial. */
	@Column(name = "reg_com")
	private String regimeCommercial;

	/** The code douane. */
	@Column(name = "code_douane")
	private String codeDouane;

	/** The adresse. */
	@Column(name = "adresse")
	private String adresse;

	/** The email. */
	@Column(name = "email")
	private String email;

	/** The telephone. */
	@Column(name = "telephone")
	private String telephone;

	/** The fax. */
	@Column(name = "fax")
	private String fax;

	/** The actif. */
	@Column(name = "actif")
	private Boolean actif;

	/** The famille entite. */
	@Column(name = "pi_famillepi_id")
	private Long famillePartieInteressee;

	/** The type entite. */
	@Column(name = "pi_typepi_id")
	private Long typePartieInteressee;

	/** The site entite. */
	@Column(name = "pi_com_site_id")
	private Long sitePartieInteressee;

	/** The categorie entite. */
	@Column(name = "pi_categorie_id")
	private Long categoriePartieInteressee;
	
	

	
	

	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** The date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;

	/** The type entite. */
	@Column(name = "pi_region_id")
	private Long regionId;

	/** The document entites. */
	@OneToMany(mappedBy = "partieInteresse", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<DocumentEntite> documentEntites;

	/** The represantent entites. */
	@OneToMany(mappedBy = "partieInteresse", cascade = CascadeType.ALL)
	private Set<RepresentantEntite> representantEntites;
	
	
	/** The GroupeClient. */
	@Column(name = "pi_groupe_client_id")
	private Long groupeClientId;
	
	
	/** The passager. */
	@Column(name = "passager")
	private Boolean passager;
	
	
	
	
	@Column(name = "boutique_id")
	private Long boutiqueId;
	
	
	/** The telephone mobile. */
	@Column(name = "telephone_mobile")
	private String telephoneMobile;
	
	/** The payement terme. */
	@Column(name = "payement_terme")
	private String payementTerme;
	
	
	/** Thepi_compte_comptable_id. */
	@Column(name = "pi_compte_comptable_id")
	private Long compteComptablePartieInteressee;
	

	
	/** The banque */
	@Column(name = "banque_id")
	private Long banqueId;
	

	/** The banque bancaire */
	@Column(name = "code_bancaire")
	private String codeBancaire;
	
	/** The email2. */
	@Column(name = "email2")
	private String email2;
	
	@Column(name = "code_produit")
	private String codeProduit;
	
	
	
	@Column(name = "nature")
	private String nature;
	
	
	
	@Column(name = "devise_id")
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

	public Long getCompteComptablePartieInteressee() {
		return compteComptablePartieInteressee;
	}

	public void setCompteComptablePartieInteressee(Long compteComptablePartieInteressee) {
		this.compteComptablePartieInteressee = compteComptablePartieInteressee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/********* constructeur partieInteresseCache **********/
	public PartieInteresseEntite(Long id, String abreviation,
			Long famillePartieInteressee) {
		this.id = id;
		this.abreviation = abreviation;
		this.famillePartieInteressee = famillePartieInteressee;
	}

	/******** constructeur globale ********/
	public PartieInteresseEntite() {

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
	 * Accesseur en lecture de l'attribut <code>matrFiscal</code>.
	 * 
	 * @return String L'attribut matrFiscal à lire.
	 */
	public String getMatrFiscal() {
		return matrFiscal;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>matrFiscal</code>.
	 *
	 * @param matrFiscal
	 *            L'attribut matrFiscal à modifier.
	 */
	public void setMatrFiscal(String matrFiscal) {
		this.matrFiscal = matrFiscal;
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
	 * @return the devise
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * @param devise
	 *            the devise to set
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
	 * Accesseur en lecture de l'attribut <code>blSuppression</code>.
	 * 
	 * @return boolean L'attribut blSuppression à lire.
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>blSuppression</code>.
	 *
	 * @param blSuppression
	 *            L'attribut blSuppression à modifier.
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>dateSuppression</code>.
	 * 
	 * @return Calendar L'attribut dateSuppression à lire.
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>dateSuppression</code>.
	 *
	 * @param dateSuppression
	 *            L'attribut dateSuppression à modifier.
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>dateCreation</code>.
	 * 
	 * @return Calendar L'attribut dateCreation à lire.
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>dateCreation</code>.
	 *
	 * @param dateCreation
	 *            L'attribut dateCreation à modifier.
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>dateModification</code>.
	 * 
	 * @return Calendar L'attribut dateModification à lire.
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>dateModification</code>.
	 *
	 * @param dateModification
	 *            L'attribut dateModification à modifier.
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>documentEntites</code>.
	 * 
	 * @return Set<DocumentEntite> L'attribut documentEntites à lire.
	 */
	public Set<DocumentEntite> getDocumentEntites() {
		return documentEntites;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>documentEntites</code>.
	 *
	 * @param documentEntites
	 *            L'attribut documentEntites à modifier.
	 */
	public void setDocumentEntites(Set<DocumentEntite> documentEntites) {
		this.documentEntites = documentEntites;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>representantEntites</code>.
	 * 
	 * @return Set<RepresentantEntite> L'attribut representantEntites à lire.
	 */
	public Set<RepresentantEntite> getRepresentantEntites() {
		return representantEntites;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>representantEntites</code>.
	 *
	 * @param representantEntites
	 *            L'attribut representantEntites à modifier.
	 */
	public void setRepresentantEntites(
			Set<RepresentantEntite> representantEntites) {
		this.representantEntites = representantEntites;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}
	
	
	

}
