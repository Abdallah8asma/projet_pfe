package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.entity.BonInventaireFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.entity.BonSortieFiniEntity;

/**
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
@Entity
@Table(name=IConstanteLogistique.TABLE_GL_ROULEAUEFINI)
public class RouleauFiniEntity implements Serializable{
	private static final long serialVersionUID = 7505955537667008484L;
	
	@Id
	@SequenceGenerator(name="ROULEAUFINI_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_ROULEAUFINI,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROULEAUFINI_ID_GENERATOR")
    private Long id;
	
	@Column(name="REFERENCE")
	private String reference;
	
	@Column(name="METRAGE")
	private Double metrage;
	
	@Column(name="POIDS")
	private Double poids;
	
	@Column(name="CHOIX")
	private Long choix;
	
	@Column(name="CODE_BARRE")
	private String codeBarre;
	
	@Column(name="EMPLACEMENT")
	private String emplacement;
	
	/** many-to-one association to {@link EntrepotEntity}. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ENTREPOT_ID")
	private EntrepotEntity entrepot;

	@Column(name="EB_PRODUIT_ID")
	private Long produitId;
	
	@Column(name="PI_PARTINT_ID")
	private Long partieInteresseeId;
	
	@Column(name="REFERENCE_MISE")
	private String referenceMise;
	
	@Column(name="BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name="DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name="DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Calendar dateModification;
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name = "LAIZE")
	private Double laize;
	
	//added on 08/01/2016, by Wahid Gazzah
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "BON_SORTIE_ID")
	private BonSortieFiniEntity bonSortie;
	
	
	
	@Column(name="DATE_SORTIE")
	private Calendar dateSortie;
	
	@Column(name="DATE_INTRODUCTION")
	private Calendar dateIntroduction;
	
	@Column(name="METRAGE_MODIF")
	private Boolean metrageModif;
	
	@Column(name="INFO_MODIF")
	private String infoModif;
	
	//added on 20/01/2016, by Wahid Gazzah
	@Column(name = "FINI")
	private Boolean fini;
	
	@Column(name = "INFO")
	private String info;
	
	//added on 18/12/2016, by Ghazi Atroussi
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name = "BON_INVENTAIRE_ID")
		private BonInventaireFiniEntity bonInventaire;
		
	@Column(name="DATE_INVENTAIRE")
	private Calendar dateInventaire;
	
	
	
	
	
	
	@Column(name="production")
	private Boolean production;
	
	
	@Column(name="type_of")
	private String typeOf;
	
	@Column(name="user_id")
	private Long userId;
	
	
	@Column(name = "responsable")
	private String responsable;
	
	
	@Column(name="DATE_SORTIE_REEL")
	private Calendar dateSortieReel;
	
	@Column(name="user_id_expedition")
	private Long userIdExpedition;
	
	
	@Column(name = "responsable_expedition")
	private String responsableExpedition;
	
	
	
	@Column(name="PREMIER_METRAGE")
	private Double premierMetrage;
	
	
	
	
	@Column(name="number_of_box")
	private Long numberOfBox;
	
	
	
	
	
	
	
	public Long getNumberOfBox() {
		return numberOfBox;
	}

	public void setNumberOfBox(Long numberOfBox) {
		this.numberOfBox = numberOfBox;
	}

	public Boolean getProduction() {
		return production;
	}

	public void setProduction(Boolean production) {
		this.production = production;
	}

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

	public Calendar getDateSortieReel() {
		return dateSortieReel;
	}

	public void setDateSortieReel(Calendar dateSortieReel) {
		this.dateSortieReel = dateSortieReel;
	}

	public Long getUserIdExpedition() {
		return userIdExpedition;
	}

	public void setUserIdExpedition(Long userIdExpedition) {
		this.userIdExpedition = userIdExpedition;
	}

	public String getResponsableExpedition() {
		return responsableExpedition;
	}

	public void setResponsableExpedition(String responsableExpedition) {
		this.responsableExpedition = responsableExpedition;
	}

	public Double getPremierMetrage() {
		return premierMetrage;
	}

	public void setPremierMetrage(Double premierMetrage) {
		this.premierMetrage = premierMetrage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public EntrepotEntity getEntrepot() {
		return entrepot;
	}

	/**
	 * @param entrepot the entrepot to set
	 */
	public void setEntrepot(EntrepotEntity entrepot) {
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

	public BonSortieFiniEntity getBonSortie() {
		return bonSortie;
	}

	public void setBonSortie(BonSortieFiniEntity bonSortie) {
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

	public Boolean getMetrageModif() {
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

	public Boolean getBlSuppression() {
		return blSuppression;
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

	public RouleauFiniEntity(String referenceMise) {
		super();
		this.referenceMise = referenceMise;
	}

	public RouleauFiniEntity() {
		super();
	}

	/**
	 * @return the bonInventaire
	 */
	public BonInventaireFiniEntity getBonInventaire() {
		return bonInventaire;
	}

	/**
	 * @param bonInventaire the bonInventaire to set
	 */
	public void setBonInventaire(BonInventaireFiniEntity bonInventaire) {
		this.bonInventaire = bonInventaire;
	}

	/**
	 * @return the fini
	 */
	public Boolean getFini() {
		return fini;
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
