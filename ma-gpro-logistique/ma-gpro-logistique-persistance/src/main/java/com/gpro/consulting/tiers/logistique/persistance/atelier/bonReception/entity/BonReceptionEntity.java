package com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity;

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

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * Entité du Bon de réception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
@Entity
@Table(name = IConstanteLogistique.TABLE_BON_RECEPTION)
public class BonReceptionEntity implements Serializable {
	private static final long serialVersionUID = -5816155382433783418L;

	/** Id */
	@Id
	@SequenceGenerator(name = "BON_RECEPTION_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_BON_RECEPTION)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BON_RECEPTION_ID_GENERATOR")
	private Long id;

	/** Référence. */
	@Column(name = "reference")
	private String reference;

	/** Observations. */
	@Column(name = "observations")
	private String observations;

	/** Client . */
	@Column(name = "bc_client")
	private String bcClient;

	/** La date d'introduction. */
	@Column(name = "date_introduction")
	private Calendar dateIntroduction;

	/** La date d'introduction. */
	@Column(name = "date_livraison")
	private Calendar dateLivraison;

	/** Flag de suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** la date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** la date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** la date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;

	/** Version */
	@Column(name = "version")
	private Long version;

	/** Metrage */
	@Column(name = "metrage_annonce")
	private Double metrageAnnonce;

	/** Metrage trouvé */
	@Column(name = "metrage_trouve")
	private Double metrageTrouve;

	/** Poids */
	@Column(name = "poids_annonce")
	private Double poidsAnnonce;

	/** Poids trouvé */
	@Column(name = "poids_trouve")
	private Double poidsTrouve;

	/** Nombre de rouleau */
	@Column(name = "nbr_rouleau")
	private Long nombreRouleau;

	/** Laize Fini */
	@Column(name = "laize_fini")
	private Double laizeFini;

	/** Etat */
	@Column(name = "etat")
	private String etat;

	/** Produit */
	@Column(name = "eb_produit_id")
	private Long produit;

	/** Partie Interessee */
	@Column(name = "pi_partieint_id")
	private Long partieInteressee;

	/** Les Rouleaux. */
	@OneToMany(mappedBy = "bonReception", cascade = CascadeType.ALL)
	private Set<RouleauEcruEntity> listeRouleauxEcru;

	/** Les traitements. */
	@OneToMany(mappedBy = "bonReception", cascade = CascadeType.ALL)
	private Set<ReceptionTraitementEntity> listeTraitements;

