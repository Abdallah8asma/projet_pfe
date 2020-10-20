package com.gpro.consulting.tiers.logistique.service.gc.typeReglement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.typeReglement.ITypeReglementDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.typeReglement.ITypeReglementService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class TypeReglementServiceImpl implements ITypeReglementService{
	
	@Autowired
	ITypeReglementDomaine typeReglementDomaine;
	
	@Override
	public List<TypeReglementValue> getAll() {
		
		return typeReglementDomaine.getAll();
	}

	@Override
	public TypeReglementValue getById(Long id) {
		return typeReglementDomaine.getById(id);
	}

	@Override
	public String create(TypeReglementValue value) {
		return typeReglementDomaine.create(value);
	}

	@Override
	public String update(TypeReglementValue value) {
		return typeReglementDomaine.update(value);
	}

	@Override
	public void delete(Long id) {
		typeReglementDomaine.delete(id);
	}
	
}
