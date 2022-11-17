package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.BloodDonorCreationDTO;
import com.isa.BloodBank.model.BloodDonorForm;
import com.isa.BloodBank.repository.BloodDonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodDonorService {

        private final BloodDonorRepository bloodDonorRepository;

        @Autowired
        public BloodDonorService(BloodDonorRepository bloodDonorRepository) {
            this.bloodDonorRepository = bloodDonorRepository;
        }

        public BloodDonorForm create(BloodDonorCreationDTO bloodDonorCreationDTO) {
            BloodDonorForm bloodDonorForm = new BloodDonorForm(bloodDonorCreationDTO);
            return bloodDonorRepository.save(bloodDonorForm);
        }

        public List<BloodDonorForm> findAll() {
            return bloodDonorRepository.findAll();
        }


}
