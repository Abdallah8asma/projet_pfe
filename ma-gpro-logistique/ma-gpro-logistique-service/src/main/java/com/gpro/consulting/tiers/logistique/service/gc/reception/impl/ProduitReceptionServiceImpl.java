package com.gpro.consulting.tiers.logistique.service.gc.reception.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reception.IProduitReceptionDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.reception.IProduitReceptionService;

/**
 *
 */

@Component
public class ProduitReceptionServiceImpl implements IProduitReceptionService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProduitReceptionServiceImpl.class);

	@Autowired
	private IProduitReceptionDomaine produitReceptionDomaine;
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#create(com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitReceptionValue)
	 */
	@Override
	public String create(ProduitReceptionValue produitReceptionValue) {
		return produitReceptionDomaine.create(produitReceptionValue);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#getById(java.lang.Long)
	 */
	@Override
	public ProduitReceptionValue getById(Long id) {
		return produitReceptionDomaine.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#update(com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitReceptionValue)
	 */
	@Override
	public String update(ProduitReceptionValue produitReceptionValue) {
		// TODO Auto-generated method stub
		return produitReceptionDomaine.update(produitReceptionValue);
	}

	@Override
	public void delete(Long id) {
		produitReceptionDomaine.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#getAll()
	 */
	@Override
	public List<ProduitReceptionValue> getAll() {
		return produitReceptionDomaine.getAll();
	}
	

}
