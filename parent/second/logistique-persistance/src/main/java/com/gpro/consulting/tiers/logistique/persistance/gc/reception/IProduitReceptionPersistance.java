package com.gpro.consulting.tiers.logistique.persistance.gc.reception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;

/**
 * ProduitCommande Persistance interface
 * 
  *@author Zeineb Medimagh
 * @since 16/11/2016
 *
 */
public interface IProduitReceptionPersistance {

	public String create(ProduitReceptionValue produitReceptionValue);

	public ProduitReceptionValue getById(Long id);

	public String update(ProduitReceptionValue produitReceptionValue);

	public void delete(Long id);
	
	public List<ProduitReceptionValue> getAll();
	

}
