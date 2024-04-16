/**
 * 
 */
package com.gpro.consulting.tiers.logistique.domaine.gc.typeReglement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.typeReglement.ITypeReglementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.typeReglement.ITypeReglementPersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class TypeReglementDomaineImpl implements ITypeReglementDomaine{

	@Autowired
	ITypeReglementPersistance typeReglementPersistance;
	
	@Override
	public List<TypeReglementValue> getAll() {
		return typeReglementPersistance.getAll();
	}

	@Override
	public TypeReglementValue getById(Long id) {
		return typeReglementPersistance.getById(id);
	}

	@Override
	public String create(TypeReglementValue value) {
		return typeReglementPersistance.create(value);
	}

	@Override
	public String update(TypeReglementValue value) {
		return typeReglementPersistance.update(value);
	}

	@Override
	public void delete(Long id) {
		typeReglementPersistance.delete(id);
	}

}
