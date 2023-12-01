package com.salud.equipoT.controller;

import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.service.ObraSocialService;
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
@RequestMapping("/obrasocial")
public class ObraSocialController {
   @Autowired
    private ObraSocialService obraSocialService;
    @GetMapping("/registrar")//localhost:8080/obrasocial/registrar
    public String registrar(){
        return "/obrasocial_form";
    }
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,ModelMap modelo){
        try {
            obraSocialService.crearObraSocial(nombre);
            modelo.put("exito", "La Obra Social fue cargada correctamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "obrasocial_form";
        }
        return "index";
    }
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<ObraSocial> obrasociales = obraSocialService.listarObraSociales();
        modelo.addAttribute("obrasociales", obrasociales);
        return "obrasocial_list";
    }
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo){
       modelo.put("obrasocial",obraSocialService.obtenerObraSocialPorId(id));
       return "obrasocial_modificar";
    }
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id,String nombre, ModelMap modelo){
        try {
            obraSocialService.modificarObraSocial(id, nombre);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("obrasocial",obraSocialService.obtenerObraSocialPorId(id));
            modelo.put("error", ex.getMessage());
            return "obrasocial_modificar";
        }
    } 
}
