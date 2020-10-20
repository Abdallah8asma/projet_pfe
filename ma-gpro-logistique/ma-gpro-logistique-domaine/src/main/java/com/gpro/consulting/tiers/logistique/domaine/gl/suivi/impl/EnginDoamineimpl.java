package com.gpro.consulting.tiers.logistique.domaine.gl.suivi.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IEnginDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.IEnginPersistance;

@Component
public class EnginDoamineimpl implements IEnginDomaine {
	
	@Autowired
	
	IEnginPersistance EnginPersistance;

	@Override
	public List<EnginValue> getAll() {
		// TODO Auto-generated method stub
		return EnginPersistance.getAll();
	}

	@Override
	public String create(EnginValue enginValue) {
		// TODO Auto-generated method stub
		return EnginPersistance.create(enginValue);
	}

	@Override
	public String update(EnginValue enginValue) {
		// TODO Auto-generated method stub
		return EnginPersistance.update(enginValue);
	}

	@Override
	public void delete(Long id) {
		
		EnginPersistance.delete(id);
		
	}

	@Override
	public EnginValue getById(Long id) {
		// TODO Auto-generated method stub
		return EnginPersistance.getById(id);
	}

}
