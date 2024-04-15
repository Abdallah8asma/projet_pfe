package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

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

/**
 * The Class ProduitEntity.
 * 
 * @author med
 */
@Entity
@Table(name = IConstante.TABLE_PRODUIT)
public class ProduitEntity implements Serializable {

	private static final long serialVersionUID = 641467517751608746L;

	@Id
	@SequenceGenerator(name = "EB_PRODUIT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_PRODUIT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EB_PRODUIT_ID_GENERATOR")
	private Long id;

	@Column(name = "reference")
	private String reference;

	@Column(name = "designation")
	private String designation;
	
	@Column(name = "description")
	private String description;

	@Column(name = "composition")
	private String composition;

	@Column(name = "date_introduction")
	private Calendar dateIntroduction;

	@Column(name = "pi_pi_id")
	private Long partieIntersseId;

	@Column(name = "com_site_id")
	private Long siteId;



	@Column(name = "etat")
	private String etat;

	@Column(name = "uid_image")
	private String uidImage;

	@Column(name = "date_creation")
	private Calendar dateCreation;

	@Column(name = "date_modification")
	private Calendar dateModification;

	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	@Column(name = "bl_suppression")
	private boolean blSuppression;



	@Column(name = "prix_unitaire")
	private Double prixUnitaire;

	// added on 31/03/2016, by Wahid Gazzah
	@Column(name = "CODE_COULEUR")
	private String codeCouleur;

	// added on 21/04/2016, by Wahid Gazzah
	@Column(name = "REFERENCE_INTERNE")
	private String referenceInterne;

	@Column(name = "QUANTITE")
	private Double quantite;

	@Column(name="prix_achat")
	 private Double prixAchat ;
	
	@Column(name="id_taxe")
	 private Long idTaxe ;
	
	@Column(name="unite")
	 private String unite ;
	@Column(name="unite_supplementaire")
	private String uniteSupplementaire;
	@Column(name="fond")
	private Boolean fond;
	@Column(name="fond_supplementaire")
	private Boolean  fondSupplementaire ;
	
	
	
	@Column(name = "tva")
	private Double tva;
	
	
	/** The serialisable. */
	@Column(name = "serialisable")
	private boolean serialisable;
	
	
	
	@Column(name="prix_vente_ttc")
	 private Double prixVenteTTC ;
	
	
	
	@Column(name="retour")
	private Boolean  retour ;
	
	
	@Column(name = "boutique_id")
	private Long boutiqueId;
	
	
	@Column(name = "couleur")
	private String couleur;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "ref_fournisseur")
	private String referenceFournisseur;
	
	@Column(name="prix_achat_ttc")
	 private Double prixAchatTTC ;
	
	/** The serialisable. */
	@Column(name = "stock")
	private boolean stock;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eb_sfprod_id")
	private SousFamilleProduitEntity sousFamille;
	
	
	@Column(name = "dimension")
	private String dimension;
	
	@Column(name = "grammage")
	private String grammage;
	
	@Column(name = "numerotation")
	private String numerotation;
	
	@Column(name = "nbr_pause")
	private String nbrPause;
	
	@Column(name = "nature")
	private String nature;
	
	
	@Column(name = "compte_comptable_id")
	private Long compteComptableId;
	
	/** The fodec. */
	@Column(name = "fodec")
	private boolean fodec;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ArticleProduitEntity> articleProduits;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<DocumentProduitEntity> documentProduits;
	
	
	
	@Column(name = "devise")
	private Long devise;
	
	
	
	@Column(name = "pantone")
	private String pantone;
	
	@Column(name = "format")
	private String format;
	
	
	public String getPantone() {
		return pantone;
	}

	public void setPantone(String pantone) {
		this.pantone = pantone;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Long getDevise() {
		return devise;
	}

	public void setDevise(Long devise) {
		this.devise = devise;
	}

	public boolean isFodec() {
		return fodec;
	}

	public void setFodec(boolean fodec) {
		this.fodec = fodec;
	}

	public Long getCompteComptableId() {
		return compteComptableId;
	}

	public void setCompteComptableId(Long compteComptableId) {
		this.compteComptableId = compteComptableId;
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

	public String getNumerotation() {
		return numerotation;
	}

	public void setNumerotation(String numerotation) {
		this.numerotation = numerotation;
	}

	public String getNbrPause() {
		return nbrPause;
	}

	public void setNbrPause(String nbrPause) {
		this.nbrPause = nbrPause;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Set<ArticleProduitEntity> getArticleProduits() {
		return articleProduits;
	}

	public void setArticleProduits(Set<ArticleProduitEntity> articleProduits) {
		this.articleProduits = articleProduits;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getReferenceFournisseur() {
		return referenceFournisseur;
	}

	public void setReferenceFournisseur(String referenceFournisseur) {
		this.referenceFournisseur = referenceFournisseur;
	}

	public Double getPrixAchatTTC() {
		return prixAchatTTC;
	}

	public void setPrixAchatTTC(Double prixAchatTTC) {
		this.prixAchatTTC = prixAchatTTC;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Boolean getRetour() {
		return retour;
	}

	public void setRetour(Boolean retour) {
		this.retour = retour;
	}

	public Double getPrixVenteTTC() {
		return prixVenteTTC;
	}

	public void setPrixVenteTTC(Double prixVenteTTC) {
		this.prixVenteTTC = prixVenteTTC;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Long getIdTaxe() {
		return idTaxe;
	}

	public void setIdTaxe(Long idTaxe) {
		this.idTaxe = idTaxe;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public SousFamilleProduitEntity getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(SousFamilleProduitEntity sousFamille) {
		this.sousFamille = sousFamille;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getUidImage() {
		return uidImage;
	}

	public void setUidImage(String uidImage) {
		this.uidImage = uidImage;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Set<DocumentProduitEntity> getDocumentProduits() {
		return documentProduits;
	}

	public void setDocumentProduits(Set<DocumentProduitEntity> documentProduits) {
		this.documentProduits = documentProduits;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getCodeCouleur() {
		return codeCouleur;
	}

	public void setCodeCouleur(String codeCouleur) {
		this.codeCouleur = codeCouleur;
	}

	public String getReferenceInterne() {
		return referenceInterne;
	}

	public void setReferenceInterne(String referenceInterne) {
		this.referenceInterne = referenceInterne;
	}

	public Double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public String getUniteSupplementaire() {
		return uniteSupplementaire;
	}

	public void setUniteSupplementaire(String uniteSupplementaire) {
		this.uniteSupplementaire = uniteSupplementaire;
	}

	public Boolean getFond() {
		return fond;
	}

	public void setFond(Boolean fond) {
		this.fond = fond;
	}

	public Boolean getFondSupplementaire() {
		return fondSupplementaire;
	}

	public void setFondSupplementaire(Boolean fondSupplementaire) {
		this.fondSupplementaire = fondSupplementaire;
	}



}