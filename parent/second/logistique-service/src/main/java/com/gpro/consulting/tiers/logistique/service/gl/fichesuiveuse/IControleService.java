package com.gpro.consulting.tiers.logistique.service.gl.fichesuiveuse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ControleValue;

/**
 * @author Wahid Gazzah
 * @since 29 mars 2016
 */
public interface IControleService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ControleValue controleValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ControleValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ControleValue controleValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ControleValue> getAll();
}
