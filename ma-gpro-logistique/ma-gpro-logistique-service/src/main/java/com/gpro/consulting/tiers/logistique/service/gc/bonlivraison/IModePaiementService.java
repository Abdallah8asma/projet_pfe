package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;

/**
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */
public interface IModePaiementService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ModePaiementValue modePaiementValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ModePaiementValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ModePaiementValue modePaiementValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<ModePaiementValue> getAll();

}
