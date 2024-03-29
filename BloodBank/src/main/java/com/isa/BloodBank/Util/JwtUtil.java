package com.isa.BloodBank.Util;

import com.isa.BloodBank.model.Person;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.model.SystemAdmin;
import com.isa.BloodBank.repository.StaffRepository;
import com.isa.BloodBank.repository.SystemAdminRepository;
import com.isa.BloodBank.repository.UserRepository;
import com.isa.BloodBank.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String secret = "javatechie";

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SystemAdminRepository systemAdminRepository;

    @Autowired
    private StaffRepository staffRepository;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email) {

        SystemAdmin systemAdmin = systemAdminRepository.findByEmail(email);
        Staff staff = staffRepository.findByEmail(email);

        Map<String, Object> claims = new HashMap<>();
        UserDetails test = userDetailsService.loadUserByUsername(email);
        userDetailsService.loadUserByUsername(email).getAuthorities();
        claims.put("role", userDetailsService.loadUserByUsername(email).getAuthorities().toArray()[1]);
        claims.put("id", userDetailsService.loadUserByUsername(email).getAuthorities().toArray()[0]);

        if(systemAdmin != null){
            if(systemAdmin.isRequiresPasswordChange())
                claims.put("requiresPasswordChange", "true");
            else
                claims.put("requiresPasswordChange", "false");
        }

        if(staff != null) {
            claims.put("bloodBankCenterId", staff.getBloodBankCenter().getId());
        }

        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
