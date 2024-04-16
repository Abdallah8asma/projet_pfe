package com.gpro.consulting.tiers.logistique.service.gl.machine;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineOFValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;

/**
 * @author Wahid Gazzah
 * @since 30 mars 2016
 */
public interface IMachineService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public MachineValue getById(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MachineValue> getAll();
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(MachineValue machineValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(MachineValue machineValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MachineOFValue> getAllMachineOFvalue();
	
}
