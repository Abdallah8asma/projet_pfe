package com.gpro.consulting.tiers.commun.persistance.baseinfo;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;

/**
 * BaseInfo Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */
public interface IBaseInfoPersistance {
	
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
