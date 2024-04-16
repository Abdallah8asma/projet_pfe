package com.gpro.consulting.tiers.logistique.persistance.gl.suivi.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.entity.EnginEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.entity.PersonnelEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.suivi.entity.RemorqueEntity;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */
@Component
public class Persistanceutilities {
	
	
/**conversion to value Personnel Entity*/	
public PersonnelValue toValue(PersonnelEntity entity) {
		
	PersonnelValue dto = new PersonnelValue();
		
		dto.setId(entity.getId());
		dto.setMatricule(entity.getMatricule());
		dto.setNom(entity.getNom());
		dto.setPrenom(entity.getPrenom());

		return dto;
	}
	
    /**conversion value to entity Personnel*/
	public PersonnelEntity toEntity(PersonnelValue dto) {
		
		PersonnelEntity entity = new PersonnelEntity();
		
		entity.setId(dto.getId());
		entity.setMatricule(dto.getMatricule());
		entity.setNom(dto.getNom());
		entity.setPrenom(dto.getPrenom());

		return entity;
	}
	
	/**conversion ListPersonnel to value*/ 
	public List<PersonnelValue> listToValue(List<PersonnelEntity> listEntity) {
		
		List<PersonnelValue> list = new ArrayList<PersonnelValue>();
		
		for(PersonnelEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
	
	/**conversion Remorque to Value*/
	
	public RemorqueValue toValue(RemorqueEntity entity) {
		
		RemorqueValue dto = new RemorqueValue();
			
			dto.setId(entity.getId());
			dto.setImmatriculation(entity.getImmatriculation());
            dto.setType(entity.getType());
			return dto;
		}
		
	    /**conversion value to entity Remorque*/
		public RemorqueEntity toEntity(RemorqueValue dto) {
			
			RemorqueEntity entity = new RemorqueEntity();
			
			entity.setId(dto.getId());
			entity.setImmatriculation(dto.getImmatriculation());
            entity.setType(dto.getType());
			return entity;
		}
		
		/**conversion ListRemorque to value*/ 
		public List<RemorqueValue> listtoValue(List<RemorqueEntity> listEntity) {
			
			List<RemorqueValue> list = new ArrayList<RemorqueValue>();
			
			for(RemorqueEntity entity : listEntity){
				list.add(toValue(entity));
			}
			
			return list;
		}
		
		//**conversion to value Engin*/
		
		public EnginValue toValue(EnginEntity entity) {
			
			EnginValue dto = new EnginValue();
				
				dto.setId(entity.getId());
				dto.setImmatriculation(entity.getSerie()+"TU"+entity.getImmatriculation());
				dto.setMarque(entity.getMarque());
                dto.setSerie(entity.getSerie());
				return dto;
			}
			
		    /**conversion value to entity Engin*/
			public EnginEntity toEntity(EnginValue dto) {
				
				EnginEntity entity = new EnginEntity();
				
				entity.setId(dto.getId());
				entity.setImmatriculation(dto.getImmatriculation());
				entity.setMarque(dto.getMarque());
                entity.setSerie(dto.getSerie());
				return entity;
			}
			
			/**conversion List ENGIN to value*/ 
			public List<EnginValue> listtovalue(List<EnginEntity> listEntity) {
				
				List<EnginValue> list = new ArrayList<EnginValue>();
				
				for(EnginEntity entity : listEntity){
					list.add(toValue(entity));
				}
				
				return list;
			}
	
	


}
