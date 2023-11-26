package com.salud.equipoT.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salud.equipoT.entidad.Especializacion;

@Repository
public interface EspecializacionRepository extends JpaRepository<Especializacion,Long>{


}
