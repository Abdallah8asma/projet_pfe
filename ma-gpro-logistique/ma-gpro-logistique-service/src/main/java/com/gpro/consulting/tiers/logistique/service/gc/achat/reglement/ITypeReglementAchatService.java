package com.gpro.consulting.tiers.logistique.service.gc.achat.reglement;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ITypeReglementAchatService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<TypeReglementAchatValue> getAll();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public TypeReglementAchatValue getById(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(TypeReglementAchatValue value);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(TypeReglementAchatValue value);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
}
