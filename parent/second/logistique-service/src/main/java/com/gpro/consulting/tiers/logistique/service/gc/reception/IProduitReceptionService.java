package com.gpro.consulting.tiers.logistique.service.gc.reception;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;

/**
 * produitCommande Service interface
 * 
 * Hajer AMRI
 */
public interface IProduitReceptionService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ProduitReceptionValue produitReceptionValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProduitReceptionValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ProduitReceptionValue produitReceptionValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ProduitReceptionValue> getAll();
}
