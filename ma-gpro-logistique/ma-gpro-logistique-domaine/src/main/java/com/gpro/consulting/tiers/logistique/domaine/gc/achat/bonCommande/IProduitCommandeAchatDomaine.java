package com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;

/**
 * produitCommande Domaine interface
 * 
 * @author HAmdi Etteieb
 * @since 16/11/2016
 *
 */
public interface IProduitCommandeAchatDomaine {

	public String create(ProduitCommandeAchatValue produitCommandeValue);

	public ProduitCommandeAchatValue getById(Long id);

	public String update(ProduitCommandeAchatValue produitCommandeValue);

	public void delete(Long id);

	public List<ProduitCommandeAchatValue> getAll();
}
