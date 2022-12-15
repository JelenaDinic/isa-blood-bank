package com.isa.BloodBank.model;

import javax.persistence.*;
import java.sql.Timestamp;
import com.isa.BloodBank.model.Person;


@Entity
@Table(name = "verification_token")
public class VerifiactionToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String token;

    @Column(name = "expiry_date")
    private Timestamp expiryDate;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private RegisteredUser user;

    public VerifiactionToken(){

    }

    public VerifiactionToken(String token, RegisteredUser user){
        this.token = token;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public RegisteredUser getPerson() {
        return user;
    }

    public void setPerson(RegisteredUser user) {
        this.user = user;
    }
}
