package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class TypeReglementAchatValue {
    
	private Long id;
	private String designation;
	private Boolean aTerme;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Boolean getaTerme() {
		return aTerme;
	}
	public void setaTerme(Boolean aTerme) {
		this.aTerme = aTerme;
	}
	
}
