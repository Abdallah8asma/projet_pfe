package com.gpro.consulting.tiers.commun.rest.utils;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gpro.consulting.tiers.commun.coordination.utils.value.DateUtilsValue;

public interface UtilsRest {
	@RequestMapping(value = "/getDateDebAndMaxGroupByMonth", method = RequestMethod.POST)
	List<DateUtilsValue> getDateDebAndMaxGroupByMonth(DateUtilsValue dateUtils);

}
