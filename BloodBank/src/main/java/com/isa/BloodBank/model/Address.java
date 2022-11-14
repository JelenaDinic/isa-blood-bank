package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "adresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private String number;
    private String city;
    private String country;


    public Address(String street, String number, String city, String country){
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString(){
        return this.getStreet() + " " + this.getNumber() + ", " + this.getCity() + " " + this.getCountry();
    }

}
