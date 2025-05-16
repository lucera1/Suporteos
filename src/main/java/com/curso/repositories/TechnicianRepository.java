package com.curso.repositories;

import com.curso.domains.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    Optional<Technician> findByCpf(String cpf);
    Optional<Technician> findByEmail(String email);
}
