package com.gpro.consulting.tiers.commun.coordination.login.value;

import java.util.Calendar;


public class RoleValue /*implements Comparable<RoleValue>*/ {
    private Long id ;
	
    private String designation;
    
    private String code;
    
    private String module;
	
	/*private Boolean blSuppression;
	
	private Calendar dateSuppression;
	
	private Calendar dateCreation;

	private Calendar dateModification;

	private String version;
	
	private String description;*/
	
	
  
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getModule() {
		return module;
	}


	public void setModule(String module) {
		this.module = module;
	}


/*
	public int compareTo(RoleValue element) {
		return (element.getId().compareTo(id));
	}

*/
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



	



}
