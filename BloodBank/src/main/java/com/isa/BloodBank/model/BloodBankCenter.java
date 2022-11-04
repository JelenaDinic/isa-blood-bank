package com.isa.BloodBank.model;

public class BloodBankCenter {
    private String name;
    private String address;
    private String description;
    private double averageGrade;

    public BloodBankCenter() {
    }

    public BloodBankCenter(String name, String address, String description, double averageGrade) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageGrade = averageGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
