package com.gpro.consulting.tiers.logistique.domaine.gl.machine;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineOFValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;

/**
 * MachineDomaine Interface
 *  
 * @author Wahid Gazzah
 * @since 30 mars 2016
 */
public interface IMachineDomaine {
	
	public MachineValue getById(Long id);
	
	public List<MachineValue> getAll();
	
	public String create(MachineValue machineValue);

	public String update(MachineValue machineValue);

	public void delete(Long id);
	
	public List<MachineOFValue> getAllMachineOFvalue();
}
