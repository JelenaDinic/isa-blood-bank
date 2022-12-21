package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.UnregisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnregisteredUserRepository extends JpaRepository<UnregisteredUser, Integer> {

    UnregisteredUser findByActivationCode(String activationCode);
}
