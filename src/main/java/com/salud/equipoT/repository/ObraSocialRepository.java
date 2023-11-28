package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.ObraSocial;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {

    @Query("SELECT o FROM ObraSocial o WHERE o.nombre = :nombre")
    List<ObraSocial> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT o FROM ObraSocial o WHERE o.cobertura > :cobertura")
    List<ObraSocial> findByCoberturaGreaterThan(@Param("cobertura") Long cobertura);

    @Query("SELECT o FROM ObraSocial o ORDER BY o.nombre ASC")
    List<ObraSocial> findByOrderByNombreAsc();

}
