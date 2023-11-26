package com.salud.equipoT.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salud.equipoT.service.DisponibilidadService;

@Controller
@RequestMapping("/turnero")
public class DisponibilidadController {

  @Autowired
  DisponibilidadService dispoServicio = new DisponibilidadService();

  @GetMapping("/elegir")
  public String turnero(ModelMap modelo) {
    
    Date[][] semanaCalendario = dispoServicio.generarDisponibilidades("01/10/1990", 30);

    modelo.put("turnero", semanaCalendario);

    return "turnos.html";
  }


}