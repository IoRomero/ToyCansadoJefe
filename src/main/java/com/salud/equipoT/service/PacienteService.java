package com.salud.equipoT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salud.equipoT.entidad.Consulta;
import com.salud.equipoT.entidad.Imagen;
import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.entidad.Rol;
import com.salud.equipoT.repository.ObraSocialRepository;
import com.salud.equipoT.repository.PacienteRepository;
import com.salud.equipoT.repository.UsuarioRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ObraSocialRepository obraSocialRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void crearPaciente(Long dni, String nombre, String email, String password, Long obraSocialId, Imagen imagen){
        
        Paciente paciente = new Paciente();
        if (obraSocialId != null) {

            paciente.setDni(dni);
            paciente.setNombre(nombre);
            paciente.setEmail(email);
            paciente.setPassword(password);
            paciente.setObraSocial(obraSocialRepository.findById(obraSocialId).orElse(null));
            paciente.setRol(Rol.PACIENTE);
            paciente.setImagen(imagen);

        } else {
            paciente.setDni(dni);
            paciente.setNombre(nombre);
            paciente.setEmail(email);
            paciente.setPassword(password);
            paciente.setObraSocial(null);
            paciente.setRol(Rol.PACIENTE);
            paciente.setImagen(imagen);

        }

        usuarioRepository.save(paciente);

        pacienteRepository.save(paciente);

    }
    @Transactional
    public void editarPaciente(String dni, String nombre, String email, String password, String obraSocial,Imagen imagen) {
            pacienteRepository.editarPaciente(dni, nombre, email, password, obraSocial,imagen);
        }

   

    @Transactional
    public void eliminarPaciente(Paciente paciente) {
        pacienteRepository.delete(paciente);
    }

    public Paciente buscarPaciente(Long dni) {

        return pacienteRepository.findById(dni).orElse(null);
    }

    public List<Consulta> buscarHistoriaClinica(Long dni) {

        return pacienteRepository.findHistoriaClinica(dni);

    }

    public List<Paciente> listarPacientesObraSocial(ObraSocial obraSocial) {

        return pacienteRepository.findByObraSocial(obraSocial.getId());
    }
    

}
