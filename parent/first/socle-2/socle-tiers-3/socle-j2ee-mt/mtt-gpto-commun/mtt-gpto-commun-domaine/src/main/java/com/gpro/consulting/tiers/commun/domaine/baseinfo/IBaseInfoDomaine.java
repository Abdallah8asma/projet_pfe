package com.gpro.consulting.tiers.commun.domaine.baseinfo;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;

public interface IBaseInfoDomaine {
	
public Long getGuichetClient();
	
	public Long getGuichetFournisseur();

	public String create(BaseInfoValue baseInfoValue);

	public BaseInfoValue getById(Long id);

	public String update(BaseInfoValue baseInfoValue);

	public void delete(Long id);

	public List<BaseInfoValue> getAll();
	
	public BaseInfoValue getClientActif();
	
	public String getLogo();
}
