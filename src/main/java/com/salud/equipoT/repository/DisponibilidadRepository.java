package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Disponibilidad;

@Repository
public interface DisponibilidadRepository extends JpaRepository<Disponibilidad,Long>{
    
   /* @Query("SELECT d FROM disponibilidad d WHERE d.id")*/ 
}
