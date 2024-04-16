/**
 * 
 */
package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.ITypeReglementAchatDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.ITypeReglementAchatPersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class TypeReglementAchatDomaineImpl implements ITypeReglementAchatDomaine{

	@Autowired
	ITypeReglementAchatPersistance typeReglementPersistance;
	
	@Override
	public List<TypeReglementAchatValue> getAll() {
		return typeReglementPersistance.getAll();
	}

	@Override
	public TypeReglementAchatValue getById(Long id) {
		return typeReglementPersistance.getById(id);
	}

	@Override
	public String create(TypeReglementAchatValue value) {
		return typeReglementPersistance.create(value);
	}

	@Override
	public String update(TypeReglementAchatValue value) {
		return typeReglementPersistance.update(value);
	}

	@Override
	public void delete(Long id) {
		typeReglementPersistance.delete(id);
	}

}
