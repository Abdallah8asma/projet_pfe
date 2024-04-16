package com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value;

import java.util.List;

public class RouleauFiniReportGroupedByProduitValue {

	//for the groupBy
	private String refTissu;
	private String tissu;
	private String type;
	private String composition;
	
	private Double totalMetrage;
	private Double totalPoids;
	private Long produitId; 
	
	private String avecMise;
	
	private List<RouleauFiniReportValue> list;

	public List<RouleauFiniReportValue> getList() {
		return list;
	}
	public void setList(List<RouleauFiniReportValue> list) {
		this.list = list;
	}
	public Double getTotalMetrage() {
		return totalMetrage;
	}
	public void setTotalMetrage(Double totalMetrage) {
		this.totalMetrage = totalMetrage;
	}
	public Double getTotalPoids() {
		return totalPoids;
	}
	public void setTotalPoids(Double totalPoids) {
		this.totalPoids = totalPoids;
	}

	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
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
	public String getAvecMise() {
		return avecMise;
	}
	public void setAvecMise(String avecMise) {
		this.avecMise = avecMise;
	}

}
