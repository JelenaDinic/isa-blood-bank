package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.StaffCreationDTO;
import com.isa.BloodBank.model.Address;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.repository.AddressRepository;
import com.isa.BloodBank.repository.BloodBankCenterRepository;
import com.isa.BloodBank.repository.StaffRepository;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository repository;
    private final BloodBankCenterRepository bloodBankCenterRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public StaffService(StaffRepository repository, BloodBankCenterRepository  bloodBankCenterRepository, AddressRepository addressRepository) {
        this.repository = repository;
        this.bloodBankCenterRepository = bloodBankCenterRepository;
        this.addressRepository = addressRepository;
    }

    public Staff create(StaffCreationDTO staffCreationDTO) {
        BloodBankCenter bloodBankCenter = bloodBankCenterRepository.findBloodBankCenterById(staffCreationDTO.getBloodBankId());
        Staff staff = new Staff(staffCreationDTO);
        staff.setBloodBankCenter(bloodBankCenter);

        Address address = new Address(staffCreationDTO.getStreet(), staffCreationDTO.getNumber(), staffCreationDTO.getCity(), staffCreationDTO.getCountry());
        addressRepository.save(address);

        staff.setAddress(address);

        return repository.save(staff);
    }
    public Staff update(StaffCreationDTO staffCreationDTO) {
        Staff staff = repository.findStaffById(staffCreationDTO.getId());
        Address address = addressRepository.findAddressById(staffCreationDTO.getAddressId());
        address.setStreet(staffCreationDTO.getStreet());
        address.setNumber(staffCreationDTO.getNumber());
        address.setCity(staffCreationDTO.getCity());
        address.setCountry(staffCreationDTO.getCountry());
        addressRepository.save(address);
        staff.setAddress(address);
        staff.setDob(staffCreationDTO.getDob());
        staff.setEmail(staffCreationDTO.getEmail());
        staff.setFirstName(staffCreationDTO.getFirstName());
        staff.setLastName(staffCreationDTO.getLastName());
        staff.setPersonalId(staffCreationDTO.getPersonalId());
        staff.setPhoneNumber(staffCreationDTO.getPhoneNumber());

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
    public StaffCreationDTO getById(int id) {
        Staff staff = repository.findStaffById(id);
        StaffCreationDTO dto = new StaffCreationDTO(staff);
        return dto;

    }
}
