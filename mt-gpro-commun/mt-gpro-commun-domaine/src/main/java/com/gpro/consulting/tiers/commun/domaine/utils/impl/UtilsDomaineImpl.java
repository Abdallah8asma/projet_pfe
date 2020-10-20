package com.gpro.consulting.tiers.commun.domaine.utils.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.utils.value.DateUtilsValue;
import com.gpro.consulting.tiers.commun.domaine.utils.IUtilsDomaine;

@Component
public class UtilsDomaineImpl implements IUtilsDomaine {

	@Override
	public List<DateUtilsValue> getDateDebAndMaxGroupByMonth(DateUtilsValue dateUtils) {
	
		
		List<DateUtilsValue> list = new ArrayList<>();
		
		Calendar c3 = (Calendar)dateUtils.getDateMin().clone();
		
		Calendar c2 = (Calendar)dateUtils.getDateMax().clone();
		
		
		while(c3.before(c2)) {
			
			Calendar deb = (Calendar)c3.clone();
		
			deb.set(Calendar.DAY_OF_MONTH, 1);
					
			Calendar fin =(Calendar) c3.clone();
			fin.set(Calendar.DAY_OF_MONTH, c3.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			
		//	System.out.println("deb = "+dateFormat2.format(deb.getTime()) );
		//	System.out.println("fin = "+dateFormat2.format(fin.getTime()) );
			
			
			
			
			c3 = (Calendar)fin.clone();
			c3.add(Calendar.DATE, 1);
			
			list.add(new DateUtilsValue(deb,fin));
			
			
		}
		
		return list;
		
		
	}

}
