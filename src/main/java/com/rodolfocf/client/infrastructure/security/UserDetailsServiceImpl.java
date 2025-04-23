package com.rodolfocf.client.infrastructure.security;

import com.rodolfocf.client.infrastructure.entity.Client;
import com.rodolfocf.client.infrastructure.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + ", not found."));
        return org.springframework.security.core.userdetails.User
                .withUsername(client.getEmail())
                .password(client.getPassword())
                .build();
    }
}
