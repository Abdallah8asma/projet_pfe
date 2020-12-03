package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.util.Calendar;

public class ColisValue implements Comparable<ColisValue>{
	
	private Long id;
	private Long num;
	private Long couleurId;
	private Long tailleId;
	private Long quantite;
	private Long ordre;	
	private String quantiteDesignation;
	private Long ficheColisageId;
	private String tailleDesignation;
	private String couleurDesignation;
	private Double poidsNet ;
	private Double poidsBrut ; 
	private String produitReference;
	private String produitDesignation;
	private String ordreNumero;
	private Long nombreCartons;
	private String size ;
	private String carton;
	
	private String palette ;
	
	
	private String choix;
	
	
	private Long bonSortie;
	private Calendar dateSortie;
	
	
	
	
	
	
	public int compareTo(ColisValue element) {
		return (ordre.compareTo(element.getOrdre()));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Long getCouleurId() {
		return couleurId;
	}
	public void setCouleurId(Long couleurId) {
		this.couleurId = couleurId;
	}
	public Long getTailleId() {
		return tailleId;
	}
	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Long getOrdre() {
		return ordre;
	}
	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}
	
	public Long getFicheColisageId() {
		return ficheColisageId;
	}

	public void setFicheColisageId(Long ficheColisageId) {
		this.ficheColisageId = ficheColisageId;
	}

	public String getTailleDesignation() {
		return tailleDesignation;
	}

	public void setTailleDesignation(String tailleDesignation) {
		this.tailleDesignation = tailleDesignation;
	}

	public String getCouleurDesignation() {
		return couleurDesignation;
	}

	public void setCouleurDesignation(String couleurDesignation) {
		this.couleurDesignation = couleurDesignation;
	}

	public Double getPoidsNet() {
		return poidsNet;
	}

	public void setPoidsNet(Double poidsNet) {
		this.poidsNet = poidsNet;
	}

	public Double getPoidsBrut() {
		return poidsBrut;
	}

	public void setPoidsBrut(Double poidsBrut) {
		this.poidsBrut = poidsBrut;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public String getOrdreNumero() {
		return ordreNumero;
	}

	public void setOrdreNumero(String ordreNumero) {
		this.ordreNumero = ordreNumero;
	}

	public Long getNombreCartons() {
		return nombreCartons;
	}

	public void setNombreCartons(Long nombreCartons) {
		this.nombreCartons = nombreCartons;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCarton() {
		return carton;
	}

	public void setCarton(String carton) {
		this.carton = carton;
	}

	public String getQuantiteDesignation() {
		return quantiteDesignation;
	}

	public void setQuantiteDesignation(String quantiteDesignation) {
		this.quantiteDesignation = quantiteDesignation;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public Long getBonSortie() {
		return bonSortie;
	}

	public void setBonSortie(Long bonSortie) {
		this.bonSortie = bonSortie;
	}

	public Calendar getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}

	
	
	
	
	
}
