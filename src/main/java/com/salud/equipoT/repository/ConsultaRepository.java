package com.salud.equipoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long>{
    


}
