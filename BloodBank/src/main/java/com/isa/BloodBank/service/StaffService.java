package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.StaffCreationDTO;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.repository.BloodBankCenterRepository;
import com.isa.BloodBank.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository repository;
    private final BloodBankCenterRepository bloodBankCenterRepository;

    @Autowired
    public StaffService(StaffRepository repository, BloodBankCenterRepository  bloodBankCenterRepository) {
        this.repository = repository;
        this.bloodBankCenterRepository = bloodBankCenterRepository;
    }

    public Staff create(StaffCreationDTO staffCreationDTO) {
        BloodBankCenter bloodBankCenter = new BloodBankCenter();
        bloodBankCenter.setId(staffCreationDTO.getBloodBankId());
        Staff staff = new Staff(staffCreationDTO);
        staff.setBloodBankCenter(bloodBankCenter);
        return repository.save(staff);
    }
    public Staff update(Staff staff) {
        return repository.save(staff);
    }
    public List<Staff> findAllByCenterId(int id) {
        List<Staff> bloodbankStaff = new ArrayList<>();
        for(Staff staff: repository.findAll()) {
            if(staff.getBloodBankCenter().getId() == id) {
                bloodbankStaff.add(staff);
            }
        }
        return bloodbankStaff;
    }

    public List<Staff> findAll() {
        return repository.findAll();
    }
    public Optional<Staff> getById(int id) {
        return repository.findById(id);
    }
}
