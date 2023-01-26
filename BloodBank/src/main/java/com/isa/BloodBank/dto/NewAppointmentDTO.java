package com.isa.BloodBank.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class NewAppointmentDTO {
    private int bloodBankCenterId;
    private int patientId;

    private LocalDateTime dateTime;

    public NewAppointmentDTO() {
    }

    public NewAppointmentDTO(int bloodBankCenterId, LocalDateTime dateTime, int patientId) {
        this.bloodBankCenterId = bloodBankCenterId;
        this.dateTime = dateTime;
        this.patientId = patientId;
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
