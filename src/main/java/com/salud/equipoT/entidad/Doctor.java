package com.salud.equipoT.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Pablo A. Nista Casanova
 */
@Entity
@Data
@Table(name="doctor")
public class Doctor implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private Long matricula;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "puntuacion")
    @OneToMany
    private ArrayList<Puntuacion> puntuacion;
    
    
    
}
