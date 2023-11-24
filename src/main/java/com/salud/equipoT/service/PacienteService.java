package com.salud.equipoT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salud.equipoT.entidad.Consulta;
import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.entidad.Rol;
import com.salud.equipoT.repository.ObraSocialRepository;
import com.salud.equipoT.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ObraSocialRepository obraSocialRepository;

    @Transactional
    public void crearPaciente(Long dni, String nombre, String email, String password, Long obraSocialId) {
        Paciente paciente = new Paciente();
        if(obraSocialId!=null){
            
        paciente.setDni(dni);
        paciente.setNombre(nombre);
        paciente.setEmail(email);
        paciente.setPassword(password);
        paciente.setObraSocial(obraSocialRepository.findById(obraSocialId).orElse(null)); 
        paciente.setRol(Rol.PACIENTE);

        }else{      
        paciente.setDni(dni);
        paciente.setNombre(nombre);
        paciente.setEmail(email);
        paciente.setPassword(password);
        paciente.setObraSocial(null); 
        paciente.setRol(Rol.PACIENTE);
}

        
        pacienteRepository.save(paciente);

    }
    public void cambiarPassword(Long dni,String nuevaPassword){
    
        pacienteRepository.cambiarPassword(dni, nuevaPassword);

    }
    public void eliminarPaciente(Paciente paciente){
        pacienteRepository.delete(paciente);
    }

    public Paciente buscarPaciente(Long dni){

        return pacienteRepository.findById(dni).orElse(null);
    }
   public List<Consulta> buscarHistoriaClinica(Long dni){

        return pacienteRepository.findHistoriaClinica(dni);

}
public List<Paciente> listarPacientesObraSocial(ObraSocial obraSocial){

return pacienteRepository.findByObraSocial(obraSocial.getId());
}

    public void validar(Long id, String nombre, String email, String password) throws Exception{

        if(nombre.isBlank()){
            throw new Exception("el nombre no puede ser nulo o estar vacio");
        }
        if(email.isBlank()){
            
        }
    }
}
