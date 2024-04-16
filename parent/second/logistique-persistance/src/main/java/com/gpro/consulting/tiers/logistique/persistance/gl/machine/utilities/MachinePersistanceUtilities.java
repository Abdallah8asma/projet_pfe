package com.gpro.consulting.tiers.logistique.persistance.gl.machine.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.MachineEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 31/03/2016
 *
 */

@Component
public class MachinePersistanceUtilities {


	public MachineValue toValue(MachineEntity entity) {
		
		MachineValue dto = new MachineValue();
		
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());

		return dto;
	}
	
	public MachineEntity toEntity(MachineValue dto) {
		
		MachineEntity entity = new MachineEntity();
		
		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());

		return entity;
	}
	
	public List<MachineValue> listToValue(List<MachineEntity> listEntity) {
		
		List<MachineValue> list = new ArrayList<MachineValue>();
		
		for(MachineEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

}
