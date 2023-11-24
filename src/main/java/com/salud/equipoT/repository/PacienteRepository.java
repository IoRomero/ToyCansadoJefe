package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Consulta;
import com.salud.equipoT.entidad.Paciente;

import java.util.List;
import java.util.Optional;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long>{

    Optional<Paciente> findById(Long dni);
    
    @Query("SELECT P From Paciente P WHERE P.email =:email")
    Paciente findByEmail(@Param ("email") String email);

    @Query("SELECT P.historiaClinica.id FROM Paciente P WHERE P.id = :id")
    List<Consulta> findHistoriaClinica(@Param ("id") Long dni);
    
    @Query("SELECT P FROM Paciente P WHERE P.obraSocial.id = :obraSocialId")
    List<Paciente> findByObraSocial(@Param ("obraSocialId") Long obraSocialId);

    @Query("UPDATE Paciente P SET P.password = :nuevaPassword WHERE P.id = :id")
    void cambiarPassword(@Param("id") Long dni, @Param("nuevaPassword") String nuevaPassword);

    @Query("UPDATE Paciente P SET P.email = :nuevoEmail WHERE P.id = :id")
    void cambiarEmail(@Param("id") Long dni, @Param("nuevoEmail") String nuevoEmail);


}
