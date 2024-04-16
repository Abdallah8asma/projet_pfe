package com.gpro.consulting.tiers.logistique.persistance.gl.suivi;

import java.util.List;


import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */
public interface IEnginPersistance {
	
	public EnginValue getById(Long id);

	public List<EnginValue> getAll();
	
	public String create(EnginValue enginValue);

	public String update(EnginValue enginValue);

	public void delete(Long id);

}
