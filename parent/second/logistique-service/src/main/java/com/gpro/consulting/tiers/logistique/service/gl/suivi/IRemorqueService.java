package com.gpro.consulting.tiers.logistique.service.gl.suivi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;


/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */
public interface IRemorqueService {
	

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public RemorqueValue getById(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RemorqueValue> getAll();
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(RemorqueValue remorquelValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(RemorqueValue remorquelValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);


}
