package com.salud.equipoT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salud.equipoT.entidad.Imagen;
import com.salud.equipoT.service.PacienteService;

@Controller
@RequestMapping("/paciente")
@Validated
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/registrar")
    public String registrarPaciente() {
        return "registrar.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam Long id, @RequestParam String nombre, @RequestParam String email,
            @RequestParam String password, Long obraSocialId,Imagen imagen) {

        pacienteService.crearPaciente(id, nombre, email, password, obraSocialId,imagen);

        return "index.html";
    }
}
