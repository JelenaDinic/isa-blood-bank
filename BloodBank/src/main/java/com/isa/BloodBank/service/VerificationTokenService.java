package com.isa.BloodBank.service;

import com.isa.BloodBank.model.Person;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.VerifiactionToken;
import com.isa.BloodBank.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository){
        this.verificationTokenRepository = verificationTokenRepository;
    }
    @Transactional
    public VerifiactionToken findByToken(String token){
        return verificationTokenRepository.findByToken(token);
    }

    @Transactional
    public VerifiactionToken findbyUser(RegisteredUser user){
        return verificationTokenRepository.findByUser(user);
    }

    @Transactional
    public void save(RegisteredUser user, String token){
        VerifiactionToken verifiactionToken = new VerifiactionToken(token, user);
        verifiactionToken.setExpiryDate(calculateExpiryDate(24*60));
        verificationTokenRepository.save(verifiactionToken);
    }

    private Timestamp calculateExpiryDate(int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
