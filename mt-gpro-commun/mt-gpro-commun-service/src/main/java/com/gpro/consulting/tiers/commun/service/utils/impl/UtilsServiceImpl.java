package com.gpro.consulting.tiers.commun.service.utils.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.utils.value.DateUtilsValue;
import com.gpro.consulting.tiers.commun.domaine.utils.IUtilsDomaine;
import com.gpro.consulting.tiers.commun.service.utils.IUtilsService;

@Service
public class UtilsServiceImpl implements IUtilsService {
	
	  @Autowired
	  private IUtilsDomaine utilsDomaine;

	@Override
	public List<DateUtilsValue> getDateDebAndMaxGroupByMonth(DateUtilsValue dateUtils) {
	
		return utilsDomaine.getDateDebAndMaxGroupByMonth(dateUtils);
	}

}
