package com.salud.equipoT.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Foto;
import com.salud.equipoT.repository.FotoRepository;

@Service
@Transactional
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;
    

    public void subirFoto(Foto foto){
        fotoRepository.save(foto);
    }

    public void deleteFoto(Long id) {
        fotoRepository.deleteById(id);
    }

    public List<Foto> listarFoto(){
        return fotoRepository.findAll();
    }
    
}
