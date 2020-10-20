package com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ControleValue;


/**
 * Controle Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */
public interface IControlePersistance {
	
	public String create(ControleValue controleValue);

	public ControleValue getById(Long id);

	public String update(ControleValue controleValue);

	public void delete(Long id);

	public List<ControleValue> getAll();

}
