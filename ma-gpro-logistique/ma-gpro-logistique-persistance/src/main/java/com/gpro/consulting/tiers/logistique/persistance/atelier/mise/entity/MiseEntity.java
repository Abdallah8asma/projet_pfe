package com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * Entité de mise
 * 
 * @author $Author: gatroussi $
 * @version $Revision: 0 $
 */

@Entity
@Table(name = IConstanteLogistique.TABLE_MISE)
public class MiseEntity implements Serializable {
	private static final long serialVersionUID = 7470319800368796325L;
	
	@Id
	@SequenceGenerator(name = "MISE_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_MISE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MISE_ID_GENERATOR")
	private Long id;
	@Column(name = "reference")
	private String reference;
	@Column(name = "metrage")
	private Double metrage;
	@Column(name = "poids")
	private Double poids;
	@Column(name = "ref_bonreception")
	private String refBonreception;
	@Column(name = "date_introduction")
	private Calendar dateIntroduction;
	@Column(name = "observations")
	private String observations;
	@Column(name = "code_barre")
	private String codeBarre;
	@Column(name = "nombre_rouleau")
	private Long nombreRouleau;
	@Column(name = "eb_produit_id")
	private Long produitId;
	@Column(name = "pi_partieint_id")
	private Long partieintId;
	@Column(name = "bl_suppression")
	private Boolean blSuppression;
	@Column(name = "date_suppression")
	private Calendar dateSuppression;
	@Column(name = "date_creation")
	private Calendar dateCreation;
	@Column(name = "date_modification")
	private Calendar dateModification;
	@Column(name = "version")
	private String version;
	
	//added on 20/01/2016, by Wahid Gazzah
	@Column(name = "FINI")
	private Boolean fini;
	
	
	//nouvelles column from thermo
	


	/** The poid fini. */
	@Column(name = "poid_fini")
	private Double poidFini;

	/** The quantite. */
	@Column(name = "quantite")
	private Double quantite;

	/** The destination produit. */
	@Column(name = "destination_produit")
	private String destinationProduit;

	/** The statut. */
	@Column(name = "statut")
	private String statut;

	/** The date fin. */
	@Column(name = "date_fin")
	private Calendar dateFin;

	
	@Column(name = "destination")

    private String destination ;
	
	@Column(name = "packaging")

	private String packaging ;
	
	@Column(name = "type_etiquette")

	private String typeEtiquette ;
	
	
	@Column(name = "type_of")

	private String typeOF ;
	
	
	@Column(name = "machine")

	private String machine ;
	
	/** The quantity produced. */
	@Column(name = "qte_produite")
	private Long qteProduite;
	
	
	/** The number of colis. */
	@Column(name = "nb_colis")
	private Long nbrColis;
	
	
	/** The date debut production. */
	@Column(name = "debut_production")
	private Calendar dateDebutProduction;
	
	/** The date fin production. */
	@Column(name = "fin_production")
	private Calendar dateFinProduction;
	
	
	
	/** The shipped quantity . */
	@Column(name = "qte_expedition")
	private Double qteExpedition;
	
	
	/** The number of shipped colis . */
	@Column(name = "nbr_colis_expedition")
	private Long nbrColisExpedition ;
	
	
	
	
	
	@Column(name = "ref_commande")
	private String refCommande ;
	
	/* Les traitements. 
	@OneToMany(mappedBy = "mise", cascade = CascadeType.ALL)
	private Set<TraitementMiseEntity> listeTraitements;
	 */
	
	
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public Double getPoidFini() {
		return poidFini;
	}

	public void setPoidFini(Double poidFini) {
		this.poidFini = poidFini;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public String getDestinationProduit() {
		return destinationProduit;
	}

	public void setDestinationProduit(String destinationProduit) {
		this.destinationProduit = destinationProduit;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Calendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getTypeEtiquette() {
		return typeEtiquette;
	}

	public void setTypeEtiquette(String typeEtiquette) {
		this.typeEtiquette = typeEtiquette;
	}

	public String getTypeOF() {
		return typeOF;
	}

	public void setTypeOF(String typeOF) {
		this.typeOF = typeOF;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public Long getQteProduite() {
		return qteProduite;
	}

	public void setQteProduite(Long qteProduite) {
		this.qteProduite = qteProduite;
	}

	public Long getNbrColis() {
		return nbrColis;
	}

	public void setNbrColis(Long nbrColis) {
		this.nbrColis = nbrColis;
	}

	public Calendar getDateDebutProduction() {
		return dateDebutProduction;
	}

	public void setDateDebutProduction(Calendar dateDebutProduction) {
		this.dateDebutProduction = dateDebutProduction;
	}

	public Calendar getDateFinProduction() {
		return dateFinProduction;
	}

	public void setDateFinProduction(Calendar dateFinProduction) {
		this.dateFinProduction = dateFinProduction;
	}

	public Double getQteExpedition() {
		return qteExpedition;
	}

	public void setQteExpedition(Double qteExpedition) {
		this.qteExpedition = qteExpedition;
	}

	public Long getNbrColisExpedition() {
		return nbrColisExpedition;
	}

	public void setNbrColisExpedition(Long nbrColisExpedition) {
		this.nbrColisExpedition = nbrColisExpedition;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param reference
	 *            the reference to set
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
	 * @param metrage
	 *            the metrage to set
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
	 * @param poids
	 *            the poids to set
	 */
	public void setPoids(Double poids) {
		this.poids = poids;
	}

	/**
	 * @return the refBonreception
	 */
	public String getRefBonreception() {
		return refBonreception;
	}

	/**
	 * @param refBonreception
	 *            the refBonreception to set
	 */
	public void setRefBonreception(String refBonreception) {
		this.refBonreception = refBonreception;
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
	 * @return the codeBarre
	 */
	public String getCodeBarre() {
		return codeBarre;
	}

	/**
	 * @param codeBarre
	 *            the codeBarre to set
	 */
	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
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
	 * @return the produitId
	 */
	public Long getProduitId() {
		return produitId;
	}

	/**
	 * @param produitId
	 *            the produitId to set
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	/**
	 * @return the partieintId
	 */
	public Long getPartieintId() {
		return partieintId;
	}

	/**
	 * @param partieintId
	 *            the partieintId to set
	 */
	public void setPartieintId(Long partieintId) {
		this.partieintId = partieintId;
	}

	/**
	 * @return the blSuppression
	 */
	public Boolean getBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression
	 *            the blSuppression to set
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
	 * @param dateSuppression
	 *            the dateSuppression to set
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
	 * @param dateCreation
	 *            the dateCreation to set
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
	 * @param dateModification
	 *            the dateModification to set
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
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	public Boolean isFini() {
		return fini;
	}

	public void setFini(Boolean fini) {
		this.fini = fini;
	}

	public Boolean getFini() {
		return fini;
	}
	

}
