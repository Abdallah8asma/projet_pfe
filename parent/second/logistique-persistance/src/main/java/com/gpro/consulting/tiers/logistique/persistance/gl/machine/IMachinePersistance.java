package com.gpro.consulting.tiers.logistique.persistance.gl.machine;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;

/**
 * MAchine Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 31/03/2016
 */
public interface IMachinePersistance {

	public MachineValue getById(Long id);

	public List<MachineValue> getAll();
	
	public String create(MachineValue machineValue);

	public String update(MachineValue machineValue);

	public void delete(Long id);
}
