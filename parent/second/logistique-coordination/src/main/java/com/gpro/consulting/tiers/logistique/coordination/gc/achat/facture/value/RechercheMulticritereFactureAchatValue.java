package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.Calendar;

/**
 * The Class RechercheMulticritereFactureAchatValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class RechercheMulticritereFactureAchatValue {

	private boolean isOptimized;

	/** The reference facture. */
	private String referenceFacture;

	/** The reference bl. */
	private String referenceBl;

	/** The partie int id. */
	private Long partieIntId;

	/** The date facture min. */
	private Calendar dateFactureMin;

	/** The date facture max. */
	private Calendar dateFactureMax;

	/** The metrage min. */
	private Double metrageMin;

	/** The metrage max. */
	private Double metrageMax;

	/** The prix min. */
	private Double prixMin;

	/** The prix max. */
	private Double prixMax;

	/** The type. */
	private String type;

	/** The reference bl externe. */
	private String referenceBlExterne;

	/** The marche id. */
	private Long marcheId;

	/** The nature livraison. */
	// added on 12/10/2016, by Zeineb Medimagh
	private String natureLivraison;

	private Long GroupeClientId;

	private String raison;

	private String observations;

	private String nature;

	private Long boutiqueId;

	private Calendar dateIntroductionDe;
	private Calendar dateIntroductionA;

	private Long idDepot;
	
	private String declarerecherche;
	
	private String forcerCalculMontantRech;
	
	
	
	private String infoLivraison;
	
	
	
	
	

	public String getInfoLivraison() {
		return infoLivraison;
	}

	public void setInfoLivraison(String infoLivraison) {
		this.infoLivraison = infoLivraison;
	}

	public String getForcerCalculMontantRech() {
		return forcerCalculMontantRech;
	}

	public void setForcerCalculMontantRech(String forcerCalculMontantRech) {
		this.forcerCalculMontantRech = forcerCalculMontantRech;
	}

	public String getDeclarerecherche() {
		return declarerecherche;
	}

	public void setDeclarerecherche(String declarerecherche) {
		this.declarerecherche = declarerecherche;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public Calendar getDateIntroductionDe() {
		return dateIntroductionDe;
	}

	public void setDateIntroductionDe(Calendar dateIntroductionDe) {
		this.dateIntroductionDe = dateIntroductionDe;
	}

	public Calendar getDateIntroductionA() {
		return dateIntroductionA;
	}

	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Long getGroupeClientId() {
		return GroupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		GroupeClientId = groupeClientId;
	}

	/**
	 * Gets the reference bl.
	 *
	 * @return the reference bl
	 */
	public String getReferenceBl() {
		return referenceBl;
	}

	/**
	 * Sets the reference bl.
	 *
	 * @param referenceBl the new reference bl
	 */
	public void setReferenceBl(String referenceBl) {
		this.referenceBl = referenceBl;
	}

	/**
	 * Gets the partie int id.
	 *
	 * @return the partie int id
	 */
	public Long getPartieIntId() {
		return partieIntId;
	}

	/**
	 * Sets the partie int id.
	 *
	 * @param partieIntId the new partie int id
	 */
	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	/**
	 * Gets the date facture min.
	 *
	 * @return the date facture min
	 */
	public Calendar getDateFactureMin() {
		return dateFactureMin;
	}

	/**
	 * Sets the date facture min.
	 *
	 * @param dateFactureMin the new date facture min
	 */
	public void setDateFactureMin(Calendar dateFactureMin) {
		this.dateFactureMin = dateFactureMin;
	}

	/**Tf
	 * Gets the date facture max.
	 *
	 * @return the date facture max
	 */
	public Calendar getDateFactureMax() {
		return dateFactureMax;
	}

	/**
	 * Sets the date facture max.
	 *
	 * @param dateFactureMax the new date facture max
	 */
	public void setDateFactureMax(Calendar dateFactureMax) {
		this.dateFactureMax = dateFactureMax;
	}

	/**
	 * Gets the metrage min.
	 *
	 * @return the metrage min
	 */
	public Double getMetrageMin() {
		return metrageMin;
	}

	/**
	 * Sets the metrage min.
	 *
	 * @param metrageMin the new metrage min
	 */
	public void setMetrageMin(Double metrageMin) {
		this.metrageMin = metrageMin;
	}

	/**
	 * Gets the metrage max.
	 *
	 * @return the metrage max
	 */
	public Double getMetrageMax() {
		return metrageMax;
	}

	/**
	 * Sets the metrage max.
	 *
	 * @param metrageMax the new metrage max
	 */
	public void setMetrageMax(Double metrageMax) {
		this.metrageMax = metrageMax;
	}

	/**
	 * Gets the prix min.
	 *
	 * @return the prix min
	 */
	public Double getPrixMin() {
		return prixMin;
	}

	/**
	 * Sets the prix min.
	 *
	 * @param prixMin the new prix min
	 */
	public void setPrixMin(Double prixMin) {
		this.prixMin = prixMin;
	}

	/**
	 * Gets the prix max.
	 *
	 * @return the prix max
	 */
	public Double getPrixMax() {
		return prixMax;
	}

	/**
	 * Sets the prix max.
	 *
	 * @param prixMax the new prix max
	 */
	public void setPrixMax(Double prixMax) {
		this.prixMax = prixMax;
	}

	/**
	 * Gets the reference facture.
	 *
	 * @return the reference facture
	 */
	public String getReferenceFacture() {
		return referenceFacture;
	}

	/**
	 * Sets the reference facture.
	 *
	 * @param referenceFacture the new reference facture
	 */
	public void setReferenceFacture(String referenceFacture) {
		this.referenceFacture = referenceFacture;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the nature livraison.
	 *
	 * @return the nature livraison
	 */
	public String getNatureLivraison() {
		return natureLivraison;
	}

	/**
	 * Sets the nature livraison.
	 *
	 * @param natureLivraison the new nature livraison
	 */
	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}

	/**
	 * Gets the reference bl externe.
	 *
	 * @return the reference bl externe
	 */
	public String getReferenceBlExterne() {
		return referenceBlExterne;
	}

	/**
	 * Sets the reference bl externe.
	 *
	 * @param referenceBlExterne the new reference bl externe
	 */
	public void setReferenceBlExterne(String referenceBlExterne) {
		this.referenceBlExterne = referenceBlExterne;
	}

	/**
	 * Gets the marche id.
	 *
	 * @return the marche id
	 */
	public Long getMarcheId() {
		return marcheId;
	}

	/**
	 * Sets the marche id.
	 *
	 * @param marcheId the new marche id
	 */
	public void setMarcheId(Long marcheId) {
		this.marcheId = marcheId;
	}

	public boolean isOptimized() {
		return isOptimized;
	}

	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}

}
