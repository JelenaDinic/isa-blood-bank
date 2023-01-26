package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.model.Address;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.FreeExamination;
import com.isa.BloodBank.repository.FreeExaminationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FreeExaminationService {

    private final FreeExaminationRepository repository;

    public FreeExaminationService(FreeExaminationRepository repository) {
        this.repository = repository;
    }

    public void create(FreeExamination freeExamination){
        try {
            repository.save(freeExamination);
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Can't save blood bank!");
        }

    }
    public FreeExamination getById(int id) {
        return repository.findFreeExaminationById(id);
    }
}
