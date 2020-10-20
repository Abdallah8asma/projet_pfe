package com.gpro.consulting.tiers.logistique.domaine.gc.boncommande;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;

/**
 * produitCommande Domaine interface
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */
public interface IProduitCommandeDomaine {

	public String create(ProduitCommandeValue produitCommandeValue);

	public ProduitCommandeValue getById(Long id);

	public String update(ProduitCommandeValue produitCommandeValue);

	public void delete(Long id);

	public List<ProduitCommandeValue> getAll();
}
