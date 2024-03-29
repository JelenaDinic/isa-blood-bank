package com.isa.BloodBank.service;

import com.isa.BloodBank.model.Person;
import com.isa.BloodBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = repository.findByEmail(email);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        String role = "ROLE_" + user.getRole().toString();
        String id = Integer.toString(user.getId());
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        grantedAuthorities.add(new SimpleGrantedAuthority(id));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail().trim(),
                user.getPassword().trim(),
                grantedAuthorities);
    }
}
