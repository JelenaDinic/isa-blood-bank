package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.BloodDonorCreationDTO;
import com.isa.BloodBank.model.BloodDonorForm;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.repository.BloodDonorRepository;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodDonorService {

        private final BloodDonorRepository bloodDonorRepository;
        private final RegisteredUserRepository registeredUserRepository;

        @Autowired
        public BloodDonorService(BloodDonorRepository bloodDonorRepository, RegisteredUserRepository registeredUserRepository) {
            this.bloodDonorRepository = bloodDonorRepository;
            this.registeredUserRepository = registeredUserRepository;
        }

        public BloodDonorForm create(BloodDonorCreationDTO bloodDonorCreationDTO) {
            RegisteredUser registeredUser = registeredUserRepository.getReferenceById(bloodDonorCreationDTO.getUserId());
            BloodDonorForm bloodDonorForm = new BloodDonorForm(bloodDonorCreationDTO, registeredUser);
            return bloodDonorRepository.save(bloodDonorForm);
        }

        public List<BloodDonorForm> findAll() {
            return bloodDonorRepository.findAll();
        }

        public boolean checkIfAllowed(int id) {
            BloodDonorForm bloodDonorForm = bloodDonorRepository.findFormByUserId(id);
            boolean isAllowed = true;
            if(bloodDonorForm != null) {
            if (bloodDonorForm.isUnacceptableWeight() || bloodDonorForm.isUnhealthy() || bloodDonorForm.isHasSkinProblems() || bloodDonorForm.isHasUnacceptablePressure() || bloodDonorForm.isUnderTreatment() || bloodDonorForm.isOnPeriod() || bloodDonorForm.isHasTattoo()) {
                isAllowed = false;
            }}
            return isAllowed;
        }


}
