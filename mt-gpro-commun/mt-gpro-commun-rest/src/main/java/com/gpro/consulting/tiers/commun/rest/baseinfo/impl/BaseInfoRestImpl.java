package com.gpro.consulting.tiers.commun.rest.baseinfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;

@Controller
@RequestMapping("/baseInfo")
public class BaseInfoRestImpl {
	
	
	@Autowired
	IBaseInfoService baseInfoService;
	
	
	
	
	@RequestMapping(value = "/getClientActif", method = RequestMethod.GET)
	public @ResponseBody BaseInfoValue getClientActif() {
		return  baseInfoService.getClientActif();
	}
	
	
	@RequestMapping(value = "/updateCurrentMode", method = RequestMethod.GET)
	public @ResponseBody boolean updateCurrentMode() {
		BaseInfoValue baseInfo =   baseInfoService.getClientActif();
		baseInfo.setBlackMode(!baseInfo.isBlackMode());
		
		baseInfoService.update(baseInfo) ;
		
		
		return baseInfo.isBlackMode() ;
		
	}

}
