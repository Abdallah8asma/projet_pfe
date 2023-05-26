package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class ArticleEntite.
 *
 * @author $Ghazi
 */

@Entity
@Table(name = IConstante.TABLE_ARTICLE)
public class ArticleEntite implements Serializable {

	/** The Constant serialVersionUID. */

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1613733127538017927L;

	/** Identifiant technique. */
	  /** L'id de la table. */
	@Id
	@SequenceGenerator(name="ARTICLE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_ARTICLE,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ARTICLE_ID_GENERATOR")
	private Long id;

	/** The ref. */
	@Column(name = "ref")
	private String ref;

	/** The dedignation. */
	@Column(name = "designation")
	private String designation;

	/** The uid_image. */
	@Column(name = "uid_image")
	private String uidImage;

	/** The prix unitaire. */
	@Column(name = "pu")
	private Double pu;

	/** The prix moyen pondere. */
	@Column(name = "pmp")
	private Double pmp;

	/** The producteur. */
	@Column(name = "producteur")
	private String producteur;

	/** The date introduction. */
	@Column(name = "date_introduction")
	private Calendar dateIntroduction;

	/** The laize. */
	@Column(name = "laize")
	private Double laize;

	/** The poids. */
	@Column(name = "poids")
	private Double poids;
	
	/** The tare. */
	@Column(name = "tare")
	private Double tare;

	/** The poids_brut. */
	@Column(name = "poids_brut")
	private Double poidsBrut;


	/** The observation. */
	@Column(name = "observation")
	private String observation;

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

	/** The pi entite. */
	@Column(name = "pi_pi_id")
	private Long  piEntite;

	/** The sousfamille entite. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_sfart_id")
	private SousFamilleArticleEntity sousFamilleArtEntite;

	/** The site entite. */
	@Column(name = "com_site_id")
	private Long siteEntite;

	/** The grosseur entite. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_gros_id")
	private GrosseurEntite grosseurEntite;

	/** The metrage entite. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_metr_id")
	private MetrageEntite metrageEntite;

	/** The unite entite. */
	@Column(name = "eb_unite_id")
	private Long uniteEntite;
	
	/** The matiere entite. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_mat_id")
	private MatiereArticleEntite matiereEntite;	
	
	/**Methode de gestion */////
	@Column(name="methode_gestion")
	private String methodeGestion;
	
	@Column(name="emplacement")
	 private String emplacement ;
	
	
	/** The unite entite. */
	@Column(name = "eb_unite_2_id")
	private Long unite2Entite;
	

	
	
	
	/** The document entites. */
	
    @OneToMany(mappedBy = "article",cascade=CascadeType.ALL)
	private Set<DocumentArticleEntite> documentEntites;

	/** The seuil entites. */
	
	@OneToMany(mappedBy = "article",cascade=CascadeType.ALL)
	private Set<SeuilArticleEntity> seuilEntites;
	
	
	
	@Column(name = "dimension")
	private String dimension;
	
	@Column(name = "grammage")
	private String grammage;
	
	
	@Column(name = "tva")
	private Double tva;
	
	
	@Column(name="pu_ttc")
	 private Double puTTC ;
	
	
	@Column(name="id_taxe")
	 private Long idTaxe ;
	
	
	@Column(name="produit_id")
	 private Long produitId ;
	
	
	@Column(name="nbr_couleur")
	 private String nbrCouleur ;
	
	
	@Column(name = "dimension_papier")
	private String dimensionPapier;
	
	
	@Column(name = "fichier")
	private String fichier;
	
	@Column(name = "nbr_pose")
	private String nbrPose;	
    @Column(name="stock_min")
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

	public String getDimensionPapier() {
		return dimensionPapier;
	}

	public void setDimensionPapier(String dimensionPapier) {
		this.dimensionPapier = dimensionPapier;
	}

	

	public String getNbrCouleur() {
		return nbrCouleur;
	}

