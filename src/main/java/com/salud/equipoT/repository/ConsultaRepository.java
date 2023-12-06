package com.salud.equipoT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long>{
    
@Query("SELECT c FROM Consulta c WHERE c.historiaClinica.id = id")
List<Consulta> historialClinicoPaciente(@Param("id") Long id);

}
