package com.salud.equipoT.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Especializacion;

@Repository
public interface EspecializacionRepository extends JpaRepository<Especializacion,String>{

Optional<Especializacion> findById(String id);

@Modifying
    @Query("UPDATE Especializacion p SET p.nombre = :nombre WHERE p.id = :id")
    void    editarEspecializacion(@Param("id") String id,
            @Param("nombre") String nombre);
}
