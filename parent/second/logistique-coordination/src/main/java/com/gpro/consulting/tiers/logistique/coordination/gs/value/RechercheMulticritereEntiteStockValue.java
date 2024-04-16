package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;
import java.util.List;

public class RechercheMulticritereEntiteStockValue {
	private String typeArticle; //done
	private String familleArticle ;  //done
	private String refArticle; //done
	private String article; //done
	private String grosseur; //done
	private String metrage; //done
	private String matiere; //done
	private Calendar dateA; //Commenté
	
	private Calendar dateDu;
	private Double quantite; //done
	private String magasin; //done
	private String emplacement; //done
	private String site; //done
	private String zoneDispo; //done
	private String sousFamilleArticle ;  //done
	private String operateurQuantite; //done
	
	//TODO 
	/** Not used **/
	private String familleProduit;
	private String sousFamilleProduit;
	private String refProduit;
	private String produit;
	private String operateurZoneDispo;	
	// mouvement = non -> etat non mouvementes
	private String mouvement;
	
	
	private String numeroBonEntree;
	
	private String orderBy;
	private Double stockMin;
	
	
	
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	private List<Long> ids;
	
	private String grammageArticle;
	private String dimensionArticle;
	
	
	
	public Calendar getDateA() {
		return dateA;
	}
	public void setDateA(Calendar dateA) {
		this.dateA = dateA;
	}
	public Calendar getDateDu() {
		return dateDu;
	}
	public void setDateDu(Calendar dateDu) {
		this.dateDu = dateDu;
	}
	public String getGrammageArticle() {
		return grammageArticle;
	}
	public void setGrammageArticle(String grammageArticle) {
		this.grammageArticle = grammageArticle;
	}
	public String getDimensionArticle() {
		return dimensionArticle;
	}
	public void setDimensionArticle(String dimenssionArticle) {
		this.dimensionArticle = dimenssionArticle;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public String getNumeroBonEntree() {
		return numeroBonEntree;
	}
	public void setNumeroBonEntree(String numeroBonEntree) {
		this.numeroBonEntree = numeroBonEntree;
	}
	public String getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(String typeArticle) {
		this.typeArticle = typeArticle;
	}
	public String getFamilleArticle() {
		return familleArticle;
	}
	public void setFamilleArticle(String familleArticle) {
		this.familleArticle = familleArticle;
	}
	public String getRefArticle() {
		return refArticle;
	}
	public void setRefArticle(String refArticle) {
		this.refArticle = refArticle;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getGrosseur() {
		return grosseur;
	}
	public void setGrosseur(String grosseur) {
		this.grosseur = grosseur;
	}
	public String getMetrage() {
		return metrage;
	}
	public void setMetrage(String metrage) {
		this.metrage = metrage;
	}
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public String getFamilleProduit() {
		return familleProduit;
	}
	public void setFamilleProduit(String familleProduit) {
		this.familleProduit = familleProduit;
	}
	public String getSousFamilleProduit() {
		return sousFamilleProduit;
	}
	public void setSousFamilleProduit(String sousFamilleProduit) {
		this.sousFamilleProduit = sousFamilleProduit;
	}
	public String getRefProduit() {
		return refProduit;
	}
	public void setRefProduit(String refProduit) {
		this.refProduit = refProduit;
	}
	public String getProduit() {
		return produit;
	}
	public void setProduit(String produit) {
		this.produit = produit;
	}

	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public String getMagasin() {
		return magasin;
	}
	public void setMagasin(String magasin) {
		this.magasin = magasin;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getZoneDispo() {
		return zoneDispo;
	}
	public void setZoneDispo(String zoneDispo) {
		this.zoneDispo = zoneDispo;
	}
	public String getOperateurQuantite() {
		return operateurQuantite;
	}
	public void setOperateurQuantite(String operateurQuantite) {
		this.operateurQuantite = operateurQuantite;
	}
	public String getOperateurZoneDispo() {
		return operateurZoneDispo;
	}
	public void setOperateurZoneDispo(String operateurZoneDispo) {
		this.operateurZoneDispo = operateurZoneDispo;
	}
	public String getSousFamilleArticle() {
		return sousFamilleArticle;
	}
	public void setSousFamilleArticle(String sousFamilleArticle) {
		this.sousFamilleArticle = sousFamilleArticle;
	}
	
	
	public String getMouvement() {
		return mouvement;
	}
	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RechercheMulticritereEntiteStockValue [");
		if (typeArticle != null)
			builder.append("typeArticle=").append(typeArticle).append(", ");
		if (familleArticle != null)
			builder.append("familleArticle=").append(familleArticle)
					.append(", ");
		if (refArticle != null)
			builder.append("refArticle=").append(refArticle).append(", ");
		if (article != null)
			builder.append("article=").append(article).append(", ");
		if (grosseur != null)
			builder.append("grosseur=").append(grosseur).append(", ");
		if (metrage != null)
			builder.append("metrage=").append(metrage).append(", ");
		if (matiere != null)
			builder.append("matiere=").append(matiere).append(", ");
		if (familleProduit != null)
			builder.append("familleProduit=").append(familleProduit)
					.append(", ");
		if (sousFamilleProduit != null)
			builder.append("sousFamilleProduit=").append(sousFamilleProduit)
					.append(", ");
		if (refProduit != null)
			builder.append("refProduit=").append(refProduit).append(", ");
		if (produit != null)
			builder.append("produit=").append(produit).append(", ");
		if (dateDu != null)
			builder.append("date=").append(dateDu).append(", ");
		if (dateA != null)
			builder.append("date=").append(dateA).append(", ");
		if (quantite != null)
			builder.append("quantite=").append(quantite).append(", ");
		if (magasin != null)
			builder.append("magasin=").append(magasin).append(", ");
		if (emplacement != null)
			builder.append("emplacement=").append(emplacement).append(", ");
		if (site != null)
			builder.append("site=").append(site).append(", ");
		if (zoneDispo != null)
			builder.append("zoneDispo=").append(zoneDispo).append(", ");
		if (sousFamilleArticle != null)
			builder.append("sousFamilleArticle=").append(sousFamilleArticle)
					.append(", ");
		if (operateurQuantite != null)
			builder.append("operateurQuantite=").append(operateurQuantite)
					.append(", ");
		if (operateurZoneDispo != null)
			builder.append("operateurZoneDispo=").append(operateurZoneDispo);
		builder.append("]");
		return builder.toString();
	}
	
	
}
