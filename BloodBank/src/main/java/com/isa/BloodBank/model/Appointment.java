package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    private int id;
    private ArrayList<Staff> staff;
    private LocalDateTime time;
    private int duration; //in minutes
}