	public void setNbrCouleur(String nbrCouleur) {
		this.nbrCouleur = nbrCouleur;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
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

	public Long getIdTaxe() {
		return idTaxe;
	}

	public void setIdTaxe(Long idTaxe) {
		this.idTaxe = idTaxe;
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

	public Long getUnite2Entite() {
		return unite2Entite;
	}

	public void setUnite2Entite(Long unite2Entite) {
		this.unite2Entite = unite2Entite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<SeuilArticleEntity> getSeuilEntites() {
		return seuilEntites;
	}

	public void setSeuilEntites(Set<SeuilArticleEntity> seuilEntites) {
		this.seuilEntites = seuilEntites;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	@Override
	public String toString() {
		return "ArticleEntite [id=" + id + ", ref=" + ref + ", designation=" + designation + ", uidImage=" + uidImage
				+ ", pu=" + pu + ", pmp=" + pmp + ", producteur=" + producteur + ", dateIntroduction="
				+ dateIntroduction + ", laize=" + laize + ", poids=" + poids + ", tare=" + tare + ", poidsBrut="
				+ poidsBrut + ", observation=" + observation + ", blSuppression=" + blSuppression + ", dateSuppression="
				+ dateSuppression + ", dateCreation=" + dateCreation + ", dateModification=" + dateModification
				+ ", piEntite=" + piEntite + ", sousFamilleArtEntite=" + sousFamilleArtEntite + ", siteEntite="
				+ siteEntite + ", grosseurEntite=" + grosseurEntite + ", metrageEntite=" + metrageEntite
				+ ", uniteEntite=" + uniteEntite + ", matiereEntite=" + matiereEntite + ", methodeGestion="
				+ methodeGestion + ", emplacement=" + emplacement + ", documentEntites=" + documentEntites
				+ ", seuilEntites=" + seuilEntites + "]";
	}

	/**
	 * @return the documentEntites
	 */
	public Set<DocumentArticleEntite> getDocumentEntites() {
		return documentEntites;
	}

	/**
	 * @param documentEntites the documentEntites to set
	 */
	public void setDocumentEntites(Set<DocumentArticleEntite> documentEntites) {
		this.documentEntites = documentEntites;
	}

	/***constructeur***/
	public ArticleEntite(){
		
	}
	
	/***constructeurarticleCache***/
	public ArticleEntite(Long id,String reference,String designation){
		this.id=id;
		this.ref=reference;
		this.designation=designation;
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
	 * @return the pu
	 */
	public Double getPu() {
		return pu;
	}

	/**
	 * @param pu the pu to set
	 */
	public void setPu(Double pu) {
		this.pu = pu;
	}

	/**
	 * @return the pmp
	 */
	public Double getPmp() {
		return pmp;
	}

	/**
	 * @param pmp the pmp to set
	 */
	public void setPmp(Double pmp) {
		this.pmp = pmp;
	}

	/**
	 * @return the producteur
	 */
	public String getProducteur() {
		return producteur;
	}

	/**
	 * @param producteur the producteur to set
	 */
	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}

	/**
	 * @return the dateIntroduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * @param dateIntroduction the dateIntroduction to set
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
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
	 * @return the methodeGestion
	 */
	public String getMethodeGestion() {
		return methodeGestion;
	}

	/**
	 * @param methodeGestion the methodeGestion to set
	 */
	public void setMethodeGestion(String methodeGestion) {
		this.methodeGestion = methodeGestion;
	}

	/**
	 * @return the tare
	 */
	public Double getTare() {
		return tare;
	}

	/**
	 * @param tare the tare to set
	 */
	public void setTare(Double tare) {
		this.tare = tare;
	}

	/**
	 * @return the poidsBrut
	 */
	public Double getPoidsBrut() {
		return poidsBrut;
	}

	/**
	 * @param poidsBrut the poidsBrut to set
	 */
	public void setPoidsBrut(Double poidsBrut) {
		this.poidsBrut = poidsBrut;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the blSuppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(boolean blSuppression) {
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
	 * @return the piEntite
	 */
	public Long getPiEntite() {
		return piEntite;
	}

	/**
	 * @param piEntite the piEntite to set
	 */
	public void setPiEntite(Long piEntite) {
		this.piEntite = piEntite;
	}

	

	public SousFamilleArticleEntity getSousFamilleArtEntite() {
		return sousFamilleArtEntite;
	}

	public void setSousFamilleArtEntite(
			SousFamilleArticleEntity sousFamilleArtEntite) {
		this.sousFamilleArtEntite = sousFamilleArtEntite;
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


	public GrosseurEntite getGrosseurEntite() {
		return grosseurEntite;
	}

	public void setGrosseurEntite(GrosseurEntite grosseurEntite) {
		this.grosseurEntite = grosseurEntite;
	}

	public MetrageEntite getMetrageEntite() {
		return metrageEntite;
	}

	public void setMetrageEntite(MetrageEntite metrageEntite) {
		this.metrageEntite = metrageEntite;
	}

	/**
	 * @return the uniteEntite
	 */
	public Long getUniteEntite() {
		return uniteEntite;
	}

	/**
	 * @param uniteEntite the uniteEntite to set
	 */
	public void setUniteEntite(Long uniteEntite) {
		this.uniteEntite = uniteEntite;
	}

	

	public MatiereArticleEntite getMatiereEntite() {
		return matiereEntite;
	}

	public void setMatiereEntite(MatiereArticleEntite matiereEntite) {
		this.matiereEntite = matiereEntite;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}

	public Double getStockMin() {
		return stockMin;
	}

	public void setStockMin(Double stockMin) {
		this.stockMin = stockMin;
	}

	



}
