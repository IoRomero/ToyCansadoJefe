/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salud.equipoT.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Pablo A. Nista Casanova
 */
@Entity
@Data
@Table(name = "imagen")
public class Imagen implements Serializable {
    @Id
    @Column(name = "id" ,length = 36)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String mime;
    private String nombre;
    @Lob @Basic(fetch=FetchType.LAZY)
    private byte[] contenido;
}
