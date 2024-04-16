package com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.ITypeReglementAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.ITypeReglementAchatService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class TypeReglementAchatServiceImpl implements ITypeReglementAchatService{
	
	@Autowired
	ITypeReglementAchatDomaine typeReglementAchatDomaine;
	
	@Override
	public List<TypeReglementAchatValue> getAll() {
		
		return typeReglementAchatDomaine.getAll();
	}

	@Override
	public TypeReglementAchatValue getById(Long id) {
		return typeReglementAchatDomaine.getById(id);
	}

	@Override
	public String create(TypeReglementAchatValue value) {
		return typeReglementAchatDomaine.create(value);
	}

	@Override
	public String update(TypeReglementAchatValue value) {
		return typeReglementAchatDomaine.update(value);
	}

	@Override
	public void delete(Long id) {
		typeReglementAchatDomaine.delete(id);
	}
	
}
