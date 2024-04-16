package com.gpro.consulting.tiers.logistique.persistance.gl.suivi;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */
public interface IPersonnelPersistance {
	
	public PersonnelValue getById(Long id);

	public List<PersonnelValue> getAll();
	
	public String create(PersonnelValue personnelValue);

	public String update(PersonnelValue personnelValue);

	public void delete(Long id);
}


