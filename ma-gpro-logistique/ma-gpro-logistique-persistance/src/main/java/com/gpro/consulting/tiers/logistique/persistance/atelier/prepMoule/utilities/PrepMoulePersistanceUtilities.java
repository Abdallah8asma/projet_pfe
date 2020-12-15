package com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule.utilities;


import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ElementRechechePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.PrepMouleValue;

import com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule.entity.PrepMouleEntity;

@Component
public class PrepMoulePersistanceUtilities {
	/**
	 * Instanciation du gestionnaire de persistance
	 */
	private static PrepMoulePersistanceUtilities instance = new PrepMoulePersistanceUtilities();




	public static PrepMouleEntity toEntity(PrepMouleValue dto) {

		PrepMouleEntity entity = new PrepMouleEntity();

		if (dto.getId() != null) {
			entity.setId(dto.getId());
		}
        
		entity.setIdMoule(dto.getIdMoule());
		entity.setReference(dto.getReference());
		entity.setMachine(dto.getMachine());
		entity.setDesignation(dto.getDesignation());
		entity.setEmplacement(dto.getEmplacement());
		entity.setDatePreparation(dto.getDatePrep());

		return entity;
	}

	/**
	 * 
	 * Méthode permettant la conversion d'une entité en valeur : PrepMouleValue
	 * 
	 * @param PrepMouleEntity
	 * @return
	 */

	public static PrepMouleValue toValue(PrepMouleEntity entity) {

		PrepMouleValue dto = new PrepMouleValue();

		dto.setId(entity.getId());
		dto.setIdMoule(entity.getIdMoule());
		dto.setReference(entity.getReference());
        dto.setMachine(entity.getMachine());
		dto.setEmplacement(entity.getEmplacement());
		dto.setDatePrep(entity.getDatePreparation());
		dto.setDesignation(entity.getDesignation());

		return dto;

	}


	public ElementRechechePrepMouleValue ResultatRechechePrepMouleValue(PrepMouleEntity vPrepMouleEntite) {
		
		ElementRechechePrepMouleValue vElementRechechePrepMouleValue = new ElementRechechePrepMouleValue();
		
		vElementRechechePrepMouleValue.setIdPrepMoule(vPrepMouleEntite.getId());
		vElementRechechePrepMouleValue.setIdMoule(vPrepMouleEntite.getIdMoule());
		vElementRechechePrepMouleValue.setReference(vPrepMouleEntite.getReference());
		vElementRechechePrepMouleValue.setMachine(vPrepMouleEntite.getMachine());
		vElementRechechePrepMouleValue.setDesignation(vPrepMouleEntite.getDesignation());
		vElementRechechePrepMouleValue.setEmplacement(vPrepMouleEntite.getEmplacement());
		vElementRechechePrepMouleValue.setDatePrep(vPrepMouleEntite.getDatePreparation());
		
		return vElementRechechePrepMouleValue;
	}

	/**
	 * @return the instance
	 */
	public static PrepMoulePersistanceUtilities getInstance() {
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(PrepMoulePersistanceUtilities instance) {
		PrepMoulePersistanceUtilities.instance = instance;
	}


}
