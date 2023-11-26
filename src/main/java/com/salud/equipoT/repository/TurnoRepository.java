package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long>{
 
}