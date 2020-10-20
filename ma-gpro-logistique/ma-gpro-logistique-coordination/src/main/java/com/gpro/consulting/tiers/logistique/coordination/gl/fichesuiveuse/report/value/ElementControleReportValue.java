package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value;

/**
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */
public class ElementControleReportValue implements Comparable<ElementControleReportValue>{

	private Long id;
	private Double valeur;
	private Double valeurCorrige;
	private String methode;
	private String observations;
	private String valeurTheorique;
	private String controleDesignation;
	
	//added on 06/04/2016, by Wahid Gazzah
	private String temps;
	private String type;//Teinture - Coton, Teinture - PES, Finissage, Preparation
	
	public int compareTo(ElementControleReportValue o) {
		ElementControleReportValue element = (ElementControleReportValue)o;
		return (id.compareTo(element.getId()));
	}
	
	public Double getValeur() {
		return valeur;
	}
	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}
	public Double getValeurCorrige() {
		return valeurCorrige;
	}
	public void setValeurCorrige(Double valeurCorrige) {
		this.valeurCorrige = valeurCorrige;
	}
	public String getMethode() {
		return methode;
	}
	public void setMethode(String methode) {
		this.methode = methode;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getValeurTheorique() {
		return valeurTheorique;
	}
	public void setValeurTheorique(String valeurTheorique) {
		this.valeurTheorique = valeurTheorique;
	}
	public String getControleDesignation() {
		return controleDesignation;
	}
	public void setControleDesignation(String controleDesignation) {
		this.controleDesignation = controleDesignation;
	}
	public String getTemps() {
		return temps;
	}
	public void setTemps(String temps) {
		this.temps = temps;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
