package com.salud.equipoT.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Disponibilidad;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.entidad.Turno;
import com.salud.equipoT.repository.TurnoRepository;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    
    @Transactional
    private void sacarTurno(Paciente paciente, Disponibilidad disponibilidad){
        Turno turno = new Turno();
        turno.setDisponibilidad(disponibilidad);
        turno.setPaciente(paciente);
        turno.setBaja(false);

        turnoRepository.save(turno);
    }

    
}
