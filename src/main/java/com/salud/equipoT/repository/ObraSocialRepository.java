package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.ObraSocial;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial,Long>{
    
    

}
