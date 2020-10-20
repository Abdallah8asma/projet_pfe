package com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value;

import java.util.Calendar;
import java.util.List;

/**
 * Classe Valeur du Bon de réception
 * 
 * @author $Author: Ameni
 */
public class BonReceptionValue {

	/** id */
	private Long id;

	/** Produit */
	private Long produit;

	/** Référence. */
	private String reference;

	/** La date d'introduction. */
	private Calendar dateIntroduction;

	/** La date d'introduction. */
	private Calendar dateLivraison;

	/** Observations. */
	private String observations;

	/** Client . */
	private String bcClient;

	/** Metrage */
	private Double metrageAnnonce;

	/** Poids */
	private Double poidsAnnonce;

	/** Metrage trouvé */
	private Double metrageTrouve;

	/** Poids trouvé */
	private Double poidsTrouve;

	/** Nombre de rouleau */
	private Long nombreRouleau;

	/** Laize Fini */
	private Double laizeFini;

	/** Etat */
	private String etat;

	/** Partie Interessee */
	private Long partieInteressee;

	/** Version */
	private Long version;

	/** Les Rouleaux. */
	private List<RouleauEcruValue> listeRouleauxEcru;

	/** Les traitements. */
	private List<ReceptionTraitementValue> listeTraitements;
	
	private Boolean fini;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the produit
	 */
	public Long getProduit() {
		return produit;
	}

	/**
	 * @param produit
	 *            the produit to set
	 */
	public void setProduit(Long produit) {
		this.produit = produit;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the dateIntroduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * @param dateIntroduction
	 *            the dateIntroduction to set
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	/**
	 * @return the dateLivraison
	 */
	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	/**
	 * @param dateLivraison
	 *            the dateLivraison to set
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations
	 *            the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return the bcClient
	 */
	public String getBcClient() {
		return bcClient;
	}

	/**
	 * @param bcClient
	 *            the bcClient to set
	 */
	public void setBcClient(String bcClient) {
		this.bcClient = bcClient;
	}

	/**
	 * @return the metrageAnnonce
	 */
	public Double getMetrageAnnonce() {
		return metrageAnnonce;
	}

	/**
	 * @param metrageAnnonce
	 *            the metrageAnnonce to set
	 */
	public void setMetrageAnnonce(Double metrageAnnonce) {
		this.metrageAnnonce = metrageAnnonce;
	}

	/**
	 * @return the poidsAnnonce
	 */
	public Double getPoidsAnnonce() {
		return poidsAnnonce;
	}

	/**
	 * @param poidsAnnonce
	 *            the poidsAnnonce to set
	 */
	public void setPoidsAnnonce(Double poidsAnnonce) {
		this.poidsAnnonce = poidsAnnonce;
	}

	/**
	 * @return the metrageTrouve
	 */
	public Double getMetrageTrouve() {
		return metrageTrouve;
	}

	/**
	 * @param metrageTrouve
	 *            the metrageTrouve to set
	 */
	public void setMetrageTrouve(Double metrageTrouve) {
		this.metrageTrouve = metrageTrouve;
	}

	/**
	 * @return the poidsTrouve
	 */
	public Double getPoidsTrouve() {
		return poidsTrouve;
	}

	/**
	 * @param poidsTrouve
	 *            the poidsTrouve to set
	 */
	public void setPoidsTrouve(Double poidsTrouve) {
		this.poidsTrouve = poidsTrouve;
	}

	/**
	 * @return the nombreRouleau
	 */
	public Long getNombreRouleau() {
		return nombreRouleau;
	}

	/**
	 * @param nombreRouleau
	 *            the nombreRouleau to set
	 */
	public void setNombreRouleau(Long nombreRouleau) {
		this.nombreRouleau = nombreRouleau;
	}

	/**
	 * @return the laizeFini
	 */
	public Double getLaizeFini() {
		return laizeFini;
	}

	/**
	 * @param laizeFini
	 *            the laizeFini to set
	 */
	public void setLaizeFini(Double laizeFini) {
		this.laizeFini = laizeFini;
	}

	/**
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * @param etat
	 *            the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * @return the partieInteressee
	 */
	public Long getPartieInteressee() {
		return partieInteressee;
	}

	/**
	 * @param partieInteressee
	 *            the partieInteressee to set
	 */
	public void setPartieInteressee(Long partieInteressee) {
		this.partieInteressee = partieInteressee;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>version</code>.
	 * 
	 * @return Long L'attribut version à lire.
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>version</code>.
	 *
	 * @param version
	 *            L'attribut version à modifier.
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return the listeRouleauxEcru
	 */
	public List<RouleauEcruValue> getListeRouleauxEcru() {
		return listeRouleauxEcru;
	}

	/**
	 * @param listeRouleauxEcru
	 *            the listeRouleauxEcru to set
	 */
	public void setListeRouleauxEcru(List<RouleauEcruValue> listeRouleauxEcru) {
		this.listeRouleauxEcru = listeRouleauxEcru;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>listeTraitements</code>.
	 * 
	 * @return Set<ReceptionTraitementValue> L'attribut listeTraitements à lire.
	 */
	public List<ReceptionTraitementValue> getListeTraitements() {
		return listeTraitements;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>listeTraitements</code>.
	 *
	 * @param listeTraitements
	 *            L'attribut listeTraitements à modifier.
	 */
	public void setListeTraitements(
			List<ReceptionTraitementValue> listeTraitements) {
		this.listeTraitements = listeTraitements;
	}
	
	public Boolean isFini() {
		return fini;
	}

	public void setFini(Boolean fini) {
		this.fini = fini;
	}

}
