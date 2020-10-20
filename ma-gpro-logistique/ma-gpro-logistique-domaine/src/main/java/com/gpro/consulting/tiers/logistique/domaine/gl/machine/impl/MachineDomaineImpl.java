package com.gpro.consulting.tiers.logistique.domaine.gl.machine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.machine.IMachineDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.machine.IMachinePersistance;

/**
 * Implementation of {@link IMachineDomaine}
 *  
 * @author Wahid Gazzah
 * @since 30/03/2016
 * 
 */

@Component
public class MachineDomaineImpl implements IMachineDomaine{

	@Autowired
	private IMachinePersistance machinePersistance;

	@Override
	public MachineValue getById(Long id) {
		
		return machinePersistance.getById(id);
	}
	
	@Override
	public List<MachineValue> getAll() {
		
		return machinePersistance.getAll();
	}

	@Override
	public String create(MachineValue machineValue) {
		return machinePersistance.create(machineValue);
	}

	@Override
	public String update(MachineValue machineValue) {
		return machinePersistance.update(machineValue);
	}

	@Override
	public void delete(Long id) {
		machinePersistance.delete(id);		
	}

}
