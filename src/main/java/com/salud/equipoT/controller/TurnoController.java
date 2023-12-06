package com.salud.equipoT.controller;

import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.entidad.Turno;
import com.salud.equipoT.repository.TurnoRepository;
import com.salud.equipoT.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salud.equipoT.service.TurnoService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/turnero")
public class TurnoController {

  @Autowired
  TurnoService turnoServicio = new TurnoService();

  @Autowired
  DoctorService doctorServicio = new DoctorService();

// @GetMapping("/elegir")
//  public String elegirDoctor(ModelMap modelo){
//    
//    Doctor doctor = doctorServicio.crearDoctor(1096L, "Juan");
//    
//    modelo.put("doctor", doctor);
//    
//    
//    return "elegirDoctor";
//  }

  
  @GetMapping("/generar")
  public String turneroGenerar(ModelMap modelo) throws Exception {


    int[] diasAtencion = {1, 3, 4, 5};
    int[] horariosAtencion = {8, 9, 10, 11, 15, 18, 19};

    Turno[][] turnero = turnoServicio.generarTurnos(1L, "07/10/1990", diasAtencion, horariosAtencion);

    modelo.put("turnero", turnero);

    return "turnos.html";
  }

  @GetMapping("/reservar")
  public String turneroReservar(ModelMap modelo) {

    Turno[][] turnero = turnoServicio.mostrarTurnos(1096L, "07/10/1990");

    modelo.put("turnero", turnero);

    return "turnosDoctor.html";
  }

  @PostMapping("/reserva")
  public String reserva(Long turnoId, ModelMap modelo) {

    System.out.println(turnoId);

    try {
      turnoServicio.modificarTurno(turnoId, true);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:/";
  }


}
