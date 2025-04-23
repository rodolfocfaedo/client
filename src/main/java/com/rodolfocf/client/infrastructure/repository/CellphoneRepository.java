package com.rodolfocf.client.infrastructure.repository;

import com.rodolfocf.client.infrastructure.entity.Cellphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CellphoneRepository extends JpaRepository<Cellphone, Long> {

    @Transactional
    void deleteById(Long id);

}
