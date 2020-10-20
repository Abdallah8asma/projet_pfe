package com.gpro.consulting.tiers.logistique.service.gc.achat.facture;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;

/**
 * DetFacture Achat Service Interface
 * 
 * @author Hamdi Etteieb
 * @since 24/09/2018
 *
 */
public interface IDetFactureAchatService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(DetFactureAchatValue detFactureAchatValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public DetFactureAchatValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(DetFactureAchatValue detFactureAchatValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

}
