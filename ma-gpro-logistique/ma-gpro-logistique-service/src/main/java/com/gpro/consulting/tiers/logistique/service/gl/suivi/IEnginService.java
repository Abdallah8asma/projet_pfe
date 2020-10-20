package com.gpro.consulting.tiers.logistique.service.gl.suivi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */
public interface IEnginService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EnginValue getById(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<EnginValue> getAll();
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(EnginValue enginValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(EnginValue enginValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

}
