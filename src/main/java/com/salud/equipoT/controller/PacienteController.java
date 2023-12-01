package com.salud.equipoT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salud.equipoT.entidad.Paciente;
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
            @RequestParam String password,@RequestParam String password2, Long obraSocialId, @RequestParam (required = false)MultipartFile imagen,ModelMap model) throws Exception{
                try {
                            pacienteService.crearPaciente(id, nombre, email, password,password2, obraSocialId,imagen);

                            model.put("exito", "Usuario registrado correctamente");
                } catch (Exception e) {
                    
                    model.put("error", e.getMessage());
                    model.put("nombre", nombre);
                    model.put("email", email);

                    return "registrar.html";
                }

        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model){
        if(error != null){
            model.put("error", "Eusuario o Contrase;a invalidos!");
            return  "login.html";
        }

        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Paciente> pacientes=pacienteService.listarPacientes();
        modelo.addAttribute("pacientes", pacientes);
        return "paciente_list";
    }
  
}
