package com.salud.equipoT.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.service.PacienteService;

@Controller("/")
public class PortalController {

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
        System.out.println(auth.getPrincipal().toString());
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
            
            model.put("paciente", paciente);

        return "perfil.html";
    }else return "login.html";



}
}
