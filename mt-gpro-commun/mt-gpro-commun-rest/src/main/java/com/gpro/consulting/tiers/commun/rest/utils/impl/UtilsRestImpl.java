package com.gpro.consulting.tiers.commun.rest.utils.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.utils.value.DateUtilsValue;
import com.gpro.consulting.tiers.commun.rest.utils.UtilsRest;
import com.gpro.consulting.tiers.commun.service.utils.IUtilsService;

/**
 * 
 * @author $Author: Samer Hassen $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/utils")
public class UtilsRestImpl implements UtilsRest {

  @Autowired
  private IUtilsService utilsService;


	private static final Logger LOGGER = LoggerFactory.getLogger(UtilsRestImpl.class);

  
  /**
   * Constructeur de la classe.
   */
  public UtilsRestImpl() {
    // Constructeur vide
  }


@Override
public @ResponseBody List<DateUtilsValue> getDateDebAndMaxGroupByMonth( @RequestBody  DateUtilsValue dateUtils) {
	// TODO Auto-generated method stub
	return utilsService.getDateDebAndMaxGroupByMonth(dateUtils);
}


}
