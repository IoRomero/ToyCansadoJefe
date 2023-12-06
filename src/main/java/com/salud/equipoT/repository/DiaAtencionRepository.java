package com.salud.equipoT.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salud.equipoT.entidad.DiaAtencion;


public interface DiaAtencionRepository extends JpaRepository<DiaAtencion,Long>{
    Optional<DiaAtencion> findById(Long id);

    @Query("SELECT da FROM DiaAtencion da WHERE da.doctor.id =:id")
    List<DiaAtencion> buscarDiaAtencionPorDoctor(@Param("id") Long id);

    @Modifying
    @Query("UPDATE DiaAtencion d SET d.dia = :dia WHERE d.id = :id")
    void    editarDiaAtencion(@Param("id") Long id,
            @Param("dia") String dia);
}
