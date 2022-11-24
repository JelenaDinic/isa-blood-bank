package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloodBankCenterRepository extends JpaRepository<BloodBankCenter, Integer> {
    BloodBankCenter findBloodBankCenterById(int id);
    Page<BloodBankCenter> findAll(Pageable pageable);
    @Query("select b from bloodBankCenters b, adresses adr where b.address.id = adr.id and lower(b.name) like %?1% and lower(adr.city) like %?2%")
    Page<BloodBankCenter> findAllByNameAndCity(String searchName,String searchCity, Pageable pageable);
}
//SELECT * FROM public.blood_bank_centers b, public.adresses adr
 //       where b.address_id = adr.id
   //     and lower(b.name) like '%'||'nk'||'%' and lower(adr.city) like '%'||'kr'||'%'