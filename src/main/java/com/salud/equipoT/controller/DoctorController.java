package com.salud.equipoT.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.entidad.Especializacion;
import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.entidad.Turno;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.service.DiaAtencionService;
import com.salud.equipoT.service.DoctorService;
import com.salud.equipoT.service.EspecializacionService;
import com.salud.equipoT.service.ObraSocialService;
import com.salud.equipoT.service.PacienteService;
import com.salud.equipoT.service.TurnoService;

import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/doctor")
public class DoctorController {

   /*  @Autowired
    private TurnoService turnoService;*/
    @Autowired
    private EspecializacionService especializacionService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private ObraSocialService obraSocialService;
    @Autowired
    private TurnoService turnoService;

    @GetMapping("/registrar") 
    public String registrar(Model model) {
        List<Especializacion> especializaciones = especializacionService.listarEspecializaciones();
        model.addAttribute("especializaciones", especializaciones);
        /*List<DiaAtencion> atencion = diaAtencionService.listarDiasAtencion();
        model.addAttribute("atenciones", atencion);*/
        return "doctor_form.html";
    }

    @PostMapping("/registro")
    public String registro(
            @RequestParam Long id,
            @RequestParam String nombre,
            @RequestParam Long telefono,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2,
            @RequestParam String matricula,
            @RequestParam(required = false) Double puntuacion,
            @RequestParam Double precioConsulta,
            @RequestParam String idespecializacion,
            @RequestParam MultipartFile archivo,
            @RequestParam List<String> atencion,
            @RequestParam String horarioInicio,
            @RequestParam String horarioFin,
            @RequestParam(required = false) String observaciones,
            ModelMap modelo) throws Exception {
  
      try {
  
        doctorService.crearDoctor(id, nombre, telefono, email, password, password2, telefono, puntuacion, precioConsulta, idespecializacion, archivo, atencion, horarioInicio, horarioFin, observaciones);
  
      }catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "doctor_form";
        }
        return "redirect:/turnero/generar/" + id;
    }

    //@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_PROFESIONAL')")
    @GetMapping("/listaActivos") //localhost:8080/doctor/listar
    public String listarProfesionalesActivos(Model model) {
        List<Doctor> doctores = doctorService.listarDoctoresActivos();
        model.addAttribute("doctores", doctores);

        return "doctor_list.html";

    }
    @PostMapping("/consulta/crear")
  public String crearConsulta(Long turnoId, ModelMap modelo) {

    Turno turno = turnoService.getOne(turnoId);
    Doctor doctor = doctorService.doctorPorId(turno.getIdDoctor());

    System.out.println(doctor);

    modelo.put("turno", turno);
    modelo.put("doctor", doctor);

    return "consulta_form";
  }

    @GetMapping("/lista") //localhost:8080/profesional/listar
    public String listarDoctores(Model model) {
        List<Doctor> doctores = doctorService.listarTodosDoctores();
        model.addAttribute("doctores", doctores);

        return "doctor_list.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROFESIONAL','ROLE_ADMIN')")
    @GetMapping("/bajaDoctor/{id}")
    public String bajaDoctor(@PathVariable Long id, ModelMap model) throws MiException {
        doctorService.bajaLogicaDoctor(id);
        return "redirect:/doctor/listarAdmin";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/altaDoctor/{id}")
    public String altaDoctor(@PathVariable Long id, ModelMap model) throws MiException {
        doctorService.altaDoctor(id);
        return "redirect:/doctor/listarAdmin";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_DOCTOR','ROLE_ADMIN')")
    @GetMapping("/perfil/{id}") //localhost:8080/Doctor/perfil
    public String mostrarDoctorPerfil(@PathVariable Long id, ModelMap model, HttpSession session) {

        Doctor doctor = doctorService.getOne(id);
        model.addAttribute("doctor", doctor);
        return "doctor_perfil";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_DOCTOR','ROLE_ADMIN')")
    @PostMapping("/perfil/{id}") //localhost:8080/doctor/perfil
    public String mostrarProfesionalPerfil(
            @RequestParam @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam Long telefono,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2,
            @RequestParam Long matricula,
            @RequestParam(required = false) Double puntuacion,
            @RequestParam Double precioConsulta,
          
            @RequestParam MultipartFile archivo,
              @RequestParam String idespecializacion,
            @RequestParam List<String> diasAtencion,
            @RequestParam String horarioInicio,
            @RequestParam String horarioFin,
            @RequestParam(required = false) String observaciones,
            ModelMap model) throws Exception {

        try {

            doctorService.modificarDoctor(id, nombre, telefono, email, password, password2, telefono, puntuacion, precioConsulta, archivo, idespecializacion, diasAtencion, horarioInicio, horarioFin, observaciones, Boolean.TRUE);
            return "redirect:/login";
        } catch (MiException ex) {
            model.put("error", ex.getMessage());
            return "doctor_perfil";
        }

    }
    
    @GetMapping("/eliminar/{id}")
    public String postMethodName(@PathVariable Long id) {
        //TODO: process POST request
        doctorService.borrarDoctor(id);
       return "redirect:../lista";
    }
    
    @GetMapping("/esp/{id}")
    public String docPorEsp(@PathVariable String id,ModelMap model) {
        model.put("doctores", doctorService.findByEspecializacion(id));
        return "doctor_list.html";
    }
    

    @PostMapping("/especializacion/{id}")
    public String doctoresPorEspecializacion(@PathVariable String id,ModelMap model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
        // Verifica si el usuario tiene el rol "ROLE_PACIENTE"
        if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_PACIENTE"))) {
            // Obtén los detalles del paciente
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      List<Doctor> doctores = doctorService.findByEspecializacion(id);
         Paciente paciente = pacienteService.findByEmail(userDetails.getUsername());

            model.put("paciente", paciente);
            model.put("doctores", doctores);
        return "doctor_list.html";
    }} else if (authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_DOCTOR"))) {
            // Si el usuario tiene el rol "ROLE_DOCTOR", redirige a la página de perfil de doctor
            return "redirect:/inicio";
        }
    
    // Si no está autenticado o no tiene un rol válido, redirige al login
    return "redirect:/login.html";
}}