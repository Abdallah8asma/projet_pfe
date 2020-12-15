package com.gpro.consulting.tiers.logistique.service.gl.machine.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineOFValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.machine.IMachineDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.machine.IMachineService;

/**
 * @author Wahid Gazzah
 * @since 30/03/2016
 */

@Service
@Transactional
public class MachineServiceImpl implements IMachineService{
	
	private static final Logger logger = LoggerFactory.getLogger(MachineServiceImpl.class);

	@Autowired
	IMachineDomaine machineDomaine;
	
	@Override
	public MachineValue getById(Long id) {
		
		//logger.info("getMAchineById: Delegating request {} to Domaine layer."+id);
		
		return machineDomaine.getById(id);
	}
	
	@Override
	public List<MachineValue> getAll(){
		
		//logger.info("getAll: Delegating request to Domaine layer.");
		
		return machineDomaine.getAll();
	}

	@Override
	public String create(MachineValue machineValue) {
		//logger.info("create: Delegating request to Domaine layer.");
		
		return machineDomaine.create(machineValue);
	}

	@Override
	public String update(MachineValue machineValue) {
		//logger.info("update: Delegating request to Domaine layer.");
		return machineDomaine.update(machineValue);
	}

	@Override
	public void delete(Long id) {
		//logger.info("delete: Delegating request to Domaine layer.");
		machineDomaine.delete(id);
	}
	
	
	@Override
	public List<MachineOFValue> getAllMachineOFvalue() {
		// TODO Auto-generated method stub
		return machineDomaine.getAllMachineOFvalue();
	}
}
