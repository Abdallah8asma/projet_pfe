package com.gpro.consulting.tiers.logistique.domaine.gl.suivi.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IRemorqueDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.IRemorquePersistance;

@Component
public class RemorqueDomaineimpl implements IRemorqueDomaine {
	
	@Autowired
	
	IRemorquePersistance RemorquePersistance;

	@Override
	public RemorqueValue getById(Long id) {
		// TODO Auto-generated method stub
		return RemorquePersistance.getById(id) ;
	}

	@Override
	public List<RemorqueValue> getAll() {
		// TODO Auto-generated method stub
		return RemorquePersistance.getAll();
	}

	@Override
	public String create(RemorqueValue remorqueValue) {
		// TODO Auto-generated method stub
		return RemorquePersistance.create(remorqueValue);
	}

	@Override
	public String update(RemorqueValue remorqueValue) {
		// TODO Auto-generated method stub
		return RemorquePersistance.update(remorqueValue);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		RemorquePersistance.delete(id);
	}

}
