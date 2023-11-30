package com.salud.equipoT.controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.salud.equipoT.entidad.Foto;
import com.salud.equipoT.service.CloudinaryService;
import com.salud.equipoT.service.FotoService;

@RestController
@RequestMapping("/foto")
public class FotoController {
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private FotoService fotoService;

    @PostMapping()
    public ResponseEntity<String> guardarImagen(@RequestParam MultipartFile foto) throws IOException{

        BufferedImage image = ImageIO.read(foto.getInputStream());

        if (image == null) {
            return new ResponseEntity(new String("imagen no valida"),HttpStatus.BAD_REQUEST);
            
        }
        Map resultado = cloudinaryService.subirFoto(foto);
        Foto nuevaFoto = new Foto();
        nuevaFoto.setName((String) resultado.get("original_filename"));
        nuevaFoto.setUrl((String) resultado.get("url"));
        nuevaFoto.setImagenId((String) resultado.get("public_id"));
        
        fotoService.subirFoto(nuevaFoto);
        return new ResponseEntity(new String("imagen cargada"),HttpStatus.OK);
    }
    
}
