package com.gpro.consulting.tiers.commun.coordination.login.value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wahid Gazzah
 * @since 03/08/2016
 *
 */
public class UserValue implements Comparable<UserValue>{
	
	private Long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Boolean enabled;
	
	private String roleNames;
	private Set<String> listRoles= new HashSet<String>();
	
	private Long boutiqueId;
	private String boutiqueAbreviation;
	private String newPassword;
	
	
	private String job;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	private Collection<RoleValue> roles= new ArrayList<RoleValue>();
	
	
	

	public String getBoutiqueAbreviation() {
		return boutiqueAbreviation;
	}

	public void setBoutiqueAbreviation(String boutiqueAbreviation) {
		this.boutiqueAbreviation = boutiqueAbreviation;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public Set<String> getListRoles() {
		return listRoles;
	}

	public void setListRoles(Set<String> listRoles) {
		this.listRoles = listRoles;
	}

	public Collection<RoleValue> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleValue> roles) {
		this.roles = roles;
	}

	public int compareTo(UserValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	
}

