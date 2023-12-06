package com.salud.equipoT.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salud.equipoT.entidad.DiaAtencion;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.repository.DiaAtencionRepository;


@Service
public class DiaAtencionService {
    @Autowired
    private DiaAtencionRepository diaAtencionRepository;
    @Transactional
    public void crearDiaAtencion (String dia) throws MiException{
        validar(dia);
        DiaAtencion diaAtencion = new DiaAtencion();
        diaAtencion.setDia(dia);
         diaAtencionRepository.save(diaAtencion);
    }
    public List<DiaAtencion> listarDiasAtencion(){
        List<DiaAtencion> diaAtenciones=new ArrayList<>();
        diaAtenciones = diaAtencionRepository.findAll();
        return diaAtenciones;
    }
    
    @Transactional
    public void modificarDiaAtencion(Long id, String dia) throws MiException{
        validar(dia);
       diaAtencionRepository.editarDiaAtencion(id, dia); 
    }
    private void validar(String dia) throws MiException{
        if (dia.isEmpty()|| dia == null) {
             throw new MiException("El día de Atencion no puede ser nulo o estar vacio.");
        }
    }
    public DiaAtencion getOne(Long id){
        return diaAtencionRepository.findById(id).orElse(null);
    }
    public void borrarDiaAtencion(Long id){
        diaAtencionRepository.deleteById(id);;
    }
}
