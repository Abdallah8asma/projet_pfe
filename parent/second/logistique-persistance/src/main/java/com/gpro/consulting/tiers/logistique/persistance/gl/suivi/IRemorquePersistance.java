package com.gpro.consulting.tiers.logistique.persistance.gl.suivi;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */
public interface IRemorquePersistance {
	
	public RemorqueValue getById(Long id);

	public List<RemorqueValue> getAll();
	
	public String create(RemorqueValue remorqueValue);

	public String update(RemorqueValue remorqueValue);

	public void delete(Long id);

}
