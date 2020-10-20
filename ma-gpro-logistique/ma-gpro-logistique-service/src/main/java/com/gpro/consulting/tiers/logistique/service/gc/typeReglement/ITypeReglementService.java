package com.gpro.consulting.tiers.logistique.service.gc.typeReglement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ITypeReglementService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<TypeReglementValue> getAll();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public TypeReglementValue getById(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(TypeReglementValue value);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(TypeReglementValue value);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
}
