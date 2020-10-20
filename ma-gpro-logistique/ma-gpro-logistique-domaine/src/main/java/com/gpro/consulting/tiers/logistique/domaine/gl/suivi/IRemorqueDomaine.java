package com.gpro.consulting.tiers.logistique.domaine.gl.suivi;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;

public interface IRemorqueDomaine {
	
    public RemorqueValue getById(Long id);
	
	public List<RemorqueValue> getAll();
	
	public String create(RemorqueValue remorqueValue);

	public String update(RemorqueValue remorqueValue);

	public void delete(Long id);
	
	

}
