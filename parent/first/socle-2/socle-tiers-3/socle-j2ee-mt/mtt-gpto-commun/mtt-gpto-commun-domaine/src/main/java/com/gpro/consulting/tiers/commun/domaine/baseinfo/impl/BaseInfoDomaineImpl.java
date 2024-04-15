package com.gpro.consulting.tiers.commun.domaine.baseinfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.domaine.baseinfo.IBaseInfoDomaine;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;

@Component
public class BaseInfoDomaineImpl  implements IBaseInfoDomaine{
	
	@Autowired
	IBaseInfoPersistance baseInfoPersistance;
	

	@Override
	public Long getGuichetClient() {
		// TODO Auto-generated method stub
		return baseInfoPersistance.getGuichetClient();
	}

	@Override
	public Long getGuichetFournisseur() {
		// TODO Auto-generated method stub
		return baseInfoPersistance.getGuichetFournisseur();
	}

	@Override
	public String create(BaseInfoValue baseInfoValue) {
		// TODO Auto-generated method stub
		return baseInfoPersistance.create(baseInfoValue);
	}

	@Override
	public BaseInfoValue getById(Long id) {
		// TODO Auto-generated method stub
		return baseInfoPersistance.getById(id);
	}

	@Override
	public String update(BaseInfoValue baseInfoValue) {
		// TODO Auto-generated method stub
		return baseInfoPersistance.update(baseInfoValue);
	}

	@Override
	public void delete(Long id) {
		baseInfoPersistance.delete(id);
		
	}

	@Override
	public List<BaseInfoValue> getAll() {
		// TODO Auto-generated method stub
		return baseInfoPersistance.getAll();
	}

	@Override
	public BaseInfoValue getClientActif() {
		// TODO Auto-generated method stub
		return baseInfoPersistance.getClientActif();
	}

	@Override
	public String getLogo() {
		// TODO Auto-generated method stub
		return baseInfoPersistance.getLogo();
	}

}
