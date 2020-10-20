package com.gpro.consulting.tiers.logistique.service.gc.vente.facture;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;

/**
 * DetFacture Vente Service Interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface IDetFactureVenteService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(DetFactureVenteValue detFactureVenteValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public DetFactureVenteValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(DetFactureVenteValue detFactureVenteValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

}
