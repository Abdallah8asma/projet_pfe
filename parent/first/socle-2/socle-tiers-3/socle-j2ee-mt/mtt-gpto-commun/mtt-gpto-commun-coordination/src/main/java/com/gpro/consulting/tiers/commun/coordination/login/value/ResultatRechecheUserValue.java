package com.gpro.consulting.tiers.commun.coordination.login.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 03/08/2016
 *
 */
public class ResultatRechecheUserValue {

	private Long nombreResultaRechercher;
	
	private Set<UserValue> list = new TreeSet <UserValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<UserValue> getList() {
		return list;
	}

	public void setList(Set<UserValue> list) {
		this.list = list;
	}
	
}
