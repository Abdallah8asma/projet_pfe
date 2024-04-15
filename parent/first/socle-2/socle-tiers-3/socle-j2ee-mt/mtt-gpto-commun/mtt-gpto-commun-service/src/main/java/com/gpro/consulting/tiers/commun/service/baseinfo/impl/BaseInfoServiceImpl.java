package com.gpro.consulting.tiers.commun.service.baseinfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.domaine.baseinfo.IBaseInfoDomaine;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;

@Service
@Transactional
public class BaseInfoServiceImpl implements IBaseInfoService {

	@Autowired
	IBaseInfoDomaine baseInfoDomaine;
	
	
	@Override
	public Long getGuichetClient() {
		// TODO Auto-generated method stub
		return baseInfoDomaine.getGuichetClient();
	}

	@Override
	public Long getGuichetFournisseur() {
		// TODO Auto-generated method stub
		return baseInfoDomaine.getGuichetFournisseur();
	}

	@Override
	public String create(BaseInfoValue baseInfoValue) {
		// TODO Auto-generated method stub
		return baseInfoDomaine.create(baseInfoValue);
	}

	@Override
	public BaseInfoValue getById(Long id) {
		// TODO Auto-generated method stub
		return baseInfoDomaine.getById(id);
	}

	@Override
	public String update(BaseInfoValue baseInfoValue) {
		// TODO Auto-generated method stub
		return baseInfoDomaine.update(baseInfoValue);
	}

	@Override
	public void delete(Long id) {
		baseInfoDomaine.delete(id);
		
	}

	@Override
	public List<BaseInfoValue> getAll() {
		// TODO Auto-generated method stub
		return baseInfoDomaine.getAll();
	}

	@Override
	public BaseInfoValue getClientActif() {
		// TODO Auto-generated method stub
		return baseInfoDomaine.getClientActif();
	}

	@Override
	public String getLogo() {
		// TODO Auto-generated method stub
		return baseInfoDomaine.getLogo();
	}

}
