package com.gpro.consulting.tiers.logistique.persistance.gc.boncommande;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;

/**
 * ProduitCommande Persistance interface
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */
public interface IProduitCommandePersistance {

	public String create(ProduitCommandeValue ProduitCommandeValue);

	public ProduitCommandeValue getById(Long id);

	public String update(ProduitCommandeValue ProduitCommandeValue);

	public void delete(Long id);

	public List<ProduitCommandeValue> getAll();

}
