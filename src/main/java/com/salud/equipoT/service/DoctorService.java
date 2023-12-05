package com.salud.equipoT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Doctor;
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
}
