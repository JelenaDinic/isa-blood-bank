package com.isa.BloodBank.dto;


import com.isa.BloodBank.model.RegisteredUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDisplayDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String number;
    private String city;
    private String country;
    private String phoneNumber;
    private String personalId;
    private String profession;
    private String professionInfo;
    private String dateOfBirth;
    private String gender;

    private int addressId;

    public UserProfileDisplayDTO(RegisteredUser registeredUser){
        this.firstName = registeredUser.getFirstName();
        this.lastName = registeredUser.getLastName();
        this.email = registeredUser.getEmail();
        this.street = registeredUser.getAddress().getStreet();
        this.number = registeredUser.getAddress().getNumber();
        this.city = registeredUser.getAddress().getCity();
        this.country = registeredUser.getAddress().getCountry();
        this.phoneNumber = registeredUser.getPhoneNumber();
        this.personalId = registeredUser.getPersonalId().toString();
        this.profession = registeredUser.getProfession();
        this.professionInfo = registeredUser.getProfessionInfo();
        this.dateOfBirth = registeredUser.getDob().toString();
        this.gender = registeredUser.getGender();
        this.addressId = registeredUser.getAddress().getId();

    }
    public UserProfileDisplayDTO(){

    }
}
