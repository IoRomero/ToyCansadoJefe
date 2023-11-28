package com.salud.equipoT.service;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salud.equipoT.entidad.Disponibilidad;
import com.salud.equipoT.repository.DisponibilidadRepository;

@Service
public class DisponibilidadService {

  @Autowired
  private DisponibilidadRepository dispoRepositorio;

  @Transactional
  public void crearDisponibilidad(Date fechaDispo) {

    Disponibilidad dispo = new Disponibilidad();

    dispo.setFechaInicio(fechaDispo);
    
    dispoRepositorio.save(dispo);

  }

  public Date[][] generarDisponibilidades(String fechaInicial, int duracionTurno) {

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate fecha = LocalDate.parse(fechaInicial, formato); // Convertir LocalDate a Date si es necesario (Java 8 y versiones posteriores)
    Date fechaInicia = java.sql.Date.valueOf(fecha);

    // Crear una matriz para representar los días y horarios de una semana
    Date[][] semanaCalendario = new Date[7][10]; // 7 días, 10 horas

    // Crear un Calendar para manejar las fechas
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fechaInicia);

    // Llenar la matriz con fechas y horarios
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 10; j++) {

        if (j == 0) {
          calendar.set(Calendar.HOUR_OF_DAY, 8);
        } else if (j == 5) {
          calendar.set(Calendar.HOUR_OF_DAY, 16);
        }

        Date fechaDispo = (Date) calendar.getTime();      

        semanaCalendario[i][j] = fechaDispo;

        calendar.add(Calendar.HOUR_OF_DAY, 1); // Avanzar una hora
      }

      calendar.add(Calendar.DAY_OF_MONTH, 1); // Avanzar al siguiente día
    }

    for (int i = 0; i < 7; i++) {
      System.out.println("Día " + (i + 1) + ":");
      for (int j = 0; j < 10; j++) {
        System.out.println(semanaCalendario[i][j]);
      }
    }

    return invertirMatriz(semanaCalendario);
  }

  public Date[][] invertirMatriz(Date[][] matrizOriginal) {
    int filas = matrizOriginal.length;
    int columnas = matrizOriginal[0].length;

    // Crear una nueva matriz con filas y columnas intercambiadas
    Date[][] matrizInvertida = new Date[columnas][filas];

    // Transponer la matriz original
    for (int i = 0; i < filas; i++) {
      for (int j = 0; j < columnas; j++) {
        matrizInvertida[j][i] = matrizOriginal[i][j];
      }
    }

    return matrizInvertida;
  }

}