package com.isa.BloodBank.model;

public class BloodSupply {
    private String id;
    private String idCenter;
    private String amount;
    private BloodType type;

    public BloodSupply() {
    }

    public BloodSupply(String id, String idCenter, String amount, BloodType type) {
        this.id = id;
        this.idCenter = idCenter;
        this.amount = amount;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCenter() {
        return idCenter;
    }

    public void setIdCenter(String idCenter) {
        this.idCenter = idCenter;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BloodType getType() {
        return type;
    }

    public void setType(BloodType type) {
        this.type = type;
    }
}
