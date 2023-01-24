package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.dto.BloodSupplyDTO;
import com.isa.BloodBank.service.BloodSupplyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/blood-supply")
public class BloodSupplyController {
    private final BloodSupplyService service;

    public BloodSupplyController(BloodSupplyService service) {
        this.service = service;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/byCenter/{id}")
    public List<BloodSupplyDTO> getAllByCenterId(@PathVariable("id") int id) {
        return service.getByCenterId(id);
    }
}
