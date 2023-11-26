package com.salud.equipoT.controller;

import com.salud.equipoT.entidad.Especializacion;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.service.EspecializacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/especializacion")
public class EspecializacionController {
   @Autowired
    private EspecializacionService especializacionService;
    @GetMapping("/registrar")//localhost:8080/especializacion/registrar
    public String registrar(){
        return "/especializacion_form";
    }
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,ModelMap modelo){
        try {
            //System.out.println("Nombre: "+nombre);
            especializacionService.crearEspecializacion(nombre);
            modelo.put("exito", "La Especializacion fue cargada correctamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "especializacion_form";
        }
        return "index";
    }
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Especializacion> especializaciones = especializacionService.listarEspecializaciones();
        modelo.addAttribute("especializaciones", especializaciones);
        return "especializacion_list";
    }
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
       modelo.put("especializacion",especializacionService.getOne(id));
       return "especializacion_modificar";
    }
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,String nombre, ModelMap modelo){
        try {
            especializacionService.modificarEspecializacion(id, nombre);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("especializacion",especializacionService.getOne(id));
            modelo.put("error", ex.getMessage());
            return "especializacion_modificar";
        }
    } 
}
