package com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;

/**
 * produitCommande Service interface
 * 
 * @author Ham Etteieb
 * @since 16/0016
 *
 */
public interface IProduitCommandeAchatService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ProduitCommandeAchatValue produitCommandeValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProduitCommandeAchatValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ProduitCommandeAchatValue produitCommandeValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ProduitCommandeAchatValue> getAll();
}
