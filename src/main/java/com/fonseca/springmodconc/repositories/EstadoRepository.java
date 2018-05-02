package com.fonseca.springmodconc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fonseca.springmodconc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
 
}
