package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ITypeReglementAchatDomaine {
	
	public List<TypeReglementAchatValue> getAll();
	
	public TypeReglementAchatValue getById(Long id);
	
	public String create(TypeReglementAchatValue value);

	public String update(TypeReglementAchatValue value);

	public void delete(Long id);
}
