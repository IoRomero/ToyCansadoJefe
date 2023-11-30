package com.salud.equipoT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.repository.DoctorRepository;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    
     public List<Doctor> buscarDoctoresPorEspecializacion(Long id){

        return doctorRepository.buscarDoctoresPorEspecializacion(id);
    }
    public Doctor getOne(Long matricula){
        return doctorRepository.getOne(matricula);
    } 
}
