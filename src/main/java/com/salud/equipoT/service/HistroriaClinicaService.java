package com.salud.equipoT.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Consulta;
import com.salud.equipoT.entidad.HistoriaClinica;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.repository.HistoriaClinicaRepository;

@Service
public class HistroriaClinicaService {
    
    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Transactional
    public void crearHistoriaClinica(Paciente paciente, List<Consulta> consultas) {
        HistoriaClinica historiaClinica = new HistoriaClinica();
        historiaClinica.setPaciente(paciente);
        historiaClinica.setConsultas(consultas);
        historiaClinicaRepository.save(historiaClinica);
    }

    public List<HistoriaClinica> obtenerTodasLasHistoriasClinicas() {
        return historiaClinicaRepository.findAll();
    }

    public HistoriaClinica obtenerHistoriaClinicaPorId(Long id) {
        return historiaClinicaRepository.findById(id).orElse(null);
    }

    public void guardarHistoriaClinica(HistoriaClinica historiaClinica) {
        historiaClinicaRepository.save(historiaClinica);
    }

    public void eliminarHistoriaClinica(Long id) {
        historiaClinicaRepository.deleteById(id);
    }

    public List<HistoriaClinica> buscarPorPaciente(Paciente paciente) {
        
       return historiaClinicaRepository.findByPaciente(paciente);
  
    }
    
}
