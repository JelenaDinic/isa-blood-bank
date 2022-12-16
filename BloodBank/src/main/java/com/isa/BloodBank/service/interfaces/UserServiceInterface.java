package com.isa.BloodBank.service.interfaces;

import com.isa.BloodBank.model.Person;
import com.isa.BloodBank.model.UserRequest;

import java.util.List;

public interface UserServiceInterface {
    Person findById(int id);
    Person findByEmail(String email);
    List<Person> findAll ();
    Person save(UserRequest userRequest);
}
