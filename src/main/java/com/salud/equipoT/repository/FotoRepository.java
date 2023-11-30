package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto,Long> {
    
    


}
