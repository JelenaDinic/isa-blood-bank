package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository  extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
