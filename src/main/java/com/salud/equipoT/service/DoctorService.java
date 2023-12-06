package com.salud.equipoT.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.salud.equipoT.entidad.DiaAtencion;
import com.salud.equipoT.entidad.Doctor;
<<<<<<< HEAD
import com.salud.equipoT.entidad.Turno;
import com.salud.equipoT.repository.DoctorRepository;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorService {

  @Autowired
  private DoctorRepository doctorRepository;

  @Transactional
  public Doctor crearDoctor(Long matricula, String nombre) {
    Doctor doctor = new Doctor();

    doctor.setMatricula(matricula);
    doctor.setNombre(nombre);

    doctorRepository.save(doctor);

    return doctor;
  }

  @Transactional
  public void modificarDoctor(Long doctorId) throws Exception {

    Optional<Doctor> respuestaDoctor = doctorRepository.findById(doctorId);

    if (respuestaDoctor.isPresent()) {
      Doctor doctor = respuestaDoctor.get();

      doctorRepository.save(doctor);
    }
  }

  public List<Doctor> buscarDoctoresPorEspecializacion(Long id) {

    return doctorRepository.buscarDoctoresPorEspecializacion(id);
  }

  public Doctor getOne(Long matricula) {
    return doctorRepository.getOne(matricula);
  }
=======
import com.salud.equipoT.entidad.Especializacion;
import com.salud.equipoT.entidad.Imagen;
import com.salud.equipoT.entidad.Turno;
import com.salud.equipoT.enums.Rol;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.repository.DoctorRepository;
import com.salud.equipoT.repository.EspecializacionRepository;



@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ImagenService imagenService;
    @Autowired
    private EspecializacionRepository especializacionRepository;
    @Transactional
    public void crearDoctor (Long id, String nombre,Long telefono, String email,String password,String password2, Long matricula,Double puntuacion, Double precioConsulta,String idespecializacion, MultipartFile archivo, List<String> atencion,LocalTime horarioInicio, LocalTime horarioFin,String observaciones) throws Exception{
        validar(id,nombre,telefono,email,password,password2,matricula,precioConsulta,idespecializacion,atencion,horarioInicio,horarioFin);
        Doctor doctor = new Doctor();
        Especializacion especializacion=especializacionRepository.getById(idespecializacion);
        List<DiaAtencion> diasAtencion = new ArrayList<>();
        for (String dia : atencion) {
            DiaAtencion diaAtencion = new DiaAtencion();
            diaAtencion.setDia(dia);
            diasAtencion.add(diaAtencion);
            System.out.println(diaAtencion.toString());
        }
        doctor.setId(id);
        doctor.setNombre(nombre);
        doctor.setEmail(email);
        doctor.setPassword(new BCryptPasswordEncoder().encode(password));
        doctor.setMatricula(matricula);
        doctor.setPrecioConsulta(precioConsulta);
        doctor.setPuntuacion(0.0);
        Imagen imagen=imagenService.guardar(archivo);
        doctor.setImagen(imagen);
        doctor.setEspecializacion(especializacion);
        doctor.setAtencion(diasAtencion);
        doctor.setHorarioInicio(horarioInicio);
        doctor.setHorarioFin(horarioFin);
        doctor.setObservaciones(observaciones);
        doctor.setActivo(false);
        doctor.setRol(Rol.DOCTOR);
        doctorRepository.save(doctor);
    }
    public List<Doctor> listarDoctores(){
        List<Doctor> doctores=new ArrayList<>();
        doctores = doctorRepository.findAll();
        return doctores;
    }
    
    @Transactional
    public void modificarDoctor(Long id, String nombre, Long telefono, String email, String password, String password2, Long matricula,
            Double puntuacion, Double precioConsulta, MultipartFile archivo, String idespecializacion,
            List<String> atencion, LocalTime horarioInicio,LocalTime horarioFin, String observaciones,Boolean activo) throws Exception {
        validarModificacion(id, nombre, telefono, email, password, password2, matricula, puntuacion, precioConsulta, idespecializacion,
                atencion,horarioInicio,horarioFin);
        Optional<Doctor> respuestaDoctor = doctorRepository.findById(id);
        Optional<Especializacion> respuestaEspecializacion = especializacionRepository.findById(idespecializacion);
        Doctor doctor = new Doctor();
        Especializacion especializacion = new Especializacion();
        List<DiaAtencion> diasAtencion = new ArrayList<>();
        for (String dia : atencion) {
            DiaAtencion diaAtencion = new DiaAtencion();
            diaAtencion.setDia(dia);
            diasAtencion.add(diaAtencion);
            System.out.println(diaAtencion.toString());
        }
        if (respuestaEspecializacion.isPresent()) {
            especializacion = respuestaEspecializacion.get();
        }
        if (respuestaDoctor.isPresent()) {
            doctor = respuestaDoctor.get();
            doctor.setId(id);
            doctor.setNombre(nombre);
            doctor.setEmail(email);
            doctor.setPassword(new BCryptPasswordEncoder().encode(password));
            doctor.setMatricula(matricula);
            doctor.setPrecioConsulta(precioConsulta);
            doctor.setPuntuacion(puntuacion);
            doctor.setEspecializacion(especializacion);
            String idImagen = null;
            if (doctor.getImagen() != null) {
                idImagen = doctor.getImagen().getId();
            }
            Imagen imagen = imagenService.actualizar(archivo, idImagen);
            doctor.setImagen(imagen);
            doctor.setAtencion(diasAtencion);
            doctor.setHorarioInicio(horarioInicio);
            doctor.setHorarioFin(horarioFin);
            doctor.setObservaciones(observaciones);
            doctor.setActivo(activo);
            doctorRepository.save(doctor);
        }

    }
    public void validar(Long id, String nombre,Long telefono, String email,String password,String password2, Long matricula, Double precioConsulta,String idespecializacion, List<String> atencion, LocalTime horarioInicio, LocalTime horarioFin)throws Exception{

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El Nombre del Doctor no puede ser nulo o estar vacío.");
        }
        if (telefono<=0) {
           throw new MiException("El Telefono del Doctor no puede ser negativo."); 
        }
    
        if (email == null || email.isEmpty()) {
            throw new MiException("El Email del Doctor no puede ser nulo o estar vacío.");
        }
        Doctor doctorExistente = doctorRepository.buscarDoctorPorEmail(email);
        if (doctorExistente != null) {
            throw new MiException("El Email del Doctor ya se encuentra registrado!!!");
        }
    
        if (password == null || password.isEmpty() || password.length() <= 4) {
            throw new MiException("El Password del Doctor no puede estar vacío o ser menor de 4 caracteres.");
        }
    
        if (!password2.equals(password)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales.");
        }
    
        if (matricula == null || matricula <= 0) {
            throw new MiException("La matrícula del Doctor debe ser un número positivo.");
        }
    
        if (precioConsulta == null || precioConsulta < 0) {
            throw new MiException("El Precio de consulta del Doctor debe ser un número no negativo.");
        }
    
        if (idespecializacion == null||idespecializacion.isEmpty()) {
            throw new MiException("La especialización del Doctor no puede ser nula.");
        }
    
        if (atencion == null || atencion.isEmpty()) {
            throw new MiException("La lista de días de atención del Doctor no puede ser nula o estar vacía.");
        }
        if (horarioInicio == null) {
            throw new MiException("El Horario de Inicio de atención del Doctor no puede ser nulo");
        }
        if (horarioFin == null) {
            throw new MiException("El Horario de Final de atención del Doctor no puede ser nulo");
        }
    }
    public void validarModificacion(Long id, String nombre,Long telefono, String email,String password,String password2, Long matricula,Double puntuacion, Double precioConsulta,String idespecializacion, List<String> atencion, LocalTime horarioInicio, LocalTime horarioFin)throws Exception{

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El Nombre del Doctor no puede ser nulo o estar vacío.");
        }
        if (telefono<=0) {
           throw new MiException("El Telefono del Doctor no puede ser negativo."); 
        }
    
        if (email == null || email.isEmpty()) {
            throw new MiException("El Email del Doctor no puede ser nulo o estar vacío.");
        }
    
        if (password == null || password.isEmpty() || password.length() <= 4) {
            throw new MiException("El Password del Doctor no puede estar vacío o ser menor de 4 caracteres.");
        }
    
        if (!password2.equals(password)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales.");
        }
    
        if (matricula == null || matricula <= 0) {
            throw new MiException("La matrícula del Doctor debe ser un número positivo.");
        }
    
        if (puntuacion == null || puntuacion < 0) {
            throw new MiException("La puntuación del Doctor debe ser un número no negativo.");
        }
    
        if (precioConsulta == null || precioConsulta < 0) {
            throw new MiException("El Precio de consulta del Doctor debe ser un número no negativo.");
        }
    
        if (idespecializacion == null||idespecializacion.isEmpty()) {
            throw new MiException("La especialización del Doctor no puede ser nula.");
        }
    
        if (atencion == null || atencion.isEmpty()) {
            throw new MiException("La lista de días de atención del Doctor no puede ser nula o estar vacía.");
        }
        if (horarioInicio == null) {
            throw new MiException("El Horario de Inicio de atención del Doctor no puede ser nulo");
        }
        if (horarioFin == null) {
            throw new MiException("El Horario de Final de atención del Doctor no puede ser nulo");
        }
    }

    public List<Doctor> listarDoctoresActivos(){
        return doctorRepository.findByActivoTrue();
    }
    public List<Doctor> listarTodosDoctores(){
        return doctorRepository.findAll();
    }
    public Doctor doctorPorId(Long id){
        return doctorRepository.getOne(id);
    }
    public void borrarDoctor(Long id){
        doctorRepository.deleteById(id);;
    }
    @Transactional
    public void bajaLogicaDoctor(long id) throws MiException{
        if(id <= 0){
            throw new MiException("No se ha podido dar de Baja al doctor, el ID es Null");
        }
        Optional<Doctor> respuesta=doctorRepository.findById(id);
        if(respuesta.isPresent()){
            Doctor doctor=respuesta.get();
            doctor.setActivo(false);
            doctorRepository.save(doctor);
        }
    }
    @Transactional
    public void altaDoctor(Long id) throws MiException {
        if (id < 0) {
            throw new MiException("El id ingresado debe ser positivo");
        }
        Optional<Doctor> respuesta = doctorRepository.findById(id);
        if (respuesta.isPresent()) {
            Doctor doctor = respuesta.get();
            doctor.setActivo(true); // Establece el estado del profesional como activo
           doctorRepository.save(doctor);
        }
    } 
    public Doctor getOne(Long id){
        return doctorRepository.findById(id).orElse(null);
    }
    public List<Turno> getTurnos(Long id){
      return doctorRepository.listarTurnosCreados(id);
    }

>>>>>>> c08482c86cdbff624e01fa74309e7688b7a87dc0
}
