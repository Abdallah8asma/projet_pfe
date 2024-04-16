package com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;

/**
 * ProduitCommande Persistance interface
 * 
 * @author Hamdi Etteieb
 * @since 16/09/2018
 * 
 */
public interface IProduitCommandeAchatPersistance {

	public String create(ProduitCommandeAchatValue produitCommandeValue);

	public ProduitCommandeAchatValue getById(Long id);

	public String update(ProduitCommandeAchatValue produitCommandeValue);

	public void delete(Long id);

	public List<ProduitCommandeAchatValue> getAll();

}
