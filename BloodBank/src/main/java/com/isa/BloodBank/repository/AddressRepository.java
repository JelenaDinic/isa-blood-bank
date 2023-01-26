package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findAddressById(int id);

}
