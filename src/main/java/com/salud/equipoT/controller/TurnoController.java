package com.salud.equipoT.controller;

import com.salud.equipoT.entidad.DiaAtencion;
import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.entidad.Turno;
import com.salud.equipoT.repository.DoctorRepository;
import com.salud.equipoT.repository.TurnoRepository;
import com.salud.equipoT.service.DoctorService;
import com.salud.equipoT.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salud.equipoT.service.TurnoService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/turnero")
public class TurnoController {

  @Autowired
  TurnoService turnoServicio = new TurnoService();

  @Autowired
  DoctorService doctorServicio = new DoctorService();

  @Autowired
  PacienteService pacienteServicio = new PacienteService();

  @GetMapping("/generar/{doctorId}")
  public String turneroGenerar(@PathVariable Long doctorId, ModelMap modelo) throws Exception {

    LocalDate hoy = LocalDate.now();

    String domingoSemanaAnterior = turnoServicio.obtenerDomingoEnString(hoy);

    Doctor doctor = doctorServicio.getOne(doctorId);
    
    System.out.println(doctor);

    boolean existenTurnosParaFechaInicial = doctorServicio.existenTurnosParaFecha(doctor.getId(), domingoSemanaAnterior);

    if (!existenTurnosParaFechaInicial) {

      int[] diasAtencion = doctorServicio.diasAtencionAInteger(doctor.getDiasAtencion());
      int[] horariosAtencion = doctorServicio.horariosAtencionAInteger(doctor.getHorarioInicio(), doctor.getHorarioFin());

      Turno[][] turnero = turnoServicio.generarTurnos(doctor.getId(), domingoSemanaAnterior, diasAtencion, horariosAtencion);

      modelo.put("turnero", turnero);
      modelo.put("doctor", doctor);

      return "redirect:../../inicio";
    }

    return "redirect:../../inicio";
  }

  @GetMapping("/reservar/{doctorId}")
  public String turneroReservar(@PathVariable Long doctorId, ModelMap modelo) {

    Doctor doctor = doctorServicio.getOne(doctorId);

    modelo.put("doctor", doctor);

    LocalDate hoy = LocalDate.now();

    String domingoSemanaAnterior = turnoServicio.obtenerDomingoEnString(hoy);

    Turno[][] turnero = turnoServicio.mostrarTurnos(doctorId, domingoSemanaAnterior);

    modelo.put("turnero", turnero);

    return "turnosDoctor.html";
  }

  @PostMapping("/reserva")
  public String reserva(@RequestParam("turnoId") Long turnoId, ModelMap modelo
  ) {

    System.out.println(turnoId);

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    // Verifica si el usuario está autenticado y no es un usuario anónimo
    if (auth.isAuthenticated() && !(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"))) {
      // Obtén los detalles del usuario
      UserDetails userDetails = (UserDetails) auth.getPrincipal();
      Paciente paciente = pacienteServicio.findByEmail(userDetails.getUsername());

      System.out.println(paciente);

      try {
        turnoServicio.modificarTurno(turnoId, true, paciente);
      } catch (Exception e) {
        e.printStackTrace();
      }

      return "redirect:/";
    }

    return "redirect:/";
  }

  @GetMapping("/ver-turno/{id}")
  public String verTurno(@PathVariable Long id, ModelMap modelo
  ) {

    Turno turno = turnoServicio.getOne(id);
    Doctor doctor = doctorServicio.doctorPorId(turno.getIdDoctor());

    System.out.println(doctor);

    modelo.put("turno", turno);
    modelo.put("doctor", doctor);

    return "verTurno.html";
  }

}