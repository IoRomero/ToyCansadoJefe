package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long>{

    
    
}
