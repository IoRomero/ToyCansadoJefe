package com.salud.equipoT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Consulta;
import com.salud.equipoT.repository.ConsultaRepository;
import com.salud.equipoT.repository.DoctorRepository;
import com.salud.equipoT.repository.HistoriaClinicaRepository;
import com.salud.equipoT.repository.PacienteRepository;

@Service
public class ConsultaService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    public void crearConsulta(String diagnostico, String tratamiento, String motivoConsultar,Long doctorId,Long pacienteId,Long historialClinicaId){

        Consulta consulta = new Consulta();
        
        consulta.setDiagnostico(diagnostico);
        consulta.setTratamiento(tratamiento);
        consulta.setMotivoConsulta(motivoConsultar);
        consulta.setDoctor(doctorRepository.findById(doctorId).orElse(null));
        consulta.setPaciente(pacienteRepository.findById(pacienteId).orElse(null));
        consulta.setHistoriaClinica(historiaClinicaRepository.findById(historialClinicaId).orElse(null));
        
        consultaRepository.save(consulta);
    }

    public List<Consulta> historiaClinica(Long historiaClinicaId){

        return consultaRepository.historialClinicoPaciente(historiaClinicaId);
        
    }

    public Consulta findById(Long id){
    
    return consultaRepository.findById(id).orElse(null);
    }

    public void borrarConsulta(Long id){

        consultaRepository.deleteById(id);
    }
    
}