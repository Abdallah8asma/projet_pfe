package com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value;

import java.util.Calendar;

/**
 * Classe valeur destinée pour l'IHM en cas de recheche d'un Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
/**
 * @author Ameni
 *
 */
public class ElementRechechePrepMouleValue implements Comparable {

	/** idPrepMoule */
	private Long idPrepMoule;

	private Long idMoule;

	/** The reference. */
	private String reference;

	private String designation;
	
	private String machine;
	
	private String emplacement;
	 
	private Calendar datePrep;

	@Override
	public int compareTo(Object o) {
		ElementRechechePrepMouleValue element = (ElementRechechePrepMouleValue) o;
		return (element.getIdPrepMoule().compareTo(idPrepMoule));
	}

	public Long getIdPrepMoule() {
		return idPrepMoule;
	}

	public void setIdPrepMoule(Long idPrepMoule) {
		this.idPrepMoule = idPrepMoule;
	}

	public Long getIdMoule() {
		return idMoule;
	}

	public void setIdMoule(Long idMoule) {
		this.idMoule = idMoule;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}



	public Calendar getDatePrep() {
		return datePrep;
	}

	public void setDatePrep(Calendar datePrep) {
		this.datePrep = datePrep;
	}
	
	

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	@Override
	public String toString() {
		return "ElementRechechePrepMouleValue [idPrepMoule=" + idPrepMoule + ", idMoule=" + idMoule + ", reference="
				+ reference + ", designation=" + designation + ", machine=" + machine + ", emplacement=" + emplacement
				+ ", datePrep=" + datePrep + "]";
	}

	

}
