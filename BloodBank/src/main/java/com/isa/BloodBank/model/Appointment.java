package com.isa.BloodBank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Appointment {
    private ArrayList<Staff> staff;
    private LocalDateTime time;
    private int duration; //in minutes

    public Appointment() {
    }

    public Appointment(ArrayList<Staff> staff, LocalDateTime time, int duration) {
        this.staff = staff;
        this.time = time;
        this.duration = duration;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
