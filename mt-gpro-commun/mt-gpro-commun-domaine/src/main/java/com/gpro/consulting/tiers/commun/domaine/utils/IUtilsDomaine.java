package com.gpro.consulting.tiers.commun.domaine.utils;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.utils.value.DateUtilsValue;

public interface IUtilsDomaine {
	List<DateUtilsValue> getDateDebAndMaxGroupByMonth(DateUtilsValue dateUtils);
}
