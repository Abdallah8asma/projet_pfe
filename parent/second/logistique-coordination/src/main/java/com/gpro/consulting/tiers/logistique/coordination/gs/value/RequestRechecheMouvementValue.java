package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;

/** 
 * @author Wahid Gazzah
 * @since 25/02/2016
 */

public class RequestRechecheMouvementValue {
	
	private Long articleId;
	private Calendar dateMin;
	private Calendar dateMax;
	
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public Calendar getDateMin() {
		return dateMin;
	}
	public void setDateMin(Calendar dateMin) {
		this.dateMin = dateMin;
	}
	public Calendar getDateMax() {
		return dateMax;
	}
	public void setDateMax(Calendar dateMax) {
		this.dateMax = dateMax;
	}
	
	

}
