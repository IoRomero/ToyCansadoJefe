package com.salud.equipoT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    

    
    @Query("SELECT d FROM Doctor d WHERE d.especializacion.id =:id")
    List<Doctor> buscarDoctoresPorEspecializacion(@Param("id") Long id);
    
}
