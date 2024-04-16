package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereMarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheMarcheValue;

/**
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */
public interface IMarcheService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(MarcheValue marcheValue);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public MarcheValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(MarcheValue marcheValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<MarcheValue> getAll();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheMarcheValue rechercherMultiCritere(
			RechercheMulticritereMarcheValue request);

	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<MarcheValue> getListById(Long id);
	

}
