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
}
