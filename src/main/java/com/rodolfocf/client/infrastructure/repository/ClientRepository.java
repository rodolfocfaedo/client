package com.rodolfocf.client.infrastructure.repository;


import com.rodolfocf.client.infrastructure.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByEmail(String email);
    Optional<Client> findByEmail(String email);

    @Transactional
    void deleteClientByEmail(String email);
    

}
