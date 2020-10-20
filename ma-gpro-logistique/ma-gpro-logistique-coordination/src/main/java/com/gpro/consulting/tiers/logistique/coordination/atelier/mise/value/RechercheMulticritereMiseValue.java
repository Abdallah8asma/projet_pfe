package com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value;

import java.util.Calendar;

public class RechercheMulticritereMiseValue {
	 /** Client */
	  private Long client;

	  /** Date Introduction */
	  private Calendar dateIntroduction;
	  
	  private String referenceBonReception ;

	/**
	 * @return the client
	 */
	public Long getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Long client) {
		this.client = client;
	}

	/**
	 * @return the dateIntroduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * @param dateIntroduction the dateIntroduction to set
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	/**
	 * @return the referenceBonReception
	 */
	public String getReferenceBonReception() {
		return referenceBonReception;
	}

	/**
	 * @param referenceBonReception the referenceBonReception to set
	 */
	public void setReferenceBonReception(String referenceBonReception) {
		this.referenceBonReception = referenceBonReception;
	}
	  
	  

}
