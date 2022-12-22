package com.isa.BloodBank.service;

import com.isa.BloodBank.model.Equipment;
import com.isa.BloodBank.repository.EquipmentRepository;
import org.springframework.stereotype.Service;


@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {

        this.equipmentRepository = equipmentRepository;
    }

    public void updateQuantity(Equipment[] equipmentArr) {
        for(Equipment e : equipmentArr ) {
            Equipment eDB = equipmentRepository.getEquipmentByType(e.getType());
            eDB.setQuantity(eDB.getQuantity() - e.getQuantity());
            equipmentRepository.save(eDB);
        }
    }
}
