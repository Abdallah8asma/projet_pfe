package com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 26/04/2016
 */
public class RechercheMulticritereVariationChartValue {
	
	private Long sousFamilleId;
	private Long articleId;

	/**
	 * typeArticle
	 * 
	 * USE 'FOURNITURE' or 'TISSU' or 'FILACOUDRE'
	 */
	private String typeArticle;
	
	/**
	 * typeMouvement
	 * 
	 * USE 'ENTREE' or 'SORTIE'
	 */
	private String typeMouvement;
	
	private Calendar dateFrom;
	private Calendar dateTo;
	
	
	public Long getSousFamilleId() {
		return sousFamilleId;
	}
	public void setSousFamilleId(Long sousFamilleId) {
		this.sousFamilleId = sousFamilleId;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public String getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(String typeArticle) {
		this.typeArticle = typeArticle;
	}
	public String getTypeMouvement() {
		return typeMouvement;
	}
	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
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
