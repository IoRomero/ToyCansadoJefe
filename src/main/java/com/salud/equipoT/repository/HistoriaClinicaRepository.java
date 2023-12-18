package com.salud.equipoT.repository;

import com.salud.equipoT.entidad.HistoriaClinica;
import com.salud.equipoT.entidad.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {

    @Query("SELECT hc FROM HistoriaClinica hc WHERE hc.paciente = :paciente")
    HistoriaClinica findByPaciente(@Param("paciente") Paciente paciente);

    @Query("SELECT hc FROM HistoriaClinica hc JOIN hc.consultas c WHERE hc.paciente = :paciente ORDER BY c.id ASC")
    List<HistoriaClinica> findByPacienteOrderByConsultaIdAsc(@Param("paciente") Paciente paciente);

}