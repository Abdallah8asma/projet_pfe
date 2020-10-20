package com.gpro.consulting.tiers.commun.service.baseinfo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;

public interface IBaseInfoService {

	public Long getGuichetClient();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Long getGuichetFournisseur();

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(BaseInfoValue baseInfoValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BaseInfoValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(BaseInfoValue baseInfoValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<BaseInfoValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BaseInfoValue getClientActif();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getLogo();

}
