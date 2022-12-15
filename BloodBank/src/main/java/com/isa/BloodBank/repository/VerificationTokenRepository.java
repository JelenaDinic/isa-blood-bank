package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Person;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.VerifiactionToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Period;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerifiactionToken, Long> {

    VerifiactionToken findByToken(String token);

    VerifiactionToken findByUser(RegisteredUser user);
}
