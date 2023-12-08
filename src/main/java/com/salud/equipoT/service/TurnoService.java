package com.salud.equipoT.service;

import com.salud.equipoT.entidad.Doctor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salud.equipoT.entidad.Turno;
import com.salud.equipoT.repository.TurnoRepository;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

  @Autowired
  private TurnoRepository turnoRepositorio;

  @Autowired
  DoctorService doctorServicio = new DoctorService();

  @Transactional
  public Turno crearTurno(Date fechaTurno) {

    Turno turno = new Turno();

    turno.setFechaInicio(fechaTurno);

    turnoRepositorio.save(turno);

    return turno;

  }
  
  /*@Transactional
  private void sacarTurno(Paciente paciente, Disponibilidad disponibilidad){
      Turno turno = new Turno();
      turno.setDisponibilidad(disponibilidad);
      turno.setPaciente(paciente);
      turno.setBaja(false);

      turnoRepository.save(turno);
  }*/

  @Transactional
  public void modificarTurno(Long turnoId, boolean reservado) throws Exception {

    Optional<Turno> respuestaTurno = turnoRepositorio.findById(turnoId);

    if (respuestaTurno.isPresent()) {
      Turno turno = respuestaTurno.get();

      turno.setReservado(reservado);


      turnoRepositorio.save(turno);
    }
  }

  @Transactional
  public void eliminarTurno(Long turnoId){
    turnoRepositorio.deleteById(turnoId);
  }


  
  public Turno[][] generarTurnos(Long DoctorId, String fechaInicial, int[] diasDeAtencion, int[] horariosDeAtencion) {

    Doctor doctor = doctorServicio.getOne(DoctorId);

    List<Turno> calendario = new ArrayList();

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate fecha = LocalDate.parse(fechaInicial, formato); // Convertir LocalDate a Date si es necesario (Java 8 y versiones posteriores)
    Date fechaInicia = java.sql.Date.valueOf(fecha);

    // Crear una matriz para representar los días y horarios de una semana
    Turno[][] semanaCalendario = new Turno[7][13]; // 7 días, 13 horas

    // Crear un Calendar para manejar las fechas
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fechaInicia);

    int diaDeLaSemana = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 0 es domingo, 1 es lunes, ...
    int horario;

    for (int i = 0; i < 7; i++) {
      horario = 8;
      for (int j = 0; j < 13; j++) {

        calendar.set(Calendar.HOUR_OF_DAY, horario);
        
        Date fechaTurno = calendar.getTime();
        System.out.println(fechaTurno);
        Turno turno = crearTurno(fechaTurno);

        semanaCalendario[i][j] = turno;

        if (esDiaDeAtencion(diaDeLaSemana, diasDeAtencion) && esHorarioDeAtencion(horario, horariosDeAtencion)) {

          turno.setDisponible(Boolean.TRUE);

          calendario.add(turno);

          semanaCalendario[i][j] = turno;
        } else {

          turno.setDisponible(Boolean.FALSE);

          calendario.add(turno);

          semanaCalendario[i][j] = turno;
        }

        calendar.add(Calendar.HOUR_OF_DAY, 1); // Avanzar una hora
        horario++;
      }

      calendar.add(Calendar.DAY_OF_MONTH, 1); // Avanzar al siguiente día
      diaDeLaSemana++;
    }

  //  doctor.setTurnos(calendario);

    System.out.println(doctor);

  /* try {
      doctorServicio.modificarDoctor(doctor.getMatricula());

    } catch (Exception e) {

      e.printStackTrace();

    } */ 


//    for (int i = 0; i < 7; i++) {
//      System.out.println("Día " + (i + 1) + ":");
//      for (int j = 0; j < 13; j++) {
//        System.out.println(semanaCalendario[i][j]);
//      }
//    }


    return invertirMatriz(semanaCalendario);
  }

  public Turno[][] mostrarTurnos(Long DoctorId, String fechaInicial) {

    Doctor doctor = doctorServicio.getOne(DoctorId);

    List<Turno> calendarioDoctor = doctor.getTurnosCreados();

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fecha = LocalDate.parse(fechaInicial, formato); // Convertir LocalDate a Date si es necesario (Java 8 y versiones posteriores)
    Date fechaInicia = java.sql.Date.valueOf(fecha);

    // Crear una matriz para representar los días y horarios de una semana
    Turno[][] semanaCalendario = new Turno[7][13]; // 7 días, 13 horas

    // Iterar sobre la lista de turnos y asignar cada turno a la matriz según su fecha y hora
    for (Turno turno : calendarioDoctor) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(turno.getFechaInicio());
      
      System.out.println(turno.getFechaInicio());
      
      System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

      if (turno.getFechaInicio().compareTo(fechaInicia) >= 0) {
        int posicionDia = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int posicionHora = calendar.get(Calendar.HOUR_OF_DAY) - 8;
        
        System.out.println(posicionDia);
        System.out.println(posicionHora);
        
        semanaCalendario[posicionDia][posicionHora] = turno;        
      }      
      
    }

//    for (int i = 0; i < 7; i++) {
//      System.out.println("Día " + (i + 1) + ":");
//      for (int j = 0; j < 13; j++) {
//        System.out.println(semanaCalendario[i][j]);
//      }
//    }

    return invertirMatriz(semanaCalendario);
  }

  public Turno[][] invertirMatriz(Turno[][] matrizOriginal) {
    int filas = matrizOriginal.length;
    int columnas = matrizOriginal[0].length;

    Turno[][] matrizInvertida = new Turno[columnas][filas]; // Crear una nueva matriz con filas y columnas intercambiadas

    // Transponer la matriz original
    for (int i = 0; i < filas; i++) {
      for (int j = 0; j < columnas; j++) {
        matrizInvertida[j][i] = matrizOriginal[i][j];
      }
    }

    return matrizInvertida;
  }

  private boolean esDiaDeAtencion(int diaDeLaSemana, int[] diasDeAtencion) {

    for (int i = 0; i < diasDeAtencion.length; i++) {
      if (diaDeLaSemana == diasDeAtencion[i]) {
        return true;
      }
    }
    return false;

  }

  private boolean esHorarioDeAtencion(int horario, int[] horariosDeAtencion) {

    for (int i = 0; i < horariosDeAtencion.length; i++) {
      if (horario == horariosDeAtencion[i]) {
        return true;
      }
    }
    return false;
  }

}
