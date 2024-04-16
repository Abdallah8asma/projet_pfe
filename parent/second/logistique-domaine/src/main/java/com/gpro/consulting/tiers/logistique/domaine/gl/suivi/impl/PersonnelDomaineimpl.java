package com.gpro.consulting.tiers.logistique.domaine.gl.suivi.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IPersonnelDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.IPersonnelPersistance;

@Component
public class PersonnelDomaineimpl implements IPersonnelDomaine {
	
	
	@Autowired
	private IPersonnelPersistance PersonnelPersistance;

	@Override
	public List<PersonnelValue> getAll() {
		
		return PersonnelPersistance.getAll();
	}

	@Override
	public String create(PersonnelValue personnelValue) {
		
		return PersonnelPersistance.create(personnelValue);
	}

	@Override
	public String update(PersonnelValue personnelValue) {
		
		return PersonnelPersistance.update(personnelValue);
	}

	@Override
	public void delete(Long id) {
		
		PersonnelPersistance.delete(id);
		
	}

	@Override
	public PersonnelValue getById(Long id) {
		// TODO Auto-generated method stub
		return PersonnelPersistance.getById(id);
	}

}
