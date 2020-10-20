package com.gpro.consulting.tiers.logistique.domaine.gc.typeReglement;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ITypeReglementDomaine {
	
	public List<TypeReglementValue> getAll();
	
	public TypeReglementValue getById(Long id);
	
	public String create(TypeReglementValue value);

	public String update(TypeReglementValue value);

	public void delete(Long id);
}
