package com.salud.equipoT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.service.ObraSocialService;
import com.salud.equipoT.service.PacienteService;

@Controller("/")
public class PortalController {
@Autowired
private ObraSocialService obraSocialService;
    @Autowired
    private PacienteService pacienteService;
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
    public String perfil(ModelMap model){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Verifica si el usuario está autenticado y no es un usuario anónimo
        if (auth.isAuthenticated() && !(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"))) {
            // Obtén los detalles del usuario
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            Paciente paciente = pacienteService.findByEmail(userDetails.getUsername());
            List<ObraSocial> obraSociales = obraSocialService.listarObraSociales();
            model.put("paciente", paciente);
            model.put("obrasociales", obraSociales);
        return "perfil.html";
    }else return "login.html";



}
}
