package com.salud.equipoT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salud.equipoT.entidad.DiaAtencion;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.service.DiaAtencionService;


@Controller
@RequestMapping("/diaatencion")
public class DiaAtencionController {
    @Autowired
    private DiaAtencionService diaAtencionService;
    @GetMapping("/registrar")//localhost:8080/especializacion/registrar
    public String registrar(){
        return "/diaatencion_form";
    }
    @PostMapping("/registro")
    public String registro(@RequestParam String dia,ModelMap modelo){
        try {
            //System.out.println("Nombre: "+nombre);
            diaAtencionService.crearDiaAtencion(dia);;
            modelo.put("exito", "El dia de Atencion fue cargado correctamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "diaatencion_form";
        }
        return "index";
    }
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<DiaAtencion> diaatenciones=diaAtencionService.listarDiasAtencion();
        modelo.addAttribute("diaatenciones", diaatenciones);
        return "diaatencion_list";
    }
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo){
       modelo.put("diaatencion",diaAtencionService.getOne(id));
       return "diaatencion_modificar";
    }
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id,String dia, ModelMap modelo){
        try {
            diaAtencionService.modificarDiaAtencion(id, dia);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("diaatencion",diaAtencionService.getOne(id));
            modelo.put("error", ex.getMessage());
            return "diaatencion_modificar";
        }
    } 
}
