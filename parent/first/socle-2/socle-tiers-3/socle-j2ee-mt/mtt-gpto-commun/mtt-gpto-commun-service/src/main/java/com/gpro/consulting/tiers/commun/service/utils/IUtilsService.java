package com.gpro.consulting.tiers.commun.service.utils;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.utils.value.DateUtilsValue;

public interface IUtilsService {

	List<DateUtilsValue> getDateDebAndMaxGroupByMonth(DateUtilsValue dateUtils);

}
