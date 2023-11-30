package com.salud.equipoT.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class PortalController {

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
    @PreAuthorize("isAnonymous()")
    @GetMapping("/login2")
    public String login2() {
        return "login.html";
    }
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal().toString());
        if (auth.getPrincipal() == "anonymousUser") {
            // Si el usuario está autenticado y tiene roles asignados, redirige a la página de inicio
            return "login.html";
        }
        
        // Si el usuario no tiene roles o no está autenticado, muestra la página de login
        return "redirect:/inicio";
    }
}
