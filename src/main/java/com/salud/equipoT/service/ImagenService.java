/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salud.equipoT.service;

import com.salud.equipoT.entidad.Imagen;
import com.salud.equipoT.repository.ImagenRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Pablo A. Nista Casanova
 */
@Service
public class ImagenService {
    
    @Autowired
    private ImagenRepository imagenRepository;

    public Imagen guardar(MultipartFile archivo) throws Exception{
        if (archivo !=null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepository.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    public Imagen actualizar(MultipartFile archivo, String idImagen) throws Exception{
        if (archivo !=null) {
            try {
                Imagen imagen = new Imagen();
                if (idImagen!=null) {
                    Optional<Imagen> respuesta = imagenRepository.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen=respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepository.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

}
