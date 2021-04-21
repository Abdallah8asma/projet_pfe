package com.gpro.consulting.tiers.logistique.service.gc.guichet;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.RechercheMulticritereGuichetMensuelValue;

/**
 * @author zormati wassim
 *
 */
public interface IGuichetMensuelService {
	

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GuichetMensuelValue getById(Long id);
	
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	 public String update(GuichetMensuelValue guichetMensuelValue);
	 
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	 public String create(GuichetMensuelValue guichetMensuelValue);
		
		
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	 public String deleteById(Long id);
	

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	 public List<GuichetMensuelValue> rechercheMultiCritere(RechercheMulticritereGuichetMensuelValue request);
	

	
	
}
