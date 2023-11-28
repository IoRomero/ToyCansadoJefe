package com.salud.equipoT.controller;

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

    @GetMapping("/login")
    public String login() {
        return "autogestion.html";
    }
}
