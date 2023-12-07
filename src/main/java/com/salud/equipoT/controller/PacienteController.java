package com.salud.equipoT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salud.equipoT.entidad.Imagen;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.service.ObraSocialService;
import com.salud.equipoT.service.PacienteService;

@Controller
@RequestMapping("/paciente")
@Validated
public class PacienteController {

    @Autowired
    private ObraSocialService obraSocialService;
    @Autowired
    PacienteService pacienteService;

    @GetMapping("/registrar")
    public String registrarPaciente(ModelMap model) {
        model.put("obrasociales",obraSocialService.listarObraSociales());
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
    @PostMapping("/modificar/{id}")
    public String modificar(Long dni, String nombre, String email, String password, String password2, Long obraSocialId,MultipartFile imagen, ModelMap modelo){
        try {
            pacienteService.editarPaciente(dni, nombre, email, password, password2, obraSocialId,imagen);
            return "redirect:../lista";
        } catch (Exception ex) {
            modelo.put("obrasociales",pacienteService.buscarPaciente(dni));
            modelo.put("error", ex.getMessage());
            return "obrasocial_modificar.html";
        }
    }
   

    @GetMapping("/perfil/{id}")
    public String perfil(Long id,ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Verifica si el usuario está autenticado y no es un usuario anónimo
        if (auth.isAuthenticated() && !(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"))) {
           
            
            // Aquí puedes acceder a los atributos del usuario
            Paciente paciente = pacienteService.buscarPaciente(id);
            // Ejemplo: String email = userDetails.getEmail();

            // Agrega los atributos del usuario al ModelMap para ser mostrados en la vista
            model.addAttribute("paciente", paciente.getDni());
            // model.addAttribute("email", email);

            // Redirige a la vista que mostrará los detalles del usuario
            return "perfil.html"; // Nombre de la vista que mostrará los detalles del perfil
        } else {
            // Si el usuario no está autenticado o es un usuario anónimo, redirige a la página de login
            return "login.html"; // Nombre de la vista de login
        }
    }
    
}
