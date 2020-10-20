package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;

public class RecherchePrixClientValue {
	
	private Long idClient;
		
	private Long idProduit;
	
	
	private Long famillePartieInteressee;
	


	public Long getFamillePartieInteressee() {
		return famillePartieInteressee;
	}

	public void setFamillePartieInteressee(Long famillePartieInteressee) {
		this.famillePartieInteressee = famillePartieInteressee;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public RecherchePrixClientValue(Long idClient, Long idProduit) {
		super();
		this.idClient = idClient;
		this.idProduit = idProduit;
	}

	public RecherchePrixClientValue() {
		super();
	}
	
	
	
	
	
}
