package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Imagen;
import com.salud.equipoT.entidad.ObraSocial;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {

    Optional<ObraSocial> findById(Long id);
    
    @Query("SELECT o FROM ObraSocial o WHERE o.nombre = :nombre")
    List<ObraSocial> findByNombre(@Param("nombre") String nombre);

   /*  @Query("SELECT o FROM ObraSocial o WHERE o.cobertura > :cobertura")
    List<ObraSocial> findByCoberturaGreaterThan(@Param("cobertura") Long cobertura);
*/
    @Query("SELECT o FROM ObraSocial o ORDER BY o.nombre ASC")
    List<ObraSocial> findByOrderByNombreAsc();

    @Modifying
    @Query("UPDATE ObraSocial p SET p.nombre = :nombre WHERE p.id = :id")
    void    editarObraSocial(@Param("id") Long id,
            @Param("nombre") String nombre);
 
}
