package com.salud.equipoT.controller;

import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.service.DoctorService;
import com.salud.equipoT.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/historia-clinica")
public class HistoriaClinicaController {
  
  @Autowired
  PacienteService pacienteServicio = new PacienteService();
  
  @Autowired
  DoctorService doctorServicio = new DoctorService();
  
  @GetMapping("/ver/{pacienteId}")
  public String verHistoriaClinica(@PathVariable Long pacienteId, ModelMap modelo) {
    
    Paciente paciente = pacienteServicio.buscarPaciente(pacienteId);
    
    modelo.put("paciente", paciente);
    
     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    // Verifica si el usuario está autenticado y no es un usuario anónimo
    if (auth.isAuthenticated() && !(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"))) {
      // Obtén los detalles del usuario
      UserDetails userDetails = (UserDetails) auth.getPrincipal();
      Doctor doctor = doctorServicio.findByEmail(userDetails.getUsername());
      
      modelo.put("doctor", doctor);

    }
    

    
    return "verHistoriaClinica";
  }
  
    
}