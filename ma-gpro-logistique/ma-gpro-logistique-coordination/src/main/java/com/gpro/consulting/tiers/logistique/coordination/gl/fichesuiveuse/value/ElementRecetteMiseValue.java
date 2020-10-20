package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value;

/**
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public class ElementRecetteMiseValue implements Comparable<ElementRecetteMiseValue>{

    private Long id;
	private Long articleId;
	private Double pourcentage;
	private Double poids;
	private String observations;
	private String type;//Teinture - Coton, Teinture - PES, Finissage, Preparation
	
	//added on 04/04/2016, by Wahid Gazzah
	private Double rajout;
	
	//added on 21/04/2016, by Wahid Gazzah
	private String temps;
	private Double temperature;
	private String methode;
	private Long traitementMiseId;
	
	private Long order;
	private String ph;
	
	public int compareTo(ElementRecetteMiseValue element) {
		
		return order.compareTo(element.getOrder());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
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
	public Double getRajout() {
		return rajout;
	}
	public void setRajout(Double rajout) {
		this.rajout = rajout;
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

	public Long getTraitementMiseId() {
		return traitementMiseId;
	}

	public void setTraitementMiseId(Long traitementMiseId) {
		this.traitementMiseId = traitementMiseId;
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


}
