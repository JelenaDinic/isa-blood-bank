package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "registeredUsers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisteredUser extends Person {
    private BloodType bloodType;
}
