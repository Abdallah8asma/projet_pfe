package com.gpro.consulting.tiers.logistique.persistance.gc.typeReglement;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ITypeReglementPersistance {

	public List<TypeReglementValue> getAll();
	
	public TypeReglementValue getById(Long id);
	
	public String create(TypeReglementValue value);

	public String update(TypeReglementValue value);

	public void delete(Long id);
	
}
