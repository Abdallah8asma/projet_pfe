package com.gpro.consulting.tiers.logistique.domaine.gc.reception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;

/**
 * produitCommande Domaine interface
 * 
 *Hajer AMRI
 */
public interface IProduitReceptionDomaine {

	public String create(ProduitReceptionValue produitReceptionValue);

	public ProduitReceptionValue getById(Long id);

	public String update(ProduitReceptionValue produitReceptionValue);

	public void delete(Long id);
	
	public List<ProduitReceptionValue> getAll();
}
