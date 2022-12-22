package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.model.Equipment;
import com.isa.BloodBank.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService service;

    @Autowired
    public EquipmentController(EquipmentService service) {
        this.service = service;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PutMapping("/updateQuantity")
    public ResponseEntity<Object> update(@RequestBody Equipment[] equipmentArr) {
        try {
            service.updateQuantity(equipmentArr);
            return new ResponseEntity<>(equipmentArr, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
