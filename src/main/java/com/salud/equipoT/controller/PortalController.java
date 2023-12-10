package com.salud.equipoT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.entidad.Especializacion;
import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.service.DoctorService;
import com.salud.equipoT.service.EspecializacionService;
import com.salud.equipoT.service.ObraSocialService;
import com.salud.equipoT.service.PacienteService;

@Controller("/")
public class PortalController {
@Autowired
private ObraSocialService obraSocialService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private EspecializacionService especializacionService;
    @Autowired
    private DoctorService doctorService;
    
    public String index() {

        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registrar.html";
    }

    
    @GetMapping("/inicio")
    public String inicio(){
        return "index.html";
    }
   
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() == "anonymousUser") {

              // Si el usuario no tiene roles o no está autenticado, muestra la página de login
            return "login.html";
        }

           // Si el usuario está autenticado y tiene roles asignados, redirige a la página de inicio
        return "redirect:/inicio";
    }


@GetMapping("/perfil")
public String perfil(ModelMap model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
        // Verifica si el usuario tiene el rol "ROLE_PACIENTE"
        if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_PACIENTE"))) {
            // Obtén los detalles del paciente
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Paciente paciente = pacienteService.findByEmail(userDetails.getUsername());
            List<ObraSocial> obraSociales = obraSocialService.listarObraSociales();
            List<Especializacion> especializaciones = especializacionService.listarEspecializaciones();
            List<Doctor> doctores = doctorService.listarDoctores();


            model.put("doctores", doctores);
            model.put("paciente", paciente);
            model.put("obrasociales", obraSociales);
            model.put("especializaciones", especializaciones);
            return "perfil.html";
        } else if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_DOCTOR"))) {
            // Si el usuario tiene el rol "ROLE_DOCTOR", redirige a la página de perfil de doctor
            return "redirect:/inicio";
        }
    }
    // Si no está autenticado o no tiene un rol válido, redirige al login
    return "redirect:/login.html";
}
}
