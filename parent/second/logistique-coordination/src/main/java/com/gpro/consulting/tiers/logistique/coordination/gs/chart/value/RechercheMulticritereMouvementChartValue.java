package com.gpro.consulting.tiers.logistique.coordination.gs.chart.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 14/04/2016
 */
public class RechercheMulticritereMouvementChartValue {
	
	//type "ENTREE" or "SORTIE"
	private String type;
	private Calendar dateFrom;
	private Calendar dateTo;
	
	private Calendar dateDe;

	private Calendar dateA;
	
	
	private Long typeArticle;
	
	
	private Long familleArticle;
	
	
	private Long sousFamilleArticle;
	
	
	
	
	
	public Long getSousFamilleArticle() {
		return sousFamilleArticle;
	}
	public void setSousFamilleArticle(Long sousFamilleArticle) {
		this.sousFamilleArticle = sousFamilleArticle;
	}
	public Long getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(Long typeArticle) {
		this.typeArticle = typeArticle;
	}
	public Long getFamilleArticle() {
		return familleArticle;
	}
	public void setFamilleArticle(Long familleArticle) {
		this.familleArticle = familleArticle;
	}
	public Calendar getDateDe() {
		return dateDe;
	}
	public void setDateDe(Calendar dateDe) {
		this.dateDe = dateDe;
	}
	public Calendar getDateA() {
		return dateA;
	}
	public void setDateA(Calendar dateA) {
		this.dateA = dateA;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Calendar getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Calendar dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Calendar getDateTo() {
		return dateTo;
	}
	public void setDateTo(Calendar dateTo) {
		this.dateTo = dateTo;
	}
	
	

}
