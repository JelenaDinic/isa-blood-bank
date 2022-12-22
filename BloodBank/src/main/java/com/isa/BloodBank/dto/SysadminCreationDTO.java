package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysadminCreationDTO {
    private String fullName;

    private int id;

    @NotNull
    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp="([A-Z][a-z]+)(\\s[A-Z]?[a-z]+)*", message="Invalid firstname input!")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp="([A-Z][a-z]+)(\\s[A-Z]?[a-z]+)*", message="Invalid lastname input!")
    private String lastName;

    @NotNull
    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "Invalid email format")
    private String email;

    @NotNull
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotNull
    private UserRole role;

    @NotNull
    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotNull
    @NotBlank(message = "Street number cannot be blank")
    private String number;

    @NotNull
    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotNull
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotNull
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotNull
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @NotNull
    @Digits(integer = 13, fraction = 0, message = "Personal id must be exactly 13 digits")
    private Long personalId;

    @NotNull
    @NotBlank(message = "Gender cannot be blank")
    private String gender;
    private int addressId;
}
