package com.gpro.consulting.tiers.logistique.persistance.gl.methodeteinture.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gl.methodeteinture.value.MethodeTeintureValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.methodeteinture.entity.MethodeTeintureEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class MethodeTeinturePersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodeTeinturePersistanceUtilities.class);
	
	/************************************ Methode Teinture ****************************************/

	public static MethodeTeintureValue toValue(MethodeTeintureEntity entity) {
		
		MethodeTeintureValue value = new MethodeTeintureValue();
		
		value.setId(entity.getId());
		value.setDesignation(entity.getDesignation());
		
		return value;
	}



	public static MethodeTeintureEntity toEntity(MethodeTeintureValue value) {
		
		MethodeTeintureEntity entity = new MethodeTeintureEntity();
		
		entity.setId(value.getId());
		entity.setDesignation(value.getDesignation());
		return entity;
	}
	

	
}
