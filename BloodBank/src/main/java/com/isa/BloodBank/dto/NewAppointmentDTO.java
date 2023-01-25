package com.isa.BloodBank.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class NewAppointmentDTO {
    private int bloodBankCenterId;
    private LocalDateTime dateTime;

    public NewAppointmentDTO() {
    }

    public NewAppointmentDTO(int bloodBankCenterId, LocalDateTime dateTime) {
        this.bloodBankCenterId = bloodBankCenterId;
        this.dateTime = dateTime;
    }

    public int getBloodBankCenterId() {
        return bloodBankCenterId;
    }

    public void setBloodBankCenterId(int bloodBankCenterId) {
        this.bloodBankCenterId = bloodBankCenterId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
