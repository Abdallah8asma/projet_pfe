package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value;

/**
 * @author Wahid Gazzah
 * @since 29/03/2016
 */
public class ElementRecetteMiseReportValue implements Comparable<ElementRecetteMiseReportValue>{
	
	private Long id;
	private String articleReference;
	private String articleDesignation;
	private Double pourcentage;
	private Double poids;
	private String observations;
	private String type;
	private String famille;
	private Double rajout;
	
	private String temps;
	private Double temperature;
	private String methode;
	private Long order;
	private String ph;
	
	private Double prixUnitaireRecette;
	private Double prixTotalRecette;
	
	public int compareTo(ElementRecetteMiseReportValue element) {
		
		return order.compareTo(element.getOrder());
	}
	
	public String getArticleReference() {
		return articleReference;
	}
	public void setArticleReference(String articleReference) {
		this.articleReference = articleReference;
	}
	public String getArticleDesignation() {
		return articleDesignation;
	}
	public void setArticleDesignation(String articleDesignation) {
		this.articleDesignation = articleDesignation;
	}
	public Double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
	public Double getRajout() {
		return rajout;
	}
	public void setRajout(Double rajout) {
		this.rajout = rajout;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTemps() {
		return temps;
	}
	public void setTemps(String temps) {
		this.temps = temps;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public String getMethode() {
		return methode;
	}
	public void setMethode(String methode) {
		this.methode = methode;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}

	public Double getPrixUnitaireRecette() {
		return prixUnitaireRecette;
	}

	public void setPrixUnitaireRecette(Double prixUnitaireRecette) {
		this.prixUnitaireRecette = prixUnitaireRecette;
	}

	public Double getPrixTotalRecette() {
		return prixTotalRecette;
	}

	public void setPrixTotalRecette(Double prixTotalRecette) {
		this.prixTotalRecette = prixTotalRecette;
	}
}
