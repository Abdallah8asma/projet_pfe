package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;

/**
 * The Class EntiteStockValue.
 */
public class EntiteStockValue implements Comparable<Object>{
	
	private Long id;
	private Long coneReserve;
	private Long conesActuel;
	private Calendar dateEntree;
	private Calendar dateLot;
	private String emplacement;
	private Long finconeActuel;
	private Long finconeReserve;
	private String libelleArticle;
	private Double poidsActuel;
	private Double poidsReserve;
	private Double qteActuelle;
	private Double qteReservee;
	private String referenceArticle;
	private String referenceContenaire;
	private String referenceLot;
	private Long rouleauxActuel;
	private Long rouleauxReserve;
    private Long article ;
    private Long magasin;
    private String designationMagasin;
	private Double equivalenceCone;
	private Double prixUnitaire;
	private Double prixTotal;
	private String familleArticle;
	private Double pmp;
	private String OA ;
	
	private String numeroBonEntree;
	
	private Double qteEntree;
	
	
	private String grammageArticle;
	
	private String dimensionArticle;
	
	
	private String producteurArticle;
	private Double stockMin;
	
	
	
	
	
	
	public String getProducteurArticle() {
		return producteurArticle;
	}

	public void setProducteurArticle(String producteurArticle) {
		this.producteurArticle = producteurArticle;
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

	public void setDimensionArticle(String dimensionArticle) {
		this.dimensionArticle = dimensionArticle;
	}

	public Double getQteEntree() {
		return qteEntree;
	}

	public void setQteEntree(Double qteEntree) {
		this.qteEntree = qteEntree;
	}

	public String getNumeroBonEntree() {
		return numeroBonEntree;
	}

	public void setNumeroBonEntree(String numeroBonEntree) {
		this.numeroBonEntree = numeroBonEntree;
	}

	public EntiteStockValue() {
		// TODO Auto-generated constructor stub
	}
	
    public EntiteStockValue(Long id, Double qteActuelle) {
		super();
		this.id = id;
		this.qteActuelle = qteActuelle;
	}

	public EntiteStockValue(Long id, Long conesActuel, Long finconeActuel, Double poidsActuel) {
		super();
		this.id = id;
		this.conesActuel = conesActuel;
		this.finconeActuel = finconeActuel;
		this.poidsActuel = poidsActuel;
	}

	public EntiteStockValue(Long id, Double qteActuelle, Long rouleauxActuel) {
		super();
		this.id = id;
		this.qteActuelle = qteActuelle;
		this.rouleauxActuel = rouleauxActuel;
	}

	public String getOA() {
		return OA;
	}

	public void setOA(String oA) {
		OA = oA;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the cone reserve.
	 *
	 * @return the cone reserve
	 */
	public Long getConeReserve() {
		return coneReserve;
	}

	/**
	 * Sets the cone reserve.
	 *
	 * @param coneReserve the new cone reserve
	 */
	public void setConeReserve(Long coneReserve) {
		this.coneReserve = coneReserve;
	}

	/**
	 * Gets the cones actuel.
	 *
	 * @return the cones actuel
	 */
	public Long getConesActuel() {
		return conesActuel;
	}

	/**
	 * Sets the cones actuel.
	 *
	 * @param conesActuel the new cones actuel
	 */
	public void setConesActuel(Long conesActuel) {
		this.conesActuel = conesActuel;
	}

	/**
	 * Gets the date entree.
	 *
	 * @return the date entree
	 */
	public Calendar getDateEntree() {
		return dateEntree;
	}

	/**
	 * Sets the date entree.
	 *
	 * @param dateEntree the new date entree
	 */
	public void setDateEntree(Calendar dateEntree) {
		this.dateEntree = dateEntree;
	}

	/**
	 * Gets the date lot.
	 *
	 * @return the date lot
	 */
	public Calendar getDateLot() {
		return dateLot;
	}

	/**
	 * Sets the date lot.
	 *
	 * @param dateLot the new date lot
	 */
	public void setDateLot(Calendar dateLot) {
		this.dateLot = dateLot;
	}

	/**
	 * Gets the emplacement.
	 *
	 * @return the emplacement
	 */
	public String getEmplacement() {
		return emplacement;
	}

	/**
	 * Sets the emplacement.
	 *
	 * @param emplacement the new emplacement
	 */
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	
	public Long getFinconeActuel() {
		return finconeActuel;
	}

	public void setFinconeActuel(Long finconeActuel) {
		this.finconeActuel = finconeActuel;
	}

	public Long getFinconeReserve() {
		return finconeReserve;
	}

	public void setFinconeReserve(Long finconeReserve) {
		this.finconeReserve = finconeReserve;
	}



	/**
	 * Gets the libelle article.
	 *
	 * @return the libelle article
	 */
	public String getLibelleArticle() {
		return libelleArticle;
	}

	/**
	 * Sets the libelle article.
	 *
	 * @param libelleArticle the new libelle article
	 */
	public void setLibelleArticle(String libelleArticle) {
		this.libelleArticle = libelleArticle;
	}

	/**
	 * Gets the poids actuel.
	 *
	 * @return the poids actuel
	 */
	public Double getPoidsActuel() {
		return poidsActuel;
	}

	/**
	 * Sets the poids actuel.
	 *
	 * @param poidsActuel the new poids actuel
	 */
	public void setPoidsActuel(Double poidsActuel) {
		this.poidsActuel = poidsActuel;
	}

	/**
	 * Gets the poids reserve.
	 *
	 * @return the poids reserve
	 */
	public Double getPoidsReserve() {
		return poidsReserve;
	}

	/**
	 * Sets the poids reserve.
	 *
	 * @param poidsReserve the new poids reserve
	 */
	public void setPoidsReserve(Double poidsReserve) {
		this.poidsReserve = poidsReserve;
	}

	/**
	 * Gets the qte actuelle.
	 *
	 * @return the qte actuelle
	 */
	public Double getQteActuelle() {
		return qteActuelle;
	}

	/**
	 * Sets the qte actuelle.
	 *
	 * @param qteActuelle the new qte actuelle
	 */
	public void setQteActuelle(Double qteActuelle) {
		this.qteActuelle = qteActuelle;
	}

	/**
	 * Gets the qte reservee.
	 *
	 * @return the qte reservee
	 */
	public Double getQteReservee() {
		return qteReservee;
	}

	/**
	 * Sets the qte reservee.
	 *
	 * @param qteReservee the new qte reservee
	 */
	public void setQteReservee(Double qteReservee) {
		this.qteReservee = qteReservee;
	}

	/**
	 * Gets the reference article.
	 *
	 * @return the reference article
	 */
	public String getReferenceArticle() {
		return referenceArticle;
	}

	/**
	 * Sets the reference article.
	 *
	 * @param referenceArticle the new reference article
	 */
	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}

	/**
	 * Gets the reference contenaire.
	 *
	 * @return the reference contenaire
	 */
	public String getReferenceContenaire() {
		return referenceContenaire;
	}

	/**
	 * Sets the reference contenaire.
	 *
	 * @param referenceContenaire the new reference contenaire
	 */
	public void setReferenceContenaire(String referenceContenaire) {
		this.referenceContenaire = referenceContenaire;
	}

	/**
	 * Gets the reference lot.
	 *
	 * @return the reference lot
	 */
	public String getReferenceLot() {
		return referenceLot;
	}

	/**
	 * Sets the reference lot.
	 *
	 * @param referenceLot the new reference lot
	 */
	public void setReferenceLot(String referenceLot) {
		this.referenceLot = referenceLot;
	}

	/**
	 * Gets the rouleaux actuel.
	 *
	 * @return the rouleaux actuel
	 */
	public Long getRouleauxActuel() {
		return rouleauxActuel;
	}

	/**
	 * Sets the rouleaux actuel.
	 *
	 * @param rouleauxActuel the new rouleaux actuel
	 */
	public void setRouleauxActuel(Long rouleauxActuel) {
		this.rouleauxActuel = rouleauxActuel;
	}

	/**
	 * @return the designationMagasin
	 */
	public String getDesignationMagasin() {
		return designationMagasin;
	}

	/**
	 * @param designationMagasin the designationMagasin to set
	 */
	public void setDesignationMagasin(String designationMagasin) {
		this.designationMagasin = designationMagasin;
	}

	/**
	 * @return the prixTotal
	 */
	public Double getPrixTotal() {
		return prixTotal;
	}

	/**
	 * @param prixTotal the prixTotal to set
	 */
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	/**
	 * Gets the rouleaux reserve.
	 *
	 * @return the rouleaux reserve
	 */
	public Long getRouleauxReserve() {
		return rouleauxReserve;
	}

	/**
	 * Sets the rouleaux reserve.
	 *
	 * @param rouleauxReserve the new rouleaux reserve
	 */
	public void setRouleauxReserve(Long rouleauxReserve) {
		this.rouleauxReserve = rouleauxReserve;
	}

	public Long getArticle() {
		return article;
	}

	public void setArticle(Long article) {
		this.article = article;
	}

	/**
	 * @return the magasin
	 */
	public Long getMagasin() {
		return magasin;
	}

	/**
	 * @param magasin the magasin to set
	 */
	public void setMagasin(Long magasin) {
		this.magasin = magasin;
	}

	/**
	 * @return the equivalenceCone
	 */
	public Double getEquivalenceCone() {
		return equivalenceCone;
	}

	/**
	 * @param equivalenceCone the equivalenceCone to set
	 */
	public void setEquivalenceCone(Double equivalenceCone) {
		this.equivalenceCone = equivalenceCone;
	}

	/**
	 * @return the prixUnitaire
	 */
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire the prixUnitaire to set
	 */
	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
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
	 * @return the familleArticle
	 */
	public String getFamilleArticle() {
		return familleArticle;
	}

	/**
	 * @param familleArticle the familleArticle to set
	 */
	public void setFamilleArticle(String familleArticle) {
		this.familleArticle = familleArticle;
	}

	@Override
	public int compareTo(Object o) {
		EntiteStockValue element= (EntiteStockValue)o;
		return (element.getId().compareTo(id));
	}

	@Override
	public String toString() {
		return "EntiteStockValue [id=" + id + ", coneReserve=" + coneReserve + ", conesActuel=" + conesActuel
				+ ", dateEntree=" + dateEntree + ", dateLot=" + dateLot + ", emplacement=" + emplacement
				+ ", finconeActuel=" + finconeActuel + ", libelleArticle=" + libelleArticle + ", poidsActuel="
				+ poidsActuel + ", poidsReserve=" + poidsReserve + ", qteActuelle=" + qteActuelle
				+ ", referenceArticle=" + referenceArticle + ", magasin=" + magasin + "]";
	}

	public Double getStockMin() {
		return stockMin;
	}

	public void setStockMin(Double stockMin) {
		this.stockMin = stockMin;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("EntiteStockValue [");
//		if (id != null)
//			builder.append("id=").append(id).append(", ");
//		if (coneReserve != null)
//			builder.append("coneReserve=").append(coneReserve).append(", ");
//		if (conesActuel != null)
//			builder.append("conesActuel=").append(conesActuel).append(", ");
//		if (dateEntree != null)
//			builder.append("dateEntree=").append(dateEntree).append(", ");
//		if (dateLot != null)
//			builder.append("dateLot=").append(dateLot).append(", ");
//		if (emplacement != null)
//			builder.append("emplacement=").append(emplacement).append(", ");
//		if (finconeActuel != null)
//			builder.append("finconeActuel=").append(finconeActuel).append(", ");
//		if (finconeReserve != null)
//			builder.append("finconeReserve=").append(finconeReserve)
//					.append(", ");
//		if (libelleArticle != null)
//			builder.append("libelleArticle=").append(libelleArticle)
//					.append(", ");
//		if (poidsActuel != null)
//			builder.append("poidsActuel=").append(poidsActuel).append(", ");
//		if (poidsReserve != null)
//			builder.append("poidsReserve=").append(poidsReserve).append(", ");
//		if (qteActuelle != null)
//			builder.append("qteActuelle=").append(qteActuelle).append(", ");
//		if (qteReservee != null)
//			builder.append("qteReservee=").append(qteReservee).append(", ");
//		if (referenceArticle != null)
//			builder.append("referenceArticle=").append(referenceArticle)
//					.append(", ");
//		if (referenceContenaire != null)
//			builder.append("referenceContenaire=").append(referenceContenaire)
//					.append(", ");
//		if (referenceLot != null)
//			builder.append("referenceLot=").append(referenceLot).append(", ");
//		if (rouleauxActuel != null)
//			builder.append("rouleauxActuel=").append(rouleauxActuel)
//					.append(", ");
//		if (rouleauxReserve != null)
//			builder.append("rouleauxReserve=").append(rouleauxReserve)
//					.append(", ");
//		if (article != null)
//			builder.append("article=").append(article).append(", ");
//		if (magasin != null)
//			builder.append("magasin=").append(magasin).append(", ");
//		if (designationMagasin != null)
//			builder.append("designationMagasin=").append(designationMagasin)
//					.append(", ");
//		if (equivalenceCone != null)
//			builder.append("equivalenceCone=").append(equivalenceCone)
//					.append(", ");
//		if (prixUnitaire != null)
//			builder.append("prixUnitaire=").append(prixUnitaire).append(", ");
//		if (prixTotal != null)
//			builder.append("prixTotal=").append(prixTotal).append(", ");
//		if (familleArticle != null)
//			builder.append("familleArticle=").append(familleArticle)
//					.append(", ");
//		if (pmp != null)
//			builder.append("pmp=").append(pmp).append(", ");
//		if (OA != null)
//			builder.append("OA=").append(OA);
//		builder.append("]");
//		return builder.toString();
//	}

	
	

}
