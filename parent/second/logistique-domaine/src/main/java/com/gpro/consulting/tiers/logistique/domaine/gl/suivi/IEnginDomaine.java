package com.gpro.consulting.tiers.logistique.domaine.gl.suivi;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;

public interface IEnginDomaine {
	
    public List<EnginValue> getAll();
	
	public String create(EnginValue enginValue);

	public String update(EnginValue enginValue);

	public void delete(Long id);
	
	public EnginValue getById(Long id);

}
