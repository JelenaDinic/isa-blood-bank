package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.dto.BloodbankDisplayDTO;
import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.service.BloodBankCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="api/blood-bank-center")
public class BloodBankCenterController {
        private final BloodBankCenterService bloodBankCenterService;
    @Autowired
    public BloodBankCenterController(BloodBankCenterService bloodBankCenterService) {
        this.bloodBankCenterService = bloodBankCenterService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEMADMIN')")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody BloodBankCreationDTO bloodBankCenter, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.err.println("Error creating new staff!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            bloodBankCenterService.create(bloodBankCenter);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public BloodBankCreationDTO getById(@PathVariable("id") int id) {
        return bloodBankCenterService.getById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<BloodBankCenter>> getAll() {
        List<BloodBankCenter> bloodBankCenters = bloodBankCenterService.findAll();
        return new ResponseEntity<>(bloodBankCenters, HttpStatus.OK);
    }

    @GetMapping("/all-bloodbankDTOs")
    public ResponseEntity<List<BloodbankDisplayDTO>> getAllDTOs(Pageable page) {

        ArrayList<BloodbankDisplayDTO> bloodBanksDTOs = (ArrayList<BloodbankDisplayDTO>) bloodBankCenterService.findAllCenters(page);

        return new ResponseEntity<>(bloodBanksDTOs, HttpStatus.OK);
    }

    @GetMapping(path = "/searchBanks")
    public ResponseEntity<List<BloodbankDisplayDTO>> searchBanks(Pageable page, @RequestParam("searchName") Optional<String> searchName, @RequestParam("searchCity") Optional<String> searchCity, @RequestParam("filterByRating") Optional<Double> filterByRating, @RequestParam("sortByParam") Optional<String> sortByParam, @RequestParam("sortDirection") Optional<String> sortDirection) {
        List<BloodbankDisplayDTO> bloodbankDisplayDTOs = bloodBankCenterService.searchBanks(page, searchName.get(), searchCity.get(), filterByRating.get(), sortByParam.get(), sortDirection.get());

        return new ResponseEntity<>(bloodbankDisplayDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody BloodBankCreationDTO bloodBankDTO) {
        bloodBankCenterService.update(bloodBankDTO);
    }
}
