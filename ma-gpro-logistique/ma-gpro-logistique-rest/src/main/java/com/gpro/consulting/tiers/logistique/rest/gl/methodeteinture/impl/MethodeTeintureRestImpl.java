package com.gpro.consulting.tiers.logistique.rest.gl.methodeteinture.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gl.methodeteinture.value.MethodeTeintureValue;
import com.gpro.consulting.tiers.logistique.service.gl.methodeteinture.IMethodeTeintureService;

/**
 * Methode Teinture Controller
 * 
 * @author Zeineb Medimagh
 * @since 03/10/2016
 *
 */

@RestController
@RequestMapping("/methodeTeinture")
public class MethodeTeintureRestImpl {

	@Autowired
	private IMethodeTeintureService methodeTeintureService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody MethodeTeintureValue MethodeTeintureValue) {

		return methodeTeintureService.create(MethodeTeintureValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public MethodeTeintureValue getById(@PathVariable Long id) {

		return methodeTeintureService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody MethodeTeintureValue MethodeTeintureValue) {

		return methodeTeintureService.update(MethodeTeintureValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		methodeTeintureService.delete(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<MethodeTeintureValue> getAll() {

		return methodeTeintureService.getAll();
	}
}
