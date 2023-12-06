package com.salud.equipoT.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salud.equipoT.entidad.DiaAtencion;
import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.entidad.Especializacion;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.repository.DiaAtencionRepository;
import com.salud.equipoT.repository.DoctorRepository;
import com.salud.equipoT.service.DiaAtencionService;
import com.salud.equipoT.service.DoctorService;
import com.salud.equipoT.service.EspecializacionService;
import com.salud.equipoT.service.PacienteService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

   /*  @Autowired
    private TurnoService turnoService;*/
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DiaAtencionService diaAtencionService;
    @Autowired
    private EspecializacionService especializacionService;
    @Autowired
    private DoctorService doctorService;


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
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horarioInicio,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horarioFin,
            @RequestParam(required = false) String observaciones,
           
            ModelMap modelo) throws Exception {

        try {
            doctorService.crearDoctor(id, nombre, telefono, email, password, password2, telefono, puntuacion, precioConsulta, idespecializacion, archivo, atencion, horarioInicio, horarioFin, observaciones);

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "doctor_form";
        }
        return "redirect:/login";
    }

    //@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_PROFESIONAL')")
    @GetMapping("/lista") //localhost:8080/doctor/listar
    public String listarProfesionalesActivos(Model model) {
        List<Doctor> doctores = doctorService.listarDoctoresActivos();
        model.addAttribute("doctores", doctores);

        return "doctor_lista";

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/listarAdmin") //localhost:8080/profesional/listar
    public String listarDoctores(Model model) {
        List<Doctor> doctores = doctorService.listarTodosDoctores();
        model.addAttribute("doctores", doctores);

        return "PanelAdminDoctores";
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
        System.out.println("Doctor Email"+doctor.getEmail());
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
            @RequestParam String matricula,
            @RequestParam(required = false) Double puntuacion,
            @RequestParam Double precioConsulta,
            @RequestParam String idespecializacion,
            @RequestParam MultipartFile archivo,
            @RequestParam List<String> atencion,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horarioInicio,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime horarioFin,
            @RequestParam(required = false) String observaciones,
            ModelMap model) throws Exception {

        try {

            doctorService.modificarDoctor(id, nombre, telefono, email, password, password2, telefono, puntuacion, precioConsulta, archivo, idespecializacion, atencion, horarioInicio, horarioFin, observaciones, Boolean.TRUE);
            return "redirect:/login";
        } catch (MiException ex) {
            model.put("error", ex.getMessage());
            return "doctor_perfil";
        }

    }
}