	//added on 20/01/2016, by Wahid Gazzah
	@Column(name = "FINI")
	private Boolean fini;
	
	

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
	 * Accesseur en lecture de l'attribut <code>observations</code>.
	 * 
	 * @return String L'attribut observations à lire.
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>observations</code>.
	 *
	 * @param observations
	 *            L'attribut observations à modifier.
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>bcClient</code>.
	 * 
	 * @return String L'attribut bcClient à lire.
	 */
	public String getBcClient() {
		return bcClient;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>bcClient</code>.
	 *
	 * @param bcClient
	 *            L'attribut bcClient à modifier.
	 */
	public void setBcClient(String bcClient) {
		this.bcClient = bcClient;
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
	 * Accesseur en lecture de l'attribut <code>dateLivraison</code>.
	 * 
	 * @return Calendar L'attribut dateLivraison à lire.
	 */
	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>dateLivraison</code>.
	 *
	 * @param dateLivraison
	 *            L'attribut dateLivraison à modifier.
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
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
	 * Accesseur en lecture de l'attribut <code>metrageAnnonce</code>.
	 * 
	 * @return Double L'attribut metrageAnnonce à lire.
	 */
	public Double getMetrageAnnonce() {
		return metrageAnnonce;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>metrageAnnonce</code>.
	 *
	 * @param metrageAnnonce
	 *            L'attribut metrageAnnonce à modifier.
	 */
	public void setMetrageAnnonce(Double metrageAnnonce) {
		this.metrageAnnonce = metrageAnnonce;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>metrageTrouve</code>.
	 * 
	 * @return Double L'attribut metrageTrouve à lire.
	 */
	public Double getMetrageTrouve() {
		return metrageTrouve;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>metrageTrouve</code>.
	 *
	 * @param metrageTrouve
	 *            L'attribut metrageTrouve à modifier.
	 */
	public void setMetrageTrouve(Double metrageTrouve) {
		this.metrageTrouve = metrageTrouve;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>poidsAnnonce</code>.
	 * 
	 * @return Double L'attribut poidsAnnonce à lire.
	 */
	public Double getPoidsAnnonce() {
		return poidsAnnonce;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>poidsAnnonce</code>.
	 *
	 * @param poidsAnnonce
	 *            L'attribut poidsAnnonce à modifier.
	 */
	public void setPoidsAnnonce(Double poidsAnnonce) {
		this.poidsAnnonce = poidsAnnonce;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>poidsTrouve</code>.
	 * 
	 * @return Double L'attribut poidsTrouve à lire.
	 */
	public Double getPoidsTrouve() {
		return poidsTrouve;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>poidsTrouve</code>.
	 *
	 * @param poidsTrouve
	 *            L'attribut poidsTrouve à modifier.
	 */
	public void setPoidsTrouve(Double poidsTrouve) {
		this.poidsTrouve = poidsTrouve;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>nombreRouleau</code>.
	 * 
	 * @return Long L'attribut nombreRouleau à lire.
	 */
	public Long getNombreRouleau() {
		return nombreRouleau;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>nombreRouleau</code>.
	 *
	 * @param nombreRouleau
	 *            L'attribut nombreRouleau à modifier.
	 */
	public void setNombreRouleau(Long nombreRouleau) {
		this.nombreRouleau = nombreRouleau;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>laizeFini</code>.
	 * 
	 * @return Double L'attribut laizeFini à lire.
	 */
	public Double getLaizeFini() {
		return laizeFini;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>laizeFini</code>.
	 *
	 * @param laizeFini
	 *            L'attribut laizeFini à modifier.
	 */
	public void setLaizeFini(Double laizeFini) {
		this.laizeFini = laizeFini;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>etat</code>.
	 * 
	 * @return String L'attribut etat à lire.
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>etat</code>.
	 *
	 * @param etat
	 *            L'attribut etat à modifier.
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>produit</code>.
	 * 
	 * @return Long L'attribut produit à lire.
	 */
	public Long getProduit() {
		return produit;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>produit</code>.
	 *
	 * @param produit
	 *            L'attribut produit à modifier.
	 */
	public void setProduit(Long produit) {
		this.produit = produit;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>partieInteressee</code>.
	 * 
	 * @return Long L'attribut partieInteressee à lire.
	 */
	public Long getPartieInteressee() {
		return partieInteressee;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>partieInteressee</code>.
	 *
	 * @param partieInteressee
	 *            L'attribut partieInteressee à modifier.
	 */
	public void setPartieInteressee(Long partieInteressee) {
		this.partieInteressee = partieInteressee;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>listeRouleauxEcru</code>.
	 * 
	 * @return Set<RouleauEcruEntity> L'attribut listeRouleauxEcru à lire.
	 */
	public Set<RouleauEcruEntity> getListeRouleauxEcru() {
		return listeRouleauxEcru;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>listeRouleauxEcru</code>.
	 *
	 * @param listeRouleauxEcru
	 *            L'attribut listeRouleauxEcru à modifier.
	 */
	public void setListeRouleauxEcru(Set<RouleauEcruEntity> listeRouleauxEcru) {
		this.listeRouleauxEcru = listeRouleauxEcru;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>listeTraitements</code>.
	 * 
	 * @return Set<ReceptionTraitementEntity> L'attribut listeTraitements à
	 *         lire.
	 */
	public Set<ReceptionTraitementEntity> getListeTraitements() {
		return listeTraitements;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>listeTraitements</code>.
	 *
	 * @param listeTraitements
	 *            L'attribut listeTraitements à modifier.
	 */
	public void setListeTraitements(
			Set<ReceptionTraitementEntity> listeTraitements) {
		this.listeTraitements = listeTraitements;
	}

	public Boolean isFini() {
		return fini;
	}

	public void setFini(Boolean fini) {
		this.fini = fini;
	}

}
