package com.gpro.consulting.tiers.logistique.domaine.gl.suivi;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;

public interface IPersonnelDomaine {
	
	
    public List<PersonnelValue> getAll();
	
	public String create(PersonnelValue personnelValue);

	public String update(PersonnelValue personnelValue);

	public void delete(Long id);
	
	public PersonnelValue getById(Long id);
	

}
