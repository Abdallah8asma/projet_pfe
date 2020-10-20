package com.gpro.consulting.tiers.logistique.coordination.gs.value;


public class ArticleEntreeValue {

	/** The Id **/
	private Long id;
	
	/** The ref. */

	private String ref;

	/** The dedignation. */
	private String designation;

	/** The prix unitaire. */

	private Double pu;

	/** The famille. */
	private String familleArticleDesignation;

	/** The sousFamille. */
	private String sousFamille;

	/** The prix moyen pondere. */

	private Double pmp;

	/** The finCone. */

	private Boolean finCone;

	/** The nombreConeAct. */
	private Long nombreConeAct;

	/** The nombreFinConeAct. */
	private Long nombreFinConeAct;

	/** The nombreFinConeAct. */
	private Long nombreCone;

	/** The nombreFinConeAct. */
	private Long nombreFinCone;

	/** The poids. */
	private Double poids;
	/** The prixTotal. */
	private Double prixTotal;
    
	/** The quantite. */
	private Double quantite;

	/** The nombreRouleaux. */
	private Long nombreRouleaux;
    
	/** The nombreRouleaux. */
	private String emplacement;

	/** The etat. */
	private String etat;

	
	/** The nombreRouleaux. */
	private Long nombreRouleauxAct;
    
	/** The quantite Actuelle. */
	private Double quantiteActuelle;

	/** The poidsActuel. */
	private Double poidsActuel;
	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getPu() {
		return pu;
	}

	public void setPu(Double pu) {
		this.pu = pu;
	}

	public String getFamilleArticleDesignation() {
		return familleArticleDesignation;
	}

	public void setFamilleArticleDesignation(String familleArticleDesignation) {
		this.familleArticleDesignation = familleArticleDesignation;
	}

	public String getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(String sousFamille) {
		this.sousFamille = sousFamille;
	}

	public Double getPmp() {
		return pmp;
	}

	public void setPmp(Double pmp) {
		this.pmp = pmp;
	}

	public Boolean getFinCone() {
		return finCone;
	}

	public void setFinCone(Boolean finCone) {
		this.finCone = finCone;
	}

	
	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}



	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombreConeAct
	 */
	public Long getNombreConeAct() {
		return nombreConeAct;
	}

	/**
	 * @param nombreConeAct the nombreConeAct to set
	 */
	public void setNombreConeAct(Long nombreConeAct) {
		this.nombreConeAct = nombreConeAct;
	}

	/**
	 * @return the nombreFinConeAct
	 */
	public Long getNombreFinConeAct() {
		return nombreFinConeAct;
	}

	/**
	 * @param nombreFinConeAct the nombreFinConeAct to set
	 */
	public void setNombreFinConeAct(Long nombreFinConeAct) {
		this.nombreFinConeAct = nombreFinConeAct;
	}

	/**
	 * @return the nombreCone
	 */
	public Long getNombreCone() {
		return nombreCone;
	}

	/**
	 * @param nombreCone the nombreCone to set
	 */
	public void setNombreCone(Long nombreCone) {
		this.nombreCone = nombreCone;
	}

	/**
	 * @return the nombreFinCone
	 */
	public Long getNombreFinCone() {
		return nombreFinCone;
	}

	/**
	 * @param nombreFinCone the nombreFinCone to set
	 */
	public void setNombreFinCone(Long nombreFinCone) {
		this.nombreFinCone = nombreFinCone;
	}

	/**
	 * @return the nombreRouleaux
	 */
	public Long getNombreRouleaux() {
		return nombreRouleaux;
	}

	/**
	 * @param nombreRouleaux the nombreRouleaux to set
	 */
	public void setNombreRouleaux(Long nombreRouleaux) {
		this.nombreRouleaux = nombreRouleaux;
	}

	/**
	 * @return the nombreRouleauxAct
	 */
	public Long getNombreRouleauxAct() {
		return nombreRouleauxAct;
	}

	/**
	 * @param nombreRouleauxAct the nombreRouleauxAct to set
	 */
	public void setNombreRouleauxAct(Long nombreRouleauxAct) {
		this.nombreRouleauxAct = nombreRouleauxAct;
	}

	/**
	 * @return the quantiteActuelle
	 */
	public Double getQuantiteActuelle() {
		return quantiteActuelle;
	}

	/**
	 * @param quantiteActuelle the quantiteActuelle to set
	 */
	public void setQuantiteActuelle(Double quantiteActuelle) {
		this.quantiteActuelle = quantiteActuelle;
	}

	/**
	 * @return the poidsActuel
	 */
	public Double getPoidsActuel() {
		return poidsActuel;
	}

	/**
	 * @param poidsActuel the poidsActuel to set
	 */
	public void setPoidsActuel(Double poidsActuel) {
		this.poidsActuel = poidsActuel;
	}

}
