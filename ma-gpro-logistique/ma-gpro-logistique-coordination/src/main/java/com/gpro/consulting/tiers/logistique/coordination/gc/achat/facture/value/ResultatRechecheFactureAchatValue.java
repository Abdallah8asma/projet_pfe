package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * The Class ResultatRechecheFactureAchatValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class ResultatRechecheFactureAchatValue {

	/** The nombre resulta rechercher. */
	private Long nombreResultaRechercher;

	/** The list. */
	private Set<FactureAchatValue> list = new TreeSet<FactureAchatValue>();

	/**
	 * Gets the nombre resulta rechercher.
	 *
	 * @return the nombre resulta rechercher
	 */
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	/**
	 * Sets the nombre resulta rechercher.
	 *
	 * @param nombreResultaRechercher
	 *            the new nombre resulta rechercher
	 */
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public Set<FactureAchatValue> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list
	 *            the new list
	 */
	public void setList(Set<FactureAchatValue> list) {
		this.list = list;
	}

}
