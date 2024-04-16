package com.gpro.consulting.tiers.logistique.service.gl.methodeteinture;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.methodeteinture.value.MethodeTeintureValue;

/**
 * Fiche façon Service interface
 * 
 * @author Zeineb Medimagh
 * @since 03/10/2016
 *
 */
public interface IMethodeTeintureService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(MethodeTeintureValue methodeTeintureValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public MethodeTeintureValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(MethodeTeintureValue methodeTeintureValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MethodeTeintureValue> getAll();
	

}
