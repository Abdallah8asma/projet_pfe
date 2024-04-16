package com.gpro.consulting.tiers.logistique.rest.gc.typeReglement.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.service.gc.typeReglement.ITypeReglementService;

/**
 * @author Ameni Berrich
 * 
 */
@RestController
@RequestMapping("/typeReglement")
public class TypeReglementRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(TypeReglementRestImpl.class);
	@Autowired
	private ITypeReglementService typeReglementService;

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public TypeReglementValue getById(@PathVariable Long id) {

		//logger.info("getById: Delegating request id {} to service layer.", id);

		return typeReglementService.getById(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<TypeReglementValue> getAll() {

		//logger.info("Delegating request to service layer.");

		return typeReglementService.getAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody TypeReglementValue value) {

		//logger.info("create: Delegating request to Service layer.");

		return typeReglementService.create(value);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody TypeReglementValue value) {

		//logger.info("Update: Delegating request to service layer.");

		return typeReglementService.update(value);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		//logger.info("delete: Delegating request id {} to service layer.", id);

		typeReglementService.delete(id);
	}
}
