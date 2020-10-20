package com.gpro.consulting.tiers.logistique.service.gc.guichet;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;

/**
 * @author zormati wassim
 *
 */
public interface IGuichetAnnuelService {
	
	/*@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<GuichetAnnuelValue> getAll();*/
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GuichetAnnuelValue getById(Long id);
	
	 public String updateGuichetAnnuel(GuichetAnnuelValue guichetAnnuelValue);
	
	

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Long modifierGuichetAvoirAnnuel(GuichetAnnuelValue value);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Long modifierGuichetFactureAnnuel(GuichetAnnuelValue value);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Long modifierGuichetReglementAnnuel(GuichetAnnuelValue value);



	



	

	
	

	
	
}
