package com.salud.equipoT.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.entidad.Especializacion;
import com.salud.equipoT.entidad.Turno;



@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    

    @Query("SELECT d FROM Doctor d WHERE d.especializacion = :especializacion")
    public Doctor buscarDoctorPorEspecializacion(@Param("especializacion") Especializacion especializacion);

    @Query("SELECT d FROM Doctor d WHERE d.especializacion = :especializacion")
    public List<Doctor> buscarNombresPorEspecializacion(@Param("especializacion") Especializacion especializacion);

    @Query("SELECT d FROM Doctor d WHERE d.nombre = :nombre")
    public List<Doctor> buscarDoctorPorNombre(@Param("nombre") String nombre);

    @Query("SELECT d FROM Doctor d WHERE d.email = :email")
    public Doctor buscarDoctorPorEmail(@Param("email") String email);

    @Query("SELECT d FROM Doctor d WHERE d.telefono = :telefono")
    public Doctor buscarDoctorPorTelefono(@Param("telefono") String telefono);

    @Query("SELECT d FROM Doctor d WHERE d.id = :id")
    public Doctor buscarDoctorPorID(@Param("id") Long id);

    @Query("SELECT d FROM Doctor d WHERE d.especializacion = :especializacion")
    public List<Doctor> listarDoctorPorEspecializacion(@Param("especializacion") Especializacion especializacion);
    List<Doctor> findByActivoTrue();

    @Query("SELECT d.turnosCreados FROM Doctor d WHERE d.id = :id")
    List<Turno> listarTurnosCreados(@Param("id") Long id);
}
