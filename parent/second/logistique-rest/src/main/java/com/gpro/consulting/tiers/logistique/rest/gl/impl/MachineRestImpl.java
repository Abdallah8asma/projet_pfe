package com.gpro.consulting.tiers.logistique.rest.gl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.service.gl.machine.IMachineService;

/**
 * MAchine Controller
 * 
 * @author Wahid Gazzah
 * @since 01/04/2016
 *
 */

@RestController
@RequestMapping("/machine")
public class MachineRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(MachineRestImpl.class);

	@Autowired
	private IMachineService machineService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<MachineValue> getAll() {

		//logger.info("getAllMachine: Delegating request id to service layer.");

		return machineService.getAll();
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public MachineValue getById(@PathVariable Long id) {

		//logger.info("getMachineById: Delegating request id {} to service layer.", id);

		return machineService.getById(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody MachineValue controleValue) {

		//logger.info("createControle: Delegating request to Service layer.");

		return machineService.create(controleValue);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody MachineValue controleValue) {

		//logger.info("UpdateControle: Delegating request to service layer.");

		return machineService.update(controleValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("deleteControle: Delegating request id {} to service layer.", id);

		machineService.delete(id);
	}
}
