package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class ProduitValue.
 * 
 * @author med
 */
public class ProduitValue implements Comparable<ProduitValue> {

	private Long id;
	private Long siteId;
	private String siteEntiteDesignation;
	private Long sousFamilleId;
	private String sousFamilleDesignation;
	private String familleDesignation;
	private String uidImage;
	private Long partieIntersseId;
	private String partieIntersseDesignation;
	private String reference;
	private String designation;
	private Double prixUnitaire;
	private String etat;
	private String composition;
	private Calendar dateIntroduction;
	private Double quantite;
	private Double prixAchat;
	private Double prixSpecial;

	private Double tva;

	private boolean serialisable;

	private String description;

	private Long groupeClientId;

	private Double prixVenteTTC;

	private Boolean retour;

	private Double remise;

	private Long boutiqueId;

	private String couleur;

	private String numero;

	private String referenceFournisseur;

	private Double prixAchatTTC;

	private boolean stock;

	private String superFamilleDesignation;
	
	
	

	private String dimension;
	
	
	private String grammage;
	

	private String numerotation;
	

	private String nbrPause;
	
	
	private String nature;
	
	
	private Long compteComptableId;
	
	
	private String compteComptableDesignation;
	
	
	
	private boolean fodec;
	

	// added by zeineb g
	private Set<ArticleProduitValue> articleProduits = new HashSet<ArticleProduitValue>();
	// added by zeineb g
	private Set<ArticleProduitValue> materialProduits = new HashSet<ArticleProduitValue>();
	
	
	
	
	
	
	
	
	//column from thermo

	
	private Long cavity;
	
	
	//ADDED BY zeineb g
	/*** color tag **//////
	private String colorTag;
		
	/*** gross weight **//////
	private Double grossWeight;
		
	/*** net weight **//////
	private Double netWeight;
		
	/*** moule **//////
	private String moule;
		 
	/*** JIG **//////
	private String jig;
		 
		
	/*** gty box **//////
	private Long qtyBox;
		
	/*** cliches **//////
	private String cliches;
		
	private String refPrixUnitaire;
	
	
	
	
	
	private Long devise;
	
	private String pantone;
	
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

	public Long getCavity() {
		return cavity;
	}

	public void setCavity(Long cavity) {
		this.cavity = cavity;
	}

	public String getColorTag() {
		return colorTag;
	}

	public void setColorTag(String colorTag) {
		this.colorTag = colorTag;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public String getMoule() {
		return moule;
	}

	public void setMoule(String moule) {
		this.moule = moule;
	}

	public String getJig() {
		return jig;
	}

	public void setJig(String jig) {
		this.jig = jig;
	}

	public Long getQtyBox() {
		return qtyBox;
	}

	public void setQtyBox(Long qtyBox) {
		this.qtyBox = qtyBox;
	}

	public String getCliches() {
		return cliches;
	}

	public void setCliches(String cliches) {
		this.cliches = cliches;
	}

	public String getRefPrixUnitaire() {
		return refPrixUnitaire;
	}

	public void setRefPrixUnitaire(String refPrixUnitaire) {
		this.refPrixUnitaire = refPrixUnitaire;
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

	public String getCompteComptableDesignation() {
		return compteComptableDesignation;
	}

	public void setCompteComptableDesignation(String compteComptableDesignation) {
		this.compteComptableDesignation = compteComptableDesignation;
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

	public Set<ArticleProduitValue> getArticleProduits() {
		return articleProduits;
	}

	public void setArticleProduits(Set<ArticleProduitValue> articleProduits) {
		this.articleProduits = articleProduits;
	}

	public Set<ArticleProduitValue> getMaterialProduits() {
		return materialProduits;
	}

	public void setMaterialProduits(Set<ArticleProduitValue> materialProduits) {
		this.materialProduits = materialProduits;
	}

	public String getSuperFamilleDesignation() {
		return superFamilleDesignation;
	}

	public void setSuperFamilleDesignation(String superFamilleDesignation) {
		this.superFamilleDesignation = superFamilleDesignation;
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

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
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

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
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

	public Double getPrixSpecial() {
		return prixSpecial;
	}

	public void setPrixSpecial(Double prixSpecial) {
		this.prixSpecial = prixSpecial;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	private Long idTaxe;
	private String unite;
	private String uniteSupplementaire;
	private Boolean fond;
	private Boolean fondSupplementaire;

	private Set<DocumentProduitValue> documentProduits = new HashSet<DocumentProduitValue>();

	// added on 31/03/2016, by Wahid Gazzah
	private String codeCouleur;

	// added on 21/04/2016, by Wahid Gazzah
	private String referenceInterne;

	private Double tauxTVA;

	@Override
	public int compareTo(ProduitValue o) {
		ProduitValue element = (ProduitValue) o;
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdTaxe() {
		return idTaxe;
	}

	public void setIdTaxe(Long idTaxe) {
		this.idTaxe = idTaxe;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getSiteEntiteDesignation() {
		return siteEntiteDesignation;
	}

	public void setSiteEntiteDesignation(String siteEntiteDesignation) {
		this.siteEntiteDesignation = siteEntiteDesignation;
	}

	public Long getSousFamilleId() {
		return sousFamilleId;
	}

	public void setSousFamilleId(Long sousFamilleId) {
		this.sousFamilleId = sousFamilleId;
	}

	public String getSousFamilleDesignation() {
		return sousFamilleDesignation;
	}

	public void setSousFamilleDesignation(String sousFamilleDesignation) {
		this.sousFamilleDesignation = sousFamilleDesignation;
	}

	public String getFamilleDesignation() {
		return familleDesignation;
	}

	public void setFamilleDesignation(String familleDesignation) {
		this.familleDesignation = familleDesignation;
	}

	public String getUidImage() {
		return uidImage;
	}

	public void setUidImage(String uidImage) {
		this.uidImage = uidImage;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	public String getPartieIntersseDesignation() {
		return partieIntersseDesignation;
	}

	public void setPartieIntersseDesignation(String partieIntersseDesignation) {
		this.partieIntersseDesignation = partieIntersseDesignation;
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

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
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

	public Set<DocumentProduitValue> getDocumentProduits() {
		return documentProduits;
	}

	public void setDocumentProduits(Set<DocumentProduitValue> documentProduits) {
		this.documentProduits = documentProduits;
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

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
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

	public Double getTauxTVA() {
		return tauxTVA;
	}

	public void setTauxTVA(Double tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	@Override
	public String toString() {
		return "ProduitValue [id=" + id + ", siteId=" + siteId + ", siteEntiteDesignation=" + siteEntiteDesignation
				+ ", sousFamilleId=" + sousFamilleId + ", sousFamilleDesignation=" + sousFamilleDesignation
				+ ", familleDesignation=" + familleDesignation + ", uidImage=" + uidImage + ", partieIntersseId="
				+ partieIntersseId + ", partieIntersseDesignation=" + partieIntersseDesignation + ", reference="
				+ reference + ", designation=" + designation + ", prixUnitaire=" + prixUnitaire + ", etat=" + etat
				+ ", composition=" + composition + ", dateIntroduction=" + dateIntroduction + ", quantite=" + quantite
				+ ", prixAchat=" + prixAchat + ", documentProduits=" + documentProduits + ", codeCouleur=" + codeCouleur
				+ ", referenceInterne=" + referenceInterne + "]";
	}

}