package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Person, Integer> {

    Page<Person> findAll(Pageable pageable);

    Person findByEmail(String email);

    @Query("select u from users u where lower(u.firstName) like %?1% or lower(u.lastName) like %?1%")
    Page<Person> findAllByFirstNameOrLastName(String searchTerm, Pageable pageable);
}
