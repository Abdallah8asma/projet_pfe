package com.gpro.consulting.tiers.logistique.service.gl.suivi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;


/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */
public interface IPersonnelService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public PersonnelValue getById(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<PersonnelValue> getAll();
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(PersonnelValue personnelValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(PersonnelValue personnelValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

}
