package com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value;

/**
 * 
 * @author Wahid Gazzah
 * @since 12/01/2016
 *
 */
public class RouleauFiniReportValue implements Comparable<RouleauFiniReportValue>{
	
	private String refRouleau;
	/////
	private String refTissu;
	private String tissu;
	private String type;
	private String composition;
	/////
	private Double laize;
	private Double metrage;
	private Double poids;
	private String choix;
	private String mise;
	
	private String avecMise;
	
	@Override
	 public int compareTo(RouleauFiniReportValue o) {
		RouleauFiniReportValue element= (RouleauFiniReportValue)o;
	   return (element.getRefTissu().compareTo(refTissu));
	 }
	
	public String getRefRouleau() {
		return refRouleau;
	}
	public void setRefRouleau(String refRouleau) {
		this.refRouleau = refRouleau;
	}
	public String getRefTissu() {
		return refTissu;
	}
	public void setRefTissu(String refTissu) {
		this.refTissu = refTissu;
	}
	public String getTissu() {
		return tissu;
	}
	public void setTissu(String tissu) {
		this.tissu = tissu;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public Double getLaize() {
		return laize;
	}
	public void setLaize(Double laize) {
		this.laize = laize;
	}
	public Double getMetrage() {
		return metrage;
	}
	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	public String getMise() {
		return mise;
	}
	public void setMise(String mise) {
		this.mise = mise;
	}
	
	public String getAvecMise() {
		return avecMise;
	}
	public void setAvecMise(String avecMise) {
		this.avecMise = avecMise;
	}
	

}
