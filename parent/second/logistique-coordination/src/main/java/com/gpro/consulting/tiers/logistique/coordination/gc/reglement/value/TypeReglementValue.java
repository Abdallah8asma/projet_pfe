package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class TypeReglementValue {
    
	private Long id;
	private String designation;
	private Boolean aTerme;
	
	private String prefixe;
	

	private Boolean regle;
	
	
	
	
	public Boolean getRegle() {
		return regle;
	}
	public void setRegle(Boolean regle) {
		this.regle = regle;
	}
	public String getPrefixe() {
		return prefixe;
	}
	public void setPrefixe(String prefixe) {
		this.prefixe = prefixe;
	}
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
