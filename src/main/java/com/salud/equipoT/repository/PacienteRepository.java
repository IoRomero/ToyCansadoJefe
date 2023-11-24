package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long>{

 
    @Query("SELECT P FROM Paciente P WHERE P.obraSocial.id = :obraSocialId")
    List<Paciente> findByObraSocial(@Param ("obraSocialId") Long obraSocialId);

    @Query("UPDATE Paciente P SET P.password = :nuevaPassword WHERE P.id = :id")
    void cambiarPassword(@Param("id") Long dni, @Param("nuevaPassword") String nuevaPassword);

    @Query("UPDATE Paciente P SET P.email = :nuevoEmail WHERE P.id = :id")
    void cambiarEmail(@Param("id") Long dni, @Param("nuevoEmail") String nuevoEmail);


}
