package com.gpro.consulting.tiers.logistique.domaine.gl.methodeteinture;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.methodeteinture.value.MethodeTeintureValue;

/**
 * Methode Teinture Domaine interface
 * 
 * @author Zeineb Medimagh
 * @since 03/10/2016
 *
 */
public interface IMethodeTeintureDomaine {

	public String create(MethodeTeintureValue methodeTeintureValue);

	public MethodeTeintureValue getById(Long id);

	public String update(MethodeTeintureValue methodeTeintureValue);

	public void delete(Long id);

	public List<MethodeTeintureValue> getAll();

}
