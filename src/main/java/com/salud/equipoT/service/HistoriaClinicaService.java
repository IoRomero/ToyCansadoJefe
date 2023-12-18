package com.salud.equipoT.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Consulta;
import com.salud.equipoT.entidad.HistoriaClinica;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.repository.HistoriaClinicaRepository;
import com.salud.equipoT.repository.PacienteRepository;

import java.util.Optional;

@Service
public class HistoriaClinicaService {

  @Autowired
  private HistoriaClinicaRepository historiaClinicaRepository;

  @Autowired
  private PacienteService pacienteService;
  @Autowired
  private PacienteRepository pacienteRepository;
  @Transactional
  public HistoriaClinica crearHistoriaClinica(Long pacienteId) {
    Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
    HistoriaClinica historiaClinica = new HistoriaClinica();
    historiaClinica.setPaciente(paciente);
    historiaClinicaRepository.save(historiaClinica);
    return historiaClinica;
  }

  public List<HistoriaClinica> obtenerTodasLasHistoriasClinicas() {
    return historiaClinicaRepository.findAll();
  }

  public HistoriaClinica obtenerHistoriaClinicaPorId(Long id) {
    return historiaClinicaRepository.findById(id).orElse(null);
  }

  public void eliminarHistoriaClinica(Long id) {
    historiaClinicaRepository.deleteById(id);
  }

  public HistoriaClinica buscarHistoriaPorPaciente(Paciente paciente) {

    return historiaClinicaRepository.findByPaciente(paciente);

  }

  @Transactional
  public void agregarConsultaAHistoriaClinica(Long historiaClinicaId, Consulta consulta) {

    Optional<HistoriaClinica> historiaClinicaOptional = historiaClinicaRepository.findById(historiaClinicaId);

    if (historiaClinicaOptional.isPresent()) {

      HistoriaClinica historiaClinica = historiaClinicaOptional.get();
      List<Consulta> consultas = historiaClinica.getConsultas();
      consultas.add(consulta);
      historiaClinica.setConsultas(consultas);
      historiaClinicaRepository.save(historiaClinica);
    } else {
    }
  }

}