package com.gpro.consulting.tiers.logistique.service.gc.bonCommande;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;

/**
 * produitCommande Service interface
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 * 
 */
public interface IProduitCommandeService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ProduitCommandeValue ProduitCommandeValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProduitCommandeValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ProduitCommandeValue ProduitCommandeValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ProduitCommandeValue> getAll();
}
