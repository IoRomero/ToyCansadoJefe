package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Consulta;
import com.salud.equipoT.entidad.Imagen;
import com.salud.equipoT.entidad.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findById(Long dni);
    
    @Query("SELECT P FROM Paciente P WHERE P.nombre =: nombre")
    Paciente findByNombre(@Param("nombre") String nombre);

    @Query("SELECT P From Paciente P WHERE P.email =:email")
    Paciente findByEmail(@Param("email") String email);

    @Query("SELECT P.historiaClinica.id FROM Paciente P WHERE P.id = :id")
    List<Consulta> findHistoriaClinica(@Param("id") Long dni);

    @Query("SELECT P FROM Paciente P WHERE P.obraSocial.id = :obraSocialId")
    List<Paciente> findByObraSocial(@Param("obraSocialId") Long obraSocialId);
    
    @Modifying
    @Query("UPDATE Paciente p SET p.nombre = :nombre, p.email = :email, p.password = :password, p.obraSocial.id = :obraSocial, p.imagen = :imagen WHERE p.dni = :dni")
    void    editarPaciente(@Param("dni") Long dni,
            @Param("nombre") String nombre,
            @Param("email") String email,
            @Param("password") String password,
            @Param("obraSocial") Long obraSocial,
            @Param("imagen") Imagen imagen);


    List<Paciente> findAll();

    
@Modifying
    @Query("UPDATE Paciente p SET p.nombre = :nombre, p.email = :email, p.password = :password, p.obraSocial.id = :obraSocial WHERE p.dni = :dni")
    void    editarPacienteSinImagen(@Param("dni") Long dni,
            @Param("nombre") String nombre,
            @Param("email") String email,
            @Param("password") String password,
            @Param("obraSocial") Long obraSocial);


}
