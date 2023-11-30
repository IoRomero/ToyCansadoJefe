package com.salud.equipoT.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {
    
    private Cloudinary cloudinary;
    
    private Map<String, String> valoresMapa = new HashMap<>();
    

    public CloudinaryService(){

        valoresMapa.put("cloud_name", "dprhopips");
                valoresMapa.put("api_key", "894311123961918");
                        valoresMapa.put("api_secret", "SAL6phTWQHcyVFHRN7RYlnKAy70");

                        cloudinary = new Cloudinary(valoresMapa);

    }

    public Map subirFoto(MultipartFile foto) throws IOException{

        File archivo = convertir(foto);
        Map resultado = cloudinary.uploader().upload(archivo, ObjectUtils.emptyMap());
        archivo.delete();

        return resultado;

    }

    public File convertir(MultipartFile foto) throws IOException{

        File archivo = new File(Objects.requireNonNull(foto.getOriginalFilename()));

        FileOutputStream f = new FileOutputStream(archivo);

        f.write(foto.getBytes());
        f.close();

        return archivo;
    }


    public Map delete(String id) throws IOException{

        Map resultado = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    
        return resultado;
    
    }
}