package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value;

/**
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public class ElementControleValue implements Comparable<ElementControleValue>{

    private Long id;
	private Double valeur;
	private Double valeurCorrige;
	private String methode;
	private String observations;
	private String valeurTheorique;
	private Long controleId;
	private Long ficheSuiveuseId;
	
	//added on 06/04/2016, by Wahid Gazzah
	private String temps;
	private String type;//Teinture - Coton, Teinture - PES, Finissage, Preparation
	
	public int compareTo(ElementControleValue o) {
		ElementControleValue element= (ElementControleValue)o;
		return (id.compareTo(element.getId()));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getControleId() {
		return controleId;
	}
	public void setControleId(Long controleId) {
		this.controleId = controleId;
	}
	public Long getFicheSuiveuseId() {
		return ficheSuiveuseId;
	}
	public void setFicheSuiveuseId(Long ficheSuiveuseId) {
		this.ficheSuiveuseId = ficheSuiveuseId;
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
	
	
}